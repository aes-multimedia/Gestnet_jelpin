package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;

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
    private String fk_tipo_combustion;
    private int fk_protocolo;
    private int fk_instalador;
    private int fk_remoto_central;
    private int fk_tipo;
    private int fk_instalacion;
    private int fk_estado;
    private int fk_contrato_mantenimiento;
    private int fk_gama;
    private int fk_tipo_gama;
    private String fecha_creacion;
    private String modelo;
    private String num_serie;
    private String num_producto;
    private String aparato;
    private String puesta_marcha;
    private String fecha_compra;
    private String fecha_fin_garantia;
    private String mantenimiento_anual;
    private String observaciones;
    private String ubicacion;
    private String tienda_compra;
    private String garantia_extendida;
    private String factura_compra;
    private String refrigerante;
    private boolean bEsInstalacion;
    private String nombre_instalacion;
    private String en_propiedad;
    private String esPrincipal;
    private String situacion;
    private String temperatura_max_acs;
    private String caudal_acs;
    private String potencia_util;
    private String temperatura_agua_generador_calor_entrada;
    private String temperatura_agua_generador_calor_salida;



    public HiloActualizaMaquina(int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String fk_tipo_combustion, int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion, int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama, String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato, String puesta_marcha, String fecha_compra, String fecha_fin_garantia, String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra, String garantia_extendida, String factura_compra, String refrigerante, boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal, String situacion, String temperatura_max_acs, String caudal_acs, String potencia_util, String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida) {

        this.fk_maquina=fk_maquina;
        this.fk_parte=fk_parte;
        this.fk_direccion=fk_direccion;
        this.fk_marca=fk_marca;
        this.fk_tipo_combustion=fk_tipo_combustion;
        this.fk_protocolo=fk_protocolo;
        this.fk_instalador=fk_instalador;
        this.fk_remoto_central=fk_remoto_central;
        this.fk_tipo=fk_tipo;
        this.fk_instalacion=fk_instalacion;
        this.fk_estado=fk_estado;
        this.fk_contrato_mantenimiento=fk_contrato_mantenimiento;
        this.fk_gama=fk_gama;
        this.fk_tipo_gama=fk_tipo_gama;
        this.fecha_creacion=fecha_creacion;
        this.modelo=modelo;
        this.num_serie=num_serie;
        this.num_producto=num_producto;
        this.aparato=aparato;
        this.puesta_marcha=puesta_marcha;
        this.fecha_compra=fecha_compra;
        this.fecha_fin_garantia=fecha_fin_garantia;
        this.mantenimiento_anual=mantenimiento_anual;
        this.observaciones=observaciones;
        this.ubicacion=ubicacion;
        this.tienda_compra=tienda_compra;
        this.garantia_extendida=garantia_extendida;
        this.factura_compra=factura_compra;
        this.refrigerante=refrigerante;
        this.bEsInstalacion=bEsInstalacion;
        this.nombre_instalacion=nombre_instalacion;
        this.en_propiedad=en_propiedad;
        this.esPrincipal=esPrincipal;
        this.situacion=situacion;
        this.temperatura_max_acs=temperatura_max_acs;
        this.caudal_acs=caudal_acs;
        this.potencia_util=potencia_util;
        this.temperatura_agua_generador_calor_entrada=temperatura_agua_generador_calor_entrada;
        this.temperatura_agua_generador_calor_salida=temperatura_agua_generador_calor_salida;


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

                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{

        }

    }

    private String actualizaMaquina() throws JSONException{
        JSONObject msg = new JSONObject();
        msg.put("fk_maquina",fk_maquina);
        msg.put("fk_parte",fk_parte);
        msg.put("fk_direccion",fk_direccion);
        msg.put("fk_marca",fk_marca);
        msg.put("fk_tipo_combustion",fk_tipo_combustion);
        msg.put("fk_protocolo",fk_protocolo);
        msg.put("fk_instalador",fk_instalador);
        msg.put("fk_remoto_central",fk_remoto_central);
        msg.put("fk_tipo",fk_tipo);
        msg.put("fk_instalacion",fk_instalacion);
        msg.put("fk_estado",fk_estado);
        msg.put("fk_contrato_mantenimiento",fk_contrato_mantenimiento);
        msg.put("fk_gama",fk_gama);
        msg.put("fk_tipo_gama",fk_tipo_gama);
        msg.put("fecha_creacion",fecha_creacion);
        msg.put("modelo",modelo);
        msg.put("num_serie",num_serie);
        msg.put("num_producto",num_producto);
        msg.put("aparato",aparato);
        msg.put("puesta_marcha",puesta_marcha);
        msg.put("fecha_compra",fecha_compra);
        msg.put("fecha_fin_garantia",fecha_fin_garantia);
        msg.put("mantenimiento_anual",mantenimiento_anual);
        msg.put("observaciones",observaciones);
        msg.put("ubicacion",ubicacion);
        msg.put("tienda_compra",tienda_compra);
        msg.put("garantia_extendida",garantia_extendida);
        msg.put("factura_compra",factura_compra);
        msg.put("refrigerante",refrigerante);
        msg.put("bEsInstalacion",bEsInstalacion);
        msg.put("nombre_instalacion",nombre_instalacion);
        msg.put("en_propiedad",en_propiedad);
        msg.put("esPrincipal",esPrincipal);
        msg.put("situacion",situacion);
        msg.put("temperatura_max_acs",temperatura_max_acs);
        msg.put("caudal_acs",caudal_acs);
        msg.put("potencia_util",potencia_util);
        msg.put("temperatura_agua_generador_calor_entrada",temperatura_agua_generador_calor_entrada);
        msg.put("temperatura_agua_generador_calor_salida)",temperatura_agua_generador_calor_salida);
        Log.d("JSON_ACTUALIZAR",msg.toString());

        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_ACTUALIZA_MAQUINA);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type","application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error de protocolo");
            return error.toString();
        } catch (IOException e) {
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
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado",5);
            error.put("mensaje","Error de conexión, error de lectura");
            contenido = error.toString();
        }
        return contenido;
    }

}
