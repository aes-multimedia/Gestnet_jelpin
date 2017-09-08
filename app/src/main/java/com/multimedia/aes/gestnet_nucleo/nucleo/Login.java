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

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class Login extends AppCompatActivity implements View.OnClickListener, TextWatcher {

    private EditText etUsuario,etContraseña;
    private Button btnLogin;
    private Usuario usuario;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        try {
            inicializarVariables();
            if (UsuarioDAO.buscarTodosLosUsuarios(this)!=null) {
                if (ParteDAO.buscarTodosLosPartes(this)!=null){
                    irIndex();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void inicializarVariables()throws SQLException{
        ////EDITTEXTS////
        etUsuario = (EditText)findViewById(R.id.etUsuario);
        etUsuario.addTextChangedListener(this);
        etContraseña = (EditText)findViewById(R.id.etContraseña);
        etContraseña.addTextChangedListener(this);
        ////BUTTONS////
        btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
        btnLogin.setClickable(true);
        btnLogin.setAlpha(0.5f);
        cliente = ClienteDAO.buscarTodosLosClientes(this).get(0);

    }
    public void guardarUsuario(String msg){
        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.guardar_datos));
            JSONObject jsonObject = new JSONObject(msg);
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarUsuario(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            Dialogo.dialogoError("Error en login",this);
            if (ManagerProgressDialog.getDialog()!=null){
                ManagerProgressDialog.cerrarDialog();
            }

            e.printStackTrace();
        }
    }
    public void obtenerPartes(){
        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.obtener_datos));
            usuario = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);

            new HiloPartes(this,usuario.getFk_entidad(),cliente.getIp_cliente()).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void guardarPartes(String msg){
        try {
            if (ManagerProgressDialog.getDialog()==null){
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.guardar_datos));
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado")==1){
                new GuardarParte(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void irIndex() {
        if(ManagerProgressDialog.getDialog()!=null){
            ManagerProgressDialog.cerrarDialog();
        }
        Intent i = new Intent(this,Index.class);
        startActivity(i);

    }
    public void sacarMensaje(String msg) {
        if (ManagerProgressDialog.getDialog()!=null){
            ManagerProgressDialog.cerrarDialog();
        }
        Dialogo.dialogoError(msg,this);
        try {
            BBDDConstantes.borrarDatosError(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
            if (ManagerProgressDialog.getDialog()!=null){
                ManagerProgressDialog.abrirDialog(this);
            }
            //ManagerProgressDialog.setMensaje(getResources().getString(R.string.obtener_datos));

            new HiloLogin(etUsuario.getText().toString().trim(),etContraseña.getText().toString().trim(),cliente.getIp_cliente(),this).execute();
        }
    }



}
