package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.TipoCaldera;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by acp on 02/01/2018.
 */

public class TipoCalderaDAO extends DBHelperMOS{


    static Dao<TipoCaldera, Integer> dao;

//__________FUNCIONES DE CREACIÓN________________________//

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getTipoCalderaDAO();
    }


    public static boolean newTipoCaldera(Context context,int id_tipo_caldera, String nombre_tipo_caldera){
        TipoCaldera t = montarTipoCaldera(id_tipo_caldera,   nombre_tipo_caldera);
        return crearTipoCaldera(t, context);
    }

    public static boolean crearTipoCaldera(TipoCaldera t, Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static TipoCaldera montarTipoCaldera(int id_tipo_caldera, String nombre_tipo_caldera ) {
        TipoCaldera t = new TipoCaldera(id_tipo_caldera,   nombre_tipo_caldera);
        return t;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodasLosTipoCaldera(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TipoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }

    public static void borrarTipoCalderaPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TipoCaldera, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(TipoCaldera.ID_TIPO_COMBUSTION, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<TipoCaldera> buscarTodasLosTipoCaldera(Context context) throws SQLException {
        cargarDao(context);
        List<TipoCaldera> listadoTipos = dao.queryForAll();
        if (listadoTipos.isEmpty()) {
            return null;
        } else {
            return listadoTipos;
        }
    }

    public static TipoCaldera buscarTipoCalderaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TipoCaldera> listadoTipos = dao.queryForEq(TipoCaldera.ID_TIPO_COMBUSTION, id);
        if (listadoTipos.isEmpty()) {
            return null;
        } else {
            return listadoTipos.get(0);
        }
    }
    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
    public static void actualizarTipos(Context context, int id_tipo_caldera, String nombre_tipo_caldera ) throws SQLException {
        cargarDao(context);
        UpdateBuilder<TipoCaldera, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(TipoCaldera.ID_TIPO_COMBUSTION,id_tipo_caldera);
        updateBuilder.updateColumnValue(TipoCaldera.ID_TIPO_COMBUSTION,id_tipo_caldera);
        updateBuilder.updateColumnValue(TipoCaldera.NOMBRE_TIPO_COMBUSTION,nombre_tipo_caldera);
        updateBuilder.update();
    }




}
