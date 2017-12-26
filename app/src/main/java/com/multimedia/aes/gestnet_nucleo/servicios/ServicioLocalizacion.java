package com.multimedia.aes.gestnet_nucleo.servicios;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.nucleo.HiloLoc;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;

/**
 * Created by acp on 26/12/2017.
 */

public class ServicioLocalizacion extends Service{






    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void onCreate(){
        super.onCreate();
    }




    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");
        final Handler handler = new Handler();
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    public void run() {
                        try {

                           HiloLoc hiloLoc = new HiloLoc();
                           hiloLoc.execute();

                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 3000);
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        Log.d(TAG, "Servicio detenido...");
        super.onDestroy();

    }

}
