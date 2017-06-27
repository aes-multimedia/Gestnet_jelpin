/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

import java.util.List;

/**
 * call back interface for measurer's reply
 * @author sergey.zaburunov
 *
 */
public interface ReplyCallBack {

	/**
	 * call back for reply
	 * @param tfList list of measures
	 */
	public void reply(List<TransmissionFormat> tfList);
	
}
