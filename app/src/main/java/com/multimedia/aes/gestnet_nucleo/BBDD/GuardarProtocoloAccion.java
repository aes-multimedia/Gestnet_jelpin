package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuardarProtocoloAccion extends AsyncTask<Void,Void,Void> {
    private static String json;
    private static Context context;
    private static boolean bien=true;
    private static ArrayList<ProtocoloAccion> protocoloAcciones = new ArrayList() {};
    private ProgressDialog dialog;

    public GuardarProtocoloAccion(Context context, String json) {
        this.context = context;
        this.json = json;
    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Protocolos.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonProtocoloAccion();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (bien){
            if (context.getClass()==Login.class){
                new GuardarConfiguracion(context,json).execute();
            }else if (context.getClass()==Index.class){
                new GuardarDatosAdicionales(context,json).execute();
            }
        }else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar protocolos");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar protocolos");
            }
        }
    }
    private static void guardarJsonProtocoloAccion() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("partes");
        for (int i = 0; i < jsonArray.length(); i++) {
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("acciones");
            for (int j = 0; j < jsonArray1.length(); j++) {
                int id_protocolo_accion;
                if (jsonArray1.getJSONObject(j).getString("fk_accion_protocolo").equals("null") || jsonArray1.getJSONObject(j).getString("fk_accion_protocolo").equals("")) {
                    id_protocolo_accion = -1;
                } else {
                    id_protocolo_accion = jsonArray1.getJSONObject(j).getInt("fk_accion_protocolo");
                }
                if (ProtocoloAccionDAO.buscarTodosLosProtocoloAccion(context)!=null){
                    protocoloAcciones.addAll(ProtocoloAccionDAO.buscarTodosLosProtocoloAccion(context));
                }
                boolean esta = false;
                if (protocoloAcciones != null) {
                    for (int k = 0; k < protocoloAcciones.size(); k++) {
                        if (protocoloAcciones.get(k).getId_protocolo_accion() == id_protocolo_accion) {
                            esta = true;
                            break;
                        } else {
                            esta = false;
                        }
                    }
                }
                int fk_maquina;
                if(jsonArray1.getJSONObject(j).getString("fk_maquina").equals("null") ||  jsonArray1.getJSONObject(j).getString("fk_maquina").equals("0")){
                    fk_maquina = -1;
                }else{
                    fk_maquina = jsonArray1.getJSONObject(j).getInt("fk_maquina");
                }
                int fk_parte;
                if(jsonArray1.getJSONObject(j).getString("fk_parte").equals("null") ||  jsonArray1.getJSONObject(j).getString("fk_parte").equals("0")){
                    fk_parte = -1;
                }else{
                    fk_parte = jsonArray1.getJSONObject(j).getInt("fk_parte");
                }
                String valor;
                if(jsonArray1.getJSONObject(j).getString("valor").equals("null")){
                    valor = "";
                }else{
                    valor = jsonArray1.getJSONObject(j).getString("valor");
                }
                boolean tipo_accion;
                if(jsonArray1.getJSONObject(j).getString("tipo_accion").equals("null") ||  jsonArray1.getJSONObject(j).getString("tipo_accion").equals("0") ||  jsonArray1.getJSONObject(j).getString("tipo_accion").equals("")){
                    tipo_accion = false;
                }else{
                    tipo_accion = true;
                }

                int id_accion;
                if(jsonArray1.getJSONObject(j).getString("id_accion").equals("null") ||  jsonArray1.getJSONObject(j).getString("id_accion").equals("0")){
                    id_accion = -1;
                }else{
                    id_accion = jsonArray1.getJSONObject(j).getInt("id_accion");
                }

                int fk_protocolo;
                if(jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("null") ||  jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("0")){
                    fk_protocolo = -1;
                }else{
                    fk_protocolo = jsonArray1.getJSONObject(j).getInt("fk_protocolo");
                }

                String descripcion;
                if(jsonArray1.getJSONObject(j).getString("descripcion").equals("null")){
                    descripcion = "";
                }else{
                    descripcion = jsonArray1.getJSONObject(j).getString("descripcion");
                }

                String nombre_protocolo;
                if(jsonArray1.getJSONObject(j).getString("nombre_protocolo").equals("null")){
                    nombre_protocolo = "";
                }else{
                    nombre_protocolo = jsonArray1.getJSONObject(j).getString("nombre_protocolo");
                }
                if (!esta) {
                    if (!ProtocoloAccionDAO.newProtocoloAccion(context,id_protocolo_accion,valor,fk_maquina,fk_parte,fk_protocolo,nombre_protocolo,id_accion,tipo_accion,descripcion)) {
                        bien = false;
                    }
                }else{
                    ProtocoloAccionDAO.actualizarProtocoloAccion(context,id_protocolo_accion,valor,fk_maquina,fk_parte,fk_protocolo,nombre_protocolo,id_accion,tipo_accion,descripcion);
                }
            }
        }

    }
}
