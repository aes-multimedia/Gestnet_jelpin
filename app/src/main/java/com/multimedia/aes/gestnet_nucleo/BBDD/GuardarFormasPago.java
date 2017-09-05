package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.content.Context;
import android.database.SQLException;

import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * Created by acp on 01/09/2017.
 */

public class GuardarFormasPago {


    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static List<FormasPago> formas;

    public GuardarFormasPago(Context context, String json) throws java.sql.SQLException {
        this.context = context;
        this.json = json;
        try {
            if (FormasPagoDAO.buscarTodasLasFormasPago(context) != null) {
                formas = FormasPagoDAO.buscarTodasLasFormasPago(context);
            }
            guardarJsonParte();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }




    public static void guardarJsonParte() throws JSONException, SQLException, java.sql.SQLException {
        int id_forma_pago,financiado;
        String forma_pago;
        boolean  mostrar_cuenta, sumar_dias, bAparecerEnInforme, mostrarcuenta, esta = false;


        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("formasPago");
        for (int i = 0; i < jsonArray.length(); i++) {


            if (jsonArray.getJSONObject(i).getString("id_forma_pago").equals("null") || jsonArray.getJSONObject(i).getString("id_forma_pago").equals("")) {
                id_forma_pago = -1;
            } else {
                id_forma_pago = jsonArray.getJSONObject(i).getInt("id_forma_pago");
            }

            if (formas != null) {
                for (int k = 0; k < formas.size(); k++) {
                    if (formas.get(k).getId_forma_pago() == id_forma_pago) {
                        esta = true;
                        break;
                    } else {
                        esta = false;
                    }
                }
            }

            if (!esta) {

                if (jsonArray.getJSONObject(i).getString("forma_pago").equals("null") || jsonArray.getJSONObject(i).getString("forma_pago").equals("")) {
                    forma_pago = "";
                } else {
                    forma_pago = jsonArray.getJSONObject(i).getString("forma_pago");
                }


                if (jsonArray.getJSONObject(i).getString("financiado").equals("null") || jsonArray.getJSONObject(i).getString("financiado").equals("")) {
                    financiado = -1;
                } else {
                    financiado = jsonArray.getJSONObject(i).getInt("financiado");
                }




                if (jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("null") || jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("")) {
                    mostrar_cuenta = false;
                } else if(jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("0")){
                    mostrar_cuenta = false;

                }else{
                    mostrar_cuenta=true;
                }
                if (jsonArray.getJSONObject(i).getString("sumar_dias").equals("null") || jsonArray.getJSONObject(i).getString("sumar_dias").equals("")) {
                    sumar_dias = false;
                } else if(jsonArray.getJSONObject(i).getString("sumar_dias").equals("0")){
                    sumar_dias = false;

                }else{
                    sumar_dias=true;
                }

                if (jsonArray.getJSONObject(i).getString("bAparecerEnInforme").equals("null") || jsonArray.getJSONObject(i).getString("bAparecerEnInforme").equals("")) {
                    bAparecerEnInforme = false;
                } else if(jsonArray.getJSONObject(i).getString("bAparecerEnInforme").equals("0")){
                    bAparecerEnInforme = false;

                }else{
                    bAparecerEnInforme=true;
                }
                if (jsonArray.getJSONObject(i).getString("mostrarcuenta").equals("null") || jsonArray.getJSONObject(i).getString("mostrarcuenta").equals("")) {
                    mostrarcuenta = false;
                } else if(jsonArray.getJSONObject(i).getString("mostrarcuenta").equals("0")){
                    mostrarcuenta = false;

                }else{
                    mostrarcuenta=true;
                }

                if (FormasPagoDAO.newFomasPago(context,id_forma_pago,forma_pago,financiado,mostrar_cuenta,sumar_dias,bAparecerEnInforme,mostrarcuenta)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }

            if(bien){
               new GuardarManoObra(context,json);
            }
                else{
                    ((Login)context).sacarMensaje("error al guardar las formas de pago");
                }


        }


    }

}
