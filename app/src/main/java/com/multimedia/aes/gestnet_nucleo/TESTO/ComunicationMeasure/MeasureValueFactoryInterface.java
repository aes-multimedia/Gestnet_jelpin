/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

import java.util.ArrayList;

/**
 * This Interface defines all functions needed to be implemented 
 * for measure value factories of the measurers 330-1-LL and 330-2-LL.
 * The functions help to convert incoming list of measure values as TransmissionFormat into values array
 * @author sergey.zaburunov
 *
 */
public interface MeasureValueFactoryInterface {

	/**
	 * get all measures as array
	 * @return measures array
	 */
	public ArrayList<MeasureValue> getValues();
	
	/**
	 * get number of values
	 * @return amount of values
	 */
	public int getNumberOfValues();


}
