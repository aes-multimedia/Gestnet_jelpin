/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

/**
 * Command Factory Interface defines all commands to send to measurer testo-330.
 * It is an abstract basic for implementation of command factories for 
 * measurers 330-1-LL and 330-2-LL.
 * @author sergey.zaburunov
 */
public interface CommandInterface {
	
	/**
	 * get command by command index. index is corresponding to enum Command
	 * @param commandIndex command index
	 * @return ready to send command
	 */
	public byte[] getCommand(int commandIndex);
	
	/**
	 * build identification command
	 * @return ready to send command
	 */
	public byte[] getIdentification();
	
	/**
	 * build command GetNumberViewValues
	 * @return ready to send command
	 */
	public byte[] getNumberViewValues();
	
	/**
	 * build command GetViewValues with filter ALL
	 * @return ready to send command
	 */
	public byte[] getViewValues();

	/**
	 * build command GetViewValues
	 * @param param3 3. parameter: Filter (ALL=0x00, USER=0x01, MEAS=0x02, NONDUPLICATE=0x03)
	 * @return ready to send command
	 */
	public byte[] getViewValues(int param3);
	
	/**
	 * build command Acknowledgement
	 * @param isOk command parameter: true=Ok, false=ERROR
	 * @return ready to send command
	 */
	public byte[] getAcknowledgement(boolean isOk);

	/**
	 * build device type command
	 * @return ready to send command
	 */
	public byte[] getDeviceType();

	/**
	 * build command GET_SERIALNUMBER
	 * @return ready to send command
	 */
	public byte[] getSerialNumber();
	
	/**
	 * build command GET_FIRMWAREVERSION
	 * @return ready to send command
	 */
	public byte[] getFirmwareVersion();
	
	/**
	 * build command SetMeasureMode
	 * @param param3 3. parameter: MeasMode (E_MEAS_FLUEGAS = 0x01)
	 * @return ready to send command
	 */
	public byte[] setMeasureMode(int param3);
	
	/**
	 * build command GET_SMOKEPUMPNR
	 * @return ready to send command
	 */
	public byte[] getSmokePumpNr();
	
	/**
	 * build command GET_FUELID
	 * @return ready to send command
	 */
	public byte[] getFuelId();
	
	/**
	 * build command GetFuelCoeff
	 * @param param_3_4 3. und 4. parameter: ID of fuel (from GetFuelId)
	 * @return ready to send command
	 */
	public byte[] getFuelCoeff(int param_3_4);

	/**
	 * build command GetMeasureCacheCounter
	 * @return ready to send command
	 */
	public byte[] getMeasureCacheCounter();
	
	/**
	 * build command GetMeasCacheNumberOfValues
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheNumberOfValues(int param3);
	
	/**
	 * build command GetMeasCacheValues
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheValues(int param3);

	/**
	 * build command Set / Clear Slave Mode.
	 * SET SLAVE mode is always the first command, the CLEAR SLAVE Mode always the last command. 
	 * During the slave mode, the testo 330 cannot be controlled by the user.
	 * @param param3 3. parameter: 0 = Clear Slave Mode, 1 = Set Slave Mode
	 * @return ready to send command
	 */
	public byte[] setSlaveMode(int param3);
	
	/**
	 * build command GetAllViewIdents
	 * @return ready to send command
	 */
	public byte[] getAllViewIdents();
	
	/**
	 * build command GetUnicodeText
	 * @param param2 2. parameter: String Ident, high byte
	 * @param param3 3. parameter: String Ident, low byte
	 * @return ready to send command
	 */
	public byte[] getUnicodeText(int param2, int param3);
	

}
