package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;

import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;

import java.util.List;

public class FormasPagoDAO extends DBHelperMOS {
    static Dao<FormasPago, Integer> dao;

    public static void cargarDao(Context context) throws SQLException, java.sql.SQLException {
        dao = getHelper(context).getFormasPagoDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newFomasPago(Context context, int id_forma_pago, String forma_pago, int financiado, boolean mostrar_cuenta, boolean sumar_dias, boolean bAparecerEnInforme, boolean mostrarcuenta )throws java.sql.SQLException {
        FormasPago d = montarFormasPago( id_forma_pago, forma_pago,  financiado,  mostrar_cuenta,  sumar_dias,  bAparecerEnInforme,  mostrarcuenta);
        return crearFormasPago(d,context);
    }
    public static boolean crearFormasPago(FormasPago d, Context context) throws java.sql.SQLException {
        try {
            cargarDao(context);
            dao.create(d);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static FormasPago montarFormasPago(int id_forma_pago, String forma_pago, int financiado, boolean mostrar_cuenta, boolean sumar_dias, boolean bAparecerEnInforme, boolean mostrarcuenta ) {
        FormasPago d =new FormasPago( id_forma_pago, forma_pago,  financiado,  mostrar_cuenta,  sumar_dias,  bAparecerEnInforme,  mostrarcuenta);
        return d;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodasLasFormasPago(Context context) throws SQLException {
        try {
            cargarDao(context);
            DeleteBuilder<FormasPago, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrarFormasPagoPorID(Context context, int id) throws SQLException {
        try { cargarDao(context);
            DeleteBuilder<FormasPago, Integer> deleteBuilder = dao.deleteBuilder();

            deleteBuilder.where().eq(FormasPago.ID_FORMA_PAGO, id);
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<FormasPago> buscarTodasLasFormasPago(Context context) throws SQLException {
        try {
            cargarDao(context);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        List<FormasPago> listadoFormasPago= null;
        try {
            listadoFormasPago = dao.queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoFormasPago.isEmpty()) {
            return null;
        }else{
            return listadoFormasPago;
        }
    }
    public static FormasPago buscarFormasPagoPorId(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<FormasPago> listadoFormasPago= null;
        try {
            listadoFormasPago = dao.queryForEq(FormasPago.ID_FORMA_PAGO, id);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoFormasPago.isEmpty()) {
            return null;
        }else{
            return listadoFormasPago.get(0);
        }
    }


    public static int buscarIdFormaPagoPorNombre(Context context, String s) throws java.sql.SQLException {
        cargarDao(context);
        List<FormasPago> listadoFormasPago = dao.queryForEq(FormasPago.FORMA_PAGO, s);
        if(listadoFormasPago.isEmpty()) {
            return -1;
        }else{
            return listadoFormasPago.get(0).getId_forma_pago();
        }
    }

    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
    public static void actualizarFormasPago(Context context, int id_forma_pago, String forma_pago, int financiado, boolean mostrar_cuenta, boolean sumar_dias, boolean bAparecerEnInforme, boolean mostrarcuenta) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<FormasPago, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(FormasPago.ID_FORMA_PAGO,id_forma_pago);
        updateBuilder.updateColumnValue(FormasPago.FORMA_PAGO,forma_pago);
        updateBuilder.updateColumnValue(FormasPago.FINANCIADO,financiado);
        updateBuilder.updateColumnValue(FormasPago.MOSTRAR_CUENTA,mostrar_cuenta);
        updateBuilder.updateColumnValue(FormasPago.SUMAR_DIAS,sumar_dias);
        updateBuilder.updateColumnValue(FormasPago.B_APARECER_EN_INFORME,bAparecerEnInforme);
        updateBuilder.updateColumnValue(FormasPago.MOSTRARCUENTA,mostrarcuenta);
        updateBuilder.update();
    }



}