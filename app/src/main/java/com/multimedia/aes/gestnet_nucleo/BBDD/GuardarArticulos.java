package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.content.Intent;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuardarArticulos {

    private static String msg;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<Articulo> articulos = new ArrayList<>();


    public GuardarArticulos(Context context, String msg) throws java.sql.SQLException, JSONException {

        this.context = context;
        this.msg = msg;

    }

    public boolean guardarArticulos() throws JSONException, SQLException, java.sql.SQLException {

        int id_articulo;
        String nombre_articulo;
        int stock;
        double coste;
        int imagen;
        boolean todoBien = false;


        boolean esta = false;


        JSONArray jsonArray = new JSONArray(msg);
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_articulo").equals("null") || jsonArray.getJSONObject(i).getString("id_articulo").equals("")) {
                id_articulo = -1;
            } else {
                id_articulo = jsonArray.getJSONObject(i).getInt("id_articulo");
            }

            if (ArticuloDAO.buscarTodosLosArticulos(context) != null) {
                articulos.addAll(ArticuloDAO.buscarTodosLosArticulos(context));
            }
            if (articulos != null) {
                for (int k = 0; k < articulos.size(); k++) {
                    if (articulos.get(k).getId_articulo() == id_articulo) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }
            if (jsonArray.getJSONObject(i).getString("nombre_articulo").equals("null") || jsonArray.getJSONObject(i).getString("nombre_articulo").equals("")) {
                nombre_articulo = "";
            } else {
                nombre_articulo = jsonArray.getJSONObject(i).getString("nombre_articulo");
            }

            if (jsonArray.getJSONObject(i).getString("stock").equals("null") || jsonArray.getJSONObject(i).getString("stock").equals("")) {
                stock = -1;
            } else {
                stock = jsonArray.getJSONObject(i).getInt("stock");
            }


            if (jsonArray.getJSONObject(i).getString("coste").equals("null") || jsonArray.getJSONObject(i).getString("stock").equals("")) {
                coste = -1;
            } else {
                coste = jsonArray.getJSONObject(i).getDouble("coste");
            }


            if (!esta) {
                if (ArticuloDAO.newArticuloP(context, id_articulo, nombre_articulo, stock, coste)) {
                    bien = true;
                } else {
                    bien = false;
                }

            } else {
                ArticuloDAO.actualizarArticuloP(context, id_articulo, nombre_articulo, stock, coste);
            }


        }
        if (bien) {
            todoBien = true;
        } else {
            if (context.getClass() == Login.class) {
                ((Login) context).sacarMensaje("error al guardar las marcas");
            } else if (context.getClass() == Index.class) {
                ((Index) context).sacarMensaje("error al guardar las marcas");
            }

        }
        return todoBien;

    }


}

