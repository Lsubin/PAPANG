package com.example.perfume.main.wish;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Handler;
import android.text.BoringLayout;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.perfume.MainActivity;
import com.example.perfume.R;
import com.example.perfume.WishPageAdapter;

public class WishFragment extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    PagerAdapter adapter;
    int current_page;
    int p;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wish, container, false);

        //상단바 툴바 셋팅
        toolbar=(Toolbar)view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("");
        activity.setSupportActionBar(toolbar);

        setHasOptionsMenu(true);

        // 뷰페이저
        viewPager = (ViewPager)view.findViewById(R.id.viewpager_wish);
        adapter = new WishPageAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        current_page = viewPager.getCurrentItem();

        return view;
    }

    @Override
    public void onPrepareOptionsMenu(@NonNull Menu menu) {
        super.onPrepareOptionsMenu(menu);

        if(p == 0){
        }
        else if(p == 1){
        }
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.wish_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.menu_perfume:
                // 향수 페이지라면
                viewPager.setCurrentItem(0);
                break;
            case R.id.menu_theme:
                // 테마 페이지라면
                viewPager.setCurrentItem(1);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}