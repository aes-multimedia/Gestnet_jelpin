package com.multimedia.aes.gestnet_sgsv2.hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.AsyncTask;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.clases.Impresora;
import com.multimedia.aes.gestnet_sgsv2.constants.Constantes;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;
import com.sewoo.request.android.RequestHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.sql.SQLException;

public class HiloConectarImpr extends AsyncTask<BluetoothDevice, Void, String> {

	private Impresora impresora;
	private Activity activity;
	private ProgressDialog dialog;

	public HiloConectarImpr(Impresora impresora, Activity activity) {
		super();
		this.impresora = impresora;
		this.activity = activity;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
		dialog = new ProgressDialog(activity);
		dialog.setTitle(activity.getResources().getString(R.string.bluetooth));
		dialog.setMessage(activity.getResources().getString(R.string.conectando));
		dialog.setCancelable(false);
		dialog.setIndeterminate(true);
		dialog.show();
	}

	@Override
	protected String doInBackground(BluetoothDevice... params) {
		try {
			impresora.bp.connect(params[0]);
			RequestHandler rh = new RequestHandler();
			impresora.hThread = new Thread(rh);
			impresora.hThread.start();			
			impresora.realizarImpresion();
			return Constantes.SUCCES;
		} catch (IOException e) {
			return Constantes.ERROR;
		}
	}

	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
		dialog.dismiss();
		Mantenimiento mantenimiento = null;
		try {
			JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(activity));
			int id = jsonObject.getInt("id");
			mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(activity,id);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			MantenimientoDAO.actualizarEstadoAndroid(activity, 3, mantenimiento.getId_mantenimiento());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		new HiloSubirTicket(activity).execute();
		Intent i = new Intent(activity, Index.class);
		activity.startActivity(i);
		activity.finish();
		if (result.equals(Constantes.SUCCES)) {
		}			
		else 
			Toast.makeText(activity, R.string.err_impr, Toast.LENGTH_SHORT).show();
		
	}

}
