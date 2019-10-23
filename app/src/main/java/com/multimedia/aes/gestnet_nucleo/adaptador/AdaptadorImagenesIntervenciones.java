package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ClienteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Cliente;
import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;

public class AdaptadorImagenesIntervenciones extends ArrayAdapter implements View.OnClickListener{


    private Context context;
    private int view;
    private ArrayList<String> rutaImagenes;
    private Cliente cliente;


    public AdaptadorImagenesIntervenciones(Context context, int view, ArrayList<String> rutaImagenes) {
        super(context, view, rutaImagenes);
        this.context=context;
        this.view=view;
        this.rutaImagenes=rutaImagenes;
        try {
            cliente = ClienteDAO.buscarCliente(getContext());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }





    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        if (item == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            item = inflater.inflate(view, null);
        }

        ImageView ivIntervenciones1= item.findViewById(R.id.ivIntervenciones1);
        Picasso.get().load("http://"+cliente.getIp_cliente()+rutaImagenes.get(position)).resize(1300,1100).into(ivIntervenciones1);
        ivIntervenciones1.setTag(position);
        ivIntervenciones1.setOnClickListener(this);

        return item;
    }

    @Override
    public void onClick(View v) {
        int position = Integer.parseInt(v.getTag().toString());
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://"+cliente.getIp_cliente()+rutaImagenes.get(position)));
        context.startActivity(browserIntent);
    }
}
