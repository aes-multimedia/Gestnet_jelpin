package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.EstadoVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;


public class GuardarEquipamientos {



    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarEquipamientos(Context context, String json) {
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
        JSONArray jsonArray = jsonObject.getJSONArray("mantenimientos");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("maquina");
            for (int j = 0; j < jsonArray1.length(); j++) {
                JSONArray jsonArray2 = jsonArray1.getJSONObject(i).getJSONArray("equipamientosMaquina");
                for (int k = 0; k < jsonArray2.length(); k++) {
                    int id = jsonArray2.getJSONObject(i).getInt("id_equipamiento");
                    int fk_maquina = jsonArray2.getJSONObject(i).getInt("fk_maquina");
                    int fk_tipo_equipamiento = jsonArray2.getJSONObject(i).getInt("fk_tipo_equipamiento");
                    String potencia_fuegos = jsonArray2.getJSONObject(i).getString("potencia_fuegos");
                    String codigo_equipamiento = jsonArray2.getJSONObject(i).getString("codigo_equipamiento");
                    String co2_equipamiento = jsonArray2.getJSONObject(i).getString("co2_equipamiento");
                    if (EquipamientoDAO.newEquipamiento(context,id,fk_maquina,fk_tipo_equipamiento,potencia_fuegos,codigo_equipamiento,co2_equipamiento)){
                        bien=true;
                    }else{
                        bien=false;
                    }
                }
            }

        }
        if (bien){
            new GuardarTipoEquipamiento(context,Json);
        }else{
            ((Login)context).sacarMensaje("error equipamientos");
        }
    }
}
