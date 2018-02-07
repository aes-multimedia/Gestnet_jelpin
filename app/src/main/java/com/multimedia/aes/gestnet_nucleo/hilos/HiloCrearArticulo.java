package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HiloCrearArticulo extends AsyncTask<Void,Void,Void> {
    private int fk_parte;
    private String mensaje;
    private Context context;
    private ProgressDialog dialog;
    private String nombre;
    private int unidades,iva,cantidadStock;
    private float precio,coste;

    public HiloCrearArticulo(int fk_parte, String nombre, int unidades, int iva, int cantidadStock, float precio, float coste) {
        this.fk_parte = fk_parte;
        this.nombre = nombre;
        this.unidades = unidades;
        this.iva = iva;
        this.cantidadStock = cantidadStock;
        this.precio = precio;
        this.coste = coste;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        return null;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando articulo nuevo.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }


    private String iniciar() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("precio",precio);
        msg.put("coste",coste);
        msg.put("iva",iva);
        msg.put("unidades",unidades);
        msg.put("nombre_en_ese_momento",nombre);
        msg.put("fk_parte",fk_parte);
        msg.put("stock_tecnico",cantidadStock);

        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_CREA_MATERIAL);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        String contenido = "";
        OutputStream os = null;
        try {
            os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(msg.toString());
            osw.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            osw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }

}
