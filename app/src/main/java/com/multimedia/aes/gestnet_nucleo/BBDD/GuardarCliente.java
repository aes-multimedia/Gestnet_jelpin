package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;


import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarCliente {
    private static String json;
    private static Context context;
    private static boolean bien=false;

    public GuardarCliente(Context context, String json) {
        this.context = context;
        GuardarCliente.json = json;
        try {
            guardarJsonCliente();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonCliente() throws JSONException, SQLException {
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
        if (ClienteDAO.newCliente(context,id,nombre,color,logo,ip,cod_cliente)){
            bien = true;
        }
        if (bien){
            ((PreLogin)context).irLogin();
        }else{
            ((PreLogin)context).sacarMensaje("error cliente");
        }
    }
}
