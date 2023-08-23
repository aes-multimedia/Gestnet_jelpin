package com.multimedia.aes.gestnet_ssl.clases;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;

import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.hilos.HiloConectarImpr;

import com.sewoo.jpos.POSPrinterService;
import com.sewoo.port.android.BluetoothPort;
import jpos.JposException;
import jpos.POSPrinterConst;

import org.json.JSONException;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.SQLException;

public class Impresora {

	public Activity activity;
	private BluetoothAdapter bluetoothAdapter;
	public BluetoothPort bp;
	public Thread hThread;
	public BluetoothDevice mmDevice;
	private Parte parte;
	private Context context;

	public Impresora(Activity activity, BluetoothDevice mmDevice, Context context) {
		super();
		this.activity = activity;
		this.mmDevice = mmDevice;
		this.context = context;
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		bp = BluetoothPort.getInstance();
		try {
			JSONObject jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(activity));
			int id = jsonObject.getInt("id");
			parte = ParteDAO.buscarPartePorId(activity,id);
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void imprimir(Ticket ticket) {
		iniciarConexion();
		HiloConectarImpr hci = new HiloConectarImpr(activity,this,context,ticket);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			hci.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mmDevice);
		} else {
			hci.execute(mmDevice);
		}
	}
	private void iniciarConexion() {
		if (!bluetoothAdapter.isEnabled()) {
			bluetoothAdapter.enable();
			try {
				Thread.sleep(3600);
			} catch (Exception e) {
			}
		}
	}
	/////IMPRESION SEITRON
	public void realizarImpresionSeitron(Ticket ticket) {
		POSPrinterService pps = new POSPrinterService();
		try {
			//imprimirImagenEncabezadoSeitron(pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.encabezado()),pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.cuerpo(parte.getId_parte(),context)),pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.pie(parte.getId_parte(),context)),pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.conformeCliente(parte.getId_parte(),context)),pps);
			imprimirFirmaClienteSeitron(ticket,pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.conformeTecnico(context)),pps);
			imprimirFirmaTecnicoSeitron(ticket,pps);
			imprimirSeitron(ticket.limpiarAcentos(ticket.proteccionDatos(context)),pps);
			//bluetoothAdapter.disable();
		} catch (IOException | InterruptedException e) {
			Dialogo.errorDuranteImpresion(activity);
		} catch (JposException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void imprimirSeitron(String texto,POSPrinterService pps) throws JposException, InterruptedException {
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, texto);
		Thread.sleep(1000);
	}

	private void imprimirFirmaClienteSeitron(Ticket ticket,POSPrinterService pps) throws IOException, JposException, InterruptedException, SQLException {
		int img[][] = null;
		int ancho = 1;
		Bitmap bit = ticket.loadFirmaClienteFromStorage(parte.getId_parte(),activity);
		if(bit != null) {
			img = new int[bit.getWidth()][bit.getHeight()];
			ancho = bit.getWidth();
			for (int i = 0; i < bit.getHeight(); i++) {
				for (int j = 0; j < bit.getWidth(); j++) {
					img[j][i] = bit.getPixel(j, i);
				}
			}
			pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
			Thread.sleep(4000);
		} else {
			String texto = "\n\n\n\n";
			pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, texto);
			//Toast.makeText(this.context,"Necesitas la firma dek tecnico",Toast.LENGTH_SHORT).show();
		}
	}
	private void imprimirFirmaTecnicoSeitron(Ticket ticket,POSPrinterService pps) throws IOException, JposException, InterruptedException, SQLException {
		int img[][] = null;
		int ancho = 1;
		Bitmap bit = ticket.loadFirmaTecnicoFromStorage(activity);
		if(bit!=null) {
			img = new int[bit.getWidth()][bit.getHeight()];
			ancho = bit.getWidth();
			for (int i = 0; i < bit.getHeight(); i++) {
				for (int j = 0; j < bit.getWidth(); j++) {
					img[j][i] = bit.getPixel(j, i);
				}
			}
			pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
			Thread.sleep(4000);
		} else {
			String texto = "\n\n\n\n";
			pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, texto);
			//Toast.makeText(this.context,"Necesitas la firma dek tecnico",Toast.LENGTH_SHORT).show();
		}
	}



	/*private void imprimirImagenEncabezadoSeitron(POSPrinterService pps) throws IOException, JposException, InterruptedException {
		InputStream bitmap = null;
		int img[][] = null;
		int ancho = 1;
		try {
			bitmap = activity.getAssets().open("logo.png");
			Bitmap bit = BitmapFactory.decodeStream(bitmap);
			img = new int[bit.getWidth()][bit.getHeight()];
			ancho = bit.getWidth();
			for (int i = 0; i < bit.getHeight(); i++) {
				for (int j = 0; j < bit.getWidth(); j++) {
					img[j][i] = bit.getPixel(j, i);
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bitmap.close();
		}
		pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
		Thread.sleep(2000);
	}*/

}
