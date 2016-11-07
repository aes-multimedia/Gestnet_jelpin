/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure;

import android.util.Log;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * help class to convert incoming list of measure values as TransmissionFormat into values array
 * @author sergey.zaburunov
 */
public class MeasureValueFactoryCommon {

	/**
	 * class tag for debugging
	 */
	private static final String TAG = "MeasureValueFactory";
	
	/**
	 * debugging flag
	 */
	protected static final boolean D = true;

	/**
	 * container for incoming measures
	 */
	protected final List<TransmissionFormat> list; 
	
	/**
	 * array for all measure values
	 */
	protected final byte[] allParams;
	
	/**
	 * amount of params in incoming message
	 */
	protected int paramsAmount = 0;
	
	/**
	 * constructor converts list of TransmissionFormats into byte array of params
	 * @param list list of incoming measures
	 * @throws IllegalFormatException if something failed
	 */
	public MeasureValueFactoryCommon(List<TransmissionFormat> list) throws IllegalFormatException{
		this.list = list;

		if (this.list == null){ 
			allParams = null;
			return;
		}

		this.paramsAmount = getParamsAmount();
		allParams = new byte[paramsAmount] ;
		int cnt = 0;
		int currentPositionInDestinationArray = 0;
		for (TransmissionFormat tf : this.list){
			cnt++;
			if (tf == null) continue;
			
			int sourceOffset = 0;
			int sourceLength = tf.getParams().length;
			if (cnt == 1){
				sourceOffset = 4;
				sourceLength -= 4;
			}
			
			System.arraycopy(tf.getParams(), sourceOffset, 
					allParams, currentPositionInDestinationArray, sourceLength);
			
			currentPositionInDestinationArray += sourceLength;
		}
	}
	
	/**
	 * get amount of all params
	 * @return amount
	 */
	private int getParamsAmount(){
		if (this.list == null){ 
			return 0;
		}

		int retval = 0;
		int cnt = 0;
		for (TransmissionFormat tf : this.list){
			cnt++;
			if (tf == null) continue;
			int size = tf.getParams().length;
			if (cnt == 1)
				size -= 4;
			retval += size;
		}
		
		if (D)
			Log.d(TAG, "getParamsAmount params=" + retval );
		
		return retval;
	}
	
	
}
