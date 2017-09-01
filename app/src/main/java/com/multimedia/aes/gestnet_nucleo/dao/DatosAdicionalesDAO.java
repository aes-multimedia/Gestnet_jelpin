package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import android.database.SQLException;
import android.widget.Toast;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment1;

import java.util.List;

/**
 * Created by acp on 22/08/2017.
 */

public class DatosAdicionalesDAO extends DBHelperMOS {

    static Dao<DatosAdicionales, Integer> dao;

    public static void cargarDao(Context context) throws SQLException, java.sql.SQLException {
        dao = getHelper(context).getDatosAdicionalesDAO();
    }

//__________FUNCIONES DE CREACIÓN________________________//


    public static boolean newDatosAdicionales(Context context,int id_rel,int fk_parte,int fk_forma_pago,String sintomas_averia, String operacion_efectuada,String observaciones, boolean preeu_disposicion_servicio_si_no, double preeu_disposicion_servicio,
                                           boolean preeu_mano_de_obra_si_no, double preeu_mano_de_obra_precio, double preeu_mano_de_obra,
                                           boolean preeu_materiales_si_no, double preeu_materiales, boolean preeu_puesta_marcha_si_no,
                                           double preeu_puesta_marcha, boolean preeu_servicio_urgencia_si_no, double preeu_servicio_urgencia,
                                           boolean preeu_km_si_no, double preeu_km_precio, double preeu_km, double preeu_km_precio_total,
                                           double preeu_analisis_combustion, double preeu_adicional, double preeu_adicional_coste,
                                           boolean preeu_otros_si_no, String preeu_otros_nombre, String preeu_adicional_coste_nombre,
                                           double preeu_iva_aplicado, double total_ppto, boolean baceptapresupuesto, String ctrlgas_rencal_porco2,
                                           String ctrlgas_rencal_poro2, String ctrlgas_rencal_ppmcocorreg, String ctrlgas_rencal_thumosgrados,
                                           String ctrlgas_rencal_tambientegrados, String ctrlgas_rencal_porexcaire, String ctrlgas_rencal_porrendimiento,
                                           String regque_inyector, String regque_aireprimario, String regque_presionbomba, String regque_aire_linea,
                                           boolean bcontrolaraireventilacion, double fact_materiales, double fact_disposicion_servicio,
                                           double fact_manodeobra_precio, double fact_manodeobra, double fact_puesta_en_marcha,
                                           double fact_analisis_combustion, double fact_servicio_urgencia, double fact_km, double fact_km_precio,
                                           double fact_km_precio_total, double fact_adicional, double fact_adicional_coste, String fact_otros_nombre,
                                           String fact_adicional_coste_nombre, double fact_por_iva_aplicado, double fact_total_con_iva, double retencion,
                                           double retencion_porc, String matem_hora_entrada, String matem_hora_salida, String observacionespago, boolean cobrar_analisis_combustion) throws java.sql.SQLException {


        DatosAdicionales d=montarDatosAdicionales( id_rel,fk_parte,fk_forma_pago,sintomas_averia,operacion_efectuada,observaciones,  preeu_disposicion_servicio_si_no,  preeu_disposicion_servicio,
                preeu_mano_de_obra_si_no,  preeu_mano_de_obra_precio,  preeu_mano_de_obra,
                preeu_materiales_si_no,  preeu_materiales,  preeu_puesta_marcha_si_no,
                preeu_puesta_marcha,  preeu_servicio_urgencia_si_no,  preeu_servicio_urgencia,
                preeu_km_si_no,  preeu_km_precio,  preeu_km,  preeu_km_precio_total,
                preeu_analisis_combustion,  preeu_adicional,  preeu_adicional_coste,
                preeu_otros_si_no,  preeu_otros_nombre,  preeu_adicional_coste_nombre,
                preeu_iva_aplicado,  total_ppto,  baceptapresupuesto,  ctrlgas_rencal_porco2,
                ctrlgas_rencal_poro2,  ctrlgas_rencal_ppmcocorreg,  ctrlgas_rencal_thumosgrados,
                ctrlgas_rencal_tambientegrados,  ctrlgas_rencal_porexcaire,  ctrlgas_rencal_porrendimiento,
                regque_inyector,  regque_aireprimario,  regque_presionbomba,  regque_aire_linea,
                bcontrolaraireventilacion,  fact_materiales,  fact_disposicion_servicio,
                fact_manodeobra_precio,  fact_manodeobra,  fact_puesta_en_marcha,
                fact_analisis_combustion,  fact_servicio_urgencia,  fact_km,  fact_km_precio,
                fact_km_precio_total,  fact_adicional,  fact_adicional_coste,  fact_otros_nombre,
                fact_adicional_coste_nombre,  fact_por_iva_aplicado,  fact_total_con_iva,  retencion,
                retencion_porc,  matem_hora_entrada,  matem_hora_salida,  observacionespago,  cobrar_analisis_combustion);
        return crearDatosAdicionales(d,context);
    }
    public static boolean crearDatosAdicionales(DatosAdicionales d,Context context) throws java.sql.SQLException {
        try {
            cargarDao(context);
            dao.create(d);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static DatosAdicionales montarDatosAdicionales(int id_rel,int fk_parte,int fk_forma_pago,String sintomas_averia, String operacion_efectuada,String observaciones, boolean preeu_disposicion_servicio_si_no, double preeu_disposicion_servicio,
                                                          boolean preeu_mano_de_obra_si_no, double preeu_mano_de_obra_precio, double preeu_mano_de_obra,
                                                          boolean preeu_materiales_si_no, double preeu_materiales, boolean preeu_puesta_marcha_si_no,
                                                    double preeu_puesta_marcha, boolean preeu_servicio_urgencia_si_no, double preeu_servicio_urgencia,
                                                          boolean preeu_km_si_no, double preeu_km_precio, double preeu_km, double preeu_km_precio_total,
                                                    double preeu_analisis_combustion, double preeu_adicional, double preeu_adicional_coste,
                                                          boolean preeu_otros_si_no, String preeu_otros_nombre, String preeu_adicional_coste_nombre,
                                                          double preeu_iva_aplicado, double total_ppto, boolean baceptapresupuesto, String ctrlgas_rencal_porco2,
                                                    String ctrlgas_rencal_poro2, String ctrlgas_rencal_ppmcocorreg, String ctrlgas_rencal_thumosgrados,
                                                    String ctrlgas_rencal_tambientegrados, String ctrlgas_rencal_porexcaire, String ctrlgas_rencal_porrendimiento,
                                                    String regque_inyector, String regque_aireprimario, String regque_presionbomba, String regque_aire_linea,
                                                    boolean bcontrolaraireventilacion, double fact_materiales, double fact_disposicion_servicio,
                                                    double fact_manodeobra_precio, double fact_manodeobra, double fact_puesta_en_marcha,
                                                    double fact_analisis_combustion, double fact_servicio_urgencia, double fact_km, double fact_km_precio,
                                                    double fact_km_precio_total, double fact_adicional, double fact_adicional_coste, String fact_otros_nombre,
                                                    String fact_adicional_coste_nombre, double fact_por_iva_aplicado, double fact_total_con_iva, double retencion,
                                                    double retencion_porc, String matem_hora_entrada, String matem_hora_salida, String observacionespago, boolean cobrar_analisis_combustion) {
        DatosAdicionales d =new DatosAdicionales(id_rel,fk_parte,fk_forma_pago,sintomas_averia,operacion_efectuada,observaciones,  preeu_disposicion_servicio_si_no,  preeu_disposicion_servicio,
                preeu_mano_de_obra_si_no,  preeu_mano_de_obra_precio,  preeu_mano_de_obra,
                preeu_materiales_si_no,  preeu_materiales,  preeu_puesta_marcha_si_no,
                preeu_puesta_marcha,  preeu_servicio_urgencia_si_no,  preeu_servicio_urgencia,
                preeu_km_si_no,  preeu_km_precio,  preeu_km,  preeu_km_precio_total,
                preeu_analisis_combustion,  preeu_adicional,  preeu_adicional_coste,
                preeu_otros_si_no,  preeu_otros_nombre,  preeu_adicional_coste_nombre,
                preeu_iva_aplicado,  total_ppto,  baceptapresupuesto,  ctrlgas_rencal_porco2,
                ctrlgas_rencal_poro2,  ctrlgas_rencal_ppmcocorreg,  ctrlgas_rencal_thumosgrados,
                ctrlgas_rencal_tambientegrados,  ctrlgas_rencal_porexcaire,  ctrlgas_rencal_porrendimiento,
                regque_inyector,  regque_aireprimario,  regque_presionbomba,  regque_aire_linea,
                bcontrolaraireventilacion,  fact_materiales,  fact_disposicion_servicio,
                fact_manodeobra_precio,  fact_manodeobra,  fact_puesta_en_marcha,
                fact_analisis_combustion,  fact_servicio_urgencia,  fact_km,  fact_km_precio,
                fact_km_precio_total,  fact_adicional,  fact_adicional_coste,  fact_otros_nombre,
                fact_adicional_coste_nombre,  fact_por_iva_aplicado,  fact_total_con_iva,  retencion,
                retencion_porc,  matem_hora_entrada,  matem_hora_salida,  observacionespago,  cobrar_analisis_combustion);
        return d;
    }




    public static boolean montarDatosAdicionalesBool(int id_rel,int fk_parte,int fk_forma_pago,String sintomas_averia, String operacion_efectuada,String observaciones, boolean preeu_disposicion_servicio_si_no, double preeu_disposicion_servicio,
                                                          boolean preeu_mano_de_obra_si_no, double preeu_mano_de_obra_precio, double preeu_mano_de_obra,
                                                          boolean preeu_materiales_si_no, double preeu_materiales, boolean preeu_puesta_marcha_si_no,
                                                          double preeu_puesta_marcha, boolean preeu_servicio_urgencia_si_no, double preeu_servicio_urgencia,
                                                          boolean preeu_km_si_no, double preeu_km_precio, double preeu_km, double preeu_km_precio_total,
                                                          double preeu_analisis_combustion, double preeu_adicional, double preeu_adicional_coste,
                                                          boolean preeu_otros_si_no, String preeu_otros_nombre, String preeu_adicional_coste_nombre,
                                                          double preeu_iva_aplicado, double total_ppto, boolean baceptapresupuesto, String ctrlgas_rencal_porco2,
                                                          String ctrlgas_rencal_poro2, String ctrlgas_rencal_ppmcocorreg, String ctrlgas_rencal_thumosgrados,
                                                          String ctrlgas_rencal_tambientegrados, String ctrlgas_rencal_porexcaire, String ctrlgas_rencal_porrendimiento,
                                                          String regque_inyector, String regque_aireprimario, String regque_presionbomba, String regque_aire_linea,
                                                          boolean bcontrolaraireventilacion, double fact_materiales, double fact_disposicion_servicio,
                                                          double fact_manodeobra_precio, double fact_manodeobra, double fact_puesta_en_marcha,
                                                          double fact_analisis_combustion, double fact_servicio_urgencia, double fact_km, double fact_km_precio,
                                                          double fact_km_precio_total, double fact_adicional, double fact_adicional_coste, String fact_otros_nombre,
                                                          String fact_adicional_coste_nombre, double fact_por_iva_aplicado, double fact_total_con_iva, double retencion,
                                                          double retencion_porc, String matem_hora_entrada, String matem_hora_salida, String observacionespago, boolean cobrar_analisis_combustion) {
        DatosAdicionales d =new DatosAdicionales(id_rel,fk_parte,fk_forma_pago,sintomas_averia,operacion_efectuada,observaciones,  preeu_disposicion_servicio_si_no,  preeu_disposicion_servicio,
                preeu_mano_de_obra_si_no,  preeu_mano_de_obra_precio,  preeu_mano_de_obra,
                preeu_materiales_si_no,  preeu_materiales,  preeu_puesta_marcha_si_no,
                preeu_puesta_marcha,  preeu_servicio_urgencia_si_no,  preeu_servicio_urgencia,
                preeu_km_si_no,  preeu_km_precio,  preeu_km,  preeu_km_precio_total,
                preeu_analisis_combustion,  preeu_adicional,  preeu_adicional_coste,
                preeu_otros_si_no,  preeu_otros_nombre,  preeu_adicional_coste_nombre,
                preeu_iva_aplicado,  total_ppto,  baceptapresupuesto,  ctrlgas_rencal_porco2,
                ctrlgas_rencal_poro2,  ctrlgas_rencal_ppmcocorreg,  ctrlgas_rencal_thumosgrados,
                ctrlgas_rencal_tambientegrados,  ctrlgas_rencal_porexcaire,  ctrlgas_rencal_porrendimiento,
                regque_inyector,  regque_aireprimario,  regque_presionbomba,  regque_aire_linea,
                bcontrolaraireventilacion,  fact_materiales,  fact_disposicion_servicio,
                fact_manodeobra_precio,  fact_manodeobra,  fact_puesta_en_marcha,
                fact_analisis_combustion,  fact_servicio_urgencia,  fact_km,  fact_km_precio,
                fact_km_precio_total,  fact_adicional,  fact_adicional_coste,  fact_otros_nombre,
                fact_adicional_coste_nombre,  fact_por_iva_aplicado,  fact_total_con_iva,  retencion,
                retencion_porc,  matem_hora_entrada,  matem_hora_salida,  observacionespago,  cobrar_analisis_combustion);
        return true;
    }


    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosDatosAdicionales(Context context) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        DeleteBuilder<DatosAdicionales, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarDatosAdicionalesPorId(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        DeleteBuilder<DatosAdicionales, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(DatosAdicionales.ID_REL, id);
        deleteBuilder.delete();
    }

    public static void borrarDatosAdicionalesPorFkParte(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        DeleteBuilder<DatosAdicionales, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(DatosAdicionales.FK_PARTE, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<DatosAdicionales> buscarTodosLosDatosAdicionales(Context context) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForAll();
        if(listadoDatos.isEmpty()) {
            Toast.makeText(context,"no",Toast.LENGTH_SHORT).show();
            return null;
        }else{
            return listadoDatos;
        }
    }
    public static DatosAdicionales buscarDatosAdicionalesPorId(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.ID_REL, id);
        if(listadoDatos.isEmpty()) {
            return null;
        }else{
            return listadoDatos.get(0);
        }
    }

    public static DatosAdicionales buscarDatosAdicionalesPorFkParte(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.FK_PARTE, id);
        if(listadoDatos.isEmpty()) {
            return null;
        }else{
            return listadoDatos.get(0);
        }
    }
    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//






}
