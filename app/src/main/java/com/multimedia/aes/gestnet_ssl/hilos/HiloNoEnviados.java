package com.multimedia.aes.gestnet_ssl.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.EnvioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Envio;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

import java.net.HttpURLConnection;

public class HiloNoEnviados extends AsyncTask<Void,Void,Void> {

    private String url,mensaje,json;
    private JSONArray jsonArray;
    private Context context;
    private Envio envio;
    Cliente cliente;

    public HiloNoEnviados(Context context, int id) {
        try {
            envio = EnvioDAO.buscarEnvioPorId(context,id);
            url = envio.getUrl_envio();
            jsonArray = new JSONArray(envio.getRequest_envio());
            json = envio.getJson_envio();
            cliente= ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = iniciar();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        try {
            JSONObject jsonObject = new JSONObject(mensaje);
            if (jsonObject.getInt("estado")==1){
                EnvioDAO.borrarEnvioPorID(context,envio.getId_envio());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onPostExecute(aVoid);
    }
    private String iniciar() throws JSONException{
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(url);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                uc.addRequestProperty(jsonObject.getString("nombre"),jsonObject.getString("valor"));
            }
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexion, IOException");
            return error.toString();
        }
        String contenido = "";
        OutputStream os = null;
        try {
            os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(json);
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
