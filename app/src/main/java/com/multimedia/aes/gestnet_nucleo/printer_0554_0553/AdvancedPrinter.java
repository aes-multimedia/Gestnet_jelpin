package com.multimedia.aes.gestnet_nucleo.printer_0554_0553;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;

public class AdvancedPrinter extends com.multimedia.aes.gestnet_nucleo.printer_0554_0553.Printer {

	//Member variablen
	private SpoolerThread spoolerThread = new SpoolerThread();

	/*private static final byte EOT = (byte) 0x04;
	private static final byte ESC = (byte) 0x1B;
	private static final byte INIT = (byte) 0x50;
	private static final int MAX_PIXELS = 166;*/

	private static final byte INIT = (byte) 0x50;
	private static final int MAX_PIXELS = 384;
	private static final byte EOT = 4;
	private static final byte ESC = 27;
	private static final byte GRAPHICS_LARGE = 42;
	/**
	 * Constructor of the AdvancedPrinter
	 *
	 * @param  printer
	 * 			Printerhelper
	 */
	public AdvancedPrinter(PrinterHelper printer) {
		super(printer, false, new ArrayList<byte[]>());
		spoolerThread.start();
	}

	/**
	 * returns if all data are send to the printer and the spooler is empty
	 *
	 * @return true: spooler is empty
	 */
	public boolean isSpoolerEmpty() {
		return getSpooler().size() == 0;
	}

	/**
	 * prints a bitmap from a file. The max width is 166 pixels. If the image is
	 * larger, it will be clipped.
	 *
	 * @param fileName
	 *            the full name (including path) of a image file to be printed
	 *
	 * @return true if all is OK, false in case of an error (may be image file
	 *         not found)
	 */
	@Override
	public boolean printBitmap(Bitmap bitmap) {
		Bitmap bit = bitmap;
		printPicture(bit);
		return true;
	}

	/**
	 * Print one bitmap image. The max width is 166 pixels. If the image is
	 * larger, it will be clipped.
	 *
	 * @param pic
	 *            the image to be printed
	 **/
	private void printPicture(Bitmap pic) {
		int lineMax = (pic.getHeight()+7) / 8;
		int width = pic.getWidth();
		if( width > MAX_PIXELS) width = MAX_PIXELS;

		// convert image to printer specific image format
		for( int line = 0; line < lineMax; line++ ) {
			byte[] printerLine = new byte[width];

			for (int i = 0; i < width; i++) {
				printerLine[i] = 0;
				int p = 1;
				for (byte k = 0; k < 8; k++) {
					if (k + 8 * line < pic.getHeight()) {
						int pixelColor = pic.getPixel(i, k + 8 * line);
						if (pixelColor == Color.BLACK) {
							printerLine[i] += (byte) p;
						}


					}
					p = 2 * p;
				}
			}
			// send printer line to print spooler
			spoolerThread.send(ESC);
			spoolerThread.send(GRAPHICS_LARGE);
			spoolerThread.send((byte) 0x01);
			spoolerThread.send((byte) (printerLine.length % 256));
			spoolerThread.send((byte) (printerLine.length >> 8));
			spoolerThread.send(printerLine);
			spoolerThread.send((byte) 0x04);
		}

/*
		int lineMax = (pic.getHeight() + 7) / 8;
		int width = pic.getWidth();
		if (width > MAX_PIXELS)
			width = MAX_PIXELS;

		// convert image to printer specific image format
		for (int line = 0; line < lineMax; line++) {
			// bytes of one printer line (1 lines has a height of 24 bits [=3
			// bytes])
			byte[] printerLine = new byte[width];

			for (int i = 0; i < width; i++) {
				printerLine[i] = 0;
				int p = 1;
				for (byte k = 0; k < 8; k++) {
					if (k + 8 * line < pic.getHeight()) {
						int pixelColor = pic.getPixel(i, k + 8 * line);
						if (pixelColor == Color.BLACK) {
							printerLine[i] += (byte) p;
						}
					}
					p = 2 * p;
				}
			}



			// send printer line to print spooler
			spoolerThread.send(ESC);
			spoolerThread.send((byte) 0x47);
			spoolerThread.send((byte) printerLine.length);
			spoolerThread.send(printerLine);
			spoolerThread.send((byte) 0x04);
		}
		*/
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setUnderlined(boolean underlined) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean paperFeedButton(boolean paperFeedButton) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setTextAboveBarcode(boolean textAboveBarcode) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printHTab() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printCR() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printLF() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Print Text
	 * @param text
	 * 			Text for printing
	 * @return text send to spooler ok or not
	 */
	@Override
	public boolean printText(String text) {
		return spoolerThread.send(convertMultibyteCharsToByteArray(text), EOT);
	}

	/**
	 * Print Line
	 * @param text
	 * 			Line for printing
	 *
	 *  @return text send to spooler ok or not
	 */
	@Override
	public boolean printLine(String text) {
		return spoolerThread.send(convertMultibyteCharsToByteArray(text), EOT);
	}

	/**
	 * convert string with possible multi byte chars to byte array according to
	 * ECMA-94 code page
	 *
	 * @param text
	 *            string to convert
	 * @return converted byte array
	 */
	public byte[] convertMultibyteCharsToByteArray(String text) {
		try {

			byte[] unicodeBytes = text.getBytes("UNICODE");
			String unicodeString = new String(unicodeBytes, "UNICODE");
			if (isD()) {
				Log.d(getTAG(),
						"AdvPrinterII convertMultibyteCharsToByteArray: unicodeBytes=");
				log(unicodeBytes);
				Log.d(getTAG(),
						"AdvPrinterII convertMultibyteCharsToByteArray: unicodeString="
								+ unicodeString);
			}

			int stringLength = unicodeString.length();
			byte[] arr = new byte[stringLength];
			for (int i = 0; i < stringLength; i++) {

				String unicodeChar = unicodeString.substring(i, i + 1);
				byte[] bytes = unicodeChar.getBytes("UNICODE");
				int low = (int) (bytes[bytes.length - 2] & 0x00FF);
				// int high = (int) (bytes[bytes.length - 1] & 0x00FF);

				// int ecma94code = ECMA94.getTestoByteByUnicodeBytes(high,
				// low);
				// arr[i] = (byte) ecma94code;

				arr[i] = (byte) low;
				// if (D)
				// Log.d(TAG,
				// "AdvPrinterII convertMultibyteCharsToByteArray: i=" + i +
				// " unicodeChar=" + unicodeChar +
				// " ecma94code=" + ecma94code);

				Log.d(getTAG(),
						"convertMultibyteCharsToByteArray: rel unicodeBytes=");

			}
			return arr;

		} catch (Exception ex) {
			Log.e(getTAG(), "catch in convertMultibyteCharsToByteArray. text="
					+ text + ". Error: " + ex);
			byte[] retval = { 0x00 };
			return retval;
		}
	}

	/**
	 * Convert Multi byte char(Strings) to unicode byte arrays
	 * @param text
	 * @return string converted to a byte array
	 */
	public byte[] convertMultibyteCharsToByteArrayUni(String text) {
		try {

			byte[] unicodeBytes = text.getBytes("UNICODE");
			String unicodeString = new String(unicodeBytes, "UNICODE");
			if (isD()) {
				Log.d(getTAG(),
						"AdvPrinterII convertMultibyteCharsToByteArray: unicodeBytes=");
				log(unicodeBytes);
				Log.d(getTAG(),
						"AdvPrinterII convertMultibyteCharsToByteArray: unicodeString="
								+ unicodeString);
			}

			byte[] arr = new byte[2 * unicodeString.length()];

			for (int i = 0; i < unicodeString.length(); i++) {

				String unicodeChar = unicodeString.substring(i, i + 1);
				byte[] bytes = unicodeChar.getBytes("UNICODE");
				int low = (int) (bytes[bytes.length - 2] & 0x00FF);
				int high = (int) (bytes[bytes.length - 1] & 0x00FF);

				arr[2 * i] = (byte) low;
				arr[2 * i + 1] = (byte) high;

			}
			return arr;

		} catch (Exception ex) {
			Log.e(getTAG(), "catch in convertMultibyteCharsToByteArray. text="
					+ text + ". Error: " + ex);
			byte[] retval = { 0x00 };
			return retval;
		}
	}

	/**
	 * send byte array to the spooler
	 * @return byte array sent to spooler was ok or not
	 */
	@Override
	public boolean printBytes(byte[] bytes) {

		return spoolerThread.send(bytes, EOT);
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean enterSpoolingMode() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public void exitSpoolingMode() {
		throw new UnsupportedOperationException();

	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public void abortPrintingAndInitialise() {
		throw new UnsupportedOperationException();

	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setRightOfCharacterSpacing(int num) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setFontMode(int fontMode) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setDoubleHeight(boolean doubleHeight) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setDoubleWidth(boolean doubleWidth) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setAbsolutePrintPosition(int pos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printGraphic(int mode, byte[] graphic) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setDefaultRowHeight() {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setRowHeight(int rowHeight) {
		throw new UnsupportedOperationException();
	}


	/**
	 * initialise Printer with Init-value (0x50)
	 */
	@Override
	public boolean initialisePrinter() {
		return spoolerThread.send(INIT);

	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setHTabPositions(int... positions) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printAndFeedExtraPaper(int num) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setRelativePrintPosition(int pos) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean doubleClickDemoMode(boolean enabled) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printAndFeedLines(int num) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setInvertedCharacterPrinting(boolean enabled) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setTextBelowBarcode(boolean enabled) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setHeightOfBarcode(int height) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean printBarcode(int type, String data) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public boolean setWidthOfBarcode(int width) {
		throw new UnsupportedOperationException();
	}

	/**
	 * Only Relevant for the old able printer
	 */
	@Override
	public void printECMA94() {
		byte[] arr = new byte[16];
		int cnt = 0;
		for (com.multimedia.aes.gestnet_nucleo.printer_0554_0553.ECMA94 i : ECMA94.values()) {
			int ch = i.getTestobyte();
			arr[cnt] = (byte) ch;
			cnt++;

			if (cnt == 16) {
				cnt = 0;
				spoolerThread.send(arr, EOT);
			}
		}

	}

	/**
	 * Creates a spooler  thread for communication with printer
	 * @author aks
	 *
	 */
	protected class SpoolerThread extends Thread {
		private boolean isInitSet= false;

		/**
		 * run thread
		 */
		public void run() {

			while (true) {
				int iFrameSize = 0;
				while (getSpooler().size() > 0) {
					byte[] b;

					//Decide if sending (0x50) is needed
					if (isInitSet) {
						b =  getSpooler().get(0);
					} else {
						isInitSet = true;
						byte[] arr = getSpooler().get(0);
						b = new byte[arr.length +1];
						b[0] = INIT;
						for (int i = 0; i < arr.length; i++) {
							b[i+1] =arr[i];
						}
					}

					// may frame size is 256 bytes
					if ((iFrameSize + b.length) > 255) {
						try {
							// wait at least 400 msec after each frame
							sleep(400);
							iFrameSize = 0;
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}

					synchronized (getSpooler()) {
						getPrinter().send(b);
						// log(spooler.get(0));
						getSpooler().remove(0);
						iFrameSize += b.length;
					}
				}
				try {
					sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				//Wait 3s and set isInitSet to false
				try {

					sleep(3000);
					isInitSet = false;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

		/**
		 * Sent function of the spooler for graphics
		 * @param esc
		 * 			escape byte
		 * @param c
		 * 		char
		 * @param mode
		 * 		mode
		 * @param n1
		 * 		n1
		 * @param n2
		 * 		n2
		 * @param graphic
		 * 		graphic byte[]
		 * @return send is ok or not
		 */
		public boolean send(byte esc, char c, int mode, byte n1, byte n2,
							byte[] graphic) {
			byte[] data = new byte[] { esc, (byte) c, (byte) mode, n1, n2 };
			return send(concat(data, graphic));
		}

		/**
		 * send concatinated data and bytes
		 * @param gs
		 * 			byte
		 * @param c
		 * 			charr
		 * @param type
		 * 			integer
		 * @param bytes
		 * 			byte[]
		 * @param i
		 * 			integer
		 * @return Success or no Success
		 */
		public boolean send(byte gs, char c, int type, byte[] bytes, int i) {
			byte[] data = new byte[] { gs, (byte) c, (byte) type };
			data = concat(data, bytes);
			return send(concat(data, new byte[] { (byte) i }));
		}

		/**
		 * send byte
		 * @param b
		 * 			byte
		 * @return send(byte[])
		 */
		public boolean send(byte b) {
			return send(new byte[] { b });
		}

		/**
		 * Send concatinated  byte array with aditional bytes to Spooler
		 * @param data
		 * 			bytes
		 * @param bytes
		 * 			bytes
		 * @return true
		 */
		public boolean send(byte[] data, byte... bytes) {
			synchronized (getSpooler()) {
				getSpooler().add(concat(data, bytes));
			}
			return true;
		}

		/**
		 * send concadinate data bytes
		 * @param data
		 * 			bytes
		 * @param c
		 * 			charr
		 * @param bytes
		 * 			bytes
		 * @return send is ok or not
		 */
		public boolean send(byte data, char c, byte... bytes) {
			byte b = (byte) c;
			byte[] concatData = new byte[] { data, b };
			return send(concat(concatData, bytes));
		}

		/**
		 * Sent Byte
		 * @param data byte
		 */
		public void sendRT(byte data) {
			getPrinter().send(new byte[] { data });
		}


		/**
		 * concatinate to byte arrays
		 * @param array1
		 * @param array2
		 * @return concatinated array
		 */
		private byte[] concat(byte[] array1, byte[] array2) {
			byte[] concatedArray = new byte[(array1.length + array2.length)];
			System.arraycopy(array1, 0, concatedArray, 0, array1.length);
			System.arraycopy(array2, 0, concatedArray, array1.length,
					array2.length);
			return concatedArray;
		}
	}

}
