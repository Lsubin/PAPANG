package com.example.perfume.main.my;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.perfume.NotificationActivity;
import com.example.perfume.R;
import com.example.perfume.SettingActivity;

public class MyFragment extends Fragment {

    NotLoginFragment fragment;
    ImageButton btn_alarm;
    ImageButton btn_setting;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        fragment = new NotLoginFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit();

        btn_alarm = (ImageButton) view.findViewById(R.id.btn_alarm);
        btn_setting = (ImageButton) view.findViewById(R.id.btn_setting);

        btn_alarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), NotificationActivity.class);
                startActivity(intent);
            }
        });

        btn_setting.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), SettingActivity.class);
                startActivity(intent);
            }
        });


        return view;
    }
}
