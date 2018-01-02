package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.TipoCaldera;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by acp on 02/01/2018.
 */

public class GuardarTipos {


    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<TipoCaldera> tipos = new ArrayList<>();

    public GuardarTipos(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
        try {

            guardarJsonParte();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarJsonParte()  throws JSONException, SQLException, java.sql.SQLException {

        int id_tipo_combustion;
        String nombre_tipo_combustion;


        boolean esta = false;


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("tipos");
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_tipo_combustion").equals("null") || jsonArray.getJSONObject(i).getString("id_tipo_combustion").equals("")) {
                id_tipo_combustion = -1;
            } else {
                id_tipo_combustion = jsonArray.getJSONObject(i).getInt("id_tipo_combustion");
            }
            if (TipoCalderaDAO.buscarTodasLosTipoCaldera(context)!=null){
                tipos.addAll(TipoCalderaDAO.buscarTodasLosTipoCaldera(context));
            }
            if (tipos != null) {
                for (int k = 0; k < tipos.size(); k++) {
                    if (tipos.get(k).getId_tipo_combustion() == id_tipo_combustion) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }
            if (jsonArray.getJSONObject(i).getString("nombre_tipo_combustion").equals("null") || jsonArray.getJSONObject(i).getString("nombre_tipo_combustion").equals("")) {
                nombre_tipo_combustion = "";
            } else {
                nombre_tipo_combustion = jsonArray.getJSONObject(i).getString("nombre_tipo_combustion");
            }


            if (!esta) {
                if (TipoCalderaDAO.newTipoCaldera(context, id_tipo_combustion, nombre_tipo_combustion)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }else{
                TipoCalderaDAO.actualizarTipos(context, id_tipo_combustion, nombre_tipo_combustion);
            }



        } if (bien) {
            if (context.getClass()==Login.class){
                ((Login) context).irIndex();
            }else if (context.getClass()==Index.class){
                ((Index) context).datosActualizados();
            }

        } else {
            if (context.getClass()==Login.class){
                ((Login) context).sacarMensaje("error al guardar las tipos");
            }else if (context.getClass()==Index.class){
                ((Index) context).sacarMensaje("error al guardar las tipos");
            }

        }

    }
}
