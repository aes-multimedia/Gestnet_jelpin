package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public class GuardarProtocoloAccion {
    private static String json;
    private static Context context;
    private static boolean bien=true;
    private static List<ProtocoloAccion> protocoloAcciones;

    public GuardarProtocoloAccion(Context context, String json) {
        this.context = context;
        GuardarProtocoloAccion.json = json;
        try {
            protocoloAcciones = ProtocoloAccionDAO.buscarTodosLosProtocoloAccion(context);
            guardarJsonProtocoloAccion();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonProtocoloAccion() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("partes");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("acciones");
            for (int j = 0; j < jsonArray1.length(); j++) {
                int id_protocolo_accion;
                if (jsonArray1.getJSONObject(j).getString("fk_accion_protocolo").equals("null") || jsonArray1.getJSONObject(j).getString("fk_accion_protocolo").equals("")) {
                    id_protocolo_accion = -1;
                } else {
                    id_protocolo_accion = jsonArray1.getJSONObject(j).getInt("fk_accion_protocolo");
                }
                boolean esta = false;
                if (protocoloAcciones != null) {
                    for (int k = 0; k < protocoloAcciones.size(); k++) {
                        if (protocoloAcciones.get(k).getId_protocolo_accion() == id_protocolo_accion) {
                            esta = true;
                            break;
                        } else {
                            esta = false;
                        }
                    }
                }
                if (!esta) {

                    int fk_maquina;
                    if(jsonArray1.getJSONObject(j).getString("fk_maquina").equals("null") ||  jsonArray1.getJSONObject(j).getString("fk_maquina").equals("0")){
                        fk_maquina = -1;
                    }else{
                        fk_maquina = jsonArray1.getJSONObject(j).getInt("fk_maquina");
                    }

                    boolean valor;
                    if(jsonArray1.getJSONObject(j).getString("valor").equals("null") ||  jsonArray1.getJSONObject(j).getString("valor").equals("0") ||  jsonArray1.getJSONObject(j).getString("valor").equals("")){
                        valor = false;
                    }else{
                        valor = true;
                    }
                    boolean tipo_accion;
                    if(jsonArray1.getJSONObject(j).getString("tipo_accion").equals("null") ||  jsonArray1.getJSONObject(j).getString("tipo_accion").equals("0") ||  jsonArray1.getJSONObject(j).getString("tipo_accion").equals("")){
                        tipo_accion = false;
                    }else{
                        tipo_accion = true;
                    }

                    int id_accion;
                    if(jsonArray1.getJSONObject(j).getString("id_accion").equals("null") ||  jsonArray1.getJSONObject(j).getString("id_accion").equals("0")){
                        id_accion = -1;
                    }else{
                        id_accion = jsonArray1.getJSONObject(j).getInt("id_accion");
                    }

                    int fk_protocolo;
                    if(jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("null") ||  jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("0")){
                        fk_protocolo = -1;
                    }else{
                        fk_protocolo = jsonArray1.getJSONObject(j).getInt("fk_protocolo");
                    }

                    String descripcion;
                    if(jsonArray1.getJSONObject(j).getString("descripcion").equals("null")){
                        descripcion = "";
                    }else{
                        descripcion = jsonArray1.getJSONObject(j).getString("descripcion");
                    }

                    String nombre_protocolo;
                    if(jsonArray1.getJSONObject(j).getString("nombre_protocolo").equals("null")){
                        nombre_protocolo = "";
                    }else{
                        nombre_protocolo = jsonArray1.getJSONObject(j).getString("nombre_protocolo");
                    }
                    if (ProtocoloAccionDAO.ProtocoloAccion(context,id_protocolo_accion,valor,fk_maquina,fk_protocolo,nombre_protocolo,id_accion,tipo_accion,descripcion)) {
                        bien = true;
                    }
                }
            }
        }
        if (bien){
            new GuardarConfiguracion(context,json);
        }else{
            ((Login)context).sacarMensaje("error al guardar protocolos");
        }
    }
}
