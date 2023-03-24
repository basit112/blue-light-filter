package com.bluelight.nightfilter.Adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bluelight.nightfilter.fragment.FragmentHelp1;
import com.bluelight.nightfilter.fragment.FragmentHelp2;
import com.bluelight.nightfilter.fragment.FragmentHelp3;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
    }

    @NonNull
    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new FragmentHelp1();
            case 1:
                return new FragmentHelp2();
            default:
                return new FragmentHelp3();

        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}
