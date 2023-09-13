package com.multimedia.aes.gestnet_ssl.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_ssl.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_ssl.entidades.Configuracion;

import java.sql.SQLException;
import java.util.List;

public class ConfiguracionDAO extends DBHelperMOS {
	static Dao<Configuracion, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getConfiguracionDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newConfiguracion(Context context,int id_configuracion, boolean horarios, boolean operarios, boolean definiciones,
										   boolean equipos, boolean empresas, boolean marcas, boolean tipos_trabajo,
										   boolean tipos_presupuesto, boolean cuenta_bancaria, boolean fk_combustion,
										   boolean garantia, boolean pedir, boolean usar, boolean presupuestar,
										   boolean operacion_finalizacion, boolean precios_mano_obra, boolean formaPago,
										   boolean disp_servicio, boolean analisis_combustion, boolean puesta_marcha,
										   boolean servicio_urgencia, boolean kms_finalizacion, boolean traspaso_material,
										   boolean parte_usuario, boolean parte_averia, boolean parte_instalacion,
										   boolean parte_materiales, boolean parte_finalizacion, boolean parte_galeria,
										   boolean menu_asignacion, boolean menu_documentos, boolean menu_almacen,
										   boolean menu_cierre, boolean menu_ubicacion, boolean menu_datos_completos,
										   boolean menu_informar, boolean menu_datos_actualizados, boolean menu_presupuesto,
										   boolean requiere_firma, boolean usuario_conf, boolean pass_conf, boolean intersat,
										   boolean gas_natural, boolean jlsat, boolean duracion_automatica, boolean contador_km,
										   boolean bSubidaInmediataImagen, boolean bFotoInforme) {
		Configuracion c = montarConfiguracion(id_configuracion,   horarios,   operarios,   definiciones,
				equipos,   empresas,   marcas,   tipos_trabajo,
				tipos_presupuesto,   cuenta_bancaria,   fk_combustion,
				garantia,   pedir,   usar,   presupuestar,
				operacion_finalizacion,   precios_mano_obra,   formaPago,
				disp_servicio,   analisis_combustion,   puesta_marcha,
				servicio_urgencia,   kms_finalizacion,   traspaso_material,
				parte_usuario,   parte_averia,   parte_instalacion,
				parte_materiales,   parte_finalizacion,   parte_galeria,
				menu_asignacion,   menu_documentos,   menu_almacen,
				menu_cierre,   menu_ubicacion,   menu_datos_completos,
				menu_informar,   menu_datos_actualizados,   menu_presupuesto,
				requiere_firma,   usuario_conf,   pass_conf,   intersat,
				gas_natural,   jlsat,   duracion_automatica,   contador_km,
				bSubidaInmediataImagen, bFotoInforme);
		return crearConfiguracion(c,context);
	}
	public static boolean crearConfiguracion(Configuracion c,Context context) {
		try {
			cargarDao(context);
			dao.create(c);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Configuracion montarConfiguracion(int id_configuracion, boolean horarios, boolean operarios, boolean definiciones,
													boolean equipos, boolean empresas, boolean marcas, boolean tipos_trabajo,
													boolean tipos_presupuesto, boolean cuenta_bancaria, boolean fk_combustion,
													boolean garantia, boolean pedir, boolean usar, boolean presupuestar,
													boolean operacion_finalizacion, boolean precios_mano_obra, boolean formaPago,
													boolean disp_servicio, boolean analisis_combustion, boolean puesta_marcha,
													boolean servicio_urgencia, boolean kms_finalizacion, boolean traspaso_material,
													boolean parte_usuario, boolean parte_averia, boolean parte_instalacion,
													boolean parte_materiales, boolean parte_finalizacion, boolean parte_galeria,
													boolean menu_asignacion, boolean menu_documentos, boolean menu_almacen,
													boolean menu_cierre, boolean menu_ubicacion, boolean menu_datos_completos,
													boolean menu_informar, boolean menu_datos_actualizados, boolean menu_presupuesto,
													boolean requiere_firma, boolean usuario_conf, boolean pass_conf, boolean intersat,
													boolean gas_natural, boolean jlsat, boolean duracion_automatica, boolean contador_km,
													boolean bSubidaInmediataImagen, boolean bFotoInforme) {
		Configuracion c =new Configuracion(id_configuracion,   horarios,   operarios,   definiciones,
				equipos,   empresas,   marcas,   tipos_trabajo,
				tipos_presupuesto,   cuenta_bancaria,   fk_combustion,
				garantia,   pedir,   usar,   presupuestar,
				operacion_finalizacion,   precios_mano_obra,   formaPago,
				disp_servicio,   analisis_combustion,   puesta_marcha,
				servicio_urgencia,   kms_finalizacion,   traspaso_material,
				parte_usuario,   parte_averia,   parte_instalacion,
				parte_materiales,   parte_finalizacion,   parte_galeria,
				menu_asignacion,   menu_documentos,   menu_almacen,
				menu_cierre,   menu_ubicacion,   menu_datos_completos,
				menu_informar,   menu_datos_actualizados,   menu_presupuesto,
				requiere_firma,   usuario_conf,   pass_conf,   intersat,
				gas_natural,   jlsat,   duracion_automatica,   contador_km,
				bSubidaInmediataImagen, bFotoInforme);
		return c;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodasLasConfiguraciones(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Configuracion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarConfiguracionPorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Configuracion, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Configuracion.ID_CONFIGURACION, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Configuracion> buscarTodasLasConfiguraciones(Context context) throws SQLException {
		cargarDao(context);
		List<Configuracion> listadoConfiguraciones= dao.queryForAll();
		if(listadoConfiguraciones.isEmpty()) {
			return null;
		}else{
			return listadoConfiguraciones;
		}
	}
	public static Configuracion buscarConfiguracion(Context context) throws SQLException {
		cargarDao(context);
		List<Configuracion> listadoConfiguraciones= dao.queryForAll();
		if(listadoConfiguraciones.isEmpty()) {
			return null;
		}else{
			return listadoConfiguraciones.get(0);
		}
	}
	public static Configuracion buscarConfiguracionPorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Configuracion> listadoConfiguraciones= dao.queryForEq(Configuracion.ID_CONFIGURACION, id);
		if(listadoConfiguraciones.isEmpty()) {
			return null;
		}else{
			return listadoConfiguraciones.get(0);
		}
	}
	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//
	public static void actualizarConfiguracion(Context context, int id_configuracion, boolean horarios, boolean operarios, boolean definiciones,
											   boolean equipos, boolean empresas, boolean marcas, boolean tipos_trabajo,
											   boolean tipos_presupuesto, boolean cuenta_bancaria, boolean fk_combustion,
											   boolean garantia, boolean pedir, boolean usar, boolean presupuestar,
											   boolean operacion_finalizacion, boolean precios_mano_obra, boolean formaPago,
											   boolean disp_servicio, boolean analisis_combustion, boolean puesta_marcha,
											   boolean servicio_urgencia, boolean kms_finalizacion, boolean traspaso_material,
											   boolean parte_usuario, boolean parte_averia, boolean parte_instalacion,
											   boolean parte_materiales, boolean parte_finalizacion, boolean parte_galeria,
											   boolean menu_asignacion, boolean menu_documentos, boolean menu_almacen,
											   boolean menu_cierre, boolean menu_ubicacion, boolean menu_datos_completos,
											   boolean menu_informar, boolean menu_datos_actualizados, boolean menu_presupuesto,
											   boolean requiere_firma, boolean usuario_conf, boolean pass_conf, boolean intersat,
											   boolean gas_natural, boolean jlsat, boolean duracion_automatica, boolean contador_km) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Configuracion, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Configuracion.ID_CONFIGURACION,id_configuracion);
		updateBuilder.updateColumnValue(Configuracion.HORARIOS,horarios);
		updateBuilder.updateColumnValue(Configuracion.OPERARIOS,operarios);
		updateBuilder.updateColumnValue(Configuracion.DEFINICIONES,definiciones);
		updateBuilder.updateColumnValue(Configuracion.EQUIPOS,equipos);
		updateBuilder.updateColumnValue(Configuracion.EMPRESAS,empresas);
		updateBuilder.updateColumnValue(Configuracion.MARCAS,marcas);
		updateBuilder.updateColumnValue(Configuracion.TIPOS_TRABAJO,tipos_trabajo);
		updateBuilder.updateColumnValue(Configuracion.TIPOS_PRESUPUESTO,tipos_presupuesto);
		updateBuilder.updateColumnValue(Configuracion.CUENTA_BANCARIA,cuenta_bancaria);
		updateBuilder.updateColumnValue(Configuracion.FK_COMBUSTION,fk_combustion);
		updateBuilder.updateColumnValue(Configuracion.GARANTIA,garantia);
		updateBuilder.updateColumnValue(Configuracion.PEDIR,pedir);
		updateBuilder.updateColumnValue(Configuracion.USAR,usar);
		updateBuilder.updateColumnValue(Configuracion.PRESUPUESTAR,presupuestar);
		updateBuilder.updateColumnValue(Configuracion.OPERACION_FINALIZACION,operacion_finalizacion);
		updateBuilder.updateColumnValue(Configuracion.PRECIOS_MANO_OBRA,precios_mano_obra);
		updateBuilder.updateColumnValue(Configuracion.FORMA_PAGO,formaPago);
		updateBuilder.updateColumnValue(Configuracion.DISP_SERVICIO,disp_servicio);
		updateBuilder.updateColumnValue(Configuracion.ANALISIS_COMBUSTION,analisis_combustion);
		updateBuilder.updateColumnValue(Configuracion.PUESTA_MARCHA,puesta_marcha);
		updateBuilder.updateColumnValue(Configuracion.SERVICIO_URGENCIA,servicio_urgencia);
		updateBuilder.updateColumnValue(Configuracion.KMS_FINALIZACION,kms_finalizacion);
		updateBuilder.updateColumnValue(Configuracion.TRASPASO_MATERIAL,traspaso_material);
		updateBuilder.updateColumnValue(Configuracion.PARTE_USUARIO,parte_usuario);
		updateBuilder.updateColumnValue(Configuracion.PARTE_AVERIA,parte_averia);
		updateBuilder.updateColumnValue(Configuracion.PARTE_INSTALACION,parte_instalacion);
		updateBuilder.updateColumnValue(Configuracion.PARTE_MATERIALES,parte_materiales);
		updateBuilder.updateColumnValue(Configuracion.PARTE_FINALIZACION,parte_finalizacion);
		updateBuilder.updateColumnValue(Configuracion.PARTE_GALERIA,parte_galeria);
		updateBuilder.updateColumnValue(Configuracion.MENU_ASIGNACION,menu_asignacion);
		updateBuilder.updateColumnValue(Configuracion.MENU_DOCUMENTOS,menu_documentos);
		updateBuilder.updateColumnValue(Configuracion.MENU_ALMACEN,menu_almacen);
		updateBuilder.updateColumnValue(Configuracion.MENU_CIERRE,menu_cierre);
		updateBuilder.updateColumnValue(Configuracion.MENU_UBICACION,menu_ubicacion);
		updateBuilder.updateColumnValue(Configuracion.MENU_DATOS_COMPLETOS,menu_datos_completos);
		updateBuilder.updateColumnValue(Configuracion.MENU_INFORMAR,menu_informar);
		updateBuilder.updateColumnValue(Configuracion.MENU_DATOS_ACTUALIZADOS,menu_datos_actualizados);
		updateBuilder.updateColumnValue(Configuracion.MENU_PRESUPUESTO,menu_presupuesto);
		updateBuilder.updateColumnValue(Configuracion.REQUIERE_FIRMA,requiere_firma);
		updateBuilder.updateColumnValue(Configuracion.USUARIO_CONF,usuario_conf);
		updateBuilder.updateColumnValue(Configuracion.PASS_CONF,pass_conf);
		updateBuilder.updateColumnValue(Configuracion.INTERSAT,intersat);
		updateBuilder.updateColumnValue(Configuracion.GAS_NATURAL,gas_natural);
		updateBuilder.updateColumnValue(Configuracion.JLSAT,jlsat);
		updateBuilder.updateColumnValue(Configuracion.DURACION_AUTOMATICA,duracion_automatica);
		updateBuilder.updateColumnValue(Configuracion.CONTADOR_KM,contador_km);
		updateBuilder.update();
	}
}
