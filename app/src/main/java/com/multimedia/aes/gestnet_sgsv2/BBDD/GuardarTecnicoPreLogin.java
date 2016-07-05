package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.PreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarTecnicoPreLogin {
    private static String Json;
    private static Context context;

    public GuardarTecnicoPreLogin(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonTecnico();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void guardarJsonTecnico() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        int id_tecnico;
        if (jsonObject.getString("id_user").equals("null")){
            id_tecnico = -1;
        }else{
            id_tecnico = jsonObject.getInt("id_user");
        }
        String nombre_usuario = jsonObject.getString("uname");
        String login_usuario = jsonObject.getString("login");
        String email = jsonObject.getString("email");
        String num_tecnico = jsonObject.getString("tecnico");
        int fk_empresa;
        if (jsonObject.getString("fk_empresa").equals("null")){
            fk_empresa = -1;
        }else{
            fk_empresa = jsonObject.getInt("fk_empresa");
        }
        int fk_almacen;
        if (jsonObject.getString("fk_entidad").equals("null")){
            fk_almacen = -1;
        }else{
            fk_almacen = jsonObject.getInt("fk_entidad");
        }
        int fk_compañia;
        if (jsonObject.getString("fk_compania").equals("null")){
            fk_compañia = -1;
        }else{
            fk_compañia = jsonObject.getInt("fk_compania");
        }
        int fk_departamento;
        if (jsonObject.getString("fk_departamento").equals("null")){
            fk_departamento = -1;
        }else{
            fk_departamento = jsonObject.getInt("fk_departamento");
        }
        String apikey = jsonObject.getString("apikey");
        if (TecnicoDAO.newTecnico(context, id_tecnico, nombre_usuario, login_usuario, email, num_tecnico, fk_empresa, fk_almacen, fk_compañia, fk_departamento, apikey)){
            ManagerProgressDialog.guardarDatosAveria(context);
            new GuardarAveriasPreLogin(context,Json);
        }else{
            ((PreLogin)context).sacarMensaje("error en tecnico");
        }
    }
}
