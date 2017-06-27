/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums;

/**
 * This class defines invalid values from measurer
 * @author sergey.zaburunov
 */
public enum InvalidValues {
	Ext(0x0080, "-*-*-*", "Ext"),
	Overrange(0x0081, "-++++-", "Overrange"),
	Underrange(0x0082, "+----+", "Underrange"),
	Outrange(0x0083, "+-+-+-", "Outrange"),
	Defect(0x0084, "-", "Defect"),
	Empty(0x0085, "      ", "Empty"),
	Wakeup(0x008A, "******", "Wakeup");

	/**
	 * measure value
	 */
	private final int measureValue;
	/**
	 * representation of measure value
	 */
	private final String representation;
	/**
	 * description of measure value
	 */
	private final String description;

	/**
	 * constructor
	 * @param measureValue measure value
	 * @param representation representation of measure value
	 * @param description description of measure value
	 */
	InvalidValues(int measureValue, String representation, String description) {
		this.measureValue = measureValue;
		this.representation = representation;
		this.description = description;
	}

	/**
	 * get measure value
	 * @return measure value
	 */
	public int getMeasureValue() {
		return this.measureValue;
	}

	/**
	 * get representation of measure value
	 * @return representation
	 */
	public String getRepresentation() {
		return this.representation;
	}

	/**
	 * get description of measure value
	 * @return description
	 */
	public String getDescription() {
		return this.description;
	}

	/**
	 * get item by measure value
	 * @param number measure value
	 * @return item
	 */
	public static InvalidValues getItemByCode(int number){
		for (InvalidValues i : InvalidValues.values()){
			if (i.getMeasureValue() == number)
				return i;
		}
		return null;
	}

	/**
	 * is the value in enum?
	 * @param number measure value
	 * @return yes = true, no = false
	 */
	public static boolean isAvailable(int number){
		return (InvalidValues.getItemByCode(number) != null);
	}

	
}; // enum
