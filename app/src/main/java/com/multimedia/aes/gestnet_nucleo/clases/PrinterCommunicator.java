/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.clases;

import android.graphics.Bitmap;
import android.util.Log;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.BluetoothPrinterHelper;
import com.multimedia.aes.gestnet_nucleo.printer_0554_0553.Printer;


/**
 * global printer communicator class.
 * It is container for fields and implements function to send messages to printer
 */ 
public class PrinterCommunicator {
	//Member Variables
	// Debugging
	private static final String TAG = "PrinterCommunicator";
	private static final boolean D = true;

	private static PrinterCommunicator _instance = null;

	// Testo Printer 0554.0543
	public Printer printer = null;
	public BluetoothPrinterHelper btPrinterHelper = new BluetoothPrinterHelper();
	// Testo Printer MAC Adress
	public String printerMacAddress = null;

	/**
	 * singleton instantiator
	 * 
	 * @return _instance
	 */
	public static PrinterCommunicator getInstance() {
		if (PrinterCommunicator._instance == null) {
			synchronized (PrinterCommunicator.class) {
				if (PrinterCommunicator._instance == null) {
					PrinterCommunicator._instance = new PrinterCommunicator();
				}
			} // synchronized
		}
		return PrinterCommunicator._instance;
	} // getInstance

	private PrinterCommunicator() {
		if (D)
			Log.d(TAG, "+++ PrinterCommunicator +++");
	}

	/**
	 * Sends a message to print.
	 * 
	 * @param message
	 *          A string of text to send.
	 */
	public void sendMessage(String message) {
		// Check that we're actually connected before trying anything
		if (!btPrinterHelper.isConnected()) {
			return;
		}

		// Check that there's actually something to send
		if (message != null) 
			if (message.length() > 0) 
				printer.printLine(message);
	}

	/**
	 * Sends a text to print.
	 * 
	 * @param text
	 *          A string of text to send.
	 */
	public void sendText(String text) {
		// Check that we're actually connected before trying anything
		if (!btPrinterHelper.isConnected()) {
			return;
		}

		// Check that there's actually something to send
		if (text != null)
			if (text.length() > 0) 
				printer.printText(text);
	}

	/**
	 * Sends bytes to print.
	 * 
	 * @param bytes
	 *          A byte array to send.
	 */
	public void sendBytes(byte[] bytes) {
		// Check that we're actually connected before trying anything
		if (!btPrinterHelper.isConnected()) {
			return;
		}

		if (bytes != null)
			if (bytes.length > 0)
				printer.printBytes(bytes);
	}
	
	/**
	 * Print ECMA-94 table
	 * 
	 */
	public void printECMA94() {
		// Check that we're actually connected before trying anything
		if (!btPrinterHelper.isConnected()) {
			return;
		}

		// print
		printer.printECMA94();
	}
	
	/**
	 * Print Bitmap
	 * @param filename 
	 * 			Path + Name of Imagefile
	 *
	 */
	public void printBitmap(Bitmap bmp) {
		if (!btPrinterHelper.isConnected()) {
			return;
		}

		// print
		printer.printBitmap(bmp);
	}

}
