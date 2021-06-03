package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.User;

import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    Button findID_btn;
    Button findPW_btn;
    ImageButton exit_btn;
    ImageButton login_btn;

    EditText email_edit;
    EditText password_edit;

    Pattern pattern;        // 이메일 패턴

    DataService dataService;
    DataApi dataApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        findID_btn = (Button)findViewById(R.id.findID_btn);
        findPW_btn = (Button)findViewById(R.id.findPW_btn);
        exit_btn = (ImageButton)findViewById(R.id.exit_btn);
        login_btn = (ImageButton)findViewById(R.id.login_btn);

        email_edit = (EditText)findViewById(R.id.email_edit);
        password_edit = (EditText)findViewById(R.id.password_edit);

        pattern = Patterns.EMAIL_ADDRESS;       // 이메일 패턴

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!pattern.matcher(email_edit.getText().toString()).matches())
                    Toast.makeText(getApplicationContext(), "이메일을 다시 입력해주세요", 0).show();
                if(email_edit.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "이메일을 입력해주세요.", 0).show();
                    email_edit.requestFocus();
                }
                if(password_edit.getText().toString().equals("")){
                    Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", 0).show();
                    password_edit.requestFocus();
                }
                else
                    checkLogin(email_edit.getText().toString(), password_edit.getText().toString());
            }
        });

        findID_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindIdActivity.class);
                startActivity(intent);
            }
        });

        findPW_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), FindPwActivity.class);
                startActivity(intent);
            }
        });

        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void checkLogin(String email, final String password){
        dataApi.getUser(email).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                User user = response.body();
                if(password.equals(user.getPassword())){
                    SharedPreferences sharedPreferences= getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정
                    SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("Access", "Login"); // key,value 형식으로 저장
                    editor.putString("Email", email_edit.getText().toString()); // key,value 형식으로 저장
                    editor.putString("Nickname", user.getNickname()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    finish();
                    ((MainActivity)MainActivity.mContext).refresh();
                }
                else {
                    Toast.makeText(getApplicationContext(), "비밀번호를 확인해주세요", Toast.LENGTH_SHORT).show();
                    password_edit.setText("");
                    password_edit.requestFocus();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "존재하지 않는 이메일입니다.", Toast.LENGTH_SHORT).show();
                email_edit.setText("");
                email_edit.requestFocus();
            }
        });
    }
}