package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;

public class HiloSubirTicket extends AsyncTask<Void,Void,Void>{
    private String host = "192.168.0.228";
    private String mensaje="";
    private Activity activity;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
    private Mantenimiento mantenimiento=null;

    public HiloSubirTicket(Activity activity) {
        this.activity = activity;
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(activity));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(activity,id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = subir();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        } catch (IOException e) {
            mensaje = "IOException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        Toast.makeText(activity, "Imagen ticket subida", Toast.LENGTH_SHORT).show();
    }

    private String subir() throws JSONException, IOException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject = new JSONObject();
        String base64 = loadTicketFromStorage();
        jsonObject.put("name","ticket");
        jsonObject.put("base64",base64);
        msg.put("images",jsonObject);
        URL urlws = new URL("http://"+host+"/api-sgs/v1/mantenimientos/carga_imagen");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
        uc.addRequestProperty("fk_parte", String.valueOf(mantenimiento.getFk_tecnico()));
        uc.setRequestMethod("POST");
        uc.connect();
        OutputStream os = uc.getOutputStream();
        OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
        osw.write(msg.toString());
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

    private String loadTicketFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "ticket"+mantenimiento.getId_mantenimiento()+".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        b.compress(Bitmap.CompressFormat.PNG, 100, baos);
        byte[] imageBytes = baos.toByteArray();
        String encodedImage = android.util.Base64.encodeToString(imageBytes, android.util.Base64.DEFAULT);
        return encodedImage;
    }
}
