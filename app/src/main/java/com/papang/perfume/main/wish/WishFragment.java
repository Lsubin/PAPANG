package com.papang.perfume.main.wish;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.papang.perfume.MainActivity;
import com.papang.perfume.R;
import com.papang.perfume.adapter.WishPageAdapter;

public class WishFragment extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    PagerAdapter adapter;
    int current_page;
    int p;
    private Menu mOptionsMenu;
    MenuItem theme;
    MenuItem perfume;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);

        //상단바 툴바 셋팅
        toolbar = (Toolbar) view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("");
        activity.setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        // 뷰페이저
        viewPager = (ViewPager) view.findViewById(R.id.viewpager_wish);
        adapter = new WishPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        current_page = viewPager.getCurrentItem();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);
        mOptionsMenu = menu;
        theme = mOptionsMenu.findItem(R.id.menu_theme);
        perfume = mOptionsMenu.findItem(R.id.menu_perfume);
        clickColor(perfume);
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.wish_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_perfume:
                // 향수 페이지라면
                viewPager.setCurrentItem(0);
                clickColor(item);
                unclickColor(theme);
                break;
            case R.id.menu_theme:
                // 테마 페이지라면
                viewPager.setCurrentItem(1);
                clickColor(item);
                unclickColor(perfume);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void clickColor(MenuItem item) {
        // Cast to a TextView instance if the menu item was found

        SpannableString s = new SpannableString(item.getTitle().toString());
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#6557FF")), 0, s.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // provide whatever color you want here.
        item.setTitle(s);
    }

    private void unclickColor(MenuItem item) {
        // Cast to a TextView instance if the menu item was found

        SpannableString s = new SpannableString(item.getTitle().toString());
        s.setSpan(new ForegroundColorSpan(Color.parseColor("#000000")), 0, s.length(), Spannable.SPAN_INCLUSIVE_INCLUSIVE); // provide whatever color you want here.
        item.setTitle(s);
    }

}