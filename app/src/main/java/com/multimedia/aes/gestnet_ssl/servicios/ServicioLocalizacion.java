package com.multimedia.aes.gestnet_ssl.servicios;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.SettingsClient;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.hilos.HiloLoc;

import java.sql.SQLException;
import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static com.google.android.gms.location.LocationServices.getFusedLocationProviderClient;


public class ServicioLocalizacion extends Service{
    private TimerTask task;
    private float lon, lat;
    private Usuario usuario;
    private int fk_tecnico;
    private LocationRequest mLocationRequest;
    Cliente cliente;

    private long UPDATE_INTERVAL = 900 * 1000;  /* 15 mins */
    private long FASTEST_INTERVAL = 300 * 1000; /* 5 mins  */
    private long UPLOAD_TIMER = 600 * 1000; /* 10 mins  */


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @SuppressLint("RestrictedApi")
    public void onCreate() {

        try {
            usuario = UsuarioDAO.buscarUsuario(this);
            cliente= ClienteDAO.buscarCliente(this);
            fk_tecnico = usuario.getFk_entidad();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        mLocationRequest = new LocationRequest();
        mLocationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        mLocationRequest.setInterval(UPDATE_INTERVAL);
        mLocationRequest.setFastestInterval(FASTEST_INTERVAL);
        LocationSettingsRequest.Builder builder = new LocationSettingsRequest.Builder();
        builder.addLocationRequest(mLocationRequest);
        LocationSettingsRequest locationSettingsRequest = builder.build();
        SettingsClient settingsClient = LocationServices.getSettingsClient(this);
        settingsClient.checkLocationSettings(locationSettingsRequest);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                        this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        getFusedLocationProviderClient(this).requestLocationUpdates(mLocationRequest, new LocationCallback() {
                    @Override
                    public void onLocationResult(LocationResult locationResult) {
                        lon=(float) locationResult.getLastLocation().getLongitude();
                        lat=(float) locationResult.getLastLocation().getLatitude();

                    }
                },
                Looper.myLooper());
        super.onCreate();
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");
        final Handler handler = new Handler();
        Timer timer = new Timer();
        task = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @SuppressLint("MissingPermission")
                    public void run() {
                        try {
                            HiloLoc hiloLoc = new HiloLoc(fk_tecnico,lon,lat,cliente);
                            hiloLoc.execute();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, UPLOAD_TIMER);
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        Log.d(TAG, "Servicio detenido...");
        task.cancel();
        super.onDestroy();

    }
}
