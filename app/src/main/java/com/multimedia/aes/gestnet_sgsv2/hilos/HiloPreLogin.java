package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.os.AsyncTask;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

public class HiloPreLogin extends AsyncTask<Void,Void,Void>{
    private String host = "192.168.0.150";
    private String puerto = "99";
    private String mensaje="";
    private String login,pass;
    private PreLogin activity;

    public HiloPreLogin(String login, String pass, PreLogin activity) {
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
        URL urlws = new URL("http://"+host+":"+puerto+"/practicas/apis/api-sgs/v1/usuario/login");
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
