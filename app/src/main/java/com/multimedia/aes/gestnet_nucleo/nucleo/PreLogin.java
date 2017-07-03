package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.multimedia.aes.gestnet_nucleo.R;

public class PreLogin extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etCodCliente;
    private Button btnEnviarCodCliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login);
        inicializarVariables();
    }

    private void inicializarVariables() {
        ////EDITTEXTS////
        etCodCliente = (EditText)findViewById(R.id.etCodCliente);
        ////BUTTONS////
        btnEnviarCodCliente = (Button)findViewById(R.id.btnEnviarCodCliente);
    }

    //////////@OVERRIDE METODS//////////
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    ////Desabilita el boton si el campo codigo cliente esta vacio
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (etCodCliente.getText().toString().trim().isEmpty()){
            btnEnviarCodCliente.setClickable(false);
            btnEnviarCodCliente.setAlpha(0.5f);
        }else{
            btnEnviarCodCliente.setClickable(true);
            btnEnviarCodCliente.setAlpha(1f);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void onClick(View v) {

    }
}
