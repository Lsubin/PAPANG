package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;

public class PhoneNumberActivity extends AppCompatActivity {

    ImageButton goto_certificattion_btn;

    // 타이머 관련
    String time = "0300";
    CountDownTimer countDownTimer;

    boolean state = false;

    String phone;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone_number);

        goto_certificattion_btn = (ImageButton)findViewById(R.id.goto_certificattion_btn);

        goto_certificattion_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent certification = new Intent(getApplicationContext(), AuthActivity.class);
                startActivity(certification);
                finish();
            }
        });
    }
}