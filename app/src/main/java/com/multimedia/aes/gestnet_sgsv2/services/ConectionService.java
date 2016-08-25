package com.multimedia.aes.gestnet_sgsv2.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;

import com.multimedia.aes.gestnet_sgsv2.constants.Constantes;

import java.util.Timer;
import java.util.TimerTask;

public class ConectionService extends Service {
    private static final String TAG = ConectionService.class.getSimpleName();
    TimerTask timerTask;

    public ConectionService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public void onCreate() {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Timer timer = new Timer();

        timerTask = new TimerTask() {
            @Override
            public void run() {
                boolean conectado = compruebaConexion(getBaseContext());
                String availMem;
                if (conectado){
                    availMem = "1";
                }else{
                    availMem = "0";
                }
                Intent localIntent = new Intent(Constantes.ACTION_RUN_SERVICE)
                        .putExtra(Constantes.EXTRA_MEMORY, availMem);

                // Emitir el intent a la actividad
                LocalBroadcastManager.
                        getInstance(ConectionService.this).sendBroadcast(localIntent);
            }
        };

        timer.scheduleAtFixedRate(timerTask, 0, 10000);

        return START_NOT_STICKY;
    }

    public static boolean compruebaConexion(Context context) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (int i = 0; i < redes.length; i++) {
            // Si alguna red tiene conexión, se devuelve true
            if (redes[i].getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }
    @Override
    public void onDestroy() {
        timerTask.cancel();
        Intent localIntent = new Intent(Constantes.ACTION_MEMORY_EXIT);

        // Emitir el intent a la actividad
        LocalBroadcastManager.
                getInstance(ConectionService.this).sendBroadcast(localIntent);
    }


}