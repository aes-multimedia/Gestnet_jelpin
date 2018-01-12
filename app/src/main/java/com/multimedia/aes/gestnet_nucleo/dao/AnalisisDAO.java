package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;

import java.sql.SQLException;
import java.util.List;

public class AnalisisDAO extends DBHelperMOS {
	static Dao<Analisis, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getAnalisisDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newAnalisis(Context context,int fk_maquina, int fk_parte, String c0_maquina, String temperatura_gases_combustion,
									  String temperatura_ambiente_local, String rendimiento_aparato, String co_corregido,
									  String co_ambiente, String co2_ambiente, String tiro, String co2, String o2, String lambda,
									  int bCampana,String nombre_medicion,int bMaxima_potencia,int bMinima_potencia,int usar_impresion) {
		Analisis m = montarAnalisis(fk_maquina,  fk_parte,  c0_maquina,  temperatura_gases_combustion,
				temperatura_ambiente_local,  rendimiento_aparato,  co_corregido,
				co_ambiente,  co2_ambiente,  tiro,  co2,  o2,  lambda,
				bCampana, nombre_medicion, bMaxima_potencia, bMinima_potencia, usar_impresion);
		return crearAnalisis(m,context);
	}
	public static Analisis newAnalisisRet(Context context,int fk_maquina, int fk_parte, String c0_maquina, String temperatura_gases_combustion,
										  String temperatura_ambiente_local, String rendimiento_aparato, String co_corregido,
										  String co_ambiente, String co2_ambiente, String tiro, String co2, String o2, String lambda,
										  int bCampana,String nombre_medicion,int bMaxima_potencia,int bMinima_potencia,int usar_impresion) {
		Analisis m = montarAnalisis(fk_maquina,  fk_parte,  c0_maquina,  temperatura_gases_combustion,
				temperatura_ambiente_local,  rendimiento_aparato,  co_corregido,
				co_ambiente,  co2_ambiente,  tiro,  co2,  o2,  lambda,
				bCampana, nombre_medicion, bMaxima_potencia, bMinima_potencia, usar_impresion);
		return crearAnalisisRet(m,context);
	}
	public static boolean crearAnalisis(Analisis m, Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Analisis crearAnalisisRet(Analisis m, Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Analisis montarAnalisis(int fk_maquina, int fk_parte, String c0_maquina, String temperatura_gases_combustion,
										  String temperatura_ambiente_local, String rendimiento_aparato, String co_corregido,
										  String co_ambiente, String co2_ambiente, String tiro, String co2, String o2, String lambda,
										  int bCampana,String nombre_medicion,int bMaxima_potencia,int bMinima_potencia,int usar_impresion) {
		Analisis m =new Analisis(fk_maquina,  fk_parte,  c0_maquina,  temperatura_gases_combustion,
				temperatura_ambiente_local,  rendimiento_aparato,  co_corregido,
				co_ambiente,  co2_ambiente,  tiro,  co2,  o2,  lambda,
				bCampana, nombre_medicion, bMaxima_potencia, bMinima_potencia, usar_impresion);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasAnalisis(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Analisis, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarAnalisisId(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Analisis, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Analisis.ID_ANALISIS, id);
		deleteBuilder.delete();
	}
	public static void borrarAnalisisIdParte(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Analisis, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Analisis.FK_PARTE, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Analisis> buscarTodosLosAnalisis(Context context) throws SQLException {
		cargarDao(context);
		List<Analisis> listadoAnalisis= dao.queryForAll();
		if(listadoAnalisis.isEmpty()) {
			return null;
		}else{
			return listadoAnalisis;
		}
	}
	public static Analisis buscarAnalisisPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Analisis> listadoAnalisis= dao.queryForEq(Analisis.ID_ANALISIS, id);
		if(listadoAnalisis.isEmpty()){
			return  null;
		}else{
			return listadoAnalisis.get(0);
		}
	}
	public static List<Analisis> buscarAnalisisPorFkMaquina(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Analisis> listadoAnalisis= dao.queryForEq(Analisis.FK_MAQUINA, id);
		if(listadoAnalisis.isEmpty()){
			return  null;
		}else{
			return listadoAnalisis;
		}
	}
	public static List<Analisis> buscarAnalisisPorIdMantenimiento(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Analisis> listadoAnalisis= dao.queryForEq(Analisis.FK_PARTE, id);
		if(listadoAnalisis.isEmpty()){
			return  null;
		}else{
			return listadoAnalisis;
		}
	}
	public static int buscarIdAnalisisPorNombre(Context context, String nombre,int fk_maquina) throws SQLException {
		cargarDao(context);
		//List<Analisis> listadoAnalisis= dao.queryForEq(Analisis.NOMBRE_MEDICION, nombre);
		List<Analisis> listadoAnalisis= dao.queryBuilder().where().eq(Analisis.NOMBRE_MEDICION,nombre).and().eq(Analisis.FK_MAQUINA,fk_maquina).query();
		if(listadoAnalisis.isEmpty()) {
			return 0;
		}else{
			return listadoAnalisis.get(0).getId_analisis();
		}
	}
	public static Analisis buscarAnalisisFinalizacionPorFkMaquina(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Analisis> listadoAnalisis= dao.queryForEq(Analisis.FK_MAQUINA, id);
		Analisis a = null;
		for (int i = 0; i < listadoAnalisis.size(); i++) {
			if (listadoAnalisis.get(i).getbUsar_finalizacion()==1){
				a = listadoAnalisis.get(i);
			}
		}
		return a;
	}
	//__________FUNCIONES DE ACTUALIZADO______________________//
	public static void actualizarUsarFinalizacion(Context context, int i, int id_analisis) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Analisis, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Analisis.ID_ANALISIS,id_analisis);
		updateBuilder.updateColumnValue(Analisis.BUSAR_FINALIZACION,i);
		updateBuilder.update();
	}

	public static void actualizarAnalisis(Context context,int id_analisis, int fk_maquina, int fk_parte, String c0_maquina, String temperatura_gases_combustion,
										  String temperatura_ambiente_local, String rendimiento_aparato, String co_corregido,
										  String co_ambiente, String co2_ambiente, String tiro, String co2, String o2, String lambda,
										  int bCampana,String nombre_medicion,int bMaxima_potencia,int bMinima_potencia,int busar_finalizacion) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Analisis, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Analisis.ID_ANALISIS,id_analisis);
		updateBuilder.updateColumnValue(Analisis.FK_MAQUINA,fk_maquina );
		updateBuilder.updateColumnValue(Analisis.FK_PARTE,fk_parte );
		updateBuilder.updateColumnValue(Analisis.C0_MAQUINA,c0_maquina );
		updateBuilder.updateColumnValue(Analisis.TEMPERATURA_GASES_COMBUSTION,temperatura_gases_combustion );
		updateBuilder.updateColumnValue(Analisis.TEMPERATURA_AMBIENTE_LOCAL,temperatura_ambiente_local );
		updateBuilder.updateColumnValue(Analisis.RENDIMIENTO_APARATO,rendimiento_aparato );
		updateBuilder.updateColumnValue(Analisis.CO_CORREGIDO,co_corregido );
		updateBuilder.updateColumnValue(Analisis.CO_AMBIENTE,co_ambiente );
		updateBuilder.updateColumnValue(Analisis.CO2_AMBIENTE,co2_ambiente );
		updateBuilder.updateColumnValue(Analisis.TIRO,tiro );
		updateBuilder.updateColumnValue(Analisis.CO2,co2 );
		updateBuilder.updateColumnValue(Analisis.O2,o2 );
		updateBuilder.updateColumnValue(Analisis.LAMBDA,lambda );
		updateBuilder.updateColumnValue(Analisis.BCAMPANA,bCampana );
		updateBuilder.updateColumnValue(Analisis.NOMBRE_MEDICION,nombre_medicion );
		updateBuilder.updateColumnValue(Analisis.BMAXIMA_POTENCIA,bMaxima_potencia );
		updateBuilder.updateColumnValue(Analisis.BMINIMA_POTENCIA,bMinima_potencia );
		updateBuilder.updateColumnValue(Analisis.BUSAR_FINALIZACION,busar_finalizacion );
		updateBuilder.update();
	}
}
