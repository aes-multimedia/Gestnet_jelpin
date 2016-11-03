package com.multimedia.aes.gestnet_sgsv2.clases;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Build;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.dao.EquipamientoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoTerminadoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MarcaCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.MotivosNoRepDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.PotenciaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.SubTiposVisitaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TecnicoDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TipoCalderaDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposReparacionesDAO;
import com.multimedia.aes.gestnet_sgsv2.dao.TiposVisitaDAO;
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
import java.sql.SQLException;
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
			generarCodigoBarras(pps);
			imprimirImagenEncabezado(pps);
			generarTexto1(pps);
			imprimirFirmaTecnico(pps);
			generarTextoFin(pps);
			imprimirFirmaCliente(pps);
			generarSaltoFinal(pps);
			bluetoothAdapter.disable();
		} catch (IOException | JposException | SQLException | InterruptedException e) {
			Toast.makeText(activity, R.string.err_durante_impr, Toast.LENGTH_SHORT).show();
		}
	}
	private void generarCodigoBarras(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
		String cod_barras = mantenimiento.getCod_barras();
		String datos_cliente = "   "+cod_barras+"   " + "\n";
		String textoImpresion =datos_cliente;
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, limpiarAcentos(textoImpresion));
		Thread.sleep(1000);
	}
	private void generarTexto1(POSPrinterService pps) throws JposException, SQLException, IOException, InterruptedException {
		String fecha = mantenimientoTerminado.getFecha_ticket();
		String hora = mantenimientoTerminado.getHora_ticket();
		String fecha_hora = "FECHA Y HORA: "+fecha+"-"+hora + "\n";
		String gps="Long:43.283594 Lat:-3.955325"+"\n";
		String datos_cliente = "---------DATOS CLIENTE----------" + "\n";
		String nombre_cliente = mantenimiento.getNombre_usuario() + "\n";
		String num_contrato = mantenimiento.getNum_orden_endesa();
		String numero_contrato = "N. Contrato: "+num_contrato + "\n";
		String dir = mantenimiento.getDireccion()+"\n"+mantenimiento.getCod_postal()+" - "+mantenimiento.getMunicipio();
		String direccion = "Direccion: "+"\n"+dir+"\n";
		String datos_tecnico = "---------DATOS TECNICO----------" + "\n";
		String emp = "ICISA";
		String empresa = "Empresa: "+emp+"\n";
		String cif_emp = "05954765L";
		String cif = "CIF: "+cif_emp+"\n";
		String num_emp_mant = "44556678";
		String numero_empresa_mantenedora = "N. Emp. Mantenedora: "+num_emp_mant+"\n";
		String tec = tecnico.getNombre_usuario();
		String tecnic = "Tecnico: "+tec+"\n";
		String num_insta = "659898741";
		String numero_instalador = "N. Instalador: "+num_insta+"\n";
		String datos_averia ="";
		String notificada = "";
		if (mantenimiento.getFk_categoria_visita()!=1) {
			datos_averia = "----------DATOS VISITA----------" + "\n";
			String noti = "Visita realizada cumpliendo los" + "\n" + "requisitos de la IT.3 del RITE";
			notificada = "" + noti + "\n";
		}
		String presupuesto = "-----OPERACIONES REALIZADAS-----" + "\n";
		String op = operaciones();
		String operaciones = op;
		String maquina = datosMaquinas()+"\n";
		String anomalias_detectadas = "ANOMALIAS DETECTADAS: "+"\n";
		String anom = "";
		if (!mantenimientoTerminado.isAnomalia()){
			anom = "Sin Defectos."+"\n";
		}else {
			anom = TiposVisitaDAO.buscarNombreTipoVisitaPorId(activity,mantenimientoTerminado.getFk_tipo_visita())+"\n";
			if (mantenimientoTerminado.getFk_subtipo_visita()!=-1){
				anom += SubTiposVisitaDAO.buscarSubTiposVisitaPorId(activity,mantenimientoTerminado.getFk_subtipo_visita()).getDescripcion_ticket()+"\n";
			}else{
				anom = "Otras anomalias."+"\n";
			}
			if (mantenimientoTerminado.getReparacion()==1){
				anom+="Acepta reparacion."+"\n";
				anom+=mantenimientoTerminado.getObs_reparacion_iberdrola()+"\n";
				anom+="Num. Sol.: "+mantenimientoTerminado.getCod_visita_plataforma()+"\n";
			}else{
				anom+="No acepta reparacion."+"\n";
				String mot = MotivosNoRepDAO.buscarMotivosNoRepPorId(activity,mantenimientoTerminado.getFk_motivos_no_rep()).getMotivo();
				anom+=mot+"\n";
				anom+=mantenimientoTerminado.getObs_reparacion_iberdrola()+"\n";
				if (mantenimientoTerminado.getFk_motivos_no_rep()!=4){
					anom+="Num. Sol.: "+mantenimientoTerminado.getCod_visita_plataforma()+"\n";
					String preci = "";
					if (mantenimientoTerminado.getPrecintado()==1){
						preci+="Acepta Precinto: Si";
					}else if (mantenimientoTerminado.getPrecintado()==0){
						preci+="Acepta Precinto: No";
					}
					anom+=preci+"\n";
				}
			}
			anom+="";
		}
		String anomalias = anom+"\n";
		String comun = "";
		String comuni = "";
		if(mantenimientoTerminado.isAnomalia()) {
			comun = "*Se comunica la cliente, y este" + "\n" + " declara quedar informado que la" + "\n" +
					" correccion de las posibles" + "\n" + " anomalias detectadas durante" + "\n" +
					" esta visita, sean principales o" + "\n" + " secundarias, es de su exclusiva" + "\n" + " responsabilidad segun Real" + "\n" +
					" Decreto 919/2006,de 28 de julio" + "\n";
			comuni = "*En caso de existir anomalias" + "\n" + " principales no corregidas," + "\n" +
					" estas pueden ser informadas a" + "\n" + " la empresa distribuidora y/o" + "\n" + " autoridad competente." + "\n";
		}
		String observaciones_tecnico="-----OBSERVACIONES TECNICO------"+"\n";
		String obs = "";
		if (mantenimientoTerminado.isAcciones()){
			obs += "Accion realizada: "+ TiposReparacionesDAO.buscarTiposReparacionesPorId(activity,mantenimientoTerminado.getFk_tipo_reparacion()).getAbreviatura()+"\n";
		}
		if (mantenimientoTerminado.getObservaciones_tecnico()!=null){
			obs+= mantenimientoTerminado.getObservaciones_tecnico()+"\n";
		}
		String firma_tecnico = "Firma Tecnico:"+"\n";
		String textoImpresion =fecha_hora+gps+datos_cliente+nombre_cliente+numero_contrato+direccion+
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
	private String operaciones(){
		String operaciones = "";
		if (mantenimientoTerminado.getLimpieza_quemadores_caldera()==1){
			operaciones=operaciones+"-Limpieza quemador"+"\n";
		}
		if (mantenimientoTerminado.getRevision_vaso_expansion()==1){
			operaciones=operaciones+"-Revision vaso exp."+"\n";
		}
		if (mantenimientoTerminado.getRegulacion_aparatos()==1){
			operaciones=operaciones+"-Regulacion aparato."+"\n";
		}
		if (mantenimientoTerminado.getComprobar_estanqueidad_cierre_quemadores_caldera()==1){
			operaciones=operaciones+"-Estanqueidad cierre entre"+"\n"+" quemadores y caldera."+"\n";
		}
		if (mantenimientoTerminado.getRevision_calderas_contadores()==1){
			operaciones=operaciones+"-Revision del equipo de gas."+"\n";
		}
		if (mantenimientoTerminado.getVerificacion_circuito_hidraulico_calefaccion()==1){
			operaciones=operaciones+"-Revision circuito hidraulico"+"\n"+" calefaccion."+"\n";
		}
		if (mantenimientoTerminado.getEstanqueidad_conexion_aparatos()==1){
			operaciones=operaciones+"-Estanqueidad conexion aparatos."+"\n";
		}
		if (mantenimientoTerminado.getEstanqueidad_conducto_evacuacion_irg()==1){
			operaciones=operaciones+"-Estanqueidad conducto evac."+"\n"+" e IRG."+"\n";
		}
		if (mantenimientoTerminado.getComprobacion_niveles_agua()==1){
			operaciones=operaciones+"-Revision niveles agua"+"\n";
		}
		if (mantenimientoTerminado.getTipo_conducto_evacuacion()==1){
			operaciones=operaciones+"-Tiro evacuacion."+"\n";
		}
		if (mantenimientoTerminado.getRevision_estado_aislamiento_termico()==1){
			operaciones=operaciones+"-Revision aislamiento termico."+"\n";
		}
		if (mantenimientoTerminado.getAnalisis_productos_combustion()==1){
			operaciones=operaciones+"-Analisis PDC"+"\n";
		}
		if (mantenimientoTerminado.getCaudal_acs_calculo_potencia()==1){
			operaciones=operaciones+"-Caudal ACS y calculo pot. util"+"\n";
		}
		if (mantenimientoTerminado.getRevision_sistema_control()==1){
			operaciones=operaciones+"-Revision del sist. control"+"\n";
		}
		return operaciones;
	}
	private String datosMaquinas() throws SQLException {
		String datos_maquinas = "";
		for (int i = 0; i < maquinas.size(); i++) {
			String datos_instalacion = "--------DATOS INSTALACION-------" + "\n";
			String cod = TipoCalderaDAO.buscarTipoCalderaPorId(activity,maquinas.get(i).getFk_tipo_maquina()).getNombre_tipo_caldera();
			String codigo = "Codigo: "+cod+"\n";
			String mar = MarcaCalderaDAO.buscarNombreMarcaCalderaPorId(activity,maquinas.get(i).getFk_marca_maquina());
			String marca = "Marca: "+mar+"\n";
			String mode = maquinas.get(i).getModelo_maquina();
			String modelo = "Modelo: "+mode+"\n";
			String añ = maquinas.get(i).getPuesta_marcha_maquina();
			String año = "Fabricado: "+añ+"\n";
			String pot = PotenciaDAO.buscarNombrePotenciaPorId(activity,maquinas.get(i).getFk_potencia_maquina());
			String potencia = "Potencia: "+pot+"\n";
			String datos_equipamientos = "";
			if (equipamiento!=null){
				datos_equipamientos+="Equipamientos:"+"\n";
				for (int j = 0; j < equipamiento.size(); j++) {
					int equ = equipamiento.get(j).getFk_tipo_equipamiento();
					String tip_equ = "";
					String fuegos = "N. Fuegos/Potencia: "+equipamiento.get(j).getPotencia_fuegos()+"\n";
					switch (equ){
						case 1:
							tip_equ = "Cocina"+"\n";
							datos_equipamientos+=tip_equ+fuegos;
							break;
						case 2:
							tip_equ = "Horno"+"\n";
							datos_equipamientos+=tip_equ+fuegos;
							break;
						case 3:
							tip_equ = "Horno + Grill"+"\n";
							datos_equipamientos+=tip_equ+fuegos;
							break;
					}
				}
			}
			String observaciones_tecnico = "-----------RESULTADO------------" + "\n";
			String tem_max_acs = maquinas.get(i).getTemperatura_max_acs();
			String temperatura_max_acs = "Temp. Max. ACS: "+tem_max_acs+" C \n";
			String caud_acs = maquinas.get(i).getCaudal_acs();
			String caudal_acs = "Caudal ACS: "+caud_acs+"\n";
			String pot_uti = maquinas.get(i).getPotencia_util();
			String potencia_util = "Potencia util: "+pot_uti+"\n";
			String tem_agu_ent = maquinas.get(i).getTemperatura_agua_generador_calor_entrada();
			String temp_agua_entrada = "Temp. agua entrada: "+tem_agu_ent+" C \n";
			String tem_agu_sal = maquinas.get(i).getTemperatura_agua_generador_calor_salida();
			String temp_agua_salida = "Temp. agua salida: "+tem_agu_sal+" C \n";
			String tem_gas_comb = maquinas.get(i).getTemperatura_gases_combustion();
			String temp_gases_combust = "Temp. gases combustion: "+tem_gas_comb+" C \n";
			String rend_apar = maquinas.get(i).getRendimiento_aparato();
			String rendimiento_aparato = "Rendimiento aparato: "+rend_apar+ " %"+"\n";
			String co_cor = maquinas.get(i).getCo_corregido();
			String co_corregido = "CO corregido: "+co_cor+ " ppm \n";
			String co_amb = maquinas.get(i).getC0_maquina();
			String co_ambiente = "CO ambiente: "+co_amb+ " ppm \n";
			String tir = maquinas.get(i).getTiro();
			String tiro = "Tiro: "+tir+ " mbar \n";
			String c2 = maquinas.get(i).getCo2();
			String co2 = "CO2: "+c2+ " % \n";
			String o02 = maquinas.get(i).getO2();
			String o2 = "O2: "+o02+ " % \n";
			String lamb = maquinas.get(i).getLambda();
			String lambda = "Lambda: "+lamb+ "\n";
			String num_serie_tex= "";
			String numero_serie_texto = "Num.Serie Equip.Testo: "+num_serie_tex+"\n";
			datos_maquinas+=datos_instalacion+codigo+marca+modelo+año+potencia+datos_equipamientos+observaciones_tecnico+
					temperatura_max_acs+caudal_acs+potencia_util+temp_agua_entrada+temp_agua_salida+
					temp_gases_combust+rendimiento_aparato+co_corregido+co_ambiente+tiro+co2+o2+
					lambda+numero_serie_texto;
		}

		return datos_maquinas;
	}
	private void generarSaltoFinal(POSPrinterService pps) throws JposException, InterruptedException {
		String textoImpresion = "\n\n\n\n";
		pps.printNormal(POSPrinterConst.PTR_S_RECEIPT, limpiarAcentos(textoImpresion));
		Thread.sleep(2000);
	}
	private Bitmap loadImageClienteFromStorage(){
		Bitmap b=null;
		try {
			File f=new File(path, "firmaCliente"+mantenimiento.getId_mantenimiento()+".png");
			b = BitmapFactory.decodeStream(new FileInputStream(f));

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return b;
	}
	private void imprimirFirmaCliente(POSPrinterService pps) throws IOException, JposException, InterruptedException {
		int img[][] = null;
		int ancho = 1;
		Bitmap bit = loadImageClienteFromStorage();
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
	private Bitmap loadImageTecnicoFromStorage(){
		Bitmap b=null;
		try {
			File f=new File(path, "firmaTecnico"+mantenimiento.getId_mantenimiento()+".png");
			b = BitmapFactory.decodeStream(new FileInputStream(f));

		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		return b;
	}
	private void imprimirFirmaTecnico(POSPrinterService pps) throws IOException, JposException, InterruptedException {
		int img[][] = null;
		int ancho = 1;
		Bitmap bit = loadImageTecnicoFromStorage();
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
		Thread.sleep(2000);
	}
	private String limpiarAcentos(String texto_entrada) {
		String original = "áàäéèëíìïóòöúùuñÁÀÄÉÈËÍÌÏÓÒÖÚÙÜÑçÇº€";
		String ascii = "aaaeeeiiiooouuunAAAEEEIIIOOOUUUNcCCE";
		String output = texto_entrada;
		for (int i = 0; i < original.length(); i++) {
			output = output.replace(original.charAt(i), ascii.charAt(i));
		}
		return output;
	}


	String codigo(String codigo){
		String literal="";
		switch (codigo) {
			case "1":literal="Estanca (Tipo C)";
				break;
			case "2":literal="Atmosférica (Tipo B)";
				break;
			case "6":literal="Condensación";
				break;
			case "7":literal="Caldera tiro mixto";
				break;
			case "8":literal="Calentador Gas";
				break;
			case "9":literal="Otras";
				break;
			default: literal="Otras";
		}
		return literal;
	}


}
