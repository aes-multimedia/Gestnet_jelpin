package com.multimedia.aes.gestnet_nucleo.dialogo;

import android.app.AlertDialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloKilometros;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DialogoBuscarArticulo extends DialogFragment implements View.OnClickListener {

    private View v;
    private Button btnAceptar,btnCancelar;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialogo_kilometros, container, false);
        id = getArguments().getInt("id");
        btnAceptar = v.findViewById(R.id.btnAceptar);
        btnCancelar = v.findViewById(R.id.btnCancelar);
        btnAceptar.setOnClickListener(this);
        btnCancelar.setOnClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAceptar) {
            if (!comprobarRelleno()){
                Dialogo.dialogoError("Rellene todos los campos.",getContext());
            }
        }else if (view.getId() == R.id.btnCancelar) {
            getDialog().dismiss();
        }

    }

    private boolean comprobarRelleno(){
        return true;
    }
}
