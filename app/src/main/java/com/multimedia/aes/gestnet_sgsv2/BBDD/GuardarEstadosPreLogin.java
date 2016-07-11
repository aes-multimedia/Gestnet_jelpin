package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TipoEstadoDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarEstadosPreLogin {



    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarEstadosPreLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonTipoEstado();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonTipoEstado() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("tipo_estados");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_estado");
            String nombre = jsonArray.getJSONObject(i).getString("nombre_estado");
            if (TipoEstadoDAO.newTipoEstado(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((PreLogin)context).siguienteActivity();
        }else{
            ((PreLogin)context).sacarMensaje("error potencia");
        }

    }
}