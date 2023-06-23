package com.multimedia.aes.gestnet_ssl.nucleo;

import android.app.Activity;
import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.adaptador.AdaptadorDetallePartes;
import com.multimedia.aes.gestnet_ssl.clases.DataDetallePartes;
import com.multimedia.aes.gestnet_ssl.dialogo.Dialogo;
import com.multimedia.aes.gestnet_ssl.hilos.HiloBuscarPartes;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class BuscadorPartes extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ImageView ivBuscar,ivAtras;
    private EditText etBuscar;
    private ListView lvPartes;
    private AdaptadorDetallePartes adaptadorPartes;
    private ArrayList<DataDetallePartes> arrayListParte = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.buscador_partes);
        ivBuscar = findViewById(R.id.ivBuscar);
        ivAtras = findViewById(R.id.ivAtras);
        etBuscar = findViewById(R.id.etBuscar);
        lvPartes = findViewById(R.id.lvPartes);
        lvPartes.setOnItemClickListener(this);

        ivBuscar.setOnClickListener(this);
        ivAtras.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.ivBuscar){
            if (etBuscar.getText().toString().trim().length()<1){
                Dialogo.dialogoError("Es necesario escribir una cadena de al menos 1 caracter.",this);
            }else{
                String parte = etBuscar.getText().toString().trim();
                new HiloBuscarPartes(this,parte).execute();
            }
        }else if (view.getId()==R.id.ivAtras){
            Intent returnIntent = new Intent();
            setResult(3, returnIntent);
            finish();
        }
    }
    private void llenarLista(){
        adaptadorPartes = new AdaptadorDetallePartes(this, R.layout.camp_adapter_list_view_detalle_parte, arrayListParte);
        lvPartes.setAdapter(adaptadorPartes);
    }
    public void llenarArray(String mensaje) throws JSONException {
        JSONObject json = new JSONObject(mensaje);
        JSONArray jsonArray = json.getJSONArray("partes");
        if (jsonArray.length()!=0){
            for (int i = 0; i < jsonArray.length(); i++) {
                int id = jsonArray.getJSONObject(i).getInt("id_parte");
                String direccion = jsonArray.getJSONObject(i).getString("direccion");
                String numContrato = jsonArray.getJSONObject(i).getString("num_parte");
                String ordenEndesa = jsonArray.getJSONObject(i).getString("num_orden_endesa");
                String nombreCliente = jsonArray.getJSONObject(i).getString("nombre_usuario");
                DataDetallePartes d = new DataDetallePartes(id,direccion,numContrato,ordenEndesa,nombreCliente);
                arrayListParte.add(d);
            }
            llenarLista();
        }else{
            Dialogo.dialogoError("No se ha encontrado ninguna coincidencia.",this);
        }

    }
    public void error(){
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_CANCELED, returnIntent);
        finish();
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        int id = Integer.parseInt(String.valueOf(view.getTag()));
        Intent returnIntent = new Intent();
        returnIntent.putExtra("result",id);
        setResult(Activity.RESULT_OK,returnIntent);
        finish();
    }
}
