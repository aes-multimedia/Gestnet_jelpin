package com.multimedia.aes.gestnet_nucleo.servicios;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.LocationSource;
import com.multimedia.aes.gestnet_nucleo.nucleo.HiloLoc;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;

import java.util.Timer;
import java.util.TimerTask;

import static android.content.ContentValues.TAG;
import static java.lang.Thread.currentThread;


public class ServicioLocalizacion extends Service implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener,LocationListener{
    private TimerTask task;
    private GoogleApiClient mGoogleApiClient;
    private Location mLastLocation;
    private double lon,lat;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    public void onCreate(){
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        mGoogleApiClient.connect();
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
                    public void run() {
                        try {
                            Log.d(TAG, "Servicio sigue funcionando...");
                            HiloLoc hiloLoc = new HiloLoc(String.valueOf(lon),String.valueOf(lat));
                            hiloLoc.execute();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
                    }
                });
            }
        };
        timer.schedule(task, 0, 2000);
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        Log.d(TAG, "Servicio detenido...");
        task.cancel();
        super.onDestroy();

    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }
        mLastLocation = LocationServices.FusedLocationApi.getLastLocation(
                mGoogleApiClient);
        lon=mLastLocation.getLongitude();
        lat=mLastLocation.getLatitude();

    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

    @Override
    public void onLocationChanged(Location location) {
        lon=location.getLongitude();
        lat=location.getLatitude();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
