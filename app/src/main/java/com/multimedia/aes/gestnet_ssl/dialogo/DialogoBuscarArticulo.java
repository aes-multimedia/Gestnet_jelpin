package com.multimedia.aes.gestnet_ssl.dialogo;

import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.clases.DataArticulos;
import com.multimedia.aes.gestnet_ssl.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Articulo;
import com.multimedia.aes.gestnet_ssl.hilos.HiloBusquedaArticulo;
import com.multimedia.aes.gestnet_ssl.hilos.HiloBusquedaArticulosPorNombre;
import com.multimedia.aes.gestnet_ssl.nucleo.Index;
import com.multimedia.aes.gestnet_ssl.nucleo.InfoArticulos;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class DialogoBuscarArticulo extends DialogFragment implements View.OnClickListener, SearchView.OnQueryTextListener, AdapterView.OnItemClickListener {

    private View v;
    private Button btnAceptar;
    int id;
    private HiloBusquedaArticulosPorNombre hiloBusquedaArticulos;
    private static ArrayList<DataArticulos> dataArticulos = new ArrayList<>();
    private static ListView lvArticulo;
    private SearchView svBuscarArticulo;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullScreenDialogStyle);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.dialogo_buscar_articulo, container, false);
        btnAceptar = v.findViewById(R.id.btnAceptar);
        lvArticulo = v.findViewById(R.id.lvArticulo);
        svBuscarArticulo = v.findViewById(R.id.svBuscarArticulo);
        btnAceptar.setOnClickListener(this);
        svBuscarArticulo.setOnQueryTextListener(this);
        lvArticulo.setOnItemClickListener(this);
        return v;
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnAceptar) {
            getDialog().dismiss();
        }

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
                    hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text,1);
                    hiloBusquedaArticulos.execute();
                } else {
                    hiloBusquedaArticulos.cancel(true);
                    hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text,1);
                    hiloBusquedaArticulos.execute();
                }
            } else {
                hiloBusquedaArticulos = new HiloBusquedaArticulosPorNombre(getContext(), text,1);
                hiloBusquedaArticulos.execute();
            }

        }


        lvArticulo.setAdapter(adaptador);
    }

    @Override
    public boolean onQueryTextSubmit(String s) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        if(s.length()>1){
            try {
                buscarMaterial(s);
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return false;
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getId() == R.id.lvArticulo) {
            if (!lvArticulo.getItemAtPosition(i).toString().equals("NINGUNA COINCIDENCIA")) {
                if (dataArticulos.isEmpty()) {
                    Intent in = new Intent(getActivity(), InfoArticulos.class);
                    try {
                        String r =  lvArticulo.getItemAtPosition(i).toString().split(" <-> ")[0];
                        String n =  lvArticulo.getItemAtPosition(i).toString().split(" <-> ")[1];
                        int idArticulo;
                        if(r!=null && !r.equals(""))
                            idArticulo=ArticuloDAO.buscarArticulosPorReferencia(getContext(), r).get(0).getId_articulo();
                        else
                            idArticulo=ArticuloDAO.buscarArticulosPorNombre(getContext(), n).get(0).getId_articulo();

                        in.putExtra("articuloId",idArticulo);
                        in.putExtra("sitio",1);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    startActivity(in);
                } else {
                    for (int j = 0; j < dataArticulos.size(); j++) {
                        String a = lvArticulo.getItemAtPosition(j).toString();
                        if (dataArticulos.get(j).getNombre().equals(a)){
                            new HiloBusquedaArticulo(getContext(),dataArticulos.get(j).getId()).execute();
                        }
                    }
                }
            }
        }
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
            lvArticulo.setAdapter(adaptador);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public static void guardarArticuloDialogo(String mensaje, Context context) {
        try {
            JSONObject jsonObject = new JSONObject(mensaje);
            String nombre_articulo;
            String referencia;
            String referencia_aux;
            String ean;
            double stock;
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
                stock = jsonObject.getDouble("stock");
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
            Articulo a = ArticuloDAO.newArticuloRet(context,-1, id_articulo,nombre_articulo,stock,referencia,referencia_aux,"","","",0,iva,tarifa,descuento,coste,ean,0);
            Intent i = new Intent(context, InfoArticulos.class);
            i.putExtra("articuloId", a.getId_articulo());
            i.putExtra("sitio",1);
            ((Index)context).startActivity(i);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
