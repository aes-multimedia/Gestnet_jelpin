package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Sergio on 13/07/2016.
 */
public class SubTiposVisitaDAO extends DBHelperMOS {


    static Dao<SubTiposVisita, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getSubTiposVisitaDAO();
    }

    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newSubTiposVisita(Context context,int id_subtipo, int activo, String descripcion_ticket, String descripcion, String codigo, int fk_tipo_visita) {
        SubTiposVisita t = montarSubTipoVisita(id_subtipo,activo,descripcion_ticket,descripcion,codigo,fk_tipo_visita);
        return crearSubTiposVisita(t,context);
    }
    public static boolean crearSubTiposVisita(SubTiposVisita t,Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static SubTiposVisita montarSubTipoVisita(int id_subtipo, int activo, String descripcion_ticket, String descripcion, String codigo, int fk_tipo_visita) {
        SubTiposVisita t =new SubTiposVisita(id_subtipo,activo,descripcion_ticket,descripcion,codigo,fk_tipo_visita);
        return t;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosSubTiposVisita(Context context) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        DeleteBuilder<SubTiposVisita, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarSubTiposVisitaPorID(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        DeleteBuilder<SubTiposVisita, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Tecnico.ID_TECNICO, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<SubTiposVisita> buscarTodosLosSubTiposVisita(Context context) throws SQLException {
        cargarDao(context);
        List<SubTiposVisita> listadoSubTiposVisita= dao.queryForAll();
        if(listadoSubTiposVisita.isEmpty()) {
            return null;
        }else{
            return listadoSubTiposVisita;
        }
    }
    public static SubTiposVisita buscarSubTiposVisitaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<SubTiposVisita> listadoSubTiposVisita= dao.queryForEq(Tecnico.ID_TECNICO, id);
        if(listadoSubTiposVisita.isEmpty()) {
            return null;
        }else{
            return listadoSubTiposVisita.get(0);
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