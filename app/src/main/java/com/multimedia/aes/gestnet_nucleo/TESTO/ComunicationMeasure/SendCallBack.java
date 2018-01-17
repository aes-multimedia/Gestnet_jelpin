/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

/**
 * call back interface for command to sent to measurer
 * @author sergey.zaburunov
 */
public interface SendCallBack {

	/**
	 * call back function will be called when the command will be sent
	 * @param buffer command to measurer
	 */
	public void send(byte[] buffer);
	
}
