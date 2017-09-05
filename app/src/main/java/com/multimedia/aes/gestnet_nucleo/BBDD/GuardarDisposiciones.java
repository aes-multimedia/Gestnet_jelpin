package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by acp on 04/09/2017.
 */

public class GuardarDisposiciones {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static List<Disposiciones> disposiciones;

    public GuardarDisposiciones(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
        try {

            guardarJsonParte();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarJsonParte()  throws JSONException, SQLException, java.sql.SQLException {

        int id_disposicion_servicio;
        String nombre_disposicion;
        int coste_disposicion, precio_disposicion;

        boolean esta = false;


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("disposiciones_servicio");
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_disposicion_servicio").equals("null") || jsonArray.getJSONObject(i).getString("id_disposicion_servicio").equals("")) {
                id_disposicion_servicio = -1;
            } else {
                id_disposicion_servicio = jsonArray.getJSONObject(i).getInt("id_disposicion_servicio");
            }
            if (DisposicionesDAO.buscarTodasLasDisposiciones(context)!=null){
                disposiciones.addAll(DisposicionesDAO.buscarTodasLasDisposiciones(context));
            }
            if (disposiciones != null) {
                for (int k = 0; k < disposiciones.size(); k++) {
                    if (disposiciones.get(k).getId_disposicion_servicio() == id_disposicion_servicio) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }

            if (!esta) {

                if (jsonArray.getJSONObject(i).getString("nombre_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("nombre_disposicion").equals("")) {
                    nombre_disposicion = "";
                } else {
                    nombre_disposicion = jsonArray.getJSONObject(i).getString("nombre_disposicion");
                }

                if (jsonArray.getJSONObject(i).getString("coste_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("coste_disposicion").equals("")) {
                    coste_disposicion = 0;
                } else {
                    coste_disposicion = jsonArray.getJSONObject(i).getInt("coste_disposicion");
                }

                if (jsonArray.getJSONObject(i).getString("precio_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("precio_disposicion").equals("")) {
                    precio_disposicion = 0;
                } else {
                    precio_disposicion = jsonArray.getJSONObject(i).getInt("precio_disposicion");
                }

                if (DisposicionesDAO.newDisposiciones(context, id_disposicion_servicio, nombre_disposicion, coste_disposicion, precio_disposicion)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }



        } if (bien) {
            ((Login) context).irIndex();
        } else {
            ((Login) context).sacarMensaje("error al guardar las disposiciones");
        }

    }
}
