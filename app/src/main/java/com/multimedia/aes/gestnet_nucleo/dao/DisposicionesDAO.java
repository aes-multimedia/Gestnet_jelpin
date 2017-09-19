package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;

import java.util.List;

/**
 * Created by acp on 25/08/2017.
 */

public class DisposicionesDAO extends DBHelperMOS {
    static Dao<Disposiciones, Integer> dao;

    public static void cargarDao(Context context) throws SQLException, java.sql.SQLException {
        dao = getHelper(context).getDisposicionesDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newDisposiciones(Context context ,int id_disposicion_servicio, String nombre_disposicion, int coste_disposicion, int precio_disposicion )throws java.sql.SQLException {
        Disposiciones d = montarDisposiciones(id_disposicion_servicio,nombre_disposicion, coste_disposicion, precio_disposicion);
        return crearDisposiciones(d,context);
    }
    public static boolean crearDisposiciones(Disposiciones d,Context context) throws java.sql.SQLException {
        try {
            cargarDao(context);
            dao.create(d);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static Disposiciones montarDisposiciones(int id_disposicion_servicio, String nombre_disposicion, int coste_disposicion, int precio_disposicion) {
        Disposiciones d =new Disposiciones(id_disposicion_servicio,nombre_disposicion, coste_disposicion, precio_disposicion);
        return d;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodasLasDisposiciones(Context context) throws SQLException {
        try {
            cargarDao(context);
            DeleteBuilder<Disposiciones, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrarDisposicionesPorID(Context context, int id) throws SQLException {
        try { cargarDao(context);
        DeleteBuilder<Disposiciones, Integer> deleteBuilder = dao.deleteBuilder();

            deleteBuilder.where().eq(Disposiciones.ID_DISPOSICION_SERVICIO, id);
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<Disposiciones> buscarTodasLasDisposiciones(Context context) throws SQLException {
        try {
            cargarDao(context);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        List<Disposiciones> listadoDisposiciones= null;
        try {
            listadoDisposiciones = dao.queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoDisposiciones.isEmpty()) {

            return null;
        }else{
            return listadoDisposiciones;
        }
    }
    public static Disposiciones buscarDisposicionesPorId(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<Disposiciones> listadoDisposiciones= null;
        try {
            listadoDisposiciones = dao.queryForEq(Disposiciones.ID_DISPOSICION_SERVICIO, id);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoDisposiciones.isEmpty()) {
            return null;
        }else{
            return listadoDisposiciones.get(0);
        }
    }

    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
    public static void actualizarDisposiciones(Context context, int id_disposicion_servicio, String nombre_disposicion, int coste_disposicion, int precio_disposicion) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<Disposiciones, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Disposiciones.ID_DISPOSICION_SERVICIO,id_disposicion_servicio);
        updateBuilder.updateColumnValue(Disposiciones.NOMBRE_DISPOSICION,nombre_disposicion);
        updateBuilder.updateColumnValue(Disposiciones.COSTE_DISPOSICION,coste_disposicion);
        updateBuilder.updateColumnValue(Disposiciones.PRECIO_DISPOSICION,precio_disposicion);
        updateBuilder.update();
    }
}
