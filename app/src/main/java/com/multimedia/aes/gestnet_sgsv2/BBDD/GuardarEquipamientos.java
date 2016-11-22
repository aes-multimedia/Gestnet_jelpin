package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;
import android.util.Log;

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
    private static boolean bien=true;

    public GuardarEquipamientos(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonEquipamientos();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void guardarJsonEquipamientos() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("mantenimientos");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("maquina");
            for (int j = 0; j < jsonArray1.length(); j++) {
                JSONObject jsonObject1=null;
                jsonObject1 = jsonArray1.getJSONObject(j);
                int bPrincipal;
                if (jsonObject1.getString("bPrincipal").equals("null")){
                    bPrincipal=0;
                }else{
                    bPrincipal=jsonObject1.getInt("bPrincipal");
                }
                if (bPrincipal==1) {
                    JSONArray jsonArray2 = jsonArray1.getJSONObject(j).getJSONArray("equipamientosMaquina");
                    if (jsonArray2.toString().equals("[]")) {
                        bien = true;
                    }else{
                        for (int k = 0; k < jsonArray2.length(); k++) {
                            int id = jsonArray2.getJSONObject(k).getInt("id_equipamiento");
                            if (EquipamientoDAO.buscarEquipamientoPorId(context, id) == null) {
                                int fk_maquina = jsonArray2.getJSONObject(k).getInt("fk_maquina");
                                int fk_tipo_equipamiento = jsonArray2.getJSONObject(k).getInt("fk_tipo_equipamiento");
                                String potencia_fuegos = jsonArray2.getJSONObject(k).getString("potencia_fuegos");
                                String codigo_equipamiento = jsonArray2.getJSONObject(k).getString("codigo_equipamiento");
                                String co2_equipamiento = "34";
                                if (EquipamientoDAO.newEquipamiento(context, id, fk_maquina, fk_tipo_equipamiento, potencia_fuegos, codigo_equipamiento, co2_equipamiento)) {
                                    bien = true;
                                } else {
                                    bien = false;
                                }
                            }
                        }
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
