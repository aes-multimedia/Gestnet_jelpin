package com.multimedia.aes.gestnet_ssl.Utils;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

import com.multimedia.aes.gestnet_ssl.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.hilos.HiloCerrarParte;
import com.multimedia.aes.gestnet_ssl.hilos.HiloSubirImagen;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import java.sql.SQLException;
import java.util.List;

public class easyTakePhoto implements IPickResult  {

    Parte parte;
    Context context;
    int tipo = 1;
    boolean isFirma, isDocument;

    public easyTakePhoto(Parte parte, Context context, int tipo) {
        this.parte = parte;
        this.context = context;
        this.tipo = tipo;
    }

    public void takePhoto(FragmentActivity f){
        PickImageDialog.build(new PickSetup()
                .setTitle("Selecciona una opción")
                .setCameraButtonText("Camara")
                .setGalleryButtonText("Galeria")
                .setCancelText("CANCELAR")
                .setCancelTextColor(Color.RED))
                .setOnPickResult(this)
                .show(f);
    }

    @Override
    public void onPickResult(PickResult r) {
        result(r.getPath());
    }

    public void result(String path){

        switch (tipo){
            case 2:
                isDocument = true;
                break;
        }

        try {
            String nombre = path.substring(path.lastIndexOf('/')+1,path.length());

            boolean enviado = false;
            ImagenDAO.newImagen(context, nombre, path, parte.getId_parte(),-1,true, enviado, isDocument);
            //new HiloCerrarParte(context, parte.getId_parte()).execute();

        } catch (OutOfMemoryError memoryError){
            memoryError.printStackTrace();
        }
    }

    public static boolean hayConexion(Context c) {

        boolean connected = false;

        ConnectivityManager connec = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

        // Recupera todas las redes (tanto móviles como wifi)
        NetworkInfo[] redes = connec.getAllNetworkInfo();

        for (NetworkInfo rede : redes) {
            // Si alguna red tiene conexión, se devuelve true
            if (rede.getState() == NetworkInfo.State.CONNECTED) {
                connected = true;
            }
        }
        return connected;
    }
}
