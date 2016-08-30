package com.multimedia.aes.gestnet_sgsv2.fragment;

import android.app.Activity;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.clases.Impresora;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.nucleo.Firmar;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.ArrayList;
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
    private TextView txtImpreso,txtImpreso2,txtImpreso3,txtImpreso4;
    private LinearLayout llImpreso,llBotones;
    private Impresora impresora;
    private ImageView ivLogo,ivFirma1,ivFirma2,ivFirma3,ivCodigoBarras;
    private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
    private View vista;
    private ScrollView scTicket;
    private Mantenimiento mantenimiento;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        vista = inflater.inflate(R.layout.bluetooth, container, false);
        openButton = (Button) vista.findViewById(R.id.open);
        sendButton = (Button) vista.findViewById(R.id.send);
        closeButton = (Button) vista.findViewById(R.id.close);
        btnOtra = (Button) vista.findViewById(R.id.btnOtra);
        lvNombres = (ListView) vista.findViewById(R.id.lvNombres);
        txtImpreso = (TextView) vista.findViewById(R.id.txtImpreso);
        llImpreso = (LinearLayout) vista.findViewById(R.id.llImpreso);
        llBotones = (LinearLayout) vista.findViewById(R.id.llBotones);
        txtImpreso2 = (TextView) vista.findViewById(R.id.txtImpreso2);
        txtImpreso3 = (TextView) vista.findViewById(R.id.txtImpreso3);
        txtImpreso4 = (TextView) vista.findViewById(R.id.txtImpreso4);
        ivLogo = (ImageView) vista.findViewById(R.id.ivLogo);
        ivFirma1 = (ImageView) vista.findViewById(R.id.ivFirmaUno);
        ivFirma2 = (ImageView) vista.findViewById(R.id.ivFirmaDos);
        ivFirma3 = (ImageView) vista.findViewById(R.id.ivFirmaTres);
        ivCodigoBarras = (ImageView) vista.findViewById(R.id.ivCodigoBarras);

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
        txtImpreso.setText(generarTexto1());
        txtImpreso2.setText(generarTexto2());
        txtImpreso3.setText(generarTexto3());
        txtImpreso4.setText(generarTexto4());
        ivCodigoBarras.setImageBitmap(codigoBarras());
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
                ivFirma2.setImageBitmap(bitmap);
                ivFirma3.setImageBitmap(bitmap);
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                //Write your code if there's no result
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
        txtImpreso.setText(generarTexto1());
        txtImpreso2.setText(generarTexto2());
        txtImpreso3.setText(generarTexto3());
        txtImpreso4.setText(generarTexto4());
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
    private String generarTexto1()  {
        String fecha = "22/06/2016";
        String hora = "12:06";
        String fecha_hora = "\n\n"+"FECHA Y HORA: "+fecha+"-"+hora + "\n\n";
        String datos_cliente = "---------DATOS CLIENTE----------" + "\n";
        String nombre_cliente = "Maria Garcia Hinojosa" + "\n";
        String num_contrato = "000111522";
        String numero_contrato = "N. Contrato: "+num_contrato + "\n";
        String serv = "Mantenimiento Gas";
        String servicio = "Servicio: "+serv+ "\n";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String direccion = "Direccion"+"\n"+dir+"\n\n";
        String datos_tecnico = "---------DATOS TECNICO----------" + "\n";
        String emp = "IBERDROLA";
        String empresa = "Empresa: "+emp+"\n";
        String cif_emp = "02365474S";
        String cif = "CIF: "+cif_emp+"\n";
        String num_emp_mant = "44556678";
        String numero_empresa_mantenedora = "N. Empresa Mantenedora: "+"\n"+num_emp_mant+"\n";
        String tec = "Pedro Buenhombre Lopez";
        String tecnico = "Tecnico: "+tec+"\n";
        String num_insta = "659898741";
        String numero_instalador = "N. Instalador: "+num_insta+"\n\n";
        String datos_averia = "----------DATOS AVERIA----------" + "\n";
        String noti = "21/06/2016";
        String notificada = "Notificada: "+noti+"\n";
        String atend = "18/06/2016-14:00";
        String atendida = "Atendida: "+atend+"\n";
        String prev_repar = "26/06/2016-13:30";
        String prevista_reparacion = "Prevista reparacion: "+"\n"+prev_repar+"\n";
        String repa = "24/06/2016-12:48";
        String reparada = "Reparada: "+repa+"\n";
        String num_solic = "6547952";
        String numero_solicitud = "N. Solicitud: "+num_solic+"\n";
        String cod_ave = "3216565";
        String codigo_averia = "Codigo Averia: "+cod_ave+"\n";
        String desc = "Una averia sin importancia";
        String descripcion = "Descripcion: "+"\n"+desc+"\n\n";

        String presupuesto = "----------PRESUPUESTO-----------" + "\n";
        String piez = "Junta caldera  5 euros";
        String piezas = "Piezas: "+piez+"\n";
        String man_obra = "2 piezas: 20 euros";
        String mano_obra = "Mano de obra: "+man_obra+"\n";
        String despl = "5 horas:  24 euros";
        String desplazamiento = "Desplazamiento: "+"\n"+despl+"\n";
        String otr = "6 Km 31 euros"+"\n"+"2 Km 12 euros";
        String otros = "Otros: "+otr+"\n";
        String desc_preiva = "0%";
        String descuentos_preiva = "Descuentos antes de iva: "+"\n"+desc_preiva+"\n";
        String mat = "6 piezas: 29 euros";
        String materiales = "Materiales: "+mat+"\n";
        String pres_tot_siniva = "95 euros";
        String presupuesto_total_siniva = "Presupuesto total sin iva: "+"\n"+pres_tot_siniva+"\n";
        String iv = "21%";
        String iva = "IVA: "+iv+"\n";
        String pres_tot_coniva = "102 euros";
        String presupuesto_total_coniva = "Presupuesto total con iva: "+"\n"+pres_tot_coniva+"\n";
        String otr_desc = "0%";
        String otros_descuentos = "Otros descuentos: "+otr_desc+"\n";
        String tot = "102 euros";
        String total = "TOTAL A PAGAR: "+tot+"\n\n";
        String observaciones_tecnico = "-------OBSERVAC. TECNICO--------" + "\n";
        String obs_tecnico = "La maquina es antigüa";
        String observ_tecnico = obs_tecnico+"\n\n";
        String recepcion_presup_cliente = "---RECEPCION PRESUP. CLIENTE----" + "\n";
        String fec_recep = "22/06/2016-13:00";
        String fecha_recep = "Fecha: "+fec_recep+"\n";
        String nom = "Alejandro Perez Lopez";
        String nombre = "Nombre: "+nom+"\n";
        String dn = "02365984K";
        String dni = "DNI: "+dn+"\n";
        String firma = "Firma:"+"\n";

        String textoImpresion =fecha_hora+datos_cliente+nombre_cliente+numero_contrato+servicio+direccion+
                datos_tecnico+empresa+cif+numero_empresa_mantenedora+tecnico+numero_instalador+datos_averia+
                notificada+atendida+prevista_reparacion+reparada+numero_solicitud+codigo_averia+descripcion+
                presupuesto+piezas+mano_obra+desplazamiento+otros+descuentos_preiva+materiales+
                presupuesto_total_siniva+iva+presupuesto_total_coniva+otros_descuentos+total+observaciones_tecnico+
                observ_tecnico+recepcion_presup_cliente+fecha_recep+nombre+dni+firma;

       return textoImpresion;
    }
    private String generarTexto2()  {
        String aceptacion_presupuesto = "-----ACEPTACION PRESUPUESTO-----" + "\n";
        String recibido = "* Recibido antes de la"+"\n"+" realizacion de los trabajos."+"\n";
        String aceptado = "* Una vez aceptado, el"+"\n"+" presupuesto hara de orden de"+"\n"+" trabajo."+"\n";
        String fec_acep = "22/06/2016-13:00";
        String fecha_acep = "Fecha: "+fec_acep+"\n";
        String nom_acep = "Alejandro Perez Lopez";
        String nombre_acep = "Nombre: "+nom_acep+"\n";
        String dn_acep = "02365984K";
        String dni_acep = "DNI: "+dn_acep+"\n";
        String firma_acep = "Firma:"+"\n";
        String textoImpresion =aceptacion_presupuesto+recibido+
                aceptado+fecha_acep+nombre_acep+dni_acep+firma_acep;
       return textoImpresion;
    }
    private String generarTexto3() {
        String conforme_final_cliente = "-----CONFORME FINAL CLIENTE-----" + "\n";
        String fec_conf = "22/06/2016-13:00";
        String fecha_conf = "Fecha: "+fec_conf+"\n";
        String nom_conf = "Alejandro Perez Lopez";
        String nombre_conf = "Nombre: "+nom_conf+"\n";
        String dn_conf = "02365984K";
        String dni_conf = "DNI: "+dn_conf+"\n";
        String firma_conf = "Firma:"+"\n";
        String textoImpresion =conforme_final_cliente+fecha_conf+nombre_conf+
                dni_conf+firma_conf;
     return textoImpresion;
    }
    private String generarTexto4()  {
        String observaciones_cliente = "-------OBSERVAC. CLIENTE--------" + "\n";
        String obs_cliente = "";
        String observ_cliente = obs_cliente+"\n\n";

        String info = "-------------INFO.--------------" + "\n";
        String validez = "*Validez del presupuesto: 30"+"\n"+ " dias desde la fecha de" +"\n"+ " recepcion."+"\n";
        String garantia = "*Garantia de los trabajos" +"\n"+ " realizados: 6 meses desde la"+"\n"+" finalizacion"+"\n";
        String sustitu = "*No se sustituiran"+"\n"+" innecesariamente piezas o"+"\n"+" materiales si con ello se"+"\n"+
                " incrementan los costes del"+"\n"+" servicio o se degradan los"+"\n"+" bienes objeto de la prestacion." +"\n"+
                " Cualquier variacion del importe"+"\n"+" del presupuesto inicial debera"+"\n"+" ponerse en conocimiento del"+"\n"+
                " usuario por escrito y de modo"+"\n"+" desglosado. No cabra"+"\n"+" modificacion al alza del"+"\n"+
                " presupuesto en los casos de"+"\n"+" errores en las mediciones y"+"\n"+" valoraciones efectuadas por el"+"\n"+
                " tecnico. Las modificaciones"+"\n"+" deberan ser firmadas por ambas"+"\n"+" partes en senal de conformidad."+"\n";
        String reclamacion = "*Existen hojas de reclamaciones"+"\n"+" a disposicion del cliente."+"\n\n\n\n\n\n";
        String textoImpresion =observaciones_cliente+observ_cliente+info+validez+garantia+sustitu+reclamacion;
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
    private Bitmap codigoBarras(){
        byte[] a = Base64.decode(mantenimiento.getBase64(),Base64.DEFAULT);
        Bitmap bit = BitmapFactory.decodeByteArray(a, 0, a.length);
        return bit;
    }

}
