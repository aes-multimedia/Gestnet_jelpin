package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.MotivosNoRepDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarMotivosNoRep {



    private static String Json;
    private static Context context;
    private static boolean bien=true;

    public GuardarMotivosNoRep(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonMotivosNoRep();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void guardarJsonMotivosNoRep() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("motivos_no_rep");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_motivo_cancelacion");
            String motivo = jsonArray.getJSONObject(i).getString("motivo");
            int bCodigo = jsonArray.getJSONObject(i).getInt("bCodigo");
            if (MotivosNoRepDAO.newMotivosNoRep(context,id,motivo,bCodigo)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            new GuardarEstadoVisita(context,Json);
        }else{
            ((Login)context).sacarMensaje("error sub tipo visita");
        }
    }
}
