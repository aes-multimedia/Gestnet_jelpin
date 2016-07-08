package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Created by Sergio on 08/07/2016.
 */
public class GuardarTipoReparacionesPreLogin {



    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarTipoReparacionesPreLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonTipoReparaciones();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonTipoReparaciones() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("tipo_estados");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_tipo_reparacion");
            int codigo = jsonArray.getJSONObject(i).getInt("codigo");
            String nombre = jsonArray.getJSONObject(i).getString("descripcion");
            String abreviatura = jsonArray.getJSONObject(i).getString("abreviatura");
            if (TiposReparacionesDAO.newTiposReparaciones(context,id,codigo,nombre,abreviatura)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((Login)context).siguienteActivity();
        }else{
            ((Login)context).sacarMensaje("error tipo reparaciones");
        }

    }
}
