package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarUsoCalderaPreLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarUsoCalderaPreLogin(Context context, String json) {
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
        JSONArray jsonArray = jsonObject.getJSONArray("usuarios_usos");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_uso");
            String nombre = jsonArray.getJSONObject(i).getString("descripcion");
            if (TipoCalderaDAO.newTipoCaldera(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((PreLogin)context).siguienteActivity();
        }else{
            ((PreLogin)context).sacarMensaje("error marca caldera");
        }
    }
}
