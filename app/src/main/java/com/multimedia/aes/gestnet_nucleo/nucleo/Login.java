package com.multimedia.aes.gestnet_nucleo.nucleo;

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

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;

import org.json.JSONException;
import org.json.JSONObject;

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
        }else if (view.getId()==R.id.btnLogin) {
            ManagerProgressDialog.abrirDialog(this);
            ManagerProgressDialog.cogerDatosServidor(this);
            new HiloLogin(etUsuario.getText().toString().trim(), etContraseña.getText().toString().trim(), this).execute();
        }
    }

    public void errorDeLogin(String mensaje) {
        ManagerProgressDialog.cerrarDialog();
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
        Toast.makeText(Login.this, mensaje, Toast.LENGTH_LONG).show();
    }
    public void loginOk(String mensaje){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("nombre",etUsuario.getText().toString());
            jsonObject.put("contraseña",etContraseña.getText().toString());
            GestorSharedPreferences.setJsonTecnico(GestorSharedPreferences.getSharedPreferencesTecnico(this),jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ManagerProgressDialog.guardarDatosTecnico(this);
        new GuardarTecnicoLogin(this,mensaje);

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
        Toast.makeText(Login.this, msg, Toast.LENGTH_SHORT).show();
        ManagerProgressDialog.cerrarDialog();
        try {
            BBDDConstantes.borrarDatosTablas(this);
            GestorSharedPreferences.clearSharedPreferencesTecnico(this);
            Intent i = new Intent(this,Login.class);
            startActivity(i);
            finish();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void siguienteActivity(){
        ManagerProgressDialog.cerrarDialog();
        Intent i = new Intent(this,Index.class);
        startActivity(i);
        finish();
    }
}
