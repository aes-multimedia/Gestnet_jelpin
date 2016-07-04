package com.multimedia.aes.gestnet_sgsv2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.multimedia.aes.gestnet_sgsv2.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class AdaptadorAvisos extends ArrayAdapter {

    private Context context;
    private int view;
    private ArrayList<JSONObject> arrayList;

    public AdaptadorAvisos(Context context, int view, ArrayList<JSONObject> arrayList) {
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
        JSONObject jsonObject = arrayList.get(position);
        TextView empresa = (TextView) item.findViewById(R.id.txtEmpresa);
        TextView id = (TextView)item.findViewById(R.id.txtId);
        try {
            empresa.setText(jsonObject.getString("nombre_entidad"));
            id.setText(jsonObject.getString("id_entidad"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return item;
    }
}
