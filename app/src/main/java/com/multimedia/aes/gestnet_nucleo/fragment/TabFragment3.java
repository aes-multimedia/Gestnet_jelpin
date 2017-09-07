package com.multimedia.aes.gestnet_nucleo.fragment;

import android.content.Context;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;

import java.util.ArrayList;

public class TabFragment3  extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    private View vista;
    private ArrayList<ProtocoloAccion> protocoloAccionArrayList = new ArrayList<>();
    private String[] arrayNombreProtocolos;
    private Spinner spProtocolos;
    private Parte parte = null;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);
        spProtocolos=(Spinner)vista.findViewById(R.id.spProtocolos);

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            int idParte = bundle.getInt("id", 0);
            try {
                    parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }

        try {
            darValores();
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        return vista;
    }

    private void darValores() throws java.sql.SQLException {
        //SPINNER FORMAS PAGO
        if (ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(),parte.getId_parte())!=null){
            try {
                protocoloAccionArrayList.addAll(ProtocoloAccionDAO.buscarProtocoloAccionPorFkParte(getContext(),parte.getId_parte()));
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }


            int indice=0;
            ordenarArrayAccionProtocolos(protocoloAccionArrayList,indice);


            arrayNombreProtocolos = new String[protocoloAccionArrayList.size()+ 1];
            arrayNombreProtocolos[0]= "--Seleciones un valor--";
            for (int i = 1; i < protocoloAccionArrayList.size() + 1; i++) {
                arrayNombreProtocolos[i] = protocoloAccionArrayList.get(i - 1).getNombre_protocolo();
            }
            spProtocolos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNombreProtocolos));

        }else {

            arrayNombreProtocolos = new String[1];
            arrayNombreProtocolos[0]= "Sin protocolos";
            spProtocolos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNombreProtocolos));
        }
    }

    private void ordenarArrayAccionProtocolos(ArrayList<ProtocoloAccion> protocoloAccionArrayList,int indice) {
        if(protocoloAccionArrayList.size()>1 && indice < protocoloAccionArrayList.size()) {
                if (protocoloAccionArrayList.get(indice).getFk_protocolo() == protocoloAccionArrayList.get(indice + 1).getFk_protocolo() &&
                        protocoloAccionArrayList.get(indice).getFk_maquina() == protocoloAccionArrayList.get(indice + 1).getFk_maquina()) {
                    protocoloAccionArrayList.remove(indice + 1);
                    ordenarArrayAccionProtocolos(protocoloAccionArrayList, indice);
                } else {
                    ordenarArrayAccionProtocolos(protocoloAccionArrayList, indice + 1);
                }
            }
        }


    @Override
    public void onClick(View view) {


    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
