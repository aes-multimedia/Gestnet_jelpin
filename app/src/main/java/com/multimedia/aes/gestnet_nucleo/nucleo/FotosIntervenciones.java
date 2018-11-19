package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorImagenesIntervenciones;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorIntervenciones;
import com.multimedia.aes.gestnet_nucleo.clases.Intervencion;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloListarImagenesIntervencion;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class FotosIntervenciones extends AppCompatActivity {

    private ListView lvIndexFotosIntervenciones;

    private ArrayList<String> listaRutas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fotos_intervenciones);
        Toolbar toolbar = findViewById(R.id.toolbarFotosIntervenciones);
        setSupportActionBar(toolbar);
        setTitle("Imagenes de la intervención");
        int fk_parte=getIntent().getIntExtra("id_parte",-1);
        new HiloListarImagenesIntervencion(this,fk_parte).execute();



    }

    public void sacarMensaje(String mensaje) {


        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(mensaje);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();


    }
    @Override
    public void onBackPressed() {
       finish();
    }

    public void almacenarRutas(String mensaje) {
        listaRutas=new ArrayList<>();
        try {
            JSONArray jsonElements = new JSONArray(mensaje);
        for (int i = 0; i <= jsonElements.length() ; i++) {


            String ruta;
            if(jsonElements.getJSONObject(i).getString("imagen").equals("") || jsonElements.getJSONObject(i).getString("imagen").equals("null"))
                ruta="";
            else
                ruta=jsonElements.getJSONObject(i).getString("imagen");



            if(ruta.contains("../../../"))
                ruta= ruta.substring(8,ruta.length());

            listaRutas.add(ruta);

        }

        } catch (JSONException e) {
            e.printStackTrace();
        }





        mostrarImagenes();


    }


    private void mostrarImagenes() {

        AdaptadorImagenesIntervenciones adaptatorImagenesIntervenciones;
        lvIndexFotosIntervenciones=findViewById(R.id.lvIndexFotosIntervenciones);
        adaptatorImagenesIntervenciones = new AdaptadorImagenesIntervenciones(this, R.layout.camp_adapter_list_view_imagenes_intervencion, listaRutas);
        lvIndexFotosIntervenciones.setAdapter(adaptatorImagenesIntervenciones);

    }


}
