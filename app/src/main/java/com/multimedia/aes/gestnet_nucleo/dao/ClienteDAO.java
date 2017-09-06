package com.multimedia.aes.gestnet_nucleo.dao;

import android.content.Context;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;
import com.j256.ormlite.stmt.UpdateBuilder;
import com.multimedia.aes.gestnet_nucleo.dbhelper.DBHelperMOS;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;

import java.sql.SQLException;
import java.util.List;

public class ClienteDAO extends DBHelperMOS {
	static Dao<Cliente, Integer> dao;

	public static void cargarDao(Context context) throws SQLException {
		dao = getHelper(context).getClienteDAO();
	}


	//__________FUNCIONES DE CREACIÓN________________________//

	public static boolean newCliente(Context context,int id_cliente, String nombre_cliente, String color_cliente, String logo_cliente, String ip_cliente, String cod_cliente) {
		Cliente c = montarCliente(id_cliente, nombre_cliente, color_cliente, logo_cliente, ip_cliente, cod_cliente);
		return crearCliente(c,context);
	}
	public static boolean crearCliente(Cliente c,Context context) {
		try {
			cargarDao(context);
			dao.create(c);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	public static Cliente montarCliente(int id_cliente, String nombre_cliente, String color_cliente, String logo_cliente, String ip_cliente, String cod_cliente) {
		Cliente c =new Cliente(id_cliente, nombre_cliente, color_cliente, logo_cliente, ip_cliente, cod_cliente);
		return c;
	}

	//__________FUNCIONES DE BORRADO______________________//

	public static void borrarTodosLosClientes(Context context) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Cliente, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.delete();
	}
	public static void borrarClientePorID(Context context, int id) throws SQLException {
		cargarDao(context);
		DeleteBuilder<Cliente, Integer> deleteBuilder = dao.deleteBuilder();
		deleteBuilder.where().eq(Cliente.ID_CLIENTE, id);
		deleteBuilder.delete();
	}

	//__________FUNCIONES DE BUSQUEDA______________________//


	public static List<Cliente> buscarTodosLosClientes(Context context) throws SQLException {
		cargarDao(context);
		List<Cliente> listadoClientes= dao.queryForAll();
		if(listadoClientes.isEmpty()) {
			return null;
		}else{
			return listadoClientes;
		}
	}
	public static Cliente buscarClientePorId(Context context, int id) throws SQLException {
		cargarDao(context);
		List<Cliente> listadoClientes= dao.queryForEq(Cliente.ID_CLIENTE, id);
		if(listadoClientes.isEmpty()) {
			return null;
		}else{
			return listadoClientes.get(0);
		}
	}

	//____________________________FUNCIONES DE ACTUALIZAR_________________________________________//


	public static void actualizarCliente(Context context, Cliente cliente ) throws SQLException {
		int id = cliente.getId_cliente();
		String nombre  = cliente.getNombre_cliente();
		String color = cliente.getColor_cliente();
		String logo = cliente.getLogo_cliente();
		String ip = cliente.getIp_cliente();
		String cod_cliente = cliente.getCod_cliente();

		cargarDao(context);
		UpdateBuilder<Cliente, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Cliente.ID_CLIENTE,id);
		updateBuilder.updateColumnValue(Cliente.NOMBRE_CLIENTE, nombre);
		updateBuilder.updateColumnValue(Cliente.COLOR_CLIENTE, color);
		updateBuilder.updateColumnValue(Cliente.LOGO_CLIENTE, logo);
		updateBuilder.updateColumnValue(Cliente.IP_CLIENTE, ip);
		updateBuilder.updateColumnValue(Cliente.COD_CLIENTE, cod_cliente);
		updateBuilder.update();
	}
	public static void actualizarCliente(Context context, int id_cliente, String nombre_cliente, String color_cliente, String logo_cliente, String ip_cliente, String cod_cliente ) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Cliente, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Cliente.ID_CLIENTE,id_cliente);
		updateBuilder.updateColumnValue(Cliente.NOMBRE_CLIENTE, nombre_cliente);
		updateBuilder.updateColumnValue(Cliente.COLOR_CLIENTE, color_cliente);
		updateBuilder.updateColumnValue(Cliente.LOGO_CLIENTE, logo_cliente);
		updateBuilder.updateColumnValue(Cliente.IP_CLIENTE, ip_cliente);
		updateBuilder.updateColumnValue(Cliente.COD_CLIENTE, cod_cliente);
		updateBuilder.update();
	}

	public static void actualizarNombre(Context context, String nombre, int id) throws SQLException {
		cargarDao(context);
		UpdateBuilder<Cliente, Integer> updateBuilder = dao.updateBuilder();
		updateBuilder.where().eq(Cliente.ID_CLIENTE,id);
		updateBuilder.updateColumnValue(Cliente.NOMBRE_CLIENTE,nombre);
		updateBuilder.update();
	}
}
