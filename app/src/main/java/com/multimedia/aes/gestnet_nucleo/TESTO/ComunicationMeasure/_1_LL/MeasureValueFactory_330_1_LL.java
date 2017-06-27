/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._1_LL;

import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.MeasureValue;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.MeasureValueFactoryCommon;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.MeasureValueFactoryInterface;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.TransmissionFormat;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.MeasurerTypes;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * help class to convert incoming list of measure values as TransmissionFormat into values array for Testo 330-1-LL
 * @author sergey.zaburunov
 */
public class MeasureValueFactory_330_1_LL extends MeasureValueFactoryCommon implements MeasureValueFactoryInterface{

	/**
	 * class tag for debugging
	 */
	private static final String TAG = "330_1_LL";
	
	/**
	 * debugging flag
	 */
	private static final boolean D = true;

	
	/**
	 * constructor converts list of TransmissionFormats into byte array of params
	 * @param list list of incoming measures
	 * @throws IllegalFormatException if something failed
	 */
	public MeasureValueFactory_330_1_LL(List<TransmissionFormat> list)
		throws IllegalFormatException {
		super(list);
	}
	
	
	/**
	 * get all measures as array
	 * @return measures array
	 */
	public ArrayList<MeasureValue> getValues(){
		if (this.allParams == null)
			return null;
		ArrayList<MeasureValue> retval = new ArrayList<MeasureValue>();
		
		// loop through all params
		for (int i = 0; i < this.paramsAmount; i++){
			int startIndex = i * 15;
			
			// are next 15 bytes in array?
			if ((startIndex >= this.allParams.length) || (startIndex + 15 > this.allParams.length))
				break;
			
			byte[] data_param = new byte[15];
			System.arraycopy(this.allParams, startIndex, data_param, 0, 15);
			
			MeasureValue measureValue = new MeasureValue(data_param, MeasurerTypes.Testo_330_1_LL);
			
			if (D)
				Log.d(TAG, "getValues. measureValue=" + measureValue);
			
			retval.add(measureValue);
		}
		
		return retval;
	}
	

	/**
	 * get number of values
	 * @return amount of values
	 */
	public int getNumberOfValues(){
		float fAmount = this.paramsAmount / 15.0f;
		return (int) fAmount;
	}
	
	
}
