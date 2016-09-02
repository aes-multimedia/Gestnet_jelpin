package com.multimedia.aes.gestnet_sgsv2.constants;

import android.content.Context;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoEstadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.UsoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Imagenes;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.MarcaCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Potencia;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.TipoEstado;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.UsoCaldera;

import java.sql.SQLException;

public class BBDDConstantes {

	public static final String DATABASE_NAME = "gestnet_sgs.db";
	public static final int DATABASE_VERSION = 2;

	public static Dao<Tecnico, Integer> tecnicoDao;
	public static Dao<Averia, Integer> averiaDao;
	public static Dao<Mantenimiento, Integer> mantenimientoDao;
	public static Dao<TipoCaldera, Integer> tipoCalderaDao;
	public static Dao<MarcaCaldera, Integer> marcaCalderaDao;
	public static Dao<UsoCaldera, Integer> usoCalderaDao;
	public static Dao<Potencia, Integer> potenciaDao;
	public static Dao<TipoEstado, Integer> tipoEstadoDao;
	public static Dao<TiposReparaciones, Integer> tipoReparacionesDao;
	public static Dao<TiposVisita, Integer> tiposVisitasDao;
	public static Dao<SubTiposVisita, Integer> subTiposVisitasDao;
	public static Dao<Imagenes, Integer> imagenesDao;
	public static Dao<MantenimientoTerminado, Integer> mantenimientoTerminadoDao;
	public static Dao<EquipamientoCaldera, Integer> equipamientoCalderaDao;
	public static Dao<Maquina, Integer> maquinaDao;

	public static void cerrarDao() {
		tecnicoDao = null;
		averiaDao = null;
		mantenimientoDao = null;
		tipoCalderaDao = null;
		marcaCalderaDao = null;
		usoCalderaDao = null;
		potenciaDao = null;
		tipoEstadoDao = null;
		tipoReparacionesDao = null;
		tiposVisitasDao = null;
		subTiposVisitasDao = null;
		imagenesDao = null;
		mantenimientoTerminadoDao = null;
		equipamientoCalderaDao = null;
		maquinaDao = null;
	}

	public static void crearTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.createTable(connectionSource, Tecnico.class);
		TableUtils.createTable(connectionSource, Averia.class);
		TableUtils.createTable(connectionSource, Mantenimiento.class);
		TableUtils.createTable(connectionSource, TipoCaldera.class);
		TableUtils.createTable(connectionSource, MarcaCaldera.class);
		TableUtils.createTable(connectionSource, UsoCaldera.class);
		TableUtils.createTable(connectionSource, Potencia.class);
		TableUtils.createTable(connectionSource, TipoEstado.class);
		TableUtils.createTable(connectionSource, TiposReparaciones.class);
		TableUtils.createTable(connectionSource, TiposVisita.class);
		TableUtils.createTable(connectionSource, SubTiposVisita.class);
		TableUtils.createTable(connectionSource, Imagenes.class);
		TableUtils.createTable(connectionSource, MantenimientoTerminado.class);
		TableUtils.createTable(connectionSource, EquipamientoCaldera.class);
		TableUtils.createTable(connectionSource, Maquina.class);

	}

	public static void borrarTablas(ConnectionSource connectionSource) throws SQLException {
		TableUtils.dropTable(connectionSource, Tecnico.class, true);
		TableUtils.dropTable(connectionSource, Averia.class, true);
		TableUtils.dropTable(connectionSource, Mantenimiento.class, true);
		TableUtils.dropTable(connectionSource, TipoCaldera.class, true);
		TableUtils.dropTable(connectionSource, MarcaCaldera.class, true);
		TableUtils.dropTable(connectionSource, UsoCaldera.class, true);
		TableUtils.dropTable(connectionSource, Potencia.class, true);
		TableUtils.dropTable(connectionSource, TipoEstado.class, true);
		TableUtils.dropTable(connectionSource, TiposReparaciones.class, true);
		TableUtils.dropTable(connectionSource, TiposVisita.class, true);
		TableUtils.dropTable(connectionSource, SubTiposVisita.class, true);
		TableUtils.dropTable(connectionSource, Imagenes.class, true);
		TableUtils.dropTable(connectionSource, MantenimientoTerminado.class, true);
		TableUtils.dropTable(connectionSource, EquipamientoCaldera.class, true);
		TableUtils.dropTable(connectionSource, Maquina.class, true);

	}

	public static void borrarDatosTablas(Context context) throws SQLException {
		TecnicoDAO.borrarTodosLosTecnicos(context);
		AveriaDAO.borrarTodosLasAverias(context);
		MantenimientoDAO.borrarTodosLosMantenimientos(context);
		TipoCalderaDAO.borrarTodosLosTipoCaldera(context);
		MarcaCalderaDAO.borrarTodosLosMarcaCaldera(context);
		UsoCalderaDAO.borrarTodosLosUsoCaldera(context);
		PotenciaDAO.borrarTodosLosPotencia(context);
		TipoEstadoDAO.borrarTodosLosTipoEstado(context);
		TiposReparacionesDAO.borrarTodosLosTiposReparaciones(context);
		TiposVisitaDAO.borrarTodosLosTiposVisita(context);
		SubTiposVisitaDAO.borrarTodosLosSubTiposVisita(context);
		ImagenesDAO.borrarTodasLasImagenes(context);
		MantenimientoTerminadoDAO.borrarTodosLosMantenimientoTerminados(context);
		EquipamientoCalderaDAO.borrarTodosLosEquipamientoCaldera(context);
		MaquinaDAO.borrarTodasLasMaquinas(context);

	}
}
