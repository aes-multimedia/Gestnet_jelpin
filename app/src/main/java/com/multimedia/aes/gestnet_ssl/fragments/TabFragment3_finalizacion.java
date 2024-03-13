package com.multimedia.aes.gestnet_ssl.fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Environment;
import android.speech.RecognizerIntent;
import android.text.Editable;
import android.text.Html;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Base64;
import android.util.Log;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.Utils.Utils;
import com.multimedia.aes.gestnet_ssl.Utils.easyTakePhoto;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.ConfiguracionDAO;
import com.multimedia.aes.gestnet_ssl.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_ssl.dao.DisposicionesDAO;
import com.multimedia.aes.gestnet_ssl.dao.FormasPagoDAO;
import com.multimedia.aes.gestnet_ssl.dao.ImagenDAO;
import com.multimedia.aes.gestnet_ssl.dao.ManoObraDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_ssl.dao.TiposOsDAO;
import com.multimedia.aes.gestnet_ssl.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Articulo;
import com.multimedia.aes.gestnet_ssl.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Configuracion;
import com.multimedia.aes.gestnet_ssl.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_ssl.entidades.Disposiciones;
import com.multimedia.aes.gestnet_ssl.entidades.FormasPago;
import com.multimedia.aes.gestnet_ssl.entidades.Imagen;
import com.multimedia.aes.gestnet_ssl.entidades.ManoObra;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.entidades.ProtocoloAccion;
import com.multimedia.aes.gestnet_ssl.entidades.TiposOs;
import com.multimedia.aes.gestnet_ssl.entidades.Usuario;
import com.multimedia.aes.gestnet_ssl.hilos.HiloCerrarParte;
import com.multimedia.aes.gestnet_ssl.nucleo.FirmaCliente;
import com.multimedia.aes.gestnet_ssl.nucleo.FotosProtocoloAccion;
import com.multimedia.aes.gestnet_ssl.nucleo.GaleriaV2;
import com.multimedia.aes.gestnet_ssl.nucleo.GestionMateriales;
import com.multimedia.aes.gestnet_ssl.nucleo.LectorBarrasActivity;

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
import java.util.regex.Pattern;

import static java.lang.Math.round;

import androidx.fragment.app.Fragment;


public class TabFragment3_finalizacion extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener {
    private static final int REQUEST_CODE_SPEECH_INPUT = 1;
    private static final int REQUEST_CODE_SCAN = 69;
    private View vista;
    private double preeu_mano_de_obra_horas;
    private String tiempoDuracion;
    private ArrayList<FormasPago> formasPagos = new ArrayList<>();
    private ArrayList<ManoObra> manosObra = new ArrayList<>();
    private ArrayList<TiposOs> tiposOs = new ArrayList<>();
    private ArrayList<Disposiciones> disposicionesServicio = new ArrayList<>();
    private String[] arrayFormasPago, arrayManosObra, arrayDisposiciones, arrayTiposOs;
    private Parte parte = null;
    private Usuario usuario = null;
    private DatosAdicionales datos = null;

    private EditText etOperacionEfectuada, et_preeu_materiales, et_preeu_mano_de_obra_horas,
            et_preeu_puesta_marcha, et_preeu_servicio_urgencia, et_preeu_km, et_preeu_km_precio,
            et_preeu_analisis_combustion, et_preeu_otros_nombre,
            et_preeu_adicional, etSubTotal, et_preeu_iva_aplicado, et_total, et_total_ppto, et_preeu_total_mano_de_obra_horas, et_preeu_total_disposicion_servicio, et_enviar_por_correo;

    private boolean acepta_presupuesto = false, enviar_correo = false;
    private static DecimalFormat df2 = new DecimalFormat(",##");
    private Button btnDatosBarras, btnFinalizar, btn_preeu_mano_de_obra, btnFirmar, btn_calcular_tiempo, btnFirm, btnVerFotos, btnspeech;
    private Spinner sp_preeu_disposicion_servicio, sp_preeu_mano_de_obra_precio, spFormaPago,spTiposOS;
    private CheckBox cb_acepta_presupuesto, cb_enviar_por_correo;
    private View llTiposOs;
    private double precioTotalArticulos, preeu_adicional, preeu_analisis_combustion,
            preeu_km_precio_total, preeu_materiales, preeu_total_mano_de_obra_horas, preeu_disposicion_servicio, preeu_puesta_marcha, preeu_servicio_urgencia, preeu_km, preeu_km_precio, preeu_mano_de_obra_precio;

    private String textoBoton;
    private int forma_pago,tipoOsId;
    private Cliente c;
    private TextView etObsCliente;
    private Button BtnVerMateriales;
    private Utils u = new Utils();
    private LinearLayout llPadre, llFotos, LLNserie;
    private Configuracion configuracion;
    private Spinner spProtocolos;
    ProtocoloAccion[] arrayProtocolos;
    private final ArrayList<ProtocoloAccion> protocoloAccionArrayList = new ArrayList<>();
    private int posicion = 0;

    //METODO
    @SuppressLint("ClickableViewAccessibility")
    private void inicializar() {
        try{
            c = ClienteDAO.buscarCliente(getContext());
            configuracion = ConfiguracionDAO.buscarTodasLasConfiguraciones(getContext()).get(0);
        }catch( SQLException sqlE){
            sqlE.printStackTrace();
        }

        //EDITTEXT
        etObsCliente = vista.findViewById(R.id.etObsCliente);
        et_preeu_materiales = vista.findViewById(R.id.et_preeu_materiales);
        if(c.getId_cliente()==28 ){
            etObsCliente.setVisibility(View.VISIBLE);
            //etObsCliente.setEnabled(false);
        }else{
            etObsCliente.setVisibility(View.GONE);
        }

        et_preeu_materiales.setEnabled(false);
        et_preeu_materiales.setText(String.valueOf(0));
        et_preeu_total_mano_de_obra_horas = vista.findViewById(R.id.et_preeu_total_mano_de_obra_horas);
        et_preeu_total_mano_de_obra_horas.setEnabled(false);
        et_preeu_puesta_marcha = vista.findViewById(R.id.et_preeu_puesta_marcha);
        et_preeu_puesta_marcha.addTextChangedListener(watcher);
        et_preeu_servicio_urgencia = vista.findViewById(R.id.et_preeu_servicio_urgencia);
        et_preeu_servicio_urgencia.addTextChangedListener(watcher);
        et_preeu_km = vista.findViewById(R.id.et_preeu_km);
        et_preeu_km.addTextChangedListener(watcher);
        et_preeu_km_precio = vista.findViewById(R.id.et_preeu_km_precio);
        et_preeu_km_precio.addTextChangedListener(watcher);
        et_preeu_analisis_combustion = vista.findViewById(R.id.et_preeu_analisis_combustion);
        et_preeu_analisis_combustion.addTextChangedListener(watcher);
        et_preeu_otros_nombre = vista.findViewById(R.id.et_preeu_otros_nombre);
        et_preeu_otros_nombre.addTextChangedListener(watcher);
        et_preeu_total_disposicion_servicio = vista.findViewById(R.id.et_preeu_total_disposicion_servicio);
        et_preeu_total_disposicion_servicio.setEnabled(false);
        et_preeu_adicional = vista.findViewById(R.id.et_preeu_adicional);
        et_preeu_adicional.addTextChangedListener(watcher);
        etSubTotal = vista.findViewById(R.id.etSubTotal);
        et_preeu_iva_aplicado = vista.findViewById(R.id.et_preeu_iva_aplicado);
        et_total = vista.findViewById(R.id.et_total);
        cb_acepta_presupuesto = vista.findViewById(R.id.cb_acepta_presupuesto);
        cb_acepta_presupuesto.setOnCheckedChangeListener(this);
        cb_enviar_por_correo = vista.findViewById(R.id.cb_enviar_por_correo);
        cb_enviar_por_correo.setOnCheckedChangeListener(this);
        etOperacionEfectuada = vista.findViewById(R.id.etOperacionEfectuada);
        etOperacionEfectuada.setOnFocusChangeListener(this);
        et_enviar_por_correo = vista.findViewById(R.id.et_enviar_por_correo);
        btnVerFotos = vista.findViewById(R.id.button5);
        btnspeech = vista.findViewById(R.id.button2);

        //BUTTON
        btn_preeu_mano_de_obra = vista.findViewById(R.id.btn_preeu_mano_de_obra);
        btnFinalizar = vista.findViewById(R.id.btnFinalizar);
        btnFirmar = vista.findViewById(R.id.btnFirmar);
        btn_calcular_tiempo = vista.findViewById(R.id.btn_calcular_tiempo);
        btnFirm = vista.findViewById(R.id.btnDoc);
        btnDatosBarras = vista.findViewById(R.id.btnDatosBarras);

        try {
            if(ConfiguracionDAO.buscarConfiguracion(getContext()).isbFotoInforme()) {
                boolean mostrar = true;
                var listaimg = ImagenDAO.buscarImagenPorFk_parte(getContext(), parte.getId_parte());
                if (listaimg != null){
                    for (Imagen im:
                            listaimg) {
                        if(im.isbInforme()){
                            mostrar = false;
                        }
                    }
                }


                if (mostrar){
                    btnFirm.setVisibility(View.VISIBLE);
                    btnFirm.setOnClickListener(v -> {
                        var a = new easyTakePhoto(parte, getContext(), 2);
                        a.takePhoto(getActivity());
                        btnFirm.setEnabled(false);
                    });
                } else {
                    btnFirm.setEnabled(false);
                }

            } else {
                btnFirm.setVisibility(View.GONE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //ONCLICK
        btn_preeu_mano_de_obra.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        btnFirmar.setOnClickListener(this);
        btn_calcular_tiempo.setOnClickListener(this);
        //SPINNER
        llTiposOs = vista.findViewById(R.id.ll_tiposOS);
        spTiposOS = vista.findViewById(R.id.sp_tipos_os);
        spTiposOS.setOnItemSelectedListener(this);
        spFormaPago = vista.findViewById(R.id.spFormaPago);
        spFormaPago.setOnItemSelectedListener(this);
        sp_preeu_disposicion_servicio = vista.findViewById(R.id.sp_preeu_disposicion_servicio);
        sp_preeu_disposicion_servicio.setOnItemSelectedListener(this);
        sp_preeu_mano_de_obra_precio = vista.findViewById(R.id.sp_preeu_mano_de_obra_precio);
        sp_preeu_mano_de_obra_precio.setOnItemSelectedListener(this);

        BtnVerMateriales = vista.findViewById(R.id.btnVerMateriales);
        BtnVerMateriales.setOnClickListener(this);
        try{
            Cliente cliente = ClienteDAO.buscarCliente(getContext());
            int cliId = cliente.getId_cliente();
            if(cliId !=21){
                llTiposOs.setVisibility(View.GONE);
            }
        }catch (SQLException E){

        }

        //ON TEXT CHANGE

        et_enviar_por_correo.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {

                if (!validarEmail(et_enviar_por_correo.getText().toString())) {
                    et_enviar_por_correo.setError("Alguno de los emails introducidos no es válido");
                }
                try {
                    ParteDAO.actualizarCorreoEnvioDeFactura(getContext(), parte.getId_parte(), et_enviar_por_correo.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        spProtocolos = vista.findViewById(R.id.spProtocolos);
        spProtocolos.setOnItemSelectedListener(this);
        llPadre = vista.findViewById(R.id.llPadre);
        llPadre.setOnTouchListener((v, event) -> {
            try {
                guardarProtocolo();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return false;
        });


        try {
            if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(), parte.getId_parte()) != null) {
                try {
                    protocoloAccionArrayList.addAll(ProtocoloAccionDAO.buscarPrueba(getContext(), parte.getId_parte()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int indice = 0;
                ArrayList<ProtocoloAccion> ordenadosNombre;
                ordenadosNombre = ordenarArrayAccionProtocolosV2(protocoloAccionArrayList);
                ordenadosNombre.add(0, new ProtocoloAccion(-1, "", -1, parte.getId_parte(), -1, "--Seleccione un protocolo--", -1, false, "", -1));
                arrayProtocolos = new ProtocoloAccion[ordenadosNombre.size()];
                for (int i = 0; i < arrayProtocolos.length; i++) {
                    arrayProtocolos[i] = ordenadosNombre.get(i);
                }
                spProtocolos.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayProtocolos));
            } else {
                /*ArrayList<ProtocoloAccion> ordenadosNombre = new ArrayList<>();
                ordenadosNombre.add(0, new ProtocoloAccion(-1, "", -1, parte.getId_parte(), -1, "Sin Protocolos", -1, false , "", -1));
                arrayProtocolos = new ProtocoloAccion[ordenadosNombre.size()];
                for (int i = 0; i < arrayProtocolos.length; i++) {
                    arrayProtocolos[i] = ordenadosNombre.get(i);
                }
                spProtocolos.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayProtocolos));*/
                var llProtocolo = vista.findViewById(R.id.llProtocolo);
                llProtocolo.setVisibility(View.GONE);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        try {
            spProtocolos.setSelection(1);
        }catch (Exception ignored) {

        }

        btnVerFotos.setOnClickListener(v -> {
            Intent i = new Intent(getContext(), GaleriaV2.class);
            getContext().startActivity(i);
        });

        btnspeech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                        RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
                intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE,
                        Locale.getDefault());
                intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "¡Habla!");

                try {
                    startActivityForResult(intent, REQUEST_CODE_SPEECH_INPUT);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        try{
            darValores();
        }catch (SQLException e){
            e.printStackTrace();
        }

        btnDatosBarras.setOnClickListener(v -> {

            AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
            builder.setTitle("Selecciona un método de entrada");
            builder.setPositiveButton("Introducir manualmente", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    LinearLayout container = new LinearLayout(getContext());
                    EditText edittext = new EditText(getContext());
                    AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                    container.setOrientation(LinearLayout.VERTICAL);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    lp.setMargins(30, 20, 30, 10);
                    edittext.setPadding(10, 5, 10, 5);
                    edittext.setGravity(android.view.Gravity.TOP | android.view.Gravity.LEFT);
                    edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);

                    edittext.setSingleLine(false);
                    edittext.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
                    edittext.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_CLASS_TEXT);
                    edittext.setLines(5);
                    edittext.setMaxLines(10);
                    container.addView(edittext, lp);

                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                    builder.setView(container);
                    builder.setTitle("Introducir número de serie");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Obtener el número de serie introducido por el usuario
                            String serialNumber = edittext.getText().toString().trim();
                            // Aquí puedes realizar la lógica con el número de serie
                            if (!serialNumber.isEmpty()) {
                                Toast.makeText(getContext(), "Número de serie introducido: " + serialNumber, Toast.LENGTH_SHORT).show();
                                ArticuloParte maq = null;
                                try {
                                    maq = ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()).get(0);
                                    ArticuloParteDAO.actualizarN_Serie(getContext(), maq.getId(), serialNumber);
                                    btnDatosBarras.setEnabled(false);
                                } catch (SQLException e) {
                                    throw new RuntimeException(e);
                                }

                            } else {
                                Toast.makeText(getContext(), "Debes introducir un número de serie válido", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });

                    // Mostrar el diálogo
                    builder.show();
                }
            });
            builder.setNegativeButton("Escanear", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Intent i = new Intent(getContext(), LectorBarrasActivity.class);
                    startActivityForResult(i, REQUEST_CODE_SCAN);
                }
            });
            builder.setNeutralButton("Cancelar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            builder.show();


        });
        try {
            var maq = ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()).get(0);
            if (maq != null && maq.getN_serie() != null){
                btnDatosBarras.setEnabled(false);
            } else if (maq == null) {
                btnDatosBarras.setVisibility(View.GONE);
            }
        } catch (SQLException e) {
            btnDatosBarras.setVisibility(View.GONE);
        }

    }

    @Override
    public void onResume() {
        super.onResume();
        JSONObject jsonObject;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
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

    private void darValores() throws SQLException {

            recalcular();
        //SPINNER TIPOS OS
        if (TiposOsDAO.buscarTipoOsPorFkTipoParte(getContext(),parte.getFk_tipo()) != null) {
            tiposOs.addAll(TiposOsDAO.buscarTipoOsPorFkTipoParte(getContext(),parte.getFk_tipo()));
            arrayTiposOs  = new String[tiposOs.size() + 1];
            arrayTiposOs[0] = "--Seleciones un valor--";
            for (int i = 1; i < tiposOs.size() + 1; i++) {
                arrayTiposOs[i] = tiposOs.get(i - 1).getNombre_tipoOs();
            }

            int fk_tipo_os_parte = parte.getFk_tipo_os0();

            ArrayAdapter tiposOsAdp =new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayTiposOs) ;
            spTiposOS.setAdapter(tiposOsAdp);
            if(fk_tipo_os_parte != -1  && fk_tipo_os_parte != 0 ) {
                try{
                    String tipoOsParte = TiposOsDAO.buscarTipoOsPorId_tipoOS(getContext(),fk_tipo_os_parte).getNombre_tipoOs();
                    spTiposOS.setSelection(tiposOsAdp.getPosition(tipoOsParte));
                }catch (SQLException E){
                    E.printStackTrace();
                }
            }
        }
        //SPINNER FORMAS PAGO
        if (FormasPagoDAO.buscarTodasLasFormasPago(getContext()) != null) {
            formasPagos.addAll(FormasPagoDAO.buscarTodasLasFormasPago(getContext()));
            arrayFormasPago = new String[formasPagos.size() + 1];
            arrayFormasPago[0] = "--Seleciones un valor--";
            for (int i = 1; i < formasPagos.size() + 1; i++) {
                arrayFormasPago[i] = formasPagos.get(i - 1).getForma_pago();
            }

            spFormaPago.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayFormasPago));

            if (datos.getFk_forma_pago() > 0) {
                try {
                    int idFormaPago = datos.getFk_forma_pago();
                    String nombreFormaPago = FormasPagoDAO.buscarFormasPagoPorId(getContext(), idFormaPago).getForma_pago();
                   /* arrayFormasPago[0] = nombreFormaPago;
                    arrayFormasPago[1] = nombreFormaPago;*/
                    for (int i = 0; i < arrayFormasPago.length; ++i) {
                        String fpagoSpinner = arrayFormasPago[i];
                      if(fpagoSpinner.equals(nombreFormaPago)){
                          spFormaPago.setSelection(i);
                      }
                    }

                    /*spFormaPago.setEnabled(false);
                    spFormaPago.setClickable(false);*/
                } catch (Exception e) {
                    e.printStackTrace();
                    //si algo falla se utiliza la rutina habitual.
                    /*arrayFormasPago[0] = "--Seleciones un valor--";
                    spFormaPago.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayFormasPago));*/
                }
            } else{
                spFormaPago.setSelection(1);
            }
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


        if (parte.getFirma64() != null && !parte.getFirma64().equals("")) {

            btnFirmar.setText("FIRMADO");
            btnFirmar.setEnabled(false);
        }

        if (parte.getTextoDuracion() != null && !parte.getTextoDuracion().equals("")) {
            btn_preeu_mano_de_obra.setText(parte.getTextoDuracion());

        }

        if (datos.getObservaciones() != null && !datos.getObservaciones().equals(""))
            etObsCliente.setText(datos.getObservaciones());

        if (datos.getOperacion_efectuada() != null && !datos.getOperacion_efectuada().equals(""))
            etOperacionEfectuada.setText(datos.getOperacion_efectuada());


        et_preeu_total_disposicion_servicio.setText(String.valueOf(datos.getPreeu_disposicion_servicio()));
        et_preeu_total_mano_de_obra_horas
                .setText(String.valueOf(datos.getPreeu_mano_de_obra()
                        *
                        datos.getPreeu_mano_de_obra_precio()));

        et_enviar_por_correo.setText(parte.getEmail_cliente());


    }

    private double getPrecioTotalArticulosPartePpto(ArrayList<ArticuloParte> listaArticulos) {
        double precio = 0;
        try {
            //Si el articulo no está en garantia entonces sesuma el precio de ese articulo

            for (ArticuloParte articulo : listaArticulos) {

                Articulo art;
                art = ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo());

                if (articulo.getGarantia() != true && articulo.getPresupuestar()) {
                    precio = precio + articulo.getTarifa() * articulo.getUsados();
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
                art = ArticuloDAO.buscarArticuloPorID(getContext(), articulo.getFk_articulo());
                if (articulo.getGarantia() != true && articulo.getFacturar()) {
                    precio = precio + articulo.getTarifa() * articulo.getUsados();
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


    public void recalcular() {

        try {
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
            if (ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()) != null) {
                articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()));
                if (!articuloPartes.isEmpty()) {
                    precioTotalArticulos = getPrecioTotalArticulosPartePpto(articuloPartes);
                    et_preeu_materiales.setText(String.format("%.2f", (precioTotalArticulos)));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preeu_disposicion_servicio = DatosAdicionalesDAO.buscarDisposicionDeServicio(getContext(), datos.getId_rel());
            et_preeu_total_disposicion_servicio.setText(String.valueOf(preeu_disposicion_servicio));
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            preeu_mano_de_obra_precio = DatosAdicionalesDAO.buscarPrecioManoDeObra(getContext(), datos.getId_rel());
        } catch (SQLException e) {
            e.printStackTrace();
        }


        try {
            preeu_mano_de_obra_horas = DatosAdicionalesDAO.buscarHorasManoDeObra(getContext(), datos.getId_rel());
        } catch (SQLException e) {
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

        if (!et_preeu_km_precio.getText().toString().equals("") && u.isNumeric(et_preeu_km_precio.getText().toString())) {
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
        if (view.getId() == BtnVerMateriales.getId()) {
            Intent i = new Intent(getContext(), GestionMateriales.class);
            i.putExtra("fk_parte", parte.getId_parte());
            startActivityForResult(i, 654);
        } else {
            Calendar c = Calendar.getInstance();
            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = df.format(c.getTime());
            if (view.getId() == R.id.btnFirmar) {
                Intent i = new Intent(getContext(), FirmaCliente.class);
                startActivityForResult(i, 99);
            } else if (view.getId() == R.id.btn_calcular_tiempo) {
                preeu_mano_de_obra_horas = 0;
                String horaEntrada = datos.getMatem_hora_entrada();
                String[] tiempos = horaEntrada.split(":");
                int hour = 0;
                int minute = 0;
                int second = 0;
                try {
                    hour = Integer.valueOf(tiempos[0]);
                    minute = Integer.valueOf(tiempos[1]);
                    second = Integer.valueOf(tiempos[2]);
                }catch (Exception e){
                    //Dialogo.dialogoError("No se ha podido establecer automaticamente la duración de la visita porque la hora de inicio ha sido borrada en gestnet.", getContext());
                }
                int segDesdeElInicio = hour * 60 * 60 + minute * 60 + second;
                Calendar c2 = Calendar.getInstance();
                SimpleDateFormat df2 = new SimpleDateFormat("HH:mm:ss");
                String formattedDate2 = df.format(c.getTime());
                String[] tiempos2 = formattedDate2.split(":");
                int hourFin = Integer.valueOf(tiempos2[0]);
                int minuteFin = Integer.valueOf(tiempos2[1]);
                int secondFin = Integer.valueOf(tiempos2[2]);
                int segDesdeFin = hourFin * 60 * 60 + minuteFin * 60 + secondFin;
                int segTotales = segDesdeFin - segDesdeElInicio;
                int horaTotal = segTotales / 3600;
                int segTotal = segTotales % 3600;
                int minTotal = segTotal / 60;
                btn_preeu_mano_de_obra.setText(horaTotal + " horas " + minTotal + " minutos");
                textoBoton = horaTotal + " horas " + minTotal + " minutos";
                double mins = Double.parseDouble(String.format("%.2f", minTotal/60.0).replace(",","."));
                preeu_mano_de_obra_horas = Double.valueOf(horaTotal + mins);

                try {
                    ParteDAO.actualizarTextoDuracion(getContext(), parte.getId_parte(), textoBoton);
                    DatosAdicionalesDAO.actualizarHorasManoDeObra(getContext(), datos.getId_rel(), preeu_mano_de_obra_horas);
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

                    et_preeu_total_mano_de_obra_horas.setText(String.format("%.2f", mH * preeu_mano_de_obra_horas).replace(",","."));

                }
            } else if (view.getId() == R.id.btn_preeu_mano_de_obra) {
                preeu_mano_de_obra_horas = 0;
                int hour = 0;
                final int minute = 0;
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), (timePicker, selectedHour, selectedMinute) -> {

                    String HorasMinutos = selectedHour + " horas " + selectedMinute + " minutos";
                    btn_preeu_mano_de_obra.setText(HorasMinutos);
                    textoBoton = HorasMinutos;
                    double minutos = selectedMinute;
                    minutos = minutos / 60;
                    preeu_mano_de_obra_horas = selectedHour + minutos;
                    try {
                        ParteDAO.actualizarTextoDuracion(getContext(), parte.getId_parte(), textoBoton);
                        DatosAdicionalesDAO.actualizarHorasManoDeObra(getContext(), datos.getId_rel(), preeu_mano_de_obra_horas);
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

                }, hour, minute, true);
                mTimePicker.setTitle("Selecciona la duración");
                mTimePicker.show();
            } else if (view.getId() == R.id.btnFinalizar) {
                recalcular();

                if (usuario.getFirma().equals("")) {

                    try {
                        usuario = UsuarioDAO.buscarUsuario(getContext());
                    } catch (SQLException e) {
                        Dialogo.dialogoError("Error al intentar acceder a los datos del técnico.", getContext());
                        return;
                    }

                    if (usuario.getFirma().equals("")){
                        Dialogo.dialogoError("Por favor, registra tu firma en el apartado 'Guardar mi firma'", getContext());
                        return;
                    }
                }
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
                            preeu_materiales = Double.parseDouble(str);
                        }
                        if (!et_preeu_puesta_marcha.getText().toString().equals("")) {
                            preeu_puesta_marcha = Double.parseDouble(et_preeu_puesta_marcha.getText().toString());
                        }

                        if (!et_preeu_servicio_urgencia.getText().toString().equals("")) {
                            preeu_servicio_urgencia = Double.parseDouble(et_preeu_servicio_urgencia.getText().toString());
                        }
                        if (!et_preeu_km.getText().toString().equals("")) {
                            preeu_km = Double.parseDouble(et_preeu_km.getText().toString());
                        }
                        if (!et_preeu_km_precio.getText().toString().equals("")) {
                            preeu_km_precio = Double.parseDouble(et_preeu_km_precio.getText().toString());
                        }
                        preeu_km_precio_total = preeu_km * preeu_km_precio;
                        if (!et_preeu_analisis_combustion.getText().toString().equals("")) {
                            preeu_analisis_combustion = Double.parseDouble(et_preeu_analisis_combustion.getText().toString());
                        }
                        if (!et_preeu_adicional.getText().toString().equals("")) {
                            preeu_adicional = Double.parseDouble(et_preeu_adicional.getText().toString());
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
                                    6
                            );
                            datos.setMatem_hora_salida(formattedDate);
                            DatosAdicionalesDAO.actualizarHoraSalida(getContext(), datos.getId_rel(), formattedDate);
                            ParteDAO.actualizarParteDuracion(getContext(), parte.getId_parte(), String.valueOf(preeu_mano_de_obra_horas));
                            try {
                                guardarProtocolo();
                            } catch (Exception e) {

                            }


                            new HiloCerrarParte(getContext(), parte.getId_parte()).execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    } else {
                        Dialogo.dialogoError("Es necesario la firma del cliente para finalizar.(Pestaña de Documentos)\n\nGracias.", getContext());
                    }
            }
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        etOperacionEfectuada.clearFocus();

        if (parent.getId() == sp_preeu_disposicion_servicio.getId()) {
            if (sp_preeu_disposicion_servicio.getSelectedItemPosition() != 0) {
                String selected = sp_preeu_disposicion_servicio.getSelectedItem().toString();
                Log.d("SELECTED",selected);
                try {
                    preeu_disposicion_servicio = DisposicionesDAO.buscarPrecioDisposicionPorNombre(getContext(), sp_preeu_disposicion_servicio.getSelectedItem().toString());
                    DatosAdicionalesDAO.actualizarDisposicionDeServicio(getContext(), datos.getId_rel(), preeu_disposicion_servicio);
                    et_preeu_total_disposicion_servicio.setText(String.valueOf(preeu_disposicion_servicio));

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else if (parent.getId() == sp_preeu_mano_de_obra_precio.getId()) {
            if (sp_preeu_mano_de_obra_precio.getSelectedItemPosition() != 0) {
                try {
                    preeu_mano_de_obra_precio = ManoObraDAO.buscarPrecioManoObraPorNombre(getContext(), sp_preeu_mano_de_obra_precio.getSelectedItem().toString());
                    et_preeu_total_mano_de_obra_horas.setText(String.valueOf(preeu_mano_de_obra_precio * preeu_mano_de_obra_horas));
                    DatosAdicionalesDAO.actializarManoDeObraPrecio(getContext(), datos.getId_rel(), preeu_mano_de_obra_precio);
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        } else if (parent.getId() == spFormaPago.getId()) {
            if (spFormaPago.getSelectedItemPosition() != -1) {
                try {
                    forma_pago = FormasPagoDAO.buscarIdFormaPagoPorNombre(getContext(), spFormaPago.getSelectedItem().toString());
                    if (forma_pago == -1) forma_pago = 0;
                    DatosAdicionalesDAO.actualizarFormaPago(getContext(), datos.getId_rel(), forma_pago);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        }else if (parent.getId() == spTiposOS.getId()) {
            if (spTiposOS.getSelectedItemPosition() != 0) {
                Log.d("Posicion Spinner", String.valueOf(position));
                String selected = spTiposOS.getSelectedItem().toString();

               try {
                    tipoOsId = TiposOsDAO.buscarTipoOsPorNombreTipoOs(getContext(),selected);
                    ParteDAO.actualizarFk_tipo_os(getContext(),parte.getId_parte(),tipoOsId);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }else{
                try {
                    ParteDAO.actualizarFk_tipo_os(getContext(),parte.getId_parte(),-1);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

        } else if (parent.getId() == spProtocolos.getId()) {
            if (llPadre.getChildCount() != 0) {
                try {
                    guardarProtocolo();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (position != 0) {
                posicion = position;
                crearLinearProtocolo((ProtocoloAccion) spProtocolos.getSelectedItem());
            } else {
                posicion = 0;
                llPadre.removeAllViews();
                llPadre.setVisibility(View.GONE);
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
        if (buttonView.getId() == cb_acepta_presupuesto.getId())
            acepta_presupuesto = isChecked;
        else if (buttonView.getId() == cb_enviar_por_correo.getId()) {
            enviar_correo = isChecked;
            try {
                ParteDAO.enviarPorCorreo(getContext(), parte.getId_parte(), isChecked);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (isChecked)
            vista.findViewById(R.id.llEnviarPorCorreo).setVisibility(View.VISIBLE);
        else vista.findViewById(R.id.llEnviarPorCorreo).setVisibility(View.GONE);
    }

    public static Bitmap loadFirmaClienteFromStorage(int id, Context context) throws SQLException {
        Bitmap b = null;
        try {
            File f = new File(context.getExternalFilesDir(Environment.DIRECTORY_PICTURES), "firmaCliente" + id + ".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return b;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == REQUEST_CODE_SPEECH_INPUT && resultCode == Activity.RESULT_OK) {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

            String msg = "";
            if(!etOperacionEfectuada.getText().toString().equals(""))
                msg += etOperacionEfectuada.getText() + "\n";
            etOperacionEfectuada.setText(msg + result.get(0));
        }

        if (requestCode == REQUEST_CODE_SCAN && resultCode == Activity.RESULT_OK){
            var result = data.getStringExtra("scan_result");

            try {
                var maq = ArticuloParteDAO.buscarArticuloParteFkParte(getContext(), parte.getId_parte()).get(0);
                ArticuloParteDAO.actualizarN_Serie(getContext(), maq.getId(), result);
                btnDatosBarras.setEnabled(false);
                Toast.makeText(getContext(), "Se ha guardado el nº de serie: " + result, Toast.LENGTH_LONG).show();

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }

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
                    ParteDAO.actualizarFirma64(getContext(), parte.getId_parte(), encodedImage);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                btnFirmar.setEnabled(false);

            }
        } else if (requestCode == 654) {
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

    private boolean validarEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        String[] mails = email.split(";");
        boolean valido = true;
        for(String mail:mails){
            valido = pattern.matcher(mail).matches();
        }

        return valido;
    }



    TextWatcher watcher = new TextWatcher() {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            //YOUR CODE
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            //YOUR CODE
        }

        @Override
        public void afterTextChanged(Editable s) {

            recalcular();

        }
    };
    @Override
    public void onFocusChange(View v, boolean hasFocus) {

        recalcular();
        if (!hasFocus) {
            try {
                recalcular();
                DatosAdicionalesDAO.actualizarOperacionEfectuada(getContext(), datos.getId_rel(), etOperacionEfectuada.getText().toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void guardarProtocolo() throws SQLException {
        ProtocoloAccion protocoloAccion = (ProtocoloAccion) spProtocolos.getItemAtPosition(posicion);
        if (protocoloAccion.getFk_maquina() != 0 && protocoloAccion.getFk_maquina() != -1) {
            final int childCount = llPadre.getChildCount();
            int fk_maquina = protocoloAccion.getFk_maquina();
            for (int i = 0; i < childCount; i++) {
                View v = llPadre.getChildAt(i);
                LinearLayout ll = (LinearLayout) v;
                if (ll.getChildAt(0) instanceof TextView) {
                    ll = (LinearLayout) ll.getChildAt(1);
                } else {
                    ll = (LinearLayout) ll.getChildAt(0);
                }

                if(ll == null)
                    continue;

                final int childCount1 = (ll.getChildCount());
                for (int j = 0; j < childCount1; j++) {
                    View view1 = ll.getChildAt(j);
                    String valor = "";
                    if (view1 instanceof EditText) {
                        EditText et = (EditText) view1;
                        valor = et.getText().toString();
                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloFkMaquina(getContext(), valor, id, fk_maquina);
                        }
                    } else if (view1 instanceof CheckBox) {
                        CheckBox cb = (CheckBox) view1;
                        if (cb.isChecked()) {
                            valor = "1";
                        } else {
                            valor = "0";
                        }

                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkMaquinaIdAccion(getContext(), protocoloAccion.getFk_protocolo(), fk_maquina, Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloFkMaquina(getContext(), valor, id, fk_maquina);
                        }
                    }
                }

            }
        } else {
            final int childCount = llPadre.getChildCount();
            for (int i = 0; i < childCount; i++) {
                View v = llPadre.getChildAt(i);
                LinearLayout ll = (LinearLayout) v;
                if (configuracion.isParte_galeria()) {
                    if (ll.getChildAt(0) instanceof TextView) {
                        ll = (LinearLayout) ll.getChildAt(1);
                    } else {
                        ll = (LinearLayout) ll.getChildAt(0);
                    }
                }
                final int childCount1 = (ll.getChildCount());
                for (int j = 0; j < childCount1; j++) {
                    View view1 = ll.getChildAt(j);
                    String valor = "";
                    if (view1 instanceof EditText) {
                        EditText et = (EditText) view1;
                        valor = et.getText().toString();

                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloIdParte(getContext(), valor, id, parte.getId_parte());
                        }
                    } else if (view1 instanceof CheckBox) {
                        CheckBox cb = (CheckBox) view1;
                        if (cb.isChecked()) {
                            valor = "1";
                        } else {
                            valor = "0";
                        }
                        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())) != null) {
                            int id = ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkParteIdAccion(getContext(), protocoloAccion.getFk_protocolo(), parte.getId_parte(), Integer.parseInt(ll.getTag().toString())).getId_protocolo_accion();
                            ProtocoloAccionDAO.actualizarValorFkProtocoloIdParte(getContext(), valor, id, parte.getId_parte());
                        }

                    }
                }
            }
        }

    }

    private ArrayList<ProtocoloAccion> ordenarArrayAccionProtocolosV2(ArrayList<ProtocoloAccion> protocoloAccionArrayList) {
        ArrayList<ProtocoloAccion> arrayList = new ArrayList<>();
        for (ProtocoloAccion p : protocoloAccionArrayList) {
            if (!arrayListContieneProtocolo(arrayList, p))
                arrayList.add(p);
        }
        return arrayList;
    }

    private boolean arrayListContieneProtocolo(ArrayList<ProtocoloAccion> arrayList, ProtocoloAccion p) {
        boolean esta = false;
        for (ProtocoloAccion pA : arrayList) {
            if (p.getFk_maquina() == pA.getFk_maquina() && p.getFk_protocolo() == pA.getFk_protocolo()) {
                esta = true;
                break;
            }
        }
        return esta;
    }

    private void crearLinearProtocolo(ProtocoloAccion protocolo) {
        llPadre.removeAllViews();
        llPadre.setVisibility(View.VISIBLE);
        try {
            ArrayList<ProtocoloAccion> protocolos = new ArrayList<>();
            if (protocolo.getFk_maquina() != 0 && protocolo.getFk_maquina() != -1) {
                if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkMaquina(getContext(), protocolo.getFk_protocolo(), protocolo.getFk_maquina(), protocolo.getFk_parte()) != null) {
                    protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorFkProtocoloFkMaquina(getContext(), protocolo.getFk_protocolo(), protocolo.getFk_maquina(), protocolo.getFk_parte()));
                }
            }else{
                if (ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(), protocolo.getNombre_protocolo(), parte.getId_parte()) != null) {
                    protocolos.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorNombreProtocoloFkParte(getContext(), protocolo.getNombre_protocolo(), parte.getId_parte()));
                }
            }
            for (int i = 0; i < protocolos.size(); i++) {
                LinearLayout linearLayout = new LinearLayout(getContext());
                linearLayout.setTag(protocolos.get(i).getId_accion());
                linearLayout.setOrientation(LinearLayout.VERTICAL);
                linearLayout.setPadding(5, 5, 5, 5);
                if (protocolos.get(i).isTipo_accion() == true) {
                    LinearLayout linearLayout1 = new LinearLayout(getContext());
                    linearLayout1.setTag(protocolos.get(i).getId_accion());
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
                    if (configuracion.isParte_galeria()) {
                        ImageButton imageButton = new ImageButton(getContext());
                        imageButton.setLayoutParams(lp);
                        imageButton.setBackgroundResource(R.drawable.ic_menu_camera);
                        imageButton.setTag(protocolos.get(i).getId_protocolo_accion());
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), FotosProtocoloAccion.class);
                                i.putExtra("id", Integer.parseInt(String.valueOf(v.getTag())));
                                getActivity().startActivity(i);
                            }
                        });
                        linearLayout1.addView(imageButton);
                    }
                    CheckBox checkBox = new CheckBox(getContext());
                    checkBox.setHintTextColor(Color.parseColor("#ff9002"));
                    checkBox.setLinkTextColor(Color.parseColor("#ff9002"));
                    checkBox.setText(protocolos.get(i).getDescripcion());
                    if (protocolos.get(i).getValor().equals("1")) {
                        checkBox.setChecked(true);
                    } else {
                        checkBox.setChecked(false);
                    }
                    linearLayout1.addView(checkBox);
                    linearLayout.addView(linearLayout1);
                } else if (protocolos.get(i).isTipo_accion() == false) {
                    LinearLayout linearLayout1 = new LinearLayout(getContext());
                    linearLayout1.setTag(protocolos.get(i).getId_accion());
                    LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    linearLayout1.setLayoutParams(l);
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    if (configuracion.isParte_galeria()) {
                        ImageButton imageButton = new ImageButton(getContext());
                        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(70, 70);
                        imageButton.setLayoutParams(lp);
                        imageButton.setBackgroundResource(R.drawable.ic_menu_camera);
                        imageButton.setTag(protocolos.get(i).getId_protocolo_accion());
                        imageButton.setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                Intent i = new Intent(getActivity(), FotosProtocoloAccion.class);
                                i.putExtra("id", Integer.parseInt(String.valueOf(v.getTag())));
                                getActivity().startActivity(i);
                            }
                        });
                        linearLayout1.addView(imageButton);
                    }

                    TextView textView = new TextView(getContext());
                    //textView.setTextSize(14);
                    //textView.setTextColor(Color.BLACK);
                    textView.setText(protocolos.get(i).getDescripcion());
                    linearLayout.addView(textView);
                    EditText editText = new EditText(getContext());
                    editText.setBackgroundResource(R.drawable.edit_texts_naranja);
                    editText.setPadding(5, 5, 5, 5);
                    LinearLayout.LayoutParams lp2 = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    editText.setLayoutParams(lp2);
                    editText.setText(protocolos.get(i).getValor());
                    linearLayout1.addView(editText);
                    linearLayout.addView(linearLayout1);
                }/* else if (protocolos.get(i).isTipo_accion() == 2) {
                    LinearLayout linearLayout1 = new LinearLayout(getContext());
                    linearLayout1.setTag(protocolos.get(i).getId_accion());
                    LinearLayout.LayoutParams l = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                    linearLayout1.setLayoutParams(l);
                    linearLayout1.setOrientation(LinearLayout.HORIZONTAL);
                    TextView textView = new TextView(getContext());
                    textView.setTextSize(18);
                    textView.setTextColor(R.color.md_theme_dark_primaryContainer);
                    textView.setText(protocolos.get(i).getDescripcion());
                    textView.setPadding(0, 20, 0, 0);
                    linearLayout.addView(textView);
                    //linearLayout.addView(linearLayout1);
                }*/
                llPadre.addView(linearLayout);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}