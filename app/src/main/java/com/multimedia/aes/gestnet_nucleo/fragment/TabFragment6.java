package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;

import java.util.ArrayList;


public class TabFragment6 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private TextView tvDuracion;
    private Button btnAñadirDuracion;
    private String tiempoDuracion;
    private Spinner spFormaPago,spDisposicionServicio,spManoObra;


    private ArrayList <FormasPago> formasPagos = new ArrayList<>();
    private ArrayList <ManoObra> manosObra = new ArrayList<>();
    private ArrayList <Disposiciones> disposicionesServicio = new ArrayList<>();
    private String[] arrayFormasPago,arrayManosObra,arrayDisposiciones;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_6, container, false);
        FloatingActionButton fab = (FloatingActionButton)vista.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        inicializar();






        return vista;
    }

    private void inicializar(){



    }


    private void darValores(){


    }

    @Override
    public void onClick(View view) {

        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}