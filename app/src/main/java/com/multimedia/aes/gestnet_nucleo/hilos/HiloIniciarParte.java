package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

public class HiloIniciarParte extends AsyncTask<Void,Void,Void> {

    private int fk_estado_android,estado;
    private String mensaje;
    private Context context;
    private Parte parte;
    private ProgressDialog dialog;
    private Cliente cliente;
    private DatosAdicionales datos;

    public HiloIniciarParte(Context context, Parte parte, int fk_estado_android, int estado){
        this.parte = parte;
        this.fk_estado_android = fk_estado_android;
        this.context=context;
        this.estado=estado;
        try {
            this.cliente= ClienteDAO.buscarCliente(context);
            this.datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context,parte.getId_parte());
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Iniciando Parte.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = iniciar();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject jsonObject = new JSONObject(mensaje);

                if (jsonObject.getInt("estado")==1){
                    try {
                        ParteDAO.actualizarEstadoAndroid(context,parte.getId_parte(),fk_estado_android);
                        ParteDAO.actualizarEstadoParte(context,parte.getId_parte(),estado);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    ((Index)context).recreate();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{

        }
    }
    private String iniciar() throws JSONException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        jsonObject1.put("id_parte", parte.getId_parte());
        jsonObject1.put("fk_estado",estado);
        jsonObject1.put("estado_android", fk_estado_android);
        jsonObject1.put("observaciones", parte.getObservaciones());
        jsonObject1.put("matem_hora_entrada", datos.getMatem_hora_entrada());
        jsonObject2.put("id_usuario",parte.getFk_usuario());
        jsonObject2.put("nombre_usuario", parte.getNombre_cliente());
        jsonObject2.put("DNI", parte.getDni_cliente());
        jsonObject2.put("telefono1", parte.getTelefono1_cliente());
        jsonObject2.put("telefono2", parte.getTelefono2_cliente());
        jsonObject2.put("telefono3", parte.getTelefono3_cliente());
        jsonObject2.put("email_cliente", parte.getEmail_cliente());
        jsonObject2.put("otros_telefonos", parte.getTelefono4_cliente());
        msg.put("sat_partes",jsonObject1);
        msg.put("sat_usuarios",jsonObject2);
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+cliente.getIp_cliente()+Constantes.URL_INICIAR_PARTE_EXTERNAPRUEBAS);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_INICIAR_PARTE_EXTERNAPRUEBAS,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_INICIAR_PARTE_EXTERNAPRUEBAS,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_INICIAR_PARTE_EXTERNAPRUEBAS,jsonArray.toString());
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
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_INICIAR_PARTE_EXTERNAPRUEBAS,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
}
