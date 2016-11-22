package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarTiposVisita {

    private static String Json;
    private static Context context;
    private static boolean bien=true;

    public GuardarTiposVisita(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonTiposVisita();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonTiposVisita() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("tipos_visitas");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_tipo_visita");
            String descripcion = jsonArray.getJSONObject(i).getString("descripcion");
            int subTipos = jsonArray.getJSONObject(i).getInt("bSubTipos");
            if (TiposVisitaDAO.newTipoVisita(context,id,descripcion,subTipos)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            new GuardarSubTiposVisita(context,Json);
        }else{
            ((Login)context).sacarMensaje("error uso subtipo visita");
        }
    }

}
