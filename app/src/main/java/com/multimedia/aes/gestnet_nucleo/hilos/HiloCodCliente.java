package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

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

public class HiloCodCliente extends AsyncTask<Void,Void,Void>{
    private String mensaje="";
    private String codCliente;
    private Context context;

    public HiloCodCliente(String codCliente,Context context) {
        this.codCliente =codCliente;
        this.context=context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = codCliente();
       } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
         //   mensaje="{\"estado\":1,\"mensaje\":\"1\"}";
            ((PreLogin)context).guardarCliente(mensaje);
        }else{
            ((PreLogin)context).sacarMensaje("No se ha devuelto correctamente de la api");
        }

    }

    private String codCliente() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("codigo",codCliente);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_COD_CLIENTE);
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
            error.put("mensaje","Error de conexión, error de lectura");
            contenido = error.toString();
        }
        return contenido;
    }
}
