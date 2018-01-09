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
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TabFragment6_materiales extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;

    private Button btnBuscar;
    private EditText etBuscar;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    //METODO
    //OVERRIDE


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment6_materiales, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            int idParte = bundle.getInt("id", 0);
            try {
                parte = ParteDAO.buscarPartePorId(getContext(), idParte);
                usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
                maquina = MaquinaDAO.buscarMaquinaPorId(getContext(),parte.getFk_maquina());
                datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
            } catch (java.sql.SQLException e) {
                e.printStackTrace();
            }
        }
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

    private void actualizarVista(String cadena){

        ArrayList<Articulo> data = new ArrayList<>();
        try {
            data.addAll(filtrar(cadena));
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
    public List<Articulo> filtrar(String cadena) throws SQLException {
        List<Articulo> data = ArticuloDAO.filtrarArticulosPorNombre(getContext(),cadena);
        if(data==null)return new ArrayList<Articulo>();
        else return data;

    }

    @Override
    public void onClick(View view) {
        if(view.getId()==btnBuscar.getId()){
            String cadena= etBuscar.getText().toString();
            actualizarVista(cadena);

        }

        }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


}