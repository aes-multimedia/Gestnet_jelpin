package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;
import com.multimedia.aes.gestnet_nucleo.nucleo.PreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarUsuario {
    private static String Json;
    private static Context context;
    private static boolean bien=false;

    public GuardarUsuario(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonUsuario();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonUsuario() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        int id = jsonObject.getInt("id_usuario");
        int fk_cliente  = jsonObject.getInt("fk_cliente");
        int fk_entidad = jsonObject.getInt("fk_entidad");
        int fk_user = jsonObject.getInt("fk_user");
        String nombre_usuario = jsonObject.getString("usuario");
        String estado_activo = jsonObject.getString("estado_activo");
        String api_key = jsonObject.getString("api_key");
        if (UsuarioDAO.newUsuario(context,id,fk_cliente,fk_entidad,fk_user,nombre_usuario,estado_activo,api_key)){
            bien = true;
        }
        if (bien){
            ((Login)context).irIndex();
        }else{
            ((Login)context).sacarMensaje("error cliente");
        }
    }
}
