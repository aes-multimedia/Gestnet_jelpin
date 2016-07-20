package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;


import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

import java.sql.SQLException;
import java.util.List;

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
        deleteBuilder.where().eq(SubTiposVisita.ID_SUBTIPO_VISITA, id);
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
        List<SubTiposVisita> listadoSubTiposVisita= dao.queryForEq(SubTiposVisita.ID_SUBTIPO_VISITA, id);
        if(listadoSubTiposVisita.isEmpty()) {
            return null;
        }else{
            return listadoSubTiposVisita.get(0);
        }
    }
    public static List<SubTiposVisita> buscarSubTiposVisitaPorTipo(Context context, int tipo) throws SQLException {
        cargarDao(context);
        List<SubTiposVisita> listadoSubTiposVisita= dao.queryForEq(SubTiposVisita.FK_TIPO_VISITA, tipo);
        if(listadoSubTiposVisita.isEmpty()) {
            return null;
        }else{
            return listadoSubTiposVisita;
        }
    }


    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



}
