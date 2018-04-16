package com.multimedia.aes.gestnet_nucleo.fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.Mapa;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIniciarParte;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class TabFragment1_cliente extends Fragment implements View.OnClickListener {

    private View vista;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    private List<Usuario> listaUsuarios;
    private List<DatosAdicionales> datosAdicionalesList;
    private Switch swEdicion;
    private TextView txtNumParte,txtCreadoPor,txtMaquina,txtTipoIntervencion,txtSituacionEquipo,txtDierccionTitular,txtSintomas,txtHoraInicio,tvHoraInicio,txtSintomaLista;
    private EditText etNombreTitular,etDni,etTelefono1,etTelefono2,etTelefono3,etTelefono4,etObservaciones;
    private Button btnIniciarParte,btnClienteAusente,btnImprimir;
    private ImageButton ibLocation,ibIr;
    private ImageView ivLlamar1,ivLlamar2,ivLlamar3,ivLlamar4;
    private String horaInicio;



    //METODO
    private void inicializarVariables() {
        //TEXT VIEWS
        txtNumParte  = (TextView) vista.findViewById(R.id.txtNumParte);
        txtCreadoPor= (TextView) vista.findViewById(R.id.txtCreadoPor);
        txtMaquina = (TextView) vista.findViewById(R.id.txtMaquina);
        txtTipoIntervencion= (TextView) vista.findViewById(R.id.txtTipoIntervencion);
        txtSituacionEquipo = (TextView) vista.findViewById(R.id.txtSituacionEquipo);
        txtDierccionTitular= (TextView) vista.findViewById(R.id.txtDierccionTitular);
        txtSintomaLista= (TextView) vista.findViewById(R.id.txtSintomaLista);
        txtSintomas= (TextView)vista.findViewById(R.id.txtSintomas);
        txtHoraInicio =(TextView)vista.findViewById(R.id.txtHoraInicio);
        tvHoraInicio=(TextView)vista.findViewById(R.id.tvHoraInicio);

        //EDIT TEXTS
        etNombreTitular = (EditText) vista.findViewById(R.id.etNombreTitular);
        etDni= (EditText) vista.findViewById(R.id.etDni);
        etTelefono1 = (EditText) vista.findViewById(R.id.etTelefono1);
        etTelefono2= (EditText) vista.findViewById(R.id.etTelefono2);
        etTelefono3= (EditText) vista.findViewById(R.id.etTelefono3);
        etTelefono4= (EditText) vista.findViewById(R.id.etTelefono4);
        etObservaciones= (EditText) vista.findViewById(R.id.etObservaciones);
        //BOTONES
        btnIniciarParte= (Button) vista.findViewById(R.id.btnIniciarParte);
        btnClienteAusente = (Button) vista.findViewById(R.id.btnClienteAusente);
        btnImprimir = (Button) vista.findViewById(R.id.btnImprimir);
        //IMAGEBUTTON
        ibLocation = (ImageButton) vista.findViewById(R.id.ibLocation);
        ibIr = (ImageButton) vista.findViewById(R.id.ibIr);
        //IMAGEVIEW
        ivLlamar1 = (ImageView) vista.findViewById(R.id.ivLlamar1);
        ivLlamar2 = (ImageView) vista.findViewById(R.id.ivLlamar2);
        ivLlamar3 = (ImageView) vista.findViewById(R.id.ivLlamar3);
        ivLlamar4 = (ImageView) vista.findViewById(R.id.ivLlamar4);
        //ONCLICK
        btnIniciarParte.setOnClickListener(this);
        btnClienteAusente.setOnClickListener(this);
        btnImprimir.setOnClickListener(this);
        ibLocation.setOnClickListener(this);
        ibIr.setOnClickListener(this);
        ivLlamar1.setOnClickListener(this);
        ivLlamar2.setOnClickListener(this);
        ivLlamar3.setOnClickListener(this);
        ivLlamar4.setOnClickListener(this);
        //SWITCH
        swEdicion = (Switch)vista.findViewById(R.id.swEdicion);
        swEdicion.setChecked(false);
        //TEXTWATCHER
        etNombreTitular.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarNobreCliente(getContext(),parte.getId_parte(),etNombreTitular.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etDni.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarDniCliente(getContext(),parte.getId_parte(),etDni.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etTelefono1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarTelefono1Cliente(getContext(),parte.getId_parte(),etTelefono1.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etTelefono2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarTelefono2Cliente(getContext(),parte.getId_parte(),etTelefono2.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etTelefono3.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarTelefono3Cliente(getContext(),parte.getId_parte(),etTelefono3.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etTelefono4.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarTelefono4Cliente(getContext(),parte.getId_parte(),etTelefono4.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        etObservaciones.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                try {
                    ParteDAO.actualizarObservaciones(getContext(),parte.getId_parte(),etObservaciones.getText().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });
        //CHECKEDCHANGE
        swEdicion.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(swEdicion.isChecked()){

                    etNombreTitular.setEnabled(true);
                    etDni.setEnabled(true);
                    etTelefono1.setEnabled(true);
                    etTelefono2.setEnabled(true);
                    etTelefono3.setEnabled(true);
                    etTelefono4.setEnabled(true);
                    etObservaciones.setEnabled(true);
                }

                else {
                    etNombreTitular.setEnabled(false);
                    etDni.setEnabled(false);
                    etTelefono1.setEnabled(false);
                    etTelefono2.setEnabled(false);
                    etTelefono3.setEnabled(false);
                    etTelefono4.setEnabled(false);
                    etObservaciones.setEnabled(false);
                }


            }
        });



    }
    private void darValoresVariables(){
        if (maquina!=null){
            txtMaquina.setText(String.valueOf(maquina.getModelo()));
            txtSituacionEquipo.setText(String.valueOf(maquina.getSituacion()));
        }
        etNombreTitular.setEnabled(false);
        etDni.setEnabled(false);
        etTelefono1.setEnabled(false);
        etTelefono2.setEnabled(false);
        etTelefono3.setEnabled(false);
        etTelefono4.setEnabled(false);
        etObservaciones.setEnabled(false);
        txtNumParte.setText(String.valueOf(parte.getNum_parte()));
        txtCreadoPor.setText(String.valueOf(parte.getUser_creador()));
        txtTipoIntervencion.setText(String.valueOf(parte.getTipo()));

        String dir = "";
        if (!parte.getTipo_via().trim().equals("")&&!parte.getTipo_via().trim().equals("null")){
            dir+=parte.getTipo_via()+" ";
        }
        if (!parte.getVia().trim().equals("")&&!parte.getVia().trim().equals("null")){
            dir+=parte.getVia()+" ";
        }
        if (!parte.getNumero_direccion().trim().equals("")&&!parte.getNumero_direccion().trim().equals("null")){
            dir+="Nº "+parte.getNumero_direccion()+" ";
        }
        if (!parte.getEscalera_direccion().trim().equals("")&&!parte.getEscalera_direccion().trim().equals("null")){
            dir+="Esc. "+parte.getEscalera_direccion()+" ";
        }
        if (!parte.getPiso_direccion().trim().equals("")&&!parte.getPiso_direccion().trim().equals("null")){
            dir+="Piso "+parte.getPiso_direccion()+" ";
        }
        if (!parte.getPuerta_direccion().trim().equals("")&&!parte.getPuerta_direccion().trim().equals("null")){
            dir+=parte.getPuerta_direccion()+" ";
        }
        if (!parte.getMunicipio_direccion().trim().equals("")&&!parte.getMunicipio_direccion().trim().equals("null")){
            dir+="("+parte.getMunicipio_direccion()+"-";
        }
        if (!parte.getProvincia_direccion().trim().equals("")&&!parte.getProvincia_direccion().trim().equals("null")){
            dir+=parte.getProvincia_direccion()+")";
        }
        if (!datos.getMatem_hora_entrada().trim().equals("")&&!datos.getMatem_hora_entrada().trim().equals("null")){
           horaInicio=datos.getMatem_hora_entrada();

        }
        txtHoraInicio.setText(horaInicio);
        txtDierccionTitular.setText(dir);
        txtSintomas.setText(String.valueOf(parte.getOtros_sintomas()));
        txtSintomaLista.setText(String.valueOf(parte.getSintomas()));
        etNombreTitular.setText(parte.getNombre_cliente());
        etDni.setText(parte.getDni_cliente());
        etTelefono1.setText(parte.getTelefono1_cliente());
        etTelefono2.setText(parte.getTelefono2_cliente());
        etTelefono3.setText(parte.getTelefono3_cliente());
        etTelefono4.setText(parte.getTelefono4_cliente());
        etObservaciones.setText(parte.getObservaciones());

    }
    @SuppressLint("MissingPermission")
    public void llamar(String tel) {
        getContext().startActivity(new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + tel)));
    }
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment1_cliente, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;

        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(),parte.getFk_maquina());
            datos =DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        darValoresVariables();
        if (parte.getEstado_android()==3||parte.getEstado_android()==1){
            btnClienteAusente.setVisibility(View.GONE);
            btnIniciarParte.setVisibility(View.GONE);
            btnImprimir.setVisibility(View.VISIBLE);
            txtHoraInicio.setVisibility(View.VISIBLE);

        }else if(parte.getEstado_android()==2){
            btnImprimir.setVisibility(View.GONE);
            tvHoraInicio.setVisibility(View.GONE);
            btnClienteAusente.setVisibility(View.GONE);
            btnIniciarParte.setVisibility(View.VISIBLE);
            btnImprimir.setVisibility(View.GONE);
        }else{
            btnClienteAusente.setVisibility(View.VISIBLE);
            btnIniciarParte.setVisibility(View.VISIBLE);
            btnImprimir.setVisibility(View.GONE);
        }
        return vista;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.btnIniciarParte){
            Calendar c = Calendar.getInstance();

            SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
            String formattedDate = df.format(c.getTime());

            try {
                String observaciones = etObservaciones.getText().toString();
                String nombre = etNombreTitular.getText().toString();
                String dni = etDni.getText().toString();
                String tel1 = etTelefono1.getText().toString();
                String tel2 = etTelefono2.getText().toString();
                String tel3 = etTelefono3.getText().toString();
                String tel4 = etTelefono4.getText().toString();
                parte.setObservaciones(observaciones);
                parte.setNombre_cliente(nombre);
                parte.setDni_cliente(dni);
                parte.setTelefono1_cliente(tel1);
                parte.setTelefono2_cliente(tel2);
                parte.setTelefono3_cliente(tel3);
                parte.setTelefono4_cliente(tel4);
                datos.setMatem_hora_entrada(formattedDate);
                DatosAdicionalesDAO.actualizarHoraEntrada(getContext(), datos.getId_rel(),formattedDate);
                ParteDAO.actualizarParte(getContext(),parte.getId_parte(),nombre,dni,tel1,tel2,tel3,tel4,observaciones);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            new HiloIniciarParte(getContext(),parte,1,2).execute();


        }else if(view.getId()==R.id.btnClienteAusente){


        new HiloIniciarParte(getContext(),parte,2,13).execute();


        }else if(view.getId()==R.id.btnImprimir){
            ((Index)getContext()).impresion();
        }else if(view.getId()==R.id.ibLocation){
            Intent i = new Intent(getContext(),Mapa.class);
            Double a = Double.parseDouble(parte.getLatitud_direccion());
            Double b = Double.parseDouble(parte.getLongitud_direccion());
            i.putExtra("destino", new double[]{a,b});
            getContext().startActivity(i);
        }else if(view.getId()==R.id.ibIr){
            String geoUri = null;
            geoUri = "http://maps.google.com/maps?q=loc:" + parte.getLatitud_direccion() + "," + parte.getLongitud_direccion()+ " (" + parte.getNombre_cliente() + ")";
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(geoUri));
            getContext().startActivity(intent);
        }else if (view.getId() == R.id.ivLlamar1){
            if (etTelefono1.getText().toString().equals("")||etTelefono1.getText().toString().equals("null")){
                Dialogo.dialogoError("Movil no valido",getContext());
            }else{
                llamar(etTelefono1.getText().toString());
            }
        }else if (view.getId() == R.id.ivLlamar2){
            if (etTelefono2.getText().toString().equals("") || etTelefono2.getText().toString().equals("null")) {
                Dialogo.dialogoError("Movil no valido",getContext());
            } else {
                llamar(etTelefono2.getText().toString());
            }
        }else if (view.getId() == R.id.ivLlamar3){
            if (etTelefono3.getText().toString().equals("") || etTelefono3.getText().toString().equals("null")) {
                Dialogo.dialogoError("Movil no valido",getContext());
            } else {
                llamar(etTelefono3.getText().toString());
            }
        }else if (view.getId() == R.id.ivLlamar4){
            if (etTelefono4.getText().toString().equals("") || etTelefono4.getText().toString().equals("null")) {
                Dialogo.dialogoError("Movil no valido",getContext());
            } else {
                llamar(etTelefono4.getText().toString());
            }
        }
    }

    @Override
    public void onPause() {
        try {
            String observaciones = etObservaciones.getText().toString();
            String nombre = etNombreTitular.getText().toString();
            String dni = etDni.getText().toString();
            String tel1 = etTelefono1.getText().toString();
            String tel2 = etTelefono2.getText().toString();
            String tel3 = etTelefono3.getText().toString();
            String tel4 = etTelefono4.getText().toString();
            parte.setObservaciones(observaciones);
            parte.setNombre_cliente(nombre);
            parte.setDni_cliente(dni);
            parte.setTelefono1_cliente(tel1);
            parte.setTelefono2_cliente(tel2);
            parte.setTelefono3_cliente(tel3);
            parte.setTelefono4_cliente(tel4);
            ParteDAO.actualizarParte(getContext(),parte.getId_parte(),nombre,dni,tel1,tel2,tel3,tel4,observaciones);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        super.onPause();
    }

}