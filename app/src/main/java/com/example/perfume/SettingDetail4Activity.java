package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

// 1:1 문의
public class SettingDetail4Activity extends AppCompatActivity {

    private ImageButton btn_back;
    private ImageButton btn_goto_plusfriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_detail_4);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btn_goto_plusfriend = (ImageButton) findViewById(R.id.btn_goto_plusfriend);
        btn_goto_plusfriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("kakaoplus://plusfriend/home/_xhZJAs"));
                startActivity(i);
            }
        });
    }
}