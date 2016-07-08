package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarPotenciaPreLogin {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarPotenciaPreLogin(Context context, String json) {
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
        JSONArray jsonArray = jsonObject.getJSONArray("potencias");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_potencia");
            String nombre = jsonArray.getJSONObject(i).getString("potencia");
            if (PotenciaDAO.newPotencia(context,id,nombre)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ManagerProgressDialog.guardarTiposEstado(context);
            new GuardarEstadosLogin(context,Json);
        }else{
            ((PreLogin)context).sacarMensaje("error tipo estados");
        }
    }
}
