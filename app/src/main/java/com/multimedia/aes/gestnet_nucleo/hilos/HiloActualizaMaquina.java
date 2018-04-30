package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;

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

/**
 * Created by acp on 24/01/2018.
 */

public class HiloActualizaMaquina  extends AsyncTask<Void,Void,Void> {
    private String mensaje="";
    private String codCliente;
    private Context context;
    private int fk_maquina;
    private int fk_parte;
    private int fk_direccion;
    private int fk_marca;
    private String modelo;
    private String num_serie;

    private String puesta_marcha;

    private String temperatura_max_acs;
    private String caudal_acs;
    private String potencia_util;
    private String temperatura_agua_generador_calor_entrada;
    private String temperatura_agua_generador_calor_salida;


    private Cliente cliente;



    public HiloActualizaMaquina(int fk_maquina, int fk_parte, int fk_direccion, int fk_marca,
                                 String modelo, String num_serie, String puesta_marcha,  String temperatura_max_acs, String caudal_acs,
                                String potencia_util, String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida) {

        this.fk_maquina=fk_maquina;
        this.fk_parte=fk_parte;
        this.fk_direccion=fk_direccion;
        this.fk_marca=fk_marca;
        this.modelo=modelo;
        this.num_serie=num_serie;
        this.puesta_marcha=puesta_marcha;
        this.temperatura_max_acs=temperatura_max_acs;
        this.caudal_acs=caudal_acs;
        this.potencia_util=potencia_util;
        this.temperatura_agua_generador_calor_entrada=temperatura_agua_generador_calor_entrada;
        this.temperatura_agua_generador_calor_salida=temperatura_agua_generador_calor_salida;
        try {
            cliente = ClienteDAO.buscarCliente(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @Override
    protected Void doInBackground(Void... voids) {
        try {
            mensaje = actualizaMaquina();
        } catch (JSONException e) {
            mensaje = "JSONException";
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
            try {
                JSONObject jsonObject = new JSONObject(mensaje);
                if (jsonObject.getInt("estado")==1){
                        Log.d("Hilo actualizar maquina", "correcto");
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{

        }

    }

    private String actualizaMaquina() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("id_maquina",fk_maquina);
        msg.put("fk_parte",fk_parte);
        msg.put("fk_direccion",fk_direccion);
        msg.put("fk_marca",fk_marca);
        msg.put("num_serie",num_serie);
        msg.put("modelo",modelo);
        msg.put("puesta_marcha",puesta_marcha);
        msg.put("temperatura_max_acs",temperatura_max_acs);
        msg.put("caudal_acs",caudal_acs);
        msg.put("potencia_util",potencia_util);
        msg.put("temperatura_agua_generador_calor_entrada",temperatura_agua_generador_calor_entrada);
        msg.put("temperatura_agua_generador_calor_salida",temperatura_agua_generador_calor_salida);
        Log.d("JSON_ACTUALIZAR",msg.toString());
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+cliente.getIp_cliente()+Constantes.URL_ACTUALIZA_MAQUINA);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_ACTUALIZA_MAQUINA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_ACTUALIZA_MAQUINA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_ACTUALIZA_MAQUINA,jsonArray.toString());
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, IOException");
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
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_ACTUALIZA_MAQUINA,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error de lectura");
            contenido = error.toString();
        }
        return contenido;
    }

}
