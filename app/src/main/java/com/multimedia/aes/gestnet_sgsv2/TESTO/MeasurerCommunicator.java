package com.multimedia.aes.gestnet_sgsv2.TESTO;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.BluetoothHelper;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.CommunicationInterface;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.Communicator;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.StateCallBack;
import com.multimedia.aes.gestnet_sgsv2.TESTO.ComunicationMeasure.enums.CommunicationErrors;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Testo;

/**
 * global measurer communicator class.
 * It is container for fields and state call back function for communication state
 * @author sergey.zaburunov
 */
public class MeasurerCommunicator {
	
	// Debugging
	private static final String TAG = "MeasurerCommunicator";
	private static final boolean D = true;


	/**
	 * Communicator implements the communication between framework and the measurer
	 */
	public Communicator communicator = null;

	/**
	 * instance of this class
	 */
	private static MeasurerCommunicator _instance = null;
	
	/**
	 * text view to show the connection status
	 */
	private TextView connStatus;

	/**
	 * Name of the connected device
	 */
	private String connectedDeviceName = null;

	/**
	 * context of owner activity
	 */
	private Context context = null;
	
	/**
	 * default timeout in milliseconds
	 */
	private int timeOutInMillis = 100000;


	/**
	 * singleton instantiator
	 * 
	 * @return _instance
	 */
	public static MeasurerCommunicator getInstance() {
		if (MeasurerCommunicator._instance == null) {
			synchronized (MeasurerCommunicator.class) {
				if (MeasurerCommunicator._instance == null) {
					MeasurerCommunicator._instance = new MeasurerCommunicator();
				}
			} // synchronized
		}
		return MeasurerCommunicator._instance;
	} // getInstance

	
	private MeasurerCommunicator(){
		if (D)
			Log.d(TAG, "+++ MeasurerCommunicator +++");
	}

	
	/**
	 * set activity context
	 * @param context the context to set
	 */
	public void setContext(Context context) {
		this.context = context;
	}

	/**
	 * initialize and start communicator
	 */
	public void initCommunicator(){
		if (D)
			Log.d(TAG, "+++ initCommunicator +++");

		if (communicator == null){
			communicator = new Communicator(new BluetoothHelper(), stateCallBack, timeOutInMillis);
			communicator.start();
		}

	}


	/**
	 * set connection status
	 * @param connStatus the connStatus to set
	 */
	public void setConnStatus(TextView connStatus) {
		this.connStatus = connStatus;
	}

	/**
	 * call back functions to handle messages from measurer
	 */
	private StateCallBack stateCallBack = new StateCallBack() {
		public void getState(int state) {
			if (D)
				Log.d(TAG, "stateCallBack. state=" + state);

			switch (state) {
			case CommunicationInterface.STATE_CONNECTED:
				break;
			case CommunicationInterface.STATE_CONNECTING:
				break;
			case CommunicationInterface.STATE_LISTEN:
			case CommunicationInterface.STATE_NONE:
				break;
			}
		}
		
		public void getDeviceName(String deviceName) {
			if (D)
				Log.d(TAG, "deviceCallBack. deviceName=" + deviceName);

			// save the connected device's name
			connectedDeviceName = deviceName;
		}

		public void getMessage(int errCode) {
			if (D)
				Log.d(TAG, "messageCallBack. errCode=" + errCode);
			
			CommunicationErrors item = CommunicationErrors.getItemByCode(errCode);
			String text = "Error text is not available";
			if (item != null)
				text = item.getText();

		}

	};


}
