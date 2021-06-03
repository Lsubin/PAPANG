package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FoundEmailActivity extends AppCompatActivity {

    ImageButton btn_back;
    ImageButton goto_login_btn;
    ImageButton find_pw_btn;

    TextView email_text;
    TextView text4;
    TextView text5;

    String phone;

    DataService dataService;
    DataApi dataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_email);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        phone = getIntent().getStringExtra("번호");

        btn_back = (ImageButton)findViewById(R.id.btn_back);
        goto_login_btn = (ImageButton)findViewById(R.id.goto_login_btn);
        find_pw_btn = (ImageButton)findViewById(R.id.find_pw_btn);

        email_text = (TextView)findViewById(R.id.email_text);
        text4 = (TextView)findViewById(R.id.text4);
        text5 = (TextView)findViewById(R.id.text5);

        dataApi.getEmail(phone).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                email_text.setText(user.getEmail());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                email_text.setVisibility(View.INVISIBLE);
                goto_login_btn.setVisibility(View.INVISIBLE);
                find_pw_btn.setImageDrawable(getResources().getDrawable(R.mipmap.goto_join_btn));
                text4.setText("입력하신 정보와 일치하는 이메일이 없습니다.");
                text5.setText("회원 가입 후, 파팡을 이용해주세요.");
            }
        });

        goto_login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent login = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(login);
                finish();
            }
        });

        find_pw_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(find_pw_btn.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.find_pw_btn).getConstantState())){
                    Intent find_pw = new Intent(getApplicationContext(), FindPwActivity.class);
                    startActivity(find_pw);
                    finish();
                }
                else if(find_pw_btn.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.goto_join_btn).getConstantState())){
                    Intent join = new Intent(getApplicationContext(), JoinActivity.class);
                    startActivity(join);
                    finish();
                }
            }
        });

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}