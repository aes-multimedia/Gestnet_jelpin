package com.multimedia.aes.gestnet_nucleo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import java.sql.SQLException;
import java.util.ArrayList;

public class TabFragment1 extends Fragment implements View.OnClickListener {
    private View vista;
    private TextView txtNumOrdenIberdrola,txtTipoIntervencion,txtVenta,txtTipoVisita,
            txtTipoMantenimiento,txtContadorAverias,txtContrato,txtFechaLlamada,txtTipoUrgencia,
            txtTipo,txtMarca,txtModelo,txtDireccion, txtCodigoBarras;
    private EditText etObservaciones,etTelefono1,etTelefono2,etTelefono3,etTelefono4,etTelefono5,etNombre,etDni,etNombreFirmante,etDniFirmante;
    private ImageView ivLlamar1,ivLlamar2,ivLlamar3,ivLlamar4,ivLlamar5;
    private Button btnIniciarParte,btnConfirmarObsTel,btnVisitaFallida;
    private Parte parte = null;
    private ImageButton ibLocation,ibIr;
    private ImageView mImageView;
    private Bitmap mBitmap;
    private Button mBtnPrint;
    private ArrayList<String> mFiles = new ArrayList<String>();
    //private Maquina maquina=null;
    private CheckBox cbTitular;
    private LinearLayout llTitular,llBotonera;
    private static final int MY_PERMISSIONS_REQUEST_CALL = 1 ;
    private static final int MY_PERMISSIONS_REQUEST_INTERNET = 2 ;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 3 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_1, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            int idParte = bundle.getInt("id", 0);
            try {
                parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        inicializarVariables();



        return vista;
    }
    @Override
    public void onClick(View view) {

    }

    private void inicializarVariables() {
        txtNumOrdenIberdrola = (TextView)vista.findViewById(R.id.txtNumOrdenIberdrola);
        txtTipoIntervencion = (TextView)vista.findViewById(R.id.txtTipoIntervencion);
        txtVenta = (TextView)vista.findViewById(R.id.txtVenta);
        txtTipoVisita = (TextView)vista.findViewById(R.id.txtTipoVisita);
        txtTipoMantenimiento = (TextView)vista.findViewById(R.id.txtTipoMantenimiento);
        txtContadorAverias = (TextView)vista.findViewById(R.id.txtContadorAverias);
        txtContrato = (TextView)vista.findViewById(R.id.txtContrato);
        txtFechaLlamada = (TextView)vista.findViewById(R.id.txtFechaLlamada);
        txtTipoUrgencia = (TextView)vista.findViewById(R.id.txtTipoUrgencia);
        txtTipo = (TextView)vista.findViewById(R.id.txtTipo);
        txtMarca = (TextView)vista.findViewById(R.id.txtMarca);
        txtModelo = (TextView)vista.findViewById(R.id.txtModelo);
        txtDireccion = (TextView)vista.findViewById(R.id.txtDireccion);
        txtCodigoBarras = (TextView) vista.findViewById(R.id.txtCodigoBarras);

        etObservaciones = (EditText)vista.findViewById(R.id.etObservaciones);
        etTelefono1 = (EditText)vista.findViewById(R.id.etTelefono1);
        etTelefono2 = (EditText)vista.findViewById(R.id.etTelefono2);
        etTelefono3 = (EditText)vista.findViewById(R.id.etTelefono3);
        etTelefono4 = (EditText)vista.findViewById(R.id.etTelefono4);
        etTelefono5 = (EditText)vista.findViewById(R.id.etTelefono5);
        etNombre = (EditText)vista.findViewById(R.id.etNombre);
        etDni = (EditText)vista.findViewById(R.id.etDni);
        etNombreFirmante = (EditText)vista.findViewById(R.id.etNombreFirmante);
        etDniFirmante = (EditText)vista.findViewById(R.id.etDniFirmante);

        btnIniciarParte = (Button)vista.findViewById(R.id.btnIniciarParte);
        btnConfirmarObsTel = (Button)vista.findViewById(R.id.btnConfirmarObsTel);
        btnVisitaFallida = (Button)vista.findViewById(R.id.btnVisitaFallida);


        ivLlamar1 = (ImageView) vista.findViewById(R.id.ivLlamar1);
        ivLlamar2 = (ImageView) vista.findViewById(R.id.ivLlamar2);
        ivLlamar3 = (ImageView) vista.findViewById(R.id.ivLlamar3);
        ivLlamar4 = (ImageView) vista.findViewById(R.id.ivLlamar4);
        ivLlamar5 = (ImageView) vista.findViewById(R.id.ivLlamar5);

        ibLocation = (ImageButton) vista.findViewById(R.id.ibLocation);
        ibIr = (ImageButton) vista.findViewById(R.id.ibIr);

        cbTitular = (CheckBox) vista.findViewById(R.id.cbTitular);

        llTitular = (LinearLayout) vista. findViewById(R.id.llTitular);
        llBotonera = (LinearLayout) vista. findViewById(R.id.llBotonera);

        btnIniciarParte.setOnClickListener(this);
        btnConfirmarObsTel.setOnClickListener(this);
        btnVisitaFallida.setOnClickListener(this);
        ivLlamar1.setOnClickListener(this);
        ivLlamar2.setOnClickListener(this);
        ivLlamar3.setOnClickListener(this);
        ivLlamar4.setOnClickListener(this);
        ivLlamar5.setOnClickListener(this);
        ibLocation.setOnClickListener(this);
        ibIr.setOnClickListener(this);
        cbTitular.setOnClickListener(this);
    }
}