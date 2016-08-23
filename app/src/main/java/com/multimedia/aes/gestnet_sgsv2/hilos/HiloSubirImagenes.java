package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;

import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Imagenes;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class HiloSubirImagenes extends AsyncTask<Void,Void,Void> {

    private Activity activity;
    private String mensaje;
    private String host="192.168.0.228";
    private ArrayList<Imagenes> arraylistImagenes = new ArrayList<>();
    private Mantenimiento mantenimiento = null;
    private int id = 0;

    public HiloSubirImagenes(Activity activity) {
        this.activity = activity;
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(activity));
            id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(activity,id);
            arraylistImagenes.addAll(ImagenesDAO.buscarImagenPorFk_parte(activity,id));
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... params) {
        try {
            mensaje = images();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ((Index)activity).ticket(mensaje);
        try {
            MantenimientoDAO.actualizarEstadoAndroid(activity, 2, mantenimiento.getId_mantenimiento());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private String images() throws JSONException, IOException {
        URL urlws = new URL("http://"+host+"/api_sgs/v1/mantenimientos/foto");
        JSONObject js = new JSONObject();
        JSONArray jsa = new JSONArray();
        for (int i = 0; i <arraylistImagenes.size(); i++) {
            File f=new File(arraylistImagenes.get(i).getRuta_imagen());
            Bitmap b = null;
            b = BitmapFactory.decodeStream(new FileInputStream(f));
            JSONObject jso = new JSONObject();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            b.compress(Bitmap.CompressFormat.JPEG, 80, baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
            try {
                jso.put("name",arraylistImagenes.get(i).getNombre_imagen());
                jso.put("base64",encodedImage);
                jsa.put(jso);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        try {
            js.put("images",jsa);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.addRequestProperty("fk_parte",String.valueOf(id));
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.setRequestMethod("POST");
        uc.connect();
        OutputStream os = uc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(js.toString());
        osw.flush();
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
        String inputLine;
        String contenido = "";
        while ((inputLine = in.readLine()) != null) {
            contenido += inputLine + "\n";
        }
        in.close();
        osw.close();
        return contenido;
    }
}
