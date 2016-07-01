package com.multimedia.aes.gestnet_sgsv2.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;

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

	/**
	 * Este metodo libera los recursos
	 */

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


}
