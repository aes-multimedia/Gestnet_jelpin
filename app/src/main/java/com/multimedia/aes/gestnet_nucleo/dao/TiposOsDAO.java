package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.TipoCaldera;
import com.multimedia.aes.gestnet_nucleo.entidades.TiposOs;

import java.sql.SQLException;
import java.util.List;


public class TiposOsDAO extends DBHelperMOS {
    static Dao<TiposOs, Integer> dao;

//__________FUNCIONES DE CREACIÓN________________________//

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getTiposOSDAO();
    }


   public static boolean newTiposOs(Context context,int id_tipoOs, String nombre_tipoOs,int fk_parte_tipo){
        TiposOs t = montarTiposOs(id_tipoOs, nombre_tipoOs, fk_parte_tipo);
        return crearTiposOs(t, context);
    }
    public static TiposOs montarTiposOs(int id_tipoOs, String nombre_tipoOs,int fk_parte_tipo ) {
        TiposOs t = new TiposOs( id_tipoOs, nombre_tipoOs, fk_parte_tipo);
        return t;
    }
    public static boolean crearTiposOs(TiposOs t, Context context) {
        try {
            cargarDao(context);
            dao.create(t);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<TiposOs> buscarTodasLosTiposOs(Context context) throws SQLException {
        cargarDao(context);
        List<TiposOs> listadoTiposOs = dao.queryForAll();
        if (listadoTiposOs.isEmpty()) {
            return null;
        } else {
            return listadoTiposOs;
        }
    }


    public static void actualizarTiposOs(Context context, int id_tipoOs, String nombre_tipoOs,int fk_parte_tipo ) throws SQLException {
        cargarDao(context);
        UpdateBuilder<TiposOs, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(TiposOs.ID_TIPO_OS,id_tipoOs);
        updateBuilder.updateColumnValue(TiposOs.NOMBRE_TIPO_OS,nombre_tipoOs);
        updateBuilder.updateColumnValue(TiposOs.FK_PARTE_TIPO,fk_parte_tipo);
        updateBuilder.update();
    }
    public static void borrarTodasLosTipoOs(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<TiposOs, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public  static TiposOs buscarTipoOsPorId_tipoOS(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TiposOs> listadoTiposOS = dao.queryForEq(TiposOs.ID_TIPO_OS, id);
        if (listadoTiposOS.isEmpty()) {
            return null;
        } else {
            return listadoTiposOS.get(0);
        }
    }
    public  static int buscarTipoOsPorNombreTipoOs(Context context, String nombre) throws SQLException {
        cargarDao(context);
        List<TiposOs> listadoTiposOS = dao.queryForEq(TiposOs.NOMBRE_TIPO_OS, nombre);
        if (listadoTiposOS.isEmpty()) {
            return 0;
        } else {
            return listadoTiposOS.get(0).getId_tipoOs();
        }
    }
    public  static List buscarTipoOsPorFkTipoParte(Context context, int id) throws SQLException {
        cargarDao(context);
        List<TiposOs> listadoTiposOS = dao.queryBuilder().orderBy(TiposOs.NOMBRE_TIPO_OS,true).where().eq(TiposOs.FK_PARTE_TIPO, id).query();
        //List<TiposOs> listadoTiposOS = dao.queryForEq(TiposOs.FK_PARTE_TIPO, id);
        if (listadoTiposOS.isEmpty()) {
            return null;
        } else {
            return listadoTiposOS;
        }
    }
/*
    public static Marca montarMarca(int id_marca, String nombre_marca ) {
        Marca m = new Marca(id_marca,   nombre_marca);
        return m;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodasLasMarcas(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Marca, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }



    public static void borrarMarcaPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Marca, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Marca.ID_MARCA, id);
        deleteBuilder.delete();
    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<Marca> buscarTodasLasMarcas(Context context) throws SQLException {
        cargarDao(context);
        List<Marca> listadoMarcas = dao.queryForAll();
        if (listadoMarcas.isEmpty()) {
            return null;
        } else {
            listadoMarcas=listadoMarcas;
            return listadoMarcas;
        }
    }

    public static Marca buscarMarcaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<Marca> listadoMarcas = dao.queryForEq(Marca.ID_MARCA, id);
        if (listadoMarcas.isEmpty()) {
            return null;
        } else {
            return listadoMarcas.get(0);
        }
    }
    public static int buscarIdMarcaPorNombre(Context context, String nombre) throws SQLException {
        cargarDao(context);
        List<Marca> listadoMarcaCaldera = dao.queryForEq(Marca.NOMBRE_MARCA, nombre);
        if(listadoMarcaCaldera.isEmpty()) {
            return 0;
        }else{
            return listadoMarcaCaldera.get(0).getId_marca();
        }
    }
    public static String buscarNombreMarcaPorId(Context context, int id) throws SQLException {
        cargarDao(context);
        List<Marca> listadoMarcaCaldera= dao.queryForEq(Marca.ID_MARCA, id);
        if(listadoMarcaCaldera.isEmpty()) {
            return null;
        }else{
            return listadoMarcaCaldera.get(0).getNombre_marca();
        }
    }

    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
    public static void actualizarMarca(Context context, int id_marca, String nombre_marca ) throws SQLException {
        cargarDao(context);
        UpdateBuilder<Marca, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Marca.ID_MARCA,id_marca);
        updateBuilder.updateColumnValue(Marca.ID_MARCA,id_marca);
        updateBuilder.updateColumnValue(Marca.NOMBRE_MARCA,nombre_marca);
        updateBuilder.update();
    }*/


}
