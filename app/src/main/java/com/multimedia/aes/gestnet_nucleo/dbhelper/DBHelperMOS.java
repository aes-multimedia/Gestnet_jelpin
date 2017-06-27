package com.multimedia.aes.gestnet_nucleo.dbhelper;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;

import com.j256.ormlite.android.apptools.OpenHelperManager;

/**
 * 
 * @author Rodrigo</br>
 * Esta clase sirve para cargar el dbHelper, siempre que lo necesitemos simplemente debemos
 * extender de ella.
 * 
 */
public abstract class DBHelperMOS extends ActionBarActivity {
	public static DBHelper mDBHelper;
	
	protected static DBHelper getHelper(Context context) {
		if (mDBHelper == null) {
			mDBHelper = OpenHelperManager.getHelper(context, DBHelper.class);
		}
		return mDBHelper;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (mDBHelper != null) {
			OpenHelperManager.releaseHelper();
			mDBHelper = null;
		}
	}
}
