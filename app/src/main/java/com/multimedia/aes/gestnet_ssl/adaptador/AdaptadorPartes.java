package com.multimedia.aes.gestnet_ssl.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.dao.ClienteDAO;
import com.multimedia.aes.gestnet_ssl.dao.MaquinaDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Cliente;
import com.multimedia.aes.gestnet_ssl.entidades.Maquina;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class AdaptadorPartes extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<Parte> arrayList;

    public AdaptadorPartes(Context context, int view, ArrayList<Parte> arrayList) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Cliente c = null;
        try {
            c = ClienteDAO.buscarCliente(context);

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int idcli = c.getId_cliente();
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        TextView direccion = item.findViewById(R.id.txtDireccion);
        TextView cp =  item.findViewById(R.id.txtCP);
        TextView hora =  item.findViewById(R.id.txtHora);
        TextView cliente =  item.findViewById(R.id.txtCliente);
        TextView compania =  item.findViewById(R.id.txtCompania);
        TextView txtTipoIntervencion =  item.findViewById(R.id.txtTipoIntervencion);
        LinearLayout global = item.findViewById(R.id.global);
        String dir = "";
        if (!arrayList.get(position).getTipo_via().trim().equals("")&&!arrayList.get(position).getTipo_via().trim().equals("null")){
            dir+=arrayList.get(position).getTipo_via()+" ";
        }
        if (!arrayList.get(position).getVia().trim().equals("")&&!arrayList.get(position).getVia().trim().equals("null")){
            dir+=arrayList.get(position).getVia()+" ";
        }
        if (!arrayList.get(position).getNumero_direccion().trim().equals("")&&!arrayList.get(position).getNumero_direccion().trim().equals("null")){
            dir+="Nº "+arrayList.get(position).getNumero_direccion()+" ";
        }
        if (!arrayList.get(position).getEscalera_direccion().trim().equals("")&&!arrayList.get(position).getEscalera_direccion().trim().equals("null")){
            dir+="Esc. "+arrayList.get(position).getEscalera_direccion()+" ";
        }
        if (!arrayList.get(position).getPiso_direccion().trim().equals("")&&!arrayList.get(position).getPiso_direccion().trim().equals("null")){
            dir+="Piso "+arrayList.get(position).getPiso_direccion()+" ";
        }
        if (!arrayList.get(position).getPuerta_direccion().trim().equals("")&&!arrayList.get(position).getPuerta_direccion().trim().equals("null")){
            dir+=arrayList.get(position).getPuerta_direccion()+" ";
        }
        if (!arrayList.get(position).getMunicipio_direccion().trim().equals("")&&!arrayList.get(position).getMunicipio_direccion().trim().equals("null")){
            dir+="("+arrayList.get(position).getMunicipio_direccion()+"-";
        }
        if (!arrayList.get(position).getProvincia_direccion().trim().equals("")&&!arrayList.get(position).getProvincia_direccion().trim().equals("null")){
            dir+=arrayList.get(position).getProvincia_direccion()+")";
        }


        switch (arrayList.get(position).getEstado_android()){
            case 0:
                global.setBackgroundResource(R.drawable.fondo_rojo);
                break;
            case 1:
                global.setBackgroundResource(R.drawable.fondo_ambar);
                break;
            case 2:
                global.setBackgroundResource(R.drawable.fondo_azul);
                break;
            case 3:
                global.setBackgroundResource(R.drawable.fondo_verde);
                break;
            case 4:
                global.setBackgroundResource(R.drawable.fondo_tenue);
                break;
            case 436:
                global.setBackgroundResource(R.drawable.verde_oscuro);
                break;
        }
        direccion.setText(dir);
        cp.setText(String.valueOf(arrayList.get(position).getCp_direccion()));
        hora.setText(String.valueOf(arrayList.get(position).getHorario()));
        compania.setText("Compañia: "+ arrayList.get(position).getNombre_compania());
        cliente.setText(arrayList.get(position).getNombre_cliente());
        String txt = "";
        if(idcli == 42){
            int fk_maquina= arrayList.get(position).getFk_maquina();
            try {
                Maquina mq = MaquinaDAO.buscarMaquinaPorFkMaquina(context,fk_maquina);
                String marca_tipo_combustion = mq.getTipo_combustion();
                 txt = marca_tipo_combustion;

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

        }

        txtTipoIntervencion.setText(arrayList.get(position).getTipo() + " - "+ txt);
        global.setTag(String.valueOf(arrayList.get(position).getId_parte()));
        return item;
    }
}
