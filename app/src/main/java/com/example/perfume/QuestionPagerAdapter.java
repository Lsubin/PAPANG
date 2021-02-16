package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class QuestionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    public QuestionPagerAdapter(@NonNull FragmentManager fm){
        super(fm);
        mData = new ArrayList<>();
        mData.add(new Question1());
        mData.add(new Question2());
        mData.add(new Question3());
        mData.add(new Question4());
        mData.add(new Question5());
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return super.getPageTitle(position);
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
