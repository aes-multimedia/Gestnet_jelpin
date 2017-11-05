package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.ArticuloRecyclerViewAdapter;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import java.sql.SQLException;
import java.util.List;


public class TabFragment6_materiales extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;

    private Button btnBuscar;
    private EditText etBuscar;





    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment6_materiales, container, false);
        btnBuscar = (Button) vista.findViewById(R.id.bntBuscar);
        etBuscar = (EditText)vista.findViewById(R.id.etBuscar);
        btnBuscar.setOnClickListener(this);
        inicializar();








        return vista;
    }

    private void inicializar(){
        List<Articulo> data = null;
        try {
            data = fill_with_data();
            RecyclerView recyclerView = (RecyclerView) vista.findViewById(R.id.recyclerview);
            ArticuloRecyclerViewAdapter adapter = new ArticuloRecyclerViewAdapter(data,getContext(),getActivity());
            recyclerView.setAdapter(adapter);
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }


    public List<Articulo> fill_with_data() throws SQLException {
        List<Articulo> data = ArticuloDAO.buscarTodosLosArticulos(getContext());

        return data;

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