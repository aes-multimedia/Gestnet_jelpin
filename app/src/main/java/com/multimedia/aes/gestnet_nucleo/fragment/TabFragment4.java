package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.TimePickerDialog;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
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

import com.multimedia.aes.gestnet_nucleo.R;

import java.util.ArrayList;


public class TabFragment4 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private TextView tvDuracion;
    private Button btnAñadirDuracion;
    private String tiempoDuracion;
    private ArrayList<String> formasPago;
    private Spinner spFormaPago,spDisposicionServicio;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_4, container, false);

        inicializar();






        return vista;
    }

    private void inicializar(){
        tvDuracion = (TextView) vista.findViewById(R.id.tvDuracion);
        btnAñadirDuracion = (Button)vista.findViewById(R.id.btnAñadirDuracion);
        btnAñadirDuracion.setOnClickListener(this);
        spFormaPago = ( Spinner)vista.findViewById(R.id.spFormaPago) ;
        ArrayAdapter<String> spFormasDePago = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, getResources().getStringArray(R.array.forma_pago));
        spFormaPago.setAdapter(spFormasDePago);
        spDisposicionServicio = (Spinner) vista.findViewById(R.id.spDisposicionServicio);
        spDisposicionServicio.setAdapter(spFormasDePago);  ///CAMBIAR


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