package com.multimedia.aes.gestnet_nucleo.nucleo;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.os.Bundle;

import android.support.annotation.Nullable;


import android.support.v4.widget.SwipeRefreshLayout;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;

import android.widget.ListView;


import com.multimedia.aes.gestnet_nucleo.R;

import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorIntervenciones;
import com.multimedia.aes.gestnet_nucleo.clases.Intervencion;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIntervencionesAnteriores;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;


public class IntervencionesAnteriores extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lvIndexIntervenciones;
    private SwipeRefreshLayout srl;




    private void inicializarVariables() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        srl = findViewById(R.id.lllistview);
        lvIndexIntervenciones =  findViewById(R.id.lvIndexIntervenciones);
        lvIndexIntervenciones.setOnItemClickListener(this);
    }

    public void listarIntervenciones(String msg) {
        AdaptadorIntervenciones adaptadorIntervenciones;
        try {

            JSONArray jsonElements = new JSONArray(msg);
            ArrayList<Intervencion> listaIntervenciones= new ArrayList<>();


            for (int i = 0; i < jsonElements.length() ; i++) {
                Intervencion intervencion = new Intervencion();
                int id_parte;
                if(jsonElements.getJSONObject(i).getString("id_parte").equals("") || jsonElements.getJSONObject(i).getString("id_parte").equals("null"))
                    id_parte=-1;
                else
                    id_parte=jsonElements.getJSONObject(i).getInt("id_parte");
                String tecnico;
                if(jsonElements.getJSONObject(i).getString("tecnico").equals("") || jsonElements.getJSONObject(i).getString("tecnico").equals("null"))
                    tecnico="Sin nombre";
                else
                    tecnico=jsonElements.getJSONObject(i).getString("tecnico");
                String fecha_visita;
                if(jsonElements.getJSONObject(i).getString("fecha_visita").equals("") || jsonElements.getJSONObject(i).getString("fecha_visita").equals("null"))
                    fecha_visita="Sin fecha";
                else
                    fecha_visita=jsonElements.getJSONObject(i).getString("fecha_visita");
                String otros_sintomas;
                if(jsonElements.getJSONObject(i).getString("otros_sintomas").equals("") || jsonElements.getJSONObject(i).getString("otros_sintomas").equals("null"))
                    otros_sintomas="Sin síntomas";
                else
                    otros_sintomas=jsonElements.getJSONObject(i).getString("otros_sintomas");
                double facturado;
                if(jsonElements.getJSONObject(i).getString("fact_total_con_iva").equals("null") || jsonElements.getJSONObject(i).getString("fact_total_con_iva").equals("") )
                    facturado=0;
                else
                    facturado=jsonElements.getJSONObject(i).getDouble("fact_total_con_iva");
                String operacion;
                if(jsonElements.getJSONObject(i).getString("operacion_efectuada").equals("") || jsonElements.getJSONObject(i).getString("operacion_efectuada").equals("null"))
                    operacion="Sin operacion efectuada";
                else
                    operacion=jsonElements.getJSONObject(i).getString("operacion_efectuada");

                intervencion.setTecnico(tecnico)
                            .setId_parte(id_parte)
                            .setFecha_visita(fecha_visita)
                            .setFacturado(facturado)
                            .setOperacion_efectuada(operacion)
                            .setOtros_sintomas(otros_sintomas);

                listaIntervenciones.add(intervencion);

            }


            adaptadorIntervenciones = new AdaptadorIntervenciones(this, R.layout.cardview_adapter_listview_intervenciones, listaIntervenciones);
            lvIndexIntervenciones.setAdapter(adaptadorIntervenciones);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervenciones_aneriores);
        inicializarVariables();
        srl.setRefreshing(false);
        setTitle("Histórico de Intervenciones");
        Intent intent = getIntent();
        if (intent != null) {
            int fk_maquina = intent.getIntExtra("fk_maquina", 0);
            new HiloIntervencionesAnteriores(this,fk_maquina).execute();

        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {


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
            alert11.show();


    }
}