package com.papang.perfume.review;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.papang.perfume.R;
import com.papang.perfume.adapter.ReviewImageAdapter;
import com.papang.perfume.main.home.Product_Decoration;
import com.papang.perfume.search.RecommendationSearchAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

public class DetailReviewActivity extends AppCompatActivity {

    ImageButton dr_btn_exit;    // 닫기 버튼

    ImageView dr_user_img;      // 유저 이미지

    TextView dr_user_name;      // 유지 이름
    TextView dr_type;           // 리뷰 타입
    TextView dr_date;           // 리뷰 작성 날짜
    TextView dr_review_text;    // 리뷰 내용

    RatingBar dr_ratingbar;     // 리뷰 평점

    RecyclerView dr_image_list;     // 리뷰 이미지 리스트
    RecyclerView dr_review_tag;     // 리뷰 태그

    FlexboxLayoutManager layoutManager;
    RecommendationSearchAdapter tag_adapter;
    LinearLayoutManager mLayoutManager;
    Product_Decoration decoration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_review);

        dr_btn_exit = (ImageButton) findViewById(R.id.dr_btn_exit);
        dr_btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dr_user_img = (ImageView) findViewById(R.id.dr_user_img);

        dr_user_name = (TextView) findViewById(R.id.dr_user_name);
        dr_type = (TextView) findViewById(R.id.dr_type);
        dr_date = (TextView) findViewById(R.id.dr_date);
        dr_review_text = (TextView) findViewById(R.id.dr_review_text);

        dr_ratingbar = (RatingBar) findViewById(R.id.dr_ratingbar);

        dr_image_list = (RecyclerView) findViewById(R.id.dr_image_list);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        dr_image_list.setLayoutManager(mLayoutManager);
        ReviewImageAdapter r_adapter = new ReviewImageAdapter(getApplicationContext());
        dr_image_list.setAdapter(r_adapter);
        decoration = new Product_Decoration();
        dr_image_list.addItemDecoration(decoration);

        dr_review_tag = (RecyclerView) findViewById(R.id.dr_review_tag);
        layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexDirection(FlexDirection.ROW);
        layoutManager.setJustifyContent(JustifyContent.FLEX_START);
        dr_review_tag.setLayoutManager(layoutManager);
        tag_adapter = new RecommendationSearchAdapter(getApplicationContext());
        dr_review_tag.setAdapter(tag_adapter);
    }
}