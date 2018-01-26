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
        boolean todoBien = false;


        boolean esta = false;
        JSONArray jsonArray = new JSONArray(msg);
        for (int i = 0; i < jsonArray.length(); i++) {
            int id_articulo;
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

            String nombre_articulo;
            String referencia;
            String referencia_aux;
            String ean;
            int stock;
            double coste;
            double iva;
            double tarifa;
            double descuento;

            if (jsonArray.getJSONObject(i).getString("nombre_articulo").equals("null") || jsonArray.getJSONObject(i).getString("nombre_articulo").equals("")) {
                nombre_articulo = "";
            } else {
                nombre_articulo = jsonArray.getJSONObject(i).getString("nombre_articulo");
            }
            if (jsonArray.getJSONObject(i).getString("referencia").equals("null") || jsonArray.getJSONObject(i).getString("referencia").equals("")) {
                referencia = "";
            } else {
                referencia = jsonArray.getJSONObject(i).getString("referencia");
            }
            if (jsonArray.getJSONObject(i).getString("referencia_aux").equals("null") || jsonArray.getJSONObject(i).getString("referencia_aux").equals("")) {
                referencia_aux = "";
            } else {
                referencia_aux = jsonArray.getJSONObject(i).getString("referencia_aux");
            }
            if (jsonArray.getJSONObject(i).getString("ean").equals("null") || jsonArray.getJSONObject(i).getString("ean").equals("")) {
                ean = "";
            } else {
                ean = jsonArray.getJSONObject(i).getString("ean");
            }
            if (jsonArray.getJSONObject(i).getString("stock").equals("null") || jsonArray.getJSONObject(i).getString("stock").equals("")) {
                stock = -1;
            } else {
                stock = jsonArray.getJSONObject(i).getInt("stock");
            }
            if (jsonArray.getJSONObject(i).getString("coste").equals("null") || jsonArray.getJSONObject(i).getString("coste").equals("")) {
                coste = -1;
            } else {
                coste = jsonArray.getJSONObject(i).getDouble("coste");
            }
            if (jsonArray.getJSONObject(i).getString("iva").equals("null") || jsonArray.getJSONObject(i).getString("iva").equals("")) {
                iva = -1;
            } else {
                iva = jsonArray.getJSONObject(i).getDouble("iva");
            }
            if (jsonArray.getJSONObject(i).getString("tarifa").equals("null") || jsonArray.getJSONObject(i).getString("tarifa").equals("")) {
                tarifa = -1;
            } else {
                tarifa = jsonArray.getJSONObject(i).getDouble("tarifa");
            }
            if (jsonArray.getJSONObject(i).getString("descuento").equals("null") || jsonArray.getJSONObject(i).getString("descuento").equals("")) {
                descuento = -1;
            } else {
                descuento = jsonArray.getJSONObject(i).getDouble("descuento");
            }




            if (!esta) {
                if (ArticuloDAO.newArticuloP(context, id_articulo, nombre_articulo, stock, coste,referencia,referencia_aux,ean,iva,tarifa,descuento)) {
                    bien = true;
                } else {
                    bien = false;
                }

            } else {
                ArticuloDAO.actualizarArticuloP(context, id_articulo, nombre_articulo, stock, coste,referencia,referencia_aux,ean,iva,tarifa,descuento);
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

