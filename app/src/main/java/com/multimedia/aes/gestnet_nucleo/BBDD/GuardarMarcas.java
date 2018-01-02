package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by acp on 02/01/2018.
 */


public class GuardarMarcas {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<Marca> marcas = new ArrayList<>();

    public GuardarMarcas(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
        try {

            guardarJsonParte();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void guardarJsonParte()  throws JSONException, SQLException, java.sql.SQLException {

        int id_marca;
        String nombre_marca;


        boolean esta = false;


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("marcas");
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_marca").equals("null") || jsonArray.getJSONObject(i).getString("id_marca").equals("")) {
                id_marca = -1;
            } else {
                id_marca = jsonArray.getJSONObject(i).getInt("id_marca");
            }
            if (MarcaDAO.buscarTodasLasMarcas(context)!=null){
                marcas.addAll(MarcaDAO.buscarTodasLasMarcas(context));
            }
            if (marcas != null) {
                for (int k = 0; k < marcas.size(); k++) {
                    if (marcas.get(k).getId_marca() == id_marca) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }
            if (jsonArray.getJSONObject(i).getString("nombre_marca").equals("null") || jsonArray.getJSONObject(i).getString("nombre_marca").equals("")) {
                nombre_marca = "";
            } else {
                nombre_marca = jsonArray.getJSONObject(i).getString("nombre_marca");
            }


            if (!esta) {
                if (MarcaDAO.newMarca(context, id_marca, nombre_marca)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }else{
                MarcaDAO.actualizarMarca(context, id_marca, nombre_marca);
            }



        } if (bien) {
           new GuardarTipos(context,json);
        } else {
            if (context.getClass()==Login.class){
                ((Login) context).sacarMensaje("error al guardar las marcas");
            }else if (context.getClass()==Index.class){
                ((Index) context).sacarMensaje("error al guardar las marcas");
            }

        }

    }
}
