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

    private EditText etTempGasesComb;
    private EditText etCoCorregido;
    private EditText etO2;
    private EditText etC0;
    private EditText etLambda;
    private EditText etCo2;
    private EditText etTempAmbienteLocal;
    private EditText etTiro;
    private EditText etRendimientoAparato;
    private EditText etCoAmbiente;
    private EditText etCo2Ambiente;
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
            String sTempGasesComb = etTempGasesComb.getText().toString();
            sCoCorregido = etCoCorregido.getText().toString();
            sO2 = etO2.getText().toString();
            sC0 = etC0.getText().toString();
            sLambda = etLambda.getText().toString();
            sCo2 = etCo2.getText().toString();
            sTempAmbienteLocal= etTempAmbienteLocal.getText().toString();
            sTiro= etTiro.getText().toString();
            sRendimientoAparato= etRendimientoAparato.getText().toString();
            sCoAmbiente = etCoAmbiente.getText().toString();
            sCo2Ambiente = etCo2Ambiente.getText().toString();

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
                etTempGasesComb.setText("");
                etCoCorregido.setText("");
                etO2.setText("");
                etC0.setText("");
                etLambda.setText("");
                etCo2.setText("");
                etTempAmbienteLocal.setText("");
                etTiro.setText("");
                etRendimientoAparato.setText("");
                etCoAmbiente.setText("");
                etCo2Ambiente.setText("");
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
                etTempGasesComb.setText("");
                etCoCorregido.setText("");
                etO2.setText("");
                etC0.setText("");
                etLambda.setText("");
                etCo2.setText("");
                etTempAmbienteLocal.setText("");
                etTiro.setText("");
                etRendimientoAparato.setText("");
                etCoAmbiente.setText("");
                etCo2Ambiente.setText("");
                etNombreMedicion.setText("");
                llDatosTesto.setVisibility(View.GONE);
                fkAnalisis=-1;
            }else{
                etTempGasesComb.setText("");
                etCoCorregido.setText("");
                etO2.setText("");
                etC0.setText("");
                etLambda.setText("");
                etCo2.setText("");
                etTempAmbienteLocal.setText("");
                etTiro.setText("");
                etRendimientoAparato.setText("");
                etCoAmbiente.setText("");
                etCo2Ambiente.setText("");
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
                        etTempGasesComb.setText(valor);
                    }
                    break;
                case "ident_0x00000102":
                    if (isNumber(valor)) {
                        etTempAmbienteLocal.setText(valor);
                    }
                    break;
                case "ident_0x00020B03":
                    if (isNumber(valor)) {
                        etRendimientoAparato.setText(valor);
                    }
                    break;
                case "ident_0x00000904":
                    if (isNumber(valor)) {
                        etCoCorregido.setText(valor);
                    }
                    break;
                case "ident_0x00000903":
                    if (isNumber(valor)) {
                        etCoAmbiente.setText(valor);
                    }
                    break;
                case "ident_0x00000301":
                    if (isNumber(valor)) {
                        etTiro.setText(valor);
                    }
                    break;
                case "ident_0x00000909":
                    if (isNumber(valor)) {
                        etCo2.setText(valor);
                    }
                    break;
                case "ident_0x0000090F":
                    if (isNumber(valor)) {
                        etCo2Ambiente.setText(valor);
                    }
                    break;
                case "ident_0x00000901":
                    if (isNumber(valor)) {
                        etO2.setText(valor);
                    }
                    break;
                case "ident_0x00021282":
                    if (isNumber(valor)) {
                        etLambda.setText(valor);
                    }
                    break;
                case "ident_0x00000902":
                    if (isNumber(valor)) {
                        etC0.setText(valor);
                    }
                    break;
                case "ident_0x0000090D":
                    if (isNumber(valor)) {
                        etCoCorregido.setText(valor);
                    }
                    break;
                case "ident_0x0000090C":
                    if (isNumber(valor)) {
                        etLambda.setText(valor);
                    }
                    break;
                case "2823":
                    if (isNumber(valor)) {
                        etRendimientoAparato.setText(valor);
                    }
                    break;
                case "ident_0x00000914":
                    if (isNumber(valor)) {
                        etCoAmbiente.setText(valor);
                    }
                    break;
                case "2329":
                    if (isNumber(valor)) {
                        etCo2Ambiente.setText(valor);
                    }
                    break;
            }
        }
         serialNumber = Integer.parseInt(datos[datos.length - 1]);
    }
    private void inicializarVariables() {
        //EDIT TEXT
        etTempGasesComb = (EditText) findViewById(R.id.txtTempGasesComb);
        etCoCorregido = (EditText) findViewById(R.id.txtCoCorregido);
        etO2 = (EditText) findViewById(R.id.txtO2);
        etC0 = (EditText) findViewById(R.id.txtC0);
        etLambda = (EditText) findViewById(R.id.txtLambda);
        etCo2 = (EditText) findViewById(R.id.txtCo2);
        etTempAmbienteLocal = (EditText) findViewById(R.id.txtTempAmbienteLocal);
        etTiro = (EditText) findViewById(R.id.txtTiro);
        etRendimientoAparato = (EditText) findViewById(R.id.txtRendimientoAparato);
        etCoAmbiente = (EditText) findViewById(R.id.txtCoAmbiente);
        etCo2Ambiente = (EditText) findViewById(R.id.txtCo2Ambiente);
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
        etTempGasesComb.setText(analisis.getTemperatura_gases_combustion());
        etCoCorregido.setText(analisis.getCo_corregido());
        etO2.setText(analisis.getO2());
        etC0.setText(analisis.getC0_maquina());
        etLambda.setText(analisis.getLambda());
        etCo2.setText(analisis.getCo2());
        etTempAmbienteLocal.setText(analisis.getTemperatura_ambiente_local());
        etTiro.setText(analisis.getTiro());
        etRendimientoAparato.setText(analisis.getRendimiento_aparato());
        etCoAmbiente.setText(analisis.getCo_ambiente());
        etCo2Ambiente.setText(analisis.getCo2_ambiente());
        etNombreMedicion.setText(analisis.getNombre_medicion());
    }

}
