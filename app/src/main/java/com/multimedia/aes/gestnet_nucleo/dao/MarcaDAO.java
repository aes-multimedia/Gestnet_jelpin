package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import java.sql.SQLException;
import java.util.List;


public class MarcaDAO extends DBHelperMOS {
    static Dao<Marca, Integer> dao;

//__________FUNCIONES DE CREACIÓN________________________//

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getMarcaDAO();
    }


    public static boolean newMarca(Context context,int id_marca, String nombre_marca){
        Marca m = montarMarca(id_marca,   nombre_marca);
        return crearMarca(m, context);
    }

    public static boolean crearMarca(Marca m, Context context) {
        try {
            cargarDao(context);
            dao.create(m);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

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
    }


}
