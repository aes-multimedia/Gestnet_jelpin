package com.multimedia.aes.gestnet_sgsv2.fragment;


import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.multimedia.aes.gestnet_sgsv2.R;
import com.multimedia.aes.gestnet_sgsv2.SharedPreferences.GestorSharedPreferences;
import com.multimedia.aes.gestnet_sgsv2.adapter.PageAdapter;
import com.multimedia.aes.gestnet_sgsv2.dao.MantenimientoDAO;
import com.multimedia.aes.gestnet_sgsv2.entities.Mantenimiento;
import com.multimedia.aes.gestnet_sgsv2.entities.MantenimientoTerminado;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;

public class FragmentMantenimiento extends Fragment implements View.OnClickListener {
    private View vista;
    private boolean activo;
    private Mantenimiento mantenimiento;
    private Button btnFinalizar;
    private TabFragment1 tab1;
    private TabFragment2 tab2;
    private TabFragment3 tab3;
    private TabFragment4 tab4;
    private MantenimientoTerminado mantenimientoTerminado = new MantenimientoTerminado();
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.settings_main, container, false);
        TabLayout tabLayout = (TabLayout) vista.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Cliente"));
        try {
            JSONObject jsonObject = GestorSharedPreferences.getJsonMantenimiento(GestorSharedPreferences.getSharedPreferencesMantenimiento(getContext()));
            int id = jsonObject.getInt("id");
            mantenimiento = MantenimientoDAO.buscarMantenimientoPorId(getContext(),id);
        } catch (JSONException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (!mantenimiento.getEstado_android().equals("0")) {
            tabLayout.addTab(tabLayout.newTab().setText("Equipo"));
            tabLayout.addTab(tabLayout.newTab().setText("Operaciones"));
            tabLayout.addTab(tabLayout.newTab().setText("Finalización"));
        }
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        final ViewPager viewPager = (ViewPager) vista.findViewById(R.id.pager);
        final PageAdapter adapter = new PageAdapter
                (getFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
                tab1 = adapter.getTab1();
                tab2 = adapter.getTab2();
                tab3 = adapter.getTab3();
                tab4 = adapter.getTab4();
                if (tab.getPosition()==3){
                    tab4.setMantenimientoTerminado(mantenimientoTerminado);
                }else if (tab.getPosition()==1){
                    tab2.renovar();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                if (tab.getPosition()==1){
                    try {
                        mantenimientoTerminado = tab2.guardarDatos(mantenimientoTerminado);
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }else if (tab.getPosition()==2){
                    mantenimientoTerminado = tab3.guardarDatos(mantenimientoTerminado);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (tab.getPosition()==1){
                    tab2.renovar();
                }
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
