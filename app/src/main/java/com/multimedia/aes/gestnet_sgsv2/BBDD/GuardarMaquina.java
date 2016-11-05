package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarMaquina {
    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarMaquina(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonMaquinaMantenimiento();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonMaquinaMantenimiento() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("mantenimientos");
        JSONArray jsonArray1;
        JSONObject jsonObject1;
        for (int i = 0; i < jsonArray.length(); i++) {
            int fk_parte;
            if (jsonArray.getJSONObject(i).getString("id_parte").equals("null")||jsonArray.getJSONObject(i).getString("id_parte").equals("")){
                fk_parte = -1;
            }else{
                fk_parte = jsonArray.getJSONObject(i).getInt("id_parte");
            }
            jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("maquina");
            for (int j = 0; j < jsonArray1.length(); j++) {
                jsonObject1 = jsonArray1.getJSONObject(j);
                int fk_maquina;
                if (jsonObject1.getString("id_maquina").equals("null")){
                    fk_maquina  =0;
                }else{
                    fk_maquina = jsonObject1.getInt("id_maquina");
                }

                int fk_tipo_maquina;
                if (jsonObject1.getString("fk_tipo_caldera").equals("null")){
                    fk_tipo_maquina  =0;
                }else{
                    fk_tipo_maquina = jsonObject1.getInt("fk_tipo_caldera");
                }
                String modelo_maquina;
                if (jsonObject1.getString("modelo").equals("null")){
                    modelo_maquina  ="";
                }else{
                    modelo_maquina = jsonObject1.getString("modelo");
                }
                int fk_marca_maquina;
                if (jsonObject1.getString("fk_marca").equals("null")){
                    fk_marca_maquina  =0;
                }else{
                    fk_marca_maquina = jsonObject1.getInt("fk_marca");
                }
                int fk_uso_maquina;
                if (jsonObject1.getString("fk_uso").equals("null")||jsonObject1.getString("fk_uso").equals("")||jsonObject1.getString("fk_uso").equals("0")){
                    fk_uso_maquina = 3;
                }else{
                    fk_uso_maquina = jsonObject1.getInt("fk_uso");
                    if (fk_uso_maquina>3){
                        fk_uso_maquina = 3;
                    }
                }
                int fk_potencia_maquina = 0;
                if (jsonObject1.getString("potencia").equals("null")||jsonObject1.getString("potencia").equals("")){
                    fk_potencia_maquina = 0;
                }else{
                    fk_potencia_maquina = PotenciaDAO.buscarPotenciaPorNombre(context,jsonObject1.getString("potencia"));
                }
                String puesta_marcha_maquina;
                if (jsonObject1.getString("puesta_marcha").equals("null")||jsonObject1.getString("puesta_marcha").equals("")){
                    puesta_marcha_maquina = "0000-00-00";
                }else{
                    puesta_marcha_maquina = jsonObject1.getString("puesta_marcha");
                }

                String codigo_maquina;
                if (jsonObject1.getString("codigo_maquina").equals("null")){
                    codigo_maquina="";
                }else{
                    codigo_maquina=jsonObject1.getString("codigo_maquina");
                }
                int bPrincipal;
                if (jsonObject1.getString("bPrincipal").equals("null")){
                    bPrincipal=0;
                }else{
                    bPrincipal=jsonObject1.getInt("bPrincipal");
                }
                String c0_maquina="0";
                String temperatura_max_acs="0";
                String caudal_acs="0";
                String potencia_util="0";
                String temperatura_gases_combustion="0";
                String temperatura_ambiente_local="0";
                String temperatura_agua_generador_calor_entrada="0";
                String temperatura_agua_generador_calor_salida="0";
                String rendimiento_aparato="0";
                String co_corregido="0";
                String co_ambiente="0";
                String tiro="0";
                String co2="0";
                String o2="0";
                String lambda="0";

                if (MaquinaDAO.newMaquinaMantenimiento(context,fk_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
                        modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
                        puesta_marcha_maquina, codigo_maquina, c0_maquina,
                        temperatura_max_acs, caudal_acs, potencia_util,
                        temperatura_gases_combustion, temperatura_ambiente_local,
                        temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
                        rendimiento_aparato, co_corregido, co_ambiente, tiro, co2, o2, lambda,bPrincipal)){
                    bien=true;
                }else{
                    bien=false;
                }
            }

        }
        if (bien){
            ((Login)context).siguienteActivity();
        }else{
            ((Login)context).sacarMensaje("error tipo equipamiento");
        }
    }
}
