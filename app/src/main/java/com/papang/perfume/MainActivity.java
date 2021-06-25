package com.papang.perfume;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;

public class MainActivity extends AppCompatActivity {

    Menu menu;
    BottomNavigationView navView;
    NavController navController;

    public static Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        navView = findViewById(R.id.nav_view);
        navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);

        /*
        menu = navView.getMenu();
        navView.setItemIconTintList(null);
        navView.setSelectedItemId(R.id.navigation_home);
        navView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId())
                {
                    case R.id.navigation_home:
                        item.setIcon(R.mipmap.home_c);    // 선택한 이미지 변경
                        menu.findItem(R.id.navigation_wish).setIcon(R.mipmap.wish);
                        menu.findItem(R.id.navigation_event).setIcon(R.mipmap.event);
                        menu.findItem(R.id.navigation_my).setIcon(R.mipmap.mypage);
                        break;

                    case R.id.navigation_wish:
                        item.setIcon(R.mipmap.wish_c);    // 선택한 이미지 변경
                        menu.findItem(R.id.navigation_home).setIcon(R.mipmap.home);
                        menu.findItem(R.id.navigation_event).setIcon(R.mipmap.event);
                        menu.findItem(R.id.navigation_my).setIcon(R.mipmap.mypage);
                        break;

                    case R.id.navigation_event:
                        item.setIcon(R.mipmap.event_c);    // 선택한 이미지 변경
                        menu.findItem(R.id.navigation_wish).setIcon(R.mipmap.wish);
                        menu.findItem(R.id.navigation_home).setIcon(R.mipmap.home);
                        menu.findItem(R.id.navigation_my).setIcon(R.mipmap.mypage);
                        break;

                    case R.id.navigation_my:
                        item.setIcon(R.mipmap.mypage_c);    // 선택한 이미지 변경
                        menu.findItem(R.id.navigation_wish).setIcon(R.mipmap.wish);
                        menu.findItem(R.id.navigation_event).setIcon(R.mipmap.event);
                        menu.findItem(R.id.navigation_home).setIcon(R.mipmap.home);
                        break;
                }// switch()..
                return true;
            }
        });*/
    }

}