package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarTipoCalderaLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarTipoCalderaLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonTipoCaldera();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonTipoCaldera() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("tipos_caldera");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_tipo_caldera");
            String nombre = jsonArray.getJSONObject(i).getString("descripcion");
            if (TipoCalderaDAO.newTipoCaldera(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            new GuardarMarcaCalderaLogin(context,Json);
        }else{
            ((Login)context).sacarMensaje("error tipo caldera");
        }
    }
}
