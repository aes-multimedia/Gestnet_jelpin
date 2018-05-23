package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBuscarDocumentosParte;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloDatosPresupuesto;

import java.sql.SQLException;
import java.util.ArrayList;

public class Presupuestos extends AppCompatActivity {


    private Button btnGuardarPresupuesto, btnAdjuntarImagen;
    private Spinner spTipoInstalacion, spTipoTrabajo;
    private TextView nImagenesAdjuntadas,tvDireccionCliente,tvNombreCliente;
    private ArrayList ejemplo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuestos);

        spTipoInstalacion = findViewById(R.id.spTipoInstalacion);
        spTipoTrabajo = findViewById(R.id.spTipoTrabajo);
        tvDireccionCliente = findViewById(R.id.tvDireccionCliente);
        tvNombreCliente= findViewById(R.id.tvNombreCliente);
        int idParte=getIntent().getIntExtra("id_parte",0);

        Parte parte = null;

        try {

            parte = ParteDAO.buscarPartePorId(this,idParte);



            new HiloDatosPresupuesto(this).execute();


        }catch (SQLException e){
          e.printStackTrace();
        }

        tvDireccionCliente.setText(getDireccion(parte));
        tvNombreCliente.setText("Hommer J Simpson");
        //tvNombreCliente.setText(parte.getNombre_cliente());
        darValoresSpinner();

    }

    private CharSequence getDireccion(Parte parte){
        String dir ="";
/*
        if (!parte.getTipo_via().trim().equals("")&&!parte.getTipo_via().trim().equals("null")){
            dir+=parte.getTipo_via()+" ";
        }
        if (!parte.getVia().trim().equals("")&&!parte.getVia().trim().equals("null")){
            dir+=parte.getVia()+" ";
        }
        if (!parte.getNumero_direccion().trim().equals("")&&!parte.getNumero_direccion().trim().equals("null")){
            dir+="Nº "+parte.getNumero_direccion()+" ";
        }
        if (!parte.getEscalera_direccion().trim().equals("")&&!parte.getEscalera_direccion().trim().equals("null")){
            dir+="Esc. "+parte.getEscalera_direccion()+" ";
        }
        if (!parte.getPiso_direccion().trim().equals("")&&!parte.getPiso_direccion().trim().equals("null")){
            dir+="Piso "+parte.getPiso_direccion()+" ";
        }
        if (!parte.getPuerta_direccion().trim().equals("")&&!parte.getPuerta_direccion().trim().equals("null")){
            dir+=parte.getPuerta_direccion()+" ";
        }
        if (!parte.getMunicipio_direccion().trim().equals("")&&!parte.getMunicipio_direccion().trim().equals("null")){
            dir+="("+parte.getMunicipio_direccion()+"-";
        }
        if (!parte.getProvincia_direccion().trim().equals("")&&!parte.getProvincia_direccion().trim().equals("null")){
            dir+=parte.getProvincia_direccion()+")";
        }*/

        dir="Calle falsa 123, 28028, Springfield, Nevada";

        return dir;
    }


    public void darValoresSpinner(/*String mensaje, Context context*/) {

        //SPINNER FORMAS PAGO


        String[] arrayEjemplo = new String[6];
        arrayEjemplo[0] = "--Seleciones un tipo de trabajo--";
        arrayEjemplo[1] = "--Seleciones un valor--";
        arrayEjemplo[2] = "--Seleciones un valor--";
        arrayEjemplo[3] = "--Seleciones un valor--";
        arrayEjemplo[4] = "--Seleciones un valor--";
        arrayEjemplo[5] = "--Seleciones un valor--";

        spTipoInstalacion.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEjemplo));
        spTipoTrabajo.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayEjemplo));


    }

    public void sacarMensaje(String parte_sin_documentos) {



    }
}
