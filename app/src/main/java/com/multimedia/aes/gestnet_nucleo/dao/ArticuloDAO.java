package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by acp on 30/10/2017.
 */

public class ArticuloDAO extends DBHelperMOS {
    static Dao<Articulo, Integer> dao;

    public static void cargarDao(Context context) throws SQLException {
        dao = getHelper(context).getArticuloDAO();
    }


    //__________FUNCIONES DE CREACIÓN________________________//

    public static boolean newArticulo(Context context,int id_articulo, String nombre_articulo,int stock, String referencia, String referencia_aux, String familia,
                                      String marca, String modelo, int proveedor, double iva, double tarifa, double descuento, double coste, String ean,int imagen) {
        Articulo a = montarArticulo(id_articulo,nombre_articulo,stock,referencia, referencia_aux, familia, marca,  modelo, proveedor, iva, tarifa, descuento, coste, ean,imagen);
        return crearArticulo(a,context);
    }

    public static boolean newArticuloP(Context context,int id_articulo, String nombre_articulo,int stock, double coste) {
        Articulo a = montarArticuloP(id_articulo,nombre_articulo,stock, coste);
        return crearArticulo(a,context);
    }

    public static boolean crearArticulo(Articulo a,Context context) {
        try {
            cargarDao(context);
            dao.create(a);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    public static Articulo  montarArticulo(int id_articulo, String nombre_articulo, int stock, String referencia, String referencia_aux, String familia,
                                           String marca, String modelo, int proveedor, double iva, double tarifa, double descuento, double coste, String ean, int imagen) {
        Articulo a =new Articulo(id_articulo,nombre_articulo,stock,referencia, referencia_aux, familia, marca,  modelo, proveedor, iva, tarifa, descuento, coste, ean,imagen);
        return a;
    }


    public static Articulo  montarArticuloP(int id_articulo, String nombre_articulo,int stock, double coste) {
        Articulo a =new Articulo(id_articulo,nombre_articulo,stock, coste);
        return a;
    }


    //__________FUNCIONES DE BORRADO______________________//

    public static void borrarTodosLosArticulos(Context context) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Articulo, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.delete();
    }
    public static void borrarArticuloPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        DeleteBuilder<Articulo, Integer> deleteBuilder = dao.deleteBuilder();
        deleteBuilder.where().eq(Articulo.ID_ARTICULO, id);
        deleteBuilder.delete();
    }
//__________FUNCIONES DE BUSQUEDA______________________//


    public static List<Articulo> buscarTodosLosArticulos(Context context) throws SQLException {
        cargarDao(context);
        List<Articulo> listadoArticulo= dao.queryForAll();
        if(listadoArticulo.isEmpty()) {
            return null;
        }else{
            return listadoArticulo;
        }
    }

    public static List<Articulo> filtrarArticulosPorNombre(Context context,String cadena) throws SQLException {
        cargarDao(context);
        List<Articulo> listadoArticulo= dao.queryBuilder().where().like(Articulo.NOMBRE_ARTICULO,"%"+cadena+"%").query();
       /* for (int i = 0; i < listadoArticulo.size(); i++) {
            if(listadoArticulo.get(i).getNombre_articulo().indexOf(cadena)!=1)listadoArticulo.remove(i);
        }*/
        if(listadoArticulo.isEmpty()) {
            return null;
        }else{
            return listadoArticulo;
        }
    }

    public static Articulo buscarArticuloPorID(Context context, int id) throws SQLException {
        cargarDao(context);
        List<Articulo> listadoArticulo= dao.queryForEq(Articulo.ID_ARTICULO, id);
        if(listadoArticulo.isEmpty()) {
            return null;
        }else{
            return listadoArticulo.get(0);
        }
    }


    //____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


    public static void actualizarArticulo(Context context, Articulo articulo ) throws SQLException {
        int id_articulo=articulo.getId_articulo();
        String nombre_articulo=articulo.getNombre_articulo();
        int stock=articulo.getStock();
        String referencia=articulo.getReferencia();
        String referencia_aux=articulo.getReferencia_aux();
        String familia=articulo.getFamilia();
        String marca=articulo.getMarca();
        String modelo=articulo.getModelo();
        int proveedor=articulo.getProveedor();
        double iva=articulo.getIva();
        double tarifa=articulo.getTarifa();
        double descuento=articulo.getDescuento();
        double coste=articulo.getCoste();
        String ean=articulo.getEan();
        int imagen=articulo.getImagen();



        cargarDao(context);
        UpdateBuilder<Articulo, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Articulo.ID_ARTICULO,id_articulo);
        updateBuilder.updateColumnValue(Articulo.NOMBRE_ARTICULO,nombre_articulo);
        updateBuilder.updateColumnValue(Articulo.STOCK, stock);
        updateBuilder.updateColumnValue(Articulo.REFERENCIA, referencia);
        updateBuilder.updateColumnValue(Articulo.REFERENCIA_AUX, referencia_aux);
        updateBuilder.updateColumnValue(Articulo.FAMILIA, familia);
        updateBuilder.updateColumnValue(Articulo.MARCA, marca);
        updateBuilder.updateColumnValue(Articulo.MODELO, modelo);
        updateBuilder.updateColumnValue(Articulo.PROVEEDOR, proveedor);
        updateBuilder.updateColumnValue(Articulo.IVA, iva);
        updateBuilder.updateColumnValue(Articulo.TARIFA, tarifa);
        updateBuilder.updateColumnValue(Articulo.DESCUENTO, descuento);
        updateBuilder.updateColumnValue(Articulo.COSTE, coste);
        updateBuilder.updateColumnValue(Articulo.EAN, ean);
        updateBuilder.updateColumnValue(Articulo.IMAGEN, imagen);
        updateBuilder.update();
    }


    public static void actualizarArticuloP(Context context, int id_articulo, String nombre_articulo, int stock, double coste)throws SQLException {
        cargarDao(context);
        UpdateBuilder<Articulo, Integer> updateBuilder = dao.updateBuilder();
        updateBuilder.where().eq(Articulo.ID_ARTICULO,id_articulo);
        updateBuilder.updateColumnValue(Articulo.NOMBRE_ARTICULO,nombre_articulo);
        updateBuilder.updateColumnValue(Articulo.STOCK, stock);
        updateBuilder.updateColumnValue(Articulo.COSTE, coste);
        updateBuilder.update();
    }

    }

