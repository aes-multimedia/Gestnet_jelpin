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

/**
 * Created by Sergio on 13/07/2016.
 */
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
        deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
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
        List<TiposVisita> listadoTipoVisita= dao.queryForEq(Tecnico.ID_TECNICO, id);
        if(listadoTipoVisita.isEmpty()) {
            return null;
        }else{
            return listadoTipoVisita.get(0);
        }
    }


    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


    /*public void actualizarCif (Context context, String dni, int id_usuario ) throws SQLException
    {
        cargarDao(context);
        UpdateBuilder<Tecnico, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Tecnico.ID_TECNICO,id_usuario);
        updateBuilder.updateColumnValue(Tecnico.DNI_CIF, dni);
        updateBuilder.update();
    }*/
}