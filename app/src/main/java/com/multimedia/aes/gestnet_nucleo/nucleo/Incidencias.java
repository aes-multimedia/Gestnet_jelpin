package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;

public class Incidencias extends Activity implements View.OnClickListener {

    private EditText etNombreTecnico, etIncidencia;
    private Button btnEnviarIncidencia;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.incidencias);
        etIncidencia = (EditText) findViewById(R.id.etIncidencia);
        etNombreTecnico = (EditText) findViewById(R.id.etNombreTecnico);
        btnEnviarIncidencia = (Button) findViewById(R.id.btnEnviarIncidencia);
        btnEnviarIncidencia.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (etIncidencia.getText().toString().trim().equals("")){
            etIncidencia.requestFocus();
            Toast.makeText(this, "Introduzca la incidencia", Toast.LENGTH_SHORT).show();
        }else if (etNombreTecnico.getText().toString().trim().equals("")){
            etNombreTecnico.requestFocus();
            Toast.makeText(this, "Introduzca su nombre", Toast.LENGTH_SHORT).show();
        }else{
            String incidencia = etIncidencia.getText().toString();
            String nombre = etNombreTecnico.getText().toString();
            String[] to = { "1bhavicente@gmail.com" };
            String[] cc = {};
            enviar(to, cc, "Incidencia AppAndroid",
                    "Incidencia: "+incidencia+"\n"+nombre+" ha enviado la incidencia");

        }
    }
    private void enviar(String[] to, String[] cc,
                        String asunto, String mensaje) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to);
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, asunto);
        emailIntent.putExtra(Intent.EXTRA_TEXT, mensaje);
        emailIntent.setType("message/rfc822");
        startActivity(Intent.createChooser(emailIntent, "Email "));
    }
}
