package com.multimedia.aes.gestnet_sgsv2.dialog;

import android.app.ProgressDialog;
import android.content.Context;
import com.multimedia.aes.gestnet_sgsv2.R;

public abstract class ManagerProgressDialog {
    private static ProgressDialog p;
    public static void abrirDialog(Context context){
        p = new ProgressDialog(context);
        p.setCancelable(false);
        p.show();
    }
    public static void cogerDatosServidor(Context context){
        p.setMessage(context.getResources().getString(R.string.coger_datos));
    }
    public static void guardarDatosTecnico(Context context){
        p.setMessage(context.getResources().getString(R.string.datos_tecnico));
    }
    public static void guardarDatosAveria(Context context){
        p.setMessage(context.getResources().getString(R.string.datos_averia));
    }
    public static void guardarDatosMantenimiento(Context context){
        p.setMessage(context.getResources().getString(R.string.datos_mantenimiento));
    }
    public static void cerrarDialog(){
        p.dismiss();
    }

}