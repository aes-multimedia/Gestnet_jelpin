package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.location.Location;
import android.location.LocationListener;
import android.os.AsyncTask;
import android.os.Bundle;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;

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

public class HiloLoc  extends AsyncTask<Void,Void,Void> implements LocationListener {
    private String ipInterna = "192.168.111.228:8085";
    private String ipExterna = "80.58.161.135:8085";
    private long imei;
    private String lon, lat, time,mensaje;
   // private Context context;



    public HiloLoc(String lon, String lat) {
        //this.context = context;
        this.imei = 95959555;
        this.lon=lon;
        this.lat=lat;
        this.time=String.valueOf(System.nanoTime());
    }
    public void onLocationChanged(Location loc) {
        this.lat = String.valueOf(loc.getLatitude());
        this.lon = String.valueOf(loc.getLongitude());
    }
    @Override
    public void onProviderDisabled(String provider) {
    }
    @Override
    public void onProviderEnabled(String provider) {
    }
    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
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
    private String iniciar() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("imei", imei);
        msg.put("long", lon);
        msg.put("lat", lat);
        msg.put("time", time);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_GEOPOS_EXTERNAPRUEBAS);
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
