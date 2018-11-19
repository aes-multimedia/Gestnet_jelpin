package com.multimedia.aes.gestnet_nucleo.fragments;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaAnalisis;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaMaquinas;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloActualizaMaquina;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBuscarDocumentosModelo;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCrearMaquina;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIntervencionesAnteriores;
import com.multimedia.aes.gestnet_nucleo.nucleo.AnadirDatosAnalisis;
import com.multimedia.aes.gestnet_nucleo.nucleo.AnadirDatosMaquina;
import com.multimedia.aes.gestnet_nucleo.nucleo.Index;
import com.multimedia.aes.gestnet_nucleo.nucleo.IntervencionesAnteriores;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class TabFragment2_equipo extends Fragment implements View.OnClickListener {

    private View vista;
    private static ListView lvMaquinas;
    private Button btnNuevaMaquina;
    private static Parte parte = null;
    private static ArrayList<Maquina> arrayListMaquina = new ArrayList<>();
    private static AdaptadorListaMaquinas adaptadorListaMaquinas;
    private static Activity activity;
    public void sacarMensaje(String msg) {
        if (ManagerProgressDialog.getDialog()!=null){
            ManagerProgressDialog.cerrarDialog();
        }
        Dialogo.dialogoError(msg,getContext());
    }

    //METODOS
    public void inicializarVariables(){
        //BUTTON
        btnNuevaMaquina = vista.findViewById(R.id.btnNuevaMaquina);
        btnNuevaMaquina.setOnClickListener(this);
        //LISTVIEW
        lvMaquinas = (ListView)vista.findViewById(R.id.lvMaquinas);
        activity = getActivity();
    }

    public static void añadirMaquina(){
        try {
            arrayListMaquina.clear();
            List<Maquina> a = MaquinaDAO.buscarMaquinaPorFkParte(activity,parte.getId_parte());
            if (a!=null) {
                arrayListMaquina.addAll(a);
                adaptadorListaMaquinas  = new AdaptadorListaMaquinas(activity, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina, activity );
                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                lvMaquinas.setVisibility(View.VISIBLE);
            }else{
                adaptadorListaMaquinas  = new AdaptadorListaMaquinas(activity, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina, activity);
                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                lvMaquinas.setVisibility(View.VISIBLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment2_equipo, container, false);
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
        } catch (SQLException e) {
            e.printStackTrace();
        }
        inicializarVariables();
        arrayListMaquina.clear();
        añadirMaquina();
        return vista;
    }
    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.btnNuevaMaquina){
            Intent i = new Intent(getContext(), AnadirDatosMaquina.class);
            i.putExtra("id",-1);
            startActivity(i);
        }
    }
}