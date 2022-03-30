package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure;

public class CommandFactoryCommon {

	/**
	 * address byte (1. byte) 
	 */
	private static int[] send_address_byte = { 0x12 };

	/**
	 * block number bytes (2. and 3. bytes)
	 */
	private static int[] send_blockNo = { 0x00, 0x00 };

	/**
	 * block number total bytes (4. and 5. bytes)
	 */
	private static int[] send_blockNo_total = { 0x00, 0x01 };

	/**
	 * command prefix: first 5. bytes of a command
	 */
	private static int[] send_prefix = concat(
			concat(send_address_byte, send_blockNo),
			send_blockNo_total);

	/**
	 * status byte as acknowledgment
	 */
	private static int[] send_status_byte = { 0x80 };

	/**
	 * OK value of acknowledge byte
	 */
	private static int[] send_acknowledge_ok_byte = { 0x0F };

	/**
	 * ERROR value of acknowledge byte
	 */
	private static int[] send_acknowledge_fail_byte = { 0xFF };
	
	/**
	 * Acknowledgment command with OK
	 */
	protected static int[] send_acknowledgement_ok = concat(
			concat(send_address_byte, send_status_byte),
			send_acknowledge_ok_byte);

	/**
	 * Acknowledgment command with ERROR
	 */
	protected static int[] send_acknowledgement_fail = concat(
			concat(send_address_byte, send_status_byte),
			send_acknowledge_fail_byte);

	
	/**
	 * concatenate two int arrays
	 * @param array1 first array
	 * @param array2 last array
	 * @return concatenated array
	 */
	protected static int[] concat(int[] array1, int[] array2) {
		int[] retval = new int[array1.length + array2.length];
		System.arraycopy(array1, 0, retval, 0, array1.length);
		System.arraycopy(array2, 0, retval, array1.length, array2.length);

		return retval;
	}

	
	/**
	 * add prefix (first 6 bytes) and suffix (empty 2 bytes for CRC) to a command
	 * @param command command with params only
	 * @return command with full length
	 */
	protected int[] setPrefixAndSuffix(int[] command) {
		if (command == null)
			return null;
		int[] length_byte = { command.length };
		int[] suffix = { 0x00, 0x00 }; // empty place for 2 bytes to fill out CRC later

		int[] retval = concat(concat(
				concat(send_prefix, length_byte), command),
				suffix);
		return retval;
	} // setPrefixAndSuffix

	
	/**
	 * build command to send to measurer
	 * @param command command with params only
	 * @return ready to send command
	 */
	protected byte[] buildMessage(int[] command) {
		if (command == null)
			return null;

		// add prefix and suffix
		int[] int_array = setPrefixAndSuffix(command);

		// calculate and add CRC
		CRCCalc crc = new CRCCalc();
		crc.createCRC(int_array, int_array.length);

		// convert to byte array
		byte[] retval;
		try {
			retval = ConvertHelper.convertIntArrayToByteArray(int_array);
		} catch (Exception ex) {
			retval = null;
		}

		return retval;
	} // buildMessage

	
}
