package com.example.phong.myapplication.fragment;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class fragment_adapter extends FragmentPagerAdapter {
    private String string[] = {"quần" , "áo" , "bình luận" , "liên hệ"};
    private fragment_quan fragment_quan;
    private fragment_ao fragment_ao;
    private fragment_binhluan fragment_binhluan ;
    private fragment_lienhe fragment_lienhe;

    public fragment_adapter(FragmentManager fm) {
        super(fm);
        fragment_quan = new fragment_quan();
        fragment_ao = new fragment_ao();
        fragment_binhluan = new fragment_binhluan() ;
        fragment_lienhe = new fragment_lienhe();
    }

    @Override
    public Fragment getItem(int i) {
        switch (i)
        {
            case 0:
                return fragment_quan;
            case 1 :
                return fragment_ao;
            case 2:
                return fragment_binhluan;
            case 3:
                return fragment_lienhe;
        }
        return null;
    }

    @Override
    public int getCount() {
        return string.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return string[position];
    }
}
