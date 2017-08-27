package com.multimedia.aes.gestnet_nucleo.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Configuracion;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

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

	public Dao<Cliente, Integer> getClienteDAO() throws SQLException {
		if (BBDDConstantes.clienteDao == null) {
			BBDDConstantes.clienteDao = getDao(Cliente.class);
		}
		return BBDDConstantes.clienteDao;
	}
	public Dao<Usuario, Integer> getUsuarioDAO() throws SQLException {
		if (BBDDConstantes.usuarioDao == null) {
			BBDDConstantes.usuarioDao = getDao(Usuario.class);
		}
		return BBDDConstantes.usuarioDao;
	}

	public Dao<Parte, Integer> getParteDAO() throws SQLException {
		if (BBDDConstantes.parteDao == null) {
			BBDDConstantes.parteDao = getDao(Parte.class);
		}
		return BBDDConstantes.parteDao;
	}
	public Dao<ProtocoloAccion, Integer> getProtocoloDAO() throws SQLException {
		if (BBDDConstantes.protocoloDao == null) {
			BBDDConstantes.protocoloDao = getDao(ProtocoloAccion.class);
		}
		return BBDDConstantes.protocoloDao;
	}
	public Dao<Configuracion, Integer> getConfiguracionDAO() throws SQLException {
		if (BBDDConstantes.configuracionDao == null) {
			BBDDConstantes.configuracionDao = getDao(Configuracion.class);
		}
		return BBDDConstantes.configuracionDao;
	}
	public Dao<Maquina, Integer> getMaquinaDAO() throws SQLException {
		if (BBDDConstantes.maquinaDao == null) {
			BBDDConstantes.maquinaDao = getDao(Maquina.class);
		}
		return BBDDConstantes.maquinaDao;
	}


	public Dao<DatosAdicionales, Integer> getDatosAdicionalesDAO() throws SQLException {
		if (BBDDConstantes.datosAdicionalesDao == null) {
			BBDDConstantes.datosAdicionalesDao = getDao(DatosAdicionales.class);
		}
		return BBDDConstantes.datosAdicionalesDao;
	}

	public Dao<Disposiciones,Integer> getDisposicionesDAO() throws SQLException {
		if (BBDDConstantes.disposicionesDao == null) {
			BBDDConstantes.disposicionesDao = getDao(Disposiciones.class);
		}

		return BBDDConstantes.disposicionesDao;
	}
}
