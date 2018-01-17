package com.multimedia.aes.gestnet_nucleo.adaptador;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment1_cliente;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment2_equipo;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment3_operaciones;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment4_finalizacion;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment5_documentacion;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment6_materiales;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private TabFragment1_cliente tab1;
    private TabFragment2_equipo tab2;
    private TabFragment3_operaciones tab3;
    private TabFragment4_finalizacion tab4;
    private TabFragment5_documentacion tab5;
    private TabFragment6_materiales tab6;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new TabFragment1_cliente();
                return tab1;
            case 1:
                tab2 = new TabFragment2_equipo();
                return tab2;
            case 2:
                tab3 = new TabFragment3_operaciones();
                return tab3;
            case 3:
                tab4 = new TabFragment4_finalizacion();
                return tab4;
            case 4:
                tab5 = new TabFragment5_documentacion();
                return tab5;
            case 5:
                tab6 = new TabFragment6_materiales();
                return tab6;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public TabFragment1_cliente getTab1() {
        return tab1;
    }
    public TabFragment2_equipo getTab2() {
        return tab2;
    }
    public TabFragment3_operaciones getTab3() {
        return tab3;
    }
    public TabFragment4_finalizacion getTab4() {
        return tab4;
    }
    public TabFragment5_documentacion getTab5() {
        return tab5;
    }
    public TabFragment6_materiales getTab6() { return tab6; }
}