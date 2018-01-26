package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarUsuario extends AsyncTask<Void,Void,Void> {
    private static String json;
    private static Context context;
    private static boolean bien=false;
    private ProgressDialog dialog;

    public GuardarUsuario(Context context, String json) {
        this.context = context;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Usuario.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonUsuario();
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
        if (bien){
            ((Login)context).inicializarConfiguracion();
        }else{
            ((Login)context).sacarMensaje("error cliente");
        }
    }

    public static void guardarJsonUsuario() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        jsonObject = jsonObject.getJSONObject("usuario");
        int id = jsonObject.getInt("id_usuario");
        int fk_cliente  = jsonObject.getInt("fk_cliente");
        int fk_entidad = jsonObject.getInt("fk_entidad");
        int fk_user = jsonObject.getInt("fk_user");
        String nombre_usuario = jsonObject.getString("usuario");
        String estado_activo = jsonObject.getString("estado_activo");
        String api_key = jsonObject.getString("api_key");
        if (UsuarioDAO.newUsuario(context,id,fk_cliente,fk_entidad,fk_user,nombre_usuario,estado_activo,api_key)){
            bien = true;
        }
    }
}
