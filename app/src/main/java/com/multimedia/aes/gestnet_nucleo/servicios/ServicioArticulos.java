package com.multimedia.aes.gestnet_nucleo.servicios;


import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarArticulos;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloArticulos;
import org.json.JSONException;
import java.sql.SQLException;

import static android.content.ContentValues.TAG;

public class ServicioArticulos extends Service {

    private Usuario usuario;
    private int fk_tecnico;

    public ServicioArticulos() {
    }

    public void onCreate() {

        try {
            usuario = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);
            fk_tecnico = usuario.getFk_entidad();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        super.onCreate();
    }



    @Override
    public IBinder onBind(Intent intent) {
      return null;
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "Servicio iniciado...");
        final Handler handler = new Handler();
                        try {
                            HiloArticulos hiloArticulos = new HiloArticulos(fk_tecnico,this);
                            hiloArticulos.execute();
                        } catch (Exception e) {
                            Log.e("error", e.getMessage());
                        }
        return START_NOT_STICKY;
    }
    public void onDestroy(){
        Log.d(TAG, "Servicio detenido...");
        super.onDestroy();
    }

    public void guardarArticulos(String msg){
        try {

            if(new GuardarArticulos(this, msg).guardarArticulos()) stopService(new Intent(this, ServicioArticulos.class));

        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


}
