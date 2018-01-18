package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by acp on 04/09/2017.
 */

public class GuardarManoObra {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<ManoObra> manoObras = new ArrayList<>();

    public GuardarManoObra(Context context, String json) throws java.sql.SQLException {
        this.context = context;
        this.json = json;
        try {
            guardarJsonParte();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void guardarJsonParte() throws JSONException, SQLException, java.sql.SQLException {
        int id_mano,precio;
        String concepto,coste;

        boolean esta = false;


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("manos_obra");
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_mano").equals("null") || jsonArray.getJSONObject(i).getString("id_mano").equals("")) {
                id_mano = -1;
            } else {
                id_mano = jsonArray.getJSONObject(i).getInt("id_mano");
            }
            if (ManoObraDAO.buscarTodasLasManoDeObra(context) != null) {
                manoObras.addAll(ManoObraDAO.buscarTodasLasManoDeObra(context));
            }
            if (manoObras != null) {
                for (int k = 0; k < manoObras.size(); k++) {
                    if (manoObras.get(k).getId_mano() == id_mano) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }
            if (jsonArray.getJSONObject(i).getString("concepto").equals("null") || jsonArray.getJSONObject(i).getString("concepto").equals("")) {
                concepto = "";
            } else {
                concepto = jsonArray.getJSONObject(i).getString("concepto");
            }

            if (jsonArray.getJSONObject(i).getString("precio").equals("null") || jsonArray.getJSONObject(i).getString("precio").equals("")) {
                precio = -1;
            } else {
                precio = jsonArray.getJSONObject(i).getInt("precio");
            }

            if (jsonArray.getJSONObject(i).getString("coste").equals("null") || jsonArray.getJSONObject(i).getString("coste").equals("")) {
                coste = "";
            } else {
                coste = jsonArray.getJSONObject(i).getString("coste");
            }


            if (!esta) {
                if (ManoObraDAO.newManoObra(context,id_mano,concepto,precio,coste)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }else{
                ManoObraDAO.actualizarManoObra(context,id_mano,concepto,precio,coste);
            }


        }
        if(bien){
            new GuardarDisposiciones(context,json);
        }
        else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar las manos de obra");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar las manos de obra");
            }
        }
    }
}
