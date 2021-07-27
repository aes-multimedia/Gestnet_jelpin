package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuardarFormasPago extends AsyncTask<Void,Void,Void> {


    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<FormasPago> formas = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarFormasPago(Context context, String json) throws java.sql.SQLException {
        this.context = context;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Formas de Pago.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonParte();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if(bien){
            try {
                new GuardarManoObra(context,json).execute();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar las formas de pago");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar las formas de pago");
            }
        }
    }

    private static void guardarJsonParte() throws JSONException, SQLException, java.sql.SQLException {
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
            if (FormasPagoDAO.buscarTodasLasFormasPago(context) != null) {
                formas.addAll(FormasPagoDAO.buscarTodasLasFormasPago(context));
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
            if (jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("null") || jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("")) {
                mostrarcuenta = false;
            } else if(jsonArray.getJSONObject(i).getString("mostrar_cuenta").equals("0")){
                mostrarcuenta = false;

            }else{
                mostrarcuenta=true;
            }
            if (!esta) {
                if (FormasPagoDAO.newFomasPago(context,id_forma_pago,forma_pago,financiado,mostrar_cuenta,sumar_dias,bAparecerEnInforme,mostrarcuenta)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }else{




        try {
            FormasPagoDAO.actualizarFormasPago(context, id_forma_pago, forma_pago, financiado, mostrar_cuenta, sumar_dias, bAparecerEnInforme, mostrarcuenta);
        }catch (SQLException e){
            bien = false;

            }
            bien = true;

            }
            formas.clear();
        }

    }

    }

