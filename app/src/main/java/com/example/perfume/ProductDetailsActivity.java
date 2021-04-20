package com.example.perfume;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.perfume.adapter.PerfumeSizeAdapter;
import com.example.perfume.adapter.ReviewListAdapter;
import com.example.perfume.adapter.ReviewTabAdapter;
import com.example.perfume.custom.WrapHeightViewPager;
import com.example.perfume.main.home.Product_Decoration;
import com.example.perfume.main.home.Product_RecyclerView_Adapter;
import com.example.perfume.review.AllReviewActivity;
import com.example.perfume.review.ReviewWriteActivity;
import com.google.android.material.tabs.TabLayout;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

import java.util.ArrayList;

import iammert.com.expandablelib.ExpandableLayout;

public class ProductDetailsActivity extends AppCompatActivity {

    Toolbar toolbar;
    ActionBar actionBar;

    ImageView product_image;            // 상품 사진
    ImageView detail_note_1;            // 향수 노트 1
    ImageView detail_note_2;            // 향수 노트 2
    ImageView detail_note_3;            // 향수 노트 3

    TextView detail_shop_name;          // 쇼핑몰 이름
    TextView detail_product_name;       // 상품 이름
    TextView detail_size;               // 용량 text

    ImageButton btn_detail_wish;        // 찜 버튼
    ImageButton btn_detail_share;       // 공유 버튼
    ImageButton btn_size;               // 사이즈 변경(업 패널)
    ImageButton btn_shop;               // 웹 뷰 호출(쇼핑몰)
    ImageButton btn_go_review;          // 리뷰쓰러가기
    ImageButton btn_more_review;        // 리뷰 전체 보기

    SlidingUpPanelLayout product_slidinglayout;     // 슬라이딩 업 패널
    RecyclerView detail_size_item;                  // 용량 리싸이클러뷰
    RecyclerView detail_product_tag;                // 향수 관련 태그
    RecyclerView detail_similar_product;            // 비슷한 향수

    RecyclerView pd_review_list;                    // 리뷰 목록

    TabLayout review_tab;
    WrapHeightViewPager review_viewpager;
    ReviewTabAdapter rtAdapter;

    iammert.com.expandablelib.ExpandableLayout detail_price_item;           // 최저가 비교하기(확장 리스트뷰)

    ArrayList<String> sizes;
    ArrayList<String> tags;

    LinearLayoutManager mLayoutManager;
    PerfumeSizeAdapter adapter;
    Product_Decoration decoration;
    ReviewListAdapter  radapter;

    // 뒤로가기 버튼
    @Override
    public boolean onOptionsItemSelected(MenuItem item ){
        switch(item.getItemId()){
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        decoration = new Product_Decoration();

        sizes = new ArrayList<>();
        sizes.add("35ml");
        sizes.add("50ml");
        sizes.add("100ml");

        tags = new ArrayList<>();
        tags.add("# 달콤함");
        tags.add("# 신선함");
        tags.add("# 우아함");
        tags.add("# 부드러움");

        product_slidinglayout = (SlidingUpPanelLayout)findViewById(R.id.product_slidinglayout);
        detail_size_item = (RecyclerView)findViewById(R.id.detail_size_item);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        detail_size_item.setLayoutManager(mLayoutManager);
        adapter = new PerfumeSizeAdapter(getApplicationContext(), sizes);
        detail_size_item.setAdapter(adapter);

        detail_product_tag = (RecyclerView)findViewById(R.id.detail_product_tag);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        detail_product_tag.setLayoutManager(mLayoutManager);
        adapter = new PerfumeSizeAdapter(getApplicationContext(), tags);
        detail_product_tag.setAdapter(adapter);
        detail_product_tag.addItemDecoration(decoration);

        pd_review_list = (RecyclerView)findViewById(R.id.pd_review_list);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        pd_review_list.setLayoutManager(mLayoutManager);
        radapter = new ReviewListAdapter(getApplicationContext(), "김실버");
        pd_review_list.setAdapter(radapter);

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

        detail_similar_product = (RecyclerView)findViewById(R.id.detail_similar_product);
        mLayoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false);
        detail_similar_product.setLayoutManager(mLayoutManager);
        Product_RecyclerView_Adapter p_adapter = new Product_RecyclerView_Adapter(getApplicationContext(), data1, data2, data3);
        detail_similar_product.setAdapter(p_adapter);
        detail_similar_product.addItemDecoration(decoration);

        btn_size = (ImageButton)findViewById(R.id.btn_size);
        btn_size.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                product_slidinglayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        detail_price_item = (ExpandableLayout) findViewById(R.id.detail_price_item);

        btn_go_review = (ImageButton)findViewById(R.id.btn_go_review);
        btn_go_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent review = new Intent(getApplicationContext(), ReviewWriteActivity.class);
                startActivity(review);
            }
        });

        review_tab = (TabLayout)findViewById(R.id.review_tab);
        review_viewpager = (WrapHeightViewPager)findViewById(R.id.review_viewpager);
        rtAdapter = new ReviewTabAdapter(getSupportFragmentManager());
        review_viewpager.setAdapter(rtAdapter);

        review_tab.setupWithViewPager(review_viewpager);    // 리뷰 뷰페이저랑 탭 아이템이랑 연동

        btn_more_review = (ImageButton)findViewById(R.id.btn_more_review);
        btn_more_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent allReview = new Intent(getApplicationContext(), AllReviewActivity.class);
                startActivity(allReview);
            }
        });
    }


}