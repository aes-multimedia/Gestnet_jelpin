package com.multimedia.aes.gestnet_nucleo.nucleo;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.clases.Presupuesto;
import com.multimedia.aes.gestnet_nucleo.clases.TipoPresupuesto;
import com.multimedia.aes.gestnet_nucleo.clases.TipoTrabajo;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ImagenDAO;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.dao.UsuarioDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.multimedia.aes.gestnet_nucleo.entidades.Imagen;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;
import com.multimedia.aes.gestnet_nucleo.entidades.Usuario;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloDatosPresupuesto;
import com.multimedia.aes.gestnet_nucleo.hilos.HiloGuardarPresupuesto;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class Presupuestos extends AppCompatActivity implements View.OnClickListener, IPickResult {


    private Button btnGuardarPresupuesto;
    private ImageButton btnAdjuntarImagen;
    private Spinner spTipoInstalacion, spTipoTrabajo;
    private TextView tvNimagenesAdjuntas;
    private int idParte;
    private Cliente cliente;
    private Usuario usuario;
    private List<TipoTrabajo> tipoTrabajoList = null;
    private List<TipoPresupuesto> tipoPresupuestoList = null;
    private ArrayList<String> listaImagenes;
    private EditText observaciones;
    private ProgressDialog pd;
    private Parte parte=null;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_presupuestos);
        TextView tvDireccionCliente = findViewById(R.id.tvDireccionCliente);
        TextView tvNombreCliente = findViewById(R.id.tvNombreCliente);
        spTipoInstalacion = findViewById(R.id.spTipoInstalacion);
        spTipoTrabajo = findViewById(R.id.spTipoTrabajo);
        btnAdjuntarImagen = findViewById(R.id.btnAñadirImagen);
        observaciones = findViewById(R.id.etObservaciones);
        tvNimagenesAdjuntas = findViewById(R.id.tvNimagenesAdjuntas);
        btnGuardarPresupuesto=findViewById(R.id.btnGuardarPresupuesto);
        btnAdjuntarImagen.setOnClickListener(this);
        btnGuardarPresupuesto.setOnClickListener(this);
        idParte = getIntent().getIntExtra("id_parte", 0);

        listaImagenes=new ArrayList<>();

        try {


            cliente = ClienteDAO.buscarCliente(this);
            usuario = UsuarioDAO.buscarUsuario(this);
            parte = ParteDAO.buscarPartePorId(this, idParte);
            listaImagenes.addAll(getImagenesGuardadas());

            new HiloDatosPresupuesto(this).execute();


        } catch (SQLException e) {
            e.printStackTrace();
        }

        tvNimagenesAdjuntas.setText(String.valueOf(listaImagenes.size()));
        tvDireccionCliente.setText(getDireccion(parte));
        tvNombreCliente.setText(parte.getNombre_cliente());


    }

    private CharSequence getDireccion(Parte parte) {
        String dir = "";

        if (!parte.getTipo_via().trim().equals("") && !parte.getTipo_via().trim().equals("null")) {
            dir += parte.getTipo_via() + " ";
        }
        if (!parte.getVia().trim().equals("") && !parte.getVia().trim().equals("null")) {
            dir += parte.getVia() + " ";
        }
        if (!parte.getNumero_direccion().trim().equals("") && !parte.getNumero_direccion().trim().equals("null")) {
            dir += "Nº " + parte.getNumero_direccion() + " ";
        }
        if (!parte.getEscalera_direccion().trim().equals("") && !parte.getEscalera_direccion().trim().equals("null")) {
            dir += "Esc. " + parte.getEscalera_direccion() + " ";
        }
        if (!parte.getPiso_direccion().trim().equals("") && !parte.getPiso_direccion().trim().equals("null")) {
            dir += "Piso " + parte.getPiso_direccion() + " ";
        }
        if (!parte.getPuerta_direccion().trim().equals("") && !parte.getPuerta_direccion().trim().equals("null")) {
            dir += parte.getPuerta_direccion() + " ";
        }
        if (!parte.getMunicipio_direccion().trim().equals("") && !parte.getMunicipio_direccion().trim().equals("null")) {
            dir += "(" + parte.getMunicipio_direccion() + "-";
        }
        if (!parte.getProvincia_direccion().trim().equals("") && !parte.getProvincia_direccion().trim().equals("null")) {
            dir += parte.getProvincia_direccion() + ")";
        }

        // dir="Calle falsa 123, 28028, Springfield, Nevada";

        return dir;
    }


    public void darValoresSpinner(String mensaje) {


        JSONObject jsonObject;
        JSONArray jsonArrayTiposPresupuesto = null;
        JSONArray jsonArrayTiposTrabajo = null;

        try {

            jsonObject = new JSONObject(mensaje);
            jsonArrayTiposPresupuesto = jsonObject.getJSONArray("tipos_presupuesto");
            jsonArrayTiposTrabajo = jsonObject.getJSONArray("tipos_trabajo");

        } catch (JSONException e) {
            e.printStackTrace();
        }

        if(jsonArrayTiposPresupuesto==null || jsonArrayTiposTrabajo==null){
            sacarMensaje("Sin conexion, por favor intentelo de nuevo mas tarde.");
            finish();
        }else{
            darValoresSpinnerTiposPesupuesto(jsonArrayTiposPresupuesto);
            darValoresSpinnerTiposTrabajo(jsonArrayTiposTrabajo);
        }
        //SPINNER FORMAS PAGO
    }


    private void darValoresSpinnerTiposTrabajo(JSONArray jsonArrayTiposTrabajo) {


        List<TipoTrabajo> tipoTrabajoList = null;
        try {
            tipoTrabajoList = getListTrabajo(jsonArrayTiposTrabajo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] arrayTipoTrabajos;
        arrayTipoTrabajos = tipoTrabajoList == null ? new String[1] :  new String[tipoTrabajoList.size() + 1];
        arrayTipoTrabajos[0] = "--Seleciones un valor--";
        for (int i = 1; i < tipoTrabajoList.size() + 1; i++) {
            arrayTipoTrabajos[i] = tipoTrabajoList.get(i - 1).getDescripcion_tipo_trabajo();
        }
        spTipoTrabajo.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTipoTrabajos));


    }

    private List<TipoTrabajo> getListTrabajo(JSONArray jsonArrayTiposTrabajo) throws JSONException {
        List<TipoTrabajo> l = new ArrayList<>();
        for (int i = 0; i < jsonArrayTiposTrabajo.length(); i++) {
            JSONObject jsonObject = new JSONObject(String.valueOf(jsonArrayTiposTrabajo.getJSONObject(i)));
            int id;
            if (jsonObject.getString("id_tipo_trabajo").equals("null") || jsonObject.getString("id_tipo_trabajo").equals("")) {
                id = -1;
            } else {
                id = jsonObject.getInt("id_tipo_trabajo");
            }

            String descripcion_tipo_trabajo;
            if (jsonObject.getString("descripcion_tipo_trabajo").equals("null")) {
                descripcion_tipo_trabajo = "";
            } else {
                descripcion_tipo_trabajo = jsonObject.getString("descripcion_tipo_trabajo");
            }
            TipoTrabajo t = new TipoTrabajo(id, descripcion_tipo_trabajo);

            l.add(t);
        }
        tipoTrabajoList =  l;
        return l;
    }

    private void darValoresSpinnerTiposPesupuesto(JSONArray jsonArrayTiposPresupuesto) {


        List<TipoPresupuesto> lisTipoPresupuestos = null;
        try {
            lisTipoPresupuestos = getListPresupuestos(jsonArrayTiposPresupuesto);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        String[] arrayTiposPresupuesto;
        arrayTiposPresupuesto = lisTipoPresupuestos == null ? new String[1] :  new String[lisTipoPresupuestos.size() + 1];
        arrayTiposPresupuesto[0] = "--Seleciones un valor--";
        for (int i = 1; i < lisTipoPresupuestos.size() + 1; i++) {
            arrayTiposPresupuesto[i] = lisTipoPresupuestos.get(i - 1).getNombre_instalacion();
        }
        spTipoInstalacion.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, arrayTiposPresupuesto));


    }

    private List<TipoPresupuesto> getListPresupuestos(JSONArray jsonArrayTiposPresupuesto) throws JSONException {

        List<TipoPresupuesto> l = new ArrayList<>();

            for (int i = 0; i < jsonArrayTiposPresupuesto.length(); i++) {
                JSONObject jsonObject = new JSONObject(String.valueOf(jsonArrayTiposPresupuesto.getJSONObject(i)));
                int id;
                if (jsonObject.getString("id_instalacion_presupuesto").equals("null") || jsonObject.getString("id_instalacion_presupuesto").equals("")) {
                    id = -1;
                } else {
                    id = jsonObject.getInt("id_instalacion_presupuesto");
                }

                String nombre_instalacion;
                if (jsonObject.getString("nombre_instalacion").equals("null")) {
                    nombre_instalacion = "";
                } else {
                    nombre_instalacion = jsonObject.getString("nombre_instalacion");
                }
                TipoPresupuesto t = new TipoPresupuesto(id, nombre_instalacion);

                l.add(t);
            }


        tipoPresupuestoList = l;

        return l;
    }


    public void sacarMensaje(String mensaje) {
        AlertDialog.Builder builder1 = new AlertDialog.Builder(this);
        builder1.setMessage(mensaje);
        builder1.setCancelable(true);
        builder1.setPositiveButton(
                "Aceptar",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                        finish();
                    }
                });
        AlertDialog alert11 = builder1.create();
        alert11.setCanceledOnTouchOutside(false);
        alert11.show();


    }

    @Override
    public void onClick(View v) {



        if (v.getId() == btnAdjuntarImagen.getId()) {

            PickImageDialog.build(new PickSetup()
                    .setTitle("Selecciona una opción")
                    .setCameraButtonText("Camara")
                    .setGalleryButtonText("Galeria")
                    .setCancelText("CANCELAR")
                    .setCancelTextColor(Color.RED)).show(this);

        } else if (v.getId() == btnGuardarPresupuesto.getId()) {

            Presupuesto p = new Presupuesto()
                    .setFk_usuario(parte.getFk_usuario())
                    .setFk_user_creador(1)
                    .setFk_tecnico_origen(usuario.getFk_entidad())
                    .setFk_tipo_presupuesto(getFkTipoInstalacion(tipoPresupuestoList,spTipoInstalacion.getSelectedItem().toString(),0))
                    .setFk_tipo_trabajo(getFkTipoITrabajo(tipoTrabajoList,spTipoTrabajo.getSelectedItem().toString(),0))
                    .setObservaciones_presupuesto(observaciones.getText().toString())
                    .setListaImagenes(listaImagenes)
                    .setFk_empresa(parte.getFk_compania())
                    .setFk_parte(parte.getId_parte());

            new HiloGuardarPresupuesto(this,p,cliente).execute();


        }

    }



    @Override
    public void onPickResult(PickResult r) {
        if (r.getError() == null) {
            ImagenDAO.newImagen(this,"presupuesto_"+listaImagenes.size(),r.getPath(),idParte);
            listaImagenes.add(r.getPath());
            tvNimagenesAdjuntas.setText(String.valueOf(listaImagenes.size()));

        } else {
            Log.e("error Pickresult",r.getError().toString());
            Toast.makeText(this, r.getError().getMessage(), Toast.LENGTH_LONG).show();
        }
    }


    private int getFkTipoInstalacion(List<TipoPresupuesto> l, String s, int index){

        if(index<l.size()) {
            if (l.get(index).getNombre_instalacion().equals(s))
                return l.get(index).getId_instalacion_presupuesto();
            else return getFkTipoInstalacion(l, s, index + 1);
        }else return -1;

    }


    private int getFkTipoITrabajo(List<TipoTrabajo> l, String s, int index){

        if(index<l.size()) {
            if (l.get(index).getDescripcion_tipo_trabajo().equals(s))
                return l.get(index).getId_tipo_trabajo();
            else return getFkTipoITrabajo(l, s, index + 1);
        }else return -1;

    }


    public Collection<? extends String> getImagenesGuardadas() {
        List<String>imagenesGuardadas= new ArrayList<>();
        List<Imagen>imagenes;



        try {
                if(ImagenDAO.buscarImagenPresupuestoPorFk_parte(this,idParte)!=null) {
                    imagenes=ImagenDAO.buscarImagenPresupuestoPorFk_parte(this,idParte);
            if(imagenes != null)
            for (Imagen imagen : imagenes) {
                imagenesGuardadas.add(imagen.getRuta_imagen());
            }
        }

            } catch (SQLException e) {
                e.printStackTrace();
            }


        return imagenesGuardadas;
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setMessage("Estás seguro de que deseas salir?\n\n Si lo haces perderas la información almacenada")
                .setCancelable(true)
                .setPositiveButton("Salir", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface hi, int dd)
                            {

                                finish();
                            }
                        }
                )
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()
                        {
                            public void onClick(DialogInterface hi, int dd)
                            {

                            }
                        }
                ).show();
    }

    public void borrarImagenesPorExito(String s) {
        try {
            ImagenDAO.borrarImagenesPresupuestoPorFk_parte(this,idParte);
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            sacarMensaje(s);
        }
    }



}
