package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarCliente;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etUsuario,etContraseña;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        inicializarVariables();
        try {
            if (UsuarioDAO.buscarTodosLosUsuarios(this)!=null) {
                irIndex();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void inicializarVariables() {
        ////EDITTEXTS////
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etUsuario.addTextChangedListener(this);
        etContraseña = (EditText)findViewById(R.id.etContraseña);
        etContraseña.addTextChangedListener(this);
        ////BUTTONS////
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnLogin.setClickable(false);
        btnLogin.setAlpha(0.5f);
    }
    public void guardarUsuario(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            Log.d("prueba",jsonObject.getString("usuario"));
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarUsuario(this,msg);
                new GuardarCliente(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void irIndex() {
        Intent i = new Intent(this,Index.class);
        startActivity(i);
    }
    public void sacarMensaje(String msg) {
        Dialogo.dialogoError(msg,this);
    }
    //////////@OVERRIDE METODS//////////
    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    ////Desabilita el boton si los campos usuario y contraseña estan vacios
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

        if (etUsuario.getText().toString().trim().isEmpty()&&!etContraseña.getText().toString().isEmpty()){
            btnLogin.setClickable(false);
            btnLogin.setAlpha(0.5f);
        }else{
            btnLogin.setClickable(true);
            btnLogin.setAlpha(1f);
        }
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnLogin){
            new HiloLogin(etUsuario.getText().toString().trim(),etContraseña.getText().toString().trim(),this).execute();
        }
    }



}
