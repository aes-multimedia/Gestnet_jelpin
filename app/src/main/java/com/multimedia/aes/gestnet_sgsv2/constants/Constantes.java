package com.multimedia.aes.gestnet_sgsv2.constants;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.StrictMode;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class Constantes {

	public static final String ERROR = "NOK";
	public static final String SUCCES = "OK";
	public static final int CAMARA = 1;
	public static final int IMAGE_MAX_SIZE = 800;
	public static final long UPDATE_INTERVAL = 30 * 1000;
	public static final long MINUTO = 60 * 1000;
	public static final long SERVICIOS_LENTOS = (MINUTO / UPDATE_INTERVAL) * 5;
	public static final long SERVICIOS_MUY_LENTOS = (MINUTO / UPDATE_INTERVAL) * 30;
	public static final int REQUEST_ENABLE_BT = 2;
	public static final String ACTION_RUN_SERVICE = "com.multimedia.aes.serviceout.action.RUN_SERVICE";
	public static final String ACTION_MEMORY_EXIT = "com.multimedia.aes.serviceout.action.MEMORY_EXIT";
	public static final String EXTRA_MEMORY = "com.multimedia.aes.serviceout.extra.MEMORY";
	public static final String ACTION_RUN_ISERVICE = "com.multimedia.aes.serviceout.action.RUN_INTENT_SERVICE";
	public static final String ACTION_PROGRESS_EXIT = "com.multimedia.aes.serviceout.action.PROGRESS_EXIT";
	public static final String EXTRA_PROGRESS = "com.multimedia.aes.serviceout.extra.PROGRESS";

}
