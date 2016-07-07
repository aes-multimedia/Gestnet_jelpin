package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarUsoCalderaLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarUsoCalderaLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonMarcaCaldera();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonMarcaCaldera() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("nombre_marcas");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_marca");
            String nombre = jsonArray.getJSONObject(i).getString("nombre_marca");
            if (TipoCalderaDAO.newTipoCaldera(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((Login)context).siguienteActivity();
        }else{
            ((Login)context).sacarMensaje("error marca caldera");
        }
    }
}
