/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure;

import android.util.Log;

import java.util.IllegalFormatException;
import java.util.List;

/**
 * presentation of incoming TFUELFIX structure by parsing the measurer reply after GetFuelCoeff
 * @author sergey.zaburunov
 */
public class TFuelFixFactory {

	/**
	 * class tag for debugging
	 */
	private static final String TAG = "TFuelFixFactory";
	
	/**
	 * debugging flag
	 */
	private static final boolean D = true;

	/**
	 * container for incoming measures
	 */
	private final List<TransmissionFormat> list; 
	
	/**
	 * array for all measure values
	 */
	private final byte[] allParams;
	
	/**
	 * amount of params in incoming message
	 */
	private int paramsAmount = 0;

	/**
	 * constructor converts list of TransmissionFormats into byte array of params
	 * @param list list of incoming data
	 * @throws IllegalFormatException if something failed
	 */
	public TFuelFixFactory(List<TransmissionFormat> list) throws IllegalFormatException{
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
				sourceOffset = 11;
				sourceLength -= 11;
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
				size -= 11;
			retval += size;
		}
		
		if (D)
			Log.d(TAG, "getParamsAmount params=" + retval);
		
		return retval;
	}
	
	
	/**
	 * get TFuelFix structure
	 * @return TFuelFix
	 */
	public TFuelFix getTFuelFix(){
		if (this.allParams == null)
			return null;
		if (this.allParams.length < 8)
			return null;

		TFuelFix retval;

		try{
			float co2max = ConvertHelper.convertIntBitsToFloat(
					ConvertHelper.convertFourBytesToInt(
							this.allParams[0], 
							this.allParams[1], 
							this.allParams[2], 
							this.allParams[3]));
			float o2ref = ConvertHelper.convertIntBitsToFloat(
					ConvertHelper.convertFourBytesToInt(
							this.allParams[4], 
							this.allParams[5], 
							this.allParams[6], 
							this.allParams[7]));
			retval = new TFuelFix(co2max, o2ref);
		} catch(Exception ex){
			Log.e(TAG, "getTFuelFix. Cannot convert incoming data to TFuelFix structure. ", ex);
			retval = null;
		}
		
		return retval;
	}

	
}
