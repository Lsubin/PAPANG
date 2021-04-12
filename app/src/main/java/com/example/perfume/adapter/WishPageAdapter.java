package com.example.perfume.adapter;

import com.example.perfume.main.wish.WishProudctFragment;
import com.example.perfume.main.wish.WishThemeFragment;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class WishPageAdapter extends FragmentPagerAdapter {

    private ArrayList<Fragment> mData;

    public WishPageAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mData = new ArrayList<>();
        mData.add(new WishProudctFragment());
        mData.add(new WishThemeFragment());
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return mData.get(position);
    }

    @Override
    public int getCount() {
        return mData.size();
    }

}
