package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TipoEstadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarTipoReparacionesLogin {



    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarTipoReparacionesLogin(Context context, String json) {
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
        JSONArray jsonArray = jsonObject.getJSONArray("tipos_reparaciones");

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
            new GuardarTiposVisita(context,Json);
        }else{
            ((Login)context).sacarMensaje("error uso tipo visita");
        }

    }
}
