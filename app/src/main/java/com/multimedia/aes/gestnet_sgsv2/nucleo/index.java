package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.dao.AveriaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;

import java.sql.SQLException;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private TextView txtTecnico,txtMantenimiento,txtAveria;
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

        txtMantenimiento = (TextView)findViewById(R.id.txtMantenimiento);
        txtAveria = (TextView)findViewById(R.id.txtAveria);
        txtTecnico = (TextView)findViewById(R.id.txtTecnico);
        try {
            txtMantenimiento.setText(MantenimientoDAO.buscarTodosLosMantenimientos(this).toString());
            txtAveria.setText(AveriaDAO.buscarTodasLasAverias(this).toString());
            txtTecnico.setText(TecnicoDAO.buscarTodosLosTecnicos(this).toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
