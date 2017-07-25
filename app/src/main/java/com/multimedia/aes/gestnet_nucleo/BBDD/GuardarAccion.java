package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.dao.AccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarAccion {
    private static String json;
    private static Context context;
    private static boolean bien=false;

    public GuardarAccion(Context context, String json) {
        this.context = context;
        GuardarAccion.json = json;
        try {
            guardarJsonCliente();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonCliente() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        jsonObject = jsonObject.getJSONObject("accion");
        int id_accion = 0;
        int fk_protocolo = 0;
        String descripcion_accion = "";
        String periocidad_accion = "";
        String tipo_accion = "";

        if (AccionDAO.newAccion(context, id_accion, fk_protocolo, descripcion_accion, periocidad_accion, tipo_accion)){
            bien = true;
        }
        if (bien){
            ((PreLogin)context).irLogin();
        }else{
            ((PreLogin)context).sacarMensaje("error cliente");
        }
    }
}
