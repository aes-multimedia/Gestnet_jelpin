package com.multimedia.aes.gestnet_sgsv2.clases;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Base64;
import android.util.Log;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dialog.ManagerProgressDialog;
import com.multimedia.aes.gestnet_sgsv2.entities.EquipamientoCaldera;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;
import com.multimedia.aes.gestnet_sgsv2.entities.Maquina;
import com.multimedia.aes.gestnet_sgsv2.entities.Tecnico;
import com.multimedia.aes.gestnet_sgsv2.hilos.HiloConectarImpr;
import com.sewoo.jpos.POSPrinterService;
import com.sewoo.port.android.BluetoothPort;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import jpos.JposException;
import jpos.POSPrinterConst;

public class Impresora {
	public Activity activity;
	private BluetoothAdapter bluetoothAdapter;
	public BluetoothPort bp;
	private int ancho_sewoo_seleccionado = 32;
	private String ocupados;
	private Resources res;
	public Thread hThread;
	BluetoothDevice mmDevice;
	private String path = "/data/data/com.multimedia.aes.gestnet_sgsv2/app_imageDir";
	private Mantenimiento mantenimiento;
	private Tecnico tecnico;
	private MantenimientoTerminado mantenimientoTerminado;
	private List<Maquina> maquinas;
	private List<EquipamientoCaldera> equipamiento;

	public Impresora(Activity activity, BluetoothDevice mmDevice) {
		super();
		this.activity = activity;
		this.mmDevice = mmDevice;
		res = activity.getResources();
		bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
		bp = BluetoothPort.getInstance();
		try {
			JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(activity));
			int id = jsonObject.getInt("id");
			mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(activity,id);
			mantenimientoTerminado = MantenimientoTerminadoDAO.buscarMantenimientoTerminadoPorfkParte(activity,id);
			tecnico = TecnicoDAO.buscarTodosLosTecnicos(activity).get(0);
			maquinas = MaquinaDAO.buscarMaquinaPorFkMantenimiento(activity,mantenimiento.getId_mantenimiento());
			equipamiento = EquipamientoCalderaDAO.buscarEquipamientoCalderaPorIdMantenimiento(activity,mantenimiento.getId_mantenimiento());
		} catch (JSONException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void imprimir() {
		iniciarConexion();
		HiloConectarImpr hci = new HiloConectarImpr(this, activity);
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			hci.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mmDevice);
		}else {
			hci.execute(mmDevice);
		}
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
			generarTextoFin(pps);
			imprimirFirma(pps);
			imprimirCodigoBarras(pps);
			generarSaltoFinal(pps);
			bluetoothAdapter.disable();
		} catch (IOException | JposException | SQLException | InterruptedException e) {
			Toast.makeText(activity, R.string.err_durante_impr, Toast.LENGTH_SHORT).show();
		}
	}
	private void generarTexto1(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
		Calendar cal = new GregorianCalendar();
		Date date = cal.getTime();
		SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		String fecha = df.format(date);
		df = new SimpleDateFormat("hh:mm");
		String hora = df.format(date);
		String fecha_hora = "\n\n"+"FECHA Y HORA: "+fecha+"-"+hora + "\n\n";
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
		String datos_averia = "----------DATOS VISITA----------" + "\n";
		String noti = "Visita realizada cumpliendo los"+"\n"+"requisitos de la IT.3 del RITE";
		String notificada = ""+noti+"\n\n";
		String presupuesto = "-----OPERACIONES REALIZADAS-----" + "\n";
		String op = operaciones();
		String operaciones = op+"\n";
		String maquina = datosMaquinas()+"\n\n";
		String anomalias_detectadas = "ANOMALIAS DETECTADAS: "+"\n";
		String anom = "";
		if (!mantenimientoTerminado.isAnomalia()){
			anom = "Sin Anomalias";
		}else {
			anom = TiposVisitaDAO.buscarNombreTipoVisitaPorId(activity,mantenimientoTerminado.getFk_tipo_visita())+"\n";
			if (mantenimientoTerminado.getFk_subtipo_visita()!=-1){
				anom += SubTiposVisitaDAO.buscarCodigoSubTipoVisitaPorId(activity,mantenimientoTerminado.getFk_subtipo_visita())+"\n";
				anom+= SubTiposVisitaDAO.buscarSubTiposVisitaPorId(activity,mantenimientoTerminado.getFk_subtipo_visita()).getDescripcion();
			}else{
				anom = "otras anomalias";
			}
		}

		String anomalias = anom+"\n\n";
		String comun = "*Se comunica la cliente, y este"+"\n"+"declara quedar informado que la"+"\n"+
				"correccion de las posibles"+"\n"+"anomalias detectadas durante"+"\n"+
				"esta visita, sean principales o"+"\n"+"secundarias, es de su exclusiva"+"\n"+"responsabilidad segun Real"+"\n"+
				"Decreto 919/2006,de 28 de julio."+"\n";
		String comuni = "*En caso de existir anomalias"+"\n"+"principales no corregidas, estas"+"\n"+
				"pueden ser informadas a la"+"\n"+"empresa distribuidora y/o"+"\n"+"autoridad competente."+"\n";
		String observaciones_tecnico="-----OBSERVACIONES TECNICO------";
		String obs = "Falta rejilla superior \n";
		String firma_tecnico = "Firma Tecnico:"+"\n\n\n\n\n\n\n";
		String textoImpresion =fecha_hora+datos_cliente+nombre_cliente+numero_contrato+direccion+
				datos_tecnico+empresa+cif+numero_empresa_mantenedora+tecnic+numero_instalador+
				datos_averia+notificada+presupuesto+operaciones+maquina+anomalias_detectadas+
				anomalias+comun+comuni+observaciones_tecnico+obs+firma_tecnico;
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, limpiarAcentos(textoImpresion));
		Thread.sleep(6000);
	}
	private void generarTextoFin(POSPrinterService pps) throws JposException, InterruptedException {
		String conforme_cliente="--------CONFORME CLIENTE--------"+"\n";
		String obs = mantenimiento.getObservaciones_usuario();
		String observaciones = "Observaciones: "+obs+"\n";
		String nom = mantenimiento.getNombre_usuario();
		String nombre = "Nombre: "+nom+"\n";
		String dn = mantenimiento.getDni_usuario();
		String dni = "Dni: "+dn+"\n";
		String firma_cliente="Firma Cliente"+"\n";

		String textoImpresion =conforme_cliente+observaciones+nombre+dni+firma_cliente;
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, limpiarAcentos(textoImpresion));
		Thread.sleep(2000);
	}
	private void generarSaltoFinal(POSPrinterService pps) throws JposException, InterruptedException {
		String textoImpresion = "\n\n\n\n\n\n\n";
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, limpiarAcentos(textoImpresion));
		Thread.sleep(2000);
	}
	private Bitmap loadImageFromStorage(){
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
	private void imprimirCodigoBarras(POSPrinterService pps) throws InterruptedException, IOException, JposException {
		int img[][] = null;
		int ancho = 1;
		byte[] a =Base64.decode(mantenimiento.getBase64(),Base64.DEFAULT);
		Bitmap bit = BitmapFactory.decodeByteArray(a, 0, a.length);
		img = new int[bit.getWidth()][bit.getHeight()];
		ancho = bit.getWidth();
		for (int i = 0; i < bit.getHeight(); i++) {
			for (int j = 0; j < bit.getWidth(); j++) {
				img[j][i] = bit.getPixel(j, i);
			}
		}
		pps.printBitmap(POSPrinterConst.PTR_S_RECEIPT, img, ancho, POSPrinterConst.PTR_BM_LEFT);
		Thread.sleep(3000);
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
		Thread.sleep(4000);
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
		Thread.sleep(3000);
	}
	private String limpiarAcentos(String texto_entrada) {
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇº€";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCoE";
		String output = texto_entrada;
		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}
	private String operaciones(){
		String operaciones = "";
		if (mantenimientoTerminado.getLimpieza_quemadores_caldera()==1){
			operaciones=operaciones+"-Limpieza del quemador"+"\n"+"de la caldera.";
		}
		if (mantenimientoTerminado.getRevision_vaso_expansion()==1){
			operaciones=operaciones+"\n"+"-Revision del vaso de expansion.";
		}
		if (mantenimientoTerminado.getRegulacion_aparatos()==1){
			operaciones=operaciones+"\n"+"-Regulacion de aparatos.";
		}
		if (mantenimientoTerminado.getComprobar_estanqueidad_cierre_quemadores_caldera()==1){
			operaciones=operaciones+"\n"+"-Comprobar estanqueidad de"+"\n"+"cierre entre quemadores y"+"\n"+" caldera.";
		}
		if (mantenimientoTerminado.getRevision_calderas_contadores()==1){
			operaciones=operaciones+"\n"+"-Revision general de calderas"+"\n"+"y/o calentadores.";
		}
		if (mantenimientoTerminado.getVerificacion_circuito_hidraulico_calefaccion()==1){
			operaciones=operaciones+"\n"+"-Verificacion del circuito"+"\n"+"hidraulico de calefaccion.";
		}
		if (mantenimientoTerminado.getEstanqueidad_conexion_aparatos()==1){
			operaciones=operaciones+"\n"+"-Estanqueidad de la conexion de"+"\n"+"los aparatos.";
		}
		if (mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg()==1){
			operaciones=operaciones+"\n"+"-Estanqueidad del conducto de"+"\n"+"evacuacion y de la IRG.";
		}
		if (mantenimientoTerminado.getComprobacion_niveles_agua()==1){
			operaciones=operaciones+"\n"+"-Comprobacion de niveles de agua";
		}
		if (mantenimientoTerminado.getTipo_conducto_evacuacion()==1){
			operaciones=operaciones+"\n"+"-Tipo de conducto de evacuacion.";
		}
		if (mantenimientoTerminado.getRevision_estado_aislamiento_termico()==1){
			operaciones=operaciones+"\n"+"-Revision del estado del"+"\n"+"aislamiento termico.";
		}
		if (mantenimientoTerminado.getAnalisis_productos_combustion()==1){
			operaciones=operaciones+"\n"+"-Analisis de los productos de"+"\n"+"la combustion.";
		}
		if (mantenimientoTerminado.getCaudal_acs_calculo_potencia()==1){
			operaciones=operaciones+"\n"+"-Caudal de ACS y calculo de"+"\n"+"potencia util.";
		}
		if (mantenimientoTerminado.getRevision_sistema_control()==1){
			operaciones=operaciones+"\n"+"-Revision del sistema de control";
		}
		return operaciones;
	}
	private String datosMaquinas() throws SQLException {
		String datos_maquinas = "";
		for (int i = 0; i < maquinas.size(); i++) {
			String datos_instalacion = "--------DATOS INSTALACION-------" + "\n";
			String cod = maquinas.get(i).getCodigo_maquina();
			String codigo = "Codigo: "+cod+"\n";
			String mar = MarcaCalderaDAO.buscarNombreMarcaCalderaPorId(activity,maquinas.get(i).getFk_marca_maquina());
			String marca = "Marca: "+mar+"\n";
			String mode = maquinas.get(i).getModelo_maquina();
			String modelo = "Modelo: "+mode+"\n";
			String añ = maquinas.get(i).getPuesta_marcha_maquina();
			String año = "Fabricado: "+añ+"\n";
			String pot = PotenciaDAO.buscarNombrePotenciaPorId(activity,maquinas.get(i).getFk_potencia_maquina());
			String potencia = "Potencia: "+pot+"\n\n";
			String observaciones_tecnico = "-----------RESULTADO------------" + "\n";
			String tem_max_acs = maquinas.get(i).getTemperatura_max_acs();
			String temperatura_max_acs = "Temp. Max. ACS: "+"\n"+tem_max_acs+"\n";
			String caud_acs = maquinas.get(i).getCaudal_acs();
			String caudal_acs = "Caudal ACS: "+caud_acs+"\n";
			String pot_uti = maquinas.get(i).getPotencia_util();
			String potencia_util = "Potencia util: "+pot_uti+"\n";
			String tem_agu_ent = maquinas.get(i).getTemperatura_agua_generador_calor_entrada();
			String temp_agua_entrada = "Temp. agua entrada"+tem_agu_ent+"\n";
			String tem_agu_sal = maquinas.get(i).getTemperatura_agua_generador_calor_salida();
			String temp_agua_salida = "Temp. agua salida"+tem_agu_sal+"\n";
			String tem_gas_comb = maquinas.get(i).getTemperatura_gases_combustion();
			String temp_gases_combust = "Temp. gases combustion"+tem_gas_comb+"\n";
			String rend_apar = "98.3 %";
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
			String lamb = "1.32	";
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
				String datos_equipamiento = "-------DATOS EQUIPAMIENTO-------" + "\n";
				int equ = equipamiento.get(i).getFk_tipo_equipamiento();
				String tip_equ = "";
				String fuegos = "N. Fuegos/Potencia: "+equipamiento.get(i).getPotencia_fuegos()+"\n"+"\n";
				switch (equ){
					case 1:
						tip_equ = "Cocina"+"\n";
						break;
					case 2:
						tip_equ = "Horno"+"\n";
						break;
					case 3:
						tip_equ = "Horno + Grill"+"\n";
						break;
				}
				datos_maquinas+=datos_equipamiento+tip_equ+fuegos;
			}
		}
		return datos_maquinas;
	}
}
