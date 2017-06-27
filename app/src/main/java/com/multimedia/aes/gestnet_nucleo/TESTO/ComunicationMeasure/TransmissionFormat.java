/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

/**
 * the class describes transmission format for incoming messages from measurer
 * @author sergey.zaburunov
 */
public class TransmissionFormat {

	/**
	 * incoming message as byte array
	 */
	private byte[] message;
	
	/**
	 * incoming message as char array
	 */
	private char[] messageAsChars;
	
	/**
	 * constructor
	 * @param message incoming message
	 * @throws Exception if something failed: 
	 * <UL>
	 * <LI> IllegalArgumentException if parameter format error
	 * <LI> CRCException if crc wrong
	 * </UL>
	 */
	public TransmissionFormat(byte[] message) throws Exception{
		if (message == null)
			throw new IllegalArgumentException("Parameter cannot be null");
		if (message.length == 3)
			throw new IllegalStateException();
		if (message.length < 9)
			throw new IllegalArgumentException("Parameter array is too short: " + message.length);
		
		char[] messageAsChars = ConvertHelper.convertByteArrayToCharArray(message);
		
		CRCCalc crc = new CRCCalc();
		if (crc.validateCRC(messageAsChars, messageAsChars.length) == false)
			throw new CRCException("Invalid CRC");
		
		this.message = message;
		this.messageAsChars = messageAsChars;
	}
	

	/**
	 * get address byte
	 * @return byte
	 */
	public byte getAddressByte(){
		return message[0];
	}

	/**
	 * get get high byte from BlockNo
	 * @return high byte
	 */
	public byte getHighByteBlockNo(){
		return message[1];
	}

	/**
	 * get low byte from BlockNo
	 * @return low byte
	 */
	public byte getLowByteBlockNo(){
		return message[2];
	}
	
	/**
	 * get blockNo as int
	 * @return blockNo
	 */
	public int getBlockNo(){
		byte low = getLowByteBlockNo();
		byte high = getHighByteBlockNo();
		return ConvertHelper.convertTwoBytesToInt(low, high);
	}

	/**
	 * get high byte of BlockNoTotal
	 * @return high byte
	 */
	public byte getHighByteBlockNoTotal(){
		return message[3];
	}

	/**
	 * get low byte of BlockNoTotal
	 * @return low byte
	 */
	public byte getLowByteBlockNoTotal(){
		return message[4];
	}

	/**
	 * get BlockNoTotal as int
	 * @return BlockNoTotal
	 */
	public int getBlockNoTotal(){
		byte low = getLowByteBlockNoTotal();
		byte high = getHighByteBlockNoTotal();
		return ConvertHelper.convertTwoBytesToInt(low, high);
	}
	
	/**
	 * get param quantity within block
	 * @return param quantity
	 */
	public byte getParamQuantityWithinBlock(){
		return message[5];
	}

	/**
	 * get all params as byte array
	 * @return params
	 */
	public byte[] getParams(){
		int amount = message.length - 6 - 2;
		byte[] retval = new byte[amount];
		System.arraycopy(message, 6, retval, 0, amount);
		
		return retval;
	}

	
	/**
	 * get original message as byte array
	 * @return the message
	 */
	public byte[] getMessage() {
		return message;
	}


	/**
	 * get original message as char array
	 * @return the messageAsChars
	 */
	public char[] getMessageAsChars() {
		return messageAsChars;
	}
	
}
