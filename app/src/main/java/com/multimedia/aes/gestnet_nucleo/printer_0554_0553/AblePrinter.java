package com.multimedia.aes.gestnet_nucleo.printer_0554_0553;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.util.Log;

import java.util.ArrayList;


public class AblePrinter extends com.multimedia.aes.gestnet_nucleo.printer_0554_0553.Printer {

	//Member Variables
	private SpoolerThread spoolerThread = new SpoolerThread();
	private int maxSpoolerSize = 250;
	private int spoolerPercentageFull = 0;
	
	private boolean textAboveBarcode;
	private boolean textBelowBarcode;
	
	private boolean doubleHeight = false; 
	private boolean doubleWidth = false;
	private boolean underlined = false;
	private int fontMode = 0;
	private boolean paperFeedButtonStatus;
	
	private static final byte HTAB = (byte) 0x09;
	private static final byte LF = (byte) 0x0A;
	private static final byte CR = (byte) 0x0D;
	private static final byte ESC = (byte) 0x1B;
	private static final byte GS = (byte) 0x1D;
	private static final byte FF = (byte) 0x0C;
	private static final byte CAN = (byte) 0x18;
	
	
	/**
	 * Returns the percentage to which the driver spooler (not the spooler of the
	 * actual hardware printer) is full. Use setSpoolerSize to allocate additional
	 * or less memory to spooler.
	 * 
	 * @return Percentage to which the driver spooler (not the spooler of the
	 *         actual hardware printer) is full.
	 */
	public int getSpoolerPercentageFull() {
		return spoolerPercentageFull;
	}

	/**
	 * Sets size of driver spooler (not the spooler of the actual hardware
	 * printer).
	 * 
	 * @param spoolerSize
	 *          the number of commands the driver spooler can hold (Default = 250)
	 */
	public void setSpoolerSize(int spoolerSize) {
		this.maxSpoolerSize = spoolerSize;
	}
	
	
	/**
	 * Constructor
	 * @param printer
	 * 		Printerhelper (Bluetooths)
	 */
	public AblePrinter(PrinterHelper printer) {
		super(printer, true, new ArrayList<byte[]>());
		spoolerThread.start();
	}
	
	/**
	 * print bitmap from filename
	 * @param filename
	 * 			Name of printfile
	 * @return true
	 */
	@Override
	public boolean printBitmap(Bitmap bmp) {
		Bitmap bit = bmp;
		printPicture( bit );
		return true;
	}
	
	/**
	 * print one bitmap image
     *
     * max width is 384 pixels, we may should check it
	 **/
    private void printPicture(Bitmap pic )
    {
        this.setFontMode(3);
        this.setRowHeight(24);
        int lineMax = (pic.getHeight()+23) / 24;
                
        // convert image to printer specific image format
        for( int line = 0; line < lineMax; line++ )
        {           	
        	// bytes of one printer line (1 lines has a height of 24 bits [=3 bytes])
           	byte[] printerLine = new byte[3 * pic.getWidth()];

           	for (int i = 0; i < pic.getWidth(); i++)
           	{
             	for (int j = 0; j < 3; j++)
               	{
                	byte pix = 0;	
                  	for (byte k = 0; k < 8; k++)
                   	{
                  		if (j * 8 + k + 24 * line < pic.getHeight())
                       	{
                        	int pixelColor = pic.getPixel(i, j * 8 + k + 24 * line);
                          	if( pixelColor == Color.BLACK )
                            	pix += (byte) Math.pow(2, (7 - k));
                     	}
               		}
                  	printerLine[j + i * 3] = pix;
             	}
        	}
         	// send printer line to print spooler
           	this.printGraphic(32, printerLine);
            this.printCR();
     	}
       	// set settings to old value
      	this.setDefaultRowHeight();
    }
    
	/**
	 * Print a single horizontal tabulator
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printHTab() {
		return spoolerThread.send(HTAB);
	}

	/**
	 * Print a single carriage return
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printCR() {
		return spoolerThread.send(CR);
	}

	/**
	 * Print a single line feed
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printLF() {
		return spoolerThread.send(LF);
	}

	/**
	 * Print string without jumping to the next line afterwards.
	 * 
	 * @param text
	 *          The string to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printText(String text) {
//		return spoolerThread.send(text.getBytes());
		return spoolerThread.send(convertMultibyteCharsToByteArray(text));
	}

	/**
	 * Print string and jump to the next line afterwards (send CR & LF).
	 * 
	 * @param text
	 *          The string to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printLine(String text) {
//		return spoolerThread.send(text.getBytes(), CR, LF);
		return spoolerThread.send(convertMultibyteCharsToByteArray(text), CR, LF);
	}

	/**
	 * Print byte array.
	 * 
	 * @param bytes
	 *          The byte array to print
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printBytes(byte[] bytes) {
		return spoolerThread.send(bytes);
	}

	/**
	 * Tell the printer to enter spooling mode. Data and control codes received
	 * after this command will be stored in the buffer until either a command
	 * (exitSpoolingMode) to exit spooling mode is received, or the paper feed
	 * button is double-clicked by the operator (double-click operation can be
	 * disabled by the paperFeedButton command). Note that the normal sleep
	 * inactivity timeout period is extended in spooling mode.
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean enterSpoolingMode() {
		return spoolerThread.send(ESC, 'L');
	}

	/**
	 * Exit spooling mode immidiatly and print buffer contents. This command
	 * bypasses both spoolers. Ignored if the printer is not already in spooling
	 * mode or an error condition exists.
	 */
	@Override
	public void exitSpoolingMode() {
		spoolerThread.sendRT(FF);
	}

	/**
	 * Aborts printing and initialize printer immediately. This command bypasses
	 * both spoolers.
	 */
	@Override
	public void abortPrintingAndInitialise() {
		spoolerThread.sendRT(CAN);
	}

	/**
	 * Number of additional dot spaces placed to the right of each character.
	 * 
	 * @param num
	 *          Number of additional dot spaces (Default = 0; Maximum = 31)
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setRightOfCharacterSpacing(int num) {
		byte[] data = new byte[] { ESC, (byte) ' ', (byte) num };
		return checkRangeThenSend(data, num, 0, 31);
	}

	/**
	 * set printmode  for old able printer
	 * @param fontMode
	 * 			fondmodus
	 * @param doubleHeight
	 * 				has doubleHight?
	 * @param doubleWidth
	 * 				has doubleWith
	 * @param underlined
	 * 				is underlined?
	 * @return
	 */
	private boolean setPrintMode(int fontMode, boolean doubleHeight,
			boolean doubleWidth, boolean underlined) {
		boolean success = false;
		if (fontMode >= 0 & fontMode <= 3) {
			this.fontMode = fontMode;
			this.doubleHeight = doubleHeight;
			this.doubleWidth = doubleWidth;
			this.underlined = underlined;
			/**
			 * <ul>
			 * <li>bit 0 } Select Font Mode 0 to 3
			 * <li>bit 1 } (Mode 0 is factory setting)
			 * <li>bit 4 Select(1)/Cancel(0) Double height mode
			 * <li>bit 5 Select(1)/Cancel(0) Double width mode
			 * <li>bit 7 Select(1)/Cancel(0) Underlined mode
			 * <li>Bits 2, 3, and 6 are ignored.
			 * </ul>
			 */
			byte dataByte = super.generateByte(new boolean[] { (fontMode > 1),
					(fontMode == 1 | fontMode == 3), false, false, doubleHeight,
					doubleWidth, false, underlined });
			success = spoolerThread.send(ESC, '!', dataByte);
		}
		return success;
	}

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
	 * @param fontMode
	 *          font mode to set (0, 1, 2 or 3)
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setFontMode(int fontMode) {
		return setPrintMode(fontMode, doubleHeight, doubleWidth, underlined);
	}

	/**
	 * Double the height of the text. Resets when printer enters sleep mode.
	 * 
	 * @param doubleHeight
	 *          true to enable
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setDoubleHeight(boolean doubleHeight) {
		return setPrintMode(fontMode, doubleHeight, doubleWidth, underlined);
	}

	/**
	 * Double the width of the text. Resets when printer enters sleep mode.
	 * 
	 * @param doubleWidth
	 *          true to enable
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setDoubleWidth(boolean doubleWidth) {
		return setPrintMode(fontMode, doubleHeight, doubleWidth, underlined);
	}

	/**
	 * Underline the text. Resets when printer enters sleep mode.
	 * 
	 * @param underlined
	 *          true to enable
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean setUnderlined(boolean underlined) {
		return setPrintMode(fontMode, doubleHeight, doubleWidth, underlined);
	}

	/**
	 * Sets the absolute print position "pos" dots from start of line. If printing
	 * is currently to the left of this position, blank space is inserted up to
	 * it. If it is to the right, then over-printing can occur, allowing special
	 * character combinations to be formed. Any overflow is cut off at end of
	 * line.
	 * 
	 * @param pos
	 *          The number of dots between the print position and the start of the
	 *          line (Minimum = 0; Maximum = 65535)
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean setAbsolutePrintPosition(int pos) {
		boolean success = false;
		if (pos >= 0 & pos <= 65535) {
			byte n1 = (byte) (pos % 256);
			byte n2 = (byte) (pos / 256);
			success = spoolerThread.send(ESC, '$', n1, n2);
		}
		return success;
	}

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
	 * @param mode
	 *          Set mode of image printing (0, 2, 3, 4 or 32)
	 * @param graphic
	 *          image as byte array
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printGraphic(int mode, byte[] graphic) {
		boolean ret = false;
		int k = graphic.length;
		if (mode == 32) {
			k = k / 3;
		}
		byte n1 = (byte) (k % 256);
        byte n2 = (byte) (k / 256);
		switch (mode) {
		case 0:
		case 2:
			spoolerThread.send(ESC, '*', mode, n1, n2, graphic);
			break;
		case 3:
			spoolerThread.send(ESC, '*', mode, n1, n2, graphic);
			break;
		case 4:
			spoolerThread.send(ESC, '*', mode, n1, n2, graphic);
			break;
		case 32:
			if (graphic.length % 3 == 0) {
				spoolerThread.send(ESC, '*', mode, n1, n2, graphic);
			}
			break;
		}
		return ret;
	}

	/**
	 * Set default row height. Ie:-30 (0.125mm) dots in Font Mode 0,1 and 2; and
	 * 24 dots in Font Mode 3
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setDefaultRowHeight() {
		return spoolerThread.send(ESC, '2');
	}

	/**
	 * Sets row height. Allowed range: 20 to 100. Row height refers to the
	 * interval from the top of one character row to the top of the next,
	 * including inter-row spacing. This setting is retained until cleared, or the
	 * Font Mode is changed or the printer enters sleep mode.
	 * 
	 * @param rowHeight
	 *          row height (Minimum = 20; Maximum = 100)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setRowHeight(int rowHeight) {
		byte[] data = new byte[] { ESC, (byte) '3', (byte) rowHeight };
		return checkRangeThenSend(data, rowHeight, 20, 100);
	}

	/**
	 * Clears print parameters to power-on default, ie normal width and height, no
	 * underline, no extra space, and default tabs. Does not affect the inverted
	 * mode. Does abide spooler order.
	 * 
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean initialisePrinter() {
		return spoolerThread.send(ESC, '@');
	}

	/**
	 * Sets horizontal tabulator positions. Up to 6 positions may be recorded, and
	 * replace the default settings. Note that tab settings take variable
	 * character spacing into account. Setting is retained until cleared or the
	 * printer enters sleep mode.
	 * 
	 * @param positions
	 *          Array of 1 to 6 integers defining HTab positions
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setHTabPositions(int... positions) {
		boolean success = false;
		if (positions.length >= 1 & positions.length <= 6) {
			byte[] data;
			if (positions.length == 6) {
				data = new byte[6];
			} else {
				data = new byte[positions.length + 1];
				data[positions.length] = (byte) 0;
			}
			for (int i = 0; i < positions.length; i++) {
				data[i] = (byte) positions[i];
			}
			success = spoolerThread.send(ESC, 'D', data);
		}
		return success;
	}

	/**
	 * Feeds extra paper. num is the count of notional 1/20th print lines to be
	 * fed. This command terminates the current line; n is divided by 20
	 * (remainder discarded) and the quotient used as a count of additional blank
	 * single- height character lines.
	 * 
	 * @param num
	 *          Number of 1/20th print lines to be fed (Minimum = 1; Maximum =
	 *          255)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printAndFeedExtraPaper(int num) {
		return checkRangeThenSend(new byte[] { ESC, (byte) 'J', (byte) num }, num,
				1, 255);
	}

	/**
	 * Sets the relative print position "pos" dots from current position in text
	 * lines. Blank space is inserted up to the selected dot position. Any
	 * overflow is cut off at end of line.
	 * 
	 * @param pos
	 *          The number of dots between the print position and the current
	 *          position (Minimum = 0; Maximum = 65535)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setRelativePrintPosition(int pos) {
		boolean success = false;
		if (pos >= 0 & pos <= 65535) {
			byte n1 = (byte) (pos % 256);
			byte n2 = (byte) (pos / 256);
			success = spoolerThread.send(ESC, '\\', n1, n2);
		}
		return success;
	}

	/**
	 * Disables the paper feed button.
	 * 
	 * @param enabled
	 *          false to disable button
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean paperFeedButton(boolean enabled) {
		paperFeedButtonStatus = enabled;
		byte b = generateByte(new boolean[] { paperFeedButtonStatus,
				doubleClickDemoStatus });
		return spoolerThread.send(ESC, 'c', b);
	}

	/**
	 * Disables the double click demo mode. double click demo mode only works when
	 * paper feed button is enabled
	 * 
	 * @param enabled
	 *          false to disable double click demo mode
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean doubleClickDemoMode(boolean enabled) {
		doubleClickDemoStatus = enabled;
		byte b = generateByte(new boolean[] { paperFeedButtonStatus,
				doubleClickDemoStatus });
		return spoolerThread.send(ESC, 'c', b);
	}

	/**
	 * Terminates the current line and feeds "num" blank print lines
	 * 
	 * @param num
	 *          Number of blank lines to print (Minimum = 1; Maximum = 255)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printAndFeedLines(int num) {
		return checkRangeThenSend(new byte[] { ESC, (byte) 'd', (byte) num }, num,
				1, 255);
	}

	/**
	 * Enables inverted character printing. Normal and inverted text cannot be
	 * mixed in a single line. Resets when printer enters sleep mode.
	 * 
	 * @param enabled
	 *          true to enable inverted character printing
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setInvertedCharacterPrinting(boolean enabled) {
		boolean success = false;
		if (enabled) {
			spoolerThread.send(ESC, '{', (byte) 1);
		} else {
			spoolerThread.send(ESC, '{', (byte) 0);
		}
		return success;
	}

	/**
	 * Enables text above barcode. This setting is retained and used for all
	 * subsequent barcodes, but is cleared to the default when the printer enters
	 * sleep mode
	 * 
	 * @param enabled
	 *          true to enable text above barcode
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean setTextAboveBarcode(boolean enabled) {
		textAboveBarcode = enabled;
		byte b = generateByte(new boolean[] { textAboveBarcode, textBelowBarcode });
		return spoolerThread.send(GS, 'H', b);
	}

	/**
	 * Enables text below barcode. This setting is retained and used for all
	 * subsequent barcodes, but is cleared to the default when the printer enters
	 * sleep mode
	 * 
	 * @param enabled
	 *          true to enable text below barcode
	 * @return success of adding the command to the driver spooler
	 */
	 @Override
	public boolean setTextBelowBarcode(boolean enabled) {
		textBelowBarcode = enabled;
		byte b = generateByte(new boolean[] { textAboveBarcode, textBelowBarcode });
		return spoolerThread.send(GS, 'H', b);
	}

	/**
	 * Sets height of barcode. Height of barcode = (height x 0.125mm). This
	 * setting is retained and used for all subsequent barcodes, but is cleared to
	 * the default when the printer enters sleep mode.
	 * 
	 * @param height
	 *          Height of barcode (height x 0.125mm) (Minimum = 1; Maximum = 150)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setHeightOfBarcode(int height) {
		byte[] data = new byte[] { GS, (byte) 'h', (byte) height };
		return checkRangeThenSend(data, height, 1, 150);
	}

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
	 * @param type
	 *          Type of barcode (0, 1, 2, 3, 4 or 5)
	 * @param data
	 *          Data for barcode
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean printBarcode(int type, String data) {
		boolean ret = false;
		switch (type) {
		case 0:// UPC-A
			if (data.length() == 11) {
				if (data.matches("[0-9]*")) {
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		case 1:// UPC-E
			if (data.length() == 6) {
				if (data.matches("[0-9]*")) {
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		case 2:// EAN-13
			if (data.length() == 12) {
				if (data.matches("[0-9]*")) {
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		case 3:// EAN-8
			if (data.length() == 7) {
				if (data.matches("[0-9]*")) {
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		case 4:// Code 39
			if (data.length() <= 22) {
				if (data.matches("[a-zA-z0-9 ]*")) {
					data = data.toUpperCase();
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		case 5:// Int2 of 5
			if (data.length() <= 23) {
				if (data.matches("[0-9]*")) {
					spoolerThread.send(GS, 'k', type, data.getBytes(), 0);
				}
			}
			break;
		}
		return ret;
	}

	/**
	 * Set width of barcode. Width of barcode element (narrow bar) = (width x
	 * 0.125mm) Default value is 3. This setting is retained and used for all
	 * subsequent barcodes, but is cleared to the default when the printer enters
	 * sleep mode. Note that the user should verify that a given barcode will fit
	 * on the paper, especially when using the variable length barcodes.
	 * 
	 * @param width
	 *          Width of barcode (width x 0.125mm) (Minimum = 2; Maximum = 4)
	 * @return success of adding the command to the driver spooler
	 */
	@Override
	public boolean setWidthOfBarcode(int width) {
		return checkRangeThenSend(new byte[] { GS, (byte) 'w', (byte) width },
				width, 2, 4);
	}
	
	/**
	 * check for range of data 
	 * @param data
	 * 			byte[] to sent
	 * @param num
	 * 			current length
	 * @param min
	 * 			Minimal data range length
	 * @param max
	 * 			Maximal data range length
	 * @return Success or no Succes
	 */
	private boolean checkRangeThenSend(byte[] data, int num, int min, int max) {
		boolean success = false;
		if (num >= min & num <= max) {
			success = spoolerThread.send(data);
		}
		return success;
	}
	
	/**
	 * convert string with possible multi byte chars to byte array according to ECMA-94 code page
	 * @param text string to convert
	 * @return converted byte array
	 */
	public byte[] convertMultibyteCharsToByteArray(String text){
		try{

			byte[] unicodeBytes = text.getBytes("UNICODE");
			String unicodeString = new String(unicodeBytes, "UNICODE");
			if (isD()){
				Log.d(getTAG(), "convertMultibyteCharsToByteArray: unicodeBytes=");
				log(unicodeBytes);
				Log.d(getTAG(), "convertMultibyteCharsToByteArray: unicodeString=" + unicodeString);
			}

			
			byte[] arr = new byte[unicodeString.length()];
			
			for (int i = 0; i < unicodeString.length(); i++){
				
				String unicodeChar = unicodeString.substring(i, i + 1);
				byte[] bytes = unicodeChar.getBytes("UNICODE");
				int low = (int) (bytes[bytes.length - 2] & 0x00FF);
				int high = (int) (bytes[bytes.length - 1] & 0x00FF);
				
				int ecma94code = com.multimedia.aes.gestnet_nucleo.printer_0554_0553.ECMA94.getTestoByteByUnicodeBytes(high, low);
				
				arr[i] = (byte) ecma94code;
				
				if (isD())
					Log.d(getTAG(), "convertMultibyteCharsToByteArray: i=" + i +
							" unicodeChar=" + unicodeChar +
							" ecma94code=" + ecma94code);
				
				Log.d(getTAG(), "convertMultibyteCharsToByteArray: rel unicodeBytes=");
				log(unicodeChar.getBytes("UNICODE"));
				
			}
			return arr;
			
		} catch(Exception ex){
			Log.e(getTAG(), "catch in convertMultibyteCharsToByteArray. text=" + text + ". Error: " + ex);
			byte[] retval = {0x00};
			return retval;
		}
	}
	
	/**
	 * print ECMA-94 table. It is a test.
	 */
	@Override
	public void printECMA94(){
		byte[] arr = new byte[16];
		int cnt = 0;
		for (com.multimedia.aes.gestnet_nucleo.printer_0554_0553.ECMA94 i : ECMA94.values()){
			int ch = i.getTestobyte();
			arr[cnt] = (byte) ch;
			cnt++;
			
			if (cnt == 16){
				cnt = 0;
				spoolerThread.send(arr, CR, LF);
			}
		}
	}
	
	/**
	 * Creates a spooler  thread for communication with printer  
	 * @author aks
	 *
	 */
	 class SpoolerThread extends Thread {
		 /**
		  * run thread
		  */
		public void run() {
			while (true) {
				while (getSpooler().size() > 0) {
					if (getPrinter().isReady()) {
						if (getPrinter().wasReseted()) {
							setUnderlined(underlined);
							paperFeedButton(paperFeedButtonStatus);
							setTextAboveBarcode(textAboveBarcode);
						}
						synchronized (getSpooler()) {
							getPrinter().send(getSpooler().get(0));
							log(getSpooler().get(0));
							getSpooler().remove(0);
						}
					}
				}
				try {
					sleep(100);
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
		 * 			graphic byte[]
		 * @return Success or no Success 
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
		 * @return Success or no Success
		 */
		public boolean send(byte[] data, byte... bytes) {
			boolean success = false;
			synchronized (getSpooler()) {
				if (getSpooler().size() <= maxSpoolerSize) {
					getSpooler().add(concat(data, bytes));
					success = true;
					spoolerPercentageFull = getSpooler().size() / maxSpoolerSize;
				}
			}
			return success;
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
			System.arraycopy(array2, 0, concatedArray, array1.length, array2.length);
			return concatedArray;
		}
	
	}
}
