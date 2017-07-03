package com.multimedia.aes.gestnet_nucleo.dialogo;

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
    public static void cerrarDialog(){
        p.dismiss();
    }
    public static ProgressDialog getDialog(){
        return p;
    }
}
