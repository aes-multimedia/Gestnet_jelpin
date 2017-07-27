package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.List;

public class GuardarProtocoloAccion {
    private static String json;
    private static Context context;
    private static boolean bien=false;
    private static List<ProtocoloAccion> protocoloAcciones;

    public GuardarProtocoloAccion(Context context, String json) {
        this.context = context;
        GuardarProtocoloAccion.json = json;
        try {
            protocoloAcciones = ProtocoloAccionDAO.buscarTodosLosProtocolo(context);
            guardarJsonProtocoloAccion();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonProtocoloAccion() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        int estado = Integer.parseInt(jsonObject.getString("estado"));
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
                    int fk_protocolo;
                    int descripcion;
                    int nombre_protocolo;
                    if (ProtocoloAccionDAO.newProtocolo(context,id_protocolo_accion,fk_maquina,fk_protocolo,descripcion,nombre_protocolo)) {
                        bien = true;
                    }
                }
            }
        }
        if (bien){
            new GuardarConfiguracion(context,json);
        }else{
            ((PreLogin)context).sacarMensaje("error al guardar protocolos");
        }
    }
}
