package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;


import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarCliente extends AsyncTask<Void,Void,Void> {
    private static String json;
    private static Context context;
    private static boolean bien=false;
    private ProgressDialog dialog;

    public GuardarCliente(Context context, String json) {
        this.context = context;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Cliente.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonCliente();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (bien){
            ((PreLogin)context).irLogin();
        }else{
            ((PreLogin)context).sacarMensaje("error cliente");
        }
    }
    private static void guardarJsonCliente() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        jsonObject = jsonObject.getJSONObject("cliente");
        int id;
        if (jsonObject.getString("id_cliente").equals("null") || jsonObject.getString("id_cliente").equals("")) {
            id = -1;
        } else {
            id = jsonObject.getInt("id_cliente");
        }
        String nombre;
        if (jsonObject.getString("cliente").equals("null")) {
            nombre = "";
        } else {
            nombre = jsonObject.getString("cliente");
        }
        String color;
        if (jsonObject.getString("color").equals("null")) {
            color = "";
        } else {
            color = jsonObject.getString("color");
        }
        String logo;
        if (jsonObject.getString("logo").equals("null")) {
            logo = "";
        } else {
            logo = jsonObject.getString("logo");
        }
        String ip;
        if (jsonObject.getString("IP").equals("null")) {
            ip = "";
        } else {
            ip = jsonObject.getString("IP");
        }
        String cod_cliente;
        if (jsonObject.getString("cod_cliente").equals("null")) {
            cod_cliente = "";
        } else {
            cod_cliente = jsonObject.getString("cod_cliente");
        }

        String dir_documentos;
        if (jsonObject.getString("dir_documentos").equals("null")) {
            dir_documentos = "";
        } else {
            dir_documentos = jsonObject.getString("dir_documentos");
        }
        String proteccion_datos;
        if (jsonObject.getString("proteccion_datos").equals("null")) {
            proteccion_datos = "";
        } else {
            proteccion_datos = jsonObject.getString("proteccion_datos");
        }
        if (ClienteDAO.newCliente(context,id,nombre,color,logo,ip,cod_cliente,dir_documentos,proteccion_datos)){
            bien = true;
        }
    }
}
