package com.multimedia.aes.gestnet_nucleo.alertDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;

import static com.multimedia.aes.gestnet_nucleo.fragment.TabFragment4.context;

public class Dialog {
    public void sacardialog(String texto){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Write your message here.");
        builder1.setCancelable(true);

        builder1.setPositiveButton(
                "Yes",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alert11 = builder1.create();
        alert11.show();
    }

}
