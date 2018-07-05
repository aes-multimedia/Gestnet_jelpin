package com.multimedia.aes.gestnet_nucleo.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class GuardarDatosAdicionales extends AsyncTask<Void,Void,Void>{


    private static String json;
    private static Context context;
    private static boolean bien = false;
    private static ArrayList<DatosAdicionales> datos = new ArrayList<>();
    private ProgressDialog dialog;

    public GuardarDatosAdicionales(Context context, String json) {
        this.context = context;
        this.json = json;
    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Datos Adicionales.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonParte();
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
                try {
                    new GuardarFormasPago(context,json).execute();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else if (context.getClass()==Index.class) {
                ((Index) context).datosActualizados();
            }
        }else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar datos adicionales");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar datos adicionales");
            }
        }
    }

    private static void guardarJsonParte() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("partes");
        if (jsonArray.length()!=0){
            JSONObject jsonObject2;
            for(int i =0 ;i<jsonArray.length();i++){

                jsonObject2= jsonArray.getJSONObject(i).getJSONObject("datosAdicionales");
                int id_rel;
                if (jsonObject2.getString("id_rel").equals("null") || jsonObject2.getString("id_rel").equals("")) {
                    id_rel = -1;
                } else {
                    id_rel = jsonObject2.getInt("id_rel");
                }
                if (DatosAdicionalesDAO.buscarTodosLosDatosAdicionales(context) != null) {
                    datos.addAll(DatosAdicionalesDAO.buscarTodosLosDatosAdicionales(context));
                }
                boolean esta = false;
                if (datos != null) {
                    for (int k = 0; k < datos.size(); k++) {
                        if (datos.get(k).getId_rel() == id_rel) {
                            esta = true;
                            break;
                        } else {
                            esta = false;
                        }
                    }
                }
                int fk_parte;
                if (jsonObject2.getString("fk_parte").equals("null") || jsonObject2.getString("fk_parte").equals("")) {
                    fk_parte = -1;
                } else {
                    fk_parte = jsonObject2.getInt("fk_parte");
                }
                int fk_forma_pago;
                if (jsonObject2.getString("fk_forma_pago").equals("null") || jsonObject2.getString("fk_forma_pago").equals("")) {
                    fk_forma_pago = -1;
                } else {
                    fk_forma_pago = jsonObject2.getInt("fk_forma_pago");
                }
                String sintomas_averia;
                if (jsonObject2.getString("sintomas_averia").equals("null")) {
                    sintomas_averia = "";
                } else {
                    sintomas_averia = jsonObject2.getString("sintomas_averia");
                }
                String operacion_efectuada;
                if (jsonObject2.getString("operacion_efectuada").equals("null")) {
                    operacion_efectuada = "";
                } else {
                    operacion_efectuada = jsonObject2.getString("operacion_efectuada");
                }
                String observaciones;
                if (jsonObject2.getString("observaciones").equals("null")) {
                    observaciones = "";
                } else {
                    observaciones = jsonObject2.getString("observaciones");
                }
                boolean preeu_disposicion_servicio_si_no;
                if (jsonObject2.getString("preeu_disposicion_servicio_si_no").equals("null")) {
                    preeu_disposicion_servicio_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_disposicion_servicio_si_no").equals("0")) {
                        preeu_disposicion_servicio_si_no = false;
                    } else {
                        preeu_disposicion_servicio_si_no = true;
                    }
                }
                double preeu_disposicion_servicio;
                if (jsonObject2.getString("preeu_disposicion_servicio").equals("null") || jsonObject2.getString("preeu_disposicion_servicio").equals("")) {
                    preeu_disposicion_servicio = 0;
                } else {
                    preeu_disposicion_servicio = jsonObject2.getDouble("preeu_disposicion_servicio");
                }
                boolean preeu_mano_de_obra_si_no;
                if (jsonObject2.getString("preeu_mano_de_obra_si_no").equals("null")) {
                    preeu_mano_de_obra_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_mano_de_obra_si_no").equals("0")) {
                        preeu_mano_de_obra_si_no = false;
                    } else {
                        preeu_mano_de_obra_si_no = true;
                    }
                }
                double preeu_mano_de_obra_precio;
                if (jsonObject2.getString("preeu_mano_de_obra_precio").equals("null") || jsonObject2.getString("preeu_mano_de_obra_precio").equals("")) {
                    preeu_mano_de_obra_precio = 0;
                } else {
                    preeu_mano_de_obra_precio = jsonObject2.getDouble("preeu_mano_de_obra_precio");
                }
                double preeu_mano_de_obra;
                if (jsonObject2.getString("preeu_mano_de_obra").equals("null") || jsonObject2.getString("preeu_mano_de_obra").equals("")) {
                    preeu_mano_de_obra = 0;
                } else {
                    preeu_mano_de_obra = jsonObject2.getDouble("preeu_mano_de_obra");
                }
                boolean preeu_materiales_si_no;
                if (jsonObject2.getString("preeu_materiales_si_no").equals("null")) {
                    preeu_materiales_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_materiales_si_no").equals("0")) {
                        preeu_materiales_si_no = false;
                    } else {
                        preeu_materiales_si_no = true;
                    }
                }
                double preeu_materiales;
                if (jsonObject2.getString("preeu_materiales").equals("null") || jsonObject2.getString("preeu_materiales").equals("")) {
                    preeu_materiales = 0;
                } else {
                    preeu_materiales = jsonObject2.getDouble("preeu_materiales");
                }
                boolean preeu_puesta_marcha_si_no;
                if (jsonObject2.getString("preeu_puesta_marcha_si_no").equals("null")) {
                    preeu_puesta_marcha_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_puesta_marcha_si_no").equals("0")) {
                        preeu_puesta_marcha_si_no = false;
                    } else {
                        preeu_puesta_marcha_si_no = true;
                    }
                }
                double preeu_puesta_marcha;
                if (jsonObject2.getString("preeu_puesta_marcha").equals("null") || jsonObject2.getString("preeu_puesta_marcha").equals("")) {
                    preeu_puesta_marcha = 0;
                } else {
                    preeu_puesta_marcha = jsonObject2.getDouble("preeu_puesta_marcha");
                }
                boolean preeu_servicio_urgencia_si_no;
                if (jsonObject2.getString("preeu_servicio_urgencia_si_no").equals("null")) {
                    preeu_servicio_urgencia_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_servicio_urgencia_si_no").equals("0")) {
                        preeu_servicio_urgencia_si_no = false;
                    } else {
                        preeu_servicio_urgencia_si_no = true;
                    }
                }
                double preeu_servicio_urgencia;
                if (jsonObject2.getString("preeu_servicio_urgencia").equals("null") || jsonObject2.getString("preeu_servicio_urgencia").equals("")) {
                    preeu_servicio_urgencia = 0;
                } else {
                    preeu_servicio_urgencia = jsonObject2.getDouble("preeu_servicio_urgencia");
                }
                boolean preeu_km_si_no;
                if (jsonObject2.getString("preeu_km_si_no").equals("null")) {
                    preeu_km_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_km_si_no").equals("0")) {
                        preeu_km_si_no = false;
                    } else {
                        preeu_km_si_no = true;
                    }
                }
                double preeu_km_precio;
                if (jsonObject2.getString("preeu_km_precio").equals("null") || jsonObject2.getString("preeu_km_precio").equals("")) {
                    preeu_km_precio = 0;
                } else {
                    preeu_km_precio = jsonObject2.getDouble("preeu_km_precio");
                }
                double preeu_km;
                if (jsonObject2.getString("preeu_km").equals("null") || jsonObject2.getString("preeu_km").equals("")) {
                    preeu_km = 0;
                } else {
                    preeu_km = jsonObject2.getDouble("preeu_km");
                }
                double preeu_km_precio_total;
                if (jsonObject2.getString("preeu_km_precio_total").equals("null") || jsonObject2.getString("preeu_km_precio_total").equals("")) {
                    preeu_km_precio_total = 0;
                } else {
                    preeu_km_precio_total = jsonObject2.getDouble("preeu_km_precio_total");
                }
                double preeu_analisis_combustion;
                if (jsonObject2.getString("preeu_analisis_combustion").equals("null") || jsonObject2.getString("preeu_analisis_combustion").equals("")) {
                    preeu_analisis_combustion = 0;
                } else {
                    preeu_analisis_combustion = jsonObject2.getDouble("preeu_analisis_combustion");
                }
                double preeu_adicional;
                if (jsonObject2.getString("preeu_adicional").equals("null") || jsonObject2.getString("preeu_adicional").equals("")) {
                    preeu_adicional = 0;
                } else {
                    preeu_adicional = jsonObject2.getDouble("preeu_adicional");
                }
                double preeu_adicional_coste;
                if (jsonObject2.getString("preeu_adicional_coste").equals("null") || jsonObject2.getString("preeu_adicional_coste").equals("")) {
                    preeu_adicional_coste = 0;
                } else {
                    preeu_adicional_coste = jsonObject2.getDouble("preeu_adicional_coste");
                }
                boolean preeu_otros_si_no;
                if (jsonObject2.getString("preeu_otros_si_no").equals("null")) {
                    preeu_otros_si_no = false;
                } else {
                    if (jsonObject2.getString("preeu_otros_si_no").equals("0")) {
                        preeu_otros_si_no = false;
                    } else {
                        preeu_otros_si_no = true;
                    }
                }
                String preeu_otros_nombre;
                if (jsonObject2.getString("preeu_otros_nombre").equals("null")) {
                    preeu_otros_nombre = "";
                } else {
                    preeu_otros_nombre = jsonObject2.getString("preeu_otros_nombre");
                }
                String preeu_adicional_coste_nombre;
                if (jsonObject2.getString("preeu_adicional_coste_nombre").equals("null")) {
                    preeu_adicional_coste_nombre = "";
                } else {
                    preeu_adicional_coste_nombre = jsonObject2.getString("preeu_adicional_coste_nombre");
                }
                double preeu_iva_aplicado;
                if (jsonObject2.getString("preeu_iva_aplicado").equals("null") || jsonObject2.getString("preeu_iva_aplicado").equals("")) {
                    preeu_iva_aplicado = 0;
                } else {
                    preeu_iva_aplicado = jsonObject2.getDouble("preeu_iva_aplicado");
                }
                double total_ppto;
                if (jsonObject2.getString("total_ppto").equals("null") || jsonObject2.getString("total_ppto").equals("")) {
                    total_ppto = 0;
                } else {
                    total_ppto = jsonObject2.getDouble("total_ppto");
                }
                boolean bAceptaPresupuesto;
                if (jsonObject2.getString("bAceptaPresupuesto").equals("null")) {
                    bAceptaPresupuesto = false;
                } else {
                    if (jsonObject2.getString("bAceptaPresupuesto").equals("0")) {
                        bAceptaPresupuesto = false;
                    } else {
                        bAceptaPresupuesto = true;
                    }
                }
                String ctrlgas_rencal_porco2;
                if (jsonObject2.getString("ctrlgas_rencal_porco2").equals("null")) {
                    ctrlgas_rencal_porco2 = "";
                } else {
                    ctrlgas_rencal_porco2 = jsonObject2.getString("ctrlgas_rencal_porco2");
                }
                String ctrlgas_rencal_poro2;
                if (jsonObject2.getString("ctrlgas_rencal_poro2").equals("null")) {
                    ctrlgas_rencal_poro2 = "";
                } else {
                    ctrlgas_rencal_poro2 = jsonObject2.getString("ctrlgas_rencal_poro2");
                }
                String ctrlgas_rencal_ppmcocorreg;
                if (jsonObject2.getString("ctrlgas_rencal_ppmcocorreg").equals("null")) {
                    ctrlgas_rencal_ppmcocorreg = "";
                } else {
                    ctrlgas_rencal_ppmcocorreg = jsonObject2.getString("ctrlgas_rencal_ppmcocorreg");
                }
                String ctrlgas_rencal_thumosgrados;
                if (jsonObject2.getString("ctrlgas_rencal_thumosgrados").equals("null")) {
                    ctrlgas_rencal_thumosgrados = "";
                } else {
                    ctrlgas_rencal_thumosgrados = jsonObject2.getString("ctrlgas_rencal_thumosgrados");
                }
                String ctrlgas_rencal_tambientegrados;
                if (jsonObject2.getString("ctrlgas_rencal_tambientegrados").equals("null")) {
                    ctrlgas_rencal_tambientegrados = "";
                } else {
                    ctrlgas_rencal_tambientegrados = jsonObject2.getString("ctrlgas_rencal_tambientegrados");
                }
                String ctrlgas_rencal_porexcaire;
                if (jsonObject2.getString("ctrlgas_rencal_porexcaire").equals("null")) {
                    ctrlgas_rencal_porexcaire = "";
                } else {
                    ctrlgas_rencal_porexcaire = jsonObject2.getString("ctrlgas_rencal_porexcaire");
                }
                String ctrlgas_rencal_porrendimiento;
                if (jsonObject2.getString("ctrlgas_rencal_porrendimiento").equals("null")) {
                    ctrlgas_rencal_porrendimiento = "";
                } else {
                    ctrlgas_rencal_porrendimiento = jsonObject2.getString("ctrlgas_rencal_porrendimiento");
                }
                String regque_inyector;
                if (jsonObject2.getString("regque_inyector").equals("null")) {
                    regque_inyector = "";
                } else {
                    regque_inyector = jsonObject2.getString("regque_inyector");
                }
                String regque_aireprimario;
                if (jsonObject2.getString("regque_aireprimario").equals("null")) {
                    regque_aireprimario = "";
                } else {
                    regque_aireprimario = jsonObject2.getString("regque_aireprimario");
                }
                String regque_presionbomba;
                if (jsonObject2.getString("regque_presionbomba").equals("null")) {
                    regque_presionbomba = "";
                } else {
                    regque_presionbomba = jsonObject2.getString("regque_presionbomba");
                }
                String regque_aire_linea;
                if (jsonObject2.getString("regque_aire_linea").equals("null")) {
                    regque_aire_linea = "";
                } else {
                    regque_aire_linea = jsonObject2.getString("regque_aire_linea");
                }
                boolean bControlarAireVentilacion;
                if (jsonObject2.getString("bControlarAireVentilacion").equals("null")) {
                    bControlarAireVentilacion = false;
                } else {
                    if (jsonObject2.getString("bControlarAireVentilacion").equals("0")) {
                        bControlarAireVentilacion = false;
                    } else {
                        bControlarAireVentilacion = true;
                    }
                }
                double fact_materiales;
                if (jsonObject2.getString("fact_materiales").equals("null") || jsonObject2.getString("fact_materiales").equals("")) {
                    fact_materiales = 0;
                } else {
                    fact_materiales = jsonObject2.getDouble("fact_materiales");
                }
                double fact_disposicion_servicio;
                if (jsonObject2.getString("fact_disposicion_servicio").equals("null") || jsonObject2.getString("fact_disposicion_servicio").equals("")) {
                    fact_disposicion_servicio = 0;
                } else {
                    fact_disposicion_servicio = jsonObject2.getDouble("fact_disposicion_servicio");
                }
                double fact_manodeobra_precio;
                if (jsonObject2.getString("fact_manodeobra_precio").equals("null") || jsonObject2.getString("fact_manodeobra_precio").equals("")) {
                    fact_manodeobra_precio = 0;
                } else {
                    fact_manodeobra_precio = jsonObject2.getDouble("fact_manodeobra_precio");
                }
                double fact_manodeobra;
                if (jsonObject2.getString("fact_manodeobra").equals("null") || jsonObject2.getString("fact_manodeobra").equals("")) {
                    fact_manodeobra = 0;
                } else {
                    fact_manodeobra = jsonObject2.getDouble("fact_manodeobra");
                }
                double fact_puesta_en_marcha;
                if (jsonObject2.getString("fact_puesta_en_marcha").equals("null") || jsonObject2.getString("fact_puesta_en_marcha").equals("")) {
                    fact_puesta_en_marcha = 0;
                } else {
                    fact_puesta_en_marcha = jsonObject2.getDouble("fact_puesta_en_marcha");
                }
                double fact_analisis_combustion;
                if (jsonObject2.getString("fact_analisis_combustion").equals("null") || jsonObject2.getString("fact_analisis_combustion").equals("")) {
                    fact_analisis_combustion = 0;
                } else {
                    fact_analisis_combustion = jsonObject2.getDouble("fact_analisis_combustion");
                }
                double fact_servicio_urgencia;
                if (jsonObject2.getString("fact_servicio_urgencia").equals("null") || jsonObject2.getString("fact_servicio_urgencia").equals("")) {
                    fact_servicio_urgencia = 0;
                } else {
                    fact_servicio_urgencia = jsonObject2.getDouble("fact_servicio_urgencia");
                }
                double fact_km;
                if (jsonObject2.getString("fact_km").equals("null") || jsonObject2.getString("fact_km").equals("")) {
                    fact_km = 0;
                } else {
                    fact_km = jsonObject2.getDouble("fact_km");
                }
                double fact_km_precio;
                if (jsonObject2.getString("fact_km_precio").equals("null") || jsonObject2.getString("fact_km_precio").equals("")) {
                    fact_km_precio = 0;
                } else {
                    fact_km_precio = jsonObject2.getDouble("fact_km_precio");
                }
                double fact_km_precio_total;
                if (jsonObject2.getString("fact_km_precio_total").equals("null") || jsonObject2.getString("fact_km_precio_total").equals("")) {
                    fact_km_precio_total = 0;
                } else {
                    fact_km_precio_total = jsonObject2.getDouble("fact_km_precio_total");
                }
                double fact_adicional;
                if (jsonObject2.getString("fact_adicional").equals("null") || jsonObject2.getString("fact_adicional").equals("")) {
                    fact_adicional = 0;
                } else {
                    fact_adicional = jsonObject2.getDouble("fact_adicional");
                }
                double fact_adicional_coste;
                if (jsonObject2.getString("fact_adicional_coste").equals("null") || jsonObject2.getString("fact_adicional_coste").equals("")) {
                    fact_adicional_coste = 0;
                } else {
                    fact_adicional_coste = jsonObject2.getDouble("fact_adicional_coste");
                }
                String fact_otros_nombre;
                if (jsonObject2.getString("fact_otros_nombre").equals("null")) {
                    fact_otros_nombre = "";
                } else {
                    fact_otros_nombre = jsonObject2.getString("fact_otros_nombre");
                }
                String fact_adicional_coste_nombre;
                if (jsonObject2.getString("fact_adicional_coste_nombre").equals("null")) {
                    fact_adicional_coste_nombre = "";
                } else {
                    fact_adicional_coste_nombre = jsonObject2.getString("fact_adicional_coste_nombre");
                }
                double fact_por_iva_aplicado;
                if (jsonObject2.getString("fact_por_iva_aplicado").equals("null") || jsonObject2.getString("fact_por_iva_aplicado").equals("")) {
                    fact_por_iva_aplicado = 0;
                } else {
                    fact_por_iva_aplicado = jsonObject2.getDouble("fact_por_iva_aplicado");
                }
                double fact_total_con_iva;
                if (jsonObject2.getString("fact_total_con_iva").equals("null") || jsonObject2.getString("fact_total_con_iva").equals("")) {
                    fact_total_con_iva = 0;
                } else {
                    fact_total_con_iva = jsonObject2.getDouble("fact_total_con_iva");
                }
                double retencion;
                if (jsonObject2.getString("retencion").equals("null") || jsonObject2.getString("retencion").equals("")) {
                    retencion = 0;
                } else {
                    retencion = jsonObject2.getDouble("retencion");
                }
                double retencion_porc;
                if (jsonObject2.getString("retencion_porc").equals("null") || jsonObject2.getString("retencion_porc").equals("")) {
                    retencion_porc = 0;
                } else {
                    retencion_porc = jsonObject2.getDouble("retencion_porc");
                }
                String matem_hora_entrada;
                if (jsonObject2.getString("matem_hora_entrada").equals("null")) {
                    matem_hora_entrada = "";
                } else {
                    matem_hora_entrada = jsonObject2.getString("matem_hora_entrada");
                }
                String matem_hora_salida;
                if (jsonObject2.getString("matem_hora_salida").equals("null")) {
                    matem_hora_salida = "";
                } else {
                    matem_hora_salida = jsonObject2.getString("matem_hora_salida");
                }
                String observacionesPago;
                if (jsonObject2.getString("observacionesPago").equals("null")) {
                    observacionesPago = "";
                } else {
                    observacionesPago = jsonObject2.getString("observacionesPago");
                }
                boolean cobrar_analisis_combustion;
                if (jsonObject2.getString("cobrar_analisis_combustion").equals("null")) {
                    cobrar_analisis_combustion = false;
                } else {
                    if (jsonObject2.getString("cobrar_analisis_combustion").equals("0")) {
                        cobrar_analisis_combustion = false;
                    } else {
                        cobrar_analisis_combustion = true;
                    }
                }
                if (!esta) {
                    if (DatosAdicionalesDAO.newDatosAdicionales(context,id_rel, fk_parte, fk_forma_pago, sintomas_averia, operacion_efectuada, observaciones, preeu_disposicion_servicio_si_no, preeu_disposicion_servicio,
                            preeu_mano_de_obra_si_no, preeu_mano_de_obra_precio, preeu_mano_de_obra,
                            preeu_materiales_si_no, preeu_materiales, preeu_puesta_marcha_si_no,
                            preeu_puesta_marcha, preeu_servicio_urgencia_si_no, preeu_servicio_urgencia,
                            preeu_km_si_no, preeu_km_precio, preeu_km, preeu_km_precio_total,
                            preeu_analisis_combustion, preeu_adicional, preeu_adicional_coste,
                            preeu_otros_si_no, preeu_otros_nombre, preeu_adicional_coste_nombre,
                            preeu_iva_aplicado, total_ppto, bAceptaPresupuesto, ctrlgas_rencal_porco2,
                            ctrlgas_rencal_poro2, ctrlgas_rencal_ppmcocorreg, ctrlgas_rencal_thumosgrados,
                            ctrlgas_rencal_tambientegrados, ctrlgas_rencal_porexcaire, ctrlgas_rencal_porrendimiento,
                            regque_inyector, regque_aireprimario, regque_presionbomba, regque_aire_linea,
                            bControlarAireVentilacion, fact_materiales, fact_disposicion_servicio,
                            fact_manodeobra_precio, fact_manodeobra, fact_puesta_en_marcha,
                            fact_analisis_combustion, fact_servicio_urgencia, fact_km, fact_km_precio,
                            fact_km_precio_total, fact_adicional, fact_adicional_coste, fact_otros_nombre,
                            fact_adicional_coste_nombre, fact_por_iva_aplicado, fact_total_con_iva, retencion,
                            retencion_porc, matem_hora_entrada, matem_hora_salida, observacionesPago, cobrar_analisis_combustion)) {
                        bien = true;
                    } else {
                        bien = false;
                    }
                }else{
                    DatosAdicionalesDAO.actualizarDatosAdicionales(context,id_rel, fk_parte, fk_forma_pago, sintomas_averia, operacion_efectuada, observaciones, preeu_disposicion_servicio_si_no, preeu_disposicion_servicio,
                            preeu_mano_de_obra_si_no, preeu_mano_de_obra_precio, preeu_mano_de_obra,
                            preeu_materiales_si_no, preeu_materiales, preeu_puesta_marcha_si_no,
                            preeu_puesta_marcha, preeu_servicio_urgencia_si_no, preeu_servicio_urgencia,
                            preeu_km_si_no, preeu_km_precio, preeu_km, preeu_km_precio_total,
                            preeu_analisis_combustion, preeu_adicional, preeu_adicional_coste,
                            preeu_otros_si_no, preeu_otros_nombre, preeu_adicional_coste_nombre,
                            preeu_iva_aplicado, total_ppto, bAceptaPresupuesto, ctrlgas_rencal_porco2,
                            ctrlgas_rencal_poro2, ctrlgas_rencal_ppmcocorreg, ctrlgas_rencal_thumosgrados,
                            ctrlgas_rencal_tambientegrados, ctrlgas_rencal_porexcaire, ctrlgas_rencal_porrendimiento,
                            regque_inyector, regque_aireprimario, regque_presionbomba, regque_aire_linea,
                            bControlarAireVentilacion, fact_materiales, fact_disposicion_servicio,
                            fact_manodeobra_precio, fact_manodeobra, fact_puesta_en_marcha,
                            fact_analisis_combustion, fact_servicio_urgencia, fact_km, fact_km_precio,
                            fact_km_precio_total, fact_adicional, fact_adicional_coste, fact_otros_nombre,
                            fact_adicional_coste_nombre, fact_por_iva_aplicado, fact_total_con_iva, retencion,
                            retencion_porc, matem_hora_entrada, matem_hora_salida, observacionesPago, cobrar_analisis_combustion);
                    bien = true;
                }

                datos.clear();

            }
        }else{
            bien = true;
        }
    }
}




