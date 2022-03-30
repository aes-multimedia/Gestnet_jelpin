/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.printer_0554_0553;

import android.os.Handler;


/**
 * Interface for different Connection Helpers. Currently only a Bluetooth Helper
 * is available.
 * 
 * @author Marco Brieden
 * 
 * @see BluetoothPrinterHelper
 */
public interface PrinterHelper {

	/**
	 * Connects to a Testo Printer 0554.0543. The type of connection depends on
	 * the Helper used.
	 * 
	 * @param device
	 *          Identifies the Printer to which the connection should be
	 *          established. Type of Identifier depends on Helper.
	 *          <ul>
	 *          <li>Bluetooth Helper: Hardware address of the Bluetooth Device.
	 *          </ul>
	 * @return False when connection attempt failed. Use getConnectionError() to
	 *         determine the cause.
	 */
	public boolean connectTo(String device);

	/**
	 * Used to determine if a connection is still valid. If the connection
	 * terminated unexpectedly use getConnectionError() to determine the cause.
	 * 
	 * @return Connection status
	 */
	public boolean isConnected();

	/**
	 * Returns the ready status of the printer. If the buffer is 3/4 full the
	 * printer will cease to report ready until the buffer empties to 1/4 or less.
	 * Before a controlled entry into sleep mode the printer will also report as
	 * not ready.
	 * 
	 * @return Ready status
	 */
	public boolean isReady();

	/**
	 * Returns true when the printer was probably reseted and it is prudent to
	 * resubmit setting which are lost when the printer enters sleep mode.
	 * 
	 * @return true if printer was reseted
	 */
	public boolean wasReseted();

	/**
	 * Returns the status byte of the printer or null if there is no connection.
	 * In case of an error condition a second error byte is returned.
	 * <p>
	 * The status byte is encoded bitwise:
	 * <ul>
	 * <li>bit 0 Head Up Sensor Active? [0= head OK 1= head up]
	 * <li>bit 1 Mechanism running [0= stopped 1= running]
	 * <li>bit 2 Data buffer completely empty? [0= not empty 1= empty]
	 * <li>bit 3 Paper Out Sensor Active? [0= paper OK 1= paper out]
	 * <li>bit 4 Reserved [Always 0]
	 * <li>bit 5 Spooling mode? [0= normal 1= spooling]
	 * <li>bit 6 Error [0= no error 1= error present]
	 * <li>bit 7 Always Set [Always 1]
	 * </ul>
	 * 
	 * The values of the error ID byte are as follows:
	 * <ul>
	 * <li>80H : Mechanism Voltage (Vmech) above upper limit
	 * <li>7FH : Mechanism Voltage (Vmech) below lower limit
	 * <li>40H : Mechanism Head Temperature above upper limit
	 * </ul>
	 * Other values are either not defined or represent internal controller
	 * hardware errors.
	 * 
	 * @return Status byte and in case of error a second error ID byte or null
	 */
	public byte[] getStatus();

	/**
	 * Sends data to the printer. Each Helper provides a standard serial
	 * connection or a virtually transparent emulation of one.
	 * 
	 * @param data
	 *          Data to send to the printer
	 * 
	 * @return False if the data could not be send. Use getConnectionError() to
	 *         determine the cause.
	 */
	public boolean send(byte[] data);

	/**
	 * Terminates the connection.
	 */
	public void disconnect();

	/**
	 * Returns the last encountered error or null and resets it.
	 * 
	 * @return Last encountered error or null
	 */
	public String[] getConnectionError();
	
	
	
	/**
	 * Constants that indicate the current connection state as 'we're doing nothing'
	 */
    public static final int STATE_NONE = 0; 
	/**
	 * Constants that indicate the current connection state as 'now listening for incoming connections'
	 */
    public static final int STATE_LISTEN = 1;
	/**
	 * Constants that indicate the current connection state as 'now initiating an outgoing connection'
	 */
    public static final int STATE_CONNECTING = 2;
	/**
	 * Constants that indicate the current connection state as 'now connected to a remote device'
	 */
    public static final int STATE_CONNECTED = 3;
	/**
	 * Constants that indicate the current connection state as 'now restoring an outgoing connection'
	 */
    public static final int STATE_RECONNECTING = 4;
	/**
	 * Constants that indicate the current connection state as 'disconnected'
	 */
    public static final int STATE_DISCONNECTED = 5;
	/**
	 * Constants that indicate the current connection state as 'cannot connect'
	 */
    public static final int STATE_CANNOT_CONNECT = 6;
	/**
	 * Constants that indicate the current connection state as 'cannot disconnect'
	 */
    public static final int STATE_CANNOT_DISCONNECT = 7;

	/**
	 * Set handler to listen communication notifications
	 * @param handler external handler, what must be implemented
	 */
	public void setHandler(Handler handler);
	
	/* 
	 * Get real devicename 
	 */
	public String name(String device);

}
