package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.nucleo.FotosIntervenciones;
import com.multimedia.aes.gestnet_nucleo.nucleo.IntervencionesAnteriores;

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

public class HiloListarImagenesIntervencion extends AsyncTask<Void,Void,Void> {
    private String mensaje = "";


    private int fk_parte;
    private Context context;
    private ProgressDialog dialog;
    private Cliente cliente;
    private boolean porParte;
    private String URL;

    public HiloListarImagenesIntervencion(Context context, int fk_parte,boolean porParte) {
        this.fk_parte = fk_parte;
        this.context = context;
        this.porParte = porParte;
        try {
            this.cliente = ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if (porParte){
            URL = Constantes.URL_IMAGENES_INTERVENCIONES_ANTERIORES;
        }else{
            URL = Constantes.URL_IMAGENES_INTERVENCIONES_ANTERIORES_USUARIO;
        }
    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Buscando imagenes");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();

        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = partes();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        Log.d("Hilo imagenes inter", String.valueOf(context.getClass()));
        if (mensaje.indexOf('}') != -1) {
            if (context.getClass() == FotosIntervenciones.class) {
                 ((FotosIntervenciones) context).almacenarRutas(mensaje);
            }
        } else {
            ((FotosIntervenciones) context).sacarMensaje("Sin imagenes");

        }

    }


    private String partes() throws JSONException {
        JSONObject msg = new JSONObject();
        if (porParte){
            msg.put("fk_parte", fk_parte);
        }else{
            msg.put("fk_usuario", fk_parte);
        }

        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url = "http://" + cliente.getIp_cliente() + URL;
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
            StringBuilder sb = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                sb.append(inputLine + "\n");
                //contenido += inputLine + "\n";
            }
            contenido = sb.toString();
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
