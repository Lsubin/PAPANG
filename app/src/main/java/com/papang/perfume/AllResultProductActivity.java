package com.papang.perfume;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;


import com.papang.perfume.adapter.ResultProductAdpater;
import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;

import java.util.ArrayList;

public class AllResultProductActivity extends AppCompatActivity {

    DataService dataService;
    DataApi dataApi;
    ArrayList<String> perfumes;
    ArrayList<String> p_name;
    ArrayList<String> p_brand;
    ArrayList<String> p_id;

    ImageView flavor_img;
    ImageButton back_btn;
    ImageButton restart_btn;
    RecyclerView recycler_result;
    ResultProductAdpater adapter;
    TextView name_text;

    ArrayList<String> perfumeInfo;

    String flavor_1, flavor_2, flavor_3;
    String access;
    String nickname;

    TextView result_1_text, result_2_text, result_3_text, result_456_text, result_7_text;
    private boolean itemTouch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_result_product);

        name_text = (TextView)findViewById(R.id.name_text);
        checkLogin();

        perfumes = new ArrayList<>();
        perfumeInfo = new ArrayList<>();

        back_btn = (ImageButton) findViewById(R.id.back_btn);
        restart_btn = (ImageButton) findViewById(R.id.restart_btn);
        recycler_result = (RecyclerView) findViewById(R.id.recycler_result);

        result_2_text = (TextView) findViewById(R.id.result_2_text);
        result_3_text = (TextView) findViewById(R.id.result_3_text);
        result_456_text = (TextView) findViewById(R.id.result_456_text);
        //result_7_text = (TextView) findViewById(R.id.result_7_text);

        flavor_img = (ImageView) findViewById(R.id.flavor_img);

        // 테스트 결과 값 데이터 받기
        Intent secondIntent = getIntent();
        perfumes = (ArrayList<String>)secondIntent.getSerializableExtra("결과");
        perfumeInfo = secondIntent.getStringArrayListExtra("정보");

        // 테스트 결과 값에 따른 text 값 변경
        //changeResultText(q_1, q_2, q_3, q_4, q_5, q_6, q_7);
        changeResultText(perfumeInfo);
        setting();

        recycler_result.setLayoutManager(new LinearLayoutManager(this));

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        restart_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkLogin(){
        SharedPreferences sharedPreferences = getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        access = sharedPreferences.getString("Access","");
        nickname = sharedPreferences.getString("Nickname","");

        if(access.equals("Login"))
            name_text.setText(nickname);
        else
            name_text.setText("비회원");
    }

    private void setting(){
        p_id = new ArrayList<>();
        p_name = new ArrayList<>();
        p_brand = new ArrayList<>();

        for(int i = 0; i < perfumes.size(); i++){
            int index = perfumes.get(i).indexOf(",");
            int index2 = perfumes.get(i).indexOf(",", index+1);
            String id = perfumes.get(i).substring(0, index);
            String name = perfumes.get(i).substring(index+1, index2);
            String brand = perfumes.get(i).substring(index2+1);

            if(i != 0 && p_name.contains(name)){
                int position = p_name.indexOf(name);
                if(!p_brand.get(position).equals(brand)){
                    p_id.add(id);
                    p_name.add(name);
                    p_brand.add(brand);
                }
            }
            else {
                p_id.add(id);
                p_name.add(name);
                p_brand.add(brand);
            }
        }

        adapter = new ResultProductAdpater(getApplicationContext(), p_name, p_brand);
        recycler_result.setAdapter(adapter);

        recycler_result.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (MotionEvent.ACTION_UP == e.getAction() && itemTouch) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    String p_name = adapter.getName(rv.getChildAdapterPosition(reV));
                    Intent gotoDetail = new Intent(getApplicationContext(), ProductDetailsActivity.class);
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

        recycler_result.addOnScrollListener(new RecyclerView.OnScrollListener() {
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

    }

    private void changeResultText(ArrayList<String> perfumeInfos) { //가격은 String q_7 추가

        switch (perfumeInfos.get(0)) {
            case "1":
                result_3_text.setText("포근한, 차분한, 따뜻한, 순수한");
                break;
            case "2":
                result_3_text.setText("발랄한, 귀여운, 사랑스러운");
                break;
            case "3":
                result_3_text.setText("관능적인, 화려한");
                break;
            case "4":
                result_3_text.setText("환상적인, 황홀한, 몽환적인, 신비로운");
                break;
            case "5":
                result_3_text.setText("젠틀한, 클래식한, 깊이있는");
                break;
            case "6":
                result_3_text.setText("세련된, 우아한, 도시적인, 모던한");
                break;
            case "7":
                result_3_text.setText("산뜻한, 시원한, 활기찬");
                break;
            case "8":
                result_3_text.setText("강렬한, 파워풀한, 존재감있는, 대담한");
                break;
        }

        // 첫번째로 고른 향료 기준으로 이미지 변경!
        switch (perfumeInfos.get(1)) {
            case "1":
                flavor_1 = "Aldehyde";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_aldehyde_img));
                break;
            case "2":
                flavor_1 = "Animalic";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_animal_img));
                break;
            case "3":
                flavor_1 = "Aromatic";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_aromatic_img));
                break;
            case "4":
                flavor_1 = "Balsam";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_balsam_img));
                break;
            case "5":
                flavor_1 = "Chypre";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_chypre_img));
                break;
            case "6":
                flavor_1 = "Citrus";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_citrus_img));
                break;
            case "7":
                flavor_1 = "Green";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_green_img));
                break;
            case "8":
                flavor_1 = "Floral";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_floral_img));
                break;
            case "9":
                flavor_1 = "Fruity";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_fruity_img));
                break;
            case "10":
                flavor_1 = "Spicy";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_spicy_img));
                break;
            case "11":
                flavor_1 = "Woody";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_woody_img));
                break;
            case "12":
                flavor_1 = "Aquatic";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_aquatic_img));
                break;
            case "13":
                flavor_1 = "Nutty";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_nutty_img));
                break;
            case "14":
                flavor_1 = "Leather";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_leather_img));
                break;
            case "15":
                flavor_1 = "Smoky";
                flavor_img.setImageDrawable(getResources().getDrawable(R.mipmap.flavor_smoky_img));
                break;
            case "16":
                flavor_1 = "None";
                break;
        }

        switch (perfumeInfos.get(2)) {
            case "1":
                flavor_2 = "Aldehyde";
                break;
            case "2":
                flavor_2 = "Animalic";
                break;
            case "3":
                flavor_2 = "Aromatic";
                break;
            case "4":
                flavor_2 = "Balsam";
                break;
            case "5":
                flavor_2 = "Chypre";
                break;
            case "6":
                flavor_2 = "Citrus";
                break;
            case "7":
                flavor_2 = "Green";
                break;
            case "8":
                flavor_2 = "Floral";
                break;
            case "9":
                flavor_2 = "Fruity";
                break;
            case "10":
                flavor_2 = "Spicy";
                break;
            case "11":
                flavor_2 = "Woody";
                break;
            case "12":
                flavor_2 = "Aquatic";
                break;
            case "13":
                flavor_2 = "Nutty";
                break;
            case "14":
                flavor_2 = "Leather";
                break;
            case "15":
                flavor_2 = "Smoky";
                break;
            case "16":
                flavor_2 = "None";
                break;
        }

        switch (perfumeInfos.get(3)) {
            case "1":
                flavor_3 = "Aldehyde";
                break;
            case "2":
                flavor_3 = "Animalic";
                break;
            case "3":
                flavor_3 = "Aromatic";
                break;
            case "4":
                flavor_3 = "Balsam";
                break;
            case "5":
                flavor_3 = "Chypre";
                break;
            case "6":
                flavor_3 = "Citrus";
                break;
            case "7":
                flavor_3 = "Green";
                break;
            case "8":
                flavor_3 = "Floral";
                break;
            case "9":
                flavor_3 = "Fruity";
                break;
            case "10":
                flavor_3 = "Spicy";
                break;
            case "11":
                flavor_3 = "Woody";
                break;
            case "12":
                flavor_3 = "Aquatic";
                break;
            case "13":
                flavor_3 = "Nutty";
                break;
            case "14":
                flavor_3 = "Leather";
                break;
            case "15":
                flavor_2 = "Smoky";
                break;
            case "16":
                flavor_3 = "None";
                break;
        }

        switch (perfumeInfos.get(4)) {
            case "size1":
                result_2_text.setText("0ml ~ 50ml");
                break;
            case "size2":
                result_2_text.setText("0ml ~ 100ml");
                break;
            case "size3":
                result_2_text.setText("100ml 이상");
                break;
        }

        result_456_text.setText(flavor_1 + ", " + flavor_2 + ", " + flavor_3);

    }
}