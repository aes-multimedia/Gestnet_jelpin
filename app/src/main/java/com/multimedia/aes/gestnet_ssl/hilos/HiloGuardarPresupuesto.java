package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.multimedia.aes.gestnet_ssl.clases.Presupuesto;
import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.nucleo.Presupuestos;
import com.multimedia.aes.gestnet_ssl.progressDialog.ManagerProgressDialog;

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

import javax.net.ssl.HttpsURLConnection;

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
            try {
                ((Presupuestos)context).borrarImagenesPorExito(mensaje);
            } catch (JSONException e) {
                e.printStackTrace();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
            ((Presupuestos)context).sacarMensaje("Presupuesto no enviado");
        }

    }




    private String guardarDatosDelPresupuesto() throws JSONException {
        presupuesto.serializarImagenes();

        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            String url = "https://"+cliente.getIp_cliente()+ Constantes.URL_GUARDAR_PRESUPUESTO;
            urlws = new URL(url);
            uc = (HttpsURLConnection) urlws.openConnection();
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
            StringBuilder sb = new StringBuilder();
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
