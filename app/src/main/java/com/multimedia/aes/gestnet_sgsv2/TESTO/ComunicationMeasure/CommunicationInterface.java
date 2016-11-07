/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure;

import android.os.Handler;

/**
 * Interface for Communicator class to implement the communication 
 * between framework and measurer on abstract level and provide 
 * a possibility to simple implementation of different sockets: bluetooth, usb, ...
 * at the moment there is only the bluetooth available  
 * @author sergey.zaburunov
 */
public interface CommunicationInterface {
	
	//
    // Constants that indicate the current connection state
	//
	
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
	 * Constants that indicate the current connection state as 'now disconnected from a remote device'
	 */
    public static final int STATE_DISCONNECTED = 4;


    
    /**
     * get state of communication interface
     * @return communication state such as
     * <ul>
     * <li> CommunicationInterface.STATE_NONE
     * <li> CommunicationInterface.STATE_LISTEN
     * <li> CommunicationInterface.STATE_CONNECTING
     * <li> CommunicationInterface.STATE_CONNECTED
     * </ul>
     */
	public int getState();
	
	/**
	 * start communication
	 */
	public void start();
	
	/**
	 * stop communication
	 */
	public void stop();
	
	/**
	 * Connects to a Testo Measurer 330. The type of connection depends on
	 * the Helper's implementation used.
	 * 
	 * @param device
	 *          Identifies the Printer to which the connection should be
	 *          established. Type of Identifier depends on Helper.
	 *          <ul>
	 *          <li>Bluetooth Helper: Bluetooth Device Object.
	 *          </ul>
	 */
	public void connect(Object device);
	
	/**
	 * Sends data to the measurer via Communicator. Each Helper provides a standard serial
	 * connection or a virtually transparent emulation of one.
	 * 
	 * @param out
	 *          Data to send to the measurer
	 */
	public void write(byte[] out);

	/**
	 * set handler from communicator to send all messages back
	 * @param handler
	 */
	public void setHandler(Handler handler);
	
}
