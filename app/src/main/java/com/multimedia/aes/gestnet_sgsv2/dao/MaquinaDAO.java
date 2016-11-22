package com.multimedia.aes.gestnet_sgsv2.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_sgsv2.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;

import java.sql.SQLException;
import java.util.List;

public class MaquinaDAO extends DBHelperMOS {
	static Dao<Maquina, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMaquinaDAO();
	}

	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMaquina(Context context,int fk_maquina,int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
												  String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
												  String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
												  String temperatura_max_acs, String caudal_acs, String potencia_util,
												  String temperatura_gases_combustion, String temperatura_ambiente_local,
												  String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
												  String rendimiento_aparato, String co_corregido, String co_ambiente,String co2_ambiente, String tiro, String co2, String o2, String lambda,int bPrincipal) {
		Maquina m = montarMaquina(fk_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
				modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
				puesta_marcha_maquina, codigo_maquina, c0_maquina,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_gases_combustion, temperatura_ambiente_local,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
				rendimiento_aparato, co_corregido, co_ambiente,co2_ambiente, tiro, co2, o2, lambda,bPrincipal);
		return crearMaquina(m,context);
	}
	public static Maquina newMaquinaRet(Context context,int fk_maquina,int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
									 String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
									 String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
									 String temperatura_max_acs, String caudal_acs, String potencia_util,
									 String temperatura_gases_combustion, String temperatura_ambiente_local,
									 String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
									 String rendimiento_aparato, String co_corregido, String co_ambiente,String co2_ambiente, String tiro, String co2, String o2, String lambda,int bPrincipal) {
		Maquina m = montarMaquina(fk_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
				modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
				puesta_marcha_maquina, codigo_maquina, c0_maquina,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_gases_combustion, temperatura_ambiente_local,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
				rendimiento_aparato, co_corregido, co_ambiente,co2_ambiente, tiro, co2, o2, lambda,bPrincipal);
		return crearMaquinaRet(m,context);
	}
	public static boolean crearMaquina(Maquina m, Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Maquina crearMaquinaRet(Maquina m, Context context) {
		try {
			cargarDao(context);
			dao.create(m);
			return m;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	public static Maquina montarMaquina(int fk_maquina, int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
													 String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
													 String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
													 String temperatura_max_acs, String caudal_acs, String potencia_util,
													 String temperatura_gases_combustion, String temperatura_ambiente_local,
													 String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
													 String rendimiento_aparato, String co_corregido, String co_ambiente,String co2_ambiente, String tiro, String co2, String o2, String lambda, int bPrincipal) {
		Maquina m =new Maquina(fk_maquina,fk_parte, fk_tipo_maquina, fk_marca_maquina,
				modelo_maquina, fk_potencia_maquina, fk_uso_maquina,
				puesta_marcha_maquina, codigo_maquina, c0_maquina,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_gases_combustion, temperatura_ambiente_local,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,
				rendimiento_aparato, co_corregido, co_ambiente,co2_ambiente, tiro, co2, o2, lambda,bPrincipal);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarMaquinaID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Maquina.ID_MAQUINA, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Maquina> buscarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForAll();
		if(listadoMaquina.isEmpty()) {
			return null;
		}else{
			return listadoMaquina;
		}
	}
	public static Maquina buscarMaquinaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForEq(Maquina.ID_MAQUINA, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina.get(0);
		}
	}
	public static List<Maquina> buscarMaquinaPorIdMantenimiento(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForEq(Maquina.FK_PARTE, id);
		if(listadoMaquina.isEmpty()){
			return  null;
		}else{
			return listadoMaquina;
		}
	}
	public static Maquina buscarMaquinaPorbprincipal(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquina= dao.queryForEq(Maquina.FK_PARTE, id);
		Maquina m=null;
		for (int i = 0; i < listadoMaquina.size(); i++) {
			if (listadoMaquina.get(i).getbPrincipal()==1){
				m=listadoMaquina.get(i);
			}
		}
		if(m==null){
			return  null;
		}else{
			return m;
		}
	}
	//__________FUNCIONES DE ACTUALIZADO______________________//

	public static void actualizarMaquina(Context context,int id_maquina,int fk_maquina,int fk_parte, int fk_tipo_maquina, int fk_marca_maquina,
										 String modelo_maquina, int fk_potencia_maquina, int fk_uso_maquina,
										 String puesta_marcha_maquina, String codigo_maquina, String c0_maquina,
										 String temperatura_max_acs, String caudal_acs, String potencia_util,
										 String temperatura_gases_combustion, String temperatura_ambiente_local,
										 String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
										 String rendimiento_aparato, String co_corregido, String co_ambiente, String tiro, String co2, String o2, String lambda,int bPrincipal) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Maquina, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Maquina.ID_MAQUINA,id_maquina);
		updateBuilder.updateColumnValue(Maquina.FK_MAQUINA,fk_maquina );
		updateBuilder.updateColumnValue(Maquina.FK_PARTE,fk_parte );
		updateBuilder.updateColumnValue(Maquina.FK_TIPO_MAQUINA,fk_tipo_maquina );
		updateBuilder.updateColumnValue(Maquina.FK_MARCA_MAQUINA,fk_marca_maquina );
		updateBuilder.updateColumnValue(Maquina.MODELO_MAQUINA,modelo_maquina );
		updateBuilder.updateColumnValue(Maquina.FK_POTENCIA_MAQUINA,fk_potencia_maquina );
		updateBuilder.updateColumnValue(Maquina.FK_USO_MAQUINA,fk_uso_maquina );
		updateBuilder.updateColumnValue(Maquina.PUESTA_MARCHA_MAQUINA,puesta_marcha_maquina );
		updateBuilder.updateColumnValue(Maquina.CODIGO_MAQUINA,codigo_maquina );
		updateBuilder.updateColumnValue(Maquina.C0_MAQUINA,c0_maquina );
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_MAX_ACS,temperatura_max_acs );
		updateBuilder.updateColumnValue(Maquina.CAUDAL_ACS,caudal_acs );
		updateBuilder.updateColumnValue(Maquina.POTENCIA_UTIL,potencia_util );
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_GASES_COMBUSTION,temperatura_gases_combustion );
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AMBIENTE_LOCAL,temperatura_ambiente_local );
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA,temperatura_agua_generador_calor_entrada );
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA,temperatura_agua_generador_calor_salida );
		updateBuilder.updateColumnValue(Maquina.RENDIMIENTO_APARATO,rendimiento_aparato );
		updateBuilder.updateColumnValue(Maquina.CO_CORREGIDO,co_corregido );
		updateBuilder.updateColumnValue(Maquina.CO_AMBIENTE,co_ambiente );
		updateBuilder.updateColumnValue(Maquina.TIRO,tiro );
		updateBuilder.updateColumnValue(Maquina.CO2,co2 );
		updateBuilder.updateColumnValue(Maquina.O2,o2 );
		updateBuilder.updateColumnValue(Maquina.LAMBDA,lambda );
		updateBuilder.updateColumnValue(Maquina.BPRINCIPAL,bPrincipal );
		updateBuilder.update();
	}
}
