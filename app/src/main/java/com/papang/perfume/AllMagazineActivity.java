package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.papang.perfume.adapter.AllMagazineAdaper;

import java.util.ArrayList;

public class AllMagazineActivity extends AppCompatActivity {

    ImageButton back_btn;
    RecyclerView recycler_magazine;
    AllMagazineAdaper magazine_adpater;
    RecyclerView.LayoutManager mLayoutManager;


    ArrayList<String> m_text1;
    ArrayList<String> m_text2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_magazine);

        m_text1 = new ArrayList<>();
        m_text2 = new ArrayList<>();

        // 뒤로가기
        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // 임시
        m_text1.add("#Summer Vibe");
        m_text2.add("일상 속 휴가를 느낄 수 있는 여름 향수");

        // 매거진 리사이클러 뷰
        recycler_magazine = (RecyclerView)findViewById(R.id.recycler_magazine);
        magazine_adpater = new AllMagazineAdaper(getApplicationContext(), m_text1, m_text2);
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recycler_magazine.setLayoutManager(mLayoutManager);
        recycler_magazine.setAdapter(magazine_adpater);
    }
}