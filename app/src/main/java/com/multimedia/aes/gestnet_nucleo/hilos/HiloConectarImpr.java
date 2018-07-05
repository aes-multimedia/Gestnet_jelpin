package com.multimedia.aes.gestnet_nucleo.hilos;

import android.app.Activity;
import android.app.ProgressDialog;
import android.bluetooth.BluetoothDevice;
import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.clases.Impresora;
import com.multimedia.aes.gestnet_nucleo.clases.PrinterCommunicator;
import com.multimedia.aes.gestnet_nucleo.clases.Ticket;
import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterFactory;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper;
import com.sewoo.request.android.RequestHandler;
//import com.sewoo.request.android.RequestHandler;

import java.io.IOException;

public class HiloConectarImpr extends AsyncTask<BluetoothDevice, Void, String> {

	private Impresora impresora;
	private Activity activity;
	private Context context;
	private Ticket ticket;

	public HiloConectarImpr(Activity activity, Impresora impresora,Context context,Ticket ticket) {
		super();
        this.activity = activity;
        this.impresora=impresora;
        this.context=context;
        this.ticket=ticket;
    }

	@Override
	protected String doInBackground(BluetoothDevice... params) {
          try {
          impresora.bp.connect(params[0]);
            RequestHandler rh = new RequestHandler();
            impresora.hThread = new Thread(rh);
            impresora.hThread.start();
            impresora.realizarImpresionSeitron(ticket);
            return Constantes.SUCCES;
        } catch (IOException e) {
            return Constantes.ERROR;
        }

    }
    private void connectPrinter(String address) {
        try {
            PrinterCommunicator.getInstance().printerMacAddress = address;
            PrinterCommunicator.getInstance().btPrinterHelper
                    .connectTo(PrinterCommunicator.getInstance().printerMacAddress);
            if (PrinterCommunicator.getInstance().btPrinterHelper.isConnected()) {
                PrinterHelper helper = (PrinterHelper) PrinterCommunicator
                        .getInstance().btPrinterHelper;
                PrinterFactory factory = new PrinterFactory(helper);
                try {
                    PrinterCommunicator.getInstance().printer = factory
                            .getPrinter(address);
                } catch (Exception e) {
                    Toast.makeText(activity, "No supported Printer",
                            Toast.LENGTH_SHORT).show();
                }
            }
        } catch (Exception ex) {
            Log.e("ERROR", "connectPrinter: cannot connect to device " + address
                    + ". Error: " + ex);
        }
    }
	@Override
	protected void onPostExecute(String result) {
		super.onPostExecute(result);
        ((Index)context).datosActualizados();
    }

}
