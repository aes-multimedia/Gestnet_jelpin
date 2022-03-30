/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.printer_0554_0553;

import android.os.Handler;

/**
 * Test class for PrinterHelper. It is used to test of communication to printer
 * @author Marco Brieden
 */
public class TestPrinterHelper implements com.multimedia.aes.gestnet_ssl.printer_0554_0553.PrinterHelper {
	@Override
	public String name(String Device) {
		return "Name of Printer";
	}

	byte[] lastTransmission;

	public boolean connectTo(String device) {
		return true;
	}

	public boolean isConnected() {
		return true;
	}

	public boolean isReady() {
		return true;
	}

	public byte[] getStatus() {
		return null;
	}

	public boolean send(byte[] data) {
		lastTransmission = data;
		return true;
	}

	public byte[] getLastTransmission() {
		byte[] ret = null;
			try {
				for (int i = 0; i <= 100; i++) {
					if (lastTransmission != null) {
						ret = lastTransmission;
						lastTransmission = null;
						break;
					}
					Thread.sleep(10);
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		return ret;
	}

	public void disconnect() {
	}

	public String[] getConnectionError() {
		return null;
	}

	public boolean wasReseted() {
		return false;
	}

	@Override
	public void setHandler(Handler handler) {
		
	}

}
