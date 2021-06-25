package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class NoResultActivity extends AppCompatActivity {

    TextView text4;

    ImageButton retry_btn;
    ImageButton back_btn;

    TextView name_text;
    String access;
    String nickname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_result);

        text4 = (TextView)findViewById(R.id.text4);
        name_text = (TextView)findViewById(R.id.name_text);
        checkLogin();

        retry_btn = (ImageButton)findViewById(R.id.retry_btn);
        retry_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });

        back_btn = (ImageButton)findViewById(R.id.back_btn);
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Spannable span = (Spannable) text4.getText();
        span.setSpan(new ForegroundColorSpan(Color.parseColor("#6557FF")), 7, 16, Spanned.SPAN_EXCLUSIVE_INCLUSIVE);


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
}