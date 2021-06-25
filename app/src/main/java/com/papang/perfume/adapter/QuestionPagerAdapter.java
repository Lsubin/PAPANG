package com.papang.perfume.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.papang.perfume.Question1;
import com.papang.perfume.Question2;
import com.papang.perfume.Question3;

import java.util.ArrayList;

public class QuestionPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> mData;

    ArrayList<Integer> product_drawables;          // 상품 이미지
    ArrayList<String> company_name;                 // 회사 이름
    ArrayList<String> product_name;                 // 상품 이름
    ArrayList<Integer> wish_drawable;              // 좋아요 표시

    @Override
    public int getItemPosition(@NonNull Object object) {
        if (mData.contains(object))
            return mData.indexOf(object);
        else
            return POSITION_NONE;
    }

    public QuestionPagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
        mData = new ArrayList<>();
        mData.add(new Question1());
        mData.add(new Question2());
        mData.add(new Question3());
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

    public void addPage(Fragment fragment) {
        mData.add(fragment);
    }

    public void deletePage(int index) {
        int all = mData.size() - 1;
        for (int i = all; i > index; i--)
            mData.remove(i);
        notifyDataSetChanged();
    }
}
