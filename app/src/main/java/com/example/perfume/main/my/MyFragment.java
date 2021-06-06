package com.example.perfume.main.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.perfume.NotificationActivity;
import com.example.perfume.R;
import com.example.perfume.SettingActivity;

import static android.content.Context.MODE_PRIVATE;

public class MyFragment extends Fragment {

    NotLoginFragment notLoginFragment;
    MyPageFragment myPageFragment;
    ImageButton btn_alarm;
    ImageButton btn_setting;
    FragmentTransaction ft;
    FragmentManager fragmentManager;
    private String access;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public static Context mp_Context;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);
        mp_Context = view.getContext();

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

    private void checkLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        access = sharedPreferences.getString("Access","");
    }

    @Override
    public void onResume() {
        super.onResume();

        fragmentManager = getFragmentManager();
        ft = getFragmentManager().beginTransaction();

        checkLogin();

        if(access.equals("Login")){
            myPageFragment = new MyPageFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, myPageFragment)
                    .commit();
        }else{
            notLoginFragment = new NotLoginFragment();
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.fragment_layout, notLoginFragment)
                    .commit();
        }

    }
}
