package com.multimedia.aes.gestnet_ssl.adaptador;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.multimedia.aes.gestnet_ssl.fragments.FragmentPartes;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment1_cliente;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment2_datosSiniestro;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment2_equipo_OLD;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment3_operaciones_old;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment3_finalizacion;
import com.multimedia.aes.gestnet_ssl.fragments.TabFragment6_materiales;

public class PageAdapter extends FragmentStateAdapter {
    private final int mNumOfTabs;
    private TabFragment1_cliente tab1;
    private TabFragment2_datosSiniestro tab2;
    private TabFragment3_finalizacion tab3;
    private final FragmentPartes fm;

    public PageAdapter(FragmentPartes fm, int NumOfTabs) {
        super(fm);
        this.fm = fm;
        this.mNumOfTabs = NumOfTabs;
    }

    public TabFragment1_cliente getTab1() {
        return tab1;
    }
    public TabFragment2_datosSiniestro getTab2() {
        return tab2;
    }
    public TabFragment3_finalizacion getTab3() {
        return tab3;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0:
                tab1 = new TabFragment1_cliente();
                return tab1;
            case 1:
                tab2 = new TabFragment2_datosSiniestro();
                return tab2;
            case 2:
                tab3 = new TabFragment3_finalizacion();
                return tab3;
            default:
                return null;
        }
    }

    @Override
    public int getItemCount() {
        return mNumOfTabs;
    }
}