package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.TimePickerDialog;
import android.content.Context;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;

import java.util.ArrayList;
import java.util.List;


public class TabFragment4 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private TextView tvDuracion;
    private Button btnAñadirDuracion;
    private String tiempoDuracion;
    private Spinner spFormaPago,spDisposicionServicio,spManoObra;


    private ArrayList <FormasPago> formasPagos = new ArrayList<>();
    private ArrayList <ManoObra> manosObra = new ArrayList<>();
    private ArrayList <Disposiciones> disposicionesServicio = new ArrayList<>();
    private String[] arrayFormasPago,arrayManosObra,arrayDisposiciones;
    private boolean bool=false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_4, container, false);
       /* final FloatingActionButton fab = (FloatingActionButton) vista.findViewById(R.id.fab);
       final Snackbar snackbar = Snackbar.make(vista, "WiFi change detected; updating information...", Snackbar.LENGTH_INDEFINITE);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (view.getId() == fab.getId()) {
                    bool = !bool;
                    if (bool)
                        snackbar.show();
                    else
                        snackbar.dismiss();
                }


            }

        });*/
        inicializar();
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                vista.findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                        switch (item.getItemId()) {
                            case R.id.action_item1:
                              Toast.makeText(vista.getContext(),"uno",Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.action_item2:
                                Toast.makeText(vista.getContext(),"dos",Toast.LENGTH_SHORT).show();

                                break;
                            case R.id.action_item3:
                                Toast.makeText(vista.getContext(),"tres",Toast.LENGTH_SHORT).show();

                                break;
                        }

                        return true;
                    }
                });



        return vista;

    }


    private void inicializar(){


        tvDuracion = (TextView) vista.findViewById(R.id.tvDuracion);
        btnAñadirDuracion = (Button)vista.findViewById(R.id.btnAñadirDuracion);
        btnAñadirDuracion.setOnClickListener(this);
        spFormaPago = ( Spinner)vista.findViewById(R.id.spFormaPago) ;
       // spFormaPago.setOnClickListener(this);
        spDisposicionServicio = (Spinner) vista.findViewById(R.id.spDisposicionServicio);
     //   spDisposicionServicio.setOnClickListener(this);
        spManoObra = (Spinner) vista.findViewById(R.id.spManoObra);

        darValores();


    }


    private void darValores(){

        //SPINNER FORMAS PAGO
        if (FormasPagoDAO.buscarTodasLasFormasPago(getContext())!=null){
            formasPagos.addAll(FormasPagoDAO.buscarTodasLasFormasPago(getContext()));
            arrayFormasPago = new String[formasPagos.size()+ 1];
            arrayFormasPago[0]= "--Seleciones un valor--";
            for (int i = 1; i < formasPagos.size() + 1; i++) {
                arrayFormasPago[i] = formasPagos.get(i - 1).getForma_pago();
            }
            spFormaPago.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayFormasPago));

        }


        //SPINNER MANOS DE OBRA
        if(ManoObraDAO.buscarTodasLasManoDeObra(getContext())!=null) {
            manosObra.addAll(ManoObraDAO.buscarTodasLasManoDeObra(getContext()));
            arrayManosObra = new String[manosObra.size() + 1];
            arrayManosObra[0] = "--Seleciones un valor--";
            for (int i = 1; i < manosObra.size() + 1; i++) {
                arrayManosObra[i] = manosObra.get(i - 1).getConcepto();
            }
            spManoObra.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayManosObra));
        }
        //SPINNER DISPOSICIONES SERVICIO
        if(DisposicionesDAO.buscarTodasLasDisposiciones(getContext())!=null){
            disposicionesServicio.addAll(DisposicionesDAO.buscarTodasLasDisposiciones(getContext()));
            arrayDisposiciones = new String[disposicionesServicio.size()+ 1];
            arrayDisposiciones[0]= "--Seleciones un valor--";
            for (int i = 1; i < disposicionesServicio.size() + 1; i++) {
                arrayDisposiciones[i] = disposicionesServicio.get(i - 1).getNombre_disposicion();
            }
            spDisposicionServicio.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayDisposiciones));
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnAñadirDuracion.getId()){
            // TODO Auto-generated method stub
            Calendar mcurrentTime = Calendar.getInstance();
            int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
            int minute = mcurrentTime.get(Calendar.MINUTE);
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getContext() , new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    tvDuracion.setText( selectedHour + " horas " + selectedMinute+" minutos");
                }
            }, hour, minute, true);
            mTimePicker.setTitle("Selecciona la duración");
            mTimePicker.show();

        }

        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}