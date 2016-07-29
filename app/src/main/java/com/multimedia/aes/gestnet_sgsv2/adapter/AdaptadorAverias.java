package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

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
        return item;
    }
}
