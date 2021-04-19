package com.example.perfume.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.perfume.review.ReviewGenderFragment;
import com.example.perfume.review.ReviewRatingFragment;
import com.example.perfume.review.ReviewSeasonFragment;

import java.util.ArrayList;

public class ReviewTabAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> tabItems;

    public ReviewTabAdapter(@NonNull FragmentManager fm) {
        super(fm);

        tabItems = new ArrayList<>();
        tabItems.add(new ReviewRatingFragment());
        tabItems.add(new ReviewSeasonFragment());
        tabItems.add(new ReviewGenderFragment());
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
}
