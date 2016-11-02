package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.SQLException;

public class HiloDatosCliente extends AsyncTask<Void,Void,Void>{
    private String ipInterna = "192.168.0.228";
    private String ipExterna = "80.58.161.135";
    private String puerto = "8085";
    private String apikey="";
    private int id_user,id_parte;
    private String mensaje="";
    private Context context;
    Mantenimiento mantenimiento;

    public HiloDatosCliente(Context context, int id_parte) {
        this.id_parte=id_parte;
        this.context=context;
        try {
            Tecnico t = TecnicoDAO.buscarTodosLosTecnicos(context).get(0);
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(context,id_parte);
            id_user = t.getId_tecnico();
            apikey = t.getApikey();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = iniciar(rellenarJsonCliente());
       } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.e("ACT. DATOSUSER",mensaje);
        super.onPostExecute(aVoid);
    }
    private String iniciar(JSONObject jsonObject) throws JSONException{
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+ ipInterna +":"+puerto+"/api-sgs/v1/usuario/observaciones");
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.addRequestProperty("apikey",apikey);
            uc.addRequestProperty("id",String.valueOf(id_user));
            uc.addRequestProperty("fk_parte",String.valueOf(id_parte));
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            return "MALFORMEDURL";
        } catch (ProtocolException e) {
            e.printStackTrace();
            return "PROTOCOLEXCEPTION";
        } catch (IOException e) {
            try {
                URL urlwsExt = new URL("http://"+ ipExterna +":"+puerto+"/api-sgs/v1/usuario/observaciones");
                uc = (HttpURLConnection) urlwsExt.openConnection();
                uc.setDoOutput(true);
                uc.setDoInput(true);
                uc.addRequestProperty("apikey",apikey);
                uc.addRequestProperty("id",String.valueOf(id_user));
                uc.addRequestProperty("id_parte",String.valueOf(id_parte));
                uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
                uc.setRequestMethod("POST");
                uc.connect();
            } catch (MalformedURLException e1) {
                e1.printStackTrace();
                return "MALFORMEDURL";
            } catch (ProtocolException e1) {
                e1.printStackTrace();
                return "PROTOCOLEXCEPTION";
            } catch (IOException e1) {
                e1.printStackTrace();
                return "No se ha podido actualizar en GESTNET,  se intentara mas tarde";
            }
        }
        String contenido = "";
        try {
            OutputStream os = uc.getOutputStream();
            OutputStreamWriter osw = null;
            osw = new OutputStreamWriter(os, "UTF-8");
            osw.write(jsonObject.toString());
            osw.flush();
            BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                contenido += inputLine + "\n";
            }
            in.close();
            osw.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contenido;
    }
    private JSONObject rellenarJsonCliente() throws JSONException {
        JSONObject jsonObject=null;
        jsonObject.put("id_cliente",mantenimiento.getFk_usuario());
        jsonObject.put("dni",mantenimiento.getDni_usuario());
        jsonObject.put("observaciones",mantenimiento.getObservaciones_usuario());
        jsonObject.put("nombre",mantenimiento.getNombre_usuario());
        jsonObject.put("tfno1",mantenimiento.getTelefono1_usuario());
        jsonObject.put("tfno2",mantenimiento.getTelefono2_usuario());
        jsonObject.put("tfno3",mantenimiento.getTelefono3_usuario());
        jsonObject.put("tfno4",mantenimiento.getTelefono4_usuario());
        jsonObject.put("tfno5",mantenimiento.getTelefono5_usuario());
        return jsonObject;
    }
}
