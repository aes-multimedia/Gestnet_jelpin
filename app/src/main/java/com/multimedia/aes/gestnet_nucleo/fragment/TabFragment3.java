package com.multimedia.aes.gestnet_nucleo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.multimedia.aes.gestnet_nucleo.R;

public class TabFragment3  extends Fragment implements View.OnClickListener {

    private View vista;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_3, container, false);

        return vista;
    }

    @Override
    public void onClick(View view) {

    }
}
