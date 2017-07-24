package com.multimedia.aes.gestnet_nucleo.adaptador;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment1;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment2;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment3;
import com.multimedia.aes.gestnet_nucleo.fragment.TabFragment4;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private TabFragment1 tab1;
    private TabFragment2 tab2;
    private TabFragment3 tab3;
    private TabFragment4 tab4;
    private final Bundle bundle;

    public PageAdapter(FragmentManager fm, int NumOfTabs, Bundle bundle) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.bundle = bundle;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new TabFragment1();
                tab1.setArguments(bundle);
                return tab1;
            case 1:
                tab2 = new TabFragment2();
                tab2.setArguments(bundle);
                return tab2;
            case 2:
                tab3 = new TabFragment3();
                tab3.setArguments(bundle);
                return tab3;
            case 3:
                tab4 = new TabFragment4();
                tab4.setArguments(bundle);
                return tab4;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }

    public TabFragment1 getTab1() {
        return tab1;
    }
    public TabFragment2 getTab2() {
        return tab2;
    }
    public TabFragment3 getTab3() {
        return tab3;
    }
    public TabFragment4 getTab4() {
        return tab4;
    }
}