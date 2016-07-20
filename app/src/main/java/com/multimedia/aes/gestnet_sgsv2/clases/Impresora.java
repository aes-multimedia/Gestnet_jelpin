package com.multimedia.aes.gestnet_sgsv2.clases;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloConectarImpr;
import com.sewoo.jpos.POSPrinterService;
import com.sewoo.port.android.BluetoothPort;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;

import jpos.JposException;
import jpos.POSPrinterConst;

public class Impresora {
	public Activity activity;
	private BluetoothAdapter bluetoothAdapter;
	public BluetoothPort bp;
	private String textoImpresion = "";
	private int ancho_sewoo_seleccionado = 32;
	private String ocupados;
	private Resources res;
	public Thread hThread;
	BluetoothDevice mmDevice;
	private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
	private char chEuro = '€';
	String c = Character.toString(chEuro);

	public Impresora(Activity activity, BluetoothDevice mmDevice) {
		super();
		this.activity = activity;
		this.mmDevice = mmDevice;
		res = activity.getResources();
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		bp = BluetoothPort.getInstance();
	}
	public void imprimir() {
		iniciarConexion();
		HiloConectarImpr hci = new HiloConectarImpr(this, activity);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB)
			hci.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mmDevice);
		else
			hci.execute(mmDevice);
	}
	private void iniciarConexion() {
		if (!bluetoothAdapter.isEnabled()) {
			bluetoothAdapter.enable();
			try {
				Thread.sleep(3600);
			} catch (Exception e) {
			}
		}
	}
	public void realizarImpresion() {
		POSPrinterService pps = new POSPrinterService();
		try {
			imprimirImagenEncabezado(pps);
			generarTexto1(pps);
			imprimirFirma(pps);
			generarTexto2(pps);
			imprimirFirma(pps);
			generarTexto3(pps);
			imprimirFirma(pps);
			generarTexto4(pps);
			bluetoothAdapter.disable();
		} catch (IOException | JposException | SQLException | InterruptedException e) {
			Toast.makeText(activity, R.string.err_durante_impr, Toast.LENGTH_SHORT).show();
		}
	}
	private void generarTexto1(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
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
		String otr = "6 Km 31 euros"+"\n"+"2-12 euros";
		String otros = "Otros: "+otr+"\n";
		String desc_preiva = "0%";
		String descuentos_preiva = "Descuentos antes de iva: "+"\n"+desc_preiva+"\n";
		String mat = "6 x 29 euros";
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
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, textoImpresion);
		Thread.sleep(2000);
	}
	private void generarTexto2(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
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
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, textoImpresion);
		Thread.sleep(2000);
	}
	private void generarTexto3(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
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
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, textoImpresion);
		Thread.sleep(2000);
	}
	private void generarTexto4(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
		String observaciones_cliente = "-------OBSERVAC. TECNICO--------" + "\n";
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
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, textoImpresion);
		Thread.sleep(2000);
	}
	private Bitmap loadImageFromStorage(){
		Bitmap b=null;
		try {
			File f=new File(path, "profile.jpg");
			b = BitmapFactory.decodeStream(new FileInputStream(f));

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return b;
	}
	private void imprimirFirma(POSPrinterService pps) throws IOException, JposException, InterruptedException {
		int img[][] = null;
		int ancho = 1;
			Bitmap bit = loadImageFromStorage();
			img = new int[bit.getWidth()][bit.getHeight()];
			ancho = bit.getWidth();
			for (int i = 0; i < bit.getHeight(); i++) {
				for (int j = 0; j < bit.getWidth(); j++) {
					img[j][i] = bit.getPixel(j, i);
				}
			}
		pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
		Thread.sleep(2000);
	}
	private void imprimirImagenEncabezado(POSPrinterService pps) throws IOException, JposException, InterruptedException {
		InputStream bitmap = null;
		int img[][] = null;
		int ancho = 1;
		try {
			bitmap = activity.getAssets().open("logo.png");
			Bitmap bit = BitmapFactory.decodeStream(bitmap);
			img = new int[bit.getWidth()][bit.getHeight()];
			ancho = bit.getWidth();
			for (int i = 0; i < bit.getHeight(); i++) {
				for (int j = 0; j < bit.getWidth(); j++) {
					img[j][i] = bit.getPixel(j, i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			bitmap.close();
		}
		pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
		Thread.sleep(2000);
	}
	private String limpiarAcentos(String texto_entrada) {
		String original = "��������������u������������������Ǻ";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCo";
		String output = texto_entrada;
		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}
}
