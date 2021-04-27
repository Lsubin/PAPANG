package com.example.perfume.search;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
import com.example.perfume.main.home.Product_Decoration;
import com.example.perfume.main.home.Product_RecyclerView_Adapter;
import com.google.android.flexbox.AlignItems;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexWrap;
import com.google.android.flexbox.FlexboxItemDecoration;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

public class SearchActivity extends AppCompatActivity {

    ImageButton search_back_btn;                // 뒤로가기 버튼
    EditText search_input;                      // 검색어 입력 창
    RecyclerView recent_search_item;            // 최근 검색어 리싸이클러뷰
    RecyclerView recommendation_search_item;    // 추천 검색어 리싸이클러뷰
    ImageButton search_find_perfume_btn;        // 향수 추천 버튼
    ImageButton btn_all_delete;

    TextView search_txt2;

    ConstraintLayout search_frame3;

    FlexboxLayoutManager layoutManager;
    RecentSearchAdapter recent_adapter;
    RecommendationSearchAdapter recommendation_adapter;
    FlexboxItemDecoration itemDecoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        search_frame3 = (ConstraintLayout)findViewById(R.id.search_frame3);

        search_back_btn = (ImageButton)findViewById(R.id.search_back_btn);
        search_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        search_input = (EditText)findViewById(R.id.search_input);

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
    }
}