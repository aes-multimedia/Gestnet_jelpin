package com.multimedia.aes.gestnet_sgsv2.adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment1;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment2;
import com.multimedia.aes.gestnet_sgsv2.fragment.TabFragment3;

public class PageAdapter extends FragmentStatePagerAdapter {
    private int mNumOfTabs;
    private TabFragment1 tab1;
    private TabFragment2 tab2;
    private TabFragment3 tab3;

    public PageAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                tab1 = new TabFragment1();
                return tab1;
            case 1:
                tab2 = new TabFragment2();
                return tab2;
            case 2:
                tab3 = new TabFragment3();
                return tab3;
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
}