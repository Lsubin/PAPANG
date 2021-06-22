package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.UnderlineSpan;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.custom.BottomSheetDialog;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.User;
import com.example.perfume.main.my.MyFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JoinActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    DataService dataService;
    DataApi dataApi;
    public static Context mContext;

    EditText name_edit, nickname_edit, email_edit, password_edit, address_edit, password_checked_edit, birthday_edit, gender_edit;
    Switch agree_switch;
    TextView TC1_text, TC2_text;
    ImageButton check_btn, start_btn;
    ImageView email_check_image, password_check_image;
    Boolean email_state = false;
    Boolean password_state = false;
    Boolean tc1_checked, tc2_checked;

    Pattern pattern;        // 이메일 패턴

    String phone;
    BottomSheetDialog bottomSheetDialog;

    private ArrayAdapter<String> spinner_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);
        mContext = this;

        phone = getIntent().getStringExtra("번호");       // 전화번호 받아서 저장
        Toast.makeText(getApplicationContext(), phone, Toast.LENGTH_SHORT).show();
        name_edit = (EditText)findViewById(R.id.name_edit);                         // 이름
        nickname_edit = (EditText)findViewById(R.id.nickname_edit);                 // 닉네임
        email_edit = (EditText)findViewById(R.id.email_edit);                       // 이메일
        password_edit = (EditText)findViewById(R.id.password_edit);                 // 비밀번호
        password_checked_edit = (EditText)findViewById(R.id.password_checked_edit); // 비밀번호 확인
        birthday_edit = (EditText)findViewById(R.id.birthday_edit);                 // 생년월일
        address_edit = (EditText)findViewById(R.id.address_edit);

        gender_edit = (EditText) findViewById(R.id.gender_edit);                // 성별
        agree_switch = (Switch)findViewById(R.id.agree_switch);                     // 마케팅 수신 동의
        TC1_text = (TextView)findViewById(R.id.TC1_text);                           // 서비스 이용약관
        TC2_text = (TextView)findViewById(R.id.TC2_text);                           // 개인 정보 수집 및 이용

        check_btn = (ImageButton)findViewById(R.id.check_btn);
        start_btn = (ImageButton)findViewById(R.id.start_btn);

        email_check_image = (ImageView)findViewById(R.id.email_check_image);        // 이메일 체크 버튼
        password_check_image = (ImageView)findViewById(R.id.password_check_image);        // 비밀번호 체크 버튼

        pattern = Patterns.EMAIL_ADDRESS;       // 이메일 패턴

        // 이메일 입력 형식 맞는지 체크
        email_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(!pattern.matcher(email_edit.getText().toString()).matches()){
                    email_check_image.setVisibility(View.VISIBLE);
                    check_btn.setImageDrawable(getResources().getDrawable(R.mipmap.uncheck_btn));
                }
                else if(pattern.matcher(email_edit.getText().toString()).matches()){
                    check_btn.setImageDrawable(getResources().getDrawable(R.mipmap.checked_btn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(check_btn.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.checked_btn).getConstantState())) {
                    dataApi.getUser(email_edit.getText().toString()).enqueue(new Callback<User>() {
                        @Override
                        public void onResponse(Call<User> call, Response<User> response) {      // 이메일 존재할 때
                            Toast.makeText(getApplicationContext(), "중복된 이메일 입니다.", 0).show();
                        }

                        @Override
                        public void onFailure(Call<User> call, Throwable t) {       // 이메일 존재하지 않을 때
                            Toast.makeText(getApplicationContext(), "사용가능한 이메일 입니다.", 0).show();
                            email_check_image.setImageDrawable(getResources().getDrawable(R.mipmap.done_btn));
                            email_state = true;
                        }
                    });
                }
            }
        });

        password_checked_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                password_check_image.setVisibility(View.VISIBLE);
                if(password_checked_edit.getText().toString().equals(password_edit.getText().toString())){
                    password_check_image.setImageDrawable(getResources().getDrawable(R.mipmap.done_btn));
                    password_state = true;
                }
                else {
                    password_check_image.setImageDrawable(getResources().getDrawable(R.mipmap.not_btn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        address_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    Intent address = new Intent(JoinActivity.this, AddressViewActivity.class);
                    startActivityForResult(address, SEARCH_ADDRESS_ACTIVITY);
                }
                return false;
            }
        });

        gender_edit.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    bottomSheetDialog = BottomSheetDialog.getInstance();
                    bottomSheetDialog.show(getSupportFragmentManager(),"bottomSheet");
                }
                return false;
            }
        });

        // 마케팅 수신 동의
        agree_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                //Toast.makeText(getApplicationContext(), "체크상태 = " + isChecked, Toast.LENGTH_SHORT).show();

            }
        });

        // 서비스 이용약관, 개인정보 수집 및 이용
        SpannableString content1 = new SpannableString("서비스 이용약관, ");
        content1.setSpan(new UnderlineSpan(), 0 , content1.length()-2, 0);
        TC1_text.setText(content1);

        tc1_checked = false;
        tc2_checked = false;

        TC1_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingDetail2Activity.class);
                startActivity(intent);
                tc1_checked = true;
            }
        });

        SpannableString content2 = new SpannableString("개인정보 수집 및 이용 ");
        content2.setSpan(new UnderlineSpan(), 0 , content2.length(), 0);
        TC2_text.setText(content2);

        TC2_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), SettingDetail3Activity.class);
                startActivity(intent);
                tc2_checked = true;
            }
        });


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkJoin();
            }
        });
    }


    public void checkJoin(){
        if(name_edit.getText().toString().equals("")) {
            Toast.makeText(getApplicationContext(), "이름을 입력해주세요.", 0).show();
            name_edit.requestFocus();
        }
        else if(nickname_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "닉네임을 입력해주세요.", 0).show();
            nickname_edit.requestFocus();
        }
        else if(email_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "이메일을 입력해주세요.", 0).show();
            email_edit.requestFocus();
        }
        else if(password_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "비밀번호를 입력해주세요.", 0).show();
            password_edit.requestFocus();
        }
        else if(password_checked_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "비밀번호 확인을 입력해주세요.", 0).show();
            password_checked_edit.requestFocus();
        }
        else if(email_state.equals(false)){
            Toast.makeText(getApplicationContext(), "이메일 중복 확인을 해주세요.", 0).show();
            email_edit.requestFocus();
        }
        else if(password_state.equals(false)){
            Toast.makeText(getApplicationContext(), "비밀번호 확인을 다시 입력해주세요.", 0).show();
            password_checked_edit.requestFocus();
        }
        else if(gender_edit.getText().toString().equals("") || gender_edit.getText().toString().equals("선택안함")){
            Toast.makeText(getApplicationContext(), "성별을 입력해주세요.", 0).show();
            gender_edit.requestFocus();
        }
        else if(birthday_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "생년월일을 입력해주세요.", 0).show();
            birthday_edit.requestFocus();
        }
        else if(birthday_edit.getText().toString().length() > 0 && birthday_edit.getText().toString().length() != 8){
            Toast.makeText(getApplicationContext(), "올바른 형식으로 입력해주세요. ex)19980826", 0).show();
            birthday_edit.requestFocus();
        }
        else if(address_edit.getText().toString().equals("")){
            Toast.makeText(getApplicationContext(), "주소를 입력해주세요.", 0).show();
            address_edit.requestFocus();
        }
        else if(tc1_checked == false || tc2_checked == false){
            Toast.makeText(getApplicationContext(), "서비스 이용약관, 개인정보 수집 및 이용에 동의해주세요.", 0).show();
        }
        else{
            Map<String, String> user = new HashMap<>();
            user.put("email", email_edit.getText().toString());
            user.put("password", password_edit.getText().toString());
            user.put("name", name_edit.getText().toString());
            user.put("nickname", nickname_edit.getText().toString());
            user.put("gender", gender_edit.getText().toString());
            user.put("birth", birthday_edit.getText().toString());
            user.put("address", address_edit.getText().toString());
            user.put("access", "PAPANG");
            user.put("phone", phone);

            dataApi.joinUser(user).enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    SharedPreferences sharedPreferences= getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정
                    SharedPreferences.Editor editor= sharedPreferences.edit(); //sharedPreferences를 제어할 editor를 선언
                    editor.putString("Access", "Login"); // key,value 형식으로 저장
                    editor.putString("Email", email_edit.getText().toString()); // key,value 형식으로 저장
                    editor.putString("Nickname", nickname_edit.getText().toString()); // key,value 형식으로 저장
                    editor.commit();    //최종 커밋. 커밋을 해야 저장이 된다.
                    Toast.makeText(getApplicationContext(), "회원가입 완료", Toast.LENGTH_SHORT).show();
                    finish();
                }

                @Override
                public void onFailure(Call<User> call, Throwable t) {

                }
            });
        }
    }

    public void setGender(String gender){
        gender_edit.setText(bottomSheetDialog.gender);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent intent)
    {
        super.onActivityResult(requestCode, resultCode, intent);
        switch (requestCode) {
            case SEARCH_ADDRESS_ACTIVITY:
                if (resultCode == RESULT_OK) {
                    String data = intent.getExtras().getString("data");
                    if (data != null) {
                        address_edit.setText(data);
                    }
                }
                break;
        }
    }
}