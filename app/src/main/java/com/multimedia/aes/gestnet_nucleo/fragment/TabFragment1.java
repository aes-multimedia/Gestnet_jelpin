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
    private Parte parte = null;
    private TextView txtNumParte,txtCreadoPor,txtMaquina,txtTipoIntervencion,txtSituacionEquipo,txtDierccionTitular;
    private EditText etNombreTitular,etDni,etTelefono1,etTelefono2,etTelefono3,etTelefono4;
    private Button btnIniciarParte,btnClienteAusente;

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
        darValoresVariables();
        return vista;
    }

    private void inicializarVariables() {

        //TEXT VIEWS
        txtNumParte  = (TextView) vista.findViewById(R.id.txtNumParte);
        txtCreadoPor= (TextView) vista.findViewById(R.id.txtCreadoPor);
        txtMaquina = (TextView) vista.findViewById(R.id.txtMaquina);
        txtTipoIntervencion= (TextView) vista.findViewById(R.id.txtTipoIntervencion);
        txtSituacionEquipo = (TextView) vista.findViewById(R.id.txtSituacionEquipo);
        txtDierccionTitular= (TextView) vista.findViewById(R.id.txtDierccionTitular);


        //EDIT TEXTS

        etNombreTitular = (EditText) vista.findViewById(R.id.etNombreTitular);
        etDni= (EditText) vista.findViewById(R.id.etDni);
        etTelefono1 = (EditText) vista.findViewById(R.id.etTelefono1);
        etTelefono2= (EditText) vista.findViewById(R.id.etTelefono2);
        etTelefono3= (EditText) vista.findViewById(R.id.etTelefono3);
        etTelefono4= (EditText) vista.findViewById(R.id.etTelefono4);

        //BOTONES

        btnIniciarParte= (Button) vista.findViewById(R.id.btnIniciarParte);
        btnClienteAusente = (Button) vista.findViewById(R.id.btnClienteAusente);



    }
    public void darValoresVariables(){



    }

    @Override
    public void onClick(View view) {



    }
}