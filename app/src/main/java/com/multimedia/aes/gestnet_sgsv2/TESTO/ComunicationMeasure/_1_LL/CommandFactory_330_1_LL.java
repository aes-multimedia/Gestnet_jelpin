/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure._1_LL;

import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommandFactoryCommon;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommandInterface;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.ConvertHelper;


/**
 * Command Factory class creates all commands to send to measurer testo-330-1-LL
 * @author sergey.zaburunov
 *
 */
public class CommandFactory_330_1_LL extends CommandFactoryCommon implements CommandInterface{

	/**
	 * commands list (params part only)
	 * @author sergey.zaburunov
	 *
	 */
	private enum Command {
		/* identification */GET_IDENT(new int[] { 0x42 }),
		
		/* Set / Clear Slave Mode */GET_SET_SLAVE_MODE(new int[] { 0x20, 0x00 }),
		/* device type */GET_DEVICETYPE(new int[] { 0x85 }),
		/* read all actual measure values */GetAllFlueGasVal(new int[] { 0x9C }),
		/* read all possible ViewIdents */GetAllViewIdents(new int[] { 0xA7, 0x02, 0x00, 0x00, 0x00 }),
		/* get unicode text */GetUnicodeText(new int[] { 0xB1, 0x00, 0x00 });
		

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
	 * build command GetNumberViewValues (not used)
	 * @return ready to send command
	 */
	public byte[] getNumberViewValues() {
		return null;
	}

	/**
	 * build command GetViewValues
	 * @param param3 not used
	 * @return ready to send command
	 */
	public byte[] getViewValues(int param3) {
		return getViewValues();
	}

	/**
	 * build command GetViewValues
	 * @return ready to send command
	 */
	public byte[] getViewValues() {
		int[] command = Command.GetAllFlueGasVal.getCommand();
		return buildMessage(command);
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
	 * build device type command
	 * @return ready to send command
	 */
	public byte[] getDeviceType() {
		return buildMessage(Command.GET_DEVICETYPE.getCommand());
	}

	/**
	 * build command GET_SERIALNUMBER (not used)
	 * @return ready to send command
	 */
	public byte[] getSerialNumber() {
		return null;
	}

	/**
	 * build command GET_FIRMWAREVERSION (not used)
	 * @return ready to send command
	 */
	public byte[] getFirmwareVersion() {
		return null;
	}

	/**
	 * build command SetMeasureMode (not used)
	 * @param param3 3. parameter: MeasMode (E_MEAS_FLUEGAS = 0x01)
	 * @return ready to send command
	 */
	public byte[] setMeasureMode(int param3) {
		return null;
	}

	/**
	 * build command GET_SMOKEPUMPNR (not used)
	 * @return ready to send command
	 */
	public byte[] getSmokePumpNr() {
		return null;
	}

	/**
	 * build command GET_FUELID (not used)
	 * @return ready to send command
	 */
	public byte[] getFuelId() {
		return null;
	}
	
	/**
	 * build command GetFuelCoeff (not used)
	 * @param param_3_4 3. und 4. parameter: ID of fuel (from GetFuelId)
	 * @return ready to send command
	 */
	public byte[] getFuelCoeff(int param_3_4) {
		return null;
	}
	

	/**
	 * build command GetMeasureCacheCounter (not used)
	 * @return ready to send command
	 */
	public byte[] getMeasureCacheCounter() {
		return null;
	}

	/**
	 * build command GetMeasCacheNumberOfValues (not used)
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheNumberOfValues(int param3) {
		return null;
	}
	
	/**
	 * build command GetMeasCacheValues (not used)
	 * @param param3 3. parameter: Number of partial measurement (0,..., GetMeasCacheCounter - 1)
	 * @return ready to send command
	 */
	public byte[] getMeasCacheValues(int param3) {
		return null;
	}

	/**
	 * build command Set / Clear Slave Mode.
	 * SET SLAVE mode is always the first command, the CLEAR SLAVE Mode always the last command. 
	 * During the slave mode, the testo 330 cannot be controlled by the user.
	 * @param param3 3. parameter: 0 = Clear Slave Mode, 1 = Set Slave Mode
	 * @return ready to send command
	 */
	public byte[] setSlaveMode(int param3) {
		int[] command = Command.GET_SET_SLAVE_MODE.getCommand();
		command[command.length - 1] = param3;
		return buildMessage(command);
	}

	/**
	 * build command GetAllViewIdents
	 * @return ready to send command
	 */
	public byte[] getAllViewIdents() {
		return buildMessage(Command.GetAllViewIdents.getCommand());
	}
	
	/**
	 * build command GetUnicodeText
	 * @param param2 2. parameter: String Ident, low byte
	 * @param param3 3. parameter: String Ident, high byte
	 * @return ready to send command
	 */
	public byte[] getUnicodeText(int param2, int param3) {
		int[] command = Command.GetUnicodeText.getCommand();
		command[command.length - 2] = param2;
		command[command.length - 1] = param3;
		return buildMessage(command);
	}

	
}
