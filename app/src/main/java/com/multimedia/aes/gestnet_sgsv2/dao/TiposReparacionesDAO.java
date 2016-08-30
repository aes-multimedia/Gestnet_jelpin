package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoEstado;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergio on 08/07/2016.
 */
public class TiposReparacionesDAO  extends DBHelperMOS {


    static Dao<TiposReparaciones, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getTiposReparacionesDAO();
    }

    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newTiposReparaciones(Context context,int id_tipo_reparacion, int codigo, String descripcion,String abreviatura) {
        TiposReparaciones t = montarTiposReparaciones(context ,id_tipo_reparacion, codigo , descripcion, abreviatura);
        return crearTiposReparaciones(t,context);
    }
    public static boolean crearTiposReparaciones(TiposReparaciones t,Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static TiposReparaciones montarTiposReparaciones(Context context,int id_tipo_reparacion, int codigo, String descripcion,String abreviatura) {
        TiposReparaciones t =new TiposReparaciones(id_tipo_reparacion, codigo, descripcion, abreviatura);
        return t;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosTiposReparaciones(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TiposReparaciones, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarTiposReparacionesPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TiposReparaciones, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<TiposReparaciones> buscarTodosLosTiposReparaciones(Context context) throws SQLException {
        cargarDao(context);
        List<TiposReparaciones> listadoTiposReparaciones= dao.queryForAll();
        if(listadoTiposReparaciones.isEmpty()) {
            return null;
        }else{
            return listadoTiposReparaciones;
        }
    }
    public static TiposReparaciones buscarTiposReparacionesPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TiposReparaciones> listadoTiposReparaciones= dao.queryForEq(TiposReparaciones.ID_TIPO_REPARACION, id);
        if(listadoTiposReparaciones.isEmpty()) {
            return null;
        }else{
            return listadoTiposReparaciones.get(0);
        }
    }
    public static int buscarTiposReparacionesPorAbreviatura(Context context, String ab) throws SQLException {
        cargarDao(context);
        List<TiposReparaciones> listadoTiposReparaciones= dao.queryForEq(TiposReparaciones.ABREVIATURA, ab);
        if(listadoTiposReparaciones.isEmpty()) {
            return -1;
        }else{
            return listadoTiposReparaciones.get(0).getId_tipo_reparacion();
        }
    }



}
