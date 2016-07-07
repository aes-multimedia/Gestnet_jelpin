package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorMantenimientos;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.fragment.FragmentMantenimiento;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView lvIndex;
    private AdaptadorMantenimientos adaptadorMantenimientos;
    private ArrayList<Mantenimiento> arrayList=new ArrayList();
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias,ivLlamarAes;
    private LinearLayout cuerpo;
    private boolean entra = false;
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

        try {
            arrayList = new ArrayList(MantenimientoDAO.buscarTodosLosMantenimientos(this));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        srl = (SwipeRefreshLayout)findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        adaptadorMantenimientos = new AdaptadorMantenimientos(this,R.layout.camp_adapter_list_view_mantenimiento,arrayList);
        lvIndex = (ListView)findViewById(R.id.lvIndex);

        ivIncidencias = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivLlamarAes = (ImageView)navigationView.getHeaderView(0).findViewById(R.id.ivLlamarAes);
        ivIncidencias.setOnClickListener(this);
        ivLlamarAes.setOnClickListener(this);
        lvIndex.setAdapter(adaptadorMantenimientos);
        lvIndex.setOnItemClickListener(this);
        cuerpo = (LinearLayout)findViewById(R.id.cuerpo);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            if (entra){
                recreate();
            }else{
                super.onBackPressed();
            }

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.documentos) {
        } else if (id == R.id.averias) {

        } else if (id == R.id.almacen) {

        } else if (id == R.id.buscar_parte) {

        } else if (id == R.id.ajustes) {

        } else if (id == R.id.cerrar_sesion) {
            try {
                BBDDConstantes.borrarDatosTablas(this);
                Intent i = new Intent(this,Login.class);
                startActivity(i);
                finish();
                GestorSharedPreferences.clearSharedPreferencesTecnico(this);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("id",(String) view.getTag());
            GestorSharedPreferences.setJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(this),jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        cuerpo.removeAllViews();
        Class fragmentClass = FragmentMantenimiento.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        entra=true;
    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(true);
        adaptadorMantenimientos = new AdaptadorMantenimientos(this,R.layout.camp_adapter_list_view_mantenimiento,arrayList);
        lvIndex.setAdapter(adaptadorMantenimientos);
        srl.setRefreshing(false);
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.ivIncidencias){
            Intent i = new Intent(this,Incidencias.class);
            startActivity(i);
        }else if (view.getId()==R.id.ivLlamarAes){
            Toast.makeText(Index.this, "llamar", Toast.LENGTH_SHORT).show();
        }
    }
    public void activar(){
        cuerpo.removeAllViews();
        Class fragmentClass = FragmentMantenimiento.class;
        Fragment fragment;
        try {
            fragment = (Fragment) fragmentClass.newInstance();
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.cuerpo, fragment).commit();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        entra=true;
    }

}
