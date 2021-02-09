package com.example.perfume.main.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;

import java.util.ArrayList;

public class HomeFragment extends Fragment {
    private HomeViewModel homeViewModel;
    ArrayList<String> name;
    ArrayList<String> des;
    ImageButton find_btn;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        find_btn = (ImageButton)root.findViewById(R.id.perfume_find_btn);
        find_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent recommendation = new Intent(getContext(), RecommendationActivity.class);
                startActivity(recommendation);
            }
        });

        name = new ArrayList<>();
        des = new ArrayList<>();

        name.add("산토리니의 추억");
        name.add("비오는 날 기분 UP");
        name.add("연예인이 애정하는 향수");

        des.add("그리스 산토리니 여행을 떠올리게 하는 향수 모음집");
        des.add("비 오는 우울한 날 기분 좋게 만들어주는 향수 모음집");
        des.add("유명한 연예인들이 사용하는 향수가 궁금했다면?");

        RecyclerView_Type1_Adapter rAdapter = new RecyclerView_Type1_Adapter(name, des);
        RecyclerView recyclerView = root.findViewById(R.id.themeRecyclerView);
        RecyclerView recyclerView2 = root.findViewById(R.id.themeRecyclerView2);

        recyclerView.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView.setAdapter(rAdapter);

        recyclerView2.setLayoutManager(new LinearLayoutManager(root.getContext()));
        recyclerView2.setAdapter(rAdapter);

        return root;
    }
}