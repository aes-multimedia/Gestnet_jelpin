package com.multimedia.aes.gestnet_sgsv2.dao;


import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;


import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;

import java.sql.SQLException;
import java.util.List;

public class TiposVisitaDAO extends DBHelperMOS {

    static Dao<TiposVisita, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getTiposVisitaDAO();
    }

    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newTipoVisita(Context context,int id_tipo_visita, String descripcion, int subtipo) {
        TiposVisita t = montarTipoVisita(id_tipo_visita, descripcion, subtipo);
        return crearTipoVisita(t,context);
    }
    public static boolean crearTipoVisita(TiposVisita t,Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static TiposVisita montarTipoVisita(int id_tipo_visita, String descripcion, int subtipo) {
        TiposVisita t =new TiposVisita(id_tipo_visita, descripcion, subtipo);
        return t;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosTiposVisita(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TiposVisita, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarTipoVisitaPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TiposVisita, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(TiposVisita.ID_TIPO_VISITA, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<TiposVisita> buscarTodosLosTipoVisita(Context context) throws SQLException {
        cargarDao(context);
        List<TiposVisita> listadoTipoVisita= dao.queryForAll();
        if(listadoTipoVisita.isEmpty()) {
            return null;
        }else{
            return listadoTipoVisita;
        }
    }
    public static TiposVisita buscarTipoVisitaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TiposVisita> listadoTipoVisita= dao.queryForEq(TiposVisita.ID_TIPO_VISITA, id);
        if(listadoTipoVisita.isEmpty()) {
            return null;
        }else{
            return listadoTipoVisita.get(0);
        }
    }
    public static int buscarTipoVisitaPorNombre(Context context, String nombre) throws SQLException {
        cargarDao(context);
        List<TiposVisita> listadoTipoVisita= dao.queryForEq(TiposVisita.DESCRIPCION, nombre);
        if(listadoTipoVisita.isEmpty()) {
            return -1;
        }else{
            return listadoTipoVisita.get(0).getId_tipo_visita();
        }
    }
    public static String buscarNombreTipoVisitaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TiposVisita> listadoSubTiposVisita= dao.queryForEq(TiposVisita.ID_TIPO_VISITA, id);
        if(listadoSubTiposVisita.isEmpty()) {
            return null;
        }else{
            return listadoSubTiposVisita.get(0).getDescripcion();
        }
    }


}
