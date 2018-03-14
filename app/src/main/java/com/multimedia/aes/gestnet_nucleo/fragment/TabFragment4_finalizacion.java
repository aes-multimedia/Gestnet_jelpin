package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.TimePickerDialog;
import android.inputmethodservice.KeyboardView;
import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;


public class TabFragment4_finalizacion extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener,CompoundButton.OnCheckedChangeListener{
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
    private static EditText etOperacionEfectuada,et_preeu_materiales,et_preeu_mano_de_obra_horas,
    et_preeu_puesta_marcha,et_preeu_servicio_urgencia,et_preeu_km,et_preeu_km_precio,
    et_preeu_analisis_combustion,et_preeu_otros_nombre,
    et_preeu_adicional,etSubTotal,et_preeu_iva_aplicado,et_total,et_total_ppto,et_preeu_total_mano_de_obra_horas;

    private boolean acepta_presupuesto=false;

    private Button btnFinalizar,btn_preeu_mano_de_obra;
    private Spinner sp_preeu_disposicion_servicio,sp_preeu_mano_de_obra_precio,spFormaPago;
    private CheckBox cb_acepta_presupuesto;
    private double precioTotalArticulos,preeu_adicional,preeu_analisis_combustion,
    preeu_km_precio_total,preeu_materiales,preeu_total_mano_de_obra_horas,preeu_disposicion_servicio,preeu_puesta_marcha,preeu_servicio_urgencia,preeu_km,preeu_km_precio;


    //METODO
    private void inicializar() {



        //EDITTEXT
        et_preeu_materiales= (EditText) vista.findViewById(R.id.et_preeu_materiales);
        et_preeu_materiales.setEnabled(false);
        et_preeu_materiales.setText(String.valueOf(0));
        et_preeu_total_mano_de_obra_horas= (EditText) vista.findViewById(R.id.et_preeu_total_mano_de_obra_horas);
        et_preeu_puesta_marcha= (EditText) vista.findViewById(R.id.et_preeu_puesta_marcha);
        et_preeu_servicio_urgencia= (EditText) vista.findViewById(R.id.et_preeu_servicio_urgencia);
        et_preeu_km= (EditText) vista.findViewById(R.id.et_preeu_km);
        et_preeu_km_precio= (EditText) vista.findViewById(R.id.et_preeu_km_precio);
        et_preeu_analisis_combustion= (EditText) vista.findViewById(R.id.et_preeu_analisis_combustion);
        et_preeu_otros_nombre= (EditText) vista.findViewById(R.id.et_preeu_otros_nombre);
        et_preeu_adicional= (EditText) vista.findViewById(R.id.et_preeu_adicional);
        etSubTotal= (EditText) vista.findViewById(R.id.etSubTotal);
        et_preeu_iva_aplicado= (EditText) vista.findViewById(R.id.et_preeu_iva_aplicado);
        et_total= (EditText) vista.findViewById(R.id.et_total);
        cb_acepta_presupuesto = (CheckBox) vista.findViewById(R.id.cb_acepta_presupuesto);
        cb_acepta_presupuesto.setOnCheckedChangeListener(this);
        etOperacionEfectuada=(EditText) vista.findViewById(R.id.etOperacionEfectuada);

        //BUTTON
        btn_preeu_mano_de_obra = (Button) vista.findViewById(R.id.btn_preeu_mano_de_obra);
        btnFinalizar = (Button) vista.findViewById(R.id.btnFinalizar);


        //ONCLICK
        btn_preeu_mano_de_obra.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        //SPINNER
        spFormaPago = (Spinner) vista.findViewById(R.id.spFormaPago);
        spFormaPago.setOnItemSelectedListener(this);
        sp_preeu_disposicion_servicio = (Spinner) vista.findViewById(R.id.sp_preeu_disposicion_servicio);
        sp_preeu_mano_de_obra_precio = (Spinner) vista.findViewById(R.id.sp_preeu_mano_de_obra_precio);
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



    }

    public static void recalcularPrecioMateriales(double precio,boolean isGarantia){
        //MATERIALES

                    if(!isGarantia){
                        double antiguo = Double.valueOf(et_preeu_materiales.getText().toString());
                        precio+=antiguo;
                        et_preeu_materiales.setText(String.valueOf(precio));

                    }

                }







    private double getPrecioTotalArticulosParte(ArrayList<ArticuloParte> listaArticulos) {

        double precio = 0;
        try {

            for (ArticuloParte articulo : listaArticulos) {
                precio = ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo()).getTarifa()*articulo.getUsados();
            }
        } catch (SQLException e) {


            Log.w("getPrecioTotal", "error al sumar precio articulos parte");
        }

        return precio;
    }

    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment4_finalizacion_nuevo, container, false);
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


        return vista;

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_preeu_mano_de_obra) {
            int hour = 0;
            int minute = 0;
            TimePickerDialog mTimePicker;
            mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                    btn_preeu_mano_de_obra.setText(selectedHour + " horas " + selectedMinute + " minutos");
                    preeu_mano_de_obra_horas = (selectedHour  + selectedMinute/60);
                    if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition()!=0){

                        double mH=0;
                        try {
                            mH= ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        et_preeu_total_mano_de_obra_horas.setText(String.valueOf(mH*preeu_mano_de_obra_horas));

                    }

                }
            }, hour, minute, true);
            mTimePicker.setTitle("Selecciona la duración");
            mTimePicker.show();



        } else if (view.getId() == R.id.btnFinalizar) {
            if (parte.getFirma64()!=null&&!parte.getFirma64().equals("")){
                if (sp_preeu_disposicion_servicio.getSelectedItemPosition()!=0){
                    if (spFormaPago.getSelectedItemPosition()!=0){
                        if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition()!=0){
                            double preeu_disposicion_servicio = 0;
                            int formaPago = 0;
                            int preeu_mano_de_obra_precio = 0;
                            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
                            try {
                                preeu_disposicion_servicio = DisposicionesDAO.buscarPrecioDisposicionPorNombre(getContext(), sp_preeu_disposicion_servicio.getSelectedItem().toString());
                                formaPago = FormasPagoDAO.buscarIdFormaPagoPorNombre(getContext(), spFormaPago.getSelectedItem().toString());
                                preeu_mano_de_obra_precio = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                                if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                                    articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                                }
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }

                            String operacionEfectuada = "";
                            if (!etOperacionEfectuada.getText().toString().equals("")){
                                operacionEfectuada = etOperacionEfectuada.getText().toString();
                            }

                            preeu_total_mano_de_obra_horas=preeu_mano_de_obra_precio*preeu_mano_de_obra_horas;

                            preeu_materiales=0;

                            if (!et_preeu_materiales.getText().toString().equals("")) {
                                preeu_materiales = Double.valueOf(et_preeu_materiales.getText().toString());
                            }

                            preeu_puesta_marcha=0;
                            if (!et_preeu_puesta_marcha.getText().toString().equals("")){
                                preeu_puesta_marcha = Double.valueOf(et_preeu_puesta_marcha.getText().toString());
                            }

                            preeu_servicio_urgencia = 0;
                            if (!et_preeu_servicio_urgencia.getText().toString().equals("")){
                                preeu_servicio_urgencia = Double.valueOf(et_preeu_servicio_urgencia.getText().toString());
                            }
                             preeu_km = 0;
                            if (!et_preeu_km.getText().toString().equals("")) {
                                preeu_km = Double.valueOf(et_preeu_km.getText().toString());
                            }
                             preeu_km_precio = 0;
                            if (!et_preeu_km_precio.getText().toString().equals("")) {
                                preeu_km_precio = Double.valueOf(et_preeu_km_precio.getText().toString());
                            }
                            preeu_km_precio_total = preeu_km*preeu_km_precio;

                            preeu_analisis_combustion=0;
                            if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
                                preeu_analisis_combustion = Double.valueOf(et_preeu_analisis_combustion.getText().toString());
                            }

                            String preeu_otros_nombre="";
                            if (!et_preeu_otros_nombre.getText().toString().equals("")){
                                preeu_otros_nombre = et_preeu_otros_nombre.getText().toString();
                            }

                            preeu_adicional = 0;
                            if (!et_preeu_adicional.getText().toString().equals("")) {
                                preeu_adicional = Double.valueOf(et_preeu_adicional.getText().toString());
                            }

                           precioTotalArticulos = 0;
                            if (!articuloPartes.isEmpty()) {
                                precioTotalArticulos = getPrecioTotalArticulosParte(articuloPartes);
                            }

                            double SubTotal=precioTotalArticulos+preeu_adicional+preeu_analisis_combustion+
                                    preeu_km_precio_total+preeu_materiales+preeu_total_mano_de_obra_horas+preeu_disposicion_servicio;
                            etSubTotal.setText(String.valueOf(SubTotal));
                            double preeu_iva_aplicado=SubTotal*21/100;
                            et_preeu_iva_aplicado.setText(String.valueOf(preeu_iva_aplicado));
                            double total=SubTotal+preeu_iva_aplicado;
                            et_total.setText(String.valueOf(total));




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
                                        formaPago





                                );
                                ParteDAO.actualizarParteDuracion(getContext(), parte.getId_parte(), String.valueOf(preeu_mano_de_obra_horas));
                                new HiloCerrarParte(getContext(), parte.getId_parte()).execute();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }else{
                            Dialogo.dialogoError("Es necesario seleccionar una mano de obra de servicio",getContext());
                        }
                    }else{
                        Dialogo.dialogoError("Es necesario seleccionar una forma de pago de servicio",getContext());
                    }
                }else{
                    Dialogo.dialogoError("Es necesario seleccionar una disposicion de servicio",getContext());
                }
            }else{
                Dialogo.dialogoError("Es necesario la firma del cliente para finalizar.(Pestaña de Documentos)",getContext());
            }

        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {






            if (sp_preeu_disposicion_servicio.getSelectedItemPosition()!=0){
                if (spFormaPago.getSelectedItemPosition()!=0){
                    if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition()!=0){
                        preeu_disposicion_servicio = 0;
                        int preeu_mano_de_obra_precio = 0;
                        ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
                        try {
                            preeu_disposicion_servicio = DisposicionesDAO.buscarPrecioDisposicionPorNombre(getContext(), sp_preeu_disposicion_servicio.getSelectedItem().toString());
                            preeu_mano_de_obra_precio = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                            if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                                articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                            }
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }



                        preeu_total_mano_de_obra_horas=preeu_mano_de_obra_precio*preeu_mano_de_obra_horas;

                        if (!et_preeu_materiales.getText().toString().equals("")) {
                            preeu_materiales = Double.valueOf(et_preeu_materiales.getText().toString());
                        }

                        if (!et_preeu_puesta_marcha.getText().toString().equals("")){
                            preeu_puesta_marcha = Double.valueOf(et_preeu_puesta_marcha.getText().toString());
                        }

                        if (!et_preeu_servicio_urgencia.getText().toString().equals("")){
                            preeu_servicio_urgencia = Double.valueOf(et_preeu_servicio_urgencia.getText().toString());
                        }

                        if (!et_preeu_km.getText().toString().equals("")) {
                            preeu_km = Double.valueOf(et_preeu_km.getText().toString());
                        }

                        if (!et_preeu_km_precio.getText().toString().equals("")) {
                            preeu_km_precio = Double.valueOf(et_preeu_km_precio.getText().toString());
                        }
                        preeu_km_precio_total = preeu_km*preeu_km_precio;

                        if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
                            preeu_analisis_combustion = Double.valueOf(et_preeu_analisis_combustion.getText().toString());
                        }

                        if (!et_preeu_adicional.getText().toString().equals("")) {
                            preeu_adicional = Double.valueOf(et_preeu_adicional.getText().toString());
                        }

                        if (!articuloPartes.isEmpty()) {
                            precioTotalArticulos = getPrecioTotalArticulosParte(articuloPartes);
                        }}}}






        double SubTotal=precioTotalArticulos+preeu_adicional+preeu_analisis_combustion+
        preeu_km_precio_total+preeu_materiales+preeu_total_mano_de_obra_horas+preeu_disposicion_servicio+preeu_puesta_marcha+preeu_servicio_urgencia;
        etSubTotal.setText(String.valueOf(SubTotal));
        double preeu_iva_aplicado=SubTotal*21/100;
        et_preeu_iva_aplicado.setText(String.valueOf(preeu_iva_aplicado));
        double total=SubTotal+preeu_iva_aplicado;
        et_total.setText(String.valueOf(total));



    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked)acepta_presupuesto=true;
            else acepta_presupuesto=false;
    }


}