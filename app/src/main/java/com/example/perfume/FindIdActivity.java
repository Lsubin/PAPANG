package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.telephony.SmsManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class FindIdActivity extends AppCompatActivity {

    ImageButton btn_back;

    EditText phone_num_edit;
    EditText password_edit;
    ImageButton recieve_btn;
    ImageButton next_btn;
    TextView auth_num_text;
    ImageView check_image;
    TextView timer_text;

    // 타이머 관련
    String time = "0300";
    CountDownTimer countDownTimer;

    boolean state = false;

    String phone;
    String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_id);

        btn_back = (ImageButton)findViewById(R.id.btn_back);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        phone_num_edit = (EditText)findViewById(R.id.phone_num_edit);
        password_edit = (EditText)findViewById(R.id.password_edit);
        recieve_btn = (ImageButton)findViewById(R.id.recieve_btn);
        auth_num_text = (TextView)findViewById(R.id.auth_num_text);
        check_image = (ImageView)findViewById(R.id.check_image);
        next_btn = (ImageButton)findViewById(R.id.next_btn);
        timer_text = (TextView)findViewById(R.id.timer_text);

        recieve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = phone_num_edit.getText().toString();
                if(phone.length() < 10)
                    Toast.makeText(getApplicationContext(), "전화번호를 확인해주세요", 0).show();
                else {

                    // 타이머 셋팅
                    CountDownTimer();

                    message = String.valueOf(numberGen(4,2));

                    if (ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.SEND_SMS)
                            != PackageManager.PERMISSION_GRANTED) {
                        if (ActivityCompat.shouldShowRequestPermissionRationale(FindIdActivity.this,
                                Manifest.permission.SEND_SMS)) {
                        } else {
                            ActivityCompat.requestPermissions(FindIdActivity.this,
                                    new String[]{Manifest.permission.SEND_SMS},
                                    1004);
                        }
                    }
                    else if(ContextCompat.checkSelfPermission(getApplicationContext(),
                            Manifest.permission.SEND_SMS)
                            == PackageManager.PERMISSION_GRANTED)
                        sendSMS(phone, message);
                }
            }
        });

        next_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(next_btn.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.next_btn).getConstantState())) {
                    Toast.makeText(getApplicationContext(), "들어옴", Toast.LENGTH_SHORT).show();
                    Intent found  = new Intent(FindIdActivity.this, FoundEmailActivity.class);
                    found.putExtra("번호", phone);
                    startActivity(found);
                    finish();
                }
                else
                    Toast.makeText(getApplicationContext(), next_btn.getDrawable().getConstantState().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        password_edit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(password_edit.getText().toString().equals(message)) {
                    check_image.setImageDrawable(getResources().getDrawable(R.mipmap.done_btn));
                    next_btn.setImageDrawable(getResources().getDrawable(R.mipmap.next_btn));
                }
                else{
                    check_image.setImageDrawable(getResources().getDrawable(R.mipmap.not_btn));
                    next_btn.setImageDrawable(getResources().getDrawable(R.mipmap.unnext_btn));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    public void changeImage(boolean state){
        if(state == false)
        {
            recieve_btn.setImageDrawable(getApplicationContext().getResources().getDrawable(R.mipmap.retry_btn));
            password_edit.setVisibility(View.VISIBLE);
            auth_num_text.setVisibility(View.VISIBLE);
            check_image.setVisibility(View.VISIBLE);
            timer_text.setVisibility(View.VISIBLE);
            state = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1004: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    SmsManager smsManager = SmsManager.getDefault();
                    smsManager.sendTextMessage(phone, null, "[PAPANG]본인인증 인증번호 : " + message, null, null);
                    changeImage(state);
                    Toast.makeText(getApplicationContext(), "인증번호가 전송되었습니다.",
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(),
                            "SMS faild, please try again.", Toast.LENGTH_LONG).show();
                    return;
                }
            }
        }
    }

    private void sendSMS(String phoneNumber, String message)
    {
        SmsManager smsManager = SmsManager.getDefault();
        smsManager.sendTextMessage(phone, null, "[PAPANG]본인인증 인증번호 : " + message, null, null);
        changeImage(state);
        Toast.makeText(getApplicationContext(), "인증번호가 전송되었습니다.", Toast.LENGTH_LONG).show();
    }

    public static String numberGen(int len, int dupCd ) {

        Random rand = new Random();
        String numStr = ""; //난수가 저장될 변수

        for(int i=0;i<len;i++) {

            //0~9 까지 난수 생성
            String ran = Integer.toString(rand.nextInt(10));

            if(dupCd==1) {
                //중복 허용시 numStr에 append
                numStr += ran;
            }else if(dupCd==2) {
                //중복을 허용하지 않을시 중복된 값이 있는지 검사한다
                if(!numStr.contains(ran)) {
                    //중복된 값이 없으면 numStr에 append
                    numStr += ran;
                }else {
                    //생성된 난수가 중복되면 루틴을 다시 실행한다
                    i-=1;
                }
            }
        }
        return numStr;
    }

    public void CountDownTimer() {
        long conversionTime = 0;

        // 1000 단위가 1초
        // 60000 단위가 1분

        String getMin = time.substring(1,2);
        String getSecond = time.substring(2,4);

        conversionTime = Long.valueOf(getMin) * 60 * 1000 + Long.valueOf(getSecond) * 1000;

        countDownTimer = new CountDownTimer(conversionTime, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                // 분단위
                long getMin = millisUntilFinished - (millisUntilFinished / (60 * 60 * 1000)) ;
                String min = String.valueOf(getMin / (60 * 1000)); // 몫

                // 초단위
                String second = String.valueOf((getMin % (60 * 1000)) / 1000); // 나머지

                // 초가 한자리면 0을 붙인다
                if (second.length() == 1) {
                    second = "0" + second;
                }
                timer_text.setText(min + ":" + second);
            }

            @Override
            public void onFinish() {
                timer_text.setText("0:00");
                message = null; // 번호 초기화
                password_edit.setText("");
                Toast.makeText(getApplicationContext(), "인증번호가 만료되었습니다. 인증번호 재전송을 눌러주세요.", Toast.LENGTH_LONG).show();
            }
        }.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try{
            countDownTimer.cancel();
        } catch (Exception e) {}
        countDownTimer = null;
    }
}