package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;

import java.sql.SQLException;
import java.util.List;


public class ArticuloParteDAO  extends DBHelperMOS {
    static Dao<ArticuloParte, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getArticuloParteDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newArticuloParte(Context context, int fk_articulo,int fk_parte) {
        ArticuloParte a = montarArticuloParte(fk_articulo,fk_parte);
        return crearArticuloParte(a,context);
    }



    public static boolean crearArticuloParte(ArticuloParte a,Context context) {
        try {
            cargarDao(context);
            dao.create(a);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static ArticuloParte  montarArticuloParte(int fk_articulo,int fk_parte) {
        ArticuloParte a =new ArticuloParte(fk_articulo,fk_parte);
        return a;
    }





    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosArticuloParte(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<ArticuloParte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarArticuloParteID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<ArticuloParte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(ArticuloParte.ID, id);
        deleteBuilder.delete();
    }

    public static void borrarArticuloPartePorFkArticulo(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<ArticuloParte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(ArticuloParte.FK_ARTICULO, id);
        deleteBuilder.delete();
    }
    public static void borrarArticuloPartePoFkParte(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<ArticuloParte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(ArticuloParte.FK_PARTE, id);
        deleteBuilder.delete();
    }
//__________FUNCIONES DE BUSQUEDA______________________//


    public static List<ArticuloParte> buscarTodosLosArticulosParte(Context context) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForAll();
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte;
        }
    }
    public static ArticuloParte buscarArticuloPartePorID(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForEq(ArticuloParte.ID, id);
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte.get(0);
        }
    }

    public static int numeroArticuloParte(Context context) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForAll();
        if(listadoArticuloParte.isEmpty()) {
            return 0;
        }else{
            return listadoArticuloParte.size();
        }
    }

public static ArticuloParte buscarArticuloPartePorFkArticulo(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForEq(ArticuloParte.FK_ARTICULO, id);
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte.get(0);
        }
    }

public static ArticuloParte buscarArticuloPartePorFkParte(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForEq(ArticuloParte.FK_PARTE, id);
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte.get(0);
        }
    }

    public static List<ArticuloParte> buscarTodosLosArticuloPartePorFkParte(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForEq(ArticuloParte.FK_PARTE, id);
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte;
        }
    }

    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//



    }




