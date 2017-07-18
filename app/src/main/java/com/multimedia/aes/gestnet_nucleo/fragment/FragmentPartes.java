package com.multimedia.aes.gestnet_nucleo.fragment;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.multimedia.aes.gestnet_nucleo.R;
import com.multimedia.aes.gestnet_nucleo.adaptador.PageAdapter;

import org.json.JSONException;
import org.json.JSONObject;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class FragmentPartes extends Fragment implements View.OnClickListener {
    private View vista;
    private TabFragment1 tab1;
    private TabFragment2 tab2;
    private TabFragment3 tab3;
    private TabFragment4 tab4;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        vista = inflater.inflate(R.layout.settings_main, container, false);
        TabLayout tabLayout = (TabLayout) vista.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Cliente"));
        tabLayout.addTab(tabLayout.newTab().setText("Equipo"));
        tabLayout.addTab(tabLayout.newTab().setText("Operaciones"));
        tabLayout.addTab(tabLayout.newTab().setText("Finalización"));
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
                if (tab.getPosition()==0){

                }else if (tab.getPosition()==1){

                }else if (tab.getPosition()==2){

                }else if (tab.getPosition()==3){

                }
            }

            @Override
            public void onTabUnselected(final TabLayout.Tab tab) {
                if (tab.getPosition()==0){

                }else if (tab.getPosition()==1){

                }else if (tab.getPosition()==2){

                }else if (tab.getPosition()==3){

                }
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
