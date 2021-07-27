package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;

import java.sql.SQLException;
import java.util.List;


public class ArticuloParteDAO  extends DBHelperMOS {
    static Dao<ArticuloParte, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getArticuloParteDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newArticuloParte(Context context, int fk_articulo,int fk_parte,int fk_item_gestnet,double usados,boolean entregado,boolean garantia,double iva,double tarifa,double descuento,double coste) {
        ArticuloParte a = montarArticuloParte(fk_articulo,fk_parte,fk_item_gestnet,usados,entregado,garantia,iva,tarifa,descuento,coste);
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
    public static void actualizarFacturar(Context context, int id_articulo,boolean facturar) throws SQLException {

        cargarDao(context);
        UpdateBuilder<ArticuloParte, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(ArticuloParte.FK_ARTICULO,id_articulo);
        updateBuilder.updateColumnValue(ArticuloParte.FACTURAR,facturar);
        updateBuilder.update();


    }


    public static void actualizarPresupuestar(Context context, int id_articulo,boolean presupuestar) throws SQLException {

        cargarDao(context);
        UpdateBuilder<ArticuloParte, Integer> updateBuilder = dao.updateBuilder();

        updateBuilder.where().eq(ArticuloParte.FK_ARTICULO,id_articulo);
        updateBuilder.updateColumnValue(ArticuloParte.PRESUPUESTAR,presupuestar);
        updateBuilder.update();


    }

    public static ArticuloParte  montarArticuloParte(int fk_articulo,int fk_parte,int fk_item_gestnet,double usados,boolean entregado, boolean garantia,double iva,double tarifa,double descuento,double coste) {
        ArticuloParte a =new ArticuloParte(fk_articulo,fk_parte,fk_item_gestnet,usados,entregado,garantia,iva,tarifa,descuento,coste);
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
    public static void borrarArticuloPartePorFkArticuloFkParte(Context context, int idArticulo, int idParte) throws SQLException {
        cargarDao(context);
        DeleteBuilder<ArticuloParte, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(ArticuloParte.FK_ARTICULO, idArticulo).and().eq(ArticuloParte.FK_PARTE,idParte);
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
public static ArticuloParte buscarArticuloPartePorFkParteFkArticulo(Context context, int fk_articulo,int fk_parte) throws SQLException {
    cargarDao(context);
    List<ArticuloParte> listadoArticuloParte= dao.queryBuilder().where().eq(ArticuloParte.FK_ARTICULO,fk_articulo).and().eq(ArticuloParte.FK_PARTE,fk_parte).query();
    if(listadoArticuloParte.isEmpty()) {
        return null;
    }else{
        return listadoArticuloParte.get(0);
    }
}
    public static List<ArticuloParte> buscarArticuloParteFkParte(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte = dao.queryForEq(ArticuloParte.FK_PARTE, id);
        if(listadoArticuloParte.isEmpty()) {
            return null;
        }else{
            return listadoArticuloParte;
        }
    }
    public static Boolean existeArticuloPartePorIdItemGestnet(Context context, int id) throws SQLException {
        cargarDao(context);
        List<ArticuloParte> listadoArticuloParte= dao.queryForEq(ArticuloParte.FK_ITEM_GESTNET, id);
        if(listadoArticuloParte.isEmpty()) {
            return false;
        }else{
            return true;
        }
    }
    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


    public static void actualizarArticuloParte(Context context, int id_articulo_parte, double usados,boolean entregado, boolean garantia)throws SQLException {

        cargarDao(context);
        UpdateBuilder<ArticuloParte, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(ArticuloParte.ID,id_articulo_parte);
        updateBuilder.updateColumnValue(ArticuloParte.USADOS,usados);
        updateBuilder.updateColumnValue(ArticuloParte.ENTREGADO,entregado);
        updateBuilder.updateColumnValue(ArticuloParte.GARANTIA,garantia);
        updateBuilder.update();
    }
    public static void actualizarIdItemGestnet(Context context, int id_item_gestnet, int id_articulo_parte)throws SQLException {

        cargarDao(context);
        UpdateBuilder<ArticuloParte, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(ArticuloParte.ID,id_articulo_parte);
        updateBuilder.updateColumnValue(ArticuloParte.FK_ITEM_GESTNET,id_item_gestnet);
        updateBuilder.update();
    }
    }




