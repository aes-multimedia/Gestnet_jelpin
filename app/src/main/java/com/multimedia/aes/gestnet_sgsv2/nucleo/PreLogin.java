package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Intent;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
        import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;

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
            Intent i = new Intent(this,Index.class);
            startActivity(i);
            finish();
            //AQUI HAY QUE HACER LA COMPROBACION DE SI HAY CAMBIOS EN LA BASE DE DATOS
        }
    }
}
