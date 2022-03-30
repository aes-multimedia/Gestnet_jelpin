/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure.enums.CommunicationErrors;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.UUID;

/**
 * Bluetooth helper class.
 * 
 * This class does all the work for setting up and managing Bluetooth
 * connections with other devices. It has a thread that listens for
 * incoming connections, a thread for connecting with a device and 
 * a thread for performing data transmissions when connected.
 */
public class BluetoothHelper implements CommunicationInterface{
	/**
	 * Class tag for logging. It will be showing in logging output as corresponding info to this class
	 */
    private static final String TAG = "BluetoothHelper";
    /** 
     * add debugging info into log output
     */
    private static final boolean D = true;

    /**
     * Name for the SDP record when creating server socket
     */
    private static final String NAME = "TestoApp";

    /**
     * Unique UUID for this application
     */
    private static final UUID UUID_RFCOMM_GENERIC = UUID.fromString("00001101-0000-1000-8000-00805F9B34FB");
    
    /**
     * Bluetooth adapter member
     */
    private final BluetoothAdapter btAdapter;
    /**
     * Accept thread member running while listening for incoming connections
     */
    private AcceptThread mAcceptThread;
    /**
     * Connect thread member runs while attempting to make an outgoing connection
     */
    private ConnectThread mConnectThread;
    /**
     * Connected thread member runs during a connection with a remote device
     */
    private ConnectedThread mConnectedThread;
    /**
     * Communication state
     */
    private int mState;
    
    /**
     * A Handler to send messages back to the UI Activity
     */
    private Handler mHandler;

    /**
     * Constructor. Prepares a new Bluetooth session.
     */
    public BluetoothHelper() {
    	btAdapter = BluetoothAdapter.getDefaultAdapter();
        mState = CommunicationInterface.STATE_NONE;
    }

    /**
     * Set the current state of the bluetooth connection
     * @param state  An integer defining the current connection state
     */
    private synchronized void setState(int state) {
        if (D) Log.d(TAG, "setState() " + mState + " -> " + state);
        mState = state;

        // Give the new state to the Handler so the UI Activity can update
        mHandler.obtainMessage(Communicator.MESSAGE_STATE_CHANGE, state, -1).sendToTarget();
    }

    /**
     * Return the current connection state.
     * @return connection state 
     */
    public synchronized int getState() {
        return mState;
    }

    /**
     * Start the bluetooth service. Specifically start AcceptThread to begin a
     * session in listening (server) mode. Called by the Communicator onResume() 
     */
    public synchronized void start() {
        if (D) Log.d(TAG, "*****start");

        // Cancel any thread attempting to make a connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to listen on a BluetoothServerSocket
        if (mAcceptThread == null) {
            mAcceptThread = new AcceptThread();
            mAcceptThread.start();
        }
        setState(CommunicationInterface.STATE_LISTEN);
    }

    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     * @param device  The BluetoothDevice to connect
     */
    public synchronized void connect(Object device) {
		
		if ((device instanceof BluetoothDevice) == false)
			return;
		
		BluetoothDevice btDevice = (BluetoothDevice) device;
		
        if (D) Log.d(TAG, "connect to: " + device);

        // Cancel any thread attempting to make a connection
        if (mState == CommunicationInterface.STATE_CONNECTING) {
            if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        }

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Start the thread to connect with the given device
        mConnectThread = new ConnectThread(btDevice);
        mConnectThread.start();
        setState(CommunicationInterface.STATE_CONNECTING);
    }

    /**
     * Start the ConnectedThread to begin managing a Bluetooth connection
     * @param socket  The BluetoothSocket on which the connection was made
     * @param device  The BluetoothDevice that has been connected
     */
    public synchronized void connected(BluetoothSocket socket, BluetoothDevice device) {
        if (D) Log.d(TAG, "connected");

        // Cancel the thread that completed the connection
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}

        // Cancel any thread currently running a connection
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}

        // Cancel the accept thread because we only want to connect to one device
        if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}

        // Start the thread to manage the connection and perform transmissions
        mConnectedThread = new ConnectedThread(socket);
        mConnectedThread.start();

        // Send the name of the connected device back to the UI (via Communicator)
        Message msg = mHandler.obtainMessage(Communicator.MESSAGE_DEVICE_NAME);
        Bundle bundle = new Bundle();
        bundle.putString(Communicator.DEVICE_NAME, device.getName());
        msg.setData(bundle);
        mHandler.sendMessage(msg);

        setState(CommunicationInterface.STATE_CONNECTED);
    }

    /**
     * Stop all threads
     */
    public synchronized void stop() {
        if (D) Log.d(TAG, "stop");
        if (mConnectThread != null) {mConnectThread.cancel(); mConnectThread = null;}
        if (mConnectedThread != null) {mConnectedThread.cancel(); mConnectedThread = null;}
        if (mAcceptThread != null) {mAcceptThread.cancel(); mAcceptThread = null;}
        setState(CommunicationInterface.STATE_NONE);
    }

    /**
     * Write to the ConnectedThread in an unsynchronized manner
     * @param out The bytes to write
     */
    public void write(byte[] out) {
        // Create temporary object
        ConnectedThread r;
        // Synchronize a copy of the ConnectedThread
        synchronized (this) {
            if (mState != CommunicationInterface.STATE_CONNECTED) return;
            r = mConnectedThread;
        }
        // Perform the write unsynchronized
        r.write(out);
    }

    /**
     * Indicate that the connection attempt failed and notify the Communicator/UI Activity.
     */
    private void connectionFailed() {
        setState(CommunicationInterface.STATE_LISTEN);

        // Send a failure message back to the Communicator/Activity
        Message msg = mHandler.obtainMessage(Communicator.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putInt(Communicator.TOAST, 
        		CommunicationErrors.conn_Device_unable_connect.getErrorCode());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * Indicate that the connection was lost and notify the Communicator / UI Activity.
     */
    private void connectionLost() {
        setState(CommunicationInterface.STATE_DISCONNECTED);

        // Send a failure message back to the Activity
        Message msg = mHandler.obtainMessage(Communicator.MESSAGE_TOAST);
        Bundle bundle = new Bundle();
        bundle.putInt(Communicator.TOAST, 
        		CommunicationErrors.conn_Device_lost.getErrorCode());
        msg.setData(bundle);
        mHandler.sendMessage(msg);
    }

    /**
     * This thread runs while listening for incoming connections. It behaves
     * like a server-side client. It runs until a connection is accepted
     * (or until canceled).
     */
    private class AcceptThread extends Thread {
        /** 
         * The local server socket
         */
        private final BluetoothServerSocket btServerSocket;

        /**
         * Constructor
         */
        public AcceptThread() {
            BluetoothServerSocket btServerSocket = null;

            // Create a new listening server socket
            try {
            	if (D)
            		Log.d(TAG, "*****Start using listenUsingRfcommWithServiceRecord('" + 
            				NAME + "', '" + UUID_RFCOMM_GENERIC + "')");
            	if (btAdapter != null)
            		btServerSocket = btAdapter.listenUsingRfcommWithServiceRecord(NAME, UUID_RFCOMM_GENERIC);
            } catch (IOException e) {
                Log.e(TAG, "listen() failed", e);
            }
            this.btServerSocket = btServerSocket;
        }

        /**
         * run method: main executable part of the thread
         */
        public void run() {
            if (D) Log.d(TAG, "*****BEGIN mAcceptThread");
            setName("AcceptThread");
            BluetoothSocket socket = null;

            // Listen to the server socket if we're not connected
            while (mState != CommunicationInterface.STATE_CONNECTED) {
                try {
                    // This is a blocking call and will only return on a
                    // successful connection or an exception
                	if (this.btServerSocket != null)
                		socket = this.btServerSocket.accept();
                } catch (IOException e) {
                    Log.e(TAG, "accept() failed", e);
                    break;
                }

                // If a connection was accepted
                if (socket != null) {
                    synchronized (BluetoothHelper.this) {
                        switch (mState) {
                        case CommunicationInterface.STATE_LISTEN:
                        case CommunicationInterface.STATE_CONNECTING:
                            // Situation normal. Start the connected thread.
                        	if (socket != null)
                        		connected(socket, socket.getRemoteDevice());
                            break;
                        case CommunicationInterface.STATE_NONE:
                        case CommunicationInterface.STATE_CONNECTED:
                            // Either not ready or already connected. Terminate new socket.
                            try {
                            	if (socket != null)
                            		socket.close();
                            } catch (IOException e) {
                                Log.e(TAG, "Could not close unwanted socket", e);
                            }
                            break;
                        }
                    }
                }
            }
            if (D) Log.i(TAG, "END mAcceptThread");
        }

        /**
         * Cancel thread, close bluetooth server socket
         */
        public void cancel() {
            if (D) Log.d(TAG, "cancel " + this);
            try {
            	if (this.btServerSocket != null)
            		this.btServerSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of server failed", e);
            }
        }
    }


    /**
     * This thread runs while attempting to make an outgoing connection
     * with a device. It runs straight through; the connection either
     * succeeds or fails.
     */
    private class ConnectThread extends Thread {
    	/**
    	 * bluetooth socket
    	 */
        private final BluetoothSocket mmSocket;
        /**
         * bluetooth device
         */
        private final BluetoothDevice mmDevice;

        /**
         * constructor
         * @param device bluetooth device
         */
        public ConnectThread(BluetoothDevice device) {
            mmDevice = device;
            BluetoothSocket tmp = null;

            // Get a BluetoothSocket for a connection with the
            // given BluetoothDevice
            try {
                tmp = getSocket(device);
            } catch (IOException e) {
                Log.e(TAG, "create() failed", e);
            } catch (Exception e) {
                Log.e(TAG, "create() failed", e);
            }
            mmSocket = tmp;
        }
        
        /**
         * Open bluetooth socket. 
         * @param device bluetooth device
         * @return opened bluetooth socket
         * @throws Exception exception if failed
         */
    	private BluetoothSocket getSocket(BluetoothDevice device) throws Exception{
    		BluetoothSocket ret = null;
    		try {
    			// socket = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
    			// above command causes "Service Discovery Failed" Exception on Nexus S
    			// workaround below from
    			// http://stackoverflow.com/questions/3397071/android-bluetooth-service-discovery-failed-exception
    			Method m = device.getClass().getMethod("createRfcommSocket",
    					new Class[] { int.class });
    			ret = (BluetoothSocket) m.invoke(device, 1);
    		} catch (SecurityException e) {
    			throw new SecurityException(e);
    		} catch (NoSuchMethodException e) {
    			throw new NoSuchMethodException("400 Error creating Socket");
    		} catch (IllegalArgumentException e) {
    			throw new IllegalArgumentException("400 Error creating Socket", e);
    		} catch (IllegalAccessException e) {
    			throw new IllegalAccessException("400 Error creating Socket");
    		} catch (InvocationTargetException e) {
    			throw new InvocationTargetException(e);
    		}
    		return ret;
    	}


        /**
         * run method: main executable part of the thread
         */
        public void run() {
            Log.i(TAG, "BEGIN mConnectThread");
            setName("ConnectThread");

            // Always cancel discovery because it will slow down a connection
            btAdapter.cancelDiscovery();

            // Make a connection to the BluetoothSocket
            try {
                // This is a blocking call and will only return on a
                // successful connection or an exception
            	if (mmSocket != null)
            		mmSocket.connect();
            } catch (IOException e) {
                connectionFailed();
                // Close the socket
                try {
                	if (mmSocket != null)
                		mmSocket.close();
                } catch (IOException e2) {
                    Log.e(TAG, "unable to close() socket during connection failure", e2);
                }
                // Start the service over to restart listening mode
                BluetoothHelper.this.start();
                return;
            }

            //Reset the ConnectThread because we're done
            synchronized (BluetoothHelper.this) {
                mConnectThread = null;
            }

            // Start the connected thread
            connected(mmSocket, mmDevice);
        }

        /**
         * Cancel thread, close bluetooth socket
         */
        public void cancel() {
            try {
            	if (mmSocket != null)
            		mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    /**
     * This thread runs during a connection with a remote device.
     * It handles all incoming and outgoing transmissions.
     */
    private class ConnectedThread extends Thread {
    	/**
    	 * bluetooth socket
    	 */
        private final BluetoothSocket mmSocket;
        /**
         * input stream
         */
        private final InputStream mmInStream;
        /**
         * output stream
         */
        private final OutputStream mmOutStream;

        /**
         * Constructor
         * @param socket bluetooth socket
         */
        public ConnectedThread(BluetoothSocket socket) {
            Log.d(TAG, "create ConnectedThread");
            mmSocket = socket;
            InputStream tmpIn = null;
            OutputStream tmpOut = null;

            // Get the BluetoothSocket input and output streams
            try {
                tmpIn = socket.getInputStream();
                tmpOut = socket.getOutputStream();
            } catch (IOException e) {
                Log.e(TAG, "temp sockets not created", e);
            }

            mmInStream = tmpIn;
            mmOutStream = tmpOut;
        }

        /**
         * run method: main executable part of the thread
         * 
         * 05.12.2016: code corrected: wait until all bytes of one block are read
         */
        public void run() {
            Log.i(TAG, "BEGIN mConnectedThread");
            byte[] buffer = new byte[2048];
            byte[] readBuf = new byte[10000];
            int bytes = 0;

            int iPos = 0;
            
            // Keep listening to the InputStream while connected
            while (true) {
                try {
                    // Read from the InputStream
                	if (mmInStream != null)
                		bytes = mmInStream.read(buffer);
                    
                    Log.d("-->ConnectedThread.run", "bytes=" + bytes);
                    
    				System.arraycopy(buffer, 0, readBuf, iPos, bytes);
    				iPos += bytes;
    			    if( iPos > 5 )
    			    {
    			    	if(( readBuf[5] +8 ) == iPos )
    			    	{
    			            byte[] data = new byte[iPos];
    	    				System.arraycopy(readBuf, 0, data, 0, iPos);
    			    		Log.d("-->ConnectedThread.run", "received=" + ConvertHelper.convertByteArrayToHexString(data));

    			    		// Send the obtained bytes to the UI Activity
    			    		if (mHandler != null)
    			    			mHandler.obtainMessage(Communicator.MESSAGE_READ, iPos, -1, data).sendToTarget();
    			    		iPos = 0;
    			    	}
    			    }
                 } catch (IOException e) {
                    Log.e(TAG, "disconnected", e);
                    connectionLost();
                    break;
                }
            }
        }

        /**
         * Write to the connected OutStream.
         * @param buffer  The bytes to write
         */
        public void write(byte[] buffer) {
            try {
            	Log.d(TAG, "Write to socket: " + ConvertHelper.convertByteArrayToHexString(buffer));
                mmOutStream.write(buffer);

                // Share the sent message back to the UI Activity
                mHandler.obtainMessage(Communicator.MESSAGE_WRITE, -1, -1, buffer)
                        .sendToTarget();
            } catch (IOException e) {
                Log.e(TAG, "Exception during write", e);
            }
        }

        /**
         * Cancel thread, close bluetooth socket
         */
        public void cancel() {
            try {
            	if (mmSocket != null)
            		mmSocket.close();
            } catch (IOException e) {
                Log.e(TAG, "close() of connect socket failed", e);
            }
        }
    }

    /**
     * set handler from communicator to send notifications back
     * @param handler
     * 				handler to set
     */
	public synchronized void setHandler(Handler handler) {
		mHandler = handler;
		
	}

}
