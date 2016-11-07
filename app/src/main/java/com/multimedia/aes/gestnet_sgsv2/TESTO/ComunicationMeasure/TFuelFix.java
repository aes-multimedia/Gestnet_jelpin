/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure;

/**
 * The class defines TFUELFIX structure 
 * @author sergey.zaburunov
 */
public class TFuelFix {

	/**
	 * co2 max value
	 */
	private float CO2max = 0.0f;
	
	/**
	 * O2 ref value
	 */
	private float O2ref = 0.0f;
	
	/**
	 * constructor
	 * @param cO2max co2 max value
	 * @param o2ref O2 ref value
	 */
	public TFuelFix(float cO2max, float o2ref) {
		CO2max = cO2max;
		O2ref = o2ref;
	}


	/**
	 * get co2 max value
	 * @return the cO2max
	 */
	public float getCO2max() {
		return CO2max;
	}

	/**
	 * get O2 ref value
	 * @return the o2ref
	 */
	public float getO2ref() {
		return O2ref;
	}
	
}
