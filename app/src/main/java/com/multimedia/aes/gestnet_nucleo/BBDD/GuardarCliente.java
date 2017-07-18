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
        int id = jsonObject.getInt("id_cliente");
        String nombre = jsonObject.getString("cliente");
        String color = jsonObject.getString("color");
        String logo = jsonObject.getString("logo");
        String ip = jsonObject.getString("IP");
        String cod_cliente = jsonObject.getString("cod_cliente");
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
