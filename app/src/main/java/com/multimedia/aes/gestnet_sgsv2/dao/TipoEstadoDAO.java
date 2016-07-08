package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoEstado;

import java.sql.SQLException;
import java.util.List;


public class TipoEstadoDAO  extends DBHelperMOS {


    static Dao<TipoEstado, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getTipoEstadoDAO();
    }

    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newTipoEstado(Context context,int id_tipo_estado, String nombre_estado) {
        TipoEstado t = montarTipoEstado(id_tipo_estado, nombre_estado);
        return crearTipoEstado(t,context);
    }
    public static boolean crearTipoEstado(TipoEstado t,Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static TipoEstado montarTipoEstado(int id_tipo_estado, String nombre_estado) {
        TipoEstado t =new TipoEstado(id_tipo_estado, nombre_estado);
        return t;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosTipoEstado(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TipoEstado, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarTipoEstadoPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TipoEstado, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<TipoEstado> buscarTodosLosTipoEstado(Context context) throws SQLException {
        cargarDao(context);
        List<TipoEstado> listadoTipoEstado= dao.queryForAll();
        if(listadoTipoEstado.isEmpty()) {
            return null;
        }else{
            return listadoTipoEstado;
        }
    }
    public static TipoEstado buscarTipoEstadoPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TipoEstado> listadoTipoEstado= dao.queryForEq(Tecnico.ID_TECNICO, id);
        if(listadoTipoEstado.isEmpty()) {
            return null;
        }else{
            return listadoTipoEstado.get(0);
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
