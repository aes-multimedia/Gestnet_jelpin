package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;
import com.multimedia.aes.gestnet_sgsv2.BBDD.GuardarTecnicoPreLogin;
import com.multimedia.aes.gestnet_sgsv2.R;
        import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloPreLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class PreLogin extends AppCompatActivity {

    private JSONObject tecnico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login);
        try {
            tecnico = GestorSharedPreferences.getJsonTecnico(GestorSharedPreferences.getSharedPreferencesTecnico(this));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (tecnico.toString().equals("{}")){
            Intent i = new Intent(this,Login.class);
            startActivity(i);
            finish();
        }else{
            try {
                ManagerProgressDialog.abrirDialog(this);
                ManagerProgressDialog.cogerDatosServidor(this);
                new HiloPreLogin(tecnico.getString("nombre"), tecnico.getString("contraseña"), this).execute();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
    public void errorDeLogin(String mensaje) {
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
    public void loginOk(String mensaje){
        ManagerProgressDialog.guardarDatosTecnico(this);
        new GuardarTecnicoPreLogin(this,mensaje);
    }

    public void sacarMensaje(String s) {
        Toast.makeText(PreLogin.this, s, Toast.LENGTH_SHORT).show();
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

    public void siguienteActivity() {
        ManagerProgressDialog.cerrarDialog();
        Intent i = new Intent(this,Index.class);
        startActivity(i);
        finish();
    }
}
