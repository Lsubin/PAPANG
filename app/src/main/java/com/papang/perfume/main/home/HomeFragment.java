package com.papang.perfume.main.home;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.papang.perfume.AllMagazineActivity;
import com.papang.perfume.AppSatisfactionActivity;
import com.papang.perfume.ProductDetailsActivity;
import com.papang.perfume.R;
import com.papang.perfume.RecommendationActivity;
import com.papang.perfume.SettingDetail2Activity;
import com.papang.perfume.SettingDetail3Activity;
import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;
import com.papang.perfume.data.Recommendation;
import com.papang.perfume.search.SearchActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static java.lang.Thread.sleep;

public class HomeFragment extends Fragment {
    View root;

    List<Recommendation> r_perfumes;       // 당신이 잊지 못할 향수
    List<Recommendation> y_perfumes;       // 파팡 추천 향수
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
    ImageButton magazine_btn;

    ImageButton btn_satisfaction_good;
    ImageButton btn_satisfaction_bad;

    private boolean itemTouch;

    boolean isResult;   // 추천 받았는지
    ConstraintLayout frame1;
    ConstraintLayout frame2;

    ProgressBar loading_pb;
    ConstraintLayout whole_frame;
    Thread thread;
    int i  = 0;
    Boolean isCheckedImg1 = false;
    Boolean isCheckedImg2 = false;
    Boolean isCheckedData = false;

    Button tc1_btn;
    Button tc2_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_home, container, false);
        // 로딩바 - 화면 터치 막기
        getActivity().getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);

        // 매거진 이동
        magazine_btn = (ImageButton) root.findViewById(R.id.magazine_btn);
        magazine_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent magazine = new Intent(getContext(), AllMagazineActivity.class);
                startActivity(magazine);
            }
        });

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

        // 이용약관 이동
        tc1_btn = (Button)root.findViewById(R.id.tc1_btn);
        tc1_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingDetail2Activity.class);
                startActivity(intent);
            }
        });

        // 개인정보처리방침 이동
        tc2_btn = (Button)root.findViewById(R.id.tc2_btn);
        tc2_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingDetail3Activity.class);
                startActivity(intent);
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

        loading_pb = (ProgressBar)root.findViewById(R.id.loading_pb);
        whole_frame = (ConstraintLayout)root.findViewById(R.id.whole_frame);

        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        i++;
                        sleep(1000);
                        if(isCheckedData == true && isCheckedImg1 == true && isCheckedImg2 == true) { // 향수 정보랑 이미지 불러와졌으면
                            handler.sendEmptyMessage(1);
                            break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }); thread.start();

        dataApi.getPerfumeUp().enqueue(new Callback<List<Recommendation>>() {
            @Override
            public void onResponse(Call<List<Recommendation>> call, Response<List<Recommendation>> response) {
                r_perfumes = response.body();
                isCheckedData = true;
                getPerfumes();
            }
            @Override
            public void onFailure(Call<List<Recommendation>> call, Throwable t) {
                Log.e("연결", t.getMessage());
            }
        });

        // 당신이 잊지 못할 향수는?
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

        // 파팡 추천 향수
        recyclerView2.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (MotionEvent.ACTION_UP == e.getAction() && itemTouch) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    String p_name = adapter2.getName(rv.getChildAdapterPosition(reV));
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

        recyclerView2.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

        // 로딩 중

    }

    private void getPerfumes(){
        dataApi.getPerfumeDown().enqueue(new Callback<List<Recommendation>>() {
            @Override
            public void onResponse(Call<List<Recommendation>> call, Response<List<Recommendation>> response) {
                y_perfumes = response.body();
                getData();
            }

            @Override
            public void onFailure(Call<List<Recommendation>> call, Throwable t) {

            }
        });
    }

    private void getData() {
        adapter = new Product_RecyclerView_Adapter(root.getContext(), r_perfumes, HomeFragment.this);
        recyclerView.setAdapter(adapter);

        adapter2 = new Product_RecyclerView_Adapter(root.getContext(), y_perfumes, HomeFragment.this);
        recyclerView2.setAdapter(adapter2);

        adapter3 = new Product_Result_RecyclerView_Adpater(root.getContext(), r_perfumes, HomeFragment.this);
        recyclerView4.setAdapter(adapter3);
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 1)
            {
                loading_pb.setVisibility(View.INVISIBLE);
                whole_frame.setVisibility(View.VISIBLE);
                whole_frame.setBackground(null);
                getActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    };

    // 이미지는 어댑터에서 불러옴, 불러오면 true
    public boolean setCheckedImg1(boolean check)
    {
        return isCheckedImg1 = check;
    }

    // 결과 이미지
    public boolean setCheckedImg2(boolean check)
    {
        return isCheckedImg2 = check;
    }
}