package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.os.AsyncTask;

import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

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

public class HiloParteIniciado extends AsyncTask<Void,Void,Void>{
    private String ipInterna = "192.168.0.228";
    private String ipExterna = "80.58.161.135";
    private String puerto = "8085";
    private String apikey="",id_parte="",id_user="";
    private String mensaje="";
    private Login activity;

    public HiloParteIniciado(Login activity,String apikey,String id_parte,String id_user) {
        this.activity = activity;
        this.apikey=apikey;
        this.id_parte=id_parte;
        this.id_user=id_user;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = logeo();
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
            activity.loginOk(mensaje);
        }else{
            activity.errorDeLogin(mensaje);
        }


    }

    private String logeo() throws JSONException{
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+ ipInterna +":"+puerto+"/api-sgs/v1/usuario/login");
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.addRequestProperty("apikey",apikey);
            uc.addRequestProperty("fk_parte",id_parte);
            uc.addRequestProperty("fk_user",id_user);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("GET");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MALFORMEDURL";
        } catch (ProtocolException e) {
            e.printStackTrace();
            return "PROTOCOLEXCEPTION";
        } catch (IOException e) {
            try {
                URL urlwsExt = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/usuario/login");
                uc = (HttpURLConnection) urlwsExt.openConnection();
                uc.setDoOutput(true);
                uc.setDoInput(true);
                uc.addRequestProperty("apikey",apikey);
                uc.addRequestProperty("fk_parte",id_parte);
                uc.addRequestProperty("fk_user",id_user);
                uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                uc.setRequestMethod("GET");
                uc.connect();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return "MALFORMEDURL";
            } catch (ProtocolException e1) {
                e1.printStackTrace();
                return "PROTOCOLEXCEPTION";
            } catch (IOException e1) {
                e1.printStackTrace();
                return "No se ha podido actualizar en GESTNET,  se intentara mas tarde";
            }
        }
        String contenido = "";
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }
}
