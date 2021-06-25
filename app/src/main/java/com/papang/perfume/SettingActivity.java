package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SettingActivity extends AppCompatActivity {

    private TextView text_setting;
    private ImageView btn_right;
    private ListView list_setting;
    private SettingAdpater adapter;
    private ImageButton btn_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        btn_back = (ImageButton) findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        list_setting = (ListView) findViewById(R.id.list_setting);
        adapter = new SettingAdpater();
        list_setting.setAdapter(adapter);

        adapter.addItem("공지사항");
        adapter.addItem("이용약관");
        adapter.addItem("개인정보처리방침");
        adapter.addItem("1:1 문의");

        adapter.notifyDataSetChanged();
        list_setting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                if (position == 0) {
                    Intent intent = new Intent(getApplicationContext(), SettingDetail1Activity.class);
                    startActivity(intent);
                } else if (position == 1) {
                    Intent intent = new Intent(getApplicationContext(), SettingDetail2Activity.class);
                    startActivity(intent);
                } else if (position == 2) {
                    Intent intent = new Intent(getApplicationContext(), SettingDetail3Activity.class);
                    startActivity(intent);
                } else if (position == 3) {
                    Intent intent = new Intent(getApplicationContext(), SettingDetail4Activity.class);
                    startActivity(intent);
                }
            }
        });

    }


    // setting 리스트뷰 어뎁터
    class SettingAdpater extends BaseAdapter {
        ArrayList<String> items = new ArrayList<String>();

        @Override
        public int getCount() {
            return items.size();
        }

        public void addItem(String item) {
            items.add(item);
        }

        @Override
        public Object getItem(int position) {
            return items.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final int pos = position;
            final Context context = parent.getContext();

            if (convertView == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                convertView = inflater.inflate(R.layout.item_list_setting, parent, false);
            }

            text_setting = (TextView) convertView.findViewById(R.id.text_setting);
            btn_right = (ImageView) convertView.findViewById(R.id.btn_right);

            String setting_name = items.get(position);

            text_setting.setText(setting_name);
            btn_right.setImageResource(R.mipmap.right_btn);

            return convertView;
        }
    }
}