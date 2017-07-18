package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.multimedia.aes.gestnet_nucleo.R;


public class TabFragment4 extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    private View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_4, container, false);
        return vista;
    }
    @Override
    public void onClick(View view) {

    }
    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}