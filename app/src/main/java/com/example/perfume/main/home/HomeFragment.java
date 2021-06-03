package com.example.perfume.main.home;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferListener;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferNetworkLossHandler;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferObserver;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferState;
import com.amazonaws.mobileconnectors.s3.transferutility.TransferUtility;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3Client;
import com.bumptech.glide.Glide;
import com.example.perfume.AllResultProductActivity;
import com.example.perfume.AppSatisfactionActivity;
import com.example.perfume.NoResultActivity;
import com.example.perfume.ProductDetailsActivity;
import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.Perfume;
import com.example.perfume.search.SearchActivity;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class HomeFragment extends Fragment {
    View root;

    List<Perfume> r_perfumes;       // 파팡 추천 향수
    DataService dataService;
    DataApi dataApi;
    Retrofit retrofit;

    RecyclerView recyclerView;      // 당신이 잊지 못할 향수
    RecyclerView recyclerView2;     // 파팡 추천 향수
    RecyclerView recyclerview3;     // 파팡 이벤트
    RecyclerView recyclerView4;     // 추천 결과 향수

    Product_RecyclerView_Adapter adapter;
    Product_RecyclerView_Adapter adapter2;
    Product_Result_RecyclerView_Adpater adapter3;
    Event_RecyclerView_Adapter event_adapter;
    RecyclerView.LayoutManager mLayoutManager;
    Product_Decoration decoration;

    ViewPager magazine_viewpager;   // magazine 뷰페이저

    ImageButton btn_search;
    ImageButton find_btn;

    ImageButton btn_satisfaction_good;
    ImageButton btn_satisfaction_bad;

    private boolean itemTouch;

    boolean isResult;   // 추천 받았는지
    ConstraintLayout frame1;
    ConstraintLayout frame2;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);

        find_btn = (ImageButton) root.findViewById(R.id.perfume_find_btn);
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recommendation = new Intent(getContext(), RecommendationActivity.class);
                startActivity(recommendation);
            }
        });

        btn_search = (ImageButton) root.findViewById(R.id.main_search);
        btn_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_search = new Intent(root.getContext(), SearchActivity.class);
                startActivity(go_search);
            }
        });

        decoration = new Product_Decoration();

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);
        r_perfumes = new ArrayList<>();     // 파팡 추천 향수

        recyclerView = root.findViewById(R.id.main_recyclerview1);
        mLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.addItemDecoration(decoration);

        recyclerView2 = root.findViewById(R.id.main_recyclerview2);
        mLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView2.setLayoutManager(mLayoutManager);
        recyclerView2.addItemDecoration(decoration);

        dataApi.selectAll().enqueue(new Callback<List<Perfume>>() {
            @Override
            public void onResponse(Call<List<Perfume>> call, Response<List<Perfume>> response) {
                r_perfumes = response.body();
                adapter = new Product_RecyclerView_Adapter(root.getContext(), r_perfumes);
                recyclerView.setAdapter(adapter);

                adapter2 = new Product_RecyclerView_Adapter(root.getContext(), r_perfumes);
                recyclerView2.setAdapter(adapter2);

                adapter3 = new Product_Result_RecyclerView_Adpater(root.getContext(), r_perfumes);
                recyclerView4.setAdapter(adapter3);
            }

            @Override
            public void onFailure(Call<List<Perfume>> call, Throwable t) {

                Log.e("연결", t.getMessage());
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (MotionEvent.ACTION_UP == e.getAction() && itemTouch) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    String p_name = adapter.getName(rv.getChildAdapterPosition(reV));
                    Intent gotoDetail = new Intent(root.getContext(), ProductDetailsActivity.class);
                    gotoDetail.putExtra("name", p_name);
                    startActivity(gotoDetail);
                } else if (MotionEvent.ACTION_DOWN == e.getAction()) {
                    itemTouch = true;
                }
                return false;

            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                itemTouch = false;
            }
        });

        recyclerview3 = root.findViewById(R.id.main_recyclerview3);
        mLayoutManager = new LinearLayoutManager(root.getContext());
        recyclerview3.setLayoutManager(mLayoutManager);
        event_adapter = new Event_RecyclerView_Adapter(root.getContext());
        recyclerview3.setAdapter(event_adapter);

        // 추천 받은 향수
        recyclerView4 = root.findViewById(R.id.main_recyclerview4);
        mLayoutManager = new LinearLayoutManager(root.getContext(), LinearLayoutManager.HORIZONTAL, false);
        recyclerView4.setLayoutManager(mLayoutManager);
        recyclerView4.addItemDecoration(decoration);

        // 메거진 뷰페이저 설정
        magazine_viewpager = root.findViewById(R.id.main_magazine_viewpager);
        MainViewPagerAdapter viewAdapter = new MainViewPagerAdapter(root.getContext());
        magazine_viewpager.setAdapter(viewAdapter);

        // 앱 만족도 조사 넘어가기
        btn_satisfaction_good = (ImageButton) root.findViewById(R.id.btn_satisfaction_good);
        btn_satisfaction_bad = (ImageButton) root.findViewById(R.id.btn_satisfaction_bad);

        btn_satisfaction_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AppSatisfactionActivity.class);
                startActivity(intent);
            }
        });

        btn_satisfaction_bad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AppSatisfactionActivity.class);
                startActivity(intent);
            }
        });

        // 추천 결과 없을 때, 있을 때..
        frame1 = (ConstraintLayout)root.findViewById(R.id.frame1);
        frame2 = (ConstraintLayout)root.findViewById(R.id.frame2);
        isResult = false;
        if(isResult)
        {
            frame1.setVisibility(View.INVISIBLE);
            frame2.setVisibility(View.VISIBLE);
        }
        else
        {
            frame1.setVisibility(View.VISIBLE);
            frame2.setVisibility(View.INVISIBLE);
        }
        return root;
    }

}