package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import java.util.ArrayList;

public class AllResultProductActivity extends AppCompatActivity {

    ImageButton back_btn;
    ImageButton restart_btn;
    RecyclerView recycler_result;
    ResultProductAdpater adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_result_product);

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        restart_btn = (ImageButton)findViewById(R.id.restart_btn);
        recycler_result = (RecyclerView)findViewById(R.id.recycler_result);

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
}