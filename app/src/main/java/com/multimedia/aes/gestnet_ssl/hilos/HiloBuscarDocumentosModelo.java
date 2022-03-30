package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.nucleo.AnadirDatosMaquina;

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

public class HiloBuscarDocumentosModelo extends AsyncTask<Void, Void, Void> {

    private String mensaje = "";
    private String modelo;
    private Context context;
    private ProgressDialog dialog;
    private Cliente cliente;


    public HiloBuscarDocumentosModelo(Context context, String modelo) {
        this.context = context;
        this.modelo = modelo;
        try {
            cliente = ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = buscarDocumentos();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (!mensaje.equals("")){
           AnadirDatosMaquina.abrirWebView(mensaje);

        }else{

            Dialogo.dialogoError("No hay documentos para este modelo",context);
            }

        }




    private String buscarDocumentos() throws JSONException {
        JSONObject msg = new JSONObject();
        msg.put("modelo", modelo);
        URL urlws = null;
        HttpsURLConnection uc = null;
        try {
            String url = "https://"+cliente.getIp_cliente()+ Constantes.URL_BUSCAR_DOCUMENTOS_MODELO;
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
            msg.put("estado", 5);
            msg.put("mensaje", "Error de conexión, error en lectura");
            contenido = error.toString();
        }
        return contenido;
    }


}
