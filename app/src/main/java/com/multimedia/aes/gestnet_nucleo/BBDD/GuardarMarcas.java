package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class GuardarMarcas extends AsyncTask<Void,Void,Void> {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<Marca> marcas = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarMarcas(Context context, String json) throws java.sql.SQLException, JSONException {
        this.context = context;
        this.json = json;
    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Marcas.");
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
            try {
                new GuardarTipos(context,json).execute();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            if (context.getClass()==Login.class){
                ((Login) context).sacarMensaje("error al guardar las marcas");
            }else if (context.getClass()==Index.class){
                ((Index) context).sacarMensaje("error al guardar las marcas");
            }

        }
    }
    private void guardarJsonParte()  throws JSONException, SQLException, java.sql.SQLException {
        int id_marca;
        String nombre_marca;
        boolean esta = false;
        JSONObject jsonObject = new JSONObject(json);
        Log.d("bajada",json);
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
           try{
                MarcaDAO.actualizarMarca(context, id_marca, nombre_marca);
           }catch (SQLException e){
               bien = false;

           }
                bien = true;
            }
            marcas.clear();
        }
    }
}
