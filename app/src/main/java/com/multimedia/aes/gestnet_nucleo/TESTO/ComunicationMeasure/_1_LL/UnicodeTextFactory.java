package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._1_LL;

import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.TransmissionFormat;

import java.io.UnsupportedEncodingException;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * help class to convert incoming list of string idents as TransmissionFormat into text
 * @author sergey.zaburunov
 *
 */
public class UnicodeTextFactory {

	/**
	 * class tag for debugging
	 */
	private static final String TAG = "UnicodeTextFactory";
	
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
	 * text of string idents
	 */
	private String identText = "";

	/**
	 * constructor converts list of TransmissionFormats into byte array of params
	 * @param list list of incoming measures
	 * @throws IllegalFormatException if something failed
	 */
	public UnicodeTextFactory(List<TransmissionFormat> list) throws IllegalFormatException{
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

		if (D){
			String s = "";
			for (byte b : allParams)
				s += b + ";";
			Log.d(TAG, "UnicodeTextFactory allParams=" + s );
		}
		
		initText();
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

	
	
	/**
	 * initialize string ident text
	 */
	private void initText(){
		if (this.allParams == null)
			return;
		
		// loop through all params
		for (int i = 0; i < this.paramsAmount; i++){
			int startIndex = i * 2;
			
			// are next 2 bytes in array?
			if ((startIndex >= this.allParams.length) || (startIndex + 2 > this.allParams.length))
				break;
			
			byte[] data_param = new byte[2];
			
			try{
				System.arraycopy(this.allParams, startIndex, data_param, 0, 2);
			} catch(Exception ex){
				Log.e(TAG, "initValues position=" + i + ". Error: " + ex );
			}
			
			identText += getStringFromUnicode(data_param);
		}
		
	}
	
	/**
	 * convert bytes to text
	 * @param data_param bytes
	 * @return text
	 */
	private String getStringFromUnicode(byte[] data_param){
		try {
			if (data_param == null) return "";
			
			// revert the array data_param
			byte[] arrStr = new byte[data_param.length];
			int cnt = arrStr.length;
			for(byte b : data_param){
				cnt--;
				arrStr[cnt] = b;
			}
			
			String retval = new String(arrStr, "UNICODE");
			return retval;
		} catch (UnsupportedEncodingException ex) {
			Log.e(TAG, "getStringFromUnicode cannot convert bytes to string: " + data_param + ". Error: " + ex );
			return "";
		}
	}
	
	/**
	 * get text of string idents
	 * @return
	 */
	public String getIdentText(){
		return identText;
	}
}
