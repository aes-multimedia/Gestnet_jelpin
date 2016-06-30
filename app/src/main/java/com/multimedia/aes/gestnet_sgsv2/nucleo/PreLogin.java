package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;

import org.json.JSONException;

public class PreLogin extends AppCompatActivity {

    private String tecnico;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pre_login);
        try {
            tecnico = GestorSharedPreferences.getJsonTecnico(GestorSharedPreferences.getSharedPreferencesTecnico(this)).toString();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        if (tecnico.equals("{ ghf }")){
            Intent i = new Intent(this,Login.class);
            startActivity(i);
        }
    }
}
