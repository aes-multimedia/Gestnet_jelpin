package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.dialogo.DialogoBuscarArticulo;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment6_materiales;

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


public class HiloBusquedaArticulosPorNombre extends AsyncTask<Void, Void, Void> {

    private String mensaje = "",cadena;
    private Context context;
    private ProgressDialog dialog;
    private Cliente cliente;
    private int sitio;

    public HiloBusquedaArticulosPorNombre(Context context, String cadena,int sitio){
        this.context = context;
        this.sitio = sitio;
        this.cadena = cadena;
        try {
            cliente= ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = buscarMateriales();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}') != -1) {
            if (sitio == 0) {
                TabFragment6_materiales.sacarArticulos(mensaje,context);
            }else{
                DialogoBuscarArticulo.sacarArticulos(mensaje,context);
            }

        } else {
            Dialogo.dialogoError("No hay coincidencias",context);
        }

    }


    private String buscarMateriales() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("cadena", cadena);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url = "http://"+cliente.getIp_cliente()+Constantes.URL_BUSCAR_ARTICULOS_POR_NOMBRE;
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
            error.put("estado", 5);
            error.put("mensaje", "Error de conexión, error en lectura");
            contenido = error.toString();
        }
        return contenido;
    }
}
