package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.nucleo.BuscadorPartes;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

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
import java.sql.SQLException;

public class HiloBuscarPartes extends AsyncTask<Void,Void,Void>{

    private String mensaje="";
    private String num_parte;
    private Context context;
    private ProgressDialog dialog;
    Cliente cliente;

    public HiloBuscarPartes(Context context, String num_parte) {
        this.context = context;
        this.num_parte=num_parte;
        try {
            this.cliente= ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Buscando Partes.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = partes();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        dialog.dismiss();
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject json = new JSONObject(mensaje);
                if (json.getInt("estado")==1){
                    try {
                        ((BuscadorPartes)context).llenarArray(mensaje);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else{
                    ((BuscadorPartes)context).error();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ((BuscadorPartes)context).error();
            }
        }else{
            ((BuscadorPartes)context).error();
        }
        super.onPostExecute(aVoid);
    }

    private String partes() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("num_parte",num_parte);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url="http://"+cliente.getIp_cliente()+Constantes.URL_BUSCAR_PARTES_EXTERNAPRUEBAS;
            urlws = new URL(url);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, IOException");
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
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error en lectura");
            contenido = error.toString();
        }
        return contenido;
    }
}
