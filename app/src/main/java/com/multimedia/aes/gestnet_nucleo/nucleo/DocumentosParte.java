package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.clases.Documento;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloBuscarDocumentosParte;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.SQLException;
import java.util.ArrayList;

public class DocumentosParte extends AppCompatActivity implements  AdapterView.OnItemClickListener{


    private static ArrayList<Documento> arrayListDocumentos = new ArrayList<>();
    private ListView lvDocumentos;
    private Cliente cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_documentos_parte);
        try {
            cliente = ClienteDAO.buscarCliente(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        int fkParte= getIntent().getIntExtra("fk_parte",0);
        lvDocumentos = (ListView) findViewById(R.id.lvDocumentos);
        lvDocumentos.setOnItemClickListener(this);
        new HiloBuscarDocumentosParte(this,fkParte).execute();




    }


    public void sacarMensaje(String s) {
        Dialogo.dialogoError(s,this);
    }

    public void mostrarDocumentos(String mensaje, Context context) {
        try {
            JSONArray jsonArray = new JSONArray(mensaje);
            ArrayAdapter<String> adaptador;
            adaptador = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
            if (arrayListDocumentos != null) {
                if (arrayListDocumentos.size() != 0) {
                    arrayListDocumentos.clear();
                }
            }
            if (jsonArray.length() != 0) {
                for (int i = 0; i < jsonArray.length(); i++) {
                    String direccion = jsonArray.getJSONObject(i).getString("ruta");
                    String nombre = jsonArray.getJSONObject(i).getString("nombre");
                    Documento d = new Documento(nombre, direccion);
                    arrayListDocumentos.add(d);
                    adaptador.add(nombre);
                }
            } else {
                adaptador.add("PARTE SIN DOCUMENTOS");
            }
            lvDocumentos.setAdapter(adaptador);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId() == R.id.lvDocumentos) {
            if (!lvDocumentos.getItemAtPosition(position).toString().equals("NINGUNA COINCIDENCIA")) {
                if (!arrayListDocumentos.isEmpty()) {

                    String nombre= arrayListDocumentos.get(position).getNombre();
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://" + cliente.getIp_cliente() + "/uploaded/"+cliente.getDir_documentos()+"/modelos/" + arrayListDocumentos.get(position).getDireccion()));
                    startActivity(browserIntent);


                }
            }
        }
    }
}
