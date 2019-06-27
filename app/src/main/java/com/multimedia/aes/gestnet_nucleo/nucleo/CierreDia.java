package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCierreDia;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class CierreDia extends AppCompatActivity implements View.OnClickListener, TextWatcher {
    private Button btnFechaCierre,btnHorasComida,btnHoraInicio,btnHoraFin,btnHorasExtra,btnHoraGuardia,btnEnviar;
    private TextView txtTotalHoras,txtTotalGastos;
    private EditText etDietas,etParking,etCombustible,etLitrosCombustible,etMaterial,etEntregado,etObservaciones;
    private CheckBox cbFestivo;
    //METODOS
    private void inicializarVariables(){
        //BUTTON
        btnFechaCierre = (Button) findViewById(R.id.btnFechaCierre);
        btnHorasComida = (Button) findViewById(R.id.btnHorasComida);
        btnHoraInicio = (Button) findViewById(R.id.btnHoraInicio);
        btnHoraFin = (Button) findViewById(R.id.btnHoraFin);
        btnHorasExtra = (Button) findViewById(R.id.btnHorasExtra);
        btnHoraGuardia = (Button) findViewById(R.id.btnHoraGuardia);
        btnEnviar = (Button) findViewById(R.id.btnEnviar);
        //TEXTVIEW
        txtTotalHoras = (TextView) findViewById(R.id.txtTotalHoras);
        txtTotalGastos = (TextView) findViewById(R.id.txtTotalGastos);
        //EDITTEXT
        etDietas = (EditText) findViewById(R.id.etDietas);
        etParking = (EditText) findViewById(R.id.etParking);
        etCombustible = (EditText) findViewById(R.id.etCombustible);
        etLitrosCombustible = (EditText) findViewById(R.id.etLitrosCombustible);
        etMaterial = (EditText) findViewById(R.id.etMaterial);
        etEntregado = (EditText) findViewById(R.id.etEntregado);
        etObservaciones = (EditText) findViewById(R.id.etObservaciones);
        //CHECKBOX
        cbFestivo = (CheckBox) findViewById(R.id.cbFestivo);
        //ONCLICK
        btnFechaCierre.setOnClickListener(this);
        btnHorasComida.setOnClickListener(this);
        btnHoraInicio.setOnClickListener(this);
        btnHoraFin.setOnClickListener(this);
        btnHorasExtra.setOnClickListener(this);
        btnHoraGuardia.setOnClickListener(this);
        btnEnviar.setOnClickListener(this);

        //TEXTWATCHER
        etDietas.addTextChangedListener(this);
        etParking.addTextChangedListener(this);
        etCombustible.addTextChangedListener(this);
        etLitrosCombustible.addTextChangedListener(this);
        etMaterial.addTextChangedListener(this);
        etEntregado.addTextChangedListener(this);
    }
    private void darValoresVariables(){
        Calendar mcurrentDate = Calendar.getInstance();
        int mYear = mcurrentDate.get(Calendar.YEAR);
        int mMonth = mcurrentDate.get(Calendar.MONTH)+1;
        String mes = "";
        if (mMonth<10){
            mes = "0"+mMonth;
        }else{
            mes = ""+mMonth;
        }
        int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
        String dia = "";
        if (mDay<10){
            dia = "0"+mDay;
        }else{
            dia = ""+mDay;
        }
        btnFechaCierre.setText(dia+"/"+mes+"/"+mYear);
    }
    private void actualizarHoras(){
        int horInicio = Integer.parseInt(btnHoraInicio.getText().toString().split(":")[0]);
        int minInicio = Integer.parseInt(btnHoraInicio.getText().toString().split(":")[1]);
        int horFin = Integer.parseInt(btnHoraFin.getText().toString().split(":")[0]);
        int minFin = Integer.parseInt(btnHoraFin.getText().toString().split(":")[1]);
        int horComida = Integer.parseInt(btnHorasComida.getText().toString().split(":")[0]);
        int minComida = Integer.parseInt(btnHorasComida.getText().toString().split(":")[1]);
        long inicio = horInicio*3600000+minInicio*60000;
        long fin = horFin*3600000+minFin*60000;
        long comida = horComida*3600000+minComida*60000;
        long result = fin-inicio-comida;
        int minutes = (int) ((result / (1000*60)) % 60);
        int hours   = (int) ((result / (1000*60*60)) % 24);
        String horas = hours < 10? "0"+String.valueOf(hours) : String.valueOf(hours);
        String minutos = minutes < 10? "0"+String.valueOf(minutes) : String.valueOf(minutes);
        txtTotalHoras.setText(String.valueOf(horas)+":"+String.valueOf(minutos));
    }
    public void finalizar(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("Cierre del dia guardado correctamente.");
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
    public void error(){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage("No se ha podido guardar el cierre del dia en estos momentos, por favor intentelo mas tarde.");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();

    }
    private void actualizarTotal(){
        double dietas=0,parking=0,combustible=0,material=0;
        if (!etDietas.getText().toString().trim().equals("")){
            dietas = Double.parseDouble(etDietas.getText().toString());
        }
        if (!etParking.getText().toString().trim().equals("")){
            parking = Double.parseDouble(etParking.getText().toString());
        }
        if (!etCombustible.getText().toString().trim().equals("")){
            combustible = Double.parseDouble(etCombustible.getText().toString());
        }
        if (!etMaterial.getText().toString().trim().equals("")){
            material = Double.parseDouble(etMaterial.getText().toString());
        }
        double total =dietas+parking+combustible+material ;
        txtTotalGastos.setText(total+"€");
    }
    //OVERRIDE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cierre_dia);
        inicializarVariables();
        darValoresVariables();
    }
    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnFechaCierre){
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("Seguro que quieres cambiar de fecha, se perderan todos los datos de este formulario que no hayas guardado.");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Aceptar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            Calendar mcurrentDate = Calendar.getInstance();
                            int mYear = mcurrentDate.get(Calendar.YEAR);
                            int mMonth = mcurrentDate.get(Calendar.MONTH)+1;
                            int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                            DatePickerDialog mDatePicker;
                            mDatePicker = new DatePickerDialog(CierreDia.this, new DatePickerDialog.OnDateSetListener() {
                                public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                                    String dia = "";
                                    String mes = "";
                                    if (selectedday<10){
                                        dia = "0"+selectedday;
                                    }else{
                                        dia = ""+selectedday;
                                    }selectedmonth = selectedmonth+1;
                                    if (selectedmonth+1<10){
                                        mes = "0"+selectedmonth;
                                    }else{
                                        mes = ""+selectedmonth;
                                    }
                                    btnFechaCierre.setText(dia+"/"+mes+"/"+selectedyear);
                                }
                            }, mYear, mMonth, mDay);
                            mDatePicker.setTitle("Seleccione fecha");
                            mDatePicker.show();
                            btnHorasComida.setText("00:00");
                            btnHoraInicio.setText("00:00");
                            btnHoraFin.setText("00:00");
                            txtTotalHoras.setText("00:00");
                            etDietas.setText("");
                            etParking.setText("");
                            etCombustible.setText("");
                            etLitrosCombustible.setText("");
                            etMaterial.setText("");
                            etEntregado.setText("");
                            txtTotalGastos.setText("0.00€");
                            btnHorasExtra.setText("00:00");
                            btnHoraGuardia.setText("00:00");
                            cbFestivo.setChecked(false);
                            etObservaciones.setText("");
                            dialog.cancel();
                        }
                    });
            builder1.setNegativeButton(
                    "Cancelar",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            dialog.cancel();
                        }
                    });
            AlertDialog alert11 = builder1.create();
            alert11.setCanceledOnTouchOutside(false);
            alert11.show();

        }else if (v.getId()==R.id.btnHorasComida){
            final int mHour = 0;
            final int mMin = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String min = "";
                    String hor = "";
                    if (hourOfDay<10){
                        hor = "0"+hourOfDay;
                    }else{
                        hor = hourOfDay+"";
                    }
                    if (minute<10){
                        min = "0"+minute;
                    }else{
                        min = ""+minute;
                    }
                    btnHorasComida.setText(hor+":"+min);
                    actualizarHoras();
                }
            },mHour,mMin,true);
            mTimePicker.setTitle("Seleccione Hora");
            mTimePicker.show();
        }else if (v.getId()==R.id.btnHoraInicio){
            final int mHour = 0;
            final int mMin = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String min = "";
                    String hor = "";
                    if (hourOfDay<10){
                        hor = "0"+hourOfDay;
                    }else{
                        hor = hourOfDay+"";
                    }
                    if (minute<10){
                        min = "0"+minute;
                    }else{
                        min = ""+minute;
                    }
                    btnHoraInicio.setText(hor+":"+min);
                    actualizarHoras();
                }
            },mHour,mMin,true);
            mTimePicker.setTitle("Seleccione Hora");
            mTimePicker.show();

        }else if (v.getId()==R.id.btnHoraFin){
            final int mHour = 0;
            final int mMin = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String min = "";
                    String hor = "";
                    if (hourOfDay<10){
                        hor = "0"+hourOfDay;
                    }else{
                        hor = hourOfDay+"";
                    }
                    if (minute<10){
                        min = "0"+minute;
                    }else{
                        min = ""+minute;
                    }
                    btnHoraFin.setText(hor+":"+min);
                    actualizarHoras();
                }
            },mHour,mMin,true);
            mTimePicker.setTitle("Seleccione Hora");
            mTimePicker.show();


        }else if (v.getId()==R.id.btnHorasExtra){
            final int mHour = 0;
            final int mMin = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String min = "";
                    String hor = "";
                    if (hourOfDay<10){
                        hor = "0"+hourOfDay;
                    }else{
                        hor = hourOfDay+"";
                    }
                    if (minute<10){
                        min = "0"+minute;
                    }else{
                        min = ""+minute;
                    }
                    btnHorasExtra.setText(hor+":"+min);
                }
            },mHour,mMin,true);
            mTimePicker.setTitle("Seleccione Hora");
            mTimePicker.show();
        }else if (v.getId()==R.id.btnHoraGuardia){
            final int mHour = 0;
            final int mMin = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    String min = "";
                    String hor = "";
                    if (hourOfDay<10){
                        hor = "0"+hourOfDay;
                    }else{
                        hor = hourOfDay+"";
                    }
                    if (minute<10){
                        min = "0"+minute;
                    }else{
                        min = ""+minute;
                    }
                    btnHoraGuardia.setText(hor+":"+min);
                }
            },mHour,mMin,true);
            mTimePicker.setTitle("Seleccione Hora");
            mTimePicker.show();
        }else if (v.getId()==R.id.btnEnviar){
            int fk_tecnico = 0;
            try {
                fk_tecnico = UsuarioDAO.buscarUsuario(this).getFk_entidad();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            int horDuracion = Integer.parseInt(txtTotalHoras.getText().toString().split(":")[0])*3600;
            int minDuracion = Integer.parseInt(txtTotalHoras.getText().toString().split(":")[1])*60;
            int duracion = horDuracion+minDuracion;
            int horGuardia = Integer.parseInt(btnHoraGuardia.getText().toString().split(":")[0])*3600;
            int minGuardia = Integer.parseInt(btnHoraGuardia.getText().toString().split(":")[1])*60;
            int horas_guardia = horGuardia+minGuardia;
            int horExtra = Integer.parseInt(btnHorasExtra.getText().toString().split(":")[0])*3600;
            int minExtra = Integer.parseInt(btnHorasExtra.getText().toString().split(":")[1])*60;
            int horas_extra = horExtra+minExtra;
            double dietas = (etDietas.getText().toString().equals("")) ? 0.0 : Double.parseDouble(etDietas.getText().toString().trim());
            double parking = (etParking.getText().toString().equals("")) ? 0.0 : Double.parseDouble(etParking.getText().toString().trim());
            double combustible = (etCombustible.getText().toString().equals("")) ? 0.0 : Double.parseDouble(etCombustible.getText().toString().trim());
            double litros_reposta = (etLitrosCombustible.getText().toString().equals("")) ? 0.0 : Double.parseDouble(etLitrosCombustible.getText().toString().trim());
            double entregado = (etEntregado.getText().toString().equals("")) ? 0.0 : Double.parseDouble(etEntregado.getText().toString().trim());
            double material = etMaterial.getText().toString().equals("") ? 0.0 : Double.parseDouble(etMaterial.getText().toString().trim());
            String horas_comida = btnHorasComida.getText().toString().trim();
            String hora_inicio = btnHoraInicio.getText().toString().trim();
            String hora_fin = btnHoraFin.getText().toString().trim();
            String observaciones = etObservaciones.getText().toString().trim();
            String fecha_cierre = btnFechaCierre.getText().toString().trim();
            String oldFormat = "dd/MM/yyyy";
            String newFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
            SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
            try {
                fecha_cierre = sdf2.format(sdf1.parse(fecha_cierre));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            boolean festivo;
            if (cbFestivo.isChecked()){
                festivo =  true;
            }else{
                festivo = false;
            }
            new HiloCierreDia(this,fk_tecnico,duracion,horas_guardia,horas_extra,dietas,parking,
                    combustible,litros_reposta,entregado,material,horas_comida,hora_inicio,hora_fin,
                    observaciones,fecha_cierre,festivo).execute();
        }
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }
    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        actualizarTotal();
    }
    @Override
    public void afterTextChanged(Editable s) {

    }
}
