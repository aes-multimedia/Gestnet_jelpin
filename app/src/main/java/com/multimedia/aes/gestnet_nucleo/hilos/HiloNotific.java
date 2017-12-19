package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

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


public class HiloNotific extends AsyncTask<Void,Void,Void> {
    private String mensaje="",tokken = "",imei = "",apikey;
    private int idEntidad;
    private Context context;
    private String ipCliente;


    public HiloNotific(Context context,String tokken,String imei) {
        this.context = context;
        this.tokken = tokken;
        this.imei=imei;
        try {
        this.idEntidad=UsuarioDAO.buscarTodosLosUsuarios(context).get(0).getFk_entidad();
        this.apikey=UsuarioDAO.buscarTodosLosUsuarios(context).get(0).getApi_key();
        this.ipCliente=ClienteDAO.buscarTodosLosClientes(context).get(0).getIp_cliente();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = registrarNotificaciones();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }



    //TO DO
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')==-1){
            ((Login)context).sacarMensaje("No se ha devuelto correctamente de la api");
        }else{
            ((Login)context).hiloPartes();
        }

    }

    private String registrarNotificaciones() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("fk_entidad",idEntidad);
        msg.put("tokken",tokken);
        msg.put("deviceImei",imei);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            String url = Constantes.URL_ALTA_NOTIFICACIONES_EXTERNA_PRUEBAS;
            urlws = new URL(url);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestProperty("id",String.valueOf(idEntidad));
            uc.setRequestProperty("apikey",apikey);
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            msg.put("estado",5);
            msg.put("mensaje","Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            msg.put("estado",5);
            msg.put("mensaje","Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONObject error = new JSONObject();
            msg.put("estado",5);
            msg.put("mensaje","Error de conexión, IOException");
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
            msg.put("estado",5);
            msg.put("mensaje","Error de conexión, error en lectura");
            contenido = error.toString();
        }


        return contenido;
    }


}
