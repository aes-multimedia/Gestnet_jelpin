package com.multimedia.aes.gestnet_ssl.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;
import com.multimedia.aes.gestnet_ssl.nucleo.Login;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class GuardarConfiguracion extends AsyncTask<Void,Void,Void>{
    private static String json;
    private static Context context;
    private static boolean bien=false;
    private ProgressDialog dialog;

    public GuardarConfiguracion(Context context, String json) {
        this.context = context;
        this.json = json;

    }
    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Configuracion.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonConfiguracion();
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
            new GuardarDatosAdicionales(context,json).execute();
        }else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar la configuracion");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar la configuracion");
            }
        }
    }

    private static void guardarJsonConfiguracion() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        jsonObject = jsonObject.getJSONObject("configuracion");
        int id_configuracion;
        if (jsonObject.getString("id_configuracion").equals("null") || jsonObject.getString("id_configuracion").equals("")) {
            id_configuracion = -1;
        } else {
            id_configuracion = jsonObject.getInt("id_configuracion");
        }
        boolean horarios;
        if (jsonObject.getString("horarios").equals("null")) {
            horarios = false;
        } else {
            if (jsonObject.getString("horarios").equals("0")) {
                horarios = false;
            } else {
                horarios = true;
            }
        }
        boolean operarios;
        if (jsonObject.getString("operarios").equals("null")) {
            operarios = false;
        } else {
            if (jsonObject.getString("operarios").equals("0")) {
                operarios = false;
            } else {
                operarios = true;
            }
        }
        boolean definiciones;
        if (jsonObject.getString("definiciones").equals("null")) {
            definiciones = false;
        } else {
            if (jsonObject.getString("definiciones").equals("0")) {
                definiciones = false;
            } else {
                definiciones = true;
            }
        }
        boolean equipos;
        if (jsonObject.getString("equipos").equals("null")) {
            equipos = false;
        } else {
            if (jsonObject.getString("equipos").equals("0")) {
                equipos = false;
            } else {
                equipos = true;
            }
        }
        boolean empresas;
        if (jsonObject.getString("empresas").equals("null")) {
            empresas = false;
        } else {
            if (jsonObject.getString("empresas").equals("0")) {
                empresas = false;
            } else {
                empresas = true;
            }
        }
        boolean marcas;
        if (jsonObject.getString("marcas").equals("null")) {
            marcas = false;
        } else {
            if (jsonObject.getString("marcas").equals("0")) {
                marcas = false;
            } else {
                marcas = true;
            }
        }
        boolean tipos_trabajo;
        if (jsonObject.getString("tipos_trabajo").equals("null")) {
            tipos_trabajo = false;
        } else {
            if (jsonObject.getString("tipos_trabajo").equals("0")) {
                tipos_trabajo = false;
            } else {
                tipos_trabajo = true;
            }
        }
        boolean tipos_presupuesto;
        if (jsonObject.getString("tipos_presupuesto").equals("null")) {
            tipos_presupuesto = false;
        } else {
            if (jsonObject.getString("tipos_presupuesto").equals("0")) {
                tipos_presupuesto = false;
            } else {
                tipos_presupuesto = true;
            }
        }
        boolean cuenta_bancaria;
        if (jsonObject.getString("cuenta_bancaria").equals("null")) {
            cuenta_bancaria = false;
        } else {
            if (jsonObject.getString("cuenta_bancaria").equals("0")) {
                cuenta_bancaria = false;
            } else {
                cuenta_bancaria = true;
            }
        }
        boolean fk_combustion;
        if (jsonObject.getString("fk_combustion").equals("null")) {
            fk_combustion = false;
        } else {
            if (jsonObject.getString("fk_combustion").equals("0")) {
                fk_combustion = false;
            } else {
                fk_combustion = true;
            }
        }
        boolean garantia;
        if (jsonObject.getString("garantia").equals("null")) {
            garantia = false;
        } else {
            if (jsonObject.getString("garantia").equals("0")) {
                garantia = false;
            } else {
                garantia = true;
            }
        }
        boolean pedir;
        if (jsonObject.getString("pedir").equals("null")) {
            pedir = false;
        } else {
            if (jsonObject.getString("pedir").equals("0")) {
                pedir = false;
            } else {
                pedir = true;
            }
        }
        boolean usar;
        if (jsonObject.getString("usar").equals("null")) {
            usar = false;
        } else {
            if (jsonObject.getString("usar").equals("0")) {
                usar = false;
            } else {
                usar = true;
            }
        }
        boolean presupuestar;
        if (jsonObject.getString("presupuestar").equals("null")) {
            presupuestar = false;
        } else {
            if (jsonObject.getString("presupuestar").equals("0")) {
                presupuestar = false;
            } else {
                presupuestar = true;
            }
        }
        boolean operacion_finalizacion;
        if (jsonObject.getString("operacion_finalizacion").equals("null")) {
            operacion_finalizacion = false;
        } else {
            if (jsonObject.getString("operacion_finalizacion").equals("0")) {
                operacion_finalizacion = false;
            } else {
                operacion_finalizacion = true;
            }
        }
        boolean precios_mano_obra;
        if (jsonObject.getString("precios_mano_obra").equals("null")) {
            precios_mano_obra = false;
        } else {
            if (jsonObject.getString("precios_mano_obra").equals("0")) {
                precios_mano_obra = false;
            } else {
                precios_mano_obra = true;
            }
        }
        boolean formaPago;
        if (jsonObject.getString("formaPago").equals("null")) {
            formaPago = false;
        } else {
            if (jsonObject.getString("formaPago").equals("0")) {
                formaPago = false;
            } else {
                formaPago = true;
            }
        }
        boolean disp_servicio;
        if (jsonObject.getString("disp_servicio").equals("null")) {
            disp_servicio = false;
        } else {
            if (jsonObject.getString("disp_servicio").equals("0")) {
                disp_servicio = false;
            } else {
                disp_servicio = true;
            }
        }
        boolean analisis_combustion;
        if (jsonObject.getString("analisis_combustion").equals("null")) {
            analisis_combustion = false;
        } else {
            if (jsonObject.getString("analisis_combustion").equals("0")) {
                analisis_combustion = false;
            } else {
                analisis_combustion = true;
            }
        }
        boolean puesta_marcha;
        if (jsonObject.getString("puesta_marcha").equals("null")) {
            puesta_marcha = false;
        } else {
            if (jsonObject.getString("puesta_marcha").equals("0")) {
                puesta_marcha = false;
            } else {
                puesta_marcha = true;
            }
        }
        boolean servicio_urgencia;
        if (jsonObject.getString("servicio_urgencia").equals("null")) {
            servicio_urgencia = false;
        } else {
            if (jsonObject.getString("servicio_urgencia").equals("0")) {
                servicio_urgencia = false;
            } else {
                servicio_urgencia = true;
            }
        }
        boolean kms_finalizacion;
        if (jsonObject.getString("kms_finalizacion").equals("null")) {
            kms_finalizacion = false;
        } else {
            if (jsonObject.getString("kms_finalizacion").equals("0")) {
                kms_finalizacion = false;
            } else {
                kms_finalizacion = true;
            }
        }
        boolean traspaso_material;
        if (jsonObject.getString("traspaso_material").equals("null")) {
            traspaso_material = false;
        } else {
            if (jsonObject.getString("traspaso_material").equals("0")) {
                traspaso_material = false;
            } else {
                traspaso_material = true;
            }
        }
        boolean parte_usuario;
        if (jsonObject.getString("parte_usuario").equals("null")) {
            parte_usuario = false;
        } else {
            if (jsonObject.getString("parte_usuario").equals("0")) {
                parte_usuario = false;
            } else {
                parte_usuario = true;
            }
        }
        boolean parte_averia;
        if (jsonObject.getString("parte_averia").equals("null")) {
            parte_averia = false;
        } else {
            if (jsonObject.getString("parte_averia").equals("0")) {
                parte_averia = false;
            } else {
                parte_averia = true;
            }
        }
        boolean parte_instalacion;
        if (jsonObject.getString("parte_instalacion").equals("null")) {
            parte_instalacion = false;
        } else {
            if (jsonObject.getString("parte_instalacion").equals("0")) {
                parte_instalacion = false;
            } else {
                parte_instalacion = true;
            }
        }
        boolean parte_materiales;
        if (jsonObject.getString("parte_materiales").equals("null")) {
            parte_materiales = false;
        } else {
            if (jsonObject.getString("parte_materiales").equals("0")) {
                parte_materiales = false;
            } else {
                parte_materiales = true;
            }
        }
        boolean parte_finalizacion;
        if (jsonObject.getString("parte_finalizacion").equals("null")) {
            parte_finalizacion = false;
        } else {
            if (jsonObject.getString("parte_finalizacion").equals("0")) {
                parte_finalizacion = false;
            } else {
                parte_finalizacion = true;
            }
        }
        boolean parte_galeria;
        if (jsonObject.getString("parte_galeria").equals("null")) {
            parte_galeria = false;
        } else {
            if (jsonObject.getString("parte_galeria").equals("0")) {
                parte_galeria = false;
            } else {
                parte_galeria = true;
            }
        }
        boolean menu_asignacion;
        if (jsonObject.getString("menu_asignacion").equals("null")) {
            menu_asignacion = false;
        } else {
            if (jsonObject.getString("menu_asignacion").equals("0")) {
                menu_asignacion = false;
            } else {
                menu_asignacion = true;
            }
        }
        boolean menu_documentos;
        if (jsonObject.getString("menu_documentos").equals("null")) {
            menu_documentos = false;
        } else {
            if (jsonObject.getString("menu_documentos").equals("0")) {
                menu_documentos = false;
            } else {
                menu_documentos = true;
            }
        }
        boolean menu_almacen;
        if (jsonObject.getString("menu_almacen").equals("null")) {
            menu_almacen = false;
        } else {
            if (jsonObject.getString("menu_almacen").equals("0")) {
                menu_almacen = false;
            } else {
                menu_almacen = true;
            }
        }
        boolean menu_cierre;
        if (jsonObject.getString("menu_cierre").equals("null")) {
            menu_cierre = false;
        } else {
            if (jsonObject.getString("menu_cierre").equals("0")) {
                menu_cierre = false;
            } else {
                menu_cierre = true;
            }
        }
        boolean menu_ubicacion;
        if (jsonObject.getString("menu_ubicacion").equals("null")) {
            menu_ubicacion = false;
        } else {
            if (jsonObject.getString("menu_ubicacion").equals("0")) {
                menu_ubicacion = false;
            } else {
                menu_ubicacion = true;
            }
        }
        boolean menu_datos_completos;
        if (jsonObject.getString("menu_datos_completos").equals("null")) {
            menu_datos_completos = false;
        } else {
            if (jsonObject.getString("menu_datos_completos").equals("0")) {
                menu_datos_completos = false;
            } else {
                menu_datos_completos = true;
            }
        }
        boolean menu_informar;
        if (jsonObject.getString("menu_informar").equals("null")) {
            menu_informar = false;
        } else {
            if (jsonObject.getString("menu_informar").equals("0")) {
                menu_informar = false;
            } else {
                menu_informar = true;
            }
        }
        boolean menu_datos_actualizados;
        if (jsonObject.getString("menu_datos_actualizados").equals("null")) {
            menu_datos_actualizados = false;
        } else {
            if (jsonObject.getString("menu_datos_actualizados").equals("0")) {
                menu_datos_actualizados = false;
            } else {
                menu_datos_actualizados = true;
            }
        }
        boolean menu_presupuesto;
        if (jsonObject.getString("menu_presupuesto").equals("null")) {
            menu_presupuesto = false;
        } else {
            if (jsonObject.getString("menu_presupuesto").equals("0")) {
                menu_presupuesto = false;
            } else {
                menu_presupuesto = true;
            }
        }
        boolean requiere_firma;
        if (jsonObject.getString("requiere_firma").equals("null")) {
            requiere_firma = false;
        } else {
            if (jsonObject.getString("requiere_firma").equals("0")) {
                requiere_firma = false;
            } else {
                requiere_firma = true;
            }
        }
        boolean usuario_conf;
        if (jsonObject.getString("usuario_conf").equals("null")) {
            usuario_conf = false;
        } else {
            if (jsonObject.getString("usuario_conf").equals("0")) {
                usuario_conf = false;
            } else {
                usuario_conf = true;
            }
        }
        boolean pass_conf;
        if (jsonObject.getString("pass_conf").equals("null")) {
            pass_conf = false;
        } else {
            if (jsonObject.getString("pass_conf").equals("0")) {
                pass_conf = false;
            } else {
                pass_conf = true;
            }
        }
        boolean intersat;
        if (jsonObject.getString("intersat").equals("null")) {
            intersat = false;
        } else {
            if (jsonObject.getString("intersat").equals("0")) {
                intersat = false;
            } else {
                intersat = true;
            }
        }
        boolean gas_natural;
        if (jsonObject.getString("gas_natural").equals("null")) {
            gas_natural = false;
        } else {
            if (jsonObject.getString("gas_natural").equals("0")) {
                gas_natural = false;
            } else {
                gas_natural = true;
            }
        }
        boolean jlsat;
        if (jsonObject.getString("jlsat").equals("null")) {
            jlsat = false;
        } else {
            if (jsonObject.getString("jlsat").equals("0")) {
                jlsat = false;
            } else {
                jlsat = true;
            }
        }
        boolean duracion_automatica;
        if (jsonObject.getString("duracion_automatica").equals("null")) {
            duracion_automatica = false;
        } else {
            if (jsonObject.getString("duracion_automatica").equals("0")) {
                duracion_automatica = false;
            } else {
                duracion_automatica = true;
            }
        }
        boolean contador_km;
        if (jsonObject.getString("contador_km").equals("null")) {
            contador_km = false;
        } else {
            if (jsonObject.getString("contador_km").equals("0")) {
                contador_km = false;
            } else {
                contador_km = true;
            }
        }

        boolean bSubidaInmediataImagen;
        if (!jsonObject.has("bSubidaInmediataImagen")) {
            bSubidaInmediataImagen = false;
        } else {
            if (jsonObject.getString("bSubidaInmediataImagen").equals("0") || jsonObject.getString("bSubidaInmediataImagen").equals("null")) {
                bSubidaInmediataImagen = false;
            } else {
                bSubidaInmediataImagen = true;
            }
        }

        boolean bFotoInforme;
        if (!jsonObject.has("bFotoInforme")) {
            bFotoInforme = false;
        } else {
            if (jsonObject.getString("bFotoInforme").equals("0")) {
                bFotoInforme = false;
            } else {
                bFotoInforme = true;
            }
        }

        if (ConfiguracionDAO.newConfiguracion(context,id_configuracion,   horarios,   operarios,   definiciones,
                equipos,   empresas,   marcas,   tipos_trabajo,
                tipos_presupuesto,   cuenta_bancaria,   fk_combustion,
                garantia,   pedir,   usar,   presupuestar,
                operacion_finalizacion,   precios_mano_obra,   formaPago,
                disp_servicio,   analisis_combustion,   puesta_marcha,
                servicio_urgencia,   kms_finalizacion,   traspaso_material,
                parte_usuario,   parte_averia,   parte_instalacion,
                parte_materiales,   parte_finalizacion,   parte_galeria,
                menu_asignacion,   menu_documentos,   menu_almacen,
                menu_cierre,   menu_ubicacion,   menu_datos_completos,
                menu_informar,   menu_datos_actualizados,   menu_presupuesto,
                requiere_firma,   usuario_conf,   pass_conf,   intersat,
                gas_natural,   jlsat,   duracion_automatica,   contador_km,
                bSubidaInmediataImagen, bFotoInforme)){
            bien = true;
        }
    }
}
