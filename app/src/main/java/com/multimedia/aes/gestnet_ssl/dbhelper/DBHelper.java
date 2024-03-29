package com.multimedia.aes.gestnet_ssl.dbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.multimedia.aes.gestnet_ssl.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_ssl.entidades.Analisis;
import com.multimedia.aes.gestnet_ssl.entidades.Articulo;
import com.multimedia.aes.gestnet_ssl.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Configuracion;
import com.multimedia.aes.gestnet_ssl.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_ssl.entidades.Disposiciones;
import com.multimedia.aes.gestnet_ssl.entidades.Envio;
import com.multimedia.aes.gestnet_ssl.entidades.FormasPago;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.ManoObra;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.entidades.Marca;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_ssl.entidades.TipoCaldera;
import com.multimedia.aes.gestnet_ssl.entidades.TiposOs;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;

import java.sql.SQLException;

public class DBHelper extends OrmLiteSqliteOpenHelper {

	private Context context;

	public DBHelper(Context context) {
		super(context, BBDDConstantes.DATABASE_NAME, null, BBDDConstantes.DATABASE_VERSION);
		//context.deleteDatabase(BBDDConstantes.DATABASE_NAME);
		context.openOrCreateDatabase(BBDDConstantes.DATABASE_NAME, context.MODE_PRIVATE, null);

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

	public Dao<FormasPago,Integer> getFormasPagoDAO() throws SQLException {
		if (BBDDConstantes.formasPagoDao == null) {
			BBDDConstantes.formasPagoDao = getDao(FormasPago.class);
		}

		return BBDDConstantes.formasPagoDao;
	}
	public Dao<ManoObra,Integer> getManoObraDAO() throws SQLException {
		if (BBDDConstantes.manoObrasDao == null) {
			BBDDConstantes.manoObrasDao = getDao(ManoObra.class);
		}

		return BBDDConstantes.manoObrasDao;
	}

	public Dao<ProtocoloAccion,Integer> getProtocoloAccionDAO() throws SQLException {
		if (BBDDConstantes.protocoloDao == null) {
			BBDDConstantes.protocoloDao = getDao(ProtocoloAccion.class);
		}

		return BBDDConstantes.protocoloDao;
	}

	public Dao<Articulo,Integer> getArticuloDAO() throws SQLException {
		if (BBDDConstantes.articuloDao == null) {
			BBDDConstantes.articuloDao = getDao(Articulo.class);
		}

		return BBDDConstantes.articuloDao;
	}

	public Dao<Marca,Integer> getMarcaDAO() throws SQLException {
		if (BBDDConstantes.marcaDao == null) {
			BBDDConstantes.marcaDao = getDao(Marca.class);
		}

		return BBDDConstantes.marcaDao;
	}
	public Dao<TiposOs,Integer> getTiposOSDAO() throws SQLException {
		if (BBDDConstantes.tiposOSDao == null) {
			BBDDConstantes.tiposOSDao = getDao(TiposOs.class);
		}

		return BBDDConstantes.tiposOSDao;
	}
	public Dao<TipoCaldera,Integer> getTipoCalderaDAO() throws SQLException {
		if (BBDDConstantes.tipoCalderaDao == null) {
			BBDDConstantes.tipoCalderaDao = getDao(TipoCaldera.class);
		}

		return BBDDConstantes.tipoCalderaDao;
	}
	public Dao<ArticuloParte,Integer> getArticuloParteDAO() throws SQLException {
		if (BBDDConstantes.tipoArticuloParteDao == null) {
			BBDDConstantes.tipoArticuloParteDao = getDao(ArticuloParte.class);
		}

		return BBDDConstantes.tipoArticuloParteDao;
	}
	public Dao<Analisis,Integer> getAnalisisDAO() throws SQLException {
		if (BBDDConstantes.analisisDao == null) {
			BBDDConstantes.analisisDao = getDao(Analisis.class);
		}

		return BBDDConstantes.analisisDao;
	}
	public Dao<Imagen,Integer> getImagenDAO() throws SQLException {
		if (BBDDConstantes.imagenDao == null) {
			BBDDConstantes.imagenDao = getDao(Imagen.class);
		}

		return BBDDConstantes.imagenDao;
	}
	public Dao<Envio,Integer> getEnvioDAO() throws SQLException {
		if (BBDDConstantes.envioDao == null) {
			BBDDConstantes.envioDao = getDao(Envio.class);
		}

		return BBDDConstantes.envioDao;
	}
}
