/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure;

import android.util.Log;

/**
 * help convert functions
 * @author sergey.zaburunov
 *
 */
public class ConvertHelper {

	/**
	 * class tag for Debugging
	 */
	private static final String TAG = "ConvertHelper";
	
	/**
	 * debugging flag. Please set to false before release 
	 */
	private static final boolean D = true;

	/**
	 * hex symbols array
	 */
	private static final byte[] Hexhars = { '0', '1', '2', '3', '4', '5', '6',
			'7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };

	/**
	 * convert byte array to hex string
	 * @param b byte array
	 * @return hex string
	 */
	public static String convertByteArrayToHexString(byte[] b) {

		if (b == null) return null;
		
		StringBuilder s = new StringBuilder(4 * b.length);

		for (int i = 0; i < b.length; i++) {

			int v = b[i] & 0xff;

			s.append("0x");
			s.append((char) Hexhars[v >> 4]);
			s.append((char) Hexhars[v & 0xf]);
			s.append(" ");
		}

		return s.toString().trim();

	}

	/**
	 * convert byte to hex string
	 * @param b byte to convert
	 * @return hex string
	 */
	public static String convertByteToHexString(byte b) {

		StringBuilder s = new StringBuilder(4);

		int v = b & 0xff;

		s.append("0x");
		s.append((char) Hexhars[v >> 4]);
		s.append((char) Hexhars[v & 0xf]);
		s.append(" ");

		return s.toString().trim();

	}
	

	/**
	 * convert char array to byte array
	 * @param char_array char array to convert
	 * @return byte array
	 * @throws Exception if something failed
	 */
	public static byte[] convertCharArrayToByteArray(char[] char_array)
			throws Exception {

		byte[] retval = new byte[char_array.length];
		int i = 0;
		for (char c : char_array) {
			retval[i] = (byte) c;
			i++;
		}
		return retval;
	}

	/**
	 * convert int array to byte array, each int will loose 2 high bytes 
	 * @param int_array int array to convert
	 * @return byte array
	 * @throws Exception if something failed
	 */
	public static byte[] convertIntArrayToByteArray(int[] int_array)
			throws Exception {

		byte[] retval = new byte[int_array.length];
		int i = 0;
		for (int item : int_array) {
			retval[i] = (byte) item;
			i++;
		}
		return retval;
	}
	
	/**
	 * convert byte array to char array
	 * @param byte_array byte array to convert
	 * @return char array
	 * @throws Exception if something failed
	 */
	public static char[] convertByteArrayToCharArray(byte[] byte_array)
		throws Exception {

		char[] retval = new char[byte_array.length];
		int i = 0;
		for (byte b : byte_array) {
			retval[i] = (char) b;
			i++;
		}
		return retval;
	}
	
	/**
	 * convert 2 bytes to int
	 * @param low low byte
	 * @param high high byte
	 * @return int
	 */
	public static int convertTwoBytesToInt(byte low, byte high){
		if (D)
			Log.d(TAG, 
					"--> low=" + convertByteToHexString(low) + 
					" high=" + convertByteToHexString(high));
		
		int retval = 0;
		retval += convertByteToInt(high) << 8;
		retval += convertByteToInt(low)  << 0;
		return retval;
	}

	/**
	 * convert four bytes to long
	 * @param low low byte
	 * @param b2 second byte
	 * @param b3 third byte
	 * @param high high byte
	 * @return long
	 */
	public static long convertFourBytesToLong(byte low, byte b2, byte b3, byte high){

		long retval = 0;
		retval += convertByteToLong(high) << 24;
		retval += convertByteToLong(b3)   << 16;
		retval += convertByteToLong(b2)   << 8;
		retval += convertByteToLong(low)  << 0;
		return retval;
	}
	
	/**
	 * convert four bytes to int
	 * @param low low byte
	 * @param b2 second byte
	 * @param b3 third byte
	 * @param high high byte
	 * @return int
	 */
	public static int convertFourBytesToInt(byte low, byte b2, byte b3, byte high){

		int retval = 0;
		retval += convertByteToLong(high) << 24;
		retval += convertByteToLong(b3)   << 16;
		retval += convertByteToLong(b2)   << 8;
		retval += convertByteToLong(low)  << 0;
		return retval;
	}

	/**
	 * convert integer bits to float
	 * @param value int
	 * @return float
	 */
	public static float convertIntBitsToFloat(int value){
		return Float.intBitsToFloat(value);
	}
	
	/**
	 * convert long bits to double
	 * @param value long
	 * @return double
	 */
	public static double convertLongBitsToDouble(long value){
		return Double.longBitsToDouble(value);
	}

	
	/**
	 * convert byte to int by adding 2 high bytes with 0
	 * @param b byte to convert
	 * @return int
	 */
	public static int convertByteToInt(byte b) {
		return (int) b & 0xFF;
	}

	 
	/**
	 * convert byte to long by adding 6 high bytes with 0
	 * @param b byte to convert
	 * @return long
	 */
	public static long convertByteToLong(byte b) {
		return (long) b & 0xFF;
	}

}
