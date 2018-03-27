package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaMateriales;
import com.multimedia.aes.gestnet_nucleo.clases.DataArticulos;
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
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBusquedaArticulo;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBusquedaArticulosPorNombre;
import com.multimedia.aes.gestnet_nucleo.nucleo.InfoArticulos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;


public class TabFragment6_materiales extends Fragment implements SearchView.OnQueryTextListener, AdapterView.OnItemClickListener, View.OnClickListener {
    private View vista;
    private static Parte parte = null;
    private Usuario usuario = null;
    private Maquina maquina = null;
    private DatosAdicionales datos = null;
    private SearchView svMateriales;
    private static ListView lvBusquedaMaterial;
    private static ListView lvMateriales;
    private static AdaptadorListaMateriales adaptadorListaMateriales;
    private static ArrayList<DataArticulos> dataArticulos = new ArrayList<>();
    private HiloBusquedaArticulosPorNombre hiloBusquedaArticulos;
    private Button btnCrearArticulo;
    private static Context context;
    private static Activity activity;

    //METODO
    private void inicializar() {
        //SEARCHVIEW
        svMateriales = (SearchView) vista.findViewById(R.id.svMateriales);
        //LISTVIEW
        lvBusquedaMaterial = (ListView) vista.findViewById(R.id.lvBusquedaMaterial);
        lvMateriales = (ListView) vista.findViewById(R.id.lvMateriales);
        //ONQUERYTEXTLISTENER
        svMateriales.setOnQueryTextListener(this);
        //ONITEMSELECTED
        lvBusquedaMaterial.setOnItemClickListener(this);
        //lvMateriales.setOnItemClickListener(this);

        btnCrearArticulo = (Button) vista.findViewById(R.id.btnCrearArticulo);
        btnCrearArticulo.setOnClickListener(this);

    }

    private void buscarMaterial(String text) throws SQLException {
        ArrayAdapter<String> adaptador;
        adaptador = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1);
        if (ArticuloDAO.buscarNombreArticulosPorNombre(getContext(), text) != null) {
            if (dataArticulos.size() != 0) {
                dataArticulos.clear();
            }
            adaptador.addAll(ArticuloDAO.buscarNombreArticulosPorNombre(getContext(), text));
        } else {
            if (hiloBusquedaArticulos != null) {
                if (hiloBusquedaArticulos.getStatus() != AsyncTask.Status.RUNNING) {
                    hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text);
                    hiloBusquedaArticulos.execute();
                } else {
                    hiloBusquedaArticulos.cancel(true);
                    hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text);
                    hiloBusquedaArticulos.execute();
                }
            } else {
                hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text);
                hiloBusquedaArticulos.execute();
            }

        }
        lvBusquedaMaterial.setAdapter(adaptador);
    }

    public static void sacarArticulos(String mensaje, Context context) {
        try {
            JSONArray jsonArray = new JSONArray(mensaje);
            ArrayAdapter<String> adaptador;
            adaptador = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
            if (dataArticulos != null) {
                if (dataArticulos.size() != 0) {
                    dataArticulos.clear();
                }
            }
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    int id_articulo = jsonArray.getJSONObject(i).getInt("id_articulo");
                    String nombre = jsonArray.getJSONObject(i).getString("nombre");
                    DataArticulos d = new DataArticulos(nombre, id_articulo);
                    dataArticulos.add(d);
                    adaptador.add(nombre);
                }
            } else {
                adaptador.add("NINGUNA COINCIDENCIA");
            }
            lvBusquedaMaterial.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void guardarArticulo(String mensaje, Context context,TabFragment6_materiales tab) {
        try {
            JSONObject jsonObject = new JSONObject(mensaje);
            String nombre_articulo;
            String referencia;
            String referencia_aux;
            String ean;
            int stock;
            double coste;
            double iva;
            double tarifa;
            double descuento;
            int id_articulo;
            if (jsonObject.getString("id_articulo").equals("null") || jsonObject.getString("id_articulo").equals("")) {
                id_articulo = -1;
            } else {
                id_articulo = jsonObject.getInt("id_articulo");
            }
            if (jsonObject.getString("nombre_articulo").equals("null") || jsonObject.getString("nombre_articulo").equals("")) {
                nombre_articulo = "";
            } else {
                nombre_articulo = jsonObject.getString("nombre_articulo");
            }
            if (jsonObject.getString("referencia").equals("null") || jsonObject.getString("referencia").equals("")) {
                referencia = "";
            } else {
                referencia = jsonObject.getString("referencia");
            }
            if (jsonObject.getString("referencia_aux").equals("null") || jsonObject.getString("referencia_aux").equals("")) {
                referencia_aux = "";
            } else {
                referencia_aux = jsonObject.getString("referencia_aux");
            }
            if (jsonObject.getString("ean").equals("null") || jsonObject.getString("ean").equals("")) {
                ean = "";
            } else {
                ean = jsonObject.getString("ean");
            }
            if (jsonObject.getString("stock").equals("null") || jsonObject.getString("stock").equals("")) {
                stock = -1;
            } else {
                stock = jsonObject.getInt("stock");
            }
            if (jsonObject.getString("coste").equals("null") || jsonObject.getString("coste").equals("")) {
                coste = -1;
            } else {
                coste = jsonObject.getDouble("coste");
            }
            if (jsonObject.getString("iva").equals("null") || jsonObject.getString("iva").equals("")) {
                iva = -1;
            } else {
                iva = jsonObject.getDouble("iva");
            }
            if (jsonObject.getString("tarifa").equals("null") || jsonObject.getString("tarifa").equals("")) {
                tarifa = -1;
            } else {
                tarifa = jsonObject.getDouble("tarifa");
            }
            if (jsonObject.getString("descuento").equals("null") || jsonObject.getString("descuento").equals("")) {
                descuento = -1;
            } else {
                descuento = jsonObject.getDouble("descuento");
            }
            Articulo a = ArticuloDAO.newArticuloRet(context, id_articulo,nombre_articulo,stock,referencia,referencia_aux,"","","",0,iva,tarifa,descuento,coste,ean,0);
            Intent i = new Intent(activity, InfoArticulos.class);
            i.putExtra("articuloId", a.getId_articulo());
            tab.startActivityForResult(i, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public static void llenarMateriales() throws SQLException {
        if (ArticuloParteDAO.buscarArticuloParteFkParte(context, parte.getId_parte()) != null) {
            ArrayList<Articulo> articulos = new ArrayList<>();
            ArrayList<ArticuloParte> articuloPartes = new ArrayList<>();
            articuloPartes.addAll(ArticuloParteDAO.buscarArticuloParteFkParte(context, parte.getId_parte()));
            for (ArticuloParte articuloParte : articuloPartes) {
                boolean esta = false;
                for (Articulo articulo : articulos) {
                    if (articulo.getId_articulo() == articuloParte.getFk_articulo()) {
                        esta = true;
                    }
                }
                if (!esta) {
                    articulos.add(ArticuloDAO.buscarArticuloPorID(context, articuloParte.getFk_articulo()));
                }

            }
           if (context!=null&&activity!=null){
               adaptadorListaMateriales = new AdaptadorListaMateriales(context, R.layout.camp_adapter_list_view_material, articulos, activity, parte.getId_parte());
               lvMateriales.setAdapter(adaptadorListaMateriales);
               lvMateriales.setVisibility(View.VISIBLE);
               lvBusquedaMaterial.setVisibility(View.GONE);
           }
        } else {
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
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(), parte.getFk_tecnico());
            maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(), parte.getFk_maquina());
            datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(), parte.getId_parte());
        } catch (java.sql.SQLException e) {
            e.printStackTrace();
        }
        inicializar();
        context = getContext();
        activity = getActivity();
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
        if(newText.length()>1){
        lvMateriales.setVisibility(View.GONE);
        lvBusquedaMaterial.setVisibility(View.VISIBLE);

        try {
            buscarMaterial(newText);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        }else{

            lvMateriales.setVisibility(View.VISIBLE);
            lvBusquedaMaterial.setVisibility(View.GONE);



        }
        return false;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lvBusquedaMaterial) {
            if (!lvBusquedaMaterial.getItemAtPosition(position).toString().equals("NINGUNA COINCIDENCIA")) {
                if (dataArticulos.isEmpty()) {
                    Intent i = new Intent(getActivity(), InfoArticulos.class);
                    try {
                        i.putExtra("articuloId", ArticuloDAO.buscarArticulosPorNombre(getContext(), lvBusquedaMaterial.getItemAtPosition(position).toString().split("-")[0]).get(0).getId_articulo());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    startActivityForResult(i, 1);
                } else {
                    for (int i = 0; i < dataArticulos.size(); i++) {
                        String a = lvBusquedaMaterial.getItemAtPosition(position).toString();
                        if (dataArticulos.get(i).getNombre().equals(a)){
                            new HiloBusquedaArticulo(getContext(),dataArticulos.get(i).getId(),this).execute();
                        }
                    }
                }

            }
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

    public static void borrarArticulo(int id){
        final int idArticulo = id;
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("¿Seguro que deseas borrar este artículo? Se borrarán todas las unidades");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            ArticuloParteDAO.borrarArticuloPartePorFkArticuloFkParte(context,idArticulo,parte.getId_parte());
                            llenarMateriales();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }


                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(
                "Cancelar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == btnCrearArticulo.getId()) {
            DialogFragment newFragment = new CrearArticuloDialogFragment(context);
            newFragment.setCancelable(false);
            newFragment.show(getFragmentManager(), "crear articulo");
        }


    }


}