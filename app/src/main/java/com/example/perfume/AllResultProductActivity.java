package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;


import com.example.perfume.adapter.ResultProductAdpater;

import java.util.ArrayList;

public class AllResultProductActivity extends AppCompatActivity {

    ImageButton back_btn;
    ImageButton restart_btn;
    RecyclerView recycler_result;
    ResultProductAdpater adapter;

    String q_1, q_2, q_3, q_4, q_5, q_6, q_7;
    String flavor_1, flavor_2, flavor_3;

    TextView result_1_text, result_2_text, result_3_text, result_456_text, result_7_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_result_product);

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        restart_btn = (ImageButton)findViewById(R.id.restart_btn);
        recycler_result = (RecyclerView)findViewById(R.id.recycler_result);

        result_1_text = (TextView)findViewById(R.id.result_1_text);
        result_2_text = (TextView)findViewById(R.id.result_2_text);
        result_3_text = (TextView)findViewById(R.id.result_3_text);
        result_456_text = (TextView)findViewById(R.id.result_456_text);
        result_7_text = (TextView)findViewById(R.id.result_7_text);

        // 테스트 결과 값 데이터 받기
        Intent secondIntent = getIntent();
        q_1 = secondIntent.getStringExtra("1");
        q_2 = secondIntent.getStringExtra("2");
        q_3 = secondIntent.getStringExtra("3");
        q_4 = secondIntent.getStringExtra("4");
        q_5 = secondIntent.getStringExtra("5");
        q_6 = secondIntent.getStringExtra("6");
        q_7 = secondIntent.getStringExtra("7");

        // 테스트 결과 값에 따른 text 값 변경
        changeResultText(q_1, q_2, q_3, q_4, q_5, q_6, q_7);

        // 예시
        ArrayList<Integer> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<String> data3 = new ArrayList<>();
        ArrayList<Integer> data4 = new ArrayList<>();

        // 예시로 8개 추가
        for(int i = 0; i < 6; i++)
        {
            data1.add(R.mipmap.result_produc_img);
            data2.add("CHANEL");
            data3.add("넘버5 오드빠르펭");
            data4.add(R.mipmap.icon_unfull_heart);
        }

        adapter = new ResultProductAdpater(this, data1, data2, data3, data4);
        recycler_result.setLayoutManager(new LinearLayoutManager(this));
        recycler_result.setAdapter(adapter);

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

    private void changeResultText(String q_1, String q_2, String q_3, String q_4, String q_5, String q_6, String q_7) {
        switch (q_1){
            case "ode_c":
                result_1_text.setText("오데 코롱");
                break;
            case "ode_d":
                result_1_text.setText("오드 뚜왈렛");
                break;
            case "ode_p":
                result_1_text.setText("오데 퍼퓸");
                break;
            case "ode_pp":
                result_1_text.setText("퍼퓸");
                break;
        }

        switch (q_2){
            case "size1":
                result_2_text.setText("0ml ~ 15ml");
                break;
            case "size2":
                result_2_text.setText("15ml ~ 30ml");
                break;
            case "size3":
                result_2_text.setText("30ml ~ 50ml");
                break;
            case "size4":
                result_2_text.setText("50ml ~ 75ml");
                break;
            case "size5":
                result_2_text.setText("75ml ~ 100ml");
                break;
            case "size6":
                result_2_text.setText("100ml 이상");
                break;
        }

        switch (q_3){
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

        switch (q_4){
            case "1":
                flavor_1 = "Aldehyde";
                break;
            case "2":
                flavor_1 = "Animalic";
                break;
            case "3":
                flavor_1 = "Aromatic";
                break;
            case "4":
                flavor_1 = "Balsam";
                break;
            case "5":
                flavor_1 = "Chypre";
                break;
            case "6":
                flavor_1 = "Citrus";
                break;
            case "7":
                flavor_1 = "Green";
                break;
            case "8":
                flavor_1 = "Floral";
                break;
            case "9":
                flavor_1 = "Fruity";
                break;
            case "10":
                flavor_1 = "Spicy";
                break;
            case "11":
                flavor_1 = "Woody";
                break;
            case "12":
                flavor_1 = "None";
                break;
        }

        switch (q_5){
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
                flavor_2 = "None";
                break;
        }

        switch (q_6){
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
                flavor_3 = "None";
                break;
        }

        result_456_text.setText(flavor_1 + ", " + flavor_2 + ", " + flavor_3);

        // 가격은 아직 못받아옴!

    }
}