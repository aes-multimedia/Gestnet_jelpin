package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.multimedia.aes.gestnet_nucleo.R;

public class TabFragment1 extends Fragment implements View.OnClickListener {
    private View vista;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_1, container, false);
        return vista;
    }
    @Override
    public void onClick(View view) {
    }
}