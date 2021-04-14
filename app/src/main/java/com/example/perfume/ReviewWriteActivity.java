package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatRatingBar;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.adapter.FlavorAdapter;
import com.example.perfume.adapter.ReviewFlavorAdapter;
import com.example.perfume.adapter.ReviewHashAdapter;
import com.example.perfume.search.RecentSearchAdapter;
import com.example.perfume.search.RecommendationSearchAdapter;
import com.google.android.flexbox.FlexDirection;
import com.google.android.flexbox.FlexboxLayoutManager;
import com.google.android.flexbox.JustifyContent;

public class ReviewWriteActivity extends AppCompatActivity {

    ImageButton btn_review_type1;       // 시향 리뷰
    ImageButton btn_review_type2;       // 구매 & 사용 리뷰
    ImageButton btn_review_spring;      // 계절_봄
    ImageButton btn_review_summer;      // 계절_여름
    ImageButton btn_review_fall;        // 계절_가을
    ImageButton btn_review_winter;      // 계절_겨울
    ImageButton btn_rw_ok;              // 제출하기 버튼
    ImageButton btn_rw_exit;            // 닫기 버튼

    ImageView rw_image1;                // 사진등록_1
    ImageView rw_image2;                // 사진등록_2
    ImageView rw_image3;                // 사진등록_3

    EditText rw_text_review;            // 텍스트 리뷰
    EditText rw_tag_input;              // 해시태그 추가 박스

    TextView rw_rating_text;            // RatingBar 텍스트(Good, Not Bad 등)
    TextView rw_text_count;             // 텍스트 리뷰_글자 수
    TextView rw_product_name;           // 상품 이름

    AppCompatCheckBox rw_tag_checkbox;  // 태그 자동화 체크박스
    AppCompatRatingBar rw_ratingbar;    // RatingBar 향수 평점

    RecyclerView rw_hash_tag;           // 해시태그 리싸이클러뷰
    RecyclerView rw_flavor_grid;        // 향료 선택 그리드뷰

    FlexboxLayoutManager layoutManager;
    ReviewHashAdapter hashTag_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_write);

        btn_review_type1 = (ImageButton)findViewById(R.id.btn_review_type1);
        btn_review_type2 = (ImageButton)findViewById(R.id.btn_review_type2);
        btn_review_spring = (ImageButton)findViewById(R.id.btn_review_spring);
        btn_review_summer = (ImageButton)findViewById(R.id.btn_review_summer);
        btn_review_fall = (ImageButton)findViewById(R.id.btn_review_fall);
        btn_review_winter = (ImageButton)findViewById(R.id.btn_review_winter);
        btn_rw_ok = (ImageButton)findViewById(R.id.btn_rw_ok);
        btn_rw_exit = (ImageButton)findViewById(R.id.btn_rw_exit);

        rw_hash_tag = (RecyclerView)findViewById(R.id.rw_hash_tag);
        layoutManager = new FlexboxLayoutManager(getApplicationContext());
        layoutManager.setFlexDirection (FlexDirection.ROW);
        layoutManager.setJustifyContent (JustifyContent.FLEX_START);
        rw_hash_tag.setLayoutManager(layoutManager);
        hashTag_adapter = new ReviewHashAdapter(getApplicationContext());
        rw_hash_tag.setAdapter(hashTag_adapter);

        rw_flavor_grid = (RecyclerView)findViewById(R.id.rw_flavor_grid);

        rw_rating_text = (TextView)findViewById(R.id.rw_rating_text);
        rw_text_count = (TextView)findViewById(R.id.rw_text_count);
        rw_product_name = (TextView)findViewById(R.id.rw_product_name);

        rw_tag_checkbox = (AppCompatCheckBox)findViewById(R.id.rw_tag_checkbox);
        rw_ratingbar = (AppCompatRatingBar)findViewById(R.id.rw_ratingbar);
        rw_ratingbar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                switch ((int)rating){
                    case 1:
                        rw_rating_text.setText("Not My Style");
                        break;
                    case 2:
                        rw_rating_text.setText("Not Bad");
                        break;
                    case 3:
                        rw_rating_text.setText("Good");
                        break;
                    case 4:
                        rw_rating_text.setText("Cool");
                        break;
                    case 5:
                        rw_rating_text.setText("Perfect!");
                        break;

                }
            }
        });

        rw_image1 = (ImageView)findViewById(R.id.rw_image1);
        rw_image2 = (ImageView)findViewById(R.id.rw_image2);
        rw_image3 = (ImageView)findViewById(R.id.rw_image3);

        rw_text_review = (EditText)findViewById(R.id.rw_text_review);
        rw_tag_input = (EditText)findViewById(R.id.rw_tag_input);
        rw_tag_input.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if(keyCode == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
                    hashTag_adapter.saveHashtag(rw_tag_input.getText().toString());
                return true;
            }
        });

        // 텍스트 입력될때마다 글자수 TextView에 표시하기위한 리스너너
        rw_text_review.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = rw_text_review.getText().toString();
                rw_text_count.setText(String.valueOf(input.length()));
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 5);
        rw_flavor_grid.setLayoutManager(mLayoutManager);
        ReviewFlavorAdapter flavorAdapter = new ReviewFlavorAdapter(getApplicationContext());
        rw_flavor_grid.setAdapter(flavorAdapter);

        ImageButton.OnClickListener imgBtnClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.btn_review_type1:
                        btn_review_type1.setImageDrawable(getResources().getDrawable(R.mipmap.review_type1_click));
                        btn_review_type2.setImageDrawable(getResources().getDrawable(R.mipmap.review_type2));
                        break;
                    case R.id.btn_review_type2:
                        btn_review_type1.setImageDrawable(getResources().getDrawable(R.mipmap.review_type1));
                        btn_review_type2.setImageDrawable(getResources().getDrawable(R.mipmap.review_type2_click));
                        break;
                    case R.id.btn_review_spring:
                        btn_review_spring.setImageDrawable(getResources().getDrawable(R.mipmap.review_spring_click));
                        btn_review_summer.setImageDrawable(getResources().getDrawable(R.mipmap.review_summer));
                        btn_review_fall.setImageDrawable(getResources().getDrawable(R.mipmap.review_fall));
                        btn_review_winter.setImageDrawable(getResources().getDrawable(R.mipmap.review_winter));
                        break;
                    case R.id.btn_review_summer:
                        btn_review_spring.setImageDrawable(getResources().getDrawable(R.mipmap.review_spring));
                        btn_review_summer.setImageDrawable(getResources().getDrawable(R.mipmap.review_summer_click));
                        btn_review_fall.setImageDrawable(getResources().getDrawable(R.mipmap.review_fall));
                        btn_review_winter.setImageDrawable(getResources().getDrawable(R.mipmap.review_winter));
                        break;
                    case R.id.btn_review_fall:
                        btn_review_spring.setImageDrawable(getResources().getDrawable(R.mipmap.review_spring));
                        btn_review_summer.setImageDrawable(getResources().getDrawable(R.mipmap.review_summer));
                        btn_review_fall.setImageDrawable(getResources().getDrawable(R.mipmap.review_fall_click));
                        btn_review_winter.setImageDrawable(getResources().getDrawable(R.mipmap.review_winter));
                        break;
                    case R.id.btn_review_winter:
                        btn_review_spring.setImageDrawable(getResources().getDrawable(R.mipmap.review_spring));
                        btn_review_summer.setImageDrawable(getResources().getDrawable(R.mipmap.review_summer));
                        btn_review_fall.setImageDrawable(getResources().getDrawable(R.mipmap.review_fall));
                        btn_review_winter.setImageDrawable(getResources().getDrawable(R.mipmap.review_winter_click));
                        break;
                }
            }
        };
        btn_review_type1.setOnClickListener(imgBtnClickListener);
        btn_review_type2.setOnClickListener(imgBtnClickListener);
        btn_review_spring.setOnClickListener(imgBtnClickListener);
        btn_review_summer.setOnClickListener(imgBtnClickListener);
        btn_review_fall.setOnClickListener(imgBtnClickListener);
        btn_review_winter.setOnClickListener(imgBtnClickListener);

    }

    // 권한 요청
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        Log.d("권한", "onRequestPermissionsResult");
        if (grantResults[0] == PackageManager.PERMISSION_GRANTED && grantResults[1] == PackageManager.PERMISSION_GRANTED )
        {
            Log.d("권한", "Permission: " + permissions[0] + "was " + grantResults[0]);
        }
    }


    // 권한 체크 및 요청 함수
    public void permissionCheck(){
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M)
        {
            if(checkSelfPermission(Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
                    && checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED)
            {
                Log.d("권한", "설정 완료");
            }
            else
            {
                Log.d("권한", "설정 요청");
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1);
            }
        }
    }
}