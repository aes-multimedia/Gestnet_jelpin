package com.multimedia.aes.gestnet_ssl.fragments;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.multimedia.aes.gestnet_ssl.R;
import com.multimedia.aes.gestnet_ssl.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_ssl.adaptador.PageAdapter;
import com.multimedia.aes.gestnet_ssl.dao.ParteDAO;
import com.multimedia.aes.gestnet_ssl.entidades.Parte;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class FragmentPartes extends Fragment implements View.OnClickListener {
    private View vista;
    private TabFragment1_cliente tab1;
    private TabFragment2_datosSiniestro tab2;
    private TabFragment3_finalizacion tab3;

    private Parte parte;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.settings_main, container, false);
        TabLayout tabLayout = vista.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Cliente"));
        JSONObject jsonObject = null;
        int idParte = 0;
        try {
            jsonObject = GestorSharedPreferences.getJsonParte(GestorSharedPreferences.getSharedPreferencesParte(getContext()));
            idParte = jsonObject.getInt("id");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        try {
            parte = ParteDAO.buscarPartePorId(getContext(), idParte);
        } catch (SQLException e) {
            e.printStackTrace();
        }
       if (parte.getEstado_android()!=0&&parte.getEstado_android()!=3){
            //0: asignado (rojo) // 1: iniciado (ambar) // 2: falta material (azul) // 3: finalizado (verde)
            tabLayout.addTab(tabLayout.newTab().setText("Datos Siniestro"));
            tabLayout.addTab(tabLayout.newTab().setText("Finalización"));
            /*tabLayout.addTab(tabLayout.newTab().setText("Operaciones"));
            tabLayout.addTab(tabLayout.newTab().setText("Materiales"));*/
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager2 viewPager = vista.findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (this, tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if (parte.getEstado_android() != 0 && parte.getEstado_android() != 3) {
                    switch (position) {
                        case 0:
                            tab.setText("Dirección y Equipo");
                            break;
                        case 1:
                            tab.setText("Datos Siniestro");
                            break;
                        case 2:
                            tab.setText("Visita Técnico");
                            break;
                        /*case 1:
                            tab.setText("Equipo");
                            break;
                        case 2:
                            tab.setText("Operaciones");
                            break;
                        case 3:
                            tab.setText("Materiales");
                            break;*/

                    }
                } else if(parte.getEstado_android() == 0) {
                    switch (position) {
                        case 0:
                            tab.setText("Dirección y Equipo");
                            break;
                    }
                } else {
                    switch (position) {
                        case 0:
                            tab.setText("Dirección y Equipo");
                            break;
                    }
                }
            }
        });
        tabLayoutMediator.attach();
        tabLayout.addOnTabSelectedListener (new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab1 = adapter.getTab1();
                tab2 = adapter.getTab2();
                tab3 = adapter.getTab3();
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });
        return vista;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    public void onClick(View view) {
    }

}
