package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._1_LL;

import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.ConvertHelper;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.TransmissionFormat;

import java.util.HashMap;
import java.util.IllegalFormatException;
import java.util.List;

/**
 * help class to convert incoming list of all view idents as TransmissionFormat into hash map
 * @author sergey.zaburunov
 *
 */
public class AllViewIdentsFactory {

	/**
	 * class tag for debugging
	 */
	private static final String TAG = "AllViewIdentsFactory";
	
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
	 * hash map for all parsed view indents
	 */
	private HashMap<Long, byte[]> viewIdents = new HashMap<Long, byte[]>(); 
	
	/**
	 * constructor converts list of TransmissionFormats into byte array of params
	 * @param list list of incoming measures
	 * @throws IllegalFormatException if something failed
	 */
	public AllViewIdentsFactory(List<TransmissionFormat> list) throws IllegalFormatException{
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
		
		initValues();
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
	 * initialize all view idents
	 */
	private void initValues(){
		if (this.allParams == null)
			return;
		
		viewIdents.clear();
		
		// loop through all params
		for (int i = 0; i < this.paramsAmount; i++){
			int startIndex = i * 12;
			
			// are next 12 bytes in array?
			if ((startIndex >= this.allParams.length) || (startIndex + 12 > this.allParams.length))
				break;
			
			byte[] data_param = new byte[12];
			
			try{
				System.arraycopy(this.allParams, startIndex, data_param, 0, 12);
			} catch(Exception ex){
				Log.e(TAG, "initValues position=" + i + ". Error: " + ex );
			}
			
			addIdent(data_param);
		}
		
	}
	
	
	/**
	 * add pair Ident-StringIdent into hash map
	 * @param data_param
	 */
	private void addIdent(byte[] data_param){
		if (data_param == null)
			return;

		try{
			long ident = ConvertHelper.convertFourBytesToLong(
					data_param[0], 
					data_param[1], 
					data_param[2], 
					data_param[3]);
			
			byte[] stringIdent = new byte[2];
			System.arraycopy(data_param, 8, stringIdent, 0, 2);
			
			viewIdents.put(ident, stringIdent);
			
		} catch(Exception ex){
			Log.e(TAG, "addIdent data_param=" + data_param + ". Error: " + ex );
		}
	}
	
	/**
	 * get number of values
	 * @return amount of values
	 */
	public int getNumberOfValues(){
		float fAmount = this.paramsAmount / 12.0f;
		return (int) fAmount;
	}

	/**
	 * get string ident by view ident
	 * @param ident view ident
	 * @return string ident
	 */
	public byte[] getStringIdent(long ident){
		return viewIdents.get(ident);
	}
	
	/**
	 * get hash map (view idents, string idents)
	 * @return hash map
	 */
	public HashMap<Long, byte[]> getHashMap(){
		return viewIdents;
	}
	
}
