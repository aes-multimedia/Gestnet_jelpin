package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.EstadoVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarEstadoVisita {



    private static String Json;
    private static Context context;
    private static boolean bien=true;

    public GuardarEstadoVisita(Context context, String json) {
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
        JSONArray jsonArray = jsonObject.getJSONArray("estadosVisita");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_tipo_endesa");
            String motivo = jsonArray.getJSONObject(i).getString("tipo_endesa");
            String codigo = jsonArray.getJSONObject(i).getString("codigo_compania");
            if (EstadoVisitaDAO.newEstadoVisita(context,id,motivo,codigo)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            new GuardarEquipamientos(context,Json);
        }else{
            ((Login)context).sacarMensaje("error sub tipo visita");
        }
    }
}
