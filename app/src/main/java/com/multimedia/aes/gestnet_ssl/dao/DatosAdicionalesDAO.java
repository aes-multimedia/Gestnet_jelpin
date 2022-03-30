package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;

import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.DatosAdicionales;

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
    public static DatosAdicionales crearDatosAdicionalesRet(DatosAdicionales d,Context context) throws java.sql.SQLException {
        try {
            cargarDao(context);
            dao.create(d);
            return d;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
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
    public static double buscarDisposicionDeServicio(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.ID_REL, id);
        if(listadoDatos.isEmpty()) {
            return 0;
        }else{
            return listadoDatos.get(0).getPreeu_disposicion_servicio();
        }
    }

    public static double buscarPrecioManoDeObra(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.ID_REL, id);
        if(listadoDatos.isEmpty()) {
            return 0;
        }else{
            return listadoDatos.get(0).getPreeu_mano_de_obra_precio();
        }
    }

    public static int buscarFormaDePago(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.FK_PARTE, id);
        if(listadoDatos.isEmpty()) {
            return 0;
        }else{
            return listadoDatos.get(0).getFk_forma_pago();
        }
    }

    public static double buscarHorasManoDeObra(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<DatosAdicionales> listadoDatos= dao.queryForEq(DatosAdicionales.ID_REL, id);
        if(listadoDatos.isEmpty()) {
            return 0;
        }else{
            return listadoDatos.get(0).getPreeu_mano_de_obra();
        }
    }




    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//

    public static void actualizarDatosAdicionales(Context context, int id_rel,int fk_parte,int fk_forma_pago,String sintomas_averia, String operacion_efectuada,String observaciones, boolean preeu_disposicion_servicio_si_no, double preeu_disposicion_servicio,
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
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.FK_PARTE,fk_parte);
        updateBuilder.updateColumnValue(DatosAdicionales.FK_FORMA_PAGO,fk_forma_pago);
        updateBuilder.updateColumnValue(DatosAdicionales.SINTOMAS_AVERIA,sintomas_averia);
        updateBuilder.updateColumnValue(DatosAdicionales.OPERACION_EFECTUADA,operacion_efectuada);
        updateBuilder.updateColumnValue(DatosAdicionales.OBSERVACIONES,observaciones );
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_DISPOSICION_SERVICIO_SI_NO,preeu_disposicion_servicio_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_DISPOSICION_SERVICIO,preeu_disposicion_servicio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA_SI_NO,preeu_mano_de_obra_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA_PRECIO,preeu_mano_de_obra_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA,preeu_mano_de_obra);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MATERIALES_SI_NO,preeu_materiales_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MATERIALES,preeu_materiales);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_PUESTA_MARCHA_SI_NO,preeu_puesta_marcha_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_PUESTA_MARCHA,preeu_puesta_marcha);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_SERVICIO_URGENCIA_SI_NO,preeu_servicio_urgencia_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_SERVICIO_URGENCIA,preeu_servicio_urgencia);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM_SI_NO,preeu_km_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM_PRECIO,preeu_km_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM,preeu_km);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM_PRECIO_TOTAL,preeu_km_precio_total);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ANALISIS_COMBUSTION,preeu_analisis_combustion);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ADICIONAL,preeu_adicional);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ADICIONAL_COSTE,preeu_adicional_coste);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_OTROS_SI_NO,preeu_otros_si_no);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_OTROS_NOMBRE,preeu_otros_nombre);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ADICIONAL_COSTE_NOMBRE,preeu_adicional_coste_nombre);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_IVA_APLICADO,preeu_iva_aplicado);
        updateBuilder.updateColumnValue(DatosAdicionales.TOTAL_PPTO,total_ppto);
        updateBuilder.updateColumnValue(DatosAdicionales.BACEPTAPRESUPUESTO,baceptapresupuesto);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_PORCO2,ctrlgas_rencal_porco2);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_PORO2,ctrlgas_rencal_poro2);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_PPMCOCORREG,ctrlgas_rencal_ppmcocorreg);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_THUMOSGRADOS,ctrlgas_rencal_thumosgrados);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_TAMBIENTEGRADOS,ctrlgas_rencal_tambientegrados);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_POREXCAIRE,ctrlgas_rencal_porexcaire);
        updateBuilder.updateColumnValue(DatosAdicionales.CTRLGAS_RENCAL_PORRENDIMIENTO,ctrlgas_rencal_porrendimiento);
        updateBuilder.updateColumnValue(DatosAdicionales.REGQUE_INYECTOR,regque_inyector);
        updateBuilder.updateColumnValue(DatosAdicionales.REGQUE_AIREPRIMARIO,regque_aireprimario);
        updateBuilder.updateColumnValue(DatosAdicionales.REGQUE_PRESIONBOMBA,regque_presionbomba);
        updateBuilder.updateColumnValue(DatosAdicionales.REGQUE_AIRE_LINEA,regque_aire_linea);
        updateBuilder.updateColumnValue(DatosAdicionales.BCONTROLARAIREVENTILACION,bcontrolaraireventilacion);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_MATERIALES,fact_materiales);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_DISPOSICION_SERVICIO,fact_disposicion_servicio);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_MANODEOBRA_PRECIO,fact_manodeobra_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_MANODEOBRA,fact_manodeobra);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_PUESTA_EN_MARCHA,fact_puesta_en_marcha);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_ANALISIS_COMBUSTION,fact_analisis_combustion);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_SERVICIO_URGENCIA,fact_servicio_urgencia);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_KM,fact_km);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_KM_PRECIO,fact_km_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_KM_PRECIO_TOTAL,fact_km_precio_total);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_ADICIONAL,fact_adicional);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_ADICIONAL_COSTE,fact_adicional_coste);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_OTROS_NOMBRE,fact_otros_nombre);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_ADICIONAL_COSTE_NOMBRE,fact_adicional_coste_nombre);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_POR_IVA_APLICADO,fact_por_iva_aplicado);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_TOTAL_CON_IVA,fact_total_con_iva);
        updateBuilder.updateColumnValue(DatosAdicionales.RETENCION,retencion);
        updateBuilder.updateColumnValue(DatosAdicionales.RETENCION_PORC,retencion_porc);
        updateBuilder.updateColumnValue(DatosAdicionales.MATEM_HORA_ENTRADA,matem_hora_entrada);
        updateBuilder.updateColumnValue(DatosAdicionales.MATEM_HORA_SALIDA,matem_hora_salida);
        updateBuilder.updateColumnValue(DatosAdicionales.OBSERVACIONESPAGO,observacionespago);
        updateBuilder.updateColumnValue(DatosAdicionales.COBRAR_ANALISIS_COMBUSTION,cobrar_analisis_combustion);
        updateBuilder.update();
    }




    public static void actualizarDatosAdicionales(Context context,int id_rel,
                                                  String operacionEfectuada,
                                                  double preeu_materiales,
                                                  double preeu_disposicion_servicio,
                                                  double preeu_mano_de_obra_precio,
                                                  double preeu_mano_de_obra_horas,
                                                  double preeu_total_mano_de_obra_horas,
                                                  double preeu_puesta_marcha,
                                                  double preeu_servicio_urgencia,
                                                  double preeu_km,
                                                  double preeu_km_precio,
                                                  double preeu_km_precio_total,
                                                  double preeu_analisis_combustion,
                                                  String preeu_otros_nombre,
                                                  double preeu_adicional,
                                                  double etSubTotal,
                                                  double preeu_iva_aplicado,
                                                  double total,
                                                  boolean acepta_presupuesto,
                                                  int formaPago) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.FK_FORMA_PAGO,formaPago);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_PUESTA_MARCHA,preeu_puesta_marcha);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_DISPOSICION_SERVICIO,preeu_disposicion_servicio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA_PRECIO,preeu_mano_de_obra_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA,preeu_mano_de_obra_horas);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_SERVICIO_URGENCIA,preeu_servicio_urgencia);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM_PRECIO,preeu_km_precio);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM,preeu_km);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_KM_PRECIO_TOTAL,preeu_km_precio_total);
        updateBuilder.updateColumnValue(DatosAdicionales.OPERACION_EFECTUADA,operacionEfectuada);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_OTROS_NOMBRE,preeu_otros_nombre);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ADICIONAL,preeu_adicional);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MATERIALES,preeu_materiales);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_ANALISIS_COMBUSTION,preeu_analisis_combustion);
        updateBuilder.updateColumnValue(DatosAdicionales.TOTAL_PPTO,total);
        updateBuilder.updateColumnValue(DatosAdicionales.BACEPTAPRESUPUESTO,acepta_presupuesto);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_IVA_APLICADO,preeu_iva_aplicado);

        updateBuilder.update();



    }



    public static void actualizarDatosAdicionalesParteFacturable(Context context,int id_rel,
                                                  double fact_materiales,
                                                  double fact_por_iva_aplicado,
                                                  double fact_total_con_iva) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_MATERIALES,fact_materiales);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_TOTAL_CON_IVA,fact_total_con_iva);
        updateBuilder.updateColumnValue(DatosAdicionales.FACT_POR_IVA_APLICADO,fact_por_iva_aplicado);

        updateBuilder.update();



    }




    public static void actualizarHoraEntrada(Context context,int id_rel,String horaEntrada) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.MATEM_HORA_ENTRADA,horaEntrada);

        updateBuilder.update();

    }

    public static void actualizarHoraSalida(Context context,int id_rel,String horaSalida) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.MATEM_HORA_SALIDA,horaSalida);

        updateBuilder.update();

    }

     public static void actualizarOperacionEfectuada(Context context,int id_rel,String op) throws java.sql.SQLException {
            cargarDao(context);
            UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
            updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
            updateBuilder.updateColumnValue(DatosAdicionales.OPERACION_EFECTUADA,op);

            updateBuilder.update();

        }

    public static void actualizarDisposicionDeServicio(Context context,int id_rel,double precio) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_DISPOSICION_SERVICIO,precio);

        updateBuilder.update();

    }


    public static void actializarManoDeObraPrecio(Context context,int id_rel,double precio) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA_PRECIO,precio);

        updateBuilder.update();

    }

    public static void actualizarFormaPago(Context context,int id_rel,int fp) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.FK_FORMA_PAGO,fp);

        updateBuilder.update();

    }


    public static void actualizarHorasManoDeObra(Context context,int id_rel,double horas) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<DatosAdicionales, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(DatosAdicionales.ID_REL,id_rel);
        updateBuilder.updateColumnValue(DatosAdicionales.PREEU_MANO_DE_OBRA,horas);

        updateBuilder.update();

    }
}
