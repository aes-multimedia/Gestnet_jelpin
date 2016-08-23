package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Imagenes;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;
import java.util.List;

public class ImagenesDAO extends DBHelperMOS {
	static Dao<Imagenes, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getImagenDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newImagen(Context context, String nombre_imagen, String ruta_imagen, int fk_parte) {
		Imagenes i = montarImagen(nombre_imagen, ruta_imagen, fk_parte);
		return crearImagen(i,context);
	}
	public static boolean crearImagen(Imagenes i,Context context) {
		try {
			cargarDao(context);
			dao.create(i);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Imagenes montarImagen(String nombre_imagen,String ruta_imagen, int fk_parte) {
		Imagenes i =new Imagenes(nombre_imagen, ruta_imagen, fk_parte);
		return i;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasImagenes(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Imagenes, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarImagenPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Imagenes, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Imagenes.ID_IMAGEN, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Imagenes> buscarTodasLasImagenes(Context context) throws SQLException {
		cargarDao(context);
		List<Imagenes> listadoImagenes= dao.queryForAll();
		if(listadoImagenes.isEmpty()) {
			return null;
		}else{
			return listadoImagenes;
		}
	}
	public static Imagenes buscarImagenPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Imagenes> listadoImagenes= dao.queryForEq(Imagenes.ID_IMAGEN, id);
		if(listadoImagenes.isEmpty()){
			return  null;
		}else{
			return listadoImagenes.get(0);
		}
	}

	public static List<Imagenes> buscarImagenPorFk_parte(Context context, int fk_parte) throws SQLException {
		cargarDao(context);
		List<Imagenes> listadoImagenes= dao.queryForEq(Imagenes.FK_PARTE, fk_parte);
		if(listadoImagenes.isEmpty()){
			return  null;
		}else{
			return listadoImagenes;
		}
	}

	public static int buscarUltimoIdImagen(Context context) throws SQLException {
		cargarDao(context);
		List<Imagenes> listadoImagenes= dao.queryForAll();
		int size = listadoImagenes.size();
		if(listadoImagenes.isEmpty()){
			return 0;
		}else{
			listadoImagenes.get(size-1);
			return listadoImagenes.get(0).getId_imagen();
		}
	}
}
