package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
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

public class HiloLogin extends AsyncTask<Void,Void,Void>{
    private String mensaje="",tokken = "",imei = "";
    private String login,pass;
    private Context context;
    private Cliente cliente;
    private String ipCliente;
    private ProgressDialog dialog;

    public HiloLogin(String login, String pass,String ipCliente, Context context) {
        this.login = login;
        this.pass = pass;
        this.ipCliente=ipCliente;
        this.context = context;
        try {
            cliente = ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Logueando.");
        Log.d("ip del cliente",cliente.getIp_cliente());
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = logeo();
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
        if (mensaje.indexOf('}')!=-1){
            ((Login)context).guardarUsuario(mensaje);
        }else{
            ((Login)context).sacarMensaje("No se ha devuelto correctamente de la api");
        }

    }

    private String logeo() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("login",login);
        msg.put("pass",pass);
        msg.put("codigoCliente",cliente.getId_cliente());
        URL urlws = null;
        HttpURLConnection uc = null;
        try {

            urlws = new URL(Constantes.URL_LOGIN);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","No se ha podido establecer conexión con el Servidor.\n\nCompruebe su conexión de Datos y/o Wifi\n\nGracias");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","No se ha podido establecer conexión con el Servidor.\n\nCompruebe su conexión de Datos y/o Wifi\n\nGracias");
            return error.toString();
        } catch (IOException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","No se ha podido establecer conexión con el Servidor.\n\nCompruebe su conexión de Datos y/o Wifi\n\nGracias");
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
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error en lectura");
            contenido = error.toString();
        }

        return contenido;
    }
}
