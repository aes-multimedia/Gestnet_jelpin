package com.multimedia.aes.gestnet_ssl.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.constantes.Constantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.EnvioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.nucleo.FotosProtocoloAccion;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

import java.net.HttpURLConnection;

public class HiloBorrarImagenesAcciones extends AsyncTask<Void,Void,Void> {


    private Context context;
    private ProgressDialog dialog;
    private String mensaje,nombre;
    private int fk_accion,id_imagen,fk_parte,fk_maquina;
    private Cliente cliente;


    public HiloBorrarImagenesAcciones(Context context, int fk_accion,String nombre,int id_imagen,int fk_parte,int fk_maquina) {
        this.context=context;
        this.fk_accion=fk_accion;
        this.nombre=nombre;
        this.id_imagen=id_imagen;
        this.fk_parte=fk_parte;
        this.fk_maquina=fk_maquina;
        try {
            cliente= ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = iniciar();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Borrando imagen.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..."+"\n"+"Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject jsonObject = new JSONObject(mensaje);
                if (jsonObject.getInt("estado")==1){
                    ((FotosProtocoloAccion)context).resultOkBorrar(id_imagen);
                }else{
                    ((FotosProtocoloAccion)context).resultErrorBorrar();
                }
            } catch (JSONException e) {
                e.printStackTrace();
                ((FotosProtocoloAccion)context).resultErrorBorrar();
            }
        }else{
            ((FotosProtocoloAccion)context).resultErrorBorrar();
        }
    }
    private String iniciar() throws JSONException, IOException, SQLException {
        JSONObject msg = new JSONObject();
        msg.put("nombre", nombre);
        msg.put("fk_accion_protocolo", fk_accion);
        msg.put("fk_parte", fk_parte);
        msg.put("fk_maquina", fk_maquina);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+cliente.getIp_cliente()+Constantes.URL_BORRAR_IMAGENES_ACCIONES);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        String contenido = "";
        OutputStream os = null;
        try {
            os = uc.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(msg.toString());
            osw.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            osw.close();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_DIA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
}
