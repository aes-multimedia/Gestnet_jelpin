package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;
import android.database.SQLException;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.ManoObra;

import java.util.List;

public class ManoObraDAO extends DBHelperMOS {
    static Dao<ManoObra, Integer> dao;

    public static void cargarDao(Context context) throws SQLException, java.sql.SQLException {
        dao = getHelper(context).getManoObraDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newManoObra(Context context,int id_mano, String concepto, double precio, String coste )throws java.sql.SQLException {
        ManoObra d = montarManoObra( id_mano, concepto,precio,coste);
        return crearManoObra(d,context);
    }
    public static boolean crearManoObra(ManoObra d, Context context) throws java.sql.SQLException {
        try {
            cargarDao(context);
            dao.create(d);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static ManoObra montarManoObra(   int id_mano, String concepto, double precio, String coste ) {
        ManoObra d =new ManoObra(id_mano, concepto,precio,coste);
        return d;
    }

    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodasLasManoDeObra(Context context) throws SQLException {
        try {
            cargarDao(context);
            DeleteBuilder<ManoObra, Integer> deleteBuilder = dao.deleteBuilder();
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
    }
    public static void borrarManoObra(Context context, int id) throws SQLException {
        try { cargarDao(context);
            DeleteBuilder<ManoObra, Integer> deleteBuilder = dao.deleteBuilder();

            deleteBuilder.where().eq(ManoObra.ID_MANO, id);
            deleteBuilder.delete();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }

    }

    //__________FUNCIONES DE BUSQUEDA______________________//


    public static List<ManoObra> buscarTodasLasManoDeObra(Context context) throws SQLException {
        try {
            cargarDao(context);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        List<ManoObra> listadoManoObra= null;
        try {
            listadoManoObra = dao.queryForAll();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoManoObra.isEmpty()) {
            return null;
        }else{
            return listadoManoObra;
        }
    }
    public static ManoObra buscarManoObraPorId(Context context, int id) throws SQLException, java.sql.SQLException {
        cargarDao(context);
        List<ManoObra> listadoManoObra= null;
        try {
            listadoManoObra = dao.queryForEq(ManoObra.ID_MANO, id);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoManoObra.isEmpty()) {
            return null;
        }else{
            return listadoManoObra.get(0);
        }
    }
    public static double buscarPrecioManoObraPorNombre(Context context, String s) throws java.sql.SQLException {
        cargarDao(context);
        List<ManoObra> listadoManoObra= null;
        try {
            listadoManoObra = dao.queryForEq(ManoObra.CONCEPTO, s);
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        if(listadoManoObra.isEmpty()) {
            return -1;
        }else{
            return listadoManoObra.get(0).getPrecio();
        }

    }

    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
    public static void actualizarManoObra(Context context, int id_mano, String concepto, double precio, String coste ) throws java.sql.SQLException {
        cargarDao(context);
        UpdateBuilder<ManoObra, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(ManoObra.ID_MANO,id_mano);
        updateBuilder.updateColumnValue(ManoObra.CONCEPTO,concepto);
        updateBuilder.updateColumnValue(ManoObra.PRECIO,precio);
        updateBuilder.updateColumnValue(ManoObra.COSTE,coste);
        updateBuilder.update();
    }


}