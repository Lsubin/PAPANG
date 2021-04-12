package com.example.perfume.main.home;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.perfume.ProductDetailsActivity;
import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
import com.example.perfume.search.SearchActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    View root;

    RecyclerView recyclerView;      // 당신이 잊지 못할 향수
    RecyclerView recyclerView2;     // 파팡 추천 향수
    RecyclerView recyclerview3;     // 파팡 이벤트

    Product_RecyclerView_Adapter adapter;
    Event_RecyclerView_Adapter event_adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Product_Decoration decoration;

    ViewPager magazine_viewpager;   // magazine 뷰페이저

    ImageButton btn_search;
    ImageButton find_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        /*
        product1_layout = (LinearLayout)root.findViewById(R.id.product1_layout);
        // 상품 누르면 상세페이지로 넘어가기
        product1_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ProductDetailsActivity.class);
                startActivity(intent);
            }
        });
         */

        find_btn = (ImageButton)root.findViewById(R.id.perfume_find_btn);
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recommendation = new Intent(getContext(), RecommendationActivity.class);
                startActivity(recommendation);
            }
        });

        btn_search = (ImageButton)root.findViewById(R.id.main_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_search = new Intent(root.getContext(), SearchActivity.class);
                startActivity(go_search);
            }
        });

        // 예시
        ArrayList<Drawable> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<String> data3 = new ArrayList<>();

        // 예시로 8개 추가
        for(int i = 0; i < 4; i++)
        {
            data1.add(getResources().getDrawable(R.mipmap.ex_chanel_image));
            data2.add("샤넬");
            data3.add("넘버 5 오뜨빠르펭");
        }

        decoration = new Product_Decoration();

        recyclerView = root.findViewById(R.id.main_recyclerview1);
        mLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        adapter = new Product_RecyclerView_Adapter(root.getContext(), data1, data2, data3);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(decoration);

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                Intent gotoDetail = new Intent(root.getContext(), ProductDetailsActivity.class);
                startActivity(gotoDetail);
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView2 = root.findViewById(R.id.main_recyclerview2);
        mLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(mLayoutManager);
        adapter = new Product_RecyclerView_Adapter(root.getContext(), data1, data2, data3);
        recyclerView2.setAdapter(adapter);
        recyclerView2.addItemDecoration(decoration);

        recyclerview3 = root.findViewById(R.id.main_recyclerview3);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerview3.setLayoutManager(mLayoutManager);
        event_adapter = new Event_RecyclerView_Adapter(root.getContext());
        recyclerview3.setAdapter(event_adapter);


        // 메거진 뷰페이저 설정
        magazine_viewpager = root.findViewById(R.id.main_magazine_viewpager);
        MainViewPagerAdapter viewAdapter = new MainViewPagerAdapter(root.getContext());
        magazine_viewpager.setAdapter(viewAdapter);

        return root;
    }
}