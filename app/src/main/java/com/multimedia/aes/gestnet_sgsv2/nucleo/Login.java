package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.BBDD.GuardarTecnico;
import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloLogin;

import org.json.JSONException;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private Button btnLogin;
    private EditText etUsuario,etContraseña;
    private ImageView ivIncidencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etContraseña = (EditText)findViewById(R.id.etContraseña);
        btnLogin = (Button)findViewById(R.id.btnLogin);
        ivIncidencia = (ImageView) findViewById(R.id.ivIncidencia);
        etUsuario.addTextChangedListener(this);
        etContraseña.addTextChangedListener(this);
        btnLogin.setOnClickListener(this);
        ivIncidencia.setOnClickListener(this);
        btnLogin.setClickable(false);
        btnLogin.setAlpha(0.5f);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.ivIncidencia){
            Intent i = new Intent(this,Incidencias.class);
            startActivity(i);
            try {
                BBDDConstantes.borrarDatosTablas(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }else if (view.getId()==R.id.btnLogin) {
            ManagerProgressDialog.abrirDialog(this);
            ManagerProgressDialog.cogerDatosServidor(this);
            new HiloLogin(etUsuario.getText().toString().trim(), etContraseña.getText().toString().trim(), this).execute();
        }
    }

    public void errorDeLogin(String mensaje) {
        ManagerProgressDialog.cerrarDialog();
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_SHORT).show();
    }
    public void loginOk(String mensaje){
        ManagerProgressDialog.guardarDatosTecnico(this);
        new GuardarTecnico(this,mensaje);

    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        if (!etUsuario.getText().toString().trim().isEmpty()&&!etContraseña.getText().toString().isEmpty()){
            btnLogin.setClickable(true);
            btnLogin.setAlpha(1f);
        }else{
            btnLogin.setClickable(false);
            btnLogin.setAlpha(0.5f);
        }
    }

    @Override
    public void afterTextChanged(Editable editable) {

    }
    public void sacarMensaje(String msg){
        ManagerProgressDialog.cerrarDialog();
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
    }
    public void siguienteActivity(){
        ManagerProgressDialog.cerrarDialog();
        Intent i = new Intent(this,Index.class);
        startActivity(i);
        finish();
    }
}
