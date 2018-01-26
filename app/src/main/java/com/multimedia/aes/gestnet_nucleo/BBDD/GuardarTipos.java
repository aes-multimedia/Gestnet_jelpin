package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.TipoCaldera;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class GuardarTipos extends AsyncTask<Void,Void,Void> {


    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<TipoCaldera> tipos = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarTipos(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Tipos de Combustion.");
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
        if (bien) {
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
        }
    }
}
