package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;

public class BuscadorPartes extends AppCompatActivity implements View.OnClickListener {

    private ImageView ivBuscar,ivAtras;
    private EditText etBuscar;
    private ListView lvPartes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscador_partes);
        ivBuscar = findViewById(R.id.ivBuscar);
        ivAtras = findViewById(R.id.ivAtras);
        etBuscar = findViewById(R.id.etBuscar);
        lvPartes = findViewById(R.id.lvPartes);

        ivBuscar.setOnClickListener(this);
        ivAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.ivBuscar){
            if (etBuscar.getText().toString().trim().length()<3){
                Dialogo.dialogoError("Es necesario escribir una cadena de al menos 3 caracteres.",this);
            }else{
                String parte = etBuscar.getText().toString().trim();
                //TODO enviar a un hilo el nº de parte o nº orden
            }
        }else if (view.getId()==R.id.ivAtras){
            finish();
        }
    }
}
