package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;

import java.sql.SQLException;
import java.util.List;

public class MaquinaDAO extends DBHelperMOS {
	static Dao<Maquina, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getMaquinaDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newMaquina(Context context, int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String fk_tipo_combustion,
									 int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
									 int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
									 String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
									 String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
									 String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
									 String garantia_extendida, String factura_compra, String refrigerante,
									 boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal, String situacion,
									 String temperatura_max_acs, String caudal_acs, String potencia_util,
									 String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
									 String combustible_txt,String nombre_contr_man,String documento_modelo,
									 String GamaTxt, String TipoGamaTxt) {
		Maquina m = montarMaquina(fk_maquina,fk_parte,   fk_direccion,   fk_marca,   fk_tipo_combustion,
				fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion,
				fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama,
				fecha_creacion,   modelo,   num_serie,   num_producto,   aparato,
				puesta_marcha,   fecha_compra,   fecha_fin_garantia,
				mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra,
				garantia_extendida,   factura_compra,   refrigerante,
				bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,combustible_txt,
				nombre_contr_man,documento_modelo, GamaTxt, TipoGamaTxt);
		return crearMaquina(m, context);
	}
	public static Maquina newMaquinaRet(Context context, int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String fk_tipo_combustion,
										int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
										int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
										String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
										String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
										String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
										String garantia_extendida, String factura_compra, String refrigerante,
										boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal, String situacion,
										String temperatura_max_acs, String caudal_acs, String potencia_util,
										String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,String combustible_txt,String nombre_contr_man,String documento_modelo) {
		Maquina m = montarMaquina(fk_maquina,fk_parte,   fk_direccion,   fk_marca,   fk_tipo_combustion,
				fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion,
				fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama,
				fecha_creacion,   modelo,   num_serie,   num_producto,   aparato,
				puesta_marcha,   fecha_compra,   fecha_fin_garantia,
				mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra,
				garantia_extendida,   factura_compra,   refrigerante,
				bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,combustible_txt,nombre_contr_man,documento_modelo, "", "");
		return crearMaquinaRet(m, context);
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

	public static Maquina montarMaquina(int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String fk_tipo_combustion,
										int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
										int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
										String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
										String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
										String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
										String garantia_extendida, String factura_compra, String refrigerante,
										boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal, String situacion,
										String temperatura_max_acs, String caudal_acs, String potencia_util,
										String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida,
										String combustible_txt,String nombre_contr_man,String documento_modelo,
										String GamaTxt, String tipoGamaTxt) {
		Maquina m = new Maquina(fk_maquina,fk_parte,   fk_direccion,   fk_marca,   fk_tipo_combustion,
				fk_protocolo,   fk_instalador,   fk_remoto_central,   fk_tipo,   fk_instalacion,
				fk_estado,   fk_contrato_mantenimiento,   fk_gama,   fk_tipo_gama,
				fecha_creacion,   modelo,   num_serie,   num_producto,   aparato,
				puesta_marcha,   fecha_compra,   fecha_fin_garantia,
				mantenimiento_anual,   observaciones,   ubicacion,   tienda_compra,
				garantia_extendida,   factura_compra,   refrigerante,
				bEsInstalacion,   nombre_instalacion,   en_propiedad,   esPrincipal, situacion,
				temperatura_max_acs, caudal_acs, potencia_util,
				temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida,combustible_txt,nombre_contr_man,documento_modelo, GamaTxt, tipoGamaTxt);
		return m;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}

	public static void borrarMaquinaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Maquina.ID_MAQUINA, id);
		deleteBuilder.delete();
	}

	public static void borrarMaquinaPorFkDireccion(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Maquina, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Maquina.FK_DIRECCION, id);
		deleteBuilder.delete();

	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Maquina> buscarTodasLasMaquinas(Context context) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForAll();
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas;
		}
	}

	public static Maquina buscarMaquinaPorFkMaquina(Context context, int fk) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForEq(Maquina.FK_MAQUINA, fk);
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas.get(0);
		}
	}
	public static Maquina buscarMaquinaPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForEq(Maquina.ID_MAQUINA, id);
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas.get(0);
		}
	}
	public static List<Maquina> buscarMaquinaPorFkParte(Context context, int fk) throws SQLException {
		cargarDao(context);
		List<Maquina> listadoMaquinas = dao.queryForEq(Maquina.FK_PARTE, fk);
		if (listadoMaquinas.isEmpty()) {
			return null;
		} else {
			return listadoMaquinas;
		}
	}



	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
	public static void actualizarMaquina(Context context, int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String fk_tipo_combustion,
										 int fk_protocolo, int fk_instalador, int fk_remoto_central, int fk_tipo, int fk_instalacion,
										 int fk_estado, int fk_contrato_mantenimiento, int fk_gama, int fk_tipo_gama,
										 String fecha_creacion, String modelo, String num_serie, String num_producto, String aparato,
										 String puesta_marcha, String fecha_compra, String fecha_fin_garantia,
										 String mantenimiento_anual, String observaciones, String ubicacion, String tienda_compra,
										 String garantia_extendida, String factura_compra, String refrigerante,
										 boolean bEsInstalacion, String nombre_instalacion, String en_propiedad, String esPrincipal, String situacion,
										 String temperatura_max_acs, String caudal_acs, String potencia_util, String temperatura_agua_generador_calor_entrada,
										 String temperatura_agua_generador_calor_salida, String GamaTXT, String TipoGamaTXT) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Maquina, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Maquina.FK_MAQUINA,fk_maquina);
		updateBuilder.updateColumnValue(Maquina.FK_MAQUINA,fk_maquina);
		updateBuilder.updateColumnValue(Maquina.FK_PARTE,fk_parte);
		updateBuilder.updateColumnValue(Maquina.FK_DIRECCION,fk_direccion);
		updateBuilder.updateColumnValue(Maquina.FK_MARCA,fk_marca);
		updateBuilder.updateColumnValue(Maquina.TIPO_COMBUSTION,fk_tipo_combustion);
		updateBuilder.updateColumnValue(Maquina.FK_PROTOCOLO,fk_protocolo);
		updateBuilder.updateColumnValue(Maquina.FK_INSTALADOR,fk_instalador);
		updateBuilder.updateColumnValue(Maquina.FK_REMOTO_CENTRAL,fk_remoto_central);
		updateBuilder.updateColumnValue(Maquina.FK_TIPO,fk_tipo);
		updateBuilder.updateColumnValue(Maquina.FK_INSTALACION,fk_instalacion);
		updateBuilder.updateColumnValue(Maquina.FK_ESTADO,fk_estado);
		updateBuilder.updateColumnValue(Maquina.FK_CONTRATO_MANTENIMIENTO,fk_contrato_mantenimiento);
		updateBuilder.updateColumnValue(Maquina.FK_GAMA,fk_gama);
		updateBuilder.updateColumnValue(Maquina.FK_TIPO_GAMA,fk_tipo_gama);
		updateBuilder.updateColumnValue(Maquina.FECHA_CREACION,fecha_creacion);
		updateBuilder.updateColumnValue(Maquina.MODELO,modelo);
		updateBuilder.updateColumnValue(Maquina.NUM_SERIE,num_serie);
		updateBuilder.updateColumnValue(Maquina.NUM_PRODUCTO,num_producto);
		updateBuilder.updateColumnValue(Maquina.APARATO,aparato);
		updateBuilder.updateColumnValue(Maquina.PUESTA_MARCHA,puesta_marcha);
		updateBuilder.updateColumnValue(Maquina.FECHA_COMPRA,fecha_compra);
		updateBuilder.updateColumnValue(Maquina.FECHA_FIN_GARANTIA,fecha_fin_garantia);
		updateBuilder.updateColumnValue(Maquina.MANTENIMIENTO_ANUAL,mantenimiento_anual);
		updateBuilder.updateColumnValue(Maquina.OBSERVACIONES,observaciones);
		updateBuilder.updateColumnValue(Maquina.UBICACION,ubicacion);
		updateBuilder.updateColumnValue(Maquina.TIENDA_COMPRA,tienda_compra);
		updateBuilder.updateColumnValue(Maquina.GARANTIA_EXTENDIDA,garantia_extendida);
		updateBuilder.updateColumnValue(Maquina.FACTURA_COMPRA,factura_compra);
		updateBuilder.updateColumnValue(Maquina.REFRIGERANTE,refrigerante);
		updateBuilder.updateColumnValue(Maquina.BESINSTALACION,bEsInstalacion);
		updateBuilder.updateColumnValue(Maquina.NOMBRE_INSTALACION,nombre_instalacion);
		updateBuilder.updateColumnValue(Maquina.EN_PROPIEDAD,en_propiedad);
		updateBuilder.updateColumnValue(Maquina.ESPRINCIPAL,esPrincipal);
		updateBuilder.updateColumnValue(Maquina.SITUACION,situacion);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_MAX_ACS,temperatura_max_acs);
		updateBuilder.updateColumnValue(Maquina.CAUDAL_ACS,caudal_acs);
		updateBuilder.updateColumnValue(Maquina.POTENCIA_UTIL,potencia_util);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA,temperatura_agua_generador_calor_entrada);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA,temperatura_agua_generador_calor_salida);
		updateBuilder.updateColumnValue(Maquina.GAMA_TXT,GamaTXT );
		updateBuilder.updateColumnValue(Maquina.TIPO_GAMA_TXT,TipoGamaTXT);

		updateBuilder.update();
	}
	public static void actualizaNumeroSerie(Context context, int id, String serial) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Maquina, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Maquina.ID_MAQUINA,id);
		updateBuilder.updateColumnValue(Maquina.NUM_SERIE,serial);
		updateBuilder.update();

	}

	public static void actualizarMaquina(Context context, int idMaquina,int fk_maquina, int fk_parte, int fk_direccion, int fk_marca, String modelo, String num_serie, String puesta_marcha, String temperatura_max_acs, String caudal_acs, String potencia_util, String temperatura_agua_generador_calor_entrada, String temperatura_agua_generador_calor_salida, String ubicacion) throws SQLException {

		cargarDao(context);
		UpdateBuilder<Maquina, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Maquina.ID_MAQUINA,idMaquina);
		updateBuilder.updateColumnValue(Maquina.FK_MAQUINA,fk_maquina);
		updateBuilder.updateColumnValue(Maquina.FK_PARTE,fk_parte);
		updateBuilder.updateColumnValue(Maquina.FK_DIRECCION,fk_direccion);
		updateBuilder.updateColumnValue(Maquina.FK_MARCA,fk_marca);
		updateBuilder.updateColumnValue(Maquina.MODELO,modelo);
		updateBuilder.updateColumnValue(Maquina.NUM_SERIE,num_serie);
		updateBuilder.updateColumnValue(Maquina.PUESTA_MARCHA,puesta_marcha);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_MAX_ACS,temperatura_max_acs);
		updateBuilder.updateColumnValue(Maquina.CAUDAL_ACS,caudal_acs);
		updateBuilder.updateColumnValue(Maquina.POTENCIA_UTIL,potencia_util);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_ENTRADA,temperatura_agua_generador_calor_entrada);
		updateBuilder.updateColumnValue(Maquina.TEMPERATURA_AGUA_GENERADOR_CALOR_SALIDA,temperatura_agua_generador_calor_salida);
		updateBuilder.updateColumnValue(Maquina.UBICACION,ubicacion);

		updateBuilder.update();


	}
}
