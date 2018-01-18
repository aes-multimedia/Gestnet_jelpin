package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
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

public class HiloCerrarParte  extends AsyncTask<Void,Void,Void> {

    private String mensaje;
    private Context context;
    private int fk_parte;

    public HiloCerrarParte(Context context, int fk_parte) {
        this.context = context;
        this.fk_parte = fk_parte;
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
        if (mensaje.indexOf('}')!=-1){
        }else{
        }
    }
    private String iniciar() throws JSONException {
        JSONObject msg = new JSONObject();

        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_ARTICULOS_EXTERNAPRUEBAS);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
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
            e.printStackTrace();
        }
        return contenido;
    }

    private JSONObject rellenarJsonMantenimientos(int id) throws JSONException, SQLException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();
        JSONObject jsonObject3 = new JSONObject();
        JSONObject jsonObject4 = new JSONObject();

        Parte parte = ParteDAO.buscarPartePorId(context,fk_parte);
        jsonObject1.put("fk_estado",parte.getFk_estado());
        jsonObject1.put("confirmado",parte.getConfirmado());
        jsonObject1.put("observaciones",parte.getObservaciones());
        jsonObject1.put("estado_android",parte.getEstado_android());

        DatosAdicionales datos_adicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context,fk_parte);
        jsonObject2.put("fk_formas_pago",datos_adicionales.getFk_forma_pago());
        jsonObject2.put("preeu_puesta_marcha",datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("preeu_disposicion_servicio",datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("preeu_mano_de_obra_precio",datos_adicionales.getFact_manodeobra_precio());
        jsonObject2.put("preeu_servicio_urgencia",datos_adicionales.getPreeu_servicio_urgencia());
        jsonObject2.put("preeu_km_precio",datos_adicionales.getPreeu_km_precio());
        jsonObject2.put("preeu_km",datos_adicionales.getPreeu_km());
        jsonObject2.put("operacion_efectuada",datos_adicionales.getOperacion_efectuada());


        /*
        Stock

        cantidad
        fk_producto
        fk_parte
        */
        ArticuloParte alm_stock = ArticuloParteDAO.buscarArticuloPartePorFkParte(context,fk_parte);

        Maquina maquina = MaquinaDAO.buscarMaquinaPorFkParte(context,fk_parte).get(0);
        Analisis analisis = AnalisisDAO.buscarAnalisisPorFkMaquina(context,maquina.getId_maquina()).get(0);

        jsonObject4.put("temperatura_max_acs",maquina.getTemperatura_max_acs());
        jsonObject4.put("caudal_acs",maquina.getCaudal_acs());
        jsonObject4.put("potencia_util",maquina.getPotencia_util());
        jsonObject4.put("temperatura_agua_generador_calor_entrada",maquina.getTemperatura_agua_generador_calor_entrada());
        jsonObject4.put("temperatura_agua_generador_calor_salida",maquina.getTemperatura_agua_generador_calor_salida());

        jsonObject4.put("fk_maquina",analisis.getFk_maquina());
        jsonObject4.put("fk_parte",analisis.getFk_parte());
        jsonObject4.put("c0_maquina",analisis.getC0_maquina());
        jsonObject4.put("temperatura_gases_combustion",analisis.getTemperatura_gases_combustion());
        jsonObject4.put("temperatura_ambiente_local",analisis.getTemperatura_ambiente_local());
        jsonObject4.put("rendimiento_aparato",analisis.getRendimiento_aparato());
        jsonObject4.put("co_corregido",analisis.getCo_corregido());
        jsonObject4.put("co_ambiente",analisis.getCo_ambiente());
        jsonObject4.put("co2_ambiente",analisis.getCo2_ambiente());
        jsonObject4.put("tiro",analisis.getTiro());
        jsonObject4.put("co2",analisis.getCo2());
        jsonObject4.put("o2",analisis.getO2());
        jsonObject4.put("lambda",analisis.getLambda());
        jsonObject4.put("bCampana",analisis.getbCampana());
        jsonObject4.put("bMaxima_potencia",analisis.getbMaxima_potencia());
        jsonObject4.put("bMinima_potencia",analisis.getbMinima_potencia());
        jsonObject4.put("nombre_medicion",analisis.getNombre_medicion());
















        return msg;

    }
}
