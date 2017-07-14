package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
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

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarCliente;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarUsuario;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloLogin;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView lvIndex;
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias;
    private LinearLayout cuerpo;
    private AdaptadorPartes adaptadorAverias;
    private ArrayList<Parte> arrayListAveria = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        setTitle("Averias");
        srl = (SwipeRefreshLayout) findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        lvIndex = (ListView) findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
        ivIncidencias = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivIncidencias.setOnClickListener(this);
        cuerpo = (LinearLayout) findViewById(R.id.cuerpo);
      //  Parte a = new Parte(1,"Calle Falsa 123","25698","Madrid-Madrid");
      //  arrayListAveria.add(a);
       // adaptadorAverias = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_averia, arrayListAveria);
    //    lvIndex.setAdapter(adaptadorAverias);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            recreate();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        /*if (id == R.id.documentos) {
        } else if (id == R.id.averias) {
            /*JSONObject jsonObject = new JSONObject();
            try {
                jsonObject.put("parte", 1);
                GestorSharedPreferences.clearSharedPreferencesPartes(this);
                GestorSharedPreferences.setJsonPartes(GestorSharedPreferences.getSharedPreferencesPartes(this), jsonObject);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            recreate();
        /*} else if (id == R.id.almacen) {*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public void sacarMensaje(String msg) {
        Dialogo.dialogoError(msg,this);
    }
    public void guardarParte(String msg){
        try {
            JSONObject jsonObject = new JSONObject(msg);
            Log.d("prueba",jsonObject.getString("usuario"));
            int estado = jsonObject.getInt("estado");
            if (estado==1){
                new GuardarParte(this,msg);
            }else{
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JSONObject jsonObject = new JSONObject();
    }

    @Override
    public void onRefresh() {
    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.btnLogin){
            new HiloLogin(etUsuario.getText().toString().trim(),etContraseña.getText().toString().trim(),this).execute();
        }
    }
}
