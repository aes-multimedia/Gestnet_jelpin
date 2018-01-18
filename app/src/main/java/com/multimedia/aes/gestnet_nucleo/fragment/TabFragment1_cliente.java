package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIniciarParte;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
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
    private TextView txtNumParte,txtCreadoPor,txtMaquina,txtTipoIntervencion,txtSituacionEquipo,txtDierccionTitular,txtSintomas;
    private EditText etNombreTitular,etDni,etTelefono1,etTelefono2,etTelefono3,etTelefono4,etObservaciones;
    private Button btnIniciarParte,btnClienteAusente;


    //METODO
    private void inicializarVariables() {

        //TEXT VIEWS
        txtNumParte  = (TextView) vista.findViewById(R.id.txtNumParte);
        txtCreadoPor= (TextView) vista.findViewById(R.id.txtCreadoPor);
        txtMaquina = (TextView) vista.findViewById(R.id.txtMaquina);
        txtTipoIntervencion= (TextView) vista.findViewById(R.id.txtTipoIntervencion);
        txtSituacionEquipo = (TextView) vista.findViewById(R.id.txtSituacionEquipo);
        txtDierccionTitular= (TextView) vista.findViewById(R.id.txtDierccionTitular);
        txtSintomas= (TextView)vista.findViewById(R.id.txtSintomas);


        //EDIT TEXTS
        etNombreTitular = (EditText) vista.findViewById(R.id.etNombreTitular);
        etNombreTitular.setEnabled(false);
        etDni= (EditText) vista.findViewById(R.id.etDni);
        etDni.setEnabled(false);
        etTelefono1 = (EditText) vista.findViewById(R.id.etTelefono1);
        etTelefono1.setEnabled(false);
        etTelefono2= (EditText) vista.findViewById(R.id.etTelefono2);
        etTelefono2.setEnabled(false);
        etTelefono3= (EditText) vista.findViewById(R.id.etTelefono3);
        etTelefono3.setEnabled(false);
        etTelefono4= (EditText) vista.findViewById(R.id.etTelefono4);
        etTelefono4.setEnabled(false);
        etObservaciones= (EditText) vista.findViewById(R.id.etObservaciones);
        etObservaciones.setEnabled(false);

        //BOTONES
        btnIniciarParte= (Button) vista.findViewById(R.id.btnIniciarParte);
        btnClienteAusente = (Button) vista.findViewById(R.id.btnClienteAusente);
        btnIniciarParte.setOnClickListener(this);
        btnClienteAusente.setOnClickListener(this);
        //SWITCH
        swEdicion = (Switch)vista.findViewById(R.id.swEdicion);
        swEdicion.setChecked(false);

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
    public void darValoresVariables(){
        txtNumParte.setText(String.valueOf(parte.getNum_parte()));
        txtCreadoPor.setText(String.valueOf(parte.getUser_creador()));
        txtMaquina.setText(String.valueOf(maquina.getModelo()));
        txtTipoIntervencion.setText(String.valueOf(parte.getTipo()));
        txtSituacionEquipo.setText(String.valueOf(maquina.getSituacion()));
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
        txtDierccionTitular.setText(dir);
        txtSintomas.setText(String.valueOf(parte.getOtros_sintomas()));
        etNombreTitular.setText(parte.getNombre_cliente());
        etDni.setText(parte.getDni_cliente());
        etTelefono1.setText(parte.getTelefono1_cliente());
        etTelefono2.setText(parte.getTelefono2_cliente());
        etTelefono3.setText(parte.getTelefono3_cliente());
        etTelefono4.setText(parte.getTelefono4_cliente());

    }
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment1_cliente, container, false);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
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
        if (parte.getEstado_android()!=0){
            btnIniciarParte.setVisibility(View.GONE);
            btnClienteAusente.setVisibility(View.GONE);
        }
        return vista;
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==btnIniciarParte.getId()){
            new HiloIniciarParte(getContext(),parte.getId_parte(),1).execute();
        }else if(view.getId()==btnClienteAusente.getId()){


        }
    }
}