/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure;

/**
 * call back interface for messages from measurer
 * @author sergey.zaburunov
 */
public interface StateCallBack {

	/**
	 * call back function will be called when communication state changing
	 * @param state new state
	 */
	public void getState(int state);
	
	/**
	 * call back function will be called when new device name coming
	 * @param deviceName new device name
	 */
	public void getDeviceName(String deviceName);

	/**
	 * call back function will be called when new error message coming
	 * @param errCode error message code
	 */
	public void getMessage(int errCode);
	
}
