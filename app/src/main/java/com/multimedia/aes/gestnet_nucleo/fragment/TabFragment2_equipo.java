package com.multimedia.aes.gestnet_nucleo.fragment;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.format.DateFormat;
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
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaAnalisis;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaMaquinas;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dao.DatosAdicionalesDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.MarcaDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;
import com.multimedia.aes.gestnet_nucleo.entidades.DatosAdicionales;
import com.multimedia.aes.gestnet_nucleo.entidades.Maquina;
import com.multimedia.aes.gestnet_nucleo.entidades.Marca;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.nucleo.AnadirDatosAnalisis;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static android.app.Activity.RESULT_OK;


public class TabFragment2_equipo extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener, AdapterView.OnItemClickListener {

    private View vista;
    private static Spinner spMarca, spPuestaMarcha;
    private static EditText  etModelo, etTempMaxACS, etCaudalACS, etPotenciaUtil,
            etTempGasesComb, etTempAmbienteLocal, etTempAguaGeneCalorEntrada,
            etTempAguaGeneCalorSalida ;
    private Button btnAñadirMaquina,btnDatosTesto;
    private TextView txtTipo;
    private ArrayList<Marca> arrayListMarcas= new ArrayList<>();
    private static ListView lvMaquinas,lvAnalisis;
    private static int alto=0,alto1=0,height=0;
    private String[] arrayMarcas,puestaMarcha;
    private static Parte parte = null;
    private Usuario usuario = null;
    private static Maquina maquina = null;
    private DatosAdicionales datos = null;
    private static ArrayList<Maquina> arrayListMaquina = new ArrayList<>();
    private static ArrayList<Analisis>  arrayListAnalisis = new ArrayList<>();
    private static AdaptadorListaAnalisis adaptadorListaAnalisis;
    private static AdaptadorListaMaquinas adaptadorListaMaquinas;
    private static Activity activity;
    private String serialNumber;

    //METODOS
    private void darValores(){
        alto=0;
        alto1=0;
        //SPINNER MARCAS
        try {
            if (MarcaDAO.buscarTodasLasMarcas(getContext())!=null){
                try {
                    arrayListMarcas.addAll(MarcaDAO.buscarTodasLasMarcas(getContext()));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                int indice=0;

                arrayMarcas = new String[arrayListMarcas.size() + 1];
                arrayMarcas[0]= "--Seleciones un valor--";
                for (int i = 1; i < arrayListMarcas.size() + 1; i++) {
                    if (arrayListMarcas.get(i - 1).getId_marca()!=0||arrayListMarcas.get(i - 1).getId_marca()!=-1){
                        arrayMarcas[i] = arrayListMarcas.get(i - 1).getNombre_marca()+"-"+arrayListMarcas.get(i - 1).getId_marca();
                    }
                }
                spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
            }else {
                arrayMarcas= new String[1];
                arrayMarcas[0]= "SIN MARCA";
                spMarca.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, arrayMarcas));
            }
            String marca = null;
            if (MarcaDAO.buscarMarcaPorId(getContext(), maquina.getFk_marca()) != null) {
                marca = MarcaDAO.buscarMarcaPorId(getContext(), maquina.getFk_marca()).getNombre_marca();
            }
            if (marca != null) {
                String myString = marca;
                ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
                int spinnerPosition = myAdap.getPosition(myString);
                spMarca.setSelection(spinnerPosition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            Dialogo.dialogoError("ERROR EN LAS MAQUINAS",getContext());
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
        spPuestaMarcha.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, puestaMarcha));

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
    }
    public void inicializarVariables(){
        //SPINNER
        spMarca = (Spinner)vista.findViewById(R.id.spMarca);
        spPuestaMarcha = (Spinner)vista.findViewById(R.id.spPuestaMarcha);
        //EDITTEXT
        etModelo = (EditText)vista.findViewById(R.id.etModelo);
        etTempMaxACS = (EditText)vista.findViewById(R.id.etTempMaxACS);
        etCaudalACS = (EditText)vista.findViewById(R.id.etCaudalACS);
        etPotenciaUtil = (EditText)vista.findViewById(R.id.etPotenciaUtil);
        etTempAguaGeneCalorEntrada = (EditText)vista.findViewById(R.id.etTempAguaGeneCalorEntrada);
        etTempAguaGeneCalorSalida = (EditText)vista.findViewById(R.id.etTempAguaGeneCalorSalida);
        //BUTTON
        btnAñadirMaquina = (Button)vista.findViewById(R.id.btnAñadirMaquina);
        btnDatosTesto = (Button)vista.findViewById(R.id.btnDatosTesto);
        //LISTVIEW
        lvMaquinas = (ListView)vista.findViewById(R.id.lvMaquinas);
        lvAnalisis = (ListView)vista.findViewById(R.id.lvAnalisis);
        lvAnalisis.setOnItemClickListener(this);
        //TEXTVIEW
        txtTipo = (TextView) vista.findViewById(R.id.txtTipo);
        //ONCLICKLISTENER
        btnAñadirMaquina.setOnClickListener(this);
        btnDatosTesto.setOnClickListener(this);
        activity = getActivity();
    }
    public static void añadirMaquina(Context context){
        try {
            arrayListMaquina.clear();
            List<Maquina> a = MaquinaDAO.buscarMaquinaPorFkParte(context,parte.getId_parte());
            if (a!=null) {
                alto =height * a.size();
                arrayListMaquina.addAll(a);
                lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                adaptadorListaMaquinas  = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina, activity );
                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                lvMaquinas.setVisibility(View.VISIBLE);
            }else{
                alto =0;
                lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                adaptadorListaMaquinas  = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina, activity);
                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                lvMaquinas.setVisibility(View.VISIBLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }



    }
    public static void borrarArrayMaquina(final int idMaquina, final Context context){
        AlertDialog.Builder builder1 = new AlertDialog.Builder(context);
        builder1.setMessage("Seguro que desea borrar la máquina, una vez borrada no se podrá recuperar");
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Si",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            MaquinaDAO.borrarMaquinaPorId(context,idMaquina);
                            arrayListMaquina.clear();
                            List<Maquina> a = MaquinaDAO.buscarTodasLasMaquinas(context);
                            if (a!=null) {
                                alto =height * a.size();
                                arrayListMaquina.addAll(a);
                                lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                                adaptadorListaMaquinas = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina,activity);
                                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                            }else{
                                alto = 0;
                                lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                                adaptadorListaMaquinas = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina,activity);
                                lvMaquinas.setAdapter(adaptadorListaMaquinas);
                            }

                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        dialog.cancel();
                    }
                });
        builder1.setNegativeButton(
                "No",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();

    }
    public static void rellenarDatosMaquina(int id_maquina, Context context,int position) {
        try {
            Maquina m = MaquinaDAO.buscarMaquinaPorId(context,id_maquina);
            maquina=m;
            ponerValores(context);
            arrayListMaquina.remove(position);
            alto =height * arrayListMaquina.size();
            lvMaquinas.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
            adaptadorListaMaquinas  = new AdaptadorListaMaquinas(context, R.layout.camp_adapter_list_view_maquinas, arrayListMaquina, activity);
            lvMaquinas.setAdapter(adaptadorListaMaquinas);
            lvMaquinas.setVisibility(View.VISIBLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private static void ponerValores(Context context){
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
                if (MarcaDAO.buscarMarcaPorId(context, maquina.getFk_marca()) != null) {
                    marca = MarcaDAO.buscarMarcaPorId(context, maquina.getFk_marca()).getNombre_marca();
                }
                if (marca != null) {
                    String myString = marca;
                    ArrayAdapter myAdap = (ArrayAdapter) spMarca.getAdapter();
                    int spinnerPosition = myAdap.getPosition(myString);
                    spMarca.setSelection(spinnerPosition);
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void añadirAnalisis(){
        try {
            arrayListAnalisis.clear();
            List<Analisis> a = AnalisisDAO.buscarAnalisisPorFkMaquina(getContext(),maquina.getId_maquina());
            if (a!=null) {
                alto1 =height * a.size();
                arrayListAnalisis.addAll(a);
                lvAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto1));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(getContext(), R.layout.camp_adapter_list_view_analisis_solicitud, arrayListAnalisis);
                lvAnalisis.setAdapter(adaptadorListaAnalisis);
                lvAnalisis.setVisibility(View.VISIBLE);
            }else{
                alto1 =0;
                lvAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto1));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(getContext(), R.layout.camp_adapter_list_view_analisis_solicitud, arrayListAnalisis);
                lvAnalisis.setAdapter(adaptadorListaAnalisis);
                lvAnalisis.setVisibility(View.VISIBLE);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    //OVERRIDE
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment2_equipo, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            int idParte = bundle.getInt("id", 0);
            try {
                parte = ParteDAO.buscarPartePorId(getContext(), idParte);
                usuario = UsuarioDAO.buscarUsuarioPorFkEntidad(getContext(),parte.getFk_tecnico());
                maquina = MaquinaDAO.buscarMaquinaPorFkMaquina(getContext(),parte.getFk_maquina());
                datos = DatosAdicionalesDAO.buscarDatosAdicionalesPorFkParte(getContext(),parte.getId_parte());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if(maquina==null){
                maquina = MaquinaDAO.newMaquinaRet(getContext(),0,parte.getFk_direccion(),0,
                        0,"",0,0,0,0,
                        0,0, 0,0,0,"",
                        "","","","","","",
                        "","","", "","",
                        "","","",false,"",
                        "","","","","","",
                        "","");
            }
            ////////////
            try {
                if (AnalisisDAO.buscarAnalisisPorFkMaquina(getContext(),maquina.getId_maquina())==null){
                    AnalisisDAO.newAnalisis(getContext(), maquina.getId_maquina(), idParte, "7", "147",
                            "18", "89.1", "69",
                            "0", "0", "-0.041", "6.52", "9.5", "1.83",
                            0, "medicion", 0, 0, 0);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        inicializarVariables();
        darValores();
        arrayListAnalisis.clear();
        arrayListMaquina.clear();
        añadirMaquina(getContext());
        añadirAnalisis();
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/16;
        return vista;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==103 && resultCode==RESULT_OK) {
            añadirAnalisis();
            serialNumber = data.getStringExtra("serial");
            try {
                MaquinaDAO.actualizaNumeroSerie(getContext(),maquina.getId_maquina(),serialNumber);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }
    @Override
    public void onClick(View view) {
        if (view.getId() == btnDatosTesto.getId()) {
            Intent i = new Intent(getActivity(), AnadirDatosAnalisis.class);
            i.putExtra("id", parte.getId_parte());
            i.putExtra("fkMaquina", maquina.getId_maquina());
            startActivityForResult(i, 103);
        } else if (view.getId() == btnAñadirMaquina.getId()) {
            if (spMarca.getSelectedItemPosition() == 0)
                Dialogo.dialogoError("Es necesario seleccionar una marca", getContext());
            else if (spPuestaMarcha.getSelectedItemPosition() == 0)
                Dialogo.dialogoError("Es necesario seleccionar una puesta en marcha", getContext());
            else if (etModelo.getText().length() == 0)
                Dialogo.dialogoError("Es necesario introducir el modelo", getContext());
            else {
                AlertDialog.Builder builder1 = new AlertDialog.Builder(getContext());
                builder1.setMessage("Seguro que desea añadir esta máquina");
                builder1.setCancelable(true);
                builder1.setPositiveButton(
                        "Si",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                try {
                                    int fk_maquina = maquina.getFk_maquina();
                                    int fk_parte = parte.getId_parte();
                                    int fk_direccion = parte.getFk_direccion();
                                    int fk_marca = MarcaDAO.buscarMarcaPorNombre(getContext(),spMarca.getSelectedItem().toString());
                                    String fk_tipo_combustion = txtTipo.getText().toString();
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
                                    String num_serie = "";
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
                                    String temperatura_max_acs = "";
                                    String caudal_acs = "";
                                    String potencia_util = "";
                                    String temperatura_agua_generador_calor_entrada = "";
                                    String temperatura_agua_generador_calor_salida = "";
                                    if (maquina!=null){
                                        MaquinaDAO.actualizarMaquina(getContext(),
                                                fk_maquina, fk_parte, fk_direccion, fk_marca, fk_tipo_combustion,
                                                fk_protocolo, fk_instalador, fk_remoto_central, fk_tipo, fk_instalacion,
                                                fk_estado, fk_contrato_mantenimiento, fk_gama, fk_tipo_gama,
                                                fecha_creacion, modelo, num_serie, num_producto, aparato,
                                                puesta_marcha, fecha_compra, fecha_fin_garantia,
                                                mantenimiento_anual, observaciones, ubicacion, tienda_compra,
                                                garantia_extendida, factura_compra, refrigerante,
                                                bEsInstalacion, nombre_instalacion, en_propiedad, esPrincipal, situacion,
                                                temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada,
                                                temperatura_agua_generador_calor_salida);
                                    }else{
                                        MaquinaDAO.newMaquina(getContext(),
                                                fk_maquina, fk_parte, fk_direccion, fk_marca, fk_tipo_combustion,
                                                fk_protocolo, fk_instalador, fk_remoto_central, fk_tipo, fk_instalacion,
                                                fk_estado, fk_contrato_mantenimiento, fk_gama, fk_tipo_gama,
                                                fecha_creacion, modelo, num_serie, num_producto, aparato,
                                                puesta_marcha, fecha_compra, fecha_fin_garantia,
                                                mantenimiento_anual, observaciones, ubicacion, tienda_compra,
                                                garantia_extendida, factura_compra, refrigerante,
                                                bEsInstalacion, nombre_instalacion, en_propiedad, esPrincipal, situacion,
                                                temperatura_max_acs, caudal_acs, potencia_util, temperatura_agua_generador_calor_entrada,
                                                temperatura_agua_generador_calor_salida
                                        );
                                    }
                                    /*new HiloGuardarDatosMaquina(getContext(),solicitud.getId(),maquina.getFk_maquina(),solicitud.getFk_direccion(),tipoCaldera,marcaCaldera,modelo,potencia,usoCaldera,puestaMarcha,maquina.getSerial_testo()).execute();
                                    añadirMaquina(getContext());
                                    etModelo.setText("");
                                    etTempMaxACS.setText("");
                                    etCaudalACS.setText("");
                                    etPotenciaUtil.setText("");
                                    etTempAguaGeneCalorEntrada.setText("");
                                    etTempAguaGeneCalorSalida.setText("");
                                    spTipo.setSelection(0);
                                    spMarca.setSelection(0);
                                    spUso.setSelection(0);
                                    spPotencia.setSelection(0);
                                    spPuestaMarcha.setSelection(0);
                                    Dialogo.error("Maquina añadida", getContext());
                                    maquina=null;*/
                                } catch (SQLException e) {
                                    e.printStackTrace();
                                }
                                dialog.cancel();
                            }
                        });
                builder1.setNegativeButton(
                        "No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
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
                a = AnalisisDAO.buscarAnalisisPorId(getContext(),Integer.parseInt(String.valueOf(view.getTag())));
            } catch (SQLException e) {
                e.printStackTrace();
            }
            Intent i = new Intent(getActivity(), AnadirDatosAnalisis.class);
            i.putExtra("id",a.getFk_parte());
            i.putExtra("fkMaquina",a.getFk_maquina());
            i.putExtra("fkAnalisis",a.getId_analisis());
            startActivityForResult(i,103);
        }
    }
}