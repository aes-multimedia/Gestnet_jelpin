package com.multimedia.aes.gestnet_nucleo.fragment;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.dao.ParteDAO;
import com.multimedia.aes.gestnet_nucleo.entidades.Parte;

import java.sql.SQLException;
import java.util.ArrayList;

public class TabFragment1 extends Fragment implements View.OnClickListener {
    private View vista;
    private Parte parte = null;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.tab_fragment_1, container, false);
        Bundle bundle = this.getArguments();
        if(bundle != null) {
            int idParte = bundle.getInt("id", 0);
            try {
                parte = ParteDAO.buscarPartePorId(getContext(), idParte);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        inicializarVariables();
        darValoresVariables();
        return vista;
    }

    private void inicializarVariables() {
    }
    public void darValoresVariables(){
    }

    @Override
    public void onClick(View view) {

    }
}