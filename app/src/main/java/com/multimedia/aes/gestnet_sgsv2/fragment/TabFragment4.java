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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.AdaptadorListaImagenes;
import com.multimedia.aes.gestnet_sgsv2.clases.DataImagenes;
import com.multimedia.aes.gestnet_sgsv2.dao.EstadoVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.ImagenesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MotivosNoRepDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.EstadoVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.MotivosNoRep;
import com.multimedia.aes.gestnet_sgsv2.entities.SubTiposVisita;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposVisita;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Camara;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Index;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;


public class TabFragment4 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;
    private Spinner spEstadoVisita, spTipoVisita, spTipoReparacion,  spSubTipoVisita, spTiempoManoObra,spMotivoNoAcepta;
    private EditText etObservaciones, etObservacionesInsitu,etCodVisitaPlataformaA,etCodVisitaPlataformaB;
    private CheckBox cbContadorInterno, cbReparacion,cbAceptaRepSi,cbAceptaRepNo,cbSolicitudVisita,cbInSitu;
    private Button btnFinalizar,btnImprimir,btnArchivo,btnFoto;
    private List<TiposReparaciones> tiposReparacion;
    private String[] tipos;
    private List<EstadoVisita> estadoVisitas;
    private String[] estado;
    private TextView tvFechaVisita,tvFechaLimite,txtFinalizado,txtFech,txtCosteMateriales,txtImporteManoObra,txtImporteManoObraAdicional;
    private static Mantenimiento mantenimiento = null;
    private LinearLayout llReparacion,llAnomalias,llAceptaRep,llNoAceptaRep,llCerrada;
    private List<TiposVisita> listaTiposVisita=null;
    private List<SubTiposVisita> listaSubTiposVista=null;
    private String tiposVisita [];
    private TiposVisita tipoVisita;
    private LinearLayout linearSubtipos;
    private String subTiposVisita[];
    private ScrollView scFinalizar;
    public static AdaptadorListaImagenes adaptadorListaImagenes;
    public static ArrayList<DataImagenes> arraylistImagenes = new ArrayList<>();
    public static int alto1=0, height;
    public static ListView lvImagenes;
    public static Context context;
    private MantenimientoTerminado mantenimientoTerminado;
    private List<MotivosNoRep> motivosNoRep;
    private String[] motivos;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_4, container, false);
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
        cbContadorInterno = (CheckBox) vista.findViewById(R.id.cbContadorInterno);
        cbReparacion = (CheckBox) vista.findViewById(R.id.cbReparacion);
        cbAceptaRepSi=(CheckBox) vista.findViewById(R.id.checkSiAcepta);
        cbAceptaRepNo=(CheckBox) vista.findViewById(R.id.checkNoAcepta);
        cbSolicitudVisita=(CheckBox) vista.findViewById(R.id.checkSolcitudVisita);
        cbInSitu=(CheckBox) vista.findViewById(R.id.cbInSitu);
        etCodVisitaPlataformaA=(EditText)vista.findViewById(R.id.etCodVisitaPlataformaA);
        etCodVisitaPlataformaB=(EditText)vista.findViewById(R.id.etCodVisitaPlataformaB);

        btnFinalizar =(Button) vista.findViewById(R.id.btnFinalizar);
        btnImprimir =(Button) vista.findViewById(R.id.btnImprimir);
        btnArchivo = (Button)vista.findViewById(R.id.btnArchivo);
        btnFoto = (Button)vista.findViewById(R.id.btnFoto);
        tvFechaVisita = (TextView)vista.findViewById(R.id.tvFechaVisita);
        tvFechaLimite = (TextView)vista.findViewById(R.id.tvFechaLimite);
        txtFinalizado = (TextView)vista.findViewById(R.id.txtFinalizado);
        txtFech = (TextView)vista.findViewById(R.id.txtFech);
        txtCosteMateriales = (TextView)vista.findViewById(R.id.txtCostesMateriales);
        txtImporteManoObra = (TextView)vista.findViewById(R.id.txtImporteManoObra);
        txtImporteManoObraAdicional = (TextView)vista.findViewById(R.id.txtImporteManoObraAdicional);
        etObservacionesInsitu=(EditText)vista.findViewById(R.id.observInsitu);


        llReparacion = (LinearLayout)vista.findViewById(R.id.llReparacion);
        llAnomalias = (LinearLayout)vista.findViewById(R.id.llAnomalias);
        llReparacion.setVisibility(View.GONE);
        llAceptaRep=(LinearLayout)vista.findViewById(R.id.LinearAceptaReparacion);
        llAceptaRep.setVisibility(View.GONE);
        llNoAceptaRep=(LinearLayout)vista.findViewById(R.id.LinearNoAceptaRep);
        llCerrada=(LinearLayout)vista.findViewById(R.id.llCerrada);
        llNoAceptaRep.setVisibility(View.GONE);
        spSubTipoVisita = (Spinner)vista.findViewById(R.id.spSubTipoVisita);
        spTiempoManoObra = (Spinner)vista.findViewById(R.id.spTiempoManoObra);
        spMotivoNoAcepta = (Spinner)vista.findViewById(R.id.spMotivoNoAcepta);
        linearSubtipos = (LinearLayout)vista.findViewById(R.id.linearSubtipos);
        scFinalizar = (ScrollView)vista.findViewById(R.id.scFinalizar);
        lvImagenes = (ListView)vista.findViewById(R.id.lvImagenes);
        cbReparacion.setOnClickListener(this);
        cbContadorInterno.setOnClickListener(this);

        cbAceptaRepSi.setOnClickListener(this);
        cbAceptaRepNo.setOnClickListener(this);
        cbSolicitudVisita.setOnClickListener(this);
        cbInSitu.setOnClickListener(this);

        btnFinalizar.setOnClickListener(this);
        btnImprimir.setOnClickListener(this);
        btnFoto.setOnClickListener(this);
        btnArchivo.setOnClickListener(this);
        spTipoVisita.setOnItemSelectedListener(this);
        spMotivoNoAcepta.setOnItemSelectedListener(this);
        spEstadoVisita.setOnItemSelectedListener(this);

        llAceptaRep.setVisibility(View.GONE);
        llAnomalias.setVisibility(View.GONE);



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
        txtFech.setText(fecha);

        dateSample = mantenimiento.getFecha_maxima_endesa();
        try {
            fecha = sdf2.format(sdf1.parse(dateSample));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        tvFechaLimite.setText(fecha);
        txtCosteMateriales.setText("50 €");
        txtImporteManoObra.setText("0 €");
        txtImporteManoObraAdicional.setText("0 €");
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
        try {
            motivosNoRep = MotivosNoRepDAO.buscarTodosLosMotivosNoRep(getContext());
            motivos = new String[motivosNoRep.size()+1];
            motivos[0]="--Seleccione un valor--";
            for (int i = 1; i < motivosNoRep.size()+1; i++) {
                motivos[i]=motivosNoRep.get(i-1).getMotivo();
            }
            spMotivoNoAcepta.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, motivos));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            estadoVisitas = EstadoVisitaDAO.buscarTodosLosEstadoVisita(getContext());
            estado = new String[estadoVisitas.size()+1];
            estado[0]="--Seleccione un valor--";
            for (int i = 1; i < estadoVisitas.size()+1; i++) {
                estado[i]=estadoVisitas.get(i-1).getNombre_estado_visita();
            }
            spEstadoVisita.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, estado));

        } catch (SQLException e) {
            e.printStackTrace();
        }
        spTipoReparacion.setSelection(1);
        spTiempoManoObra.setSelection(3);
        int estado = Integer.parseInt(mantenimiento.getEstado_android());
        if (estado==0){

        }else if (estado==1){
            btnImprimir.setVisibility(View.GONE);
            scFinalizar.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.GONE);
        }else if (estado==2){
            scFinalizar.setVisibility(View.GONE);
            btnImprimir.setVisibility(View.VISIBLE);
            txtFinalizado.setVisibility(View.VISIBLE);
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

        }else if (view.getId()==R.id.checkSiAcepta) {
            if (cbAceptaRepSi.isChecked()) {
                cbAceptaRepNo.setChecked(false);
                llNoAceptaRep.setVisibility(View.GONE);
                llAceptaRep.setVisibility(View.VISIBLE);
            } else {
                llAceptaRep.setVisibility(View.GONE);
            }
        }else if(view.getId()==R.id.checkNoAcepta){
            if(cbAceptaRepNo.isChecked()){
                cbAceptaRepSi.setChecked(false);
                llAceptaRep.setVisibility(View.GONE);
                llNoAceptaRep.setVisibility(View.VISIBLE);
            }else{
                etCodVisitaPlataformaA.setVisibility(View.GONE);
                llNoAceptaRep.setVisibility(View.GONE);
            }
        }else if(view.getId()==R.id.cbInSitu){
            if(cbInSitu.isChecked()){
                cbSolicitudVisita.setChecked(false);
               etObservacionesInsitu.setVisibility(View.VISIBLE);
               etCodVisitaPlataformaB.setVisibility(View.GONE);

            }else{
                etObservacionesInsitu.setVisibility(View.GONE);
            }
        }
        else if(view.getId()==R.id.checkSolcitudVisita){
            if(cbSolicitudVisita.isChecked()){
                etObservacionesInsitu.setVisibility(View.GONE);
                etCodVisitaPlataformaB.setVisibility(View.VISIBLE);
                cbInSitu.setChecked(false);
            }else{

                etCodVisitaPlataformaB.setVisibility(View.GONE);
            }
        }else if (view.getId()==R.id.btnFinalizar) {
            try {
                guardarDatos();
            } catch (SQLException e) {
                e.printStackTrace();
            }
                if (mantenimientoTerminado.isMaquina()) {
                    Calendar cal = new GregorianCalendar();
                    Date date = cal.getTime();
                    SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                    String fecha = df.format(date);
                    df = new SimpleDateFormat("hh:mm");
                    String hora = df.format(date);
                    mantenimientoTerminado.setFecha_ticket(fecha);
                    mantenimientoTerminado.setHora_ticket(hora);
                    MantenimientoTerminadoDAO.newMantenimientoTerminado(getContext(),mantenimientoTerminado);
                    if (!arraylistImagenes.isEmpty()) {
                        for (int i = 0; i < arraylistImagenes.size(); i++) {
                            ImagenesDAO.newImagen(getContext(), arraylistImagenes.get(i).nombre, arraylistImagenes.get(i).ruta, mantenimiento.getId_mantenimiento());
                        }
                    } else {
                        ((Index) getContext()).ticket();
                        try {
                            MantenimientoDAO.actualizarEstadoAndroid(getContext(), 3, mantenimiento.getId_mantenimiento());
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                    }
            }

        }else if (view.getId()==R.id.btnImprimir){
            ((Index)getContext()).ticket();
        }else if (view.getId()==R.id.btnFoto){
            hacerFoto();
        }else if (view.getId()==R.id.btnArchivo){
            cogerFoto();
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
    }
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView==spTipoVisita) {
            if (i==1){
                llAnomalias.setVisibility(View.GONE);
                mantenimientoTerminado.setAnomalia(false);
                cbAceptaRepSi.setChecked(false);
            }else if (i == 3) {
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
                llAnomalias.setVisibility(View.VISIBLE);
                mantenimientoTerminado.setAnomalia(true);
                cbAceptaRepSi.setChecked(false);
                llAceptaRep.setVisibility(View.GONE);
            } else if (i == 5) {
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
                llAnomalias.setVisibility(View.VISIBLE);
                mantenimientoTerminado.setAnomalia(true);
                cbAceptaRepSi.setChecked(false);
                llAceptaRep.setVisibility(View.GONE);
            } else if (i == 6) {
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
                llAnomalias.setVisibility(View.VISIBLE);
                mantenimientoTerminado.setAnomalia(true);
                cbAceptaRepSi.setChecked(false);
                llAceptaRep.setVisibility(View.GONE);
            } else {
                linearSubtipos.setVisibility(View.GONE);
                cbAceptaRepSi.setChecked(false);
                llAceptaRep.setVisibility(View.GONE);
            }
        }else if(adapterView==spMotivoNoAcepta){
            if (i==1||i==2){
                etCodVisitaPlataformaA.setVisibility(View.VISIBLE);
            }else{
                etCodVisitaPlataformaA.setVisibility(View.GONE);
            }
        }else if(adapterView==spEstadoVisita){
            if (i==5){
                llCerrada.setVisibility(View.VISIBLE);
            }else{
                llCerrada.setVisibility(View.GONE);
            }
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void guardarDatos() throws SQLException {
        mantenimientoTerminado.setFk_parte(mantenimiento.getId_mantenimiento());
        mantenimientoTerminado.setFk_estado_visita(EstadoVisitaDAO.buscarIdEstadoVisitaPorNombre(getContext(),spEstadoVisita.getSelectedItem().toString()));

        if (!etObservaciones.getText().toString().trim().equals("")){
            mantenimientoTerminado.setObservaciones_tecnico(etObservaciones.getText().toString());
        }
        if (cbContadorInterno.isChecked()){
            mantenimientoTerminado.setContador_interno(1);
        }
        if (cbReparacion.isChecked()){
            mantenimientoTerminado.setAcciones(true);
            int c = spTipoReparacion.getSelectedItemPosition();
            if (c!=0){
                int id= -1;
                try {
                    id= TiposReparacionesDAO.buscarTiposReparacionesPorAbreviatura(getContext(),spTipoReparacion.getSelectedItem().toString());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                mantenimientoTerminado.setFk_tipo_reparacion(id);
            }
            String fecha = txtFech.getText().toString();
            mantenimientoTerminado.setFecha_reparacion(fecha);
            int d = spTiempoManoObra.getSelectedItemPosition();
            switch (d){
                case 0:
                    mantenimientoTerminado.setFk_tiempo_mano_obra(-1);
                    break;
                case 1:
                    mantenimientoTerminado.setFk_tiempo_mano_obra(1);
                    break;
                case 2:
                    mantenimientoTerminado.setFk_tiempo_mano_obra(2);
                    break;
                case 3:
                    mantenimientoTerminado.setFk_tiempo_mano_obra(3);
                    break;
            }

            mantenimientoTerminado.setCoste_materiales("50");
            mantenimientoTerminado.setCoste_mano_obra("0");
            mantenimientoTerminado.setCoste_mano_obra_adicional("0");

        }
        if (mantenimientoTerminado.isAnomalia()){
            int b = spTipoVisita.getSelectedItemPosition();
            if (b!=0){
                int id= -1;
                try {
                    id = TiposVisitaDAO.buscarTipoVisitaPorNombre(getContext(),spTipoVisita.getSelectedItem().toString());
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                mantenimientoTerminado.setFk_tipo_visita(id);
            }
            int t = spSubTipoVisita.getSelectedItemPosition();
            if (b==3||b==5||b==6){
                int id= -1;
                try {
                    id = SubTiposVisitaDAO.buscarSubTipoVisitaPorCodigo(getContext(),spSubTipoVisita.getSelectedItem().toString());

                } catch (SQLException e) {
                    e.printStackTrace();
                }
                mantenimientoTerminado.setFk_subtipo_visita(id);
            }
            if (cbAceptaRepSi.isChecked()){
                mantenimientoTerminado.setReparacion(1);
                if (cbInSitu.isChecked()){
                    mantenimientoTerminado.setObs_reparacion_iberdrola(etObservacionesInsitu.getText().toString());
                    mantenimientoTerminado.setInsitu(true);
                }else if(cbSolicitudVisita.isChecked()){
                    mantenimientoTerminado.setCod_visita_plataforma(etCodVisitaPlataformaB.getText().toString());
                }
            }else if (cbAceptaRepNo.isChecked()){
                mantenimientoTerminado.setFk_motivos_no_rep(MotivosNoRepDAO.buscarMotivosNoRepMotivo(getContext(),spMotivoNoAcepta.getSelectedItem().toString()));
                int v = spMotivoNoAcepta.getSelectedItemPosition();
                if (v==1||v==2) {
                    mantenimientoTerminado.setCod_visita_plataforma(etCodVisitaPlataformaA.getText().toString());
                }
            }
        }
        mantenimientoTerminado.setEnviado(false);
    }
    public void setMantenimientoTerminado(MantenimientoTerminado mantenimientoTerminado) {
        this.mantenimientoTerminado = mantenimientoTerminado;
    }
}