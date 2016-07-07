package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarMarcaCalderaPreLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarMarcaCalderaPreLogin(Context context, String json) {
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
            if (MarcaCalderaDAO.newMarcaCaldera(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ManagerProgressDialog.guardarDatosUsoCaldera(context);
            new GuardarUsoCalderaPreLogin(context,Json);
        }else{
            ((PreLogin)context).sacarMensaje("error marca caldera");
        }
    }
}
