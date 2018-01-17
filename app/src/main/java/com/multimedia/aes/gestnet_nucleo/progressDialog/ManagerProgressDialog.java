package com.multimedia.aes.gestnet_nucleo.progressDialog;

import android.app.ProgressDialog;
import android.content.Context;

import com.multimedia.aes.gestnet_nucleo.R;

public abstract class ManagerProgressDialog {
    private static ProgressDialog p;
    public static void abrirDialog(Context context){
        p = new ProgressDialog(context);
        p.setCancelable(false);
        p.show();
    }
    public static void setMensaje(String msg){

        p.setMessage(msg);

    }
    public static void conectarTesto(Context context) {
        p.setMessage(context.getResources().getString(R.string.conectando_testo));
    }
    public static void conectadoTesto(Context context) {
        p.setMessage(context.getResources().getString(R.string.obteniendo_datos_testo));
    }
    public static void cerrarDialog(){
        p.dismiss();
    }
    public static ProgressDialog getDialog(){
        return p;
    }
}
