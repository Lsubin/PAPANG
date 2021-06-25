package com.papang.perfume;

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

import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;
import com.papang.perfume.data.Satisfaction;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppSatisfactionActivity extends AppCompatActivity {

    DataService dataService;
    DataApi dataApi;

    private Spinner spinner;
    private ArrayAdapter<String> adapter;
    private ImageButton btn_submit;
    private EditText edit_content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_satisfaction);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

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
                {
                    if(!spinner.getSelectedItem().toString().equals("카테고리 선택"))
                        btn_submit.setImageResource(R.mipmap.btn_submit);
                }
                else
                    btn_submit.setImageResource(R.mipmap.btn_unsubmit);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btn_submit.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.btn_submit).getConstantState())){
                    String type = spinner.getSelectedItem().toString();
                    String text = edit_content.getText().toString();
                    Map<String, String> map = new HashMap();
                    map.put("type", type);
                    map.put("text", text);
                    dataApi.addSatisfaction(map).enqueue(new Callback<Satisfaction>() {
                        @Override
                        public void onResponse(Call<Satisfaction> call, Response<Satisfaction> response) {
                            Toast.makeText(AppSatisfactionActivity.this, "좋은 의견 감사합니다.", Toast.LENGTH_SHORT).show();
                            finish();
                        }

                        @Override
                        public void onFailure(Call<Satisfaction> call, Throwable t) {

                        }
                    });
                }
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