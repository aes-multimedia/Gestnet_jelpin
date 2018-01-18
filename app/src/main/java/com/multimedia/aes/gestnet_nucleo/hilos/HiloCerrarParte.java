package com.multimedia.aes.gestnet_nucleo.hilos;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
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
import java.util.ArrayList;
import java.util.List;

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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        if (mensaje.indexOf('}')!=-1){
            try {
                ParteDAO.actualizarEstadoAndroid(context,fk_parte,3);
                ((Index)context).recreate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else{
        }
    }
    private String iniciar() throws JSONException, SQLException {
        JSONObject msg = new JSONObject();
        msg=rellenarJsonMantenimientos();
        Log.w("JSON_SUBIDA", String.valueOf(msg));
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL(Constantes.URL_CIERRE_PARTE_EXTERNAPRUEBAS);
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

    private JSONObject rellenarJsonMantenimientos() throws JSONException, SQLException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        Parte parte = ParteDAO.buscarPartePorId(context,fk_parte);
        jsonObject1.put("fk_estado",parte.getFk_estado());
        jsonObject1.put("id_parte",parte.getId_parte());
        jsonObject1.put("confirmado",parte.getConfirmado());
        jsonObject1.put("observaciones",parte.getObservaciones());
        jsonObject1.put("estado_android",3);

        DatosAdicionales datos_adicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context,fk_parte);
        jsonObject2.put("fk_parte",parte.getId_parte());
        jsonObject2.put("fk_formas_pago",datos_adicionales.getFk_forma_pago());
        jsonObject2.put("preeu_puesta_marcha",datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("fact_puesta_en_marcha",datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("preeu_disposicion_servicio",datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("fact_disposicion_servicio",datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("preeu_mano_de_obra_precio",datos_adicionales.getFact_manodeobra_precio());
        jsonObject2.put("fact_manodeobra_precio",datos_adicionales.getFact_manodeobra_precio());
        jsonObject2.put("preeu_servicio_urgencia",datos_adicionales.getPreeu_servicio_urgencia());
        jsonObject2.put("fact_servicio_urgencia",datos_adicionales.getPreeu_servicio_urgencia());
        jsonObject2.put("preeu_km_precio",datos_adicionales.getPreeu_km_precio());
        jsonObject2.put("preeu_km",datos_adicionales.getPreeu_km());
        jsonObject2.put("preeu_km_precio_total",datos_adicionales.getPreeu_km()*datos_adicionales.getPreeu_km_precio());
        jsonObject2.put("fact_km",datos_adicionales.getPreeu_km());
        jsonObject2.put("fact_km_precio",datos_adicionales.getPreeu_km_precio());
        jsonObject2.put("fact_km_precio_total",datos_adicionales.getPreeu_km_precio()*datos_adicionales.getPreeu_km());
        jsonObject2.put("operacion_efectuada",datos_adicionales.getOperacion_efectuada());
        jsonObject2.put("preeu_adicional",datos_adicionales.getFact_adicional());
        jsonObject2.put("preeu_otros_nombre",datos_adicionales.getPreeu_otros_nombre());
        jsonObject2.put("preeu_otros_nombre",datos_adicionales.getPreeu_otros_nombre());
        jsonObject2.put("preeu_otros_nombre",datos_adicionales.getPreeu_materiales());
        jsonObject2.put("fact_materiales",datos_adicionales.getFact_materiales());



        ArrayList<ArticuloParte> articulosParte = new ArrayList<>();
        articulosParte.addAll(ArticuloParteDAO.buscarTodosLosArticuloPartePorFkParte(context,fk_parte));
        JSONArray jsonArray1 = new JSONArray();
            for (ArticuloParte articuloParte: articulosParte) {
                JSONObject obj = new JSONObject();
                Articulo a = ArticuloDAO.buscarArticuloPorID(context,articuloParte.getFk_articulo());
                obj.put("fk_parte",parte.getId_parte());
                obj.put("nombre_articulo",a.getNombre_articulo());
                obj.put("stock",a.getStock());
                obj.put("marca",a.getMarca());
                obj.put("modelo",a.getModelo());
                obj.put("iva",a.getIva());
                obj.put("tarifa",a.getTarifa());
                obj.put("descuento",a.getDescuento());
                obj.put("coste",a.getCoste());

                jsonArray1.put(obj);
        }







        JSONArray jsonArray2 = new JSONArray();
        ArrayList<Maquina> arrayList = new ArrayList<>();
        arrayList.addAll(MaquinaDAO.buscarMaquinaPorFkParte(context,parte.getId_parte()));
        for (Maquina maquina:arrayList) {
            JSONObject jsonObject4 = new JSONObject();
            if (AnalisisDAO.buscarAnalisisPorFkMaquina(context,maquina.getId_maquina())!=null){
                Analisis analisis = AnalisisDAO.buscarAnalisisPorFkMaquina(context,maquina.getId_maquina()).get(0);
                jsonObject4.put("fk_maquina",analisis.getFk_maquina());
                jsonObject4.put("fk_parte",analisis.getFk_parte());
                jsonObject4.put("coMaquina",analisis.getC0_maquina());
                jsonObject4.put("tempGasCombustion",analisis.getTemperatura_gases_combustion());
                jsonObject4.put("tempAmbLocal",analisis.getTemperatura_ambiente_local());
                jsonObject4.put("rdtoMaquina",analisis.getRendimiento_aparato());
                jsonObject4.put("coCorregido",analisis.getCo_corregido());
                jsonObject4.put("coAmbiente",analisis.getCo_ambiente());
                jsonObject4.put("co2amb",analisis.getCo2_ambiente());
                jsonObject4.put("tiro",analisis.getTiro());
                jsonObject4.put("co2Testo",analisis.getCo2());
                jsonObject4.put("o2Testo",analisis.getO2());
                jsonObject4.put("lambda",analisis.getLambda());
                jsonObject4.put("nombre_medicion",analisis.getNombre_medicion());
            }
            jsonObject4.put("fk_parte",parte.getId_parte());
            jsonObject4.put("tempMaxACS",maquina.getTemperatura_max_acs());
            jsonObject4.put("caudalACS",maquina.getCaudal_acs());
            jsonObject4.put("potenciaUtil",maquina.getPotencia_util());
            jsonObject4.put("tempAguaGeneradorCalorEntrada",maquina.getTemperatura_agua_generador_calor_entrada());
            jsonObject4.put("tempAguaGeneradorCalorSalida",maquina.getTemperatura_agua_generador_calor_salida());
            jsonArray2.put(jsonObject4);
        }


        msg.put("sat_partes",jsonObject1);
        msg.put("datos_adicionales",jsonObject2);
        msg.put("da_items",jsonArray1);
        msg.put("datos_maquina",jsonArray2);













        return msg;

    }
}
