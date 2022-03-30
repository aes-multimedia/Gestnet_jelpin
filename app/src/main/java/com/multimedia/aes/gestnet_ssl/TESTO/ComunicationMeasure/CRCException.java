/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_ssl.TESTO.ComunicationMeasure;

public class CRCException extends Exception{

	/**
	 * serial Version UID
	 */
	private static final long serialVersionUID = 3296278590801347915L;

	/**
	 * constructor
	 */
	public CRCException (){
		return;
	} // CRCException

	/**
	 * constructor
	 * @param message error text
	 */
	public CRCException (final String message){
		super (message);
		return;
	} // CRCException

	/**
	 * constructor
	 * @param message error text
	 * @param cause exception
	 */
	public CRCException (final String message, final Throwable cause){
		super (message, cause);
		return;
	} // CRCException

	   
	/**
	 * constructor
	 * @param cause exception
	 */
	public CRCException (final Throwable cause){
		super (cause);
		return;
	} // CRCException

}
