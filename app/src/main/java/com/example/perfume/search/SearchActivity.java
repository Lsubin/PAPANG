package com.example.perfume.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
import com.example.perfume.adapter.ResultProductAdpater;
import com.example.perfume.main.home.Product_Decoration;
import com.example.perfume.main.home.Product_RecyclerView_Adapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

import java.util.ArrayList;

import static android.view.inputmethod.EditorInfo.IME_ACTION_SEARCH;

public class SearchActivity extends AppCompatActivity {

    ImageButton search_back_btn;                // 뒤로가기 버튼
    EditText search_input;                      // 검색어 입력 창
    RecyclerView recent_search_item;            // 최근 검색어 리싸이클러뷰
    RecyclerView recommendation_search_item;    // 추천 검색어 리싸이클러뷰
    RecyclerView result_search_item;            // 향수 검색 결과 리싸이클러뷰
    ImageButton search_find_perfume_btn;        // 향수 추천 버튼
    ImageButton btn_all_delete;

    TextView search_txt2;
    TextView count_txt1;

    ConstraintLayout search_frame3;             // 최근 검색어
    ConstraintLayout search_frame4;             // 추천 검색어
    ConstraintLayout search_frame5;             // 나만의 향수
    ConstraintLayout search_frame6;             // 검색된 향수
    ConstraintLayout search_frame7;             // 검색 결과 x

    FlexboxLayoutManager layoutManager;
    RecentSearchAdapter recent_adapter;
    RecommendationSearchAdapter recommendation_adapter;
    FlexboxItemDecoration itemDecoration;

    ResultProductAdpater result_product_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // 키보드 밀림 방지
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        search_frame3 = (ConstraintLayout)findViewById(R.id.search_frame3);
        search_frame4 = (ConstraintLayout)findViewById(R.id.search_frame4);
        search_frame5 = (ConstraintLayout)findViewById(R.id.search_frame5);
        search_frame6 = (ConstraintLayout)findViewById(R.id.search_frame6);
        search_frame7 = (ConstraintLayout)findViewById(R.id.search_frame7);

        search_back_btn = (ImageButton)findViewById(R.id.search_back_btn);
        search_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search_input = (EditText)findViewById(R.id.search_input);
        count_txt1 = (TextView)findViewById(R.id.count_txt1);

        recent_search_item = (RecyclerView)findViewById(R.id.recent_search_item);
        layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexDirection (FlexDirection.ROW);
        layoutManager.setJustifyContent (JustifyContent.FLEX_START);
        recent_search_item.setLayoutManager(layoutManager);
        recent_adapter = new RecentSearchAdapter(getApplicationContext());
        recent_search_item.setAdapter(recent_adapter);

        recommendation_search_item = (RecyclerView)findViewById(R.id.recommendation_search_item);
        layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexDirection (FlexDirection.ROW);
        layoutManager.setJustifyContent (JustifyContent.FLEX_START);
        recommendation_search_item.setLayoutManager(layoutManager);
        recommendation_adapter = new RecommendationSearchAdapter(getApplicationContext());
        recommendation_search_item.setAdapter(recommendation_adapter);

        search_find_perfume_btn = (ImageButton)findViewById(R.id.search_find_perfume_btn);
        search_find_perfume_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent go_recommendation = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(go_recommendation);
                finish();
            }
        });

        btn_all_delete = (ImageButton)findViewById(R.id.btn_all_delete);
        btn_all_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recent_adapter.allDelete();
                search_frame3.setVisibility(View.GONE);
                Toast.makeText(getApplicationContext(), "전체삭제 했습니다", 0).show();
            }
        });

        search_txt2 = (TextView)findViewById(R.id.search_txt2);
        search_txt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recent_adapter.update();
                search_frame3.setVisibility(View.VISIBLE);
            }
        });

        // 검색어 edittext input
        search_input.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                switch (actionId)
                {
                    case IME_ACTION_SEARCH :
                        // 검색 결과가 있을 때
                        if(search_input.length() != 0)
                        {
                            search_frame3.setVisibility(View.GONE);
                            search_frame4.setVisibility(View.GONE);
                            search_frame5.setVisibility(View.GONE);
                            search_frame6.setVisibility(View.VISIBLE);
                            search_frame7.setVisibility(View.GONE);
                        }
                        // 검색 결과 없을 때 --> 일단 예시로 공백일 때
                        if(search_input.length() == 0)
                        {
                            search_frame3.setVisibility(View.GONE);
                            search_frame4.setVisibility(View.GONE);
                            search_frame5.setVisibility(View.GONE);
                            search_frame6.setVisibility(View.GONE);
                            search_frame7.setVisibility(View.VISIBLE);
                        }

                        break;
                }
                return true;
            }
        });

        // 예시
        ArrayList<Integer> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<String> data3 = new ArrayList<>();
        ArrayList<Integer> data4 = new ArrayList<>();

        // 예시로 1개 추가
        for(int i = 0; i < 1; i++)
        {
            data1.add(R.mipmap.result_produc_img);
            data2.add("CHANEL");
            data3.add("넘버5 오드빠르펭");
            data4.add(R.mipmap.icon_unfull_heart);
        }

        // 향수 검색 결과
        result_search_item = (RecyclerView)findViewById(R.id.result_search_item);
        result_product_adapter = new ResultProductAdpater(this, data1, data2, data3, data4);
        result_search_item.setLayoutManager(new LinearLayoutManager(this));
        result_search_item.setAdapter(result_product_adapter);

        count_txt1.setText(data1.size() + "건");

    }
}