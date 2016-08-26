package com.multimedia.aes.gestnet_sgsv2.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Imagenes;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
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

public class DBHelper extends OrmLiteSqliteOpenHelper {

	private Context context;

	public DBHelper(Context context) {
		super(context, BBDDConstantes.DATABASE_NAME, null, BBDDConstantes.DATABASE_VERSION);
		this.context = context;
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			BBDDConstantes.crearTablas(connectionSource);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			BBDDConstantes.borrarTablas(connectionSource);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		onCreate(db, connectionSource);
	}

	@Override
	public void close() {
		BBDDConstantes.cerrarDao();
	}

	public Dao<Tecnico, Integer> getTecnicoDAO() throws SQLException {
		if (BBDDConstantes.tecnicoDao == null) {
			BBDDConstantes.tecnicoDao = getDao(Tecnico.class);
		}
		return BBDDConstantes.tecnicoDao;
	}
	public Dao<Averia, Integer> getAveriaDAO() throws SQLException {
		if (BBDDConstantes.averiaDao == null) {
			BBDDConstantes.averiaDao = getDao(Averia.class);
		}
		return BBDDConstantes.averiaDao;
	}
	public Dao<Mantenimiento, Integer> getMantenimientoDAO() throws SQLException {
		if (BBDDConstantes.mantenimientoDao == null) {
			BBDDConstantes.mantenimientoDao = getDao(Mantenimiento.class);
		}
		return BBDDConstantes.mantenimientoDao;
	}
	public Dao<TipoCaldera, Integer> getTipoCalderaDAO() throws SQLException {
		if (BBDDConstantes.tipoCalderaDao == null) {
			BBDDConstantes.tipoCalderaDao = getDao(TipoCaldera.class);
		}
		return BBDDConstantes.tipoCalderaDao;
	}
	public Dao<MarcaCaldera, Integer> getMarcaCalderaDAO() throws SQLException {
		if (BBDDConstantes.marcaCalderaDao == null) {
			BBDDConstantes.marcaCalderaDao = getDao(MarcaCaldera.class);
		}
		return BBDDConstantes.marcaCalderaDao;
	}
	public Dao<UsoCaldera, Integer> getUsoCalderaDAO() throws SQLException {
		if (BBDDConstantes.usoCalderaDao == null) {
			BBDDConstantes.usoCalderaDao = getDao(UsoCaldera.class);
		}
		return BBDDConstantes.usoCalderaDao;
	}
	public Dao<Potencia, Integer> getPotenciaDAO() throws SQLException {
		if (BBDDConstantes.potenciaDao == null) {
			BBDDConstantes.potenciaDao = getDao(Potencia.class);
		}
		return BBDDConstantes.potenciaDao;
	}

	public Dao<TipoEstado, Integer> getTipoEstadoDAO() throws SQLException {
		if (BBDDConstantes.tipoEstadoDao == null) {
			BBDDConstantes.tipoEstadoDao = getDao(TipoEstado.class);
		}
		return BBDDConstantes.tipoEstadoDao;
	}
	public Dao<TiposReparaciones, Integer> getTiposReparacionesDAO() throws SQLException {
		if (BBDDConstantes.tipoReparacionesDao == null) {
			BBDDConstantes.tipoReparacionesDao = getDao(TiposReparaciones.class);
		}
		return BBDDConstantes.tipoReparacionesDao;
	}

	public Dao<TiposVisita, Integer> getTiposVisitaDAO() throws SQLException {
		if (BBDDConstantes.tiposVisitasDao == null) {
			BBDDConstantes.tiposVisitasDao = getDao(TiposVisita.class);
		}
		return BBDDConstantes.tiposVisitasDao;
	}


	public Dao<SubTiposVisita, Integer> getSubTiposVisitaDAO() throws SQLException {
		if (BBDDConstantes.subTiposVisitasDao == null) {
			BBDDConstantes.subTiposVisitasDao = getDao(SubTiposVisita.class);
		}
		return BBDDConstantes.subTiposVisitasDao;
	}

	public Dao<Imagenes, Integer> getImagenDAO() throws SQLException {
		if (BBDDConstantes.imagenesDao == null) {
			BBDDConstantes.imagenesDao = getDao(Imagenes.class);
		}
		return BBDDConstantes.imagenesDao;
	}
	public Dao<MantenimientoTerminado, Integer> getMantenimientoTerminadoDAO() throws SQLException {
		if (BBDDConstantes.mantenimientoTerminadoDao == null) {
			BBDDConstantes.mantenimientoTerminadoDao = getDao(MantenimientoTerminado.class);
		}
		return BBDDConstantes.mantenimientoTerminadoDao;
	}
	public Dao<EquipamientoCaldera, Integer> getEquipamientoCalderaDAO() throws SQLException {
		if (BBDDConstantes.equipamientoCalderaDao == null) {
			BBDDConstantes.equipamientoCalderaDao = getDao(EquipamientoCaldera.class);
		}
		return BBDDConstantes.equipamientoCalderaDao;
	}

}
