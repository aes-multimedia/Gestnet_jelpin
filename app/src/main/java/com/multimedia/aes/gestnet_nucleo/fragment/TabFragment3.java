package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pools;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.ProtocoloAccion;

import java.util.ArrayList;

public class TabFragment3  extends Fragment implements View.OnClickListener {

    private View vista;
    private ArrayList<ProtocoloAccion> protocoloAccionArrayList = new ArrayList<>();
    private String[] arrayNombreProtocolos;
    private Spinner spProtocolos;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);
        spProtocolos=(Spinner)vista.findViewById(R.id.spProtocolos);


        darValores();
        return vista;
    }

    private void darValores() {
        //SPINNER FORMAS PAGO
        if (ProtocoloAccionDAO.buscarTodosLosProtocoloAccion(getContext())!=null){
            protocoloAccionArrayList.addAll(ProtocoloAccionDAO.buscarTodosLosProtocoloAccion(getContext()));
            arrayNombreProtocolos = new String[protocoloAccionArrayList.size()+ 1];
            arrayNombreProtocolos[0]= "--Seleciones un valor--";
            for (int i = 1; i < protocoloAccionArrayList.size() + 1; i++) {
                arrayNombreProtocolos[i] = protocoloAccionArrayList.get(i - 1).getNombre_protocolo();
            }
            spProtocolos.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayNombreProtocolos));

        }
    }

    @Override
    public void onClick(View view) {


    }
}
