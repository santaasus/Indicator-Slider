package com.example.vladimir.customslider;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.vladimir.customslider.fragments.FragmentBlue;
import com.example.vladimir.customslider.fragments.FragmentBrown;
import com.example.vladimir.customslider.fragments.FragmentRed;

public class FragmentPagerAdapter extends FragmentStatePagerAdapter {

    public FragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position){
            case 0: return FragmentBlue.newInstance();
            case 1: return FragmentBrown.newInstance();
            case 2: return FragmentRed.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
