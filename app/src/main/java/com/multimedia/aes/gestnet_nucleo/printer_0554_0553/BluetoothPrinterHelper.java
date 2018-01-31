
package com.multimedia.aes.gestnet_nucleo.printer_0554_0553;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.os.Handler;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;


/**
 * Bluetooth helper class.
 * 
 * This class does all the work for setting up and managing Bluetooth
 * connections with printer.
 * 
 * @author Marco Brieden
 */
public class BluetoothPrinterHelper implements com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper {
    /** 
     * add debugging info into log output
     */
	private static final boolean D = true;

	/**
	 * Class tag for logging. It will be showing in logging output as corresponding info to this class
	 */
	private final String TAG = this.getClass().getName();

	/**
	 * bluetooth adapter
	 */
	private BluetoothAdapter btAdapter;
	
	/**
	 * bluetooth socket
	 */
	private BluetoothSocket socket;

	/**
	 * listening thread keeps listening to the InputStream while connected
	 */
	ListeningThread listeningThread;
	
	/**
	 * output stream
	 */
	private OutputStream outputStream;

	/**
	 * connected flag
	 */
	private boolean connected = false;
	
	/**
	 * ready flag
	 */
	private boolean ready = false;
	
	/**
	 * printer status
	 */
	private byte status = 0;
	
	/**
	 * error status
	 */
	private byte statusError = 0;
	
	/**
	 * connection error text
	 */
	private String[] connectionError = null;
	
	/**
	 * reseted flag
	 */
	private boolean reseted = false;
	
	/**
	 * last connected bluetooth device
	 */
	private BluetoothDevice lastDevice = null;
	
	/**
	 * external handler
	 */
	private Handler handler;

	/**
	 * get status command
	 */
	private static final byte[] GET_STATUS = new byte[] { 29, 5 };

	/**
	 * constructor.
	 * get default bluetooth adapter
	 */
	public BluetoothPrinterHelper() {
		btAdapter = BluetoothAdapter.getDefaultAdapter();
	}

	/**
	 * Set handler to listen communication notifications
	 * @param handler external handler, what must be implemented
	 */
	public void setHandler(Handler handler){
		this.handler = handler;
	}

	/**
	 * check connected status
	 * @return is connected?
	 */
	public boolean isConnected() {
		return connected;
	}

	/**
	 * logging output for debug
	 * @param message log message
	 */
	private void log(String message) {
		if (D) {
			Log.d(TAG, message);
		}
	}

	/**
	 * perform connect to device
	 * @param device device name
	 * @return is connected successful?
	 */
	public boolean connectTo(String device) {
		boolean success = false;
		log("searching for: " + device);

		if (isConnected()) {
			disconnect();
		}

		boolean found = false;
		for (BluetoothDevice btDevice : btAdapter.getBondedDevices()) {
			if (btDevice == null) continue;
			if (btDevice.getAddress() == null) continue;
			log(btDevice.getName() + " : " + btDevice.getAddress());
			if (btDevice.getAddress().equals(device)) {
				log("found device");
				success = connectTo(btDevice);
				log("device added?" + success);
				found = true;
				break;
			}
		}
		
		if (!found){
			try{
				log("add device");
				BluetoothDevice btDevice = btAdapter.getRemoteDevice(device);
				success = connectTo(btDevice);
				log("device added");
			} catch(IllegalArgumentException ex){
				Log.e(TAG, "connectTo(" + device + "). Cannot get remote device. Error: " + ex.toString());
				success = false;
			}
		}

		
		if (success){
			// inform client about success
			if (handler != null)
				handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_CONNECTED);
		} else{
			// inform client about fail
			if (handler != null)
				handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_CANNOT_CONNECT);
		}
		
		return success;
	}

	/**
	 * perform connect to device
	 * @param device bluetooth device
	 * @return is connected successful?
	 */
	public boolean connectTo(BluetoothDevice device) {
		
		if (handler != null)
			handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_CONNECTING);
		
		boolean success = false;

		// Always cancel discovery because it will slow down a connection
		btAdapter.cancelDiscovery();
		log("channel discoverd");
		tryToConnect(device);
		log("tryconnect");
		if (socket != null) {
			// Get the BluetoothSocket input and output streams
			try {
				InputStream inputStream = socket.getInputStream();
				outputStream = socket.getOutputStream();

				log("connected");
				connected = true;
				ready = true;

				listeningThread = new ListeningThread(inputStream);
				listeningThread.start();
				log("listeningThread started");

				success = true;
			} catch (IOException e) {
				setError("402 Error listening to Socket", e);
			}
		}
		return success;
	}

	/**
	 * try to connect to bluetooth socket
	 * @param device bluetooth device
	 * @return is connected successful?
	 */
	private boolean tryToConnect(BluetoothDevice device) {
		boolean success = false;
		BluetoothSocket testSocket = null;
		testSocket = getSocket(device);
		// Make a connection to the BluetoothSocket
		try {
			// This is a blocking call and will only return on a
			// successful connection or an exception
			if (testSocket != null){
				Log.d(TAG, "tryToConnect: bevor testconnct" );
				testSocket.connect();
				Log.d(TAG, "tryToConnect: after testconnct");
				success = true;
			}
		} catch (IOException e) {
			setError("401 Error connecting Socket", e);
			Log.e(TAG, "tryToConnect: unable connect to socket. ", e);
			// Close the socket
			try {
				testSocket.close();
				testSocket = null;
			} catch (IOException e2) {
				Log.e(TAG, "unable to close() socket during connection failure", e2);
				testSocket = null;
			}
		}
		lastDevice = device;
		this.socket = testSocket;
		return success;
	}

	/**
	 * get bluetooth socket
	 * @param device bluetooth device
	 * @return bluetooth socket or null, if failed
	 */
	private BluetoothSocket getSocket(BluetoothDevice device) {
		BluetoothSocket ret = null;
		try {
			// socket =
			// device.createRfcommSocketToServiceRecord(UUID.randomUUID());
			// above command causes "Service Discovery Failed" Exception on
			// Nexus S
			// workaround below from
			// http://stackoverflow.com/questions/3397071/android-bluetooth-service-discovery-failed-exception
//			Method m = device.getClass().getMethod("createInsecureRfcommSocket",
			Method m = device.getClass().getMethod("createInsecureRfcommSocket",
					new Class[] { int.class });
			ret = (BluetoothSocket) m.invoke(device, 1);
//			ret = device.createInsecureRfcommSocketToServiceRecord(UUID.randomUUID());
		} catch (SecurityException e) {
			setError("400 Error creating Socket", e);
			ret = null;
			Log.e(TAG, "unable create socket. ", e);
		} catch (NoSuchMethodException e) {
			setError("400 Error creating Socket", e);
			ret = null;
			Log.e(TAG, "unable create socket. ", e);
		} catch (IllegalArgumentException e) {
			setError("400 Error creating Socket", e);
			ret = null;
			Log.e(TAG, "unable create socket. ", e);
		} catch (IllegalAccessException e) {
			setError("400 Error creating Socket", e);
			ret = null;
			Log.e(TAG, "unable create socket. ", e);
		} catch (InvocationTargetException e) {
			setError("400 Error creating Socket", e);
			ret = null;
			Log.e(TAG, "unable create socket. ", e);
		} 
		return ret;
	}

	/**
	 * disconnect and close bluetooth socket
	 */
	public synchronized void disconnect() {
		log("disconnecting");
		if (isConnected()) {
			try {
				listeningThread = null;
				if (socket != null)
					socket.close();
				socket = null;
				outputStream = null;
				connected = false;
				ready = false;

				if (handler != null)
					handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_DISCONNECTED);

			} catch (IOException e) {
				Log.e(TAG, "close() of connect socket failed", e);
				if (handler != null)
					handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_CANNOT_DISCONNECT);
			}
		}
	}

	/**
	 * get connection error
	 * @return error text
	 */
	public String[] getConnectionError() {
		String[] ret = connectionError;
		connectionError = null;
		return ret;
	}

	/**
	 * get ready printer flag
	 * @return is ready?
	 */
	public boolean isReady() {
		return ready;
	}

	/**
	 * get printer status
	 * @return status
	 */
	public byte[] getStatus() {
		byte[] ret = null;
		send(GET_STATUS);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		if (statusError == 0) {
			ret = new byte[1];
			ret[0] = status;
		} else {
			ret = new byte[2];
			ret[0] = status;
			ret[1] = statusError;
		}
		return ret;
	}

	/**
	 * set connection error
	 * @param type error type
	 * @param e error exception
	 */
	private void setError(String type, Exception e) {
		Log.e(TAG, type, e);
		ArrayList<String> conError = new ArrayList<String>();
		conError.add(type);
		conError.add(e.getLocalizedMessage());
		connectionError = conError.toArray(new String[conError.size()]);
	}

	/**
	 * This thread keeps listening to the InputStream while connected 
	 *
	 */
	private class ListeningThread extends Thread {
		/**
		 * input stream
		 */
		private InputStream inputStream;

		/**
		 * constructor
		 * @param inputStream input stream
		 */
		public ListeningThread(InputStream inputStream) {
			this.inputStream = inputStream;
		}

		/**
		 * main executing method of the thread
		 */
		public void run() {
			log("now listening to printer");
			if (handler != null)
				handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_LISTEN);

			byte[] buffer = new byte[1024];
			int bytes;

			// Keep listening to the InputStream while connected
			while (true) {
				try {
					// Read from the InputStream
					bytes = inputStream.read(buffer);
					processResponse(buffer.toString().substring(0, bytes));
				} catch (IOException e) {
					if (handler != null)
						handler.sendEmptyMessage(com.multimedia.aes.gestnet_nucleo.printer_0554_0553.PrinterHelper.STATE_RECONNECTING);
					if (!connectTo(lastDevice)) {
						setError("410 Connection interrupted", e);
						disconnect();
						break;
					}
					reseted = true;
				}
			}
		}

		/**
		 * process response, set ready flag
		 * @param response response
		 */
		private void processResponse(String response) {
			log(">>" + response);
			char[] data = response.toCharArray();
			switch (data[0]) {
			case 17:
				ready = false;
				break;
			case 19:
				ready = true;
				break;
			default:
				status = (byte) data[0];
				if (data.length > 1) {
					statusError = (byte) data[1];
				}
			}
		}
	}

	/**
	 * send data to printer
	 * @param data data to sent
	 * @return is sent successful?
	 */
	public boolean send(byte[] data) {
		boolean success = false;
		while (connected) {
			if (ready) {
				if (outputStream != null) {
					try {
						log("<<" + new String(data));
						outputStream.write(data);
						success = true;
					} catch (IOException e) {
						setError("420 Write error", e);
					}
				}
				break;
			} else {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		return success;
	}

	/**
	 * get reseted status and reset this status
	 * @return was reseted?
	 */
	public boolean wasReseted() {
		boolean ret = reseted;
		reseted = false;
		return ret;
	}

	@Override
	public String name(String device) {
		String devicename ="no Printer";
		for (BluetoothDevice btDevice : btAdapter.getBondedDevices()) {
			if (btDevice == null) continue;
			if (btDevice.getAddress() == null) continue;
			log(btDevice.getName() + " : " + btDevice.getAddress());
			if (btDevice.getAddress().equals(device)) {
				log("Current device: " +btDevice.getName());
				devicename = btDevice.getName().toString();
				break;
			}
			
		}
		return devicename;
	}
}