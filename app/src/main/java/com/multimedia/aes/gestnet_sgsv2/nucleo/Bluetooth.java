package com.multimedia.aes.gestnet_sgsv2.nucleo;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.clases.Impresora;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class Bluetooth extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothSocket mmSocket;
    private BluetoothDevice mmDevice;
    private OutputStream mmOutputStream;
    private InputStream mmInputStream;
    private ArrayList<BluetoothDevice> listaDevice = new ArrayList<>();
    private ArrayList<String> listaNombre = new ArrayList<>();
    private ListView lvNombres;
    private Button openButton, sendButton, closeButton;
    private TextView txtImpreso;
    private LinearLayout llImpreso;
    private Impresora impresora;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bluetooth);
        openButton = (Button) findViewById(R.id.open);
        sendButton = (Button) findViewById(R.id.send);
        closeButton = (Button) findViewById(R.id.close);
        lvNombres = (ListView) findViewById(R.id.lvNombres);
        txtImpreso = (TextView) findViewById(R.id.txtImpreso);
        llImpreso = (LinearLayout) findViewById(R.id.llImpreso);

        lvNombres.setOnItemClickListener(this);
        openButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        sendButton.setVisibility(View.GONE);
        closeButton.setVisibility(View.GONE);
        llImpreso.setVisibility(View.GONE);
        findBT();
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
        }
    };

    @Override
    public void onClick(View view) {
        try {
            if (view.getId() == R.id.open) {
                ManagerProgressDialog.abrirDialog(this);
                ManagerProgressDialog.buscandoBluetooth(this);
                listaDevice.clear();
                listaNombre.clear();
                findBT();
                IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
                registerReceiver(bReciever, filter);
                mBluetoothAdapter.startDiscovery();
                sendButton.setVisibility(View.VISIBLE);
                closeButton.setVisibility(View.VISIBLE);
                openButton.setVisibility(View.GONE);
            } else if (view.getId() == R.id.send) {
                impresora = new Impresora(this,mmDevice);
                impresora.imprimir();
                sendButton.setVisibility(View.GONE);
                closeButton.setVisibility(View.GONE);
                openButton.setVisibility(View.VISIBLE);
                lvNombres.setVisibility(View.VISIBLE);
                llImpreso.setVisibility(View.GONE);
            } else if (view.getId() == R.id.close) {
                closeBT();
                llImpreso.setVisibility(View.GONE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
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
    }
    private String printStruk() {
        String fecha = "22/06/2016";
        String hora = "12:06";
        String fecha_hora = "FECHA Y HORA: "+fecha+"-"+hora + "\n\n";
        String datos_cliente = "---------DATOS CLIENTE---------" + "\n";
        String nombre_cliente = "Maria Garcia Hinojosa" + "\n";
        String num_contrato = "000111522";
        String numero_contrato = "N. Contrato: "+num_contrato + "\n";
        String serv = "Mantenimiento Gas";
        String servicio = "Servicio: "+serv+ "\n";
        String dir = "Calle Ribadavia 11,2-A,"+"\n"+"Madrid,Madrid,20156";
        String direccion = "Direccion"+"\n"+dir+"\n\n";
        String datos_tecnico = "---------DATOS TECNICO---------" + "\n";
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

        String presupuesto = "----------PRESUPUESTO----------" + "\n";
        String piez = "Junta caldera-5 \u20ac";
        String piezas = "Piezas: "+piez+"\n";
        String man_obra = "2-20 \u20ac";
        String mano_obra = "Mano de obra: "+man_obra+"\n";
        String despl = "35-24 \u20ac";
        String desplazamiento = "Desplazamiento: "+"\n"+despl+"\n";
        String otr = "96-31 \u20ac"+"\n"+"2-12 \u20ac";
        String otros = "Otros: "+otr+"\n";
        String desc_preiva = "0%";
        String descuentos_preiva = "Descuentos antes de iva: "+"\n"+desc_preiva+"\n";
        String mat = "6-29 \u20ac";
        String materiales = "Materiales: "+mat+"\n";
        String pres_tot_siniva = "95 \u20ac";
        String presupuesto_total_siniva = "Presupuesto total sin iva: "+"\n"+pres_tot_siniva+"\n";
        String iv = "21%";
        String iva = "IVA: "+iv+"\n";
        String pres_tot_coniva = "102 \u20ac";
        String presupuesto_total_coniva = "Presupuesto total con iva: "+"\n"+pres_tot_coniva+"\n";
        String otr_desc = "0%";
        String otros_descuentos = "Otros descuentos: "+otr_desc+"\n";
        String tot = "102 \u20ac";
        String total = "TOTAL A PAGAR: "+tot+"\n\n";
        String observaciones_tecnico = "-------OBSERVAC. TECNICO-------" + "\n";
        String obs_tecnico = "La maquina es antigüa";
        String observ_tecnico = obs_tecnico+"\n\n";
        String recepcion_presup_cliente = "---RECEPCION PRESUP. CLIENTE---" + "\n";
        String fec_recep = "22/06/2016-13:00";
        String fecha_recep = "Fecha: "+fec_recep+"\n";
        String nom = "Alejandro Perez Lopez";
        String nombre = "Nombre: "+nom+"\n";
        String dn = "02365984K";
        String dni = "DNI: "+dn+"\n";
        String firma = "Firma:"+"\n\n\n\n\n\n";
        String aceptacion_presupuesto = "-----ACEPTACION PRESUPUESTO-----" + "\n";
        String recibido = "* Recibido antes de la realizacion de los trabajos."+"\n";
        String aceptado = "* Una vez aceptado, el presupuesto hara de orden de trabajo."+"\n";
        String fec_acep = "22/06/2016-13:00";
        String fecha_acep = "Fecha: "+fec_acep+"\n";
        String nom_acep = "Alejandro Perez Lopez";
        String nombre_acep = "Nombre: "+nom_acep+"\n";
        String dn_acep = "02365984K";
        String dni_acep = "DNI: "+dn_acep+"\n";
        String firma_acep = "Firma:"+"\n\n\n\n\n\n";
        String conforme_final_cliente = "-----CONFORME FINAL CLIENTE-----" + "\n";
        String fec_conf = "22/06/2016-13:00";
        String fecha_conf = "Fecha: "+fec_conf+"\n";
        String nom_conf = "Alejandro Perez Lopez";
        String nombre_conf = "Nombre: "+nom_conf+"\n";
        String dn_conf = "02365984K";
        String dni_conf = "DNI: "+dn_conf+"\n";
        String firma_conf = "Firma:"+"\n\n\n\n\n\n"+"\n\n";
        String observaciones_cliente = "-------OBSERVAC. TECNICO-------" + "\n";
        String obs_cliente = "";
        String observ_cliente = obs_cliente+"\n\n";
        String validez = "* Validez del presupuesto: 30 dias desde la fecha de recepcion."+"\n";
        String garantia = "* Garantia de los trabajos realizados: 6 meses desde la finalizacion"+"\n";
        String sustitu = "* No se sustituiran innecesariamente piezas o materiales si con ello se " +
                "incrementan los costes del servicio o se degradan los bienes objeto de la prestacion." +
                "Cualquier variacion del importe del presupuesto inicial debera ponerse en conocimiento del usuario" +
                "por escrito y de modo desglosado. No cabra modificacion al alza del presupuesto en los casos" +
                "de errores en las mediciones y valoraciones efectuadas por el tecnico. Las modificaciones deberan ser" +
                " firmadas por ambas partes en señal de conformidad."+"\n";
        String reclamacion = "* Existen hojas de reclamaciones a disposicion del cliente."+"\n\n\n\n\n\n";

        String resultado = fecha_hora+datos_cliente+nombre_cliente+numero_contrato+servicio+direccion+
                datos_tecnico+empresa+cif+numero_empresa_mantenedora+tecnico+numero_instalador+datos_averia+
                notificada+atendida+prevista_reparacion+reparada+numero_solicitud+codigo_averia+descripcion+
                presupuesto+piezas+mano_obra+desplazamiento+otros+descuentos_preiva+materiales+
                presupuesto_total_siniva+iva+presupuesto_total_coniva+otros_descuentos+total+observaciones_tecnico+
                observ_tecnico+recepcion_presup_cliente+fecha_recep+nombre+dni+firma+aceptacion_presupuesto+recibido+
                aceptado+fecha_acep+nombre_acep+dni_acep+firma_acep+conforme_final_cliente+fecha_conf+nombre_conf+
                dni_conf+firma_conf+observaciones_cliente+observ_cliente+validez+garantia+sustitu+reclamacion;
        return  resultado;
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
        ArrayAdapter<String> adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, listaNombre);
        lvNombres.setAdapter(adaptador);
    }
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        lvNombres.setVisibility(View.GONE);
        llImpreso.setVisibility(View.VISIBLE);
        sendButton.setVisibility(View.VISIBLE);
        closeButton.setVisibility(View.VISIBLE);
        openButton.setVisibility(View.GONE);
        txtImpreso.setText(printStruk());
        mmDevice = listaDevice.get(adapterView.getPositionForView(view));
    }
}
