package com.multimedia.aes.gestnet_sgsv2.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.constants.Constantes;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloSubirMantenimientoTerminado;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UploadService extends IntentService {
    private static final String TAG = UploadService.class.getSimpleName();

    public UploadService() {
        super("UploadService");
    }
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (Constantes.ACTION_RUN_ISERVICE.equals(action)) {
                handleActionRun();
            }
        }
    }


    private void handleActionRun() {
        try {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                    .setSmallIcon(android.R.drawable.stat_sys_download_done)
                    .setContentTitle("Cargando partes")
                    .setContentText("Procesando...");
            if (MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext())!=null){
                List<MantenimientoTerminado> list = MantenimientoTerminadoDAO.buscarTodosLosMantenimientoTerminados(getBaseContext());
                for (int i = 1; i <= list.size() ; i++) {
                    builder.setProgress(list.size(), i, false);
                    startForeground(1, builder.build());
                    Intent localIntent = new Intent(Constantes.ACTION_RUN_ISERVICE)
                            .putExtra(Constantes.EXTRA_PROGRESS, i);
                    LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
                    new HiloSubirMantenimientoTerminado(getBaseContext(),list.get(i).getId_mantenimiento_terminado());
                    Thread.sleep(3000);
                }
                // Quitar de primer plano
                stopForeground(true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroy() {
        Toast.makeText(this, "Servicio destruido...", Toast.LENGTH_SHORT).show();

        // Emisión para avisar que se terminó el servicio
        Intent localIntent = new Intent(Constantes.ACTION_PROGRESS_EXIT);
        LocalBroadcastManager.getInstance(this).sendBroadcast(localIntent);
    }
}