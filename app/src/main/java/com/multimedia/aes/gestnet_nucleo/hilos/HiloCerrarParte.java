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
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
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
    private ProgressDialog dialog;
    private Cliente cliente;
    private Usuario usuario;
    private  String data;

    public HiloCerrarParte(Context context, int fk_parte) {
        this.context = context;
        this.fk_parte = fk_parte;
        try {
            cliente= ClienteDAO.buscarCliente(context);
            usuario = UsuarioDAO.buscarUsuario(context);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
                int estado=0;
                try {
                    JSONObject jsonObject= new JSONObject(mensaje);
                    if(jsonObject.getString("estado")!=null && !jsonObject.getString("estado").equals(""))

                        estado=jsonObject.getInt("estado");

                        JSONArray resultados =jsonObject.getJSONArray("datos");
                    JSONArray items = resultados.getJSONArray(1);
                    for (int i=0; i<items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        JSONObject itemsValues  =  item.getJSONObject("entrada");
                        int id_gestnet = Integer.parseInt(itemsValues.getString("id_item_gestnet").toString());
                        int id_parte= Integer.parseInt(itemsValues.getString("id_parte").toString());
                        int id_android = Integer.parseInt(itemsValues.getString("id_android").toString());
                        try{
                            ArticuloParte artParte = ArticuloParteDAO.buscarArticuloPartePorFkParteFkArticulo(context,id_android,id_parte);
                            int idArtParte = artParte.getId();
                            ArticuloParteDAO.actualizarIdItemGestnet(context,id_gestnet,idArtParte);
                        }catch (SQLException E){
                            E.printStackTrace();
                        }
                        Log.d("resultados",item.get("entrada").toString());

                        // TODO: HACER ACTUALIZACION DE ID_ITEM_GESTNET'
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

                if(estado==436){
                    Log.d("json_respuesta",mensaje);
                    ParteDAO.actualizarEstadoAndroid(context,fk_parte,436);
                    ((Index)context).recreate();
                }else{
                    Log.d("json_respuesta",mensaje);
                    ParteDAO.actualizarEstadoAndroid(context,fk_parte,3);
                    ((Index)context).recreate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private String iniciar() throws JSONException, SQLException {
        JSONObject msg = new JSONObject();
        try {
            msg=rellenarJsonMantenimientos();
            data = msg.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
       /* Log.w("JSON_SUBIDA", String.valueOf(msg));*/
        URL urlws = null;
        HttpURLConnection uc = null;
        try {
            urlws = new URL("http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE);
            uc = (HttpURLConnection) urlws.openConnection();
            uc.setDoOutput(true);
            uc.setDoInput(true);
            uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            uc.setRequestMethod("POST");
            uc.connect();
        } catch (MalformedURLException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 404);
            error.put("mensaje", "Error de conexion, URL malformada");
            return error.toString();
        } catch (ProtocolException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 505);
            error.put("mensaje", "Error de conexion, error de protocolo");
            return error.toString();
        } catch (IOException e) {
            JSONArray jsonArray = new JSONArray();
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 436);
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
            EnvioDAO.newEnvio(context,msg.toString(),"http://"+cliente.getIp_cliente()+Constantes.URL_CIERRE_PARTE,jsonArray.toString());
            e.printStackTrace();
            JSONObject error = new JSONObject();
            error.put("estado", 5);
            error.put("mensaje", "Error de conexion, IOException");
            return error.toString();
        }
        return contenido;
    }
    private JSONObject rellenarJsonMantenimientos() throws JSONException, SQLException, IOException {
        JSONObject msg = new JSONObject();
        JSONObject jsonObject1 = new JSONObject();
        JSONObject jsonObject2 = new JSONObject();

        Parte parte = ParteDAO.buscarPartePorId(context, fk_parte);
        jsonObject1.put("fk_estado", asignarEstado());
        jsonObject1.put("id_parte", parte.getId_parte());
        jsonObject1.put("fk_tipo_os", parte.getFk_tipo_os0());
        jsonObject1.put("confirmado", parte.getConfirmado());
        jsonObject1.put("observaciones", parte.getObservaciones());
        if (asignarEstado()==8){
            jsonObject1.put("estado_android", 2);
        }else{
            jsonObject1.put("estado_android", 3);
        }
        jsonObject1.put("firma64", parte.getFirma64());
        jsonObject1.put("firma64Tecnico", usuario.getFirma());
        jsonObject1.put("firmante", parte.getNombre_firmante());
        jsonObject1.put("dnifirma", parte.getDni_firmante());

        DatosAdicionales datos_adicionales = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(context, fk_parte);
        jsonObject2.put("fk_parte", parte.getId_parte());
        jsonObject2.put("fk_formas_pago", datos_adicionales.getFk_forma_pago());

        jsonObject2.put("preeu_materiales", datos_adicionales.getPreeu_materiales());
        jsonObject2.put("preeu_disposicion_servicio", datos_adicionales.getPreeu_disposicion_servicio());
        jsonObject2.put("preeu_mano_de_obra_precio", datos_adicionales.getPreeu_mano_de_obra_precio());
        jsonObject2.put("preeu_mano_de_obra_horas", datos_adicionales.getPreeu_mano_de_obra());
        jsonObject2.put("preeu_puesta_marcha", datos_adicionales.getPreeu_puesta_marcha());
        jsonObject2.put("preeu_servicio_urgencia", datos_adicionales.getPreeu_servicio_urgencia());
        jsonObject2.put("preeu_km", datos_adicionales.getPreeu_km());
        jsonObject2.put("preeu_km_precio", datos_adicionales.getPreeu_km_precio());
        jsonObject2.put("preeu_km_precio_total", datos_adicionales.getPreeu_km_precio_total());
        jsonObject2.put("preeu_analisis_combustion", datos_adicionales.getPreeu_analisis_combustion());
        jsonObject2.put("preeu_otros_nombre", datos_adicionales.getPreeu_otros_nombre());
        jsonObject2.put("preeu_adicional_coste", datos_adicionales.getPreeu_adicional());
        jsonObject2.put("preeu_iva_aplicado", datos_adicionales.getPreeu_iva_aplicado());

        jsonObject2.put("total", datos_adicionales.getTotal_ppto());
        jsonObject2.put("fact_total_con_iva", datos_adicionales.getFact_total_con_iva());
        jsonObject2.put("fact_por_iva_aplicado", datos_adicionales.getFact_por_iva_aplicado());
        jsonObject2.put("fact_materiales", datos_adicionales.getFact_materiales());
        jsonObject2.put("acepta_presupuesto", datos_adicionales.getBaceptapresupuesto());
        jsonObject2.put("matem_hora_entrada", datos_adicionales.getMatem_hora_entrada());
        jsonObject2.put("matem_hora_salida", datos_adicionales.getMatem_hora_salida());
        jsonObject2.put("operacion_efectuada", datos_adicionales.getOperacion_efectuada());


        ArrayList<ArticuloParte> articulosParte = new ArrayList<>();
        JSONArray jsonArray1 = new JSONArray();
        if (ArticuloParteDAO.buscarArticuloParteFkParte(context, fk_parte) != null) {
            articulosParte.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(context, fk_parte));
            for (ArticuloParte articuloParte : articulosParte) {
                JSONObject obj = new JSONObject();
                Articulo a = ArticuloDAO.buscarArticuloPorID(context, articuloParte.getFk_articulo());
                //a.isGarantia()
                //a.isEntregado()
                    obj.put("fk_parte", parte.getId_parte());
                    obj.put("id_item", a.getId_item_gestnet());
                    obj.put("id_articulo_android", a.getId_articulo());
                    obj.put("fk_producto", a.getFk_articulo());
                    obj.put("nombre_articulo", a.getNombre_articulo());
                    obj.put("stock", a.getStock());
                    obj.put("marca", a.getMarca());
                    obj.put("modelo", a.getModelo());
                    obj.put("iva", articuloParte.getIva());
                    obj.put("descuento",articuloParte.getDescuento());
                    obj.put("coste",articuloParte.getCoste());
                    obj.put("entregado",articuloParte.getEntregado());
                    obj.put("cantidad", articuloParte.getUsados());
                    if (datos_adicionales.getBaceptapresupuesto())  obj.put("presupuesto", 1);
                            else obj.put("presupuesto", 0);
                    if(datos_adicionales.getBaceptapresupuesto() && articuloParte.getEntregado()) obj.put("preparada_pieza", 1);
                            else obj.put("preparada_pieza", 0);
                    if (articuloParte.getGarantia())
                        {
                        obj.put("garantia", 1);
                        obj.put("tarifa", 0);
                        }
                    else
                        {
                        obj.put("tarifa", articuloParte.getTarifa());
                        obj.put("garantia", 0);
                        }
                    jsonArray1.put(obj);
            }
        }


        JSONArray jsonArray2 = new JSONArray();
        ArrayList<Maquina> arrayList = new ArrayList<>();

        if (MaquinaDAO.buscarMaquinaPorFkParte(context, parte.getId_parte()) != null) {

            arrayList.addAll(MaquinaDAO.buscarMaquinaPorFkParte(context, parte.getId_parte()));


            for (Maquina maquina : arrayList) {
                JSONObject jsonObject4 = new JSONObject();
                if (AnalisisDAO.buscarAnalisisPorFkMaquina(context, maquina.getId_maquina()) != null) {
                    Analisis analisis = AnalisisDAO.buscarAnalisisPorFkMaquina(context, maquina.getId_maquina()).get(0);
                    jsonObject4.put("fk_maquina", analisis.getFk_maquina());
                    jsonObject4.put("fk_parte", analisis.getFk_parte());
                    jsonObject4.put("coMaquina", analisis.getC0_maquina());
                    jsonObject4.put("tempGasCombustion", analisis.getTemperatura_gases_combustion());
                    jsonObject4.put("tempAmbLocal", analisis.getTemperatura_ambiente_local());
                    jsonObject4.put("rdtoMaquina", analisis.getRendimiento_aparato());
                    jsonObject4.put("coCorregido", analisis.getCo_corregido());
                    jsonObject4.put("coAmbiente", analisis.getCo_ambiente());
                    jsonObject4.put("co2amb", analisis.getCo2_ambiente());
                    jsonObject4.put("tiro", analisis.getTiro());
                    jsonObject4.put("co2Testo", analisis.getCo2());
                    jsonObject4.put("o2Testo", analisis.getO2());
                    jsonObject4.put("lambda", analisis.getLambda());
                    jsonObject4.put("nombre_medicion", analisis.getNombre_medicion());
                }
                jsonObject4.put("fk_maquina", maquina.getId_maquina());
                jsonObject4.put("fk_parte", parte.getId_parte());
                jsonObject4.put("tempMaxACS", maquina.getTemperatura_max_acs());
                jsonObject4.put("caudalACS", maquina.getCaudal_acs());
                jsonObject4.put("potenciaUtil", maquina.getPotencia_util());
                jsonObject4.put("tempAguaGeneradorCalorEntrada", maquina.getTemperatura_agua_generador_calor_entrada());
                jsonObject4.put("tempAguaGeneradorCalorSalida", maquina.getTemperatura_agua_generador_calor_salida());
                jsonArray2.put(jsonObject4);
            }
        }



        JSONArray jsonArraya = new JSONArray();
        for (Maquina maquina : arrayList) {
            JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("id_maquina", maquina.getId_maquina());
            jsonObject5.put("fk_modelo", maquina.getModelo());
            jsonObject5.put("num_serie", maquina.getNum_serie());
            jsonObject5.put("puesta_marcha", maquina.getPuesta_marcha());
            jsonObject5.put("fk_marca", maquina.getFk_marca());

            jsonArraya.put(jsonObject5);
        }



        JSONArray jsonArray6 = new JSONArray();
        ArrayList<ProtocoloAccion> arrayLisProto = new ArrayList<>();
        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(context, parte.getId_parte()) != null){
            try {
                arrayLisProto = ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(context, parte.getId_parte());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        for (ProtocoloAccion protocoloAccion : arrayLisProto) {
            JSONObject jsonObject6 = new JSONObject();
            jsonObject6.put("fk_maquina", protocoloAccion.getFk_maquina());
            jsonObject6.put("fk_parte", protocoloAccion.getFk_parte());
            jsonObject6.put("fk_accion_protocolo", protocoloAccion.getId_protocolo_accion());
            jsonObject6.put("valor", protocoloAccion.getValor());


            jsonArray6.put(jsonObject6);

        }
    }



        JSONObject jsonObject8 = new JSONObject();
        jsonObject8.put("enviar",parte.isEnviarPorCorreo());
        jsonObject8.put("dir",parte.getEmailEnviarFactura());

        JSONObject jsonObject7 = new JSONObject();
        jsonObject7.put("fk_parte",parte.getId_parte());
        jsonObject7.put("nombre",parte.getNombre_firmante());
        jsonObject7.put("firma","1");
        jsonObject7.put("base64",parte.getFirma64());



        msg.put("sat_partes",jsonObject1);
        msg.put("datos_adicionales",jsonObject2);
        msg.put("da_items",jsonArray1);
        msg.put("datos_maquina",jsonArray2);
        msg.put("imagenes",rellenarJsonImagenes(parte));
        msg.put("maquina",jsonArraya);
        msg.put("protocolos",jsonArray6);
        msg.put("firma",jsonObject7);
        msg.put("factura",jsonObject8);


       // Log.d("json_subida",msg.toString());


        return msg;


    }

    private int asignarEstado() throws SQLException {
       int estado = 4;
        if (ArticuloParteDAO.buscarArticuloParteFkParte(context, fk_parte) != null) {
            ArrayList<ArticuloParte> articulosParte = new ArrayList<>();
            articulosParte.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(context, fk_parte));
            for(ArticuloParte articulo : articulosParte) {
                Articulo a = ArticuloDAO.buscarArticuloPorID(context, articulo.getFk_articulo());
                if(a.isEntregado()==1) {
                    ParteDAO.actualizarEstadoParte(context, fk_parte, 8);
                    estado = 8;
                }
            }
        }
        return estado;
    }

    private JSONArray rellenarJsonImagenes(Parte parte) throws  IOException, SQLException {
        List<Imagen> arraylistImagenes = new ArrayList<>();
        JSONArray jsa = new JSONArray();
        if(ImagenDAO.buscarImagenPorFk_parte(context,fk_parte)!=null) {
            arraylistImagenes.addAll(ImagenDAO.buscarImagenPorFk_parte(context, fk_parte));
            for (int i = 0; i < arraylistImagenes.size(); i++) {
                JSONObject jso = new JSONObject();
                File f = new File(arraylistImagenes.get(i).getRuta_imagen());
                Bitmap b;
                b=ShrinkBitmap(f.getAbsolutePath(),300,300);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                b.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                b.getByteCount();

                byte[] imageBytes = baos.toByteArray();
                String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                try {
                    jso.put("fk_parte", arraylistImagenes.get(i).getFk_parte());
                    jso.put("nombre", arraylistImagenes.get(i).getNombre_imagen());
                    jso.put("base64", encodedImage);
                    jso.put("firma", "0");
                    jsa.put(jso);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsa;
    }

    private Bitmap ShrinkBitmap(String file, int width, int height){
        BitmapFactory.Options bmpFactoryOptions = new BitmapFactory.Options();
        bmpFactoryOptions.inJustDecodeBounds = true;
        Bitmap bitmap = null;
        int heightRatio = (int)Math.ceil(bmpFactoryOptions.outHeight/(float)height);
        int widthRatio = (int)Math.ceil(bmpFactoryOptions.outWidth/(float)width);
        if (heightRatio > 1 || widthRatio > 1)
        {
            if (heightRatio > widthRatio)
            {
                bmpFactoryOptions.inSampleSize = heightRatio;
            } else {
                bmpFactoryOptions.inSampleSize = widthRatio;
            }
        }
        bmpFactoryOptions.inJustDecodeBounds = false;

        try {
            bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);

        } catch (OutOfMemoryError e) {
            try {
                bmpFactoryOptions.inSampleSize = 2;
                bitmap = BitmapFactory.decodeFile(file, bmpFactoryOptions);

            } catch(Exception eExc) {
                eExc.printStackTrace();
            }
              e.printStackTrace();
        }
        return bitmap;
    }

}
