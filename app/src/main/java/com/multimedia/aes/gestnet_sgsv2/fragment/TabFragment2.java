package com.multimedia.aes.gestnet_sgsv2.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.multimedia.aes.gestnet_sgsv2.R;


public class TabFragment2 extends Fragment {

    private View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_2, container, false);
        return vista;
    }

}