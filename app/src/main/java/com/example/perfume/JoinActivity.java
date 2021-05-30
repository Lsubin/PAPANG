package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
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
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class JoinActivity extends AppCompatActivity {
    private static final int SEARCH_ADDRESS_ACTIVITY = 10000;

    EditText name_edit, nickname_edit, email_edit, password_edit, address_edit, password_checked_edit, birthday_edit;
    Spinner gender_spinner;
    Switch agree_switch;
    TextView TC1_text, TC2_text;
    ImageButton check_btn, start_btn;

    Boolean tc1_checked, tc2_checked;

    private ArrayAdapter<String> spinner_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        name_edit = (EditText)findViewById(R.id.name_edit);                         // 이름
        nickname_edit = (EditText)findViewById(R.id.nickname_edit);                 // 닉네임
        email_edit = (EditText)findViewById(R.id.email_edit);                       // 이메일
        password_edit = (EditText)findViewById(R.id.password_edit);                 // 비밀번호
        password_checked_edit = (EditText)findViewById(R.id.password_checked_edit); // 비밀번호 확인
        birthday_edit = (EditText)findViewById(R.id.birthday_edit);                 // 생년월일
        address_edit = (EditText)findViewById(R.id.address_edit);

        gender_spinner = (Spinner)findViewById(R.id.gender_spinner);                // 성별
        agree_switch = (Switch)findViewById(R.id.agree_switch);                     // 마케팅 수신 동의
        TC1_text = (TextView)findViewById(R.id.TC1_text);                           // 서비스 이용약관
        TC2_text = (TextView)findViewById(R.id.TC2_text);                           // 개인 정보 수집 및 이용

        check_btn = (ImageButton)findViewById(R.id.check_btn);
        start_btn = (ImageButton)findViewById(R.id.start_btn);

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


        String[] items = {"남", "여", "성별을 선택해주세요."};
        spinner_adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getAssets(), "font/kopubworlddotumedium.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextSize(14);

                if (position == getCount()) {
                    ((TextView) v).setText("");
                    ((TextView) v).setHint(getItem(getCount()));
                    ((TextView) v).setHintTextColor(Color.parseColor("#A4A4A4"));
                    ((TextView) v).setTextSize(14);
                }
                return v;
            }

            @Override
            public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getDropDownView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getAssets(), "font/kopubworlddotumedium.ttf");
                ((TextView) v).setTypeface(externalFont);
                v.setBackgroundColor(Color.WHITE);
                ((TextView) v).setTextColor(Color.parseColor("#000000"));
                return v;
            }

            @Override
            public int getCount() {
                return super.getCount() - 1;
            }
        };

        spinner_adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        gender_spinner.setAdapter(spinner_adapter);
        gender_spinner.setSelection(spinner_adapter.getCount());


        // 성별 선택
        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Typeface externalFont = Typeface.createFromAsset(getAssets(), "font/kopubworlddotumedium.ttf");
                ((TextView) view).setTypeface(externalFont);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#000000"));
                Toast.makeText(getApplicationContext(), ((TextView) parent.getChildAt(0)).getText().toString() , Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        // 마케팃 수신 동의
        agree_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                Toast.makeText(getApplicationContext(), "체크상태 = " + isChecked, Toast.LENGTH_SHORT).show();

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
                if(tc1_checked == true && tc2_checked == true)
                {
                    start_btn.setImageDrawable(getResources().getDrawable(R.mipmap.start_btn));
                }
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
                if(tc1_checked == true && tc2_checked == true)
                {
                    start_btn.setImageDrawable(getResources().getDrawable(R.mipmap.start_btn));
                }
            }
        });


        start_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "안 쓴 거 있나 확인하는 거 넣어야징..~~", Toast.LENGTH_SHORT).show();
            }
        });

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