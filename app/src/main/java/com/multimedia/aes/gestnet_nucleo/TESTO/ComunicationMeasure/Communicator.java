/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;


import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._1_LL.CommandFactory_330_1_LL;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._1_LL.MeasureValueFactory_330_1_LL;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._2_LL.CommandFactory_330_2_LL;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure._2_LL.MeasureValueFactory_330_2_LL;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.CommunicationErrors;
import com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums.MeasurerTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Communicator class implement on abstract level 
 * the communication between framework and the measurer.
 * It can be initialized by concrete interface implementation such as bluetooth, usb, ...
 * @author sergey.zaburunov
 */
public class Communicator {

	/**
	 * Debugging class tag
	 */
	private static final String TAG = "Communicator";

	/**
	 * Abstract member object for the communication services
	 */
	private CommunicationInterface communicationInterface;

	/**
	 * Abstract member object for the measurer device: 330-1-LL or 330-2-LL
	 * to define set of commands
	 */
	private CommandInterface commandInterface;
		
	/**
	 * type of measurer (330-1-LL, 330-2-LL)
	 * to define send and reply sets
	 */
	private MeasurerTypes measurerType;

	/**
	 * thread to process background send/reply data  
	 */
	private CommunicatorThread communicatorThread;

	//
	//Message types sent from the BluetoothHelper Handler
	//
	
	/**
	 * Message type sent from Handler as 'state changed' 
	 */
	public static final int MESSAGE_STATE_CHANGE = 1;
	/**
	 * Message type sent from Handler as 'message is read' 
	 */
	public static final int MESSAGE_READ = 2;
	/**
	 * Message type sent from Handler as 'message is written' 
	 */
	public static final int MESSAGE_WRITE = 3;
	/**
	 * Message type sent from Handler as 'message contains new device name' 
	 */
	public static final int MESSAGE_DEVICE_NAME = 4;
	/**
	 * Message type sent from Handler as 'message should be shown on Toast' 
	 */
	public static final int MESSAGE_TOAST = 5;

	//
	// Key names received from Handler
	//
	
	/**
	 * Device name received from Handler
	 */
	public static final String DEVICE_NAME = "device_name";
	/**
	 * Toast message received from Handler
	 */
	public static final String TOAST = "toast";

	
	/**
	 * flag means the communicator waits for next reply from measurer (next part of message) 
	 * and sends no next commands to measurer
	 */
	private Boolean readingReply = false;

	/**
	 * call back function for states of communication (state, new device name, error messages)
	 */
	private final StateCallBack stateCallBack;

	/**
	 * time out in milliseconds to wait for reply
	 */
	private final int timeOutInMillis;

	/**
	 * help timeout timer to wait for reply
	 */
	private Long timeoutTime = System.currentTimeMillis();

	/**
	 * terminated state of thread
	 */
	private Boolean terminated = false;
	
	/**
	 * running state of communicator
	 */
	private Boolean running = false;

	/**
	 * time out in milliseconds to wait for reply, but only for 1 transmission,
	 * after that it will be set back to timeOutInMillis
	 */
	private int timeOutInMillisForOneTime;
	
	/**
	 * is timeOutInMillisForOneTime set?
	 */
	private Boolean isTimeoutForOneTime = new Boolean(false);

	/**
	 * Constructor. Prepares a new Bluetooth session.
	 * 
	 * @param communicationInterface
	 *            communication interface
	 * @param stateCallBack
	 *            call back for communication state
	 * @param timeOutInMillis time out to wait response from measurer in milliseconds
	 */
	public Communicator(CommunicationInterface communicationInterface,
			StateCallBack stateCallBack,
			int timeOutInMillis) {
		this.stateCallBack = stateCallBack;
		this.timeOutInMillis = timeOutInMillis;
		
		// register and initialize abstract communications interface (bluetooth, usb,...)
		this.communicationInterface = communicationInterface;
		this.communicationInterface.setHandler(this.handler);
		
	}

	/**
	 * set measurer type and 
	 * register/initialize abstract command interface (330-1-LL, 330-2-LL)
	 * register/initialize abstract measure value factory interface (330-1-LL, 330-2-LL)
	 * @param measurerType the measurerType to set
	 */
	public void setMeasurerType(MeasurerTypes measurerType) {
		this.measurerType = measurerType;
		
		if (this.measurerType == MeasurerTypes.Testo_330_1_LL){
			this.commandInterface = new CommandFactory_330_1_LL();
		} else {
			this.commandInterface = new CommandFactory_330_2_LL();
		}
	}
	
	/**
	 * get measurer type (330-1-LL, 330-2-LL)
	 * @return measurer type
	 */
	public MeasurerTypes getMeasurerType(){
		return this.measurerType;
	}

	/**
	 * get command interface (330-1-LL, 330-2-LL)
	 * @return commandInterface
	 */
	public CommandInterface getCommandInterface() {
		return this.commandInterface;
	}

	/**
	 * get member object for the measurer device: 330-1-LL or 330-2-LL
	 * to define format of measure values
	 * @param list incoming values from measurer
	 * @return one of the MeasureValueFactoryInterfaces
	 */
	public MeasureValueFactoryInterface getMeasureValueFactoryInterface(List<TransmissionFormat> list){
		if (this.measurerType == MeasurerTypes.Testo_330_1_LL){
			return new MeasureValueFactory_330_1_LL(list);
		} else{
			return new MeasureValueFactory_330_2_LL(list);
		}
	}
	
	
	/**
	 * Start the communicator thread.
	 * 
	 */
	public synchronized void start() {
		Log.d(TAG, "start");

		setRunning(true);
		
		// Start the thread to listen
		if (this.communicatorThread == null) {
			this.communicatorThread = new CommunicatorThread();
			setTerminated(false);
			this.communicatorThread.start();
		}
	}

	/**
	 * Restart the communicator thread.
	 * 
	 */
	public synchronized void resume() {
		Log.d(TAG, "resume");
		
		if (this.communicationInterface != null){
			// Only if the state is STATE_NONE, do we know that we haven't
			// started already
			if (this.communicationInterface.getState() == CommunicationInterface.STATE_NONE) {
				// Start the communication services
				this.communicationInterface.start();
			}
		}
	}

	/**
	 * Stop the communicator thread.
	 * 
	 */
	public synchronized void stop() {
		Log.d(TAG, "stop");
		
		setRunning(false);
		
		// stop the communication connect
		if (this.communicationInterface != null)
			this.communicationInterface.stop();
		
		// Stop the thread to listen
		setTerminated(true);
//		if (this.communicatorThread != null) {
//			if (!this.communicatorThread.isInterrupted())
//				this.communicatorThread.interrupt();
//		}
	}

	
    /**
     * Start the ConnectThread to initiate a connection to a remote device.
     * @param device  The BluetoothDevice to connect
     */
    public synchronized void connect(BluetoothDevice device) {
        Log.d(TAG, "connect to: " + device);
		// Attempt to connect to the device
		this.communicationInterface.connect(device);

    }
    
    
    
	/**
	 * get bluetooth state
	 * @return communication state
	 * @see CommunicationInterface#getState()
	 */
	public synchronized int getState() {
		Log.d(TAG, "getState");
		if (this.communicationInterface != null)
			return this.communicationInterface.getState();
		else
			return CommunicationInterface.STATE_NONE;
	}

	/**
	 * Write to the Bluetooth in an unsynchronized manner
	 * 
	 * @param out
	 *            The bytes to write
	 */
	public void write(byte[] out, SendCallBack scb, ReplyCallBack rcb) {
		// Create temporary object
		CommunicatorThread ct;
		// Synchronize a copy of the CommunicatorThread
		synchronized (this) {
			ct = this.communicatorThread;
		}
		// Perform the write unsynchronized
		if (ct != null)
			ct.write(out, scb, rcb);
	}

	/**
	 * Set ReadingReply state
	 * 
	 * @param value
	 *            ReadingReply flag
	 */
	private void setReadingReply(final boolean value) {
		synchronized (this.readingReply) {
			try {
				this.readingReply = value;
			} catch (final Exception ex) {
			}
		}
	} // setReadingReply

	/**
	 * returns whether the Reply is reading
	 * 
	 * @return ReadingReply flag
	 */
	private boolean isReadingReply() {
		boolean res = false;
		synchronized (this.readingReply) {
			try {
				res = this.readingReply;
			} catch (final Exception ex) {
			}
		}
		return res;
	} // isReadingReply

	
	/**
	 * get timer for timeout in milliseconds
	 * @return the timeoutTime timer state
	 * @see Communicator#timeoutTime
	 */
	private Long getTimeoutTime() {
		Long res = System.currentTimeMillis();
		synchronized (this.timeoutTime) {
			try {
				res = this.timeoutTime;
			} catch (final Exception ex) {
			}
		}
		return res;
	}

	/**
	 * set timeout timer in milliseconds
	 * @param timeout timer state
	 * @see Communicator#timeoutTime
	 */
	private void setTimeoutTime(Long value) {
		synchronized (this.timeoutTime) {
			try {
				this.timeoutTime = value;
			} catch (final Exception ex) {
			}
		}
	}

	/**
	 * reset timeout to wait timeOutInMillis from now again
	 */
	private void resetTimeoutTime() {
		
		synchronized (this.isTimeoutForOneTime){
			if (this.isTimeoutForOneTime == true){
				
				setTimeoutTime(System.currentTimeMillis()
						+ this.timeOutInMillisForOneTime);

				this.isTimeoutForOneTime = false;

			} else {
				
				setTimeoutTime(System.currentTimeMillis()
						+ Communicator.this.timeOutInMillis);
			
			}
		}
	} // resetTimeoutTime
	
	
	/**
	 * This thread runs during a connection with a remote device. It handles all
	 * incoming and outgoing messages.
	 */
	private class CommunicatorThread extends Thread {
		/**
		 * A buffer to keep the command to be sent to measurer.
		 * It synchronizes send/reply communication with measurer
		 * and makes sure that measurer is already replied all parts back 
		 * before next command will be sent
		 */
		private CopyOnWriteArrayList<SendReplyStructure> wrBuffer = new CopyOnWriteArrayList<SendReplyStructure>();

		/**
		 * last send message and call backs to socket
		 */
		private SendReplyStructure lastSentStructure = null;

		/**
		 * call back for send to measurer
		 */
		public SendCallBack sendCallBack;

		/**
		 * call back for reply from measurer
		 */
		public ReplyCallBack replyCallBack;
		
		/** 
		 * constructor
		 */
		public CommunicatorThread() {
			Log.d(TAG, "create CommunicatorThread");

		}

        /**
         * run method: main executable part of the thread
         */
		public void run() {

			Log.d(TAG, "BEGIN CommunicatorThread.run");

			// Keep listening to the send buffer while connected
			while (!isTerminated()) {
				try {
					// check read/write state and send next message to measurer 
					// if thread is not waiting next reply from measurer
					if (isReadingReply() == false) {
						synchronized (this.wrBuffer){
							// send next command if available
							if (this.wrBuffer.size() > 0) {
								SendReplyStructure structure = this.wrBuffer.get(0);
								this.lastSentStructure = structure;
								// get message from structure
								byte[] cmd = structure.getSend();
								// get call backs from structure
								this.sendCallBack = structure.getSendCallBack();
								this.replyCallBack = structure.getReplyCallBack();
								// perform send
								if (Communicator.this.communicationInterface != null) {
									Log.d(TAG, "CommunicatorThread.run write to socket...");
									Communicator.this.communicationInterface.write(cmd);
									setReadingReply(true);
									resetTimeoutTime();
									this.wrBuffer.remove(0);
									
									// to make sure wait interval to the next message for ...some... milliseconds
									try{
										sleep(1);
									} catch (InterruptedException ie){ // if interrupt() was called for this Thread while it was sleeping
									}
								}
							} // if (this.wrBuffer.size() > 0)
						} // synchronized
					} else {
						// check timeout
						if (System.currentTimeMillis() > getTimeoutTime()){
							Log.w(TAG, "CommunicatorThread.run Timeout to wait response!");
							setReadingReply(false);
							synchronized (this.wrBuffer){
								this.wrBuffer.clear();
							}
							// inform client about timeout by send null reply
							byte[] readBuf = new byte[]{0x21, (byte) 0x80, (byte) 0xFF};
							handler.obtainMessage(Communicator.MESSAGE_READ, readBuf.length, -1, readBuf)
                            	.sendToTarget();
						}
					}

				} catch (Exception e) {
					Log.e(TAG, "CommunicatorThread.run error:" + e.toString());
				}
			}
			
			if (Communicator.this.communicatorThread != null) {
				if (!Communicator.this.communicatorThread.isInterrupted())
					Communicator.this.communicatorThread.interrupt();
				Communicator.this.communicatorThread = null;
			}

			
			
		}

		/**
		 * Write to the send buffer.
		 * 
		 * @param buffer
		 *            The bytes to write
		 * @param scb
		 *            send call back
		 * @param rcb
		 *            reply call back
		 */
		public void write(byte[] buffer, SendCallBack scb, ReplyCallBack rcb) {
			try {
				synchronized (this.wrBuffer){
					// check write buffer overflow
					if (this.wrBuffer.size() > 20){
				        Message msg = handler.obtainMessage(Communicator.MESSAGE_TOAST);
				        Bundle bundle = new Bundle();
				        bundle.putInt(Communicator.TOAST, 
				        		CommunicationErrors.conn_Send_Buffer_overflowed.getErrorCode());
				        msg.setData(bundle);
				        handler.sendMessage(msg);

				        // clear overflowed write buffer
				        this.wrBuffer.clear();
					} 
					
					// write to buffer
					SendReplyStructure structure = new SendReplyStructure(buffer,
							scb, rcb);
					this.wrBuffer.add(structure);
						
				}

			} catch (Exception e) {
				Log.e(TAG, "Exception during write", e);
			}
		}

	} // CommunicatorThread

	
	/**
	 * Set terminated state of the thread
	 * 
	 * @param value
	 *            termination flag
	 */
	private void setTerminated(final boolean value) {
		synchronized (this.terminated) {
			try {
				this.terminated = value;
			} catch (final Exception ex) {
			}
		}
	} // setTerminated

	/**
	 * returns whether the thread is terminated or not
	 * 
	 * @return termination flag
	 */
	private boolean isTerminated() {
		boolean res = false;
		synchronized (this.terminated) {
			try {
				res = this.terminated;
			} catch (final Exception ex) {
			}
		}
		return res;
	} // isTerminated

	/**
	 * Set running state
	 * 
	 * @param value
	 *            running flag
	 */
	private void setRunning(final boolean value) {
		synchronized (this.running) {
			try {
				this.running = value;
			} catch (final Exception ex) {
			}
		}
	}

	/**
	 * returns whether the communicator is running or not
	 * 
	 * @return running flag
	 */
	public boolean isRunning() {
		boolean res = false;
		synchronized (this.running) {
			try {
				res = this.running;
			} catch (final Exception ex) {
			}
		}
		return res;
	}

	
	/**
	 * The Handler that gets information back from the Communication Interface (his implementation)
	 */
	private final Handler handler = new Handler() {

		/**
		 * buffer of replies from measurer
		 */
		private List<TransmissionFormat> replyBuffer = new ArrayList<TransmissionFormat>();

		/**
		 * handles of all incoming messages
		 * @param msg incoming message 
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case MESSAGE_STATE_CHANGE:
				if (Communicator.this.stateCallBack != null)
					Communicator.this.stateCallBack.getState(msg.arg1);
				break;
			case MESSAGE_WRITE:
				byte[] writeBuf = (byte[]) msg.obj;
				synchronized (Communicator.this) {
					if (Communicator.this.communicatorThread != null)
						if (Communicator.this.communicatorThread.sendCallBack != null)
							Communicator.this.communicatorThread.sendCallBack
								.send(writeBuf);
				}
				break;

			case MESSAGE_READ:
				byte[] readBuf = new byte[msg.arg1];
				for (int i = 0; i < msg.arg1; i++)
					readBuf[i] = ((byte[]) msg.obj)[i];

				TransmissionFormat tf = null;
				boolean isBlockOk = false;
				boolean isRepeatSendNeeded = false;
				try {
					tf = new TransmissionFormat(readBuf);
					isBlockOk = true;
				} catch (IllegalStateException ise){
					int i2 = readBuf[1] & 0xff;
					int i3 = readBuf[2] & 0xff;
					if (i2 == 0x80){
						if (i3 == 0x0F){
							isBlockOk = true;
						}
						if (i3 == 0xFF){
							Log.e(TAG, "handleMessage MESSAGE_READ Block has not been received " +
									"correctly, retransmit last block");
							isBlockOk = true;
							tf = null;
//							isBlockOk = false;
//							isRepeatSendNeeded = true;
						}
					} else {
						Log.e(TAG, "handleMessage MESSAGE_READ Error: " + ise);
						isBlockOk = false;
					}
				} catch (Exception ex) {
					Log.e(TAG, "handleMessage MESSAGE_READ Error: " + ex);
					isBlockOk = false;
				}

				if (isRepeatSendNeeded){
					if (Communicator.this.communicatorThread != null){
						if (Communicator.this.communicatorThread.lastSentStructure != null){
							// get message from structure
							byte[] cmd = Communicator.this.communicatorThread.lastSentStructure.getSend();
							// get call backs from structure
							Communicator.this.communicatorThread.sendCallBack = Communicator.this.communicatorThread.lastSentStructure.getSendCallBack();
							Communicator.this.communicatorThread.replyCallBack = Communicator.this.communicatorThread.lastSentStructure.getReplyCallBack();
							// perform send
							if (Communicator.this.communicationInterface != null) {
								Log.d(TAG, "handleMessage MESSAGE_READ re-write to socket...");
								Communicator.this.communicationInterface.write(cmd);
								resetTimeoutTime();
							}
						}
					}
					
					break;
				}
				
				if (isBlockOk) {
					int blockNo = (tf != null)? tf.getBlockNo() : 0;
					int blockNoTotal = (tf != null)? tf.getBlockNoTotal() : 0;

					// clear buffer before first reply
					if (blockNo == 0)
						replyBuffer.clear();

					// add reply to buffer
					replyBuffer.add(tf);

					// not last block
					if (blockNo + 1 < blockNoTotal) {
						byte[] cmd = Communicator.this.commandInterface.getAcknowledgement(true);
						// reset timeout
						resetTimeoutTime();
						// acknowledge and wait for the next block
						Communicator.this.communicationInterface.write(cmd);

					} else { // last block
						// send reply as callback
						synchronized (Communicator.this) {
							if (Communicator.this.communicatorThread != null)
								if (Communicator.this.communicatorThread.replyCallBack != null)
									Communicator.this.communicatorThread.replyCallBack
										.reply(replyBuffer);
						}
						// clear buffer
						replyBuffer.clear();

						setReadingReply(false);
					}

				} else {
					byte[] cmd = Communicator.this.commandInterface.getAcknowledgement(false);
					// reset timeout
					resetTimeoutTime();
					// retransmit command
					Communicator.this.communicationInterface.write(cmd);
				}

				String hexRead = ConvertHelper
						.convertByteArrayToHexString(readBuf);
				Log.d("####### Received!!! " + msg.arg1 + " bytes", hexRead);

				break;

			case MESSAGE_DEVICE_NAME:
				if (Communicator.this.stateCallBack != null)
					Communicator.this.stateCallBack.getDeviceName(msg
							.getData().getString(DEVICE_NAME));
				break;
			case MESSAGE_TOAST:
				if (Communicator.this.stateCallBack != null)
					Communicator.this.stateCallBack.getMessage(msg.getData()
							.getInt(TOAST));
				break;
			}
		}
	}; // handler

	/**
	 * Container for send command, send and reply call backs.
	 * It needs to be saved in send buffer
	 * @author sergey.zaburunov
	 *
	 */
	private class SendReplyStructure {
		/**
		 * send command
		 */
		private final byte[] send;
		/**
		 * send call back
		 */
		private final SendCallBack sendCB;
		/**
		 * reply call back
		 */
		private final ReplyCallBack replyCB;

		/**
		 * constructor
		 * @param send send command
		 * @param sendCB send call back
		 * @param replyCB reply call back
		 */
		public SendReplyStructure(byte[] send, SendCallBack sendCB,
				ReplyCallBack replyCB) {
			this.send = send;
			this.sendCB = sendCB;
			this.replyCB = replyCB;
		}

		/**
		 * get send command
		 * @return the send command
		 */
		public byte[] getSend() {
			return send;
		}

		/**
		 * get send call back
		 * @return the send call back
		 */
		public SendCallBack getSendCallBack() {
			return this.sendCB;
		}

		/**
		 * get reply call back
		 * @return the reply
		 */
		public ReplyCallBack getReplyCallBack() {
			return this.replyCB;
		}
	} // SendReplySrtucture
	
	/**
	 * set time out to wait response from measurer in milliseconds just for one time (send/reply) and then turn the default value back 
	 * @param value
	 * 			time out to wait response from measurer in milliseconds
	 */
	public void setTimeOutInMillisForOneTime(int value){
		this.timeOutInMillisForOneTime = value;
		this.isTimeoutForOneTime = true;
	}

}
