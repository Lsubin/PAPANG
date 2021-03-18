package com.example.perfume.main.wish;

import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
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
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.perfume.MainActivity;
import com.example.perfume.R;
import com.example.perfume.WishPageAdapter;

public class WishFragment extends Fragment {

    Toolbar toolbar;
    ViewPager viewPager;
    PagerAdapter adapter;

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
        viewPager = (ViewPager)view.findViewById(R.id.viewpager);
        adapter = new WishPageAdapter(activity.getSupportFragmentManager());
        viewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        return view;
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
            //int current_page = viewPager.getCurrentItem();
            case R.id.menu_perfume:

                break;
            case R.id.menu_theme:

                break;
        }
        return super.onOptionsItemSelected(item);
    }

}