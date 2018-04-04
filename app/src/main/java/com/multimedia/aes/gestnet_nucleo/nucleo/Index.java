package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
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

import com.multimedia.aes.gestnet_nucleo.BBDD.GuardarParte;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorPartes;
import com.multimedia.aes.gestnet_nucleo.constantes.BBDDConstantes;
import com.multimedia.aes.gestnet_nucleo.dao.ArticuloDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.EnvioDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ProtocoloAccionDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Envio;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.fragment.FragmentImpresion;
import com.multimedia.aes.gestnet_nucleo.fragment.FragmentPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloNoEnviados;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartes;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPartesId;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloPorFecha;
import com.multimedia.aes.gestnet_nucleo.notification.GcmIntentService;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioArticulos;
import com.multimedia.aes.gestnet_nucleo.servicios.ServicioLocalizacion;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Index extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemClickListener, SwipeRefreshLayout.OnRefreshListener, View.OnClickListener {

    private ListView lvIndex;
    private SwipeRefreshLayout srl;
    private ImageView ivIncidencias;
    private LinearLayout cuerpo;
    private AdaptadorPartes adaptadorPartes;
    private ArrayList<Parte> arrayListParte = new ArrayList<>();
    private String fecha;

    //METODO
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
        srl.setRefreshing(false);
    }
    public void guardarPartes(String msg) {
        try {
            JSONObject jsonObject = new JSONObject(msg);
            if (jsonObject.getInt("estado") == 1) {
                new GuardarParte(this, msg).execute();
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
    public void impresion() {
        Class fragmentClass = FragmentImpresion.class;
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
    private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
    //OVERRIDE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);
        inicializarVariables();
        fecha = getDateTime();
        setTitle("Averias"+" "+fecha);
        try {
            if (ArticuloDAO.buscarTodosLosArticulos(this) == null) {
                startService(new Intent(this, ServicioArticulos.class));
            }
            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        JSONObject jo= new JSONObject();
        String dia = null;
        try {
            jo = GestorSharedPreferences.getJsonDia(GestorSharedPreferences.getSharedPreferencesDia(this));
            dia = jo.getString("dia");
            fecha = dia;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
        lvIndex.setAdapter(adaptadorPartes);
        Intent intent = getIntent();
        if (intent != null) {
            int metodo = intent.getIntExtra("metodo", 0);
            int notId = intent.getIntExtra("notiId", 0);
            int id = intent.getIntExtra("id", 0);
            switch (metodo) {
                case 1:
                    break;
                case 2:
                    try {
                        if (ParteDAO.buscarPartePorId(this, id) != null) {
                            arrayListParte.clear();
                            int direccion = ParteDAO.buscarPartePorId(this, id).getFk_direccion();
                            ProtocoloAccionDAO.borrarProtocoloPorFkParte(this, id);
                            DatosAdicionalesDAO.borrarDatosAdicionalesPorFkParte(this, id);
                            MaquinaDAO.borrarMaquinaPorFkDireccion(this, direccion);
                            ParteDAO.borrarPartePorID(this, id);
                            if (ParteDAO.buscarTodosLosPartes(this) != null) {
                                arrayListParte.addAll(ParteDAO.buscarTodosLosPartes(this));
                            }
                            adaptadorPartes = new AdaptadorPartes(this, R.layout.camp_adapter_list_view_parte, arrayListParte);
                            lvIndex.setAdapter(adaptadorPartes);
                            Dialogo.dialogoError("Parte " + id + " borrado", this);
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 3:
                    try {
                        Usuario u = UsuarioDAO.buscarUsuario(this);
                        Cliente c = ClienteDAO.buscarCliente(this);
                        new HiloPartesId(this, u.getFk_entidad(), id, c.getIp_cliente(), u.getApi_key()).execute();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    break;
                case 4:
                    try {
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
        } else if (id == R.id.mis_ajustes) {
            Intent i = new Intent(this, MisAjustes.class);
            startActivity(i);
        } else if (id == R.id.cierre_dia) {
            Intent i = new Intent(this, CierreDia.class);
            startActivity(i);
        } else if (id == R.id.cambiar_fecha) {
            try {
                final Usuario u = UsuarioDAO.buscarUsuario(this);
                final Cliente c = ClienteDAO.buscarCliente(this);
                List<Parte> part = ParteDAO.buscarTodosLosPartes(this);
                //if (part==null){
                Calendar mcurrentDate = Calendar.getInstance();
                int mYear = mcurrentDate.get(Calendar.YEAR);
                int mMonth = mcurrentDate.get(Calendar.MONTH);
                int mDay = mcurrentDate.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog mDatePicker;
                mDatePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker datepicker, int selectedyear, int selectedmonth, int selectedday) {
                        String day = selectedday + "";
                        String month = selectedmonth + "";
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
                        GestorSharedPreferences.setJsonDia(GestorSharedPreferences.getSharedPreferencesDia(Index.this),js);
                        try {
                            BBDDConstantes.borrarDatosTablasPorDia(Index.this);
                            GestorSharedPreferences.clearSharedPreferencesParte(Index.this);
                            String fecha = year+"-"+month+"-"+day;
                            new HiloPorFecha(Index.this, u.getFk_entidad(), fecha, c.getIp_cliente()).execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                    }
                }, mYear, mMonth, mDay);
                mDatePicker.setTitle("Select Date");
                mDatePicker.show();

            } catch (SQLException e) {
                e.printStackTrace();
            }


        } else if (id == R.id.cierres_pendientes) {
            AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
            builder1.setMessage("¿Se enviaran todos los cambios pendientes, desea continuar?");
            builder1.setCancelable(true);
            builder1.setPositiveButton(
                    "Enviar cambios.",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            try {
                                if (EnvioDAO.buscarTodosLosEnvios(Index.this)!=null){
                                    List<Envio> envios = EnvioDAO.buscarTodosLosEnvios(Index.this);
                                    for (Envio envio : envios) {
                                        new HiloNoEnviados(Index.this,envio.getId_envio()).execute();
                                    }
                                }else{
                                    AlertDialog.Builder builder1 = new AlertDialog.Builder(Index.this);
                                    builder1.setMessage("No hay Cambios pendientes.");
                                    builder1.setCancelable(true);
                                    builder1.setPositiveButton(
                                            "Aceptar",
                                            new DialogInterface.OnClickListener() {
                                                public void onClick(DialogInterface dialog, int id) {
                                                    dialog.cancel();
                                                }
                                            });
                                    AlertDialog alert11 = builder1.create();
                                    alert11.setCanceledOnTouchOutside(false);
                                    alert11.show();
                                }
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

        }else if (id == R.id.cerrar_sesion) {
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
            GestorSharedPreferences.setJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this), jsonObject);
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
            Usuario u = UsuarioDAO.buscarUsuario(this);
            Cliente c = ClienteDAO.buscarCliente(this);
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
