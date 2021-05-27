package com.example.perfume;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class AppSatisfactionActivity extends AppCompatActivity {

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private ImageButton btn_submit;
    private EditText edit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_satisfaction);

        String[] items = {"향수 브랜드", "향수 추천", "기능", "디자인", "엔지니어링", "기타", "카테고리 선택"};

        btn_submit = (ImageButton) findViewById(R.id.btn_submit);
        edit_content = (EditText) findViewById(R.id.edit_content);

        // Edittext 내용 쓰여 있으면 제출하기 버튼 활성화
        edit_content.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (edit_content.getText().toString().length() != 0)
                    btn_submit.setImageResource(R.mipmap.btn_submit);
                else
                    btn_submit.setImageResource(R.mipmap.btn_unsubmit);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, items) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View v = super.getView(position, convertView, parent);
                Typeface externalFont = Typeface.createFromAsset(getAssets(), "font/kopubworlddotumedium.ttf");
                ((TextView) v).setTypeface(externalFont);
                ((TextView) v).setTextSize(15);

                if (position == getCount()) {
                    ((TextView) v).setText("");
                    ((TextView) v).setHint(getItem(getCount()));
                    ((TextView) v).setHintTextColor(Color.parseColor("#6557FF"));
                    ((TextView) v).setTextSize(15);
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

        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setSelection(adapter.getCount());

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long l) {
                Typeface externalFont = Typeface.createFromAsset(getAssets(), "font/kopubworlddotumbold.ttf");
                ((TextView) view).setTypeface(externalFont);
                ((TextView) parent.getChildAt(0)).setTextColor(Color.parseColor("#6557FF"));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}