package com.multimedia.aes.gestnet_sgsv2.BBDD;

import android.content.Context;

import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

/**
 * Created by Sergio on 14/07/2016.
 */
public class GuardarSubTiposVisita {



    private static String Json;
    private static Context context;
    private static boolean bien;

    public GuardarSubTiposVisita(Context context, String json) {
        this.context = context;
        Json = json;
        try {
            guardarJsonSubTiposVisita();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//int id_subtipo, int activo, String descripcion_ticket, String descripcion, String codigo, int fk_tipo_visita

    /*
    "id_subtipo": "1",
                "fk_tipo_visita": "3",
                "codigo": "IPa-1",
                "descripcion": "Fuga de Gas",
                "descripcion_ticket": "Fuga de gas",
                "activo": "1"
    * */


    public static void guardarJsonSubTiposVisita() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(Json);
        jsonObject = jsonObject.getJSONObject("usuario");
        JSONArray jsonArray = jsonObject.getJSONArray("subtipos_visita");

        for (int i = 0; i < jsonArray.length(); i++) {
            int id = jsonArray.getJSONObject(i).getInt("id_subtipo");
            int fk_tipo_visita = jsonArray.getJSONObject(i).getInt("fk_tipo_visita");
            String codigo = jsonArray.getJSONObject(i).getString("codigo");
            String descripcion = jsonArray.getJSONObject(i).getString("descripcion");
            String descripcion_ticket = jsonArray.getJSONObject(i).getString("descripcion_ticket");
            int activo = jsonArray.getJSONObject(i).getInt("activo");
            if (SubTiposVisitaDAO.newSubTiposVisita(context,id,activo,descripcion_ticket,descripcion,codigo,fk_tipo_visita)){
                bien=true;
            }else{
                bien=false;
            }
        }
        if (bien){
            ((Login)context).siguienteActivity();
        }else{
            ((Login)context).sacarMensaje("error tipo sub tipo");
        }
    }
}
