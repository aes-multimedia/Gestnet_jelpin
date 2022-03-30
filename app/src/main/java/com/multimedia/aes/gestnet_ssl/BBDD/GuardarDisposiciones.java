package com.multimedia.aes.gestnet_ssl.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Disposiciones;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;
import com.multimedia.aes.gestnet_ssl.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuardarDisposiciones extends AsyncTask<Void,Void,Void> {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<Disposiciones> disposiciones = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarDisposiciones(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Disposiciones.");
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
                new GuardarMarcas(context,json).execute();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            if (context.getClass()==Login.class){
                ((Login) context).sacarMensaje("error al guardar las disposiciones");
            }else if (context.getClass()==Index.class){
                ((Index) context).sacarMensaje("error al guardar las disposiciones");
            }

        }
    }

    private void guardarJsonParte()  throws JSONException, SQLException, java.sql.SQLException {

        int id_disposicion_servicio;
        String nombre_disposicion;
        double coste_disposicion, precio_disposicion;
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
            if (jsonArray.getJSONObject(i).getString("nombre_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("nombre_disposicion").equals("")) {
                nombre_disposicion = "";
            } else {
                nombre_disposicion = jsonArray.getJSONObject(i).getString("nombre_disposicion");
            }

            if (jsonArray.getJSONObject(i).getString("coste_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("coste_disposicion").equals("")) {
                coste_disposicion = 0;
            } else {
                coste_disposicion = jsonArray.getJSONObject(i).getDouble("coste_disposicion");
            }

            if (jsonArray.getJSONObject(i).getString("precio_disposicion").equals("null") || jsonArray.getJSONObject(i).getString("precio_disposicion").equals("")) {
                precio_disposicion = 0;
            } else {
                precio_disposicion = jsonArray.getJSONObject(i).getDouble("precio_disposicion");
            }
            if (!esta) {

                if (DisposicionesDAO.newDisposiciones(context, id_disposicion_servicio, nombre_disposicion, coste_disposicion, precio_disposicion)) {
                    bien = true;
                } else {
                    bien = false;
                }

            }else{




                 DisposicionesDAO.actualizarDisposiciones(context, id_disposicion_servicio, nombre_disposicion, coste_disposicion, precio_disposicion);
            }


            disposiciones.clear();
        }

    }
}
