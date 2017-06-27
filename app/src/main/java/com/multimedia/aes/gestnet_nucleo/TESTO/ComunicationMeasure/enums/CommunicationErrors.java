/*********************************************************************
 * Copyright � 2011 Noser Engineering AG
 * Copyright � 2011 Testo AG
 *********************************************************************/
package com.multimedia.aes.gestnet_nucleo.TESTO.ComunicationMeasure.enums;

import android.util.Log;

import java.util.Locale;


/**
 * Communication errors class defines all error messages to be replied from framework to application
 * @author sergey.zaburunov
 */
public enum CommunicationErrors{

	curvalreader_sysallocstr(CommunicationErrors.start_num, "", "", "", "", "", "", "", "", "", ""),//higher numbers than the SerialTransporter
	curvalreader_create_thread(CommunicationErrors.start_num + 1, "", "", "", "", "", "", "", "", "", ""),
		
	curvalreader_wrongvaluenumber(CommunicationErrors.start_num + 2, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_init_InitComm(CommunicationErrors.start_num + 3, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_init_CreateSerialTrans(CommunicationErrors.start_num + 4, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_init_objcreation(CommunicationErrors.start_num + 5, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_showdlg_threadcreationCloseport(CommunicationErrors.start_num + 6, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_showdlg_threadcreation(CommunicationErrors.start_num + 7, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_showdlg_closePort(CommunicationErrors.start_num + 8, "", "", "", "", "", "", "", "", "", ""),
	curvalreader_showdlg_domodalfailure(CommunicationErrors.start_num + 9, "", "", "", "", "", "", "", "", "", ""),		

	dataholder_init_NoSafeArray(CommunicationErrors.start_num + 10, "", "", "", "", "", "", "", "", "", ""),
	dataholder_init_NoSafeArrayAccess(CommunicationErrors.start_num + 11, "", "", "", "", "", "", "", "", "", ""),
	dataholder_init_NoSafeArrayUnaccess(CommunicationErrors.start_num + 12, "", "", "", "", "", "", "", "", "", ""),
	dataholder_getdeviceidentifier_conversion(CommunicationErrors.start_num + 13, "", "", "", "", "", "", "", "", "", ""),
	dataholder_getfuel_conversion(CommunicationErrors.start_num + 14, "", "", "", "", "", "", "", "", "", ""),
	dataholder_getvaluename_conversion(CommunicationErrors.start_num + 15, "", "", "", "", "", "", "", "", "", ""),
	dataholder_convertspecialcharacters_lengthdiff(CommunicationErrors.start_num + 16, "", "", "", "", "", "", "", "", "", ""),
	dataholder_accessdata_access(CommunicationErrors.start_num + 17, "", "", "", "", "", "", "", "", "", ""),
	dataholder_unaccessdata_unaccess(CommunicationErrors.start_num + 18, "", "", "", "", "", "", "", "", "", ""),

	persdatmgr_destructor_SetValue(CommunicationErrors.start_num + 19, "", "", "", "", "", "", "", "", "", ""),
	persdatmgr_destructor_FlushKey(CommunicationErrors.start_num + 20, "", "", "", "", "", "", "", "", "", ""),
	persdatmgr_destructor_CloseKey(CommunicationErrors.start_num + 21, "", "", "", "", "", "", "", "", "", ""),

	ui_onsize_DoneButton(CommunicationErrors.start_num + 22, "", "", "", "", "", "", "", "", "", ""),
	ui_onsize_ModifyStyle(CommunicationErrors.start_num + 23, "", "", "", "", "", "", "", "", "", ""),

	conn_Device_unable_connect(CommunicationErrors.start_num + 30, "Verbinden zum Messger�t ist nicht m�glich", "Unable to connect measure device", "", "", "", "", "", "", "", ""),
	conn_Device_lost(CommunicationErrors.start_num + 35, "Verbindung zum Messger�t ist verloren", "Measure Device connection was lost", "", "", "", "", "", "", "", ""),
	conn_Send_Buffer_overflowed(CommunicationErrors.start_num + 40, "Senden-Puffer ist �berf�llt", "Send buffer is overflowed", "", "", "", "", "", "", "", "");

	/**
	 * start error number as offset for the next errors
	 */
	private static final int start_num = 0x02A0;

	/**
	 * error code (error identifier)
	 */
	private final int errCode;
	
	/**
	 * error text in DE
	 */
	private final String text_de;

	/**
	 * error text in EN
	 */
	private final String text_en;

	/**
	 * error text in FR
	 */
	private final String text_fr;

	/**
	 * error text in ES
	 */
	private final String text_es;

	/**
	 * error text in IT
	 */
	private final String text_it;

	/**
	 * error text in NL
	 */
	private final String text_nl;

	/**
	 * error text in SV
	 */
	private final String text_sv;

	/**
	 * error text in CS
	 */
	private final String text_cs;

	/**
	 * error text in HU
	 */
	private final String text_hu;

	/**
	 * error text in DA
	 */
	private final String text_da;

	
	
	/**
	 * Constructor
	 * @param errCode error number
	 * @param text_de error text in DE
	 * @param text_en error text in EN
	 * @param text_fr error text in FR
	 * @param text_es error text in ES
	 * @param text_it error text in IT
	 * @param text_nl error text in NL
	 * @param text_sv error text in SV
	 * @param text_cs error text in CS
	 * @param text_hu error text in HU
	 * @param text_da error text in DA
	 */
	private CommunicationErrors(int errCode, String text_de, String text_en, String text_fr,
			String text_es, String text_it, String text_nl, String text_sv,
			String text_cs, String text_hu, String text_da) {
		this.errCode = errCode;
		this.text_de = text_de;
		this.text_en = text_en;
		this.text_fr = text_fr;
		this.text_es = text_es;
		this.text_it = text_it;
		this.text_nl = text_nl;
		this.text_sv = text_sv;
		this.text_cs = text_cs;
		this.text_hu = text_hu;
		this.text_da = text_da;
	}


	/**
	 * get error code
	 * @return error code
	 */
	public int getErrorCode() {
		return this.errCode;
	}

	/**
	 * get error text 
	 * @return error text
	 */
	public String getText(){
		return getText(getErrorCode());
	}

	
	/**
	 * get error text by error code
	 * @param errorCode error code
	 * @return error text
	 */
	public static String getText(int errorCode){
		try{
			String languageCode = Locale.getDefault().getLanguage();
			Log.d("Errors", "getText. Language Code = " + languageCode);
			CommunicationErrors item = CommunicationErrors.getItemByCode(errorCode);
			if (item == null) return null;
			
			String defaultText = item.text_en;
			
			if ("en".equalsIgnoreCase(languageCode)){
				return defaultText;
			}
			else if ("de".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_de(), defaultText);
			}
			else if ("fr".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_fr(), defaultText);
			}
			else if ("es".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_es(), defaultText);
			}
			else if ("it".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_it(), defaultText);
			}
			else if ("nl".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_nl(), defaultText);
			}
			else if ("sv".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_sv(), defaultText);
			}
			else if ("cs".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_cs(), defaultText);
			}
			else if ("hu".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_hu(), defaultText);
			}
			else if ("da".equalsIgnoreCase(languageCode)){
				return checkLocaleText(item.getText_da(), defaultText);
			}
			else
				return defaultText;
		} catch(Exception ex){
			Log.e("CommunicationErrors", "getText. Cannot get text for error code = " + errorCode + ". Error: " + ex);
			return null;
		}
	}

	/**
	 * check locale text and replace it if needed
	 * @param localeText locale text
	 * @param defaultText default text
	 * @return locale or default text
	 */
	private static String checkLocaleText(String localeText, String defaultText){
		if ((localeText == null)||(localeText.equalsIgnoreCase("")))
			return defaultText;
		else return localeText;
		
	}

	
	/**
	 * get enum item by error code
	 * @param errCode error code to search
	 * @return enum item or null if not found 
	 */
	public static CommunicationErrors getItemByCode(int errCode){
		for (CommunicationErrors i : CommunicationErrors.values()){
			if (i.getErrorCode() == errCode)
				return i;
		}
		return null;
	}
	
	/**
	 * get text in DE
	 * @return the text in DE
	 */
	public String getText_de() {
		return text_de;
	}


	/**
	 * get text in EN
	 * @return the text in EN
	 */
	public String getText_en() {
		return text_en;
	}


	/**
	 * get text in FR
	 * @return the text in FR
	 */
	public String getText_fr() {
		return text_fr;
	}


	/**
	 * get text in ES
	 * @return the text in ES
	 */
	public String getText_es() {
		return text_es;
	}


	/**
	 * get text in IT
	 * @return the text in IT
	 */
	public String getText_it() {
		return text_it;
	}


	/**
	 * get text in NL
	 * @return the text in NL
	 */
	public String getText_nl() {
		return text_nl;
	}


	/**
	 * get text in SV
	 * @return the text in SV
	 */
	public String getText_sv() {
		return text_sv;
	}


	/**
	 * get text in CS
	 * @return the text in CS
	 */
	public String getText_cs() {
		return text_cs;
	}


	/**
	 * get text in HU
	 * @return the text in HU
	 */
	public String getText_hu() {
		return text_hu;
	}


	/**
	 * get text in DA
	 * @return the text in DA
	 */
	public String getText_da() {
		return text_da;
	}

}
