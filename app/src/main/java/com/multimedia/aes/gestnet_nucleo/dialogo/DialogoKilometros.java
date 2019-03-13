package com.multimedia.aes.gestnet_nucleo.dialogo;

import android.app.DialogFragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DialogoKilometros extends DialogFragment implements View.OnClickListener {

    private View v;
    private Button btnAceptar,btnCancelar;
    int id;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    public static DialogoKilometros newInstance(int id) {
        DialogoKilometros fragment = new DialogoKilometros();

        Bundle bundle = new Bundle();
        bundle.putInt("id", id);
        fragment.setArguments(bundle);
        return fragment;
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
            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            String strDate = sdf.format(c.getTime());
            JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("fecha",strDate);
                GestorSharedPreferences.setJsonKilometros(GestorSharedPreferences.getSharedPreferencesKilometros(getContext()),jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            getDialog().dismiss();
        }else if (view.getId() == R.id.btnCancelar) {
            getDialog().dismiss();
        }

    }


}
