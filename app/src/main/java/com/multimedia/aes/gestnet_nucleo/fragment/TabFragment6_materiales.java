package com.multimedia.aes.gestnet_nucleo.fragment;
import android.app.Activity;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.database.MatrixCursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaMateriales;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Articulo;
import com.multimedia.aes.gestnet_nucleo.entidades.ArticuloParte;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.nucleo.InfoArticulos;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class TabFragment6_materiales extends Fragment implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {
    private View vista;
    private Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    private SearchView svMateriales;
    private ListView lvBusquedaMaterial,lvMateriales;
    private AdaptadorListaMateriales adaptadorListaMateriales;

    //METODO
    private void inicializar(){
        //SEARCHVIEW
        svMateriales = (SearchView)vista.findViewById(R.id.svMateriales);
        //LISTVIEW
        lvBusquedaMaterial = (ListView)vista.findViewById(R.id.lvBusquedaMaterial);
        lvMateriales = (ListView)vista.findViewById(R.id.lvMateriales);
        //ONQUERYTEXTLISTENER
        svMateriales.setOnQueryTextListener(this);
        //ONITEMSELECTED
        lvBusquedaMaterial.setOnItemClickListener(this);
        lvMateriales.setOnItemClickListener(this);


    }
    private void buscarMaterial(String text) throws SQLException {
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<>(getContext(),android.R.layout.simple_list_item_1);
        if (ArticuloDAO.buscarNombreArticulosPorNombre(getContext(),text)!=null){
            adaptador.addAll(ArticuloDAO.buscarNombreArticulosPorNombre(getContext(),text));
        }else{
            adaptador.add("NINGUNA COINCIDENCIA");
        }
        lvBusquedaMaterial.setAdapter(adaptador);
    }
    private void llenarMateriales() throws SQLException {
        if (ArticuloParteDAO.buscarTodosLosArticuloPartePorFkParte(getContext(),parte.getId_parte())!=null){
            ArrayList<Articulo> articulos = new ArrayList<>();
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
            articuloPartes.addAll(ArticuloParteDAO.buscarTodosLosArticuloPartePorFkParte(getContext(),parte.getId_parte()));
            for (ArticuloParte articuloParte :articuloPartes) {
                boolean esta = false;
                for (Articulo articulo :articulos) {
                    if (articulo.getId_articulo()==articuloParte.getFk_articulo()){
                        esta = true;
                    }
                }
                if (!esta){
                    articulos.add(ArticuloDAO.buscarArticuloPorID(getContext(),articuloParte.getFk_articulo()));
                }

            }
            adaptadorListaMateriales  = new AdaptadorListaMateriales(getContext(), R.layout.camp_adapter_list_view_material, articulos, getActivity(),parte.getId_parte());
            lvMateriales.setAdapter(adaptadorListaMateriales);
            lvMateriales.setVisibility(View.VISIBLE);
            lvBusquedaMaterial.setVisibility(View.GONE);
        }else{
            lvBusquedaMaterial.setVisibility(View.VISIBLE);
            lvMateriales.setVisibility(View.GONE);
        }

    }

    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment6_materiales, container, false);
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
            datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        inicializar();
        try {
            llenarMateriales();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return vista;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        lvMateriales.setVisibility(View.GONE);
        lvBusquedaMaterial.setVisibility(View.VISIBLE);
        try {
            buscarMaterial(newText);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==R.id.lvBusquedaMaterial){
            Intent i = new Intent(getActivity(), InfoArticulos.class);
            try {
                i.putExtra("articuloId",ArticuloDAO.buscarArticulosPorNombre(getContext(),lvBusquedaMaterial.getItemAtPosition(position).toString().split("-")[0]).get(0).getId_articulo());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            startActivityForResult(i,1);
        }else if (parent.getId()==R.id.lvMateriales){

        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                lvBusquedaMaterial.setVisibility(View.GONE);
                lvMateriales.setVisibility(View.VISIBLE);
                svMateriales.setQuery("", false);
                svMateriales.clearFocus();
                svMateriales.onActionViewCollapsed();
                try {
                    llenarMateriales();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }
}