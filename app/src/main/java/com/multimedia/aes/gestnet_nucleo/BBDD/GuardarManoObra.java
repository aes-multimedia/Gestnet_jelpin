package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.database.SQLException;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class GuardarManoObra extends AsyncTask<Void,Void,Void> {

    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<ManoObra> manoObras = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarManoObra(Context context, String json) throws java.sql.SQLException {
        this.context = context;
        this.json = json;

    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Mano de Obra.");
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
                new GuardarDisposiciones(context,json).execute();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar las manos de obra");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar las manos de obra");
            }
        }
    }

    private static void guardarJsonParte() throws JSONException, SQLException, java.sql.SQLException {
        int id_mano;
        String concepto,coste;
        double precio;

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
                precio = jsonArray.getJSONObject(i).getDouble("precio");
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
                try {
                    ManoObraDAO.actualizarManoObra(context, id_mano, concepto, precio, coste);
                }catch (SQLException e){
                    bien = false;

                }
                    bien = true;

                }
            manoObras.clear();
            }


        }
    }

