package com.multimedia.aes.gestnet_ssl.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.fragment.app.Fragment;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.adaptador.AdaptadorListaMaquinas;
import com.multimedia.aes.gestnet_ssl.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;
import com.multimedia.aes.gestnet_ssl.nucleo.AnadirDatosMaquina;
import com.multimedia.aes.gestnet_ssl.progressDialog.ManagerProgressDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


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