package com.multimedia.aes.gestnet_nucleo.adaptador;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.clases.DataStock;
import java.util.ArrayList;


public class AdaptadorListaStock extends ArrayAdapter<DataStock> {
    public AdaptadorListaStock(Context context, ArrayList<DataStock> stocks) {
        super(context, 0, stocks);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        DataStock stock = getItem(position);
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_stock, parent, false);
        }
        TextView tvName = (TextView) convertView.findViewById(R.id.tvNombreEntidad);
        TextView tvStock = (TextView) convertView.findViewById(R.id.tvStock);
        tvName.setText(stock.getNombreEntidad());
        tvStock.setText(String.valueOf(stock.getStock()));
        return convertView;
    }
}