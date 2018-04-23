package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloIntervencionesAnteriores;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IntervencionesAnteriores extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private ListView lvIndex;
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias;
    private LinearLayout cuerpo;
    private AdaptadorPartes adaptadorPartes;
    private ArrayList<Parte> arrayListParte = new ArrayList<>();
    private HiloIntervencionesAnteriores hiloIntervencionesAnteriores;

    private void inicializarVariables() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        srl = (SwipeRefreshLayout) findViewById(R.id.lllistview);
        lvIndex = (ListView) findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
    }

    public void guardarPartes(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            JSONArray jsonArray = jsonObject.getJSONArray("partes");
            Log.d("Act. Intervenciones", String.valueOf(jsonObject));
            for (int i = 0; i < jsonArray.length() ; i++) {
                String tipo_via;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("via").equals("null")) {
                    tipo_via = "";
                } else {
                    tipo_via = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("via");
                }
                String via;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("direccion").equals("null")) {
                    via = "";
                } else {
                    via = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("direccion");
                }
                String numero_direccion;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero").equals("null")) {
                    numero_direccion = "";
                } else {
                    numero_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("numero");
                }
                String escalera_direccion;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("escalera").equals("null")) {
                    escalera_direccion = "";
                } else {
                    escalera_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("escalera");
                }
                String piso_direccion;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("piso").equals("null")) {
                    piso_direccion = "";
                } else {
                    piso_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("piso");
                }
                String puerta_direccion;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("puerta").equals("null")) {
                    puerta_direccion = "";
                } else {
                    puerta_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("puerta");
                }
                String cp_direccion;
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp").equals("null")) {
                    cp_direccion = "";
                } else {
                    cp_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("cp");
                    Log.d("COD POSTAL", cp_direccion);
                }
                String municipio_direccion="";
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("municipio").equals("null")) {
                    municipio_direccion = "";
                } else {
                    municipio_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("municipio");
                }
                String provincia_direccion="";
                if (jsonArray.getJSONObject(i).getJSONObject("direccion").getString("provincia").equals("null")) {
                    provincia_direccion = "";
                } else {
                    provincia_direccion = jsonArray.getJSONObject(i).getJSONObject("direccion").getString("provincia");
                }
                String nombre_cliente;
                if (jsonArray.getJSONObject(i).getJSONObject("cliente").getString("nombre_usuario").equals("null")) {
                    nombre_cliente = "";
                } else {
                    nombre_cliente = jsonArray.getJSONObject(i).getJSONObject("cliente").getString("nombre_usuario");
                }
                String fecha_aviso;
                if (jsonArray.getJSONObject(i).getString("fecha_aviso").equals("null")) {
                    fecha_aviso = "";
                } else {
                    fecha_aviso = jsonArray.getJSONObject(i).getString("fecha_aviso");
                }
                int fk_horario;
                if (jsonArray.getJSONObject(i).getString("fk_horario").equals("null") || jsonArray.getJSONObject(i).getString("fk_horario").equals("")) {
                    fk_horario = -1;
                } else {
                    fk_horario = jsonArray.getJSONObject(i).getInt("fk_horario");
                }
                String horario;
                if (jsonArray.getJSONObject(i).getString("horario").equals("null")) {
                    horario = "";
                } else {
                    horario = jsonArray.getJSONObject(i).getString("horario");
                }
                int id_parte;
                if (jsonArray.getJSONObject(i).getString("id_parte").equals("null") || jsonArray.getJSONObject(i).getString("id_parte").equals("")) {
                    id_parte = -1;
                } else {
                    id_parte = jsonArray.getJSONObject(i).getInt("id_parte");
                }
                Parte parte = new Parte();
                parte.setTipo_via(tipo_via);
                parte.setVia(via);
                parte.setNumero_direccion(numero_direccion);
                parte.setEscalera_direccion(escalera_direccion);
                parte.setPiso_direccion(piso_direccion);
                parte.setPuerta_direccion(puerta_direccion);
                parte.setMunicipio_direccion(municipio_direccion);
                parte.setProvincia_direccion(provincia_direccion);
                parte.setCp_direccion(cp_direccion);
                parte.setNombre_cliente(nombre_cliente);
                parte.setFecha_aviso(fecha_aviso);
                parte.setId_parte(id_parte);
                parte.setHorario(horario);
                parte.setEstado_android(4);
                arrayListParte.add(parte);
            }
            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
            lvIndex.setAdapter(adaptadorPartes);
            /*
            if (jsonObject.getInt("estado") == 1) {
                new GuardarParte(this, msg).execute();
            } else {
                sacarMensaje(jsonObject.getString("mensaje"));
            }
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intervenciones_aneriores);
        inicializarVariables();
        srl.setRefreshing(false);
        setTitle("Histórico de Intervenciones");
        Intent intent = getIntent();
        if (intent != null) {
            int id = intent.getIntExtra("id", 0);
            int fk_maquina = intent.getIntExtra("fk_maquina", 0);
            int fk_entidad = intent.getIntExtra("fk_entidad", 0);
            String ip_cliente = intent.getStringExtra("ip_cliente");

            hiloIntervencionesAnteriores = new HiloIntervencionesAnteriores(this,fk_entidad,fk_maquina,ip_cliente);
            hiloIntervencionesAnteriores.execute();
        }
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

    }
}
