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
import java.net.URL;

public class HiloLogin extends AsyncTask<Void,Void,Void>{
    private String hostLocal = "192.168.0.228";
    private String hostRed = "80.58.161.135";
    private String puerto = "8085";
    private String mensaje="";
    private String login,pass;
    private Login activity;

    public HiloLogin(String login, String pass, Login activity) {
        this.login = login;
        this.pass = pass;
        this.activity = activity;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = logeo();
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
        if (mensaje.indexOf('}')!=-1){
            activity.loginOk(mensaje);
        }else{
            activity.errorDeLogin(mensaje);
        }


    }

    private String logeo() throws JSONException, IOException {
        JSONObject msg = new JSONObject();
        msg.put("login",login);
        msg.put("pass",pass);
        URL urlws = new URL("http://"+hostRed+":"+puerto+"/api-sgs/v1/usuario/login");
        HttpURLConnection uc = (HttpURLConnection) urlws.openConnection();
        uc.setDoOutput(true);
        uc.setDoInput(true);
        uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
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
}
