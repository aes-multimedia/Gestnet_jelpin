package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;


import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.AdaptadorListaAnalisis;
import com.multimedia.aes.gestnet_nucleo.dao.AnalisisDAO;
import com.multimedia.aes.gestnet_nucleo.dialogo.Dialogo;
import com.multimedia.aes.gestnet_nucleo.entidades.Analisis;

import java.sql.SQLException;
import java.util.ArrayList;

public class AnadirDatosAnalisis extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private TextView txtTempGasesComb,txtCoCorregido,txtO2,txtC0,txtLambda,txtCo2,txtTempAmbienteLocal,txtTiro,txtRendimientoAparato,txtCoAmbiente,txtCo2Ambiente;
    private EditText etNombreMedicion;
    private CheckBox cbCampana;
    private int id;
    private int fk_maquina;
    private int fkAnalisis;
    private int serialNumber;
    private Button btnAñadirAnalisis;
    private Button btnBorrarMedicion;
    private Button btnDatosTesto;
    private Button btnFinalizarAnalisis;
    private LinearLayout llDatosTesto;
    private ListView lvDatosAnalisis;
    private ArrayList<Analisis> arrayAnalisis = new ArrayList<>();
    private static int alto,height;
    private AdaptadorListaAnalisis adaptadorListaAnalisis;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.anadir_datos_analisis);
        inicializarVariables();
        Display display = getWindowManager().getDefaultDisplay();
        height = display.getHeight();
        height=height/16;
        id=getIntent().getIntExtra("id",-1);
        fk_maquina=getIntent().getIntExtra("fkMaquina",-1);
        fkAnalisis = getIntent().getIntExtra("fkAnalisis",-1);
        ponerAnalisis();
        llDatosTesto.setVisibility(View.GONE);
        btnFinalizarAnalisis.setVisibility(View.GONE);

    }
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        try {
            llDatosTesto.setVisibility(View.VISIBLE);
            btnFinalizarAnalisis.setVisibility(View.VISIBLE);
            fkAnalisis = Integer.parseInt(String.valueOf(view.getTag()));
            arrayAnalisis.remove(position);
            alto-=height;
            lvDatosAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
            adaptadorListaAnalisis = new AdaptadorListaAnalisis(this, R.layout.camp_adapter_list_view_analisis_solicitud, arrayAnalisis);
            lvDatosAnalisis.setAdapter(adaptadorListaAnalisis);
            rellenarDatos();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onClick(View view) {
        if(view.getId()==btnAñadirAnalisis.getId()){
            String  sCoCorregido,sO2,sC0,sLambda,sCo2,sTempAmbienteLocal,sTiro,sRendimientoAparato,sCoAmbiente,sCo2Ambiente,sNombreMedicion;
            String sTempGasesComb = txtTempGasesComb.getText().toString();
            sCoCorregido = txtCoCorregido.getText().toString();
            sO2 = txtO2.getText().toString();
            sC0 = txtC0.getText().toString();
            sLambda = txtLambda.getText().toString();
            sCo2 = txtCo2.getText().toString();
            sTempAmbienteLocal=txtTempAmbienteLocal.getText().toString();
            sTiro=txtTiro.getText().toString();
            sRendimientoAparato=txtRendimientoAparato.getText().toString();
            sCoAmbiente = txtCoAmbiente.getText().toString();
            sCo2Ambiente = txtCo2Ambiente.getText().toString();

            sNombreMedicion = etNombreMedicion.getText().toString();

            int campana;
            if (cbCampana.isChecked()) campana = 1;
            else campana = 0;

            if (sTempGasesComb.length() == 0) Dialogo.dialogoError("Introduce temperatura de gases", this);
            else if (sCoCorregido.length() == 0) Dialogo.dialogoError("Introduce Co corregido", this);
            else if (sO2.length() == 0) Dialogo.dialogoError("Introduce O2", this);
            else if (sC0.length() == 0) Dialogo.dialogoError("Introduce CO", this);
            else if (sCo2.length() == 0) Dialogo.dialogoError("Introduce CO2", this);
            else if (sTempAmbienteLocal.length() == 0)
                Dialogo.dialogoError("Introduce tempreatura ambiente local", this);
            else if (sTiro.length() == 0) Dialogo.dialogoError("Introduce tiro", this);
            else if (sRendimientoAparato.length() == 0)
                Dialogo.dialogoError("Introduce rendimiento del aparato", this);
            else if (sCoAmbiente.length() == 0) Dialogo.dialogoError("Introduce Co ambiente", this);
            else if (sNombreMedicion.length() == 0)
                Dialogo.dialogoError("Introduce un nombre a la medición", this);
            else {
                if (fkAnalisis!=-1){
                    try {
                        AnalisisDAO.actualizarAnalisis(this,fkAnalisis, fk_maquina, id, sC0, sTempGasesComb,
                                sTempAmbienteLocal, sRendimientoAparato, sCoCorregido,
                                sCoAmbiente, sCo2Ambiente, sTiro, sCo2, sO2, sLambda,
                                campana, sNombreMedicion, 0, 0, 0);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else{
                    AnalisisDAO.newAnalisis(this, fk_maquina, id, sC0, sTempGasesComb,
                            sTempAmbienteLocal, sRendimientoAparato, sCoCorregido,
                            sCoAmbiente, sCo2Ambiente, sTiro, sCo2, sO2, sLambda,
                            campana, sNombreMedicion, 0, 0, 0);
                }
                ponerAnalisis();
                txtTempGasesComb.setText("");
                txtCoCorregido.setText("");
                txtO2.setText("");
                txtC0.setText("");
                txtLambda.setText("");
                txtCo2.setText("");
                txtTempAmbienteLocal.setText("");
                txtTiro.setText("");
                txtRendimientoAparato.setText("");
                txtCoAmbiente.setText("");
                txtCo2Ambiente.setText("");
                etNombreMedicion.setText("");
                llDatosTesto.setVisibility(View.GONE);
                fkAnalisis=-1;
            }

        }else if(view.getId()==btnBorrarMedicion.getId()){
            if (fkAnalisis!=-1){
                try {
                    AnalisisDAO.borrarAnalisisId(this,fkAnalisis);
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                ponerAnalisis();
                txtTempGasesComb.setText("");
                txtCoCorregido.setText("");
                txtO2.setText("");
                txtC0.setText("");
                txtLambda.setText("");
                txtCo2.setText("");
                txtTempAmbienteLocal.setText("");
                txtTiro.setText("");
                txtRendimientoAparato.setText("");
                txtCoAmbiente.setText("");
                txtCo2Ambiente.setText("");
                etNombreMedicion.setText("");
                llDatosTesto.setVisibility(View.GONE);
                fkAnalisis=-1;
            }else{
                txtTempGasesComb.setText("");
                txtCoCorregido.setText("");
                txtO2.setText("");
                txtC0.setText("");
                txtLambda.setText("");
                txtCo2.setText("");
                txtTempAmbienteLocal.setText("");
                txtTiro.setText("");
                txtRendimientoAparato.setText("");
                txtCoAmbiente.setText("");
                txtCo2Ambiente.setText("");
                etNombreMedicion.setText("");
            }
        }else if (view.getId() == R.id.btnDatosTesto) {
            /////////
            Intent i = new Intent(this, Testo.class);
            startActivityForResult(i, 666);
            ////////
        }else if (view.getId() == R.id.btnFinalizarAnalisis){
            Intent returnIntent = new Intent();
            setResult(Activity.RESULT_OK, returnIntent);
            returnIntent.putExtra("serial",serialNumber);
            finish();
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==666 && resultCode==RESULT_OK) {
            String[] result=data.getStringArrayExtra("result");
            ponerDatosTesto(result);
        }
    }

    public boolean isNumber(String num){
        try{
            Double.parseDouble(num);
        }catch (Exception e){
            return  false;
        }
        return true;
    }
    public void ponerDatosTesto(String [] datos){
        llDatosTesto.setVisibility(View.VISIBLE);
        btnFinalizarAnalisis.setVisibility(View.VISIBLE);
        String id = "";
        String valor  ="";
        for (int i = 0; i < datos.length-1; i++) {
            String [] sep = datos[i].split("&");
            id = sep[0];
            valor = sep[1];
            valor = valor.replace(",",".");
            switch (id){
                case "ident_0x00000101":
                    if (isNumber(valor)) {
                        txtTempGasesComb.setText(valor);
                    }
                    break;
                case "ident_0x00000102":
                    if (isNumber(valor)) {
                        txtTempAmbienteLocal.setText(valor);
                    }
                    break;
                case "ident_0x00020B03":
                    if (isNumber(valor)) {
                        txtRendimientoAparato.setText(valor);
                    }
                    break;
                case "ident_0x00000904":
                    if (isNumber(valor)) {
                        txtCoCorregido.setText(valor);
                    }
                    break;
                case "ident_0x00000903":
                    if (isNumber(valor)) {
                        txtCoAmbiente.setText(valor);
                    }
                    break;
                case "ident_0x00000301":
                    if (isNumber(valor)) {
                        txtTiro.setText(valor);
                    }
                    break;
                case "ident_0x00000909":
                    if (isNumber(valor)) {
                        txtCo2.setText(valor);
                    }
                    break;
                case "ident_0x0000090F":
                    if (isNumber(valor)) {
                        txtCo2Ambiente.setText(valor);
                    }
                    break;
                case "ident_0x00000901":
                    if (isNumber(valor)) {
                        txtO2.setText(valor);
                    }
                    break;
                case "ident_0x00021282":
                    if (isNumber(valor)) {
                        txtLambda.setText(valor);
                    }
                    break;
                case "ident_0x00000902":
                    if (isNumber(valor)) {
                        txtC0.setText(valor);
                    }
                    break;
                case "ident_0x0000090D":
                    if (isNumber(valor)) {
                        txtCoCorregido.setText(valor);
                    }
                    break;
                case "ident_0x0000090C":
                    if (isNumber(valor)) {
                        txtLambda.setText(valor);
                    }
                    break;
                case "2823":
                    if (isNumber(valor)) {
                        txtRendimientoAparato.setText(valor);
                    }
                    break;
                case "ident_0x00000914":
                    if (isNumber(valor)) {
                        txtCoAmbiente.setText(valor);
                    }
                    break;
                case "2329":
                    if (isNumber(valor)) {
                        txtCo2Ambiente.setText(valor);
                    }
                    break;
            }
        }
         serialNumber = Integer.parseInt(datos[datos.length - 1]);
    }
    private void inicializarVariables() {
        //EDIT TEXT
        txtTempGasesComb = (TextView) findViewById(R.id.txtTempGasesComb);
        txtCoCorregido = (TextView) findViewById(R.id.txtCoCorregido);
        txtO2 = (TextView) findViewById(R.id.txtO2);
        txtC0 = (TextView) findViewById(R.id.txtC0);
        txtLambda = (TextView) findViewById(R.id.txtLambda);
        txtCo2 = (TextView) findViewById(R.id.txtCo2);
        txtTempAmbienteLocal = (TextView) findViewById(R.id.txtTempAmbienteLocal);
        txtTiro = (TextView) findViewById(R.id.txtTiro);
        txtRendimientoAparato = (TextView) findViewById(R.id.txtRendimientoAparato);
        txtCoAmbiente = (TextView) findViewById(R.id.txtCoAmbiente);
        txtCo2Ambiente = (TextView) findViewById(R.id.txtCo2Ambiente);
        etNombreMedicion = (EditText) findViewById(R.id.etNombreMedicion);


        //CHECK BOX
        cbCampana = (CheckBox) findViewById(R.id.cbCampana);

        //BUTTON
        btnAñadirAnalisis = (Button) findViewById(R.id.btnAñadirAnalisis);
        btnBorrarMedicion = (Button) findViewById(R.id.btnBorrarMedicion);
        btnDatosTesto = (Button) findViewById(R.id.btnDatosTesto);
        btnFinalizarAnalisis = (Button) findViewById(R.id.btnFinalizarAnalisis);

        //LINEARLAYOUT
        llDatosTesto = (LinearLayout) findViewById(R.id.llDatosTesto);

        //LISTVIEW
        lvDatosAnalisis = (ListView) findViewById(R.id.lvDatosAnalisis);
        //ONCLICK
        btnDatosTesto.setOnClickListener(this);
        btnAñadirAnalisis.setOnClickListener(this);
        btnBorrarMedicion.setOnClickListener(this);
        btnFinalizarAnalisis.setOnClickListener(this);
        lvDatosAnalisis.setOnItemClickListener(this);



    }
    private void ponerAnalisis(){
        arrayAnalisis.clear();
        try {
            if (AnalisisDAO.buscarAnalisisPorFkMaquina(this,fk_maquina)!=null){
                arrayAnalisis.addAll(AnalisisDAO.buscarAnalisisPorFkMaquina(this,fk_maquina));
                int i = arrayAnalisis.size();
                alto=height*i;
                lvDatosAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(this, R.layout.camp_adapter_list_view_analisis_solicitud, arrayAnalisis);
                lvDatosAnalisis.setAdapter(adaptadorListaAnalisis);
            }else{
                alto=0;
                lvDatosAnalisis.setLayoutParams(new LinearLayout.LayoutParams(AbsListView.LayoutParams.MATCH_PARENT, alto));
                adaptadorListaAnalisis = new AdaptadorListaAnalisis(this, R.layout.camp_adapter_list_view_analisis_solicitud, arrayAnalisis);
                lvDatosAnalisis.setAdapter(adaptadorListaAnalisis);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    private void rellenarDatos() throws SQLException {
        Analisis analisis = AnalisisDAO.buscarAnalisisPorId(this, fkAnalisis);
        txtTempGasesComb.setText(analisis.getTemperatura_gases_combustion());
        txtCoCorregido .setText(analisis.getCo_corregido());
        txtO2.setText(analisis.getO2());
        txtC0.setText(analisis.getC0_maquina());
        txtLambda.setText(analisis.getLambda());
        txtCo2.setText(analisis.getCo2());
        txtTempAmbienteLocal.setText(analisis.getTemperatura_ambiente_local());
        txtTiro.setText(analisis.getTiro());
        txtRendimientoAparato.setText(analisis.getRendimiento_aparato());
        txtCoAmbiente.setText(analisis.getCo_ambiente());
        txtCo2Ambiente.setText(analisis.getCo2_ambiente());
        etNombreMedicion.setText(analisis.getNombre_medicion());
    }

}
