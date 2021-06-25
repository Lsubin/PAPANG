package com.papang.perfume.adapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.papang.perfume.review.ReviewGenderFragment;
import com.papang.perfume.review.ReviewRatingFragment;
import com.papang.perfume.review.ReviewSeasonFragment;

import java.util.ArrayList;

public class ReviewTabAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> tabItems;
    private ArrayList<String> tabText;

    public ReviewTabAdapter(@NonNull FragmentManager fm) {
        super(fm);

        tabItems = new ArrayList<>();
        tabItems.add(new ReviewRatingFragment());
        tabItems.add(new ReviewSeasonFragment());
        tabItems.add(new ReviewGenderFragment());

        tabText = new ArrayList<>();
        tabText.add("평점");
        tabText.add("계절");
        tabText.add("성별");
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return tabItems.get(position);
    }

    @Override
    public int getCount() {
        return tabItems.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return tabText.get(position);
    }
}
