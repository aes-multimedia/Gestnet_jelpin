package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.multimedia.aes.gestnet_nucleo.clases.Presupuesto;
import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.nucleo.Presupuestos;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

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

public class HiloGuardarPresupuesto extends AsyncTask<Void,Void,Void> {



    private String mensaje;
    private Context context;
    private ProgressDialog dialog;
    private Cliente cliente;
    private Presupuesto presupuesto;
    private Gson gson;


    public HiloGuardarPresupuesto(Context context, Presupuesto p, Cliente c) {

        ManagerProgressDialog.cargandoPresupuesto(context);

        this.context=context;
        this.presupuesto=p;
        this.cliente=c;
        gson= new Gson();

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = guardarDatosDelPresupuesto();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }



    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        ManagerProgressDialog.cerrarDialog();
        if (mensaje.indexOf('}')!=-1){
            ((Presupuestos)context).borrarImagenesPorExito("Presupuesto enviado");
        }else{
            ((Presupuestos)context).sacarMensaje("Parte sin documentos");
        }

    }




    private String guardarDatosDelPresupuesto() throws JSONException {
        presupuesto.serializarImagenes();
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url = "http://"+cliente.getIp_cliente()+ Constantes.URL_GUARDAR_PRESUPUESTO;
            urlws = new URL(url);
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
            error.put("mensaje", "Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, IOException");
            return error.toString();
        }
        String contenido = "";
        OutputStream os = null;
        try {
            os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(gson.toJsonTree(presupuesto).toString());
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
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, error en lectura");
            contenido = error.toString();
        }
        return contenido;
    }



}
