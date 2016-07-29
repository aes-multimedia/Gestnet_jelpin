package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.entities.Averia;

import org.json.JSONObject;

import java.util.ArrayList;

public class AdaptadorAverias extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<Averia> arrayList;

    public AdaptadorAverias(Context context, int view, ArrayList<Averia> arrayList) {
        super(context, view, arrayList);
        this.context = context;
        this.view = view;
        this.arrayList=arrayList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }
        TextView direccion = (TextView) item.findViewById(R.id.txtDireccion);
        TextView provincia = (TextView) item.findViewById(R.id.txtProvincia);
        TextView cp = (TextView) item.findViewById(R.id.txtCP);
        LinearLayout global = (LinearLayout)item.findViewById(R.id.global);
        ImageView ivEstado = (ImageView)item.findViewById(R.id.ivEstado);
        direccion.setText(String.valueOf(arrayList.get(position).getNom_calle()+" - "+arrayList.get(position).getCod_portal()+" - "+arrayList.get(position).getTip_piso()+"-"+arrayList.get(position).getTip_mano()));
        cp.setText("C.P.:  "+String.valueOf(arrayList.get(position).getCod_postal()));
        provincia.setText("("+String.valueOf(arrayList.get(position).getNombre_provincia())+"-"+String.valueOf(arrayList.get(position).getNombre_poblacion())+")");
        global.setTag(String.valueOf(arrayList.get(position).getId_averia()));
        return item;
    }
}
