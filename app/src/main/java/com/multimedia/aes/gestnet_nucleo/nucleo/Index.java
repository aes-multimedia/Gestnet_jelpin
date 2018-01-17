package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.Manifest;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.fragment.FragmentPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartesId;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPorFecha;
import com.multimedia.aes.gestnet_nucleo.notification.GcmIntentService;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioLocalizacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView lvIndex;
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias;
    private LinearLayout cuerpo;
    private AdaptadorPartes adaptadorPartes;
    private ArrayList<Parte> arrayListParte = new ArrayList<>();
    private static final int MY_PERMISSIONS_REQUEST_LOCATION = 2 ;

    //METODO
    private void introducirMaterialesPrueba() {

        try {
            if (ArticuloDAO.buscarTodosLosArticulos(this) == null) {
                ArticuloDAO.newArticulo(this, 1, "Pieza 1", 16, "9d54fg98dfg", "58d5fg8fd5", "familia", "marca", "modelo",
                        185, 21, 25, 6, 66, "8f8f8f8f", 66);
                ArticuloDAO.newArticulo(this, 1, "Pieza 2", 16, "9d54fg98dfg", "58d5fg8fd5", "familia", "marca", "modelo",
                        185, 21, 25, 6, 66, "8f8f8f8f", 66);
                ArticuloDAO.newArticulo(this, 1, "Pieza 3", 16, "9d54fg98dfg", "58d5fg8fd5", "familia", "marca", "modelo",
                        185, 21, 25, 6, 66, "8f8f8f8f", 66);
                ArticuloDAO.newArticulo(this, 1, "Pieza 4", 16, "9d54fg98dfg", "58d5fg8fd5", "familia", "marca", "modelo",
                        185, 21, 25, 6, 66, "8f8f8f8f", 66);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }
    private void inicializarVariables() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        srl = (SwipeRefreshLayout) findViewById(R.id.lllistview);
        srl.setOnRefreshListener(this);
        lvIndex = (ListView) findViewById(R.id.lvIndex);
        lvIndex.setOnItemClickListener(this);
        ivIncidencias = (ImageView) navigationView.getHeaderView(0).findViewById(R.id.ivIncidencias);
        ivIncidencias.setOnClickListener(this);
        cuerpo = (LinearLayout) findViewById(R.id.cuerpo);
    }
    public void sacarMensaje(String msg) {
        Dialogo.dialogoError(msg, this);
        if (ManagerProgressDialog.getDialog() != null) {
            ManagerProgressDialog.cerrarDialog();
        }
        srl.setRefreshing(false);
    }
    public void guardarPartes(String msg) {
        try {
            if (ManagerProgressDialog.getDialog() == null) {
                ManagerProgressDialog.abrirDialog(this);
            }
            ManagerProgressDialog.setMensaje(getResources().getString(R.string.guardar_datos));
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado") == 1) {
                new GuardarParte(this, msg);
            } else {
                sacarMensaje(jsonObject.getString("mensaje"));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void datosActualizados() {
        Intent intent = new Intent(this, Index.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
    //OVERRIDE
    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                }
                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        inicializarVariables();
        setTitle(R.string.averias);
        introducirMaterialesPrueba();
        int valor= getIntent().getIntExtra("iniciarServicioArticulos",0);
        if(valor==1)startService(new Intent(this, ServicioArticulos.class));
        int permissionLocation = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if (permissionLocation != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.ACCESS_FINE_LOCATION)) {
            } else {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }else{

        }

        try {
            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
        lvIndex.setAdapter(adaptadorPartes);
        Intent intent = getIntent();
        if (intent != null) {
            int metodo = intent.getIntExtra("metodo", 0);
            int notId = intent.getIntExtra("notiId", 0);
            int id =intent.getIntExtra("id", 0);
            switch (metodo){
                case 1:
                    break;
                case 2:
                    try {
                        Toast.makeText(this,"entra borrar",Toast.LENGTH_SHORT).show();
                        if(ParteDAO.buscarPartePorId(this,id)!=null) {
                            arrayListParte.clear();
                            int direccion=ParteDAO.buscarPartePorId(this,id).getFk_direccion();
                            ProtocoloAccionDAO.borrarProtocoloPorFkParte(this, id);
                            DatosAdicionalesDAO.borrarDatosAdicionalesPorFkParte(this, id);
                            MaquinaDAO.borrarMaquinaPorFkDireccion(this, direccion);
                            ParteDAO.borrarPartePorID(this, id);
                            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
                            }
                            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
                            lvIndex.setAdapter(adaptadorPartes);
                            Dialogo.dialogoError("Parte "+id+" borrado", this);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        Toast.makeText(this,"entra nuevo",Toast.LENGTH_SHORT).show();
                        Usuario u = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);
                        Cliente c = ClienteDAO.buscarTodosLosClientes(this).get(0);
                        new HiloPartesId(this, u.getFk_entidad(), id, c.getIp_cliente(), u.getApi_key());
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;
                case 4:


                    try {
                        Toast.makeText(this,"entra borrar ruta",Toast.LENGTH_SHORT).show();

                            arrayListParte.clear();

                            ProtocoloAccionDAO.borrarTodosLosProtocolo(this);
                            DatosAdicionalesDAO.borrarTodosLosDatosAdicionales(this);
                            MaquinaDAO.borrarTodasLasMaquinas(this);
                            ParteDAO.borrarTodosLosPartes(this);
                            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
                            lvIndex.setAdapter(adaptadorPartes);
                            Dialogo.dialogoError("ruta borrada", this);

                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    break;


            }

            if (notId != 0) {
                GcmIntentService.cerrarNotificacion(notId);
            }

        }

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

        int id = item.getItemId();
        if (id == R.id.averias) {
            recreate();
        } else if (id == R.id.documentos) {

        } else if (id == R.id.almacen) {

        }else if (id == R.id.cierre_dia) {
            Intent i = new Intent(this,CierreDia.class);
            startActivity(i);
        } else if (id == R.id.cambiarFecha) {
            try {

                final Usuario u = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);
                final Cliente c = ClienteDAO.buscarTodosLosClientes(this).get(0);
                List<Parte> part = ParteDAO.buscarTodosLosPartes(this);
                //if (part==null){
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH)+1;
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        selectedmonth = selectedmonth + 1;
                        String day = selectedday + "";
                        String month = selectedmonth+1 + "";
                        if (selectedday < 10) {
                            day = "0" + selectedday;
                        }
                        if (selectedmonth < 10) {
                            month = "0" + selectedmonth;
                        }
                        String year = selectedyear + "";
                        JSONObject js = new JSONObject();
                        try {
                            js.put("dia", day + "-" + month + "-" + year);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        String fecha = year + "-" + month + "-" + day;

                        new HiloPorFecha(Index.this, u.getFk_entidad(), fecha, c.getIp_cliente()).execute();
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else if (id == R.id.cerrar_sesion) {
            try {
                stopService(new Intent(this, ServicioLocalizacion.class));
                BBDDConstantes.borrarDatosTablas(this);
                Intent i = new Intent(this, PreLogin.class);
                startActivity(i);
                finish();
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
            jsonObject.put("id", (String) view.getTag());
            GestorSharedPreferences.clearSharedPreferencesParte(this);
            GestorSharedPreferences.setJsonParte(GestorSharedPreferences.getSharedPreferencesMantenimiento(this), jsonObject);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        srl.setVisibility(View.GONE);
        Class fragmentClass = FragmentPartes.class;
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
    }
    @Override
    public void onRefresh() {
        try {
            Usuario u = UsuarioDAO.buscarTodosLosUsuarios(this).get(0);
            Cliente c = ClienteDAO.buscarTodosLosClientes(this).get(0);
            srl.setRefreshing(true);
            new HiloPartes(this, u.getFk_entidad(), c.getIp_cliente(), u.getApi_key()).execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @Override
    public void onClick(View v) {
    }


}
