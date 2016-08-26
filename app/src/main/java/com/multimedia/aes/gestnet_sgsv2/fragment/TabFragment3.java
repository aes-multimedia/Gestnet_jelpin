package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_sgsv2.clases.DataImagenes;
import com.multimedia.aes.gestnet_sgsv2.com.google.zxing.integration.android.IntentIntegrator;
import com.multimedia.aes.gestnet_sgsv2.com.google.zxing.integration.android.IntentResult;
import com.multimedia.aes.gestnet_sgsv2.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloSubirImagenes;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Camara;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;


public class TabFragment3 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private Spinner spEstadoVisita, spTipoVisita, spTipoReparacion,  spSubTipoVisita, spTiempoManoObra;
    private EditText etObservaciones, etCosteMateriales, etManoObra, etManoObraAdicional, etContadorInterno;
    private CheckBox cbContadorInterno, cbReparacion;
    private CheckBox cbLimpQuemCal, cbRevVasoExpan,cbRegulApara,cbCompEstanCierrQuemCalen,
            cbRevCaldCont,cbVeriHidraCalef,cbEstanConexApar,cbEstanCondEvacIRG,cbComprNivAgua,
            cbTipoCondEvac,cbReviEstadoAislaTerm,cbAnalProduComb,cbCaudACSCalcPotUtil,cbReviSistContro;
    private DatePicker dpFechaReparacion;
    private Button btnFinalizar,btnImprimir,scanBtn,scanBtn1,btnArchivo,btnFoto;
    private List<TiposReparaciones> tiposReparacion;
    private String[] tipos;
    private TextView tvFechaVisita,tvFechaLimite,txtFinalizado;
    private static Mantenimiento mantenimiento = null;
    private LinearLayout llReparacion,llContadorInterno;
    private List<TiposVisita> listaTiposVisita=null;
    private List<SubTiposVisita> listaSubTiposVista=null;
    private String tiposVisita [];
    private TiposVisita tipoVisita;
    private LinearLayout linearSubtipos;
    private String subTiposVisita[];
    private ScrollView scFinalizar;
    private static TextView contentTxt, contentTxt1;
    private static boolean scan = true;
    public static AdaptadorListaImagenes adaptadorListaImagenes;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    public static int alto1=0, height;
    public static ListView lvImagenes;
    public static Context context;
    private MantenimientoTerminado mantenimientoTerminado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Display display = ((Index)getContext()).getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/16;
        context=getContext();
        spEstadoVisita = (Spinner) vista.findViewById(R.id.spEstadoVisita);
        spTipoVisita = (Spinner) vista.findViewById(R.id.spTipoVisita);
        spTipoReparacion = (Spinner) vista.findViewById(R.id.spTipoReparacion);
        etObservaciones = (EditText) vista.findViewById(R.id.etObservaciones);
        etCosteMateriales = (EditText) vista.findViewById(R.id.etCosteMateriales);
        etManoObra = (EditText) vista.findViewById(R.id.etCosteManoDeObra);
        etManoObraAdicional = (EditText) vista.findViewById(R.id.etCosteManoDeObraAdicional);
        etContadorInterno = (EditText) vista.findViewById(R.id.etContadorInterno);
        cbContadorInterno = (CheckBox) vista.findViewById(R.id.cbContadorInterno);
        cbReparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);

        cbLimpQuemCal = (CheckBox) vista.findViewById(R.id.cbLimpQuemCal);
        cbRevVasoExpan = (CheckBox) vista.findViewById(R.id.cbRevVasoExpan);
        cbRegulApara = (CheckBox) vista.findViewById(R.id.cbRegulApara);
        cbCompEstanCierrQuemCalen = (CheckBox) vista.findViewById(R.id.cbCompEstanCierrQuemCalen);
        cbRevCaldCont = (CheckBox) vista.findViewById(R.id.cbRevCaldCont);
        cbVeriHidraCalef = (CheckBox) vista.findViewById(R.id.cbVeriHidraCalef);
        cbEstanConexApar = (CheckBox) vista.findViewById(R.id.cbEstanConexApar);
        cbEstanCondEvacIRG = (CheckBox) vista.findViewById(R.id.cbEstanCondEvacIRG);
        cbComprNivAgua = (CheckBox) vista.findViewById(R.id.cbComprNivAgua);
        cbTipoCondEvac = (CheckBox) vista.findViewById(R.id.cbTipoCondEvac);
        cbReviEstadoAislaTerm = (CheckBox) vista.findViewById(R.id.cbReviEstadoAislaTerm);
        cbAnalProduComb = (CheckBox) vista.findViewById(R.id.cbAnalProduComb);
        cbCaudACSCalcPotUtil = (CheckBox) vista.findViewById(R.id.cbCaudACSCalcPotUtil);
        cbReviSistContro = (CheckBox) vista.findViewById(R.id.cbReviSistContro);

        dpFechaReparacion = (DatePicker) vista.findViewById(R.id.dpFechaReparacion);
        btnFinalizar =(Button) vista.findViewById(R.id.btnFinalizar);
        btnImprimir =(Button) vista.findViewById(R.id.btnImprimir);
        btnArchivo = (Button)vista.findViewById(R.id.btnArchivo);
        btnFoto = (Button)vista.findViewById(R.id.btnFoto);
        tvFechaVisita = (TextView)vista.findViewById(R.id.tvFechaVisita);
        tvFechaLimite = (TextView)vista.findViewById(R.id.tvFechaLimite);
        txtFinalizado = (TextView)vista.findViewById(R.id.txtFinalizado);
        llReparacion = (LinearLayout)vista.findViewById(R.id.llReparacion);
        llContadorInterno = (LinearLayout)vista.findViewById(R.id.llContadorInterno);
        llReparacion.setVisibility(View.GONE);
        spSubTipoVisita = (Spinner)vista.findViewById(R.id.spSubTipoVisita);
        spTiempoManoObra = (Spinner)vista.findViewById(R.id.spTiempoManoObra);
        linearSubtipos = (LinearLayout)vista.findViewById(R.id.linearSubtipos);
        scFinalizar = (ScrollView)vista.findViewById(R.id.scFinalizar);
        scanBtn = (Button)vista.findViewById(R.id.scan_button);
        contentTxt = (TextView)vista.findViewById(R.id.scan_content);
        scanBtn1 = (Button)vista.findViewById(R.id.scan_button1);
        contentTxt1 = (TextView)vista.findViewById(R.id.scan_content1);
        lvImagenes = (ListView)vista.findViewById(R.id.lvImagenes);
        scanBtn.setOnClickListener(this);
        scanBtn1.setOnClickListener(this);
        cbReparacion.setOnClickListener(this);
        cbContadorInterno.setOnClickListener(this);
        btnFinalizar.setOnClickListener(this);
        btnImprimir.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
        btnArchivo.setOnClickListener(this);
        spTipoVisita.setOnItemSelectedListener(this);

        String dateSample = mantenimiento.getFecha_visita();
        String oldFormat = "dd-MM-yyyy HH:mm:ss";
        String newFormat = "dd/MM/yyyy";
        SimpleDateFormat sdf1 = new SimpleDateFormat(oldFormat);
        SimpleDateFormat sdf2 = new SimpleDateFormat(newFormat);
        String fecha = "";
        try {
            fecha = sdf2.format(sdf1.parse(dateSample));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvFechaVisita.setText(fecha);

        dateSample = mantenimiento.getFecha_maxima_endesa();
        try {
            fecha = sdf2.format(sdf1.parse(dateSample));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvFechaLimite.setText(fecha);

        try{
            listaTiposVisita = TiposVisitaDAO.buscarTodosLosTipoVisita(getContext());
            tiposVisita = new String[listaTiposVisita.size()+1];
            tiposVisita[0]="--Seleccione un valor--";
            for (int i = 1; i < listaTiposVisita.size()+1; i++) {
                tiposVisita[i]=listaTiposVisita.get(i-1).getDescripcion();
            }
            spTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tiposVisita));

        }catch (SQLException e){
            e.printStackTrace();
        }

        try {
            tiposReparacion = TiposReparacionesDAO.buscarTodosLosTiposReparaciones(getContext());
            tipos = new String[tiposReparacion.size()+1];
            tipos[0]="--Seleccione un valor--";
            for (int i = 1; i < tiposReparacion.size()+1; i++) {
                tipos[i]=tiposReparacion.get(i-1).getAbreviatura();
            }
            spTipoReparacion.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, tipos));

        } catch (SQLException e) {
            e.printStackTrace();
        }

        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){
            btnImprimir.setVisibility(View.GONE);
            scFinalizar.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.GONE);
        }else if (estado==2){

        }else if (estado==3){
            scFinalizar.setVisibility(View.GONE);
            btnImprimir.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.VISIBLE);
        }
        return vista;
    }

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.cbReparacion){
            if (cbReparacion.isChecked()){
                llReparacion.setVisibility(View.VISIBLE);
            }else{
                llReparacion.setVisibility(View.GONE);
            }
        }else if (view.getId()==R.id.cbContadorInterno){
            if (cbContadorInterno.isChecked()){
                llContadorInterno.setVisibility(View.VISIBLE);
            }else{
                llContadorInterno.setVisibility(View.GONE);
            }
        }else if (view.getId()==R.id.btnFinalizar) {
            guardarDatos();
            if (mantenimientoTerminado.getFk_tipo_maquina()!=-1) {
                if (mantenimientoTerminado.getFk_marca_maquina()!=-1) {
                    if (mantenimientoTerminado.getModelo_maquina()!=null) {
                        if (mantenimientoTerminado.getFk_potencia_maquina()!=-1) {
                            if (mantenimientoTerminado.getFk_uso_maquina()!=-1) {
                                if (mantenimientoTerminado.getPuesta_marcha_maquina()!=null) {
                                    if (mantenimientoTerminado.getFk_estado_visita() != -1) {
                                        if (mantenimientoTerminado.getFk_tipo_visita() != -1) {
                                            if (mantenimientoTerminado.getContador_interno() == 1 && mantenimientoTerminado.getCodigo_interno()==null) {
                                                Toast.makeText(getContext(), "Seleccione codigo de barras", Toast.LENGTH_LONG).show();
                                            } else {
                                                if (mantenimientoTerminado.getReparacion() == 1) {
                                                    if (mantenimientoTerminado.getFk_tipo_reparacion() != -1) {
                                                        if (mantenimientoTerminado.getFk_tiempo_mano_obra() != -1) {
                                                            if (mantenimientoTerminado.getCoste_materiales()!=null) {
                                                                if (mantenimientoTerminado.getCoste_mano_obra()!=null) {
                                                                    if (!arraylistImagenes.isEmpty()) {
                                                                        MantenimientoTerminadoDAO.newMantenimientoTerminado(getContext(),mantenimientoTerminado);
                                                                        for (int i = 0; i < arraylistImagenes.size(); i++) {
                                                                            ImagenesDAO.newImagen(getContext(), arraylistImagenes.get(i).nombre, arraylistImagenes.get(i).ruta, mantenimiento.getId_mantenimiento());
                                                                        }
                                                                        new HiloSubirImagenes(getActivity()).execute();
                                                                    } else {
                                                                        ((Index) getContext()).ticket();
                                                                        try {
                                                                            MantenimientoDAO.actualizarEstadoAndroid(getContext(), 2, mantenimiento.getId_mantenimiento());
                                                                        } catch (SQLException e) {
                                                                            e.printStackTrace();
                                                                        }

                                                                    }
                                                                    ((Index)getContext()).strtService();
                                                                } else {
                                                                    Toast.makeText(getContext(), "Seleccione coste de mano de obra", Toast.LENGTH_LONG).show();
                                                                }
                                                            } else {
                                                                Toast.makeText(getContext(), "Seleccione coste de materiales", Toast.LENGTH_LONG).show();
                                                            }
                                                        } else {
                                                            Toast.makeText(getContext(), "Seleccione tiempo de mano de obra", Toast.LENGTH_LONG).show();
                                                        }
                                                    } else {
                                                        Toast.makeText(getContext(), "Seleccione tipo de reparacion", Toast.LENGTH_LONG).show();
                                                    }
                                                } else {
                                                    if (!arraylistImagenes.isEmpty()) {
                                                        MantenimientoTerminadoDAO.newMantenimientoTerminado(getContext(),mantenimientoTerminado);
                                                        for (int i = 0; i < arraylistImagenes.size(); i++) {
                                                            ImagenesDAO.newImagen(getContext(), arraylistImagenes.get(i).nombre, arraylistImagenes.get(i).ruta, mantenimiento.getId_mantenimiento());
                                                        }
                                                        new HiloSubirImagenes(getActivity()).execute();
                                                    } else {
                                                        ((Index) getContext()).ticket();
                                                        try {
                                                            MantenimientoDAO.actualizarEstadoAndroid(getContext(), 2, mantenimiento.getId_mantenimiento());
                                                        } catch (SQLException e) {
                                                            e.printStackTrace();
                                                        }
                                                    }
                                                    ((Index)getContext()).strtService();
                                                }
                                            }
                                        } else {
                                            Toast.makeText(getContext(), "Seleccione tipo visita", Toast.LENGTH_LONG).show();
                                        }
                                    } else {
                                        Toast.makeText(getContext(), "Seleccione estado visita", Toast.LENGTH_LONG).show();
                                    }
                                }else {
                                    Toast.makeText(getContext(), "Seleccione puesta en marcha de la maquina", Toast.LENGTH_LONG).show();
                                }
                            }else {
                                Toast.makeText(getContext(), "Seleccione uso de maquina", Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Toast.makeText(getContext(), "Seleccione potencia de maquina", Toast.LENGTH_LONG).show();
                        }
                    }else{
                        Toast.makeText(getContext(), "Seleccione modelo de maquina", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(getContext(), "Seleccione marca de maquina", Toast.LENGTH_LONG).show();
                }
            }else{
                Toast.makeText(getContext(), "Seleccione tipo de maquina", Toast.LENGTH_LONG).show();
            }



        }else if (view.getId()==R.id.btnImprimir){
            ((Index)getContext()).ticket();
        }else if(view.getId()==R.id.scan_button){
            IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
            scanIntegrator.initiateScan();
            scan=true;
        }else if(view.getId()==R.id.scan_button1){
            IntentIntegrator scanIntegrator = new IntentIntegrator(getActivity());
            scanIntegrator.initiateScan();
            scan=false;
        }else if (view.getId()==R.id.btnFoto){
            hacerFoto();
        }else if (view.getId()==R.id.btnArchivo){
            cogerFoto();
        }


    }
    public static void llenarDatos(String scanContent, String scanFormat){
        if (scan) {
            contentTxt.setText(scanContent);
        }else{
            contentTxt1.setText(scanContent);
        }
    }
    public static Bitmap resizeImage(Bitmap bitmap) {

        Bitmap BitmapOrg = bitmap;

        int width = BitmapOrg.getWidth();
        int height = BitmapOrg.getHeight();

        if(width>1000&&height>1000) {
            int newWidth = (width * 20) / 100;
            int newHeight = (height * 20) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else if (width>1500&&height>1500) {
            int newWidth = (width * 10) / 100;
            int newHeight = (height * 10) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else if (width>2000&&height>2000) {
            int newWidth = (width * 5) / 100;
            int newHeight = (height * 5) / 100;

            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float) newHeight) / height;

            Matrix matrix = new Matrix();

            matrix.postScale(scaleWidth, scaleHeight);

            Bitmap resizedBitmap = Bitmap.createBitmap(BitmapOrg, 0, 0,
                    width, height, matrix, true);

            return resizedBitmap;
        }else{
            return bitmap;
        }
    }
    public static void borrarArrayImagenes(int position, Context context){
        arraylistImagenes.remove(position);
        alto1-=height;
        lvImagenes.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto1));
        adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);
    }

    public static void result(String path){
        File image = new File(path);
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(image.getAbsolutePath(),bmOptions);
        bitmap = resizeImage(bitmap);
        String nombre = path.substring(path.lastIndexOf('/')+1,path.length());
        arraylistImagenes.add(new DataImagenes(path,nombre,bitmap,mantenimiento.getId_mantenimiento()));
        alto1+=height;
        lvImagenes.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.WRAP_CONTENT, alto1));
        adaptadorListaImagenes = new AdaptadorListaImagenes(context, R.layout.camp_adapter_list_view_imagenes, arraylistImagenes);
        lvImagenes.setAdapter(adaptadorListaImagenes);
    }
    public static String getPath(Uri contentUri) {
        String res = null;
        String[] proj = { MediaStore.Images.Media.DATA };
        Cursor cursor = context.getContentResolver().query(contentUri, proj, null, null, null);
        if(cursor.moveToFirst()){;
            int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            res = cursor.getString(column_index);
        }
        cursor.close();
        return res;
    }
    public void hacerFoto(){
        Intent i = new Intent(getContext(), Camara.class);
        startActivity(i);
    }
    private void cogerFoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        intent.setType("image/*");
        startActivityForResult(intent, 2);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent) {
        if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = intent.getData();
                result(getPath(selectedImage));
            }
        }
        IntentResult scanningResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
        if (scanningResult != null) {
            String scanContent = scanningResult.getContents();
            String scanFormat = scanningResult.getFormatName();
            llenarDatos(scanContent,scanFormat);
        }else{
        }
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView==spTipoVisita) {
            if (i == 2) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 3);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else if (i == 4) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 5);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else if (i == 5) {
                try {
                    listaSubTiposVista = SubTiposVisitaDAO.buscarSubTiposVisitaPorTipo(getContext(), 6);
                    subTiposVisita = new String[listaSubTiposVista.size()];
                    for (int j = 0; j < listaSubTiposVista.size(); j++) {
                        subTiposVisita[j] = listaSubTiposVista.get(j).getCodigo();
                    }
                    spSubTipoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, subTiposVisita));
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                linearSubtipos.setVisibility(View.VISIBLE);
            } else {
                linearSubtipos.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void guardarDatos(){
        mantenimientoTerminado.setFk_parte(mantenimiento.getId_mantenimiento());
        int a = spEstadoVisita.getSelectedItemPosition();
        if (a!=0){
            mantenimientoTerminado.setFk_estado_visita(1);
        }else{
            mantenimientoTerminado.setFk_estado_visita(-1);
        }
        if (!contentTxt.getText().toString().trim().equals("")){
            mantenimientoTerminado.setCodigo_barras(contentTxt.getText().toString());
        }
        int b = spTipoVisita.getSelectedItemPosition();
        if (b!=0){

        }
        if (!etObservaciones.getText().toString().trim().equals("")){
            mantenimientoTerminado.setObservaciones_tecnico(etObservaciones.getText().toString());
        }
        if (cbContadorInterno.isChecked()){
            if (!etContadorInterno.getText().toString().trim().equals("")){
                mantenimientoTerminado.setContador_interno(Integer.parseInt(etContadorInterno.getText().toString()));
            }
        }
        if (cbReparacion.isChecked()){
            int c = spTipoReparacion.getSelectedItemPosition();
            if (c!=0){
                mantenimientoTerminado.setFk_tipo_reparacion(1);
            }else{
                mantenimientoTerminado.setFk_tipo_reparacion(-1);
            }
            String fecha = dpFechaReparacion.getYear()+"-"+dpFechaReparacion.getMonth()+"-"+dpFechaReparacion.getDayOfMonth();
            mantenimientoTerminado.setFecha_reparacion(fecha);
            int d = spTiempoManoObra.getSelectedItemPosition();
            if (d!=0){
                mantenimientoTerminado.setFk_tiempo_mano_obra(1);
            }else{
                mantenimientoTerminado.setFk_tiempo_mano_obra(-1);
            }
            if (!etCosteMateriales.getText().toString().trim().equals("")){
                mantenimientoTerminado.setCoste_materiales(etCosteMateriales.getText().toString());
            }
            if (!etManoObra.getText().toString().trim().equals("")){
                mantenimientoTerminado.setCoste_mano_obra(etManoObra.getText().toString());
            }
            if (!etManoObraAdicional.getText().toString().trim().equals("")){
                mantenimientoTerminado.setCoste_mano_obra_adicional(etManoObraAdicional.getText().toString());
            }
            if (!contentTxt1.getText().toString().trim().equals("")){
                mantenimientoTerminado.setCodigo_barras_reparacion(contentTxt1.getText().toString());
            }
        }
        if(cbLimpQuemCal.isChecked()){
            mantenimientoTerminado.setLimpieza_quemadores_caldera(1);
        }else{
            mantenimientoTerminado.setLimpieza_quemadores_caldera(0);
        }
        if(cbRevVasoExpan.isChecked()){
            mantenimientoTerminado.setRevision_vaso_expansion(1);
        }else{
            mantenimientoTerminado.setRevision_vaso_expansion(0);
        }
        if(cbRegulApara.isChecked()){
            mantenimientoTerminado.setRegulacion_aparatos(1);
        }else{
            mantenimientoTerminado.setRegulacion_aparatos(0);
        }
        if(cbCompEstanCierrQuemCalen.isChecked()){
            mantenimientoTerminado.setComprobar_estanqueidad_cierre_quemadores_caldera(1);
        }else{
            mantenimientoTerminado.setComprobar_estanqueidad_cierre_quemadores_caldera(0);
        }
        if(cbRevCaldCont.isChecked()){
            mantenimientoTerminado.setRevision_calderas_contadores(1);
        }else{
            mantenimientoTerminado.setRevision_calderas_contadores(0);
        }
        if(cbVeriHidraCalef.isChecked()){
            mantenimientoTerminado.setVerificacion_circuito_hidraulico_calefaccion(1);
        }else{
            mantenimientoTerminado.setVerificacion_circuito_hidraulico_calefaccion(0);
        }
        if(cbEstanConexApar.isChecked()){
            mantenimientoTerminado.setEstanqueidad_conexion_aparatos(1);
        }else{
            mantenimientoTerminado.setEstanqueidad_conexion_aparatos(0);
        }
        if(cbEstanCondEvacIRG.isChecked()){
            mantenimientoTerminado.setEstanqueidad_conducto_evacuacion_irg(1);
        }else{
            mantenimientoTerminado.setEstanqueidad_conducto_evacuacion_irg(0);
        }
        if(cbComprNivAgua.isChecked()){
            mantenimientoTerminado.setComprobacion_niveles_agua(1);
        }else{
            mantenimientoTerminado.setComprobacion_niveles_agua(0);
        }
        if(cbTipoCondEvac.isChecked()){
            mantenimientoTerminado.setTipo_conducto_evacuacion(1);
        }else{
            mantenimientoTerminado.setTipo_conducto_evacuacion(0);
        }
        if(cbReviEstadoAislaTerm.isChecked()){
            mantenimientoTerminado.setRevision_estado_aislamiento_termico(1);
        }else{
            mantenimientoTerminado.setRevision_estado_aislamiento_termico(0);
        }
        if(cbAnalProduComb.isChecked()){
            mantenimientoTerminado.setAnalisis_productos_combustion(1);
        }else{
            mantenimientoTerminado.setAnalisis_productos_combustion(0);
        }
        if(cbCaudACSCalcPotUtil.isChecked()){
            mantenimientoTerminado.setCaudal_acs_calculo_potencia(1);
        }else{
            mantenimientoTerminado.setCaudal_acs_calculo_potencia(0);
        }
        if(cbReviSistContro.isChecked()){
            mantenimientoTerminado.setRevision_sistema_control(1);
        }else{
            mantenimientoTerminado.setRevision_sistema_control(0);
        }
    }
    public void setMantenimientoTerminado(MantenimientoTerminado mantenimientoTerminado) {
        this.mantenimientoTerminado = mantenimientoTerminado;
    }
}