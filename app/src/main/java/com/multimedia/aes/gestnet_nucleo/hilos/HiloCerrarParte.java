package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
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

import static com.multimedia.aes.gestnet_nucleo.fragment.TabFragment5_documentacion.resizeImage;

public class HiloCerrarParte  extends AsyncTask<Void,Void,Void> {

    private String mensaje;
    private Context context;
    private int fk_parte;
    private ProgressDialog dialog;

    public HiloCerrarParte(Context context, int fk_parte) {
        this.context = context;
        this.fk_parte = fk_parte;
    }



    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Cerrando Parte.");
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
        } catch (SQLException e) {
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
        try {
            msg=rellenarJsonMantenimientos();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    private JSONObject rellenarJsonMantenimientos() throws JSONException, SQLException, IOException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        Parte parte = ParteDAO.buscarPartePorId(context,fk_parte);
        jsonObject1.put("fk_estado",parte.getFk_estado());
        jsonObject1.put("id_parte",parte.getId_parte());
        jsonObject1.put("confirmado",parte.getConfirmado());
        jsonObject1.put("observaciones",parte.getObservaciones());
        jsonObject1.put("estado_android",3);
        jsonObject1.put("firma64",parte.getFirma64());
        jsonObject1.put("firmante",parte.getNombre_firmante());

        DatosAdicionales datos_adicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context,fk_parte);
        jsonObject2.put("fk_parte",parte.getId_parte());
        jsonObject2.put("fk_formas_pago",datos_adicionales.getFk_forma_pago());
        jsonObject2.put("preeu_puesta_marcha",datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("fact_puesta_en_marcha",datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("preeu_disposicion_servicio",datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("fact_disposicion_servicio",datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("preeu_mano_de_obra_precio",datos_adicionales.getPreeu_mano_de_obra_precio());
        jsonObject2.put("preeu_mano_de_obra",datos_adicionales.getPreeu_mano_de_obra());
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
        JSONArray jsonArray1 = new JSONArray();
        if(ArticuloParteDAO.buscarArticuloParteFkParte(context,fk_parte)!=null){
        articulosParte.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(context,fk_parte));
            for (ArticuloParte articuloParte: articulosParte) {
                JSONObject obj = new JSONObject();
                Articulo a = ArticuloDAO.buscarArticuloPorID(context,articuloParte.getFk_articulo());
                for (int i = 0; i < articuloParte.getUsados(); i++) {
                    obj.put("fk_parte",parte.getId_parte());
                    obj.put("fk_producto",a.getFk_articulo());
                    obj.put("nombre_articulo",a.getNombre_articulo());
                    obj.put("stock",a.getStock());
                    obj.put("marca",a.getMarca());
                    obj.put("modelo",a.getModelo());
                    obj.put("iva",a.getIva());
                    obj.put("tarifa",a.getTarifa());
                    obj.put("descuento",a.getDescuento());
                    obj.put("coste",a.getCoste());
                    obj.put("garantia",a.isGarantia());
                    if(a.isGarantia())
                        obj.put("garantia",1);
                    else
                        obj.put("garantia",0);
                    jsonArray1.put(obj);
                }
            }
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





            JSONObject jsonObject5 = new JSONObject();
            JSONArray jsonArraya = new JSONArray();
        for (Maquina maquina:arrayList) {

            jsonObject5.put("id_maquina",maquina.getId_maquina());
            jsonObject5.put("fk_modelo",maquina.getModelo());
            jsonObject5.put("num_serie",maquina.getNum_serie());
            jsonObject5.put("puesta_marcha",maquina.getPuesta_marcha());
            jsonObject5.put("fk_marca",maquina.getFk_marca());
            jsonArraya.put(jsonObject5);
        }




        JSONObject jsonObject6 = new JSONObject();
        JSONArray jsonArray6 = new JSONArray();
        ArrayList<ProtocoloAccion> arrayLisProto = new ArrayList<>();
        arrayLisProto.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(context, parte.getId_parte()));

        for (ProtocoloAccion protocoloAccion : arrayLisProto) {

            jsonObject5.put("fk_maquina",protocoloAccion.getFk_maquina());
            jsonObject5.put("fk_parte",protocoloAccion.getFk_parte());
            jsonObject5.put("valor",protocoloAccion.getValor());
            jsonObject5.put("fk_protocolo",protocoloAccion.getFk_protocolo());
            jsonObject5.put("nombre_protocolo",protocoloAccion.getNombre_protocolo());
            jsonObject5.put("id_accion",protocoloAccion.getId_accion());
            jsonObject5.put("tipo_accion",protocoloAccion.isTipo_accion());
            jsonObject5.put("descripcion",protocoloAccion.getDescripcion());

            jsonArray6.put(jsonObject6);
        }



        msg.put("sat_partes",jsonObject1);
        msg.put("datos_adicionales",jsonObject2);
        msg.put("da_items",jsonArray1);
        msg.put("datos_maquina",jsonArray2);
        msg.put("imagenes",rellenarJsonImagenes());
        msg.put("maquina",jsonArraya);
        msg.put("protocolos",jsonArray6);


        Log.d("jsoooooooon",msg.toString());




        return msg;

    }

    private JSONArray rellenarJsonImagenes() throws JSONException, IOException, SQLException {
        List<Imagen> arraylistImagenes = new ArrayList<>();
        JSONObject js = new JSONObject();
        JSONArray jsa = new JSONArray();
        if(ImagenDAO.buscarImagenPorFk_parte(context,fk_parte)!=null) {
            arraylistImagenes.addAll(ImagenDAO.buscarImagenPorFk_parte(context, fk_parte));

            for (int i = 0; i < arraylistImagenes.size(); i++) {
                File f = new File(arraylistImagenes.get(i).getRuta_imagen());
                Bitmap b = null;
                b = BitmapFactory.decodeStream(new FileInputStream(f));
                b = resizeImage(b);
                JSONObject jso = new JSONObject();
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.PNG, 100, baos);
                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    jso.put("fk_parte", arraylistImagenes.get(i).getFk_parte());
                    jso.put("nombre", arraylistImagenes.get(i).getNombre_imagen());
                    jso.put("base64", encodedImage);
                    jsa.put(jso);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsa;
    }
}
