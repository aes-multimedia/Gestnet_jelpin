/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure._2_LL;

import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommandFactoryCommon;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommandInterface;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.ConvertHelper;

/**
 * Command Factory class creates all commands to send to measurer testo-330-2-LL
 * @author sergey.zaburunov
 *
 */
public class CommandFactory_330_2_LL extends CommandFactoryCommon implements CommandInterface{

	/**
	 * commands list (params part only)
	 * @author sergey.zaburunov
	 *
	 */
	private enum Command {
		/* identification */GET_IDENT(new int[] { 0x42 }),
		/* device type */GET_DEVICETYPE(new int[] { 0x4F, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x10 }),
		/* serial number */GET_SERIALNUMBER(new int[] { 0x4F, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x21 }),
		/* firmware version */GET_FIRMWAREVERSION(new int[] { 0x4F, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x01, 0x21 }),
		/* measure mode */SET_MESAUREMODE(new int[] { 0x4F, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x21, 0xA5, 0x01 }),
		/* smoke pump nr */GET_SMOKEPUMPNR(new int[] { 0x4F, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x8A, 0x25 }),
		/* fuel id */GET_FUELID(new int[] { 0x4F, 0x00, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x02, 0x25, 0x00 }),
		/* fuel coeff */GET_FUELCOEFF(new int[] { 0x4F, 0x00, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x0B, 0x25, 0x64, 0x00 }),
		/* measure cache counter */GET_MEASCACHECOUNTER(new int[] { 0x4F, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x8B, 0x25 }),
		/* measure cache number of values */GET_MEASCACHENUMBEROFVALUES(
				new int[] { 0x4F, 0x00, 0x00, 0x00, 0x00, 0x00, 0x00, 0x8C,
						0x25, 0x00 }),
		/* measure cache values */GET_MEASCACHEVALUES(new int[] { 0x4F, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x8D, 0x25, 0x00 }),
		/* number view values */GET_NUMBERVIEWVALUES(new int[] { 0x4F, 0x00,
				0x00, 0x00, 0x00, 0x00, 0x00, 0x1A, 0x25, 0x00 }),
		/* view values */GET_VIEWVALUES(new int[] { 0x4F, 0x00, 0x00, 0x00,
				0x00, 0x00, 0x00, 0x1B, 0x25, 0x00 });

		/**
		 * container for a command
		 */
		private final int[] command;

		/**
		 * enum constructor
		 * @param command command to create
		 */
		Command(int[] command) {
			this.command = command;
		}

		/**
		 * get command (params part) to send to measurer testo-330
		 * @return params part of command as int array
		 */
		public int[] getCommand() {
			return this.command;
		}
	}; // enum


	
	/**
	 * get command by command index. index is corresponding to enum Command
	 * @param commandIndex command index
	 * @return ready to send command
	 */
	public byte[] getCommand(int commandIndex) {
		try{
			return buildMessage(Command.values()[commandIndex].getCommand());
		} catch(Exception ex){
			return null;
		}
	}
	
	/**
	 * build identification command
	 * @return ready to send command
	 */
	public byte[] getIdentification() {
		return buildMessage(Command.GET_IDENT.getCommand());
	}

	/**
	 * build device type command
	 * @return ready to send command
	 */
	public byte[] getDeviceType() {
		return buildMessage(Command.GET_DEVICETYPE.getCommand());
	}

	/**
	 * build command GetNumberViewValues
	 * @return ready to send command
	 */
	public byte[] getNumberViewValues() {
		return buildMessage(Command.GET_NUMBERVIEWVALUES.getCommand());
	}

	/**
	 * build command GetViewValues
	 * @param param3 3. parameter: Filter (ALL=0x00, USER=0x01, MEAS=0x02, NONDUPLICATE=0x03)
	 * @return ready to send command
	 */
	public byte[] getViewValues(int param3) {
		int[] command = Command.GET_VIEWVALUES.getCommand();
		command[command.length - 1] = param3;
		return buildMessage(command);
	}

	
	/**
	 * build command GetViewValues with filter ALL
	 * @return ready to send command
	 */
	public byte[] getViewValues() {
		return getViewValues(0x00);
	}
	
	
	/**
	 * build command Acknowledgement
	 * @param isOk command parameter: true=Ok, false=ERROR
	 * @return ready to send command
	 */
	public byte[] getAcknowledgement(boolean isOk){
		int[] int_array;
		if (isOk){
			int_array = send_acknowledgement_ok;
		} else {
			int_array = send_acknowledgement_fail;
		}
		
		// convert to byte array
		byte[] retval;
		try {
			retval = ConvertHelper.convertIntArrayToByteArray(int_array);
		} catch (Exception ex) {
			retval = null;
		}

		return retval;
	} // getAcknowledgement

	
	/**
	 * build command GET_SERIALNUMBER
	 * @return ready to send command
	 */
	public byte[] getSerialNumber() {
		return buildMessage(Command.GET_SERIALNUMBER.getCommand());
	}

	/**
	 * build command GET_FIRMWAREVERSION
	 * @return ready to send command
	 */
	public byte[] getFirmwareVersion() {
		return buildMessage(Command.GET_FIRMWAREVERSION.getCommand());
	}

	/**
	 * build command SetMeasureMode
	 * @param param3 3. parameter: MeasMode (E_MEAS_FLUEGAS = 0x01)
	 * @return ready to send command
	 */
	public byte[] setMeasureMode(int param3) {
		int[] command = Command.SET_MESAUREMODE.getCommand();
		command[command.length - 1] = param3;
		return buildMessage(command);
	}

	/**
	 * build command GET_SMOKEPUMPNR
	 * @return ready to send command
	 */
	public byte[] getSmokePumpNr() {
		return buildMessage(Command.GET_SMOKEPUMPNR.getCommand());
	}

	/**
	 * build command GET_FUELID
	 * @return ready to send command
	 */
	public byte[] getFuelId() {
		return buildMessage(Command.GET_FUELID.getCommand());
	}
	
	/**
	 * build command GetFuelCoeff
	 * @param param_3_4 3. und 4. parameter: ID of fuel (from GetFuelId)
	 * @return ready to send command
	 */
	public byte[] getFuelCoeff(int param_3_4) {
		int[] command = Command.GET_FUELCOEFF.getCommand();
		byte low = (byte) (param_3_4 & 0xFF);
		byte high = (byte) ((param_3_4 & 0xFF00) >> 16);
		command[command.length - 1] = high;
		command[command.length - 2] = low;
		return buildMessage(command);
	}
	

	/**
	 * build command GetMeasureCacheCounter
	 * @return ready to send command
	 */
	public byte[] getMeasureCacheCounter() {
		return buildMessage(Command.GET_MEASCACHECOUNTER.getCommand());
	}

	/**
	 * build command GetMeasCacheNumberOfValues
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheNumberOfValues(int param3) {
		int[] command = Command.GET_MEASCACHENUMBEROFVALUES.getCommand();
		command[command.length - 1] = param3;
		return buildMessage(command);
	}
	
	/**
	 * build command GetMeasCacheValues
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheValues(int param3) {
		int[] command = Command.GET_MEASCACHEVALUES.getCommand();
		command[command.length - 1] = param3;
		return buildMessage(command);
	}
	
	/**
	 * build command Set / Clear Slave Mode (not used)
	 * @param param3 3. parameter: 0 = Clear Slave Mode, 1 = Set Slave Mode
	 * @return ready to send command
	 */
	public byte[] setSlaveMode(int param3) {
		return null;
	}

	/**
	 * build command GetAllViewIdents (not used)
	 * @return ready to send command
	 */
	public byte[] getAllViewIdents() {
		return null;
	}
	
	/**
	 * build command GetUnicodeText (not used)
	 * @param param2 2. parameter: String Ident, high byte
	 * @param param3 3. parameter: String Ident, low byte
	 * @return ready to send command
	 */
	public byte[] getUnicodeText(int param2, int param3) {
		return null;
	}

	
}
