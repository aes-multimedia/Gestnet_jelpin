package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TabWidget;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaAnalisis;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaMaquinas;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.fragments.TabFragment2_equipo;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloActualizaMaquina;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloCrearMaquina;
import com.multimedia.aes.gestnet_nucleo.progressDialog.ManagerProgressDialog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class AnadirDatosMaquina extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private static Spinner spMarca, spPuestaMarcha;
    private static EditText etModelo, etTempMaxACS, etCaudalACS, etPotenciaUtil,
            etTempGasesComb, etTempAmbienteLocal, etTempAguaGeneCalorEntrada,
            etTempAguaGeneCalorSalida,etNumeroSerie;
    private Button btnAñadirMaquina,btnDatosTesto,btnVerDocumentosModelo;
    private ArrayList<Marca> arrayListMarcas= new ArrayList<>();
    private static ListView lvAnalisis;
    private static int alto1=0,height=0;
    private String[] arrayMarcas,puestaMarcha;
    private static Parte parte = null;
    private Usuario usuario = null;
    private static Maquina maquina = null;
    private static ArrayList<Analisis>  arrayListAnalisis = new ArrayList<>();
    private static AdaptadorListaAnalisis adaptadorListaAnalisis;
    private static Activity activity;
    private String serialNumber;
    private static Cliente cliente;
    private static String webUrl="";
    private static int id;

    //METODOS
    private void darValores(){
        try {
            cliente= ClienteDAO.buscarCliente(this);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        alto1=0;
        //SPINNER MARCAS
        try {
            if (MarcaDAO.buscarTodasLasMarcas(this)!=null){
                try {
                    arrayListMarcas.addAll(MarcaDAO.buscarTodasLasMarcas(this));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int indice=0;
                arrayMarcas = new String[arrayListMarcas.size() + 1];
                arrayMarcas[0]= "--Seleciones un valor--";
                for (int i = 1; i < arrayListMarcas.size() + 1; i++) {
                    if (arrayListMarcas.get(i - 1).getId_marca()!=0||arrayListMarcas.get(i - 1).getId_marca()!=-1){
                        arrayMarcas[i] = arrayListMarcas.get(i - 1).getNombre_marca();
                    }
                }
                spMarca.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
            }else {
                arrayMarcas= new String[1];
                arrayMarcas[0]= "SIN MARCA";
                spMarca.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
            }
            String marca = null;
            if (MarcaDAO.buscarMarcaPorId(this, maquina.getFk_marca()) != null) {
                marca = MarcaDAO.buscarMarcaPorId(this, maquina.getFk_marca()).getNombre_marca();
            }
            if (marca != null) {
                String myString = marca;
                ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spMarca.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Dialogo.dialogoError("ERROR EN LAS MAQUINAS",this);
        }
        //SPINNER PUESTA MARCHA
        Date d = new Date();
        String s = String.valueOf(DateFormat.format("yyyy", d.getTime()));
        int año = Integer.parseInt(s);
        puestaMarcha = new String[30];
        puestaMarcha[0] = "--Seleccione un valor--";
        int a = 1;
        for (int i = año; i >= año - 28; i--) {
            puestaMarcha[a] = String.valueOf(i);
            a++;
        }
        spPuestaMarcha.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, puestaMarcha));

        String puesta = null;
        if (maquina.getPuesta_marcha().equals("null") || maquina.getPuesta_marcha().equals("")) {
        } else {
            puesta = maquina.getPuesta_marcha();
            puesta = puesta.substring(0, 4);
        }
        if (puesta != null) {
            String myString = puesta;
            ArrayAdapter myAdap = (ArrayAdapter) spPuestaMarcha.getAdapter();
            int spinnerPosition = myAdap.getPosition(myString);
            spPuestaMarcha.setSelection(spinnerPosition);
        }
        etNumeroSerie.setText(maquina.getNum_serie());
        etModelo.setText(maquina.getModelo());

    }
    public void inicializarVariables(){
        //SPINNER
        spMarca = (Spinner)findViewById(R.id.spMarca);
        spPuestaMarcha = (Spinner)findViewById(R.id.spPuestaMarcha);
        //EDITTEXT
        etModelo = (EditText)findViewById(R.id.etModelo);
        etTempMaxACS = (EditText)findViewById(R.id.etTempMaxACS);
        etCaudalACS = (EditText)findViewById(R.id.etCaudalACS);
        etPotenciaUtil = (EditText)findViewById(R.id.etPotenciaUtil);
        etTempAguaGeneCalorEntrada = (EditText)findViewById(R.id.etTempAguaGeneCalorEntrada);
        etTempAguaGeneCalorSalida = (EditText)findViewById(R.id.etTempAguaGeneCalorSalida);
        etNumeroSerie = (EditText)findViewById(R.id.etNumSerie);
        //BUTTON
        btnAñadirMaquina = (Button)findViewById(R.id.btnAñadirMaquina);
        btnDatosTesto = (Button)findViewById(R.id.btnDatosTesto);

        btnVerDocumentosModelo=(Button)findViewById(R.id.btnVerDocumentosModelo);

        //LISTVIEW
        lvAnalisis = (ListView)findViewById(R.id.lvAnalisis);
        lvAnalisis.setOnItemClickListener(this);
        //ONCLICKLISTENER
        btnAñadirMaquina.setOnClickListener(this);
        btnDatosTesto.setOnClickListener(this);
        btnVerDocumentosModelo.setOnClickListener(this);

        activity = this;
    }

    private static void ponerValores(){
        try {
            //SPINNER PUESTA EN MARCHA
            if (maquina!=null){
                String puesta = null;
                if (maquina.getPuesta_marcha()!=null){
                    if (maquina.getPuesta_marcha().equals("null") || maquina.getPuesta_marcha().equals("")) {
                    } else {
                        puesta = maquina.getPuesta_marcha();
                        puesta = puesta.substring(0, 4);
                    }
                    if (puesta != null) {
                        String myString = puesta;
                        ArrayAdapter myAdap = (ArrayAdapter) spPuestaMarcha.getAdapter();
                        int spinnerPosition = myAdap.getPosition(myString);
                        spPuestaMarcha.setSelection(spinnerPosition);
                    }
                }

            }
            //SPINNER MARCAS
            if (maquina!=null){
                String marca = null;
                if (MarcaDAO.buscarMarcaPorId(activity, maquina.getFk_marca()) != null) {
                    marca = MarcaDAO.buscarMarcaPorId(activity, maquina.getFk_marca()).getNombre_marca();
                }
                if (marca != null) {
                    String myString = marca;
                    ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
                    int spinnerPosition = myAdap.getPosition(myString);
                    spMarca.setSelection(spinnerPosition);
                }
            }
            if (!String.valueOf(maquina.getModelo()).equals("")&&!String.valueOf(maquina.getModelo()).equals("0")){
                etModelo.setText(String.valueOf(maquina.getModelo()));
            }
            if (!String.valueOf(maquina.getTemperatura_max_acs()).equals("")&&!String.valueOf(maquina.getTemperatura_max_acs()).equals("0")){
                etTempMaxACS.setText(String.valueOf(maquina.getTemperatura_max_acs()));
            }
            if (!String.valueOf(maquina.getCaudal_acs()).equals("")&&!String.valueOf(maquina.getCaudal_acs()).equals("0")){
                etCaudalACS.setText(String.valueOf(maquina.getCaudal_acs()));
            }
            if (!String.valueOf(maquina.getPotencia_util()).equals("")&&!String.valueOf(maquina.getPotencia_util()).equals("0")){
                etPotenciaUtil.setText(String.valueOf(maquina.getPotencia_util()));
            }
            if (!String.valueOf(maquina.getTemperatura_agua_generador_calor_entrada()).equals("")&&!String.valueOf(maquina.getTemperatura_agua_generador_calor_entrada()).equals("0")){
                etTempAguaGeneCalorEntrada.setText(String.valueOf(maquina.getTemperatura_agua_generador_calor_entrada()));
            }
            if (!String.valueOf(maquina.getTemperatura_agua_generador_calor_salida()).equals("")&&!String.valueOf(maquina.getTemperatura_agua_generador_calor_salida()).equals("0")){
                etTempAguaGeneCalorSalida.setText(String.valueOf(maquina.getTemperatura_agua_generador_calor_salida()));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void añadirAnalisis(){
        try {
            arrayListAnalisis.clear();
            List<Analisis> a = AnalisisDAO.buscarAnalisisPorFkMaquina(this,maquina.getId_maquina());
            if (a!=null) {
                alto1 =height * a.size();
                arrayListAnalisis.addAll(a);
                lvAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto1));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(this, R.layout.camp_adapter_list_view_analisis_solicitud, arrayListAnalisis);
                lvAnalisis.setAdapter(adaptadorListaAnalisis);
                lvAnalisis.setVisibility(View.VISIBLE);
            }else{
                alto1 =0;
                lvAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto1));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(this, R.layout.camp_adapter_list_view_analisis_solicitud, arrayListAnalisis);
                lvAnalisis.setAdapter(adaptadorListaAnalisis);
                lvAnalisis.setVisibility(View.VISIBLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    //OVERRIDE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_datos_maquina);
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(this));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            parte = ParteDAO.buscarPartePorId(this, idParte);
            usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(this,parte.getFk_tecnico());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        id = getIntent().getIntExtra("id",-1);
        if (id ==-1){

            int fkMaquina= calcularMaquina();
            if(fkMaquina<0)fkMaquina=-1*fkMaquina;




            maquina = MaquinaDAO.newMaquinaRet(this,fkMaquina,parte.getFk_direccion(),0,
                    0,"",0,0,0,0,
                    0,0, 0,0,0,"",
                    "","","","","","",
                    "","","", "","",
                    "","","",false,"",
                    "","","","","","",
                    "","","SIN COMBUSTIBLE","","");
        }else{
            try {
                maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(this,id);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        inicializarVariables();
        darValores();
        ponerValores();
        Display display = getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/8;
        arrayListAnalisis.clear();
        añadirAnalisis();
    }

    private int calcularMaquina() {

        List<Maquina> maquinaList=null;
        try {
             maquinaList = MaquinaDAO.buscarMaquinaPorFkParte(activity,parte.getId_parte());
        } catch (SQLException e) {
            e.printStackTrace();
        }
            if(maquinaList!=null)return buscarUnFkRecursivo(maquinaList);
            else return 0;
    }

    private int buscarUnFkRecursivo(List<Maquina> maquinaList) {
        int rand = new Random().nextInt(10000000-9000000)*-1  + 9000000;
        int i=0;
        boolean esta=false;
        while(i<maquinaList.size() && !esta){
            if(maquinaList.get(i).getFk_maquina()==rand)esta=true;
            i++;
        }
        if(esta)return buscarUnFkRecursivo(maquinaList);
        else return rand;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==103 && resultCode==RESULT_OK) {
            añadirAnalisis();
            serialNumber = data.getStringExtra("serial");
            try {
                MaquinaDAO.actualizaNumeroSerie(this,maquina.getId_maquina(),serialNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else if(requestCode==104 && resultCode==RESULT_OK) {
            Log.d("Act. Result", "Intervenciones anteriores");
        }

    }
    public static void abrirWebView(String direccion){
        try {
            JSONArray jsonArray = new JSONArray(direccion);
            String documento = jsonArray.getJSONObject(0).getString("documento");
            webUrl = "http://" + cliente.getIp_cliente() + "/uploaded/sanguesa/modelos/" + documento;
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==btnVerDocumentosModelo.getId()){
            if(maquina.getDocumento_modelo().equals("")){
                Dialogo.dialogoError("Esta maquina no tiene documentos",this);
            }else {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(webUrl = "http://" + cliente.getIp_cliente() + "/uploaded/"+cliente.getDir_documentos()+"/modelos/" + maquina.getDocumento_modelo()));
                startActivity(browserIntent);
            }
        } else if (view.getId() == btnDatosTesto.getId()) {
                Intent i = new Intent(this, AnadirDatosAnalisis.class);
                i.putExtra("id", parte.getId_parte());
                i.putExtra("fkMaquina", maquina.getId_maquina());
                startActivityForResult(i, 103);
        } else if (view.getId() == btnAñadirMaquina.getId()) {
            if (spMarca.getSelectedItemPosition() == 0)
                Dialogo.dialogoError("Es necesario seleccionar una marca", this);
            else if (spPuestaMarcha.getSelectedItemPosition() == 0)
                Dialogo.dialogoError("Es necesario seleccionar una puesta en marcha", this);
            else if (etModelo.getText().length() == 0)
                Dialogo.dialogoError("Es necesario introducir el modelo", this);
            else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
                if(id == -1) {
                    builder1.setMessage("¿Seguro que desea añadir esta máquina?");
                } else {
                    builder1.setMessage("¿Seguro que desea modificar esta máquina?");
                }
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Si",
                        (dialog, which) -> {
                            try {
                                int fk_maquina = maquina.getFk_maquina();
                                int fk_parte = parte.getId_parte();
                                int fk_direccion = parte.getFk_direccion();
                                int fk_marca = MarcaDAO.buscarIdMarcaPorNombre(AnadirDatosMaquina.this,spMarca.getSelectedItem().toString());
                                String fk_tipo_combustion ="";
                                int fk_protocolo = 0;
                                int fk_instalador = 0;
                                int fk_remoto_central = 0;
                                int fk_tipo = 0;
                                int fk_instalacion = 0;
                                int fk_estado = 0;
                                int fk_contrato_mantenimiento = 0;
                                int fk_gama = 0;
                                int fk_tipo_gama = 0;
                                String fecha_creacion = "";
                                String modelo = etModelo.getText().toString();
                                String num_serie = etNumeroSerie.getText().toString();
                                String num_producto = "";
                                String aparato = "";
                                String puesta_marcha = spPuestaMarcha.getSelectedItem().toString()+"-01-01";
                                String fecha_compra = "";
                                String fecha_fin_garantia = "";
                                String mantenimiento_anual = "";
                                String observaciones = "";
                                String ubicacion = "";
                                String tienda_compra = "";
                                String garantia_extendida = "";
                                String factura_compra = "";
                                String refrigerante = "";
                                boolean bEsInstalacion = false;
                                String nombre_instalacion = "";
                                String en_propiedad = "";
                                String esPrincipal = "";
                                String situacion = "";
                                String temperatura_max_acs = etTempMaxACS.getText().toString();
                                String caudal_acs = etCaudalACS.getText().toString();
                                String potencia_util = etPotenciaUtil.getText().toString();
                                String temperatura_agua_generador_calor_entrada = etTempAguaGeneCalorEntrada.getText().toString();
                                String temperatura_agua_generador_calor_salida = etTempAguaGeneCalorSalida.getText().toString();
                                String combustible_txt = "SIN COMBUSTIBLE";
                                String nombre_contr_man = "";
                                String documento_modelo="";
                                if (id!=-1){
                                    MaquinaDAO.actualizarMaquina(AnadirDatosMaquina.this,
                                            fk_maquina,fk_parte, fk_direccion, fk_marca,
                                            modelo, num_serie,  puesta_marcha,  temperatura_max_acs, caudal_acs,
                                            potencia_util, temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida);

                                    new HiloActualizaMaquina( fk_maquina,fk_parte, fk_direccion, fk_marca,
                                            modelo, num_serie,  puesta_marcha,  temperatura_max_acs, caudal_acs,
                                            potencia_util, temperatura_agua_generador_calor_entrada, temperatura_agua_generador_calor_salida).execute();
                                }else{
                                    MaquinaDAO.newMaquina(AnadirDatosMaquina.this,
                                            fk_maquina, fk_parte, fk_direccion, fk_marca, fk_tipo_combustion,
                                            fk_protocolo, fk_instalador, fk_remoto_central, fk_tipo, fk_instalacion,
                                            fk_estado, fk_contrato_mantenimiento, fk_gama, fk_tipo_gama,
                                            fecha_creacion, modelo, num_serie, num_producto, aparato,
                                            puesta_marcha, fecha_compra, fecha_fin_garantia,
                                            mantenimiento_anual, observaciones, ubicacion, tienda_compra,
                                            garantia_extendida, factura_compra, refrigerante,
                                            bEsInstalacion, nombre_instalacion, en_propiedad, esPrincipal, situacion,
                                            temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada,
                                            temperatura_agua_generador_calor_salida,combustible_txt,nombre_contr_man,documento_modelo
                                    );
                                    new HiloCrearMaquina(fk_maquina, fk_parte, fk_direccion, fk_marca, fk_tipo_combustion,
                                            fk_protocolo, fk_instalador, fk_remoto_central, fk_tipo, fk_instalacion,
                                            fk_estado, fk_contrato_mantenimiento, fk_gama, fk_tipo_gama,
                                            fecha_creacion, modelo, num_serie, num_producto, aparato,
                                            puesta_marcha, fecha_compra, fecha_fin_garantia,
                                            mantenimiento_anual, observaciones, ubicacion, tienda_compra,
                                            garantia_extendida, factura_compra, refrigerante,
                                            bEsInstalacion, nombre_instalacion, en_propiedad, esPrincipal, situacion,
                                            temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada,
                                            temperatura_agua_generador_calor_salida).execute();
                                }
                                etModelo.setText("");
                                etTempMaxACS.setText("");
                                etCaudalACS.setText("");
                                etPotenciaUtil.setText("");
                                etTempAguaGeneCalorEntrada.setText("");
                                etTempAguaGeneCalorSalida.setText("");
                                etNumeroSerie.setText("");
                                spMarca.setSelection(0);
                                spPuestaMarcha.setSelection(0);
                                if(id == -1) {
                                    Dialogo.dialogoError("Maquina añadida", AnadirDatosMaquina.this);
                                } else {
                                    Dialogo.dialogoError("Maquina modificada", AnadirDatosMaquina.this);
                                }

                                TabFragment2_equipo.añadirMaquina();
                                finish();
                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                            dialog.cancel();
                        });
                builder1.setNegativeButton(
                        "No",
                        (dialog, which) -> dialog.cancel());
                AlertDialog alert11 = builder1.create();
                alert11.setCanceledOnTouchOutside(false);
                alert11.show();
            }
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }
    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (parent.getId()==R.id.lvAnalisis){
            Analisis a = null;
            try {
                a = AnalisisDAO.buscarAnalisisPorId(this,Integer.parseInt(String.valueOf(view.getTag())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(this, AnadirDatosAnalisis.class);
            i.putExtra("id",a.getFk_parte());
            i.putExtra("fkMaquina",a.getFk_maquina());
            i.putExtra("fkAnalisis",a.getId_analisis());
            startActivityForResult(i,103);
        }
    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
