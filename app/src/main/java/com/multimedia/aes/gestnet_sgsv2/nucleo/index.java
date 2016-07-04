package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.os.Bundle;
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
import android.widget.ListView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorMantenimientos;
import com.multimedia.aes.gestnet_sgsv2.constants.BBDDConstantes;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;

import java.sql.SQLException;
import java.util.ArrayList;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener {

    private ListView lvIndex;
    private AdaptadorMantenimientos adaptadorMantenimientos;
    private ArrayList<Mantenimiento> arrayList=new ArrayList();
    private SwipeRefreshLayout srl;
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
        lvIndex.setAdapter(adaptadorMantenimientos);
        lvIndex.setOnItemClickListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onStop() {
        try {
            BBDDConstantes.borrarDatosTablas(this);
            super.onStop();
        } catch (SQLException e) {
            e.printStackTrace();
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

    }

    @Override
    public void onRefresh() {
        srl.setRefreshing(true);
        adaptadorMantenimientos = new AdaptadorMantenimientos(this,R.layout.camp_adapter_list_view_mantenimiento,arrayList);
        lvIndex.setAdapter(adaptadorMantenimientos);
        srl.setRefreshing(false);
    }
}
