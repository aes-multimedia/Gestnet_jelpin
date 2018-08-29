package com.multimedia.aes.gestnet_nucleo.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.constantes.Constantes;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Disposiciones;
import com.multimedia.aes.gestnet_nucleo.entidades.FormasPago;
import com.multimedia.aes.gestnet_nucleo.entidades.ManoObra;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCerrarParte;
import com.multimedia.aes.gestnet_nucleo.nucleo.FirmaCliente;
import com.multimedia.aes.gestnet_nucleo.nucleo.GestionMateriales;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import static java.lang.Math.round;


public class TabFragment4_finalizacion extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener,CompoundButton.OnCheckedChangeListener,View.OnFocusChangeListener {
    private View vista;
    private double preeu_mano_de_obra_horas;
    private String tiempoDuracion;
    private ArrayList<FormasPago> formasPagos = new ArrayList<>();
    private ArrayList<ManoObra> manosObra = new ArrayList<>();
    private ArrayList<Disposiciones> disposicionesServicio = new ArrayList<>();
    private String[] arrayFormasPago, arrayManosObra, arrayDisposiciones;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;

    private EditText etOperacionEfectuada, et_preeu_materiales, et_preeu_mano_de_obra_horas,
            et_preeu_puesta_marcha, et_preeu_servicio_urgencia, et_preeu_km, et_preeu_km_precio,
            et_preeu_analisis_combustion, et_preeu_otros_nombre,
            et_preeu_adicional, etSubTotal, et_preeu_iva_aplicado, et_total, et_total_ppto, et_preeu_total_mano_de_obra_horas,et_preeu_total_disposicion_servicio;

    private boolean acepta_presupuesto = false;
    private static DecimalFormat df2 = new DecimalFormat(",##");
    private Button btnFinalizar, btn_preeu_mano_de_obra, btnFirmar;
    private Spinner sp_preeu_disposicion_servicio, sp_preeu_mano_de_obra_precio, spFormaPago;
    private CheckBox cb_acepta_presupuesto;

    private double precioTotalArticulos, preeu_adicional, preeu_analisis_combustion,
            preeu_km_precio_total, preeu_materiales, preeu_total_mano_de_obra_horas, preeu_disposicion_servicio, preeu_puesta_marcha, preeu_servicio_urgencia, preeu_km, preeu_km_precio,preeu_mano_de_obra_precio;

    private String textoBoton;
    private int forma_pago;







    private Button BtnVerMateriales;



    //METODO
    private void inicializar() {
        //EDITTEXT
        et_preeu_materiales = (EditText) vista.findViewById(R.id.et_preeu_materiales);
        et_preeu_materiales.setEnabled(false);
        et_preeu_materiales.setText(String.valueOf(0));
        et_preeu_total_mano_de_obra_horas = (EditText) vista.findViewById(R.id.et_preeu_total_mano_de_obra_horas);
        et_preeu_total_mano_de_obra_horas.setEnabled(false);
        et_preeu_puesta_marcha = (EditText) vista.findViewById(R.id.et_preeu_puesta_marcha);
        et_preeu_servicio_urgencia = (EditText) vista.findViewById(R.id.et_preeu_servicio_urgencia);
        et_preeu_km = (EditText) vista.findViewById(R.id.et_preeu_km);
        et_preeu_km_precio = (EditText) vista.findViewById(R.id.et_preeu_km_precio);
        et_preeu_analisis_combustion = (EditText) vista.findViewById(R.id.et_preeu_analisis_combustion);
        et_preeu_otros_nombre = (EditText) vista.findViewById(R.id.et_preeu_otros_nombre);
        et_preeu_total_disposicion_servicio = (EditText) vista.findViewById(R.id.et_preeu_total_disposicion_servicio);
        et_preeu_total_disposicion_servicio.setEnabled(false);
        et_preeu_adicional = (EditText) vista.findViewById(R.id.et_preeu_adicional);
        etSubTotal = (EditText) vista.findViewById(R.id.etSubTotal);
        et_preeu_iva_aplicado = (EditText) vista.findViewById(R.id.et_preeu_iva_aplicado);
        et_total = (EditText) vista.findViewById(R.id.et_total);
        cb_acepta_presupuesto = (CheckBox) vista.findViewById(R.id.cb_acepta_presupuesto);
        cb_acepta_presupuesto.setOnCheckedChangeListener(this);
        etOperacionEfectuada = (EditText) vista.findViewById(R.id.etOperacionEfectuada);
        etOperacionEfectuada.setOnFocusChangeListener(this);
        //BUTTON
        btn_preeu_mano_de_obra = (Button) vista.findViewById(R.id.btn_preeu_mano_de_obra);
        btnFinalizar = (Button) vista.findViewById(R.id.btnFinalizar);
        btnFirmar = (Button) vista.findViewById(R.id.btnFirmar);


        //ONCLICK
        btn_preeu_mano_de_obra.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        btnFirmar.setOnClickListener(this);
        //SPINNER
        spFormaPago = (Spinner) vista.findViewById(R.id.spFormaPago);
        spFormaPago.setOnItemSelectedListener(this);
        sp_preeu_disposicion_servicio = (Spinner) vista.findViewById(R.id.sp_preeu_disposicion_servicio);
        sp_preeu_disposicion_servicio.setOnItemSelectedListener(this);
        sp_preeu_mano_de_obra_precio = (Spinner) vista.findViewById(R.id.sp_preeu_mano_de_obra_precio);
        sp_preeu_mano_de_obra_precio.setOnItemSelectedListener(this);


        /**
         *
         * NUEVA VERSION BOTON EN PRUEBAS COMENTAR BOTON ANTES DE PASAR A PRODUCCION
         *
         * */
        BtnVerMateriales = vista.findViewById(R.id.btnVerMateriales);
        BtnVerMateriales. setOnClickListener(this);
        /**
         *
         *
         *
         *
         *
         * */


        darValores();


    }

    @Override
    public void onResume() {
        super.onResume();
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            maquina = MaquinaDAO.buscarMaquinaPorId(getContext(), parte.getFk_maquina());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();

            float precioMaterial = 0;
            int numeroMateriales = 0;

            if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));


                et_preeu_materiales.setText(String.format("%.2f", (getPrecioTotalArticulosPartePpto(articuloPartes))));
            } else {
                et_preeu_materiales.setText("0.00");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void darValores() {


        //SPINNER FORMAS PAGO
        if (FormasPagoDAO.buscarTodasLasFormasPago(getContext()) != null) {
            formasPagos.addAll(FormasPagoDAO.buscarTodasLasFormasPago(getContext()));
            arrayFormasPago = new String[formasPagos.size() + 1];
            arrayFormasPago[0] = "--Seleciones un valor--";
            for (int i = 1; i < formasPagos.size() + 1; i++) {
                arrayFormasPago[i] = formasPagos.get(i - 1).getForma_pago();
            }
            spFormaPago.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayFormasPago));

        }


        //SPINNER MANOS DE OBRA
        if (ManoObraDAO.buscarTodasLasManoDeObra(getContext()) != null) {
            manosObra.addAll(ManoObraDAO.buscarTodasLasManoDeObra(getContext()));
            arrayManosObra = new String[manosObra.size() + 1];
            arrayManosObra[0] = "--Seleciones un valor--";
            for (int i = 1; i < manosObra.size() + 1; i++) {
                arrayManosObra[i] = manosObra.get(i - 1).getConcepto();
            }
            sp_preeu_mano_de_obra_precio.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayManosObra));
        }
        //SPINNER DISPOSICIONES SERVICIO
        if (DisposicionesDAO.buscarTodasLasDisposiciones(getContext()) != null) {
            disposicionesServicio.addAll(DisposicionesDAO.buscarTodasLasDisposiciones(getContext()));
            arrayDisposiciones = new String[disposicionesServicio.size() + 1];
            arrayDisposiciones[0] = "--Seleciones un valor--";
            for (int i = 1; i < disposicionesServicio.size() + 1; i++) {
                arrayDisposiciones[i] = disposicionesServicio.get(i - 1).getNombre_disposicion();
            }
            sp_preeu_disposicion_servicio.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayDisposiciones));
        }



        if(parte.getFirma64()!=null && !parte.getFirma64().equals("")){

            btnFirmar.setText("FIRMADO");
            btnFirmar.setEnabled(false);
        }

        if(parte.getTextoDuracion()!=null && !parte.getTextoDuracion().equals("")){
            btn_preeu_mano_de_obra.setText(parte.getTextoDuracion());

        }


        if(datos.getOperacion_efectuada()!=null && !datos.getOperacion_efectuada().equals(""))
            etOperacionEfectuada.setText(datos.getOperacion_efectuada());


        et_preeu_total_disposicion_servicio.setText(String.valueOf(datos.getPreeu_disposicion_servicio()));
        et_preeu_total_mano_de_obra_horas
                .setText(String.valueOf(datos.getPreeu_mano_de_obra()
                        *
                datos.getPreeu_mano_de_obra_precio()));




    }

    private double getPrecioTotalArticulosPartePpto(ArrayList<ArticuloParte> listaArticulos) {
        double precio = 0;
        try {
            //Si el articulo no está en garantia entonces sesuma el precio de ese articulo

            for (ArticuloParte articulo : listaArticulos) {

                Articulo art;
                art=ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo());

                if(art.isGarantia()!=true && art.isPresupuestar()){
                    precio = precio + art.getTarifa()*articulo.getUsados();
                                 }
            }
        } catch (SQLException e) {


            Log.w("getPrecioTotal", "error al sumar precio articulos parte");
        }

        return precio;
    }


    private double getPrecioTotalArticulosParteFacturar(ArrayList<ArticuloParte> listaArticulos) {
        double precio = 0;
        try {
            //Si el articulo no está en garantia entonces sesuma el precio de ese articulo

            for (ArticuloParte articulo : listaArticulos) {

                Articulo art;
                art=ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo());
                if(art.isGarantia()!=true && art.isFacturar()){
                    precio = precio + art.getTarifa()*articulo.getUsados();
                }
            }
        } catch (SQLException e) {


            Log.w("getPrecioTotal", "error al sumar precio articulos parte");
        }

        return precio;
    }

    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment4_finalizacion_nuevo_v2, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(), parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorId(getContext(), parte.getFk_maquina());
            if (DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(), parte.getId_parte()) != null) {
                datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(), parte.getId_parte());
            } else {
                DatosAdicionales datosAdicionales = new DatosAdicionales();
                datosAdicionales.setFk_parte(parte.getId_parte());
                datos = DatosAdicionalesDAO.crearDatosAdicionalesRet(datosAdicionales, getContext());
            }
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializar();
        //recalcular();


        return vista;

    }

    private void recalcular() {


        try {
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
            if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                if (!articuloPartes.isEmpty()) {
                    precioTotalArticulos = getPrecioTotalArticulosPartePpto(articuloPartes);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preeu_disposicion_servicio=DatosAdicionalesDAO.buscarDisposicionDeServicio(getContext(),datos.getId_rel());
            et_preeu_total_disposicion_servicio.setText(String.valueOf(preeu_disposicion_servicio));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try{
            preeu_mano_de_obra_precio = DatosAdicionalesDAO.buscarPrecioManoDeObra(getContext(),datos.getId_rel());
        }catch (SQLException e) {
            e.printStackTrace();
        }


        try{
            preeu_mano_de_obra_horas= DatosAdicionalesDAO.buscarHorasManoDeObra(getContext(),datos.getId_rel());
        }catch (SQLException e) {
            e.printStackTrace();
        }

        preeu_total_mano_de_obra_horas = preeu_mano_de_obra_precio * preeu_mano_de_obra_horas;

        if (!et_preeu_materiales.getText().toString().equals("")) {
            String str = et_preeu_materiales.getText().toString();
            str = str.replace(',', '.');
            preeu_materiales = Double.valueOf(str);
        }

        if (!et_preeu_puesta_marcha.getText().toString().equals("")) {
            preeu_puesta_marcha = Double.valueOf(et_preeu_puesta_marcha.getText().toString());
        }

        if (!et_preeu_servicio_urgencia.getText().toString().equals("")) {
            preeu_servicio_urgencia = Double.valueOf(et_preeu_servicio_urgencia.getText().toString());
        }

        if (!et_preeu_km.getText().toString().equals("")) {
            preeu_km = Double.valueOf(et_preeu_km.getText().toString());
        }

        if (!et_preeu_km_precio.getText().toString().equals("")) {
            preeu_km_precio = Double.valueOf(et_preeu_km_precio.getText().toString());
        }
        preeu_km_precio_total = preeu_km * preeu_km_precio;

        if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
            preeu_analisis_combustion = Double.valueOf(et_preeu_analisis_combustion.getText().toString());
        }

        if (!et_preeu_adicional.getText().toString().equals("")) {
            preeu_adicional = Double.valueOf(et_preeu_adicional.getText().toString());
        }


        double SubTotal = preeu_adicional + preeu_analisis_combustion +
                preeu_km_precio_total + preeu_materiales + preeu_total_mano_de_obra_horas + preeu_disposicion_servicio + preeu_puesta_marcha + preeu_servicio_urgencia;
        etSubTotal.setText(String.valueOf(String.format("%.2f", SubTotal)));
        double preeu_iva_aplicado = SubTotal * 21 / 100;
        et_preeu_iva_aplicado.setText(String.format("%.2f", preeu_iva_aplicado));
        double total = SubTotal + preeu_iva_aplicado;
        et_total.setText(String.format("%.2f", total));

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==BtnVerMateriales.getId()){

            Intent i = new Intent(getContext(), GestionMateriales.class);
            i.putExtra("fk_parte",parte.getId_parte());
            startActivityForResult(i,654);

        }else{


        Calendar c = Calendar.getInstance();

        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        if (view.getId() == R.id.btnFirmar) {


            Intent i = new Intent(getContext(), FirmaCliente.class);
            startActivityForResult(i, 99);


        }else if (view.getId() == R.id.btn_preeu_mano_de_obra) {
            int hour = 0;
            final int minute = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {


                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {


                    btn_preeu_mano_de_obra.setText(selectedHour + " horas " + selectedMinute + " minutos");
                    textoBoton=selectedHour + " horas " + selectedMinute + " minutos";
                    double minutos = selectedMinute;
                    minutos = minutos / 60;
                    preeu_mano_de_obra_horas = Double.valueOf(selectedHour + minutos);
                    try {
                        ParteDAO.actualizarTextoDuracion(getContext(),parte.getId_parte(),textoBoton);
                        DatosAdicionalesDAO.actualizarHorasManoDeObra(getContext(),datos.getId_rel(),preeu_mano_de_obra_horas);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition() != 0) {

                        double mH = 0;
                        try {
                            mH = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        et_preeu_total_mano_de_obra_horas.setText(String.valueOf(mH * preeu_mano_de_obra_horas));



                    }

                }
            }, hour, minute, true);
            mTimePicker.setTitle("Selecciona la duración");
            mTimePicker.show();


        } else if (view.getId() == R.id.btnFinalizar) {



            if(!acepta_presupuesto){
                new AlertDialog.Builder(getContext()).setMessage("No ha aceptado el presupuesto, si ha solicitado materiales no entrarán en el almacén.\n\n¿Desea continuar?")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface hi, int dd)
                                    {   Calendar c = Calendar.getInstance();
                                        SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                                        String formattedDate = df.format(c.getTime());

                                        Log.e("finaliza entra", "entro aqui");
                                        if (parte.getFirma64() != null && !parte.getFirma64().equals("")) {
                                            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
                                            try {
                                                    if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                                                    articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                                                }
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }
                                            String operacionEfectuada = "";
                                            if (!etOperacionEfectuada.getText().toString().equals("")) {
                                                operacionEfectuada = etOperacionEfectuada.getText().toString();
                                            }
                                            String preeu_otros_nombre = "";
                                            if (!et_preeu_otros_nombre.getText().toString().equals("")) {
                                                preeu_otros_nombre = et_preeu_otros_nombre.getText().toString();
                                            }
                                            preeu_total_mano_de_obra_horas = preeu_mano_de_obra_precio * preeu_mano_de_obra_horas;

                                            if (!et_preeu_materiales.getText().toString().equals("")) {
                                                String str = et_preeu_materiales.getText().toString();
                                                str = str.replace(',', '.');
                                                //preeu_materiales = Double.valueOf(et_preeu_materiales.getText().toString());
                                                preeu_materiales = Double.valueOf(str);
                                            }
                                            if (!et_preeu_puesta_marcha.getText().toString().equals("")) {
                                                preeu_puesta_marcha = Double.valueOf(et_preeu_puesta_marcha.getText().toString());
                                            }

                                            if (!et_preeu_servicio_urgencia.getText().toString().equals("")) {
                                                preeu_servicio_urgencia = Double.valueOf(et_preeu_servicio_urgencia.getText().toString());
                                            }

                                            if (!et_preeu_km.getText().toString().equals("")) {
                                                preeu_km = Double.valueOf(et_preeu_km.getText().toString());
                                            }

                                            if (!et_preeu_km_precio.getText().toString().equals("")) {
                                                preeu_km_precio = Double.valueOf(et_preeu_km_precio.getText().toString());
                                            }
                                            preeu_km_precio_total = preeu_km * preeu_km_precio;

                                            if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
                                                preeu_analisis_combustion = Double.valueOf(et_preeu_analisis_combustion.getText().toString());
                                            }

                                            if (!et_preeu_adicional.getText().toString().equals("")) {
                                                preeu_adicional = Double.valueOf(et_preeu_adicional.getText().toString());
                                            }
                                            double SubTotal =
                                                    preeu_adicional
                                                            + preeu_analisis_combustion
                                                            + preeu_km_precio_total
                                                            + preeu_materiales
                                                            + preeu_total_mano_de_obra_horas
                                                            + preeu_disposicion_servicio
                                                            + preeu_puesta_marcha
                                                            + preeu_servicio_urgencia;
                                            etSubTotal.setText(String.valueOf(SubTotal));
                                            double preeu_iva_aplicado = SubTotal * 21 / 100;
                                            et_preeu_iva_aplicado.setText(String.format("%.2f", preeu_iva_aplicado));
                                            double total = SubTotal + preeu_iva_aplicado;
                                            et_total.setText(String.format("%.2f", total));


                                            double fact_materiales,
                                                    fact_subtotal,
                                                    fact_por_iva_aplicado,
                                                    fact_total_con_iva;

                                            fact_materiales = getPrecioTotalArticulosParteFacturar(articuloPartes);
                                            fact_subtotal = preeu_adicional + preeu_analisis_combustion +
                                                    preeu_km_precio_total + fact_materiales + preeu_total_mano_de_obra_horas + preeu_disposicion_servicio + preeu_puesta_marcha + preeu_servicio_urgencia;

                                            fact_por_iva_aplicado = fact_subtotal * 21 / 100;
                                            fact_total_con_iva = fact_por_iva_aplicado + fact_subtotal;


                                            try {
                                                DatosAdicionalesDAO.actualizarDatosAdicionalesParteFacturable(getContext(), datos.getId_rel(), fact_materiales, fact_por_iva_aplicado, fact_total_con_iva);
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }


                                            try {


                                                DatosAdicionalesDAO.actualizarDatosAdicionales(getContext(), datos.getId_rel(),

                                                        operacionEfectuada,
                                                        preeu_materiales,
                                                        preeu_disposicion_servicio,
                                                        preeu_mano_de_obra_precio,
                                                        preeu_mano_de_obra_horas,
                                                        preeu_total_mano_de_obra_horas,
                                                        preeu_puesta_marcha,
                                                        preeu_servicio_urgencia,
                                                        preeu_km,
                                                        preeu_km_precio,
                                                        preeu_km_precio_total,
                                                        preeu_analisis_combustion,
                                                        preeu_otros_nombre,
                                                        preeu_adicional,
                                                        SubTotal,
                                                        21,
                                                        total,
                                                        acepta_presupuesto,
                                                        forma_pago

                                                );
                                                datos.setMatem_hora_salida(formattedDate);
                                                DatosAdicionalesDAO.actualizarHoraSalida(getContext(), datos.getId_rel(), formattedDate);
                                                ParteDAO.actualizarParteDuracion(getContext(), parte.getId_parte(), String.valueOf(preeu_mano_de_obra_horas));
                                                new HiloCerrarParte(getContext(), parte.getId_parte()).execute();
                                            } catch (SQLException e) {
                                                e.printStackTrace();
                                            }


                                        } else {
                                            Dialogo.dialogoError("Es necesario la firma del cliente para finalizar.(Pestaña de Documentos)", getContext());
                                        }


                                    }
                                }
                        )
                        .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                                {
                                    public void onClick(DialogInterface hi, int dd)
                                    {

                                    }
                                }
                        ).show();


            }else {


                Log.e("finaliza entra", "entro aqui");
                if (parte.getFirma64() != null && !parte.getFirma64().equals("")) {



                    ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();

                    try {
                        if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                            articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    String operacionEfectuada = "";
                    if (!etOperacionEfectuada.getText().toString().equals("")) {
                        operacionEfectuada = etOperacionEfectuada.getText().toString();
                    }

                    String preeu_otros_nombre = "";
                    if (!et_preeu_otros_nombre.getText().toString().equals("")) {
                        preeu_otros_nombre = et_preeu_otros_nombre.getText().toString();
                    }


                    preeu_total_mano_de_obra_horas = preeu_mano_de_obra_precio * preeu_mano_de_obra_horas;

                    if (!et_preeu_materiales.getText().toString().equals("")) {
                        String str = et_preeu_materiales.getText().toString();
                        str = str.replace(',', '.');
                        //preeu_materiales = Double.valueOf(et_preeu_materiales.getText().toString());
                        preeu_materiales = Double.valueOf(str);
                    }

                    if (!et_preeu_puesta_marcha.getText().toString().equals("")) {
                        preeu_puesta_marcha = Double.valueOf(et_preeu_puesta_marcha.getText().toString());
                    }

                    if (!et_preeu_servicio_urgencia.getText().toString().equals("")) {
                        preeu_servicio_urgencia = Double.valueOf(et_preeu_servicio_urgencia.getText().toString());
                    }

                    if (!et_preeu_km.getText().toString().equals("")) {
                        preeu_km = Double.valueOf(et_preeu_km.getText().toString());
                    }

                    if (!et_preeu_km_precio.getText().toString().equals("")) {
                        preeu_km_precio = Double.valueOf(et_preeu_km_precio.getText().toString());
                    }
                    preeu_km_precio_total = preeu_km * preeu_km_precio;

                    if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
                        preeu_analisis_combustion = Double.valueOf(et_preeu_analisis_combustion.getText().toString());
                    }

                    if (!et_preeu_adicional.getText().toString().equals("")) {
                        preeu_adicional = Double.valueOf(et_preeu_adicional.getText().toString());
                    }


                    double SubTotal = preeu_adicional + preeu_analisis_combustion +
                            preeu_km_precio_total + preeu_materiales + preeu_total_mano_de_obra_horas + preeu_disposicion_servicio + preeu_puesta_marcha + preeu_servicio_urgencia;
                    etSubTotal.setText(String.valueOf(SubTotal));
                    double preeu_iva_aplicado = SubTotal * 21 / 100;
                    et_preeu_iva_aplicado.setText(String.format("%.2f", preeu_iva_aplicado));
                    double total = SubTotal + preeu_iva_aplicado;
                    et_total.setText(String.format("%.2f", total));


                    double fact_materiales,
                            fact_subtotal,
                            fact_por_iva_aplicado,
                            fact_total_con_iva;

                    fact_materiales = getPrecioTotalArticulosParteFacturar(articuloPartes);
                    fact_subtotal = preeu_adicional + preeu_analisis_combustion +
                            preeu_km_precio_total + fact_materiales + preeu_total_mano_de_obra_horas + preeu_disposicion_servicio + preeu_puesta_marcha + preeu_servicio_urgencia;

                    fact_por_iva_aplicado = fact_subtotal * 21 / 100;
                    fact_total_con_iva = fact_por_iva_aplicado + fact_subtotal;


                    try {
                        DatosAdicionalesDAO.actualizarDatosAdicionalesParteFacturable(getContext(), datos.getId_rel(), fact_materiales, fact_por_iva_aplicado, fact_total_con_iva);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                    try {


                        DatosAdicionalesDAO.actualizarDatosAdicionales(getContext(), datos.getId_rel(),

                                operacionEfectuada,
                                preeu_materiales,
                                preeu_disposicion_servicio,
                                preeu_mano_de_obra_precio,
                                preeu_mano_de_obra_horas,
                                preeu_total_mano_de_obra_horas,
                                preeu_puesta_marcha,
                                preeu_servicio_urgencia,
                                preeu_km,
                                preeu_km_precio,
                                preeu_km_precio_total,
                                preeu_analisis_combustion,
                                preeu_otros_nombre,
                                preeu_adicional,
                                SubTotal,
                                21,
                                total,
                                acepta_presupuesto,
                                forma_pago

                        );
                        datos.setMatem_hora_salida(formattedDate);
                        DatosAdicionalesDAO.actualizarHoraSalida(getContext(), datos.getId_rel(), formattedDate);
                        ParteDAO.actualizarParteDuracion(getContext(), parte.getId_parte(), String.valueOf(preeu_mano_de_obra_horas));
                        new HiloCerrarParte(getContext(), parte.getId_parte()).execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }


                } else {
                    Dialogo.dialogoError("Es necesario la firma del cliente para finalizar.(Pestaña de Documentos)", getContext());
                }

                 }
             }
        }
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        etOperacionEfectuada.clearFocus();



            if ( parent.getId() == sp_preeu_disposicion_servicio.getId()) {
                if (sp_preeu_disposicion_servicio.getSelectedItemPosition() != 0) {
                    try {
                        preeu_disposicion_servicio = DisposicionesDAO.buscarPrecioDisposicionPorNombre(getContext(), sp_preeu_disposicion_servicio.getSelectedItem().toString());
                        DatosAdicionalesDAO.actualizarDisposicionDeServicio(getContext(), datos.getId_rel(), preeu_disposicion_servicio);
                        et_preeu_total_disposicion_servicio.setText(String.valueOf(preeu_disposicion_servicio));

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            } else if ( parent.getId() == sp_preeu_mano_de_obra_precio.getId()) {
                if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition() != 0) {
                    try {
                        preeu_mano_de_obra_precio = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                        DatosAdicionalesDAO.actializarManoDeObraPrecio(getContext(), datos.getId_rel(), preeu_mano_de_obra_precio);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                }
            } else if ( parent.getId() == spFormaPago.getId()) {
                if (spFormaPago.getSelectedItemPosition() != 0) {

                    try {
                        forma_pago = FormasPagoDAO.buscarIdFormaPagoPorNombre(getContext(), spFormaPago.getSelectedItem().toString());
                        DatosAdicionalesDAO.actualizarFormaPago(getContext(), datos.getId_rel(), forma_pago);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

            }

            try {
                ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
                if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                    articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                    if (!articuloPartes.isEmpty()) {
                        precioTotalArticulos = getPrecioTotalArticulosPartePpto(articuloPartes);
                    }
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            recalcular();

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if (isChecked) acepta_presupuesto = true;
        else acepta_presupuesto = false;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 99) {
            if (resultCode == Activity.RESULT_OK) {
                try {
                    String path = data.getStringExtra("path");
                Bitmap bitmap;
                bitmap = loadFirmaClienteFromStorage(parte.getId_parte(), getContext());
                    ByteArrayOutputStream baos = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
                    byte[] imageBytes = baos.toByteArray();
                    String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);
                    ParteDAO.actualizarFirma64(getContext(),parte.getId_parte(),encodedImage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                btnFirmar.setEnabled(false);

                }
        }else if(requestCode == 654){
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
            try {
                if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                    articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                    et_preeu_materiales.setText(String.format("%.2f", (getPrecioTotalArticulosPartePpto(articuloPartes))));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }


        }

    }


    public static Bitmap loadFirmaClienteFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(Constantes.PATH, "firmaCliente" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (!hasFocus) {
            try {
                DatosAdicionalesDAO.actualizarOperacionEfectuada(getContext(),datos.getId_rel(),etOperacionEfectuada.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
}