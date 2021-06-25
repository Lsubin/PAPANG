package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;
import com.papang.perfume.data.User;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ResetPwActivity extends AppCompatActivity {

    DataService dataService;
    DataApi dataApi;

    ImageButton ok_btn;
    ImageButton btn_back;

    EditText password_edit;
    EditText password_check_edit;

    String email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset_pw);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        email = getIntent().getStringExtra("email");

        ok_btn = (ImageButton)findViewById(R.id.ok_btn);
        btn_back = (ImageButton)findViewById(R.id.btn_back);

        password_edit = (EditText)findViewById(R.id.password_edit);
        password_check_edit = (EditText)findViewById(R.id.password_check_edit);

        // 로그인 화면으로 이동
        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(password_edit.getText().toString().equals(password_check_edit.getText().toString())){
                    changePW();
                }
                else {
                    Toast.makeText(ResetPwActivity.this, "비밀번호와 비밀번호 확인이 다릅니다.", Toast.LENGTH_SHORT).show();
                    password_check_edit.requestFocus();
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

    public void changePW(){
        Map<String, String> map = new HashMap();
        map.put("password", password_edit.getText().toString());
        dataApi.resetPW(email, map).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                finish();
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }
}