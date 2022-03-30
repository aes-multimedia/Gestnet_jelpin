package com.multimedia.aes.gestnet_ssl.BBDD;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;
import com.multimedia.aes.gestnet_ssl.nucleo.Login;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class GuardarMaquina extends AsyncTask<Void,Void,Void> {

    private static String json;
    private static Context context;
    private static boolean bien=true;
    private static ArrayList<Maquina> maquinas = new ArrayList<>();
    private static int contador=0;
    private ProgressDialog dialog;

    public GuardarMaquina(Context context, String json) {
        this.context = context;
        this.json = json;
    }

    @Override
    protected void onPreExecute() {
        dialog = new ProgressDialog(context);
        dialog.setTitle("Guardando Maquinas.");
        dialog.setMessage("Conectando con el servidor, porfavor espere..." + "\n" + "Esto puede tardar unos minutos si la cobertura es baja.");
        dialog.setCancelable(false);
        dialog.setIndeterminate(true);
        dialog.show();
        super.onPreExecute();
    }
    @Override
    protected Void doInBackground(Void... voids) {
        try {
            guardarJsonMaquina();
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        dialog.dismiss();
        if (bien){
            new GuardarProtocoloAccion(context,json).execute();
        }else{
            if (context.getClass()==Login.class){
                ((Login)context).sacarMensaje("error al guardar maquinas");
            }else if (context.getClass()==Index.class){
                ((Index)context).sacarMensaje("error al guardar maquinas");
            }
        }
    }
    private static void guardarJsonMaquina() throws JSONException, SQLException {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArray = jsonObject.getJSONArray("partes");

        Cliente c = ClienteDAO.buscarCliente(context);
        int idcli = c.getId_cliente();
        for (int i = 0; i < jsonArray.length(); i++) {
            int id_parte;
            if (jsonArray.getJSONObject(i).getString("id_parte").equals("null") || jsonArray.getJSONObject(i).getString("id_parte").equals("")) {
                id_parte = -1;
            } else {
                id_parte = jsonArray.getJSONObject(i).getInt("id_parte");
            }
            JSONArray jsonArray1 = jsonArray.getJSONObject(i).getJSONArray("maquina");
            for (int j = 0; j < jsonArray1.length(); j++){
                int fk_maquina;
                if (jsonArray1.getJSONObject(j).getString("id_maquina").equals("null") || jsonArray1.getJSONObject(j).getString("id_maquina").equals("")) {
                    fk_maquina = -1;
                } else {
                    fk_maquina = jsonArray1.getJSONObject(j).getInt("id_maquina");
                }
                if (MaquinaDAO.buscarTodasLasMaquinas(context)!=null){
                    maquinas.addAll(MaquinaDAO.buscarTodasLasMaquinas(context));
                }
                boolean esta = false;
                if (maquinas != null) {
                    for (int k = 0; k < maquinas.size(); k++) {
                        if (maquinas.get(k).getFk_maquina() == fk_maquina) {
                            contador ++;
                            esta = true;
                            break;
                        } else {
                            esta = false;
                        }
                    }
                }
                int fk_direccion;
                if (jsonArray1.getJSONObject(j).getString("fk_direccion").equals("null") || jsonArray1.getJSONObject(j).getString("fk_direccion").equals("")) {
                    fk_direccion = -1;
                } else {
                    fk_direccion = jsonArray1.getJSONObject(j).getInt("fk_direccion");
                }
                int fk_marca;
                if (jsonArray1.getJSONObject(j).getString("fk_marca").equals("null") || jsonArray1.getJSONObject(j).getString("fk_marca").equals("")) {
                    fk_marca = -1;
                } else {
                    fk_marca = jsonArray1.getJSONObject(j).getInt("fk_marca");
                }

                String fk_tipo_combustion = "";
                if(idcli == 42){
                    if (jsonArray1.getJSONObject(j).getString("tipo_combustion").equals("null") || jsonArray1.getJSONObject(j).getString("tipo_combustion").equals("")) {
                    fk_tipo_combustion = "";
                } else {
                    fk_tipo_combustion = jsonArray1.getJSONObject(j).getString("tipo_combustion");
                }

                if (!jsonArray1.getJSONObject(j).getString("marca").equals("null") && !jsonArray1.getJSONObject(j).getString("marca").equals("")) {
                    fk_tipo_combustion += " - "+jsonArray1.getJSONObject(j).getString("marca");
                }
                }

                int fk_protocolo;
                if (jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("null") || jsonArray1.getJSONObject(j).getString("fk_protocolo").equals("")) {
                    fk_protocolo = -1;
                } else {
                    fk_protocolo = jsonArray1.getJSONObject(j).getInt("fk_protocolo");
                }
                int fk_instalador;
                if (jsonArray1.getJSONObject(j).getString("fk_instalador").equals("null") || jsonArray1.getJSONObject(j).getString("fk_instalador").equals("")) {
                    fk_instalador = -1;
                } else {
                    fk_instalador = jsonArray1.getJSONObject(j).getInt("fk_instalador");
                }
                int fk_remoto_central;
                if (jsonArray1.getJSONObject(j).getString("fk_remoto_central").equals("null") || jsonArray1.getJSONObject(j).getString("fk_remoto_central").equals("")) {
                    fk_remoto_central = -1;
                } else {
                    fk_remoto_central = jsonArray1.getJSONObject(j).getInt("fk_remoto_central");
                }
                int fk_tipo;
                if (jsonArray1.getJSONObject(j).getString("fk_tipo").equals("null") || jsonArray1.getJSONObject(j).getString("fk_tipo").equals("")) {
                    fk_tipo = -1;
                } else {
                    fk_tipo = jsonArray1.getJSONObject(j).getInt("fk_tipo");
                }
                int fk_instalacion;
                if (jsonArray1.getJSONObject(j).getString("fk_instalacion").equals("null") || jsonArray1.getJSONObject(j).getString("fk_instalacion").equals("")) {
                    fk_instalacion = -1;
                } else {
                    fk_instalacion = jsonArray1.getJSONObject(j).getInt("fk_instalacion");
                }
                int fk_estado;
                if (jsonArray1.getJSONObject(j).getString("fk_estado").equals("null") || jsonArray1.getJSONObject(j).getString("fk_estado").equals("")) {
                    fk_estado = -1;
                } else {
                    fk_estado = jsonArray1.getJSONObject(j).getInt("fk_estado");
                }
                int fk_contrato_mantenimiento;
                if (jsonArray1.getJSONObject(j).getString("fk_contrato_mantenimiento").equals("null") || jsonArray1.getJSONObject(j).getString("fk_contrato_mantenimiento").equals("")) {
                    fk_contrato_mantenimiento = -1;
                } else {
                    fk_contrato_mantenimiento = jsonArray1.getJSONObject(j).getInt("fk_contrato_mantenimiento");
                }
                int fk_gama;
                if (jsonArray1.getJSONObject(j).getString("fk_gama").equals("null") || jsonArray1.getJSONObject(j).getString("fk_gama").equals("")) {
                    fk_gama = -1;
                } else {
                    fk_gama = jsonArray1.getJSONObject(j).getInt("fk_gama");
                }
                int fk_tipo_gama;
                if (jsonArray1.getJSONObject(j).getString("fk_tipo_gama").equals("null") || jsonArray1.getJSONObject(j).getString("fk_tipo_gama").equals("")) {
                    fk_tipo_gama = -1;
                } else {
                    fk_tipo_gama = jsonArray1.getJSONObject(j).getInt("fk_tipo_gama");
                }
                String fecha_creacion;
                if (jsonArray1.getJSONObject(j).getString("fecha_creacion").equals("null")) {
                    fecha_creacion = "";
                } else {
                    fecha_creacion = jsonArray1.getJSONObject(j).getString("fecha_creacion");
                }
                String modelo;
                if (jsonArray1.getJSONObject(j).getString("modelo").equals("null")) {
                    modelo = "";
                } else {
                    modelo = jsonArray1.getJSONObject(j).getString("modelo");
                }
                String num_serie;
                if (jsonArray1.getJSONObject(j).getString("num_serie").equals("null")) {
                    num_serie = "";
                } else {
                    num_serie = jsonArray1.getJSONObject(j).getString("num_serie");
                }
                String num_producto;
                if (jsonArray1.getJSONObject(j).getString("num_producto").equals("null")) {
                    num_producto = "";
                } else {
                    num_producto = jsonArray1.getJSONObject(j).getString("num_producto");
                }
                String aparato;
                if (jsonArray1.getJSONObject(j).getString("aparato").equals("null")) {
                    aparato = "";
                } else {
                    aparato = jsonArray1.getJSONObject(j).getString("aparato");
                }
                String puesta_marcha;
                if (jsonArray1.getJSONObject(j).getString("puesta_marcha").equals("null")) {
                    puesta_marcha = "";
                } else {
                    puesta_marcha = jsonArray1.getJSONObject(j).getString("puesta_marcha");
                }
                String fecha_compra;
                if (jsonArray1.getJSONObject(j).getString("fecha_compra").equals("null")) {
                    fecha_compra = "";
                } else {
                    fecha_compra = jsonArray1.getJSONObject(j).getString("fecha_compra");
                }
                String fecha_fin_garantia;
                if (jsonArray1.getJSONObject(j).getString("fecha_fin_garantia").equals("null")) {
                    fecha_fin_garantia = "";
                } else {
                    fecha_fin_garantia = jsonArray1.getJSONObject(j).getString("fecha_fin_garantia");
                }
                String mantenimiento_anual;
                if (jsonArray1.getJSONObject(j).getString("mantenimiento_anual").equals("null")) {
                    mantenimiento_anual = "";
                } else {
                    mantenimiento_anual = jsonArray1.getJSONObject(j).getString("mantenimiento_anual");
                }
                String observaciones;
                if (jsonArray1.getJSONObject(j).getString("observaciones").equals("null")) {
                    observaciones = "";
                } else {
                    observaciones = jsonArray1.getJSONObject(j).getString("observaciones");
                }
                String ubicacion;
                if (jsonArray1.getJSONObject(j).getString("ubicacion").equals("null")) {
                    ubicacion = "";
                } else {
                    ubicacion = jsonArray1.getJSONObject(j).getString("ubicacion");
                }
                String tienda_compra;
                if (jsonArray1.getJSONObject(j).getString("tienda_compra").equals("null")) {
                    tienda_compra = "";
                } else {
                    tienda_compra = jsonArray1.getJSONObject(j).getString("tienda_compra");
                }
                String garantia_extendida;
                if (jsonArray1.getJSONObject(j).getString("garantia_extendida").equals("null")) {
                    garantia_extendida = "";
                } else {
                    garantia_extendida = jsonArray1.getJSONObject(j).getString("garantia_extendida");
                }
                String factura_compra;
                if (jsonArray1.getJSONObject(j).getString("factura_compra").equals("null")) {
                    factura_compra = "";
                } else {
                    factura_compra = jsonArray1.getJSONObject(j).getString("factura_compra");
                }
                String refrigerante;
                if (jsonArray1.getJSONObject(j).getString("refrigerante").equals("null")) {
                    refrigerante = "";
                } else {
                    refrigerante = jsonArray1.getJSONObject(j).getString("refrigerante");
                }
                String nombre_instalacion;
                if (jsonArray1.getJSONObject(j).getString("nombre_instalacion").equals("null")) {
                    nombre_instalacion = "";
                } else {
                    nombre_instalacion = jsonArray1.getJSONObject(j).getString("nombre_instalacion");
                }
                String en_propiedad;
                if (jsonArray1.getJSONObject(j).getString("en_propiedad").equals("null")) {
                    en_propiedad = "";
                } else {
                    en_propiedad = jsonArray1.getJSONObject(j).getString("en_propiedad");
                }
                String esPrincipal;
                if (jsonArray1.getJSONObject(j).getString("esPrincipal").equals("null")) {
                    esPrincipal = "";
                } else {
                    esPrincipal = jsonArray1.getJSONObject(j).getString("esPrincipal");
                }
                boolean bEsInstalacion;
                if (jsonArray1.getJSONObject(j).getString("bEsInstalacion").equals("null")) {
                    bEsInstalacion = false;
                } else {
                    if (jsonArray1.getJSONObject(j).getString("bEsInstalacion").equals("0")) {
                        bEsInstalacion = false;
                    } else {
                        bEsInstalacion = true;
                    }
                }
                String combustible_txt;
                if (jsonArray1.getJSONObject(j).getString("combustible_txt").equals("null")) {
                    combustible_txt = "";
                } else {
                    combustible_txt = jsonArray1.getJSONObject(j).getString("combustible_txt");
                }
                String situacion;
                if (jsonArray1.getJSONObject(j).getString("situacion").equals("null")) {
                    situacion = "";
                } else {
                    situacion = jsonArray1.getJSONObject(j).getString("situacion");
                }
                String nombre_contr_man;
                if (jsonArray1.getJSONObject(j).getString("nombre_contr_man").equals("null")) {
                    nombre_contr_man = "";
                } else {
                    nombre_contr_man = jsonArray1.getJSONObject(j).getString("nombre_contr_man");
                }
                String documento_modelo;
                if (jsonArray1.getJSONObject(j).getString("documento").equals("null")) {
                    documento_modelo = "";
                } else {
                    documento_modelo = jsonArray1.getJSONObject(j).getString("documento");
                }
                String temperatura_max_acs="", caudal_acs="", potencia_util="", temperatura_agua_generador_calor_entrada="", temperatura_agua_generador_calor_salida="";
                if (!esta) {
                    if (MaquinaDAO.newMaquina(context,fk_maquina,id_parte,   fk_direccion,   fk_marca,   fk_tipo_combustion, fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion, fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama, fecha_creacion,   modelo,   num_serie,   num_producto,   aparato, puesta_marcha,   fecha_compra,   fecha_fin_garantia, mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra, garantia_extendida,   factura_compra,   refrigerante, bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion, temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida, combustible_txt,nombre_contr_man,documento_modelo)) {
                        bien = true;
                    } else {
                        bien = false;
                    }
                }else{
                    MaquinaDAO.actualizarMaquina(context,fk_maquina,id_parte,   fk_direccion,   fk_marca,   fk_tipo_combustion, fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion, fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama, fecha_creacion,   modelo,   num_serie,   num_producto,   aparato, puesta_marcha,   fecha_compra,   fecha_fin_garantia, mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra, garantia_extendida,   factura_compra,   refrigerante, bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion, temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida);
                }
                maquinas.clear();

            }
        }
    }
}
