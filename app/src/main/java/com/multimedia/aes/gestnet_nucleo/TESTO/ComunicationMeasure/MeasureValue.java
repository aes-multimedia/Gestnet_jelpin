/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.Idents;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.InvalidValues;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.MeasurerTypes;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.Units;

import java.util.IllegalFormatException;

/**
 * presentation of incoming measure value by parsing the measure array
 * @author sergey.zaburunov
 */
public class MeasureValue {

	/**
	 * incoming measure value as byte array
	 */
	private final byte[] message;
	
	/**
	 * type of from measurer (330-1-LL, 330-2-LL)
	 */
	private final MeasurerTypes measurerType; 
	
	private final int offset;
	
	/**
	 * constructor
	 * @param message data portion from measurer
	 * @param measurerType type of from measurer (330-1-LL, 330-2-LL)
	 */
	public MeasureValue(byte[] message, MeasurerTypes measurerType){
		this.message = message;
		this.measurerType = measurerType;
		this.offset = (this.measurerType == MeasurerTypes.Testo_330_2_LL) ? 1 : 0; 
	}
	
	/**
	 * get connector info
	 * @return connector info byte
	 */
	public byte getConnectorInfo(){
		if (this.measurerType == MeasurerTypes.Testo_330_2_LL)
			return message[0];
		else return 0;
	}
	
	/**
	 * get identId
	 * @return identId long 
	 */
	public long getIdentId(){
		return ConvertHelper.convertFourBytesToLong(
				message[0 + this.offset], 
				message[1 + this.offset], 
				message[2 + this.offset], 
				message[3 + this.offset]);
	}

	/**
	 * get ident bytes
	 * @return ident byte array 
	 */
	public byte[] getIdentBytes(){
		byte[] retval = new byte[]{
				message[0 + this.offset], 
				message[1 + this.offset], 
				message[2 + this.offset], 
				message[3 + this.offset]};
		return retval;
	}

	/**
	 * get identId as item of Idents
	 * @return identId
	 */
	public Idents getIdent(){
		int identId = (int) getIdentId();
		Idents retval = Idents.getItemByCode(identId);
		return retval;
	}

	/**
	 * get measure value as float
	 * @return measure value
	 */
	public float getMeasureValueAsFloat(){
		int value = ConvertHelper.convertFourBytesToInt(
				message[4 + this.offset], 
				message[5 + this.offset], 
				message[6 + this.offset], 
				message[7 + this.offset]);
		
		return ConvertHelper.convertIntBitsToFloat(value);
	}

	/**
	 * get invalid representation. If value is valid, then null
	 * @return invalid representation or null
	 */
	public String getInvalidRepresentation(){
		int value = ConvertHelper.convertFourBytesToInt(
				message[4 + this.offset], 
				message[5 + this.offset], 
				message[6 + this.offset], 
				message[7 + this.offset]);

		InvalidValues retval = InvalidValues.getItemByCode(value);
		
		if (retval == null) 
			return null;
		
		return retval.getRepresentation();
	}
	
	/**
	 * get measure value as formatted string according to precision, 
	 * invalid representation and NaN/Infinite-Check
	 * @return measure value as string
	 * @throws IllegalFormatException if something failed (e.g. converting)
	 */
	public String getMeasureValueAsFormattedString() throws IllegalFormatException {
		
		String invalidRepresentation = getInvalidRepresentation();
		if (invalidRepresentation != null)
			return invalidRepresentation;
		
		float fValue = getMeasureValueAsFloat();
		
		if (Float.isNaN(fValue))
			return "----";
		if (Float.isInfinite(fValue))
			return "----";
		
		byte precision = getPrecision();
		int pos = (precision < 0)? (-1) * precision : precision;
		
		String floatFormat = "%." + pos + "f";
		String retval = String.format(floatFormat, fValue);
		return retval;
	}

	/**
	 * get unitId
	 * @return unitId as int
	 */
	public int getUnitId(){
		return ConvertHelper.convertTwoBytesToInt(
				message[8 + this.offset], 
				message[9 + this.offset]);
	}

	/**
	 * get unit bytes
	 * @return unit byte array 
	 */
	public byte[] getUnitBytes(){
		byte[] retval = new byte[]{
				message[8 + this.offset], 
				message[9 + this.offset]};
		return retval;
	}

	/**
	 * get unitId as item of Units
	 * @return initId
	 */
	public Units getUnit(){
		int unitId = getUnitId();
		Units retval = Units.getItemByCode(unitId);
		return retval;
	}
	
	/**
	 * get precision
	 * @return precision
	 */
	public byte getPrecision(){
		return message[10 + this.offset];
	}

	/**
	 * get dilution factor
	 * @return dilution factor
	 */
	public double getDilutionFactor(){
		long value = ConvertHelper.convertFourBytesToLong(
				message[11 + this.offset], 
				message[12 + this.offset], 
				message[13 + this.offset], 
				message[14 + this.offset]);
		return ConvertHelper.convertLongBitsToDouble(value);
	}
	
	/**
	 * presentation of measure value to String
	 * @return object as string
	 */
	public String toString(){
		final StringBuilder result = new StringBuilder();
		return result.append(this.getClass().getName() + " {")
				.append(" message: " + ConvertHelper.convertByteArrayToHexString(message))
				.append(" IdentId: " + getIdentId())
				.append(" Ident: " + getIdent())
				.append(" MeasureValue: float=" + getMeasureValueAsFloat() + " string=" + getMeasureValueAsFormattedString())
				.append(" UnitId: " + getUnitId())
				.append(" Unit: " + getUnit())
				.append(" Precision: " + getPrecision())
				.append(" DilutionFactor: " + getDilutionFactor())
				.toString();
	} // toString
	
	
}
