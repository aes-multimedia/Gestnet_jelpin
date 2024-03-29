package com.multimedia.aes.gestnet_ssl.nucleo;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.multimedia.aes.gestnet_ssl.BBDD.GuardarCliente;
import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.hilos.HiloCodCliente;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

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
        etCodCliente.addTextChangedListener(this);
        ////BUTTONS////
        btnEnviarCodCliente = (Button)findViewById(R.id.btnEnviarCodCliente);
        btnEnviarCodCliente.setOnClickListener(this);
        btnEnviarCodCliente.setClickable(false);
        btnEnviarCodCliente.setAlpha(0.5f);
        try {
            if (ClienteDAO.buscarCliente(this)!=null) {
                irLogin();
            } else {
                var json = "{\n" +
                        "    \"estado\": 1,\n" +
                        "    \"cliente\": {\n" +
                        "        \"id_cliente\": \"76\",\n" +
                        "        \"cliente\": null,\n" +
                        "        \"logo\": null,\n" +
                        "        \"color\": null,\n" +
                        "        \"dir_documentos\": \"jelpin\",\n" +
                        "        \"IP\": \"jelpin.gestnet.es\",\n" +
                        "        \"bbdd\": null,\n" +
                        "        \"user_bdd\": null,\n" +
                        "        \"pass_bdd\": null,\n" +
                        "        \"cod_cliente\": null,\n" +
                        "        \"proteccion_datos\": null\n" +
                        "    }\n" +
                        "}";
                new GuardarCliente(this, json).execute();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void irLogin(){
        Intent i = new Intent(this,Login.class);
        startActivity(i);

    }
    public void guardarCliente(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarCliente(this,msg).execute();
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void sacarMensaje(String msg) {
        Dialogo.dialogoError(msg,this);
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
        if (v.getId()==R.id.btnEnviarCodCliente){
            try {
                BBDDConstantes.borrarDatosTablas(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            new HiloCodCliente(etCodCliente.getText().toString().trim(),this).execute();
        }
    }

    @Override
    public void onBackPressed() {
        recreate();
    }

    @Override
    public boolean onKeyLongPress(int keyCode, KeyEvent event) {
        if(event.getKeyCode()==KeyEvent.FLAG_CANCELED_LONG_PRESS);
        finish();
        return true;
    }
}
