package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.clases.Impresora;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.entities.TiposReparaciones;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Firmar;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Set;

public class FragmentBluetooth extends Fragment implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;
    private OutputStream mmOutputStream;
    private InputStream mmInputStream;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    private ListView lvNombres;
    private Button openButton, sendButton, closeButton,btnOtra;
    private TextView txtImpreso,txtImpreso2,txtCodigoBarras;
    private LinearLayout llImpreso,llBotones;
    private Impresora impresora;
    private ImageView ivLogo,ivFirma1;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
    private View vista;
    private ScrollView scTicket;
    private Mantenimiento mantenimiento;
    private Tecnico tecnico;
    private MantenimientoTerminado mantenimientoTerminado;
    private List<Maquina> maquinas;
    private List<EquipamientoCaldera> equipamiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vista = inflater.inflate(R.layout.bluetooth, container, false);
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
            mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorfkParte(getContext(),id);
            tecnico = TecnicoDAO.buscarTodosLosTecnicos(getContext()).get(0);
            maquinas = MaquinaDAO.buscarMaquinaPorFkMantenimiento(getContext(),mantenimiento.getId_mantenimiento());
            equipamiento = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMantenimiento(getContext(),mantenimiento.getId_mantenimiento());
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        openButton = (Button) vista.findViewById(R.id.open);
        sendButton = (Button) vista.findViewById(R.id.send);
        closeButton = (Button) vista.findViewById(R.id.close);
        btnOtra = (Button) vista.findViewById(R.id.btnOtra);
        lvNombres = (ListView) vista.findViewById(R.id.lvNombres);
        txtImpreso = (TextView) vista.findViewById(R.id.txtImpreso);
        llImpreso = (LinearLayout) vista.findViewById(R.id.llImpreso);
        llBotones = (LinearLayout) vista.findViewById(R.id.llBotones);
        txtImpreso2 = (TextView) vista.findViewById(R.id.txtImpreso2);
        txtCodigoBarras = (TextView) vista.findViewById(R.id.txtCodigoBarras);
        ivLogo = (ImageView) vista.findViewById(R.id.ivLogo);
        ivFirma1 = (ImageView) vista.findViewById(R.id.ivFirmaUno);

        scTicket = (ScrollView) vista.findViewById(R.id.scTicket);

        lvNombres.setOnItemClickListener(this);
        openButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        btnOtra.setOnClickListener(this);
        sendButton.setVisibility(View.GONE);
        closeButton.setVisibility(View.GONE);
        llImpreso.setVisibility(View.VISIBLE);
        scTicket.setVisibility(View.VISIBLE);
        lvNombres.setVisibility(View.GONE);
        btnOtra.setVisibility(View.GONE);

        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ivLogo.setImageBitmap(generarImagen());
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            txtImpreso.setText(generarTexto1());
            txtImpreso2.setText(generarTextoFin());
            txtCodigoBarras.setText("       569821435156964121       ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        findBT();
        return vista;
    }
    private final BroadcastReceiver bReciever = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (BluetoothDevice.ACTION_FOUND.equals(action)) {
            BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
            boolean noEsta = true;
            for (int i = 0; i < listaNombre.size(); i++) {
                if (listaNombre.get(i).equals(device.getName())) {
                    noEsta = false;
                    break;
                }
            }
            if (noEsta) {
                listaDevice.add(device);
                listaNombre.add(device.getName());
            }
        }
        ponerLista();
        if (ManagerProgressDialog.getDialog()!=null) {
            ManagerProgressDialog.cerrarDialog();
        }
            getContext().unregisterReceiver(bReciever);
        }
    };
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.open) {
            btnOtra.setVisibility(View.VISIBLE);
            listaDevice.clear();
            listaNombre.clear();
            findBT();
            lvNombres.setVisibility(View.VISIBLE);
            llImpreso.setVisibility(View.GONE);
            scTicket.setVisibility(View.GONE);
            sendButton.setVisibility(View.GONE);
            closeButton.setVisibility(View.GONE);
            openButton.setVisibility(View.GONE);
        } else if (view.getId() == R.id.send) {
            llBotones.setVisibility(View.VISIBLE);
            Intent i = new Intent(getContext(),Firmar.class);
            startActivityForResult(i,99);
            sendButton.setVisibility(View.GONE);
            closeButton.setVisibility(View.VISIBLE);
            openButton.setVisibility(View.GONE);
            lvNombres.setVisibility(View.GONE);
            llImpreso.setVisibility(View.VISIBLE);
            scTicket.setVisibility(View.VISIBLE);
        } else if (view.getId() == R.id.close) {
            closeButton.setVisibility(View.GONE);
            Bitmap bitmap = Bitmap.createBitmap( llImpreso.getWidth(), llImpreso.getHeight(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            llImpreso.draw(canvas);
            saveToInternalSorage(bitmap);
            impresora = new Impresora(getActivity(),mmDevice);
            impresora.imprimir();
        }else if (view.getId() == R.id.btnOtra) {
            ManagerProgressDialog.abrirDialog(getContext());
            ManagerProgressDialog.buscandoBluetooth(getContext());
            IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
            getContext().registerReceiver(bReciever, filter);
            mBluetoothAdapter.startDiscovery();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == 99) {
            if(resultCode == Activity.RESULT_OK){
                Bitmap bitmap = loadFirmaFromStorage();
                ivFirma1.setImageBitmap(bitmap);
            }
            if (resultCode == Activity.RESULT_CANCELED) {

            }
        }
    }

    private String saveToInternalSorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getContext());
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        File mypath=new File(directory,"ticket"+mantenimiento.getId_mantenimiento()+".png");
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
            fos.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return directory.getAbsolutePath();
    }
    void findBT() {
        try {
            mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();

            if (mBluetoothAdapter == null) {
            }

            if (!mBluetoothAdapter.isEnabled()) {

                Intent enableBluetooth = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
                startActivityForResult(enableBluetooth, 0);
            }
            Set<BluetoothDevice> pairedDevices = mBluetoothAdapter.getBondedDevices();

            if (pairedDevices.size() > 0) {
                for (BluetoothDevice device : pairedDevices) {
                    listaDevice.add(device);
                    listaNombre.add(device.getName());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        ponerLista();
    }
    void closeBT() throws IOException {
        try {
            mmOutputStream.close();
            mmInputStream.close();
            mmSocket.close();
            sendButton.setVisibility(View.GONE);
            closeButton.setVisibility(View.GONE);
            openButton.setVisibility(View.VISIBLE);
            lvNombres.setVisibility(View.VISIBLE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void ponerLista() {
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, listaNombre);
        lvNombres.setAdapter(adaptador);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        llBotones.setVisibility(View.VISIBLE);
        lvNombres.setVisibility(View.GONE);
        llImpreso.setVisibility(View.VISIBLE);
        scTicket.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.GONE);
        openButton.setVisibility(View.GONE);
        btnOtra.setVisibility(View.GONE);
        try {
            txtImpreso.setText(generarTexto1());
            txtImpreso2.setText(generarTextoFin());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            ivLogo.setImageBitmap(generarImagen());
        } catch (IOException e) {
            e.printStackTrace();
        }
        mmDevice = listaDevice.get(adapterView.getPositionForView(view));
        Toast.makeText(getContext(), "Impresora seleccionada", Toast.LENGTH_SHORT).show();
    }
    private Bitmap generarImagen() throws IOException {
        InputStream bitmap = null;
        bitmap =  getContext().getAssets().open("logo.png");
        Bitmap btmp= BitmapFactory.decodeStream(bitmap);
        return btmp;
    }
    private String generarTexto1() throws SQLException {
        Calendar cal = new GregorianCalendar();
        Date date = cal.getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
        String fecha = df.format(date);
        df = new SimpleDateFormat("hh:mm");
        String hora = df.format(date);
        mantenimientoTerminado.setFecha_ticket(fecha);
        mantenimientoTerminado.setHora_ticket(hora);
        String fecha_hora = "\n\n"+"FECHA Y HORA: "+fecha+"-"+hora + "\n\n";
        String gps="Long:43.283594 Lat:-3.955325"+"\n";
        String datos_cliente = "---------DATOS CLIENTE----------" + "\n";
        String nombre_cliente = mantenimiento.getNombre_usuario() + "\n";
        String num_contrato = mantenimiento.getNum_orden_endesa();
        String numero_contrato = "N. Contrato: "+num_contrato + "\n";
        String dir = mantenimiento.getDireccion()+"\n"+mantenimiento.getCod_postal()+"\n"+mantenimiento.getProvincia()+"\n"+mantenimiento.getMunicipio();
        String direccion = "Direccion: "+"\n"+dir+"\n\n";
        String datos_tecnico = "---------DATOS TECNICO----------" + "\n";
        String emp = "ICISA";
        String empresa = "Empresa: "+emp+"\n";
        String cif_emp = "05954765L";
        String cif = "CIF: "+cif_emp+"\n";
        String num_emp_mant = "44556678";
        String numero_empresa_mantenedora = "N. Empresa Mantenedora: "+"\n"+num_emp_mant+"\n";
        String tec = tecnico.getNombre_usuario();
        String tecnic = "Tecnico: "+tec+"\n";
        String num_insta = "659898741";
        String numero_instalador = "N. Instalador: "+num_insta+"\n\n";
        String datos_averia ="";
        String notificada = "";
        if (mantenimiento.getFk_categoria_visita()!=1) {
            datos_averia = "----------DATOS VISITA----------" + "\n";
            String noti = "Visita realizada cumpliendo los" + "\n" + "requisitos de la IT.3 del RITE";
            notificada = "" + noti + "\n\n";
        }
        String presupuesto = "-----OPERACIONES REALIZADAS-----" + "\n";
        String op = operaciones();
        String operaciones = op+"\n";
        String maquina = datosMaquinas()+"\n\n";
        String anomalias_detectadas = "ANOMALIAS DETECTADAS: "+"\n";
        String anom = "";
        if (!mantenimientoTerminado.isAnomalia()){
            anom = "Sin Anomalias";
        }else {
            anom = TiposVisitaDAO.buscarNombreTipoVisitaPorId(getContext(),mantenimientoTerminado.getFk_tipo_visita())+"\n";
            if (mantenimientoTerminado.getFk_subtipo_visita()!=-1){
                anom += SubTiposVisitaDAO.buscarCodigoSubTipoVisitaPorId(getContext(),mantenimientoTerminado.getFk_subtipo_visita());
            }else{
                anom = "otras anomalias";
            }
        }
        String anomalias = anom+"\n\n";
        String comun = "";
        String comuni = "";
        if(mantenimientoTerminado.isAnomalia()) {
            comun = "*Se comunica la cliente, y este" + "\n" + "declara quedar informado que la" + "\n" +
                    "correccion de las posibles" + "\n" + "anomalias detectadas durante" + "\n" +
                    "esta visita, sean principales o" + "\n" + "secundarias, es de su exclusiva" + "\n" + "responsabilidad segun Real" + "\n" +
                    "Decreto 919/2006,de 28 de julio." + "\n";
            comuni = "*En caso de existir anomalias" + "\n" + "principales no corregidas, estas" + "\n" +
                    "pueden ser informadas a la" + "\n" + "empresa distribuidora y/o" + "\n" + "autoridad competente." + "\n\n";
        }
        String observaciones_tecnico="-----OBSERVACIONES TECNICO------";
        String obs = "";
        if (mantenimientoTerminado.isAcciones()){
            obs += "Accion reak¡lizada"+TiposReparacionesDAO.buscarTiposReparacionesPorId(getContext(),mantenimientoTerminado.getFk_tipo_reparacion()).getAbreviatura()+"\n";
            obs += mantenimientoTerminado.getFecha_reparacion()+"\n";

        }
        String firma_tecnico = "Firma Tecnico:"+"\n\n\n\n\n\n\n";
        String textoImpresion =fecha_hora+gps+datos_cliente+nombre_cliente+numero_contrato+direccion+
                datos_tecnico+empresa+cif+numero_empresa_mantenedora+tecnic+numero_instalador+
                datos_averia+notificada+presupuesto+operaciones+maquina+anomalias_detectadas+
                anomalias+comun+comuni+observaciones_tecnico+obs+firma_tecnico;
        return textoImpresion;
    }
    private String generarTextoFin(){
        String conforme_cliente="--------CONFORME CLIENTE--------"+"\n";
        String obs = mantenimiento.getObservaciones_usuario();
        String observaciones = "Observaciones: "+obs+"\n";
        String nom = mantenimiento.getNombre_usuario();
        String nombre = "Nombre: "+nom+"\n";
        String dn = mantenimiento.getDni_usuario();
        String dni = "Dni: "+dn+"\n";
        String firma_cliente="Firma Cliente"+"\n";

        String textoImpresion =conforme_cliente+observaciones+nombre+dni+firma_cliente;
        return textoImpresion;
    }
    private Bitmap loadFirmaFromStorage(){
        Bitmap b=null;
        try {
            File f=new File(path, "firma"+mantenimiento.getId_mantenimiento()+".png");
            b = BitmapFactory.decodeStream(new FileInputStream(f));
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        return b;
    }
    private String operaciones(){
        String operaciones = "";
        if (mantenimientoTerminado.getLimpieza_quemadores_caldera()==1){
            operaciones=operaciones+"-Limpieza quemador";
        }
        if (mantenimientoTerminado.getRevision_vaso_expansion()==1){
            operaciones=operaciones+"\n"+"-Revision vaso exp.";
        }
        if (mantenimientoTerminado.getRegulacion_aparatos()==1){
            operaciones=operaciones+"\n"+"-Regulacion aparato.";
        }
        if (mantenimientoTerminado.getComprobar_estanqueidad_cierre_quemadores_caldera()==1){
            operaciones=operaciones+"\n"+"-Estanqueidad cierre entre"+"\n"+" quemadores y caldera.";
        }
        if (mantenimientoTerminado.getRevision_calderas_contadores()==1){
            operaciones=operaciones+"\n"+"-Revision del equipo de gas.";
        }
        if (mantenimientoTerminado.getVerificacion_circuito_hidraulico_calefaccion()==1){
            operaciones=operaciones+"\n"+"-Revision circuito hidraulico"+"\n"+" calefaccion.";
        }
        if (mantenimientoTerminado.getEstanqueidad_conexion_aparatos()==1){
            operaciones=operaciones+"\n"+"-Estanqueidad conexion aparatos.";
        }
        if (mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg()==1){
            operaciones=operaciones+"\n"+"-Estanqueidad conducto evac."+"\n"+" e IRG.";
        }
        if (mantenimientoTerminado.getComprobacion_niveles_agua()==1){
            operaciones=operaciones+"\n"+"-Revision niveles agua";
        }
        if (mantenimientoTerminado.getTipo_conducto_evacuacion()==1){
            operaciones=operaciones+"\n"+"-Tiro evacuacion.";
        }
        if (mantenimientoTerminado.getRevision_estado_aislamiento_termico()==1){
            operaciones=operaciones+"\n"+"-Revision aislamiento termico.";
        }
        if (mantenimientoTerminado.getAnalisis_productos_combustion()==1){
            operaciones=operaciones+"\n"+"-Analisis PDC";
        }
        if (mantenimientoTerminado.getCaudal_acs_calculo_potencia()==1){
            operaciones=operaciones+"\n"+"-Caudal ACS y calculo pot. util";
        }
        if (mantenimientoTerminado.getRevision_sistema_control()==1){
            operaciones=operaciones+"\n"+"-Revision del sist. control";
        }
        return operaciones;
    }
    private String datosMaquinas() throws SQLException {
        String datos_maquinas = "";
        for (int i = 0; i < maquinas.size(); i++) {
            String datos_instalacion = "--------DATOS INSTALACION-------" + "\n";
            String cod = maquinas.get(i).getCodigo_maquina();
            String codigo = "Codigo: "+cod+"\n";
            String mar = MarcaCalderaDAO.buscarNombreMarcaCalderaPorId(getContext(),maquinas.get(i).getFk_marca_maquina());
            String marca = "Marca: "+mar+"\n";
            String mode = maquinas.get(i).getModelo_maquina();
            String modelo = "Modelo: "+mode+"\n";
            String añ = maquinas.get(i).getPuesta_marcha_maquina();
            String año = "Fabricado: "+añ+"\n";
            String pot = PotenciaDAO.buscarNombrePotenciaPorId(getContext(),maquinas.get(i).getFk_potencia_maquina());
            String potencia = "Potencia: "+pot+"\n\n";
            String observaciones_tecnico = "-----------RESULTADO------------" + "\n";
            String tem_max_acs = maquinas.get(i).getTemperatura_max_acs();
            String temperatura_max_acs = "Temp. Max. ACS: "+"\n"+tem_max_acs+"\n";
            String caud_acs = maquinas.get(i).getCaudal_acs();
            String caudal_acs = "Caudal ACS: "+caud_acs+"\n";
            String pot_uti = maquinas.get(i).getPotencia_util();
            String potencia_util = "Potencia util: "+pot_uti+"\n";
            String tem_agu_ent = maquinas.get(i).getTemperatura_agua_generador_calor_entrada();
            String temp_agua_entrada = "Temp. agua entrada: "+tem_agu_ent+"\n";
            String tem_agu_sal = maquinas.get(i).getTemperatura_agua_generador_calor_salida();
            String temp_agua_salida = "Temp. agua salida: "+tem_agu_sal+"\n";
            String tem_gas_comb = maquinas.get(i).getTemperatura_gases_combustion();
            String temp_gases_combust = "Temp. gases combustion: "+tem_gas_comb+"\n";
            String rend_apar = "98.3%";
            String rendimiento_aparato = "Rendimiento aparato: "+rend_apar+ "\n";
            String co_cor = "88 ppm";
            String co_corregido = "CO corregido: "+co_cor+ "\n";
            String co_amb = maquinas.get(i).getC0_maquina();
            String co_ambiente = "CO ambiente: "+co_amb+ "\n";
            String tir = "-.014 mbar";
            String tiro = "Tiro: "+tir+ "\n";
            String c2 = "9.01 %";
            String co2 = "CO2: "+c2+ "\n";
            String o02 = "5.1 %";
            String o2 = "O2: "+o02+ "\n";
            String lamb = "1.32";
            String lambda = "Lambda: "+lamb+ "\n";
            String perd_chim = "";
            String perdidas_chimenea = "Perdidas por chimenea: "+perd_chim+ "\n"+"\n";
            datos_maquinas+=datos_maquinas+datos_instalacion+codigo+marca+modelo+año+potencia+observaciones_tecnico+
                    temperatura_max_acs+caudal_acs+potencia_util+temp_agua_entrada+temp_agua_salida+
                    temp_gases_combust+rendimiento_aparato+co_corregido+co_ambiente+tiro+co2+o2+
                    lambda+perdidas_chimenea;
        }
        if (equipamiento!=null){
            for (int i = 0; i < equipamiento.size(); i++) {
                String datos_equipamiento = "--------DATOS INSTALACION-------" + "\n";
                int equ = equipamiento.get(i).getFk_tipo_equipamiento();
                String tip_equ = "";
                String fuegos = "N. Fuegos/Potencia: "+equipamiento.get(i).getPotencia_fuegos()+"\n"+"\n";
                switch (equ){
                    case 1:
                        tip_equ = "Cocina"+"\n";
                        String observaciones_tecnico = "-----------RESULTADO------------" + "\n";
                        String co2 = "Co2 Ambiente: 20";
                        datos_maquinas+=datos_equipamiento+tip_equ+fuegos+observaciones_tecnico+co2;
                        break;
                    case 2:
                        tip_equ = "Horno"+"\n";
                        datos_maquinas+=datos_equipamiento+tip_equ+fuegos;
                        break;
                    case 3:
                        tip_equ = "Horno + Grill"+"\n";
                        datos_maquinas+=datos_equipamiento+tip_equ+fuegos;
                        break;
                }
            }
        }

        return datos_maquinas;
    }
}
