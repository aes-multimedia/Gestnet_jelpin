package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

public class HiloParteIniciado extends AsyncTask<Void,Void,Void>{
    private String ipInterna = "192.168.0.228";
    private String ipExterna = "80.58.161.135";
    private String ipSgs = "82.144.105.90";
    private String puerto = "8085";
    private String apikey="";
    private int id_user,id_parte;
    private String mensaje="";
    private Context context;

    public HiloParteIniciado(Context context,int id_parte) {
        this.id_parte=id_parte;
        this.context=context;
        try {
            Tecnico t = TecnicoDAO.buscarTodosLosTecnicos(context).get(0);
            id_user = t.getId_tecnico();
            apikey = t.getApikey();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
        super.onPostExecute(aVoid);
    }

    private String iniciar() throws JSONException{
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+ ipSgs+"/api-sgs/v1/mantenimientos/android");
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.addRequestProperty("apikey",apikey);
            uc.addRequestProperty("id",String.valueOf(id_user));
            uc.addRequestProperty("fk_parte",String.valueOf(id_parte));
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MALFORMEDURL";
        } catch (ProtocolException e) {
            e.printStackTrace();
            return "PROTOCOLEXCEPTION";
        } catch (IOException e) {
            try {
                URL urlwsExt = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/mantenimientos/android");
                uc = (HttpURLConnection) urlwsExt.openConnection();
                uc.setDoOutput(true);
                uc.setDoInput(true);
                uc.addRequestProperty("apikey",apikey);
                uc.addRequestProperty("id",String.valueOf(id_user));
                uc.addRequestProperty("id_parte",String.valueOf(id_parte));
                uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                uc.setRequestMethod("POST");
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
