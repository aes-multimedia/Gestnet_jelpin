package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.SQLException;

public class GuardarTecnico {

    public static void guardarJsonTecnico(String Json, Context context) throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");

        int id_tecnico = jsonObject.getInt("id_user");
        String nombre_usuario = jsonObject.getString("uname");
        String login_usuario = jsonObject.getString("login");
        String email = jsonObject.getString("email");
        String num_tecnico = jsonObject.getString("tecnico");
        String fk_empresa = jsonObject.getString("fk_empresa");
        String fk_almacen = jsonObject.getString("fk_entidad");
        String fk_compañia = jsonObject.getString("fk_compania");
        String fk_departamento = jsonObject.getString("fk_departamento");
        String apikey = jsonObject.getString("apikey");
        TecnicoDAO.newTecnico(context, id_tecnico, nombre_usuario, login_usuario, email, num_tecnico, fk_empresa, fk_almacen, fk_compañia, fk_departamento, apikey);
    }
}
