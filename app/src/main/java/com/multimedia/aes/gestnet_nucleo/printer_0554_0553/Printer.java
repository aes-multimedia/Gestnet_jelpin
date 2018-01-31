/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.printer_0554_0553;

import android.graphics.Bitmap;
import android.util.Log;

import java.util.ArrayList;


/**
 * This class implements all function to send messages to printer
 * @author Marco Brieden
 * @author aks
 */
public abstract class Printer {
	
	//Member Variables
	private static final boolean D = true;
	private final String TAG = this.getClass().getName();
	private PrinterHelper printer;
	private ArrayList<byte[]> spooler;
	protected boolean doubleClickDemoStatus;
	private boolean canPrintBarCode;
	
	/**
	 * is debugging mode activ?
	 * @return debugging mode or not
	 */
	protected static boolean isD() {
		return D;
	}

	/**
	 * get TAG string
	 * @return TAG
	 */
	protected String getTAG() {
		return TAG;
	}
	
	/**
	 * get Spooler
	 * @return current Spooler
	 */
	public ArrayList<byte[]> getSpooler() {
		return spooler;
	}

	/**
	 * get Printerhelper (Bluetooth)
	 * @return
	 */
	protected PrinterHelper getPrinter() {
		return printer;
	}
	/**
	 * Support the printer Barcodes
	 * @return support barcode or not
	 */
	public boolean isCanPrintBarCode() {
		return canPrintBarCode;
	}


	/**
	 * Printer constructor. Needs already connected PrinterHelper implementation.
	 * 
	 * @param printer
	 *          already connected PrinterHelper implementation
	 */
	protected Printer(PrinterHelper printer, 
					  Boolean hasAbletyToPrintBarcode,
					  ArrayList<byte[]> Spooler) {
		this.printer = printer;
		this.canPrintBarCode = hasAbletyToPrintBarcode;
		this.spooler = Spooler;
		
	}

	

	/**
	 * Returns if the driver is connected to the printer.
	 * 
	 * @return connection status
	 */
	public boolean isConnected() {
		return printer.isConnected();
	}

	
	
	// TODO reset fields on sleep

	/**
	 * generate Bytes from bit array mask
	 * @param bits 
	 * 			Array of bits like (11110010)
	 * @return resulte byte
	 */
	protected byte generateByte(boolean[] bits) {
		int byteAsInt = 0;
		for (int i = 0; i <= 7; i++) {
			if (bits.length > i && bits[i]) {
				byteAsInt += Math.pow(2, i);
			}
		}
		return (byte) byteAsInt;
	}

	
	/**
	 * writes log message at Debug mode
	 * @param data
	 */
	protected void log(byte[] data) {
		if (D) {
			String message = "";
			message += "length=";
			message += data==null? 0 : data.length;
			message += " {";
			for (byte b : data) {
				message += b + ", ";
			}
			message += " }";
			Log.d(TAG, message);
		}
	}

			
	
	/**
	 * simple convert each multibyte char into one-byte char by cutting high byte
	 * @param text
	 * @return converted byte array
	 */
	public byte[] convertEncodedString(String text){
		try{
			byte[] arr = new byte[text.length()];
			
			for (int i = 0; i < text.length(); i++){
				char c = text.charAt(i);
				arr[i] = (byte) c;
			}
			return arr;
			
		} catch(Exception ex){
			return null;
		}
	}

	
	/**
	 * Set underlinecommand
	 * Used in old able printer
	 * @param underlined
	 * 			is underlined?
	 * @return Success or no Success 
	 */
	public abstract boolean setUnderlined(boolean underlined);
	
	/**
	 * paperFeedButton state
	 *  Used in old able printer
	 * @param paperFeedButton
	 * 			is paperFeedButton pressed?
	 * @return
	 */
	public abstract boolean paperFeedButton(boolean paperFeedButton);

	
	/**
	 * setting text above barcode
	 *  Used in old able printer
	 * @param textAboveBarcode
	 * @return Success or no Success 
	 */
	public abstract boolean setTextAboveBarcode(boolean textAboveBarcode);

	/**
	 * print sequence for HTab
	 * Used in old able printer
	 * @return Success or no Success 
	 */
	public abstract boolean printHTab() ;
	
	/**
	 * Print bitmap form filename string
	 * @param fileName
	 * 			name + path of printfile
	 * @return Success or no Success 
	 */
	public abstract boolean printBitmap(Bitmap bmp);

	/**
	 * Print a single carriage return
	 * Used in old able printer
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printCR() ;
	

	/**
	 * Print a single line feed
	 * Used in old able printer
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printLF();
	
	/**
	 * Print string without jumping to the next line afterwards.
	 * 
	 * @param text
	 *          The string to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printText(String text);
	
	/**
	 * Print string and jump to the next line afterwards (send CR & LF).
	 * Used in old able printer
	 * @param text
	 *          The string to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printLine(String text) ;

	/**
	 * Print byte array.
	 * 
	 * @param bytes
	 *          The byte array to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printBytes(byte[] bytes) ;
		
	/**
	 * Tell the printer to enter spooling mode. Data and control codes received
	 * after this command will be stored in the buffer until either a command
	 * (exitSpoolingMode) to exit spooling mode is received, or the paper feed
	 * button is double-clicked by the operator (double-click operation can be
	 * disabled by the paperFeedButton command). Note that the normal sleep
	 * inactivity timeout period is extended in spooling mode.
	 * 
	 * Used in old able printer
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean enterSpoolingMode() ;

	/**
	 * Exit spooling mode immidiatly and print buffer contents. This command
	 * bypasses both spoolers. Ignored if the printer is not already in spooling
	 * mode or an error condition exists.
	 * 
	 * Used in old able printer
	 */
	public abstract void exitSpoolingMode();

	/**
	 * Aborts printing and initialize printer immediately. This command bypasses
	 * both spoolers.
	 * 
	 * Used in old able printer
	 */
	public abstract void abortPrintingAndInitialise() ;

	/**
	 * Number of additional dot spaces placed to the right of each character.
	 * 
	 * @param num
	 *          Number of additional dot spaces (Default = 0; Maximum = 31)
	 * 
	 * Used in old able printer
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setRightOfCharacterSpacing(int num) ;

	
	/**
	 * Set font mode.
	 * <table border="1">
	 * <thead>
	 * <tr>
	 * <td>Font Mode</td>
	 * <td>Chars/Line</td>
	 * <td>Character Height</td>
	 * <td>Row Height</td>
	 * </tr>
	 * </thead> <tbody>
	 * <tr>
	 * <td>0</td>
	 * <td>32</td>
	 * <td>24 dots (3.00 mm)</td>
	 * <td>30 dots (3.75 mm)</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>42</td>
	 * <td>24 dots (3.00 mm)</td>
	 * <td>30 dots (3.75 mm)</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>24</td>
	 * <td>24 dots (3.00 mm)</td>
	 * <td>30 dots (3.75 mm)</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>32</td>
	 * <td>24 dots (3.00 mm)</td>
	 * <td>24 dots (3.00 mm)</td>
	 * </tr>
	 * </tbody>
	 * </table>
	 * Resets when printer enters sleep mode.
	 * 
	 * Used in old able printer
	 * 
	 * @param fontMode
	 *          font mode to set (0, 1, 2 or 3)
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setFontMode(int fontMode) ;

	/**
	 * Double the height of the text. Resets when printer enters sleep mode.
	 * 
	 * @param doubleHeight
	 *          true to enable
	 * 
	 * Used in old able printer
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setDoubleHeight(boolean doubleHeight) ;

	/**
	 * Double the width of the text. Resets when printer enters sleep mode.
	 * 
	 * @param doubleWidth
	 *          true to enable
	 * 
	 * Used in old able printer
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setDoubleWidth(boolean doubleWidth) ;


	/**
	 * Sets the absolute print position "pos" dots from start of line. If printing
	 * is currently to the left of this position, blank space is inserted up to
	 * it. If it is to the right, then over-printing can occur, allowing special
	 * character combinations to be formed. Any overflow is cut off at end of
	 * line.
	 * 
	 * Used in old able printer
	 * 
	 * @param pos
	 *          The number of dots between the print position and the start of the
	 *          line (Minimum = 0; Maximum = 65535)
	 * 
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setAbsolutePrintPosition(int pos) ;
	

	/**
	 * Print Dot-Addressable (Bit) Graphics<br>
	 * <br>
	 * Several modes of Dot Addressable Graphics are possible: In all modes dot
	 * patterns are coded as 1= dot, 0= space, patterns are arranged in
	 * dot-columns, and the MSBit of each byte is printed at the top.
	 * 
	 * <ul>
	 * <li>Mode 0 or 2: Doubled-up 8 dot graphics<br>
	 * Each 8-dot vertical column is encoded as a single byte. Each dot is doubled
	 * in both axes to 0.25 mm square. Successive rows of 8-dot graphics cannot be
	 * printed contiguously in the vertical direction unless using a 16 dot high
	 * User Font.
	 * <li>Mode 3: Tripled-up 8 dot graphics<br>
	 * Each 8-dot vertical column is encoded as a single byte. Each dot is tripled
	 * in both axes to 0.375 mm square. Successive rows of 8-dot graphics can be
	 * printed contiguously in the vertical direction only in Font Mode 3 or a
	 * User Font which is 24 dots high.
	 * <li>Mode 4: Quadrupled-up 8 dot graphics<br>
	 * Each 8-dot vertical column is encoded as a single byte. Each dot is
	 * quadrupled in both axes to 0.5 mm square. Successive rows of 8-dot graphics
	 * can be printed contiguously in the vertical direction only in Font Modes 0,
	 * 1 or 2 or a User Font which is 32 dots high.
	 * <li>Mode 32: Plain 24 dot graphics<br>
	 * Each 24-dot vertical column is encoded as three successive bytes. Each dot
	 * is the mechanism dot size of 0.125 mm square. Significant features should
	 * be coded using at least 2 dots together. Successive rows of 24-dot graphics
	 * can be printed contiguously in the vertical direction only in Font Mode 3
	 * or a User Font which is 24 dots high.
	 * </ul>
	 * If the print mechanism is allowed to stop between successive contiguous
	 * graphics patterns, slight discontinuities may occur due to mechanical
	 * hysteresis. Consider using spool mode to prevent this.
	 * 
	 * Used in old able printer
	 * 
	 * @param mode
	 *          Set mode of image printing (0, 2, 3, 4 or 32)
	 * @param graphic
	 *          image as byte array
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printGraphic(int mode, byte[] graphic) ;
	/**
	 * Set default row height. Ie:-30 (0.125mm) dots in Font Mode 0,1 and 2; and
	 * 24 dots in Font Mode 3
	 * 
	 * Used in old able printer
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setDefaultRowHeight() ;
	/**
	 * Sets row height. Allowed range: 20 to 100. Row height refers to the
	 * interval from the top of one character row to the top of the next,
	 * including inter-row spacing. This setting is retained until cleared, or the
	 * Font Mode is changed or the printer enters sleep mode.
	 * 
	 * Used in old able printer
	 * 
	 * @param rowHeight
	 *          row height (Minimum = 20; Maximum = 100)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setRowHeight(int rowHeight) ;

	/**
	 * Clears print parameters to power-on default, ie normal width and height, no
	 * underline, no extra space, and default tabs. Does not affect the inverted
	 * mode. Does abide spooler order.
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean initialisePrinter() ;

	/**
	 * Sets horizontal tabulator positions. Up to 6 positions may be recorded, and
	 * replace the default settings. Note that tab settings take variable
	 * character spacing into account. Setting is retained until cleared or the
	 * printer enters sleep mode.
	 * 
	 * Used in old able printer
	 * 
	 * @param positions
	 *          Array of 1 to 6 integers defining HTab positions
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setHTabPositions(int... positions);

	/**
	 * Feeds extra paper. num is the count of notional 1/20th print lines to be
	 * fed. This command terminates the current line; n is divided by 20
	 * (remainder discarded) and the quotient used as a count of additional blank
	 * single- height character lines.
	 * 
	 * Used in old able printer
	 * 
	 * @param num
	 *          Number of 1/20th print lines to be fed (Minimum = 1; Maximum =
	 *          255)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printAndFeedExtraPaper(int num) ;
	/**
	 * Sets the relative print position "pos" dots from current position in text
	 * lines. Blank space is inserted up to the selected dot position. Any
	 * overflow is cut off at end of line.
	 * 
	 * Used in old able printer
	 * 
	 * @param pos
	 *          The number of dots between the print position and the current
	 *          position (Minimum = 0; Maximum = 65535)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setRelativePrintPosition(int pos);

	
	/**
	 * Disables the double click demo mode. double click demo mode only works when
	 * paper feed button is enabled
	 * 
	 * Used in old able printer
	 * 
	 * @param enabled
	 *          false to disable double click demo mode
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean doubleClickDemoMode(boolean enabled) ;
	
	/**
	 * Terminates the current line and feeds "num" blank print lines
	 * 
	 * Used in old able printer
	 * 
	 * @param num
	 *          Number of blank lines to print (Minimum = 1; Maximum = 255)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printAndFeedLines(int num) ;

	/**
	 * Enables inverted character printing. Normal and inverted text cannot be
	 * mixed in a single line. Resets when printer enters sleep mode.
	 * 
	 * Used in old able printer
	 * 
	 * @param enabled
	 *          true to enable inverted character printing
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setInvertedCharacterPrinting(boolean enabled);

	
	/**
	 * Enables text below barcode. This setting is retained and used for all
	 * subsequent barcodes, but is cleared to the default when the printer enters
	 * sleep mode
	 * 
	 * Used in old able printer
	 * 
	 * @param enabled
	 *          true to enable text below barcode
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setTextBelowBarcode(boolean enabled) ;
	
	/**
	 * Sets height of barcode. Height of barcode = (height x 0.125mm). This
	 * setting is retained and used for all subsequent barcodes, but is cleared to
	 * the default when the printer enters sleep mode.
	 * 
	 * Used in old able printer
	 * 
	 * @param height
	 *          Height of barcode (height x 0.125mm) (Minimum = 1; Maximum = 150)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setHeightOfBarcode(int height) ;
	
	/**
	 * Prints barcode using data provided.
	 * <table border=1>
	 * <theader>
	 * <tr>
	 * <td>Type</td>
	 * <td>Name</td>
	 * <td>Data</td>
	 * </tr>
	 * </theader> <tbody>
	 * <tr>
	 * <td>0</td>
	 * <td>UPC-A</td>
	 * <td>Numeric only ASCII data: supply 11 digits</td>
	 * </tr>
	 * <tr>
	 * <td>1</td>
	 * <td>UPC-E</td>
	 * <td>Numeric only ASCII data: supply 6 digits</td>
	 * </tr>
	 * <tr>
	 * <td>2</td>
	 * <td>EAN-13</td>
	 * <td>Numeric only ASCII data: supply 12 digits</td>
	 * </tr>
	 * <tr>
	 * <td>3</td>
	 * <td>EAN-8</td>
	 * <td>Numeric only ASCII data: supply 7 digits</td>
	 * </tr>
	 * <tr>
	 * <td>4</td>
	 * <td>Code 39</td>
	 * <td>Alphanumeric ASCII data: variable length (Maximum 22)</td>
	 * </tr>
	 * <tr>
	 * <td>5</td>
	 * <td>Int2 of 5</td>
	 * <td>Numeric only ASCII data: variable length (Maximum 23)</td>
	 * </tr>
	 * Note that the user should verify that a given barcode will fit on the
	 * paper, especially when using the variable length barcodes. Barcodes may not
	 * be mixed with normal text.
	 * 
	 * Used in old able printer
	 * 
	 * @param type
	 *          Type of barcode (0, 1, 2, 3, 4 or 5)
	 * @param data
	 *          Data for barcode
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean printBarcode(int type, String data) ;

	/**
	 * Set width of barcode. Width of barcode element (narrow bar) = (width x
	 * 0.125mm) Default value is 3. This setting is retained and used for all
	 * subsequent barcodes, but is cleared to the default when the printer enters
	 * sleep mode. Note that the user should verify that a given barcode will fit
	 * on the paper, especially when using the variable length barcodes.
	 * 
	 * Used in old able printer
	 * 
	 * @param width
	 *          Width of barcode (width x 0.125mm) (Minimum = 2; Maximum = 4)
	 * @return success of adding the command to the driver spooler
	 */
	public abstract boolean setWidthOfBarcode(int width) ;
	

	/**
	 * print ECMA-94 table. It is a test.
	 */
	public abstract void printECMA94();
	
		
}
