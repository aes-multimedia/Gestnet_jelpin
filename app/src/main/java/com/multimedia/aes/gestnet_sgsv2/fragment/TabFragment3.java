package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.multimedia.aes.gestnet_sgsv2.R;


public class TabFragment3 extends Fragment {
    private View vista;
    private Spinner estadoVisita,tipoVisita;
    private EditText codBarras,observaciones;
    private CheckBox contadorInterno,reparacion;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);

        estadoVisita = (Spinner) vista.findViewById(R.id.spEstado_visita);
        tipoVisita = (Spinner) vista.findViewById(R.id.spTipo_visita);
        codBarras = (EditText) vista.findViewById(R.id.etCodigo_barras);
        observaciones = (EditText) vista.findViewById(R.id.etObservaciones);
        contadorInterno = (CheckBox) vista.findViewById(R.id.cbContador_interno);
        reparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);





        return vista;
    }




}