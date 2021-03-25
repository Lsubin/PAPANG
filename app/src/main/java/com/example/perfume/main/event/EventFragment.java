package com.example.perfume.main.event;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.perfume.MainActivity;
import com.example.perfume.R;

public class EventFragment extends Fragment {

    Toolbar toolbar;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);

        //상단바 툴바 셋팅
        toolbar=(androidx.appcompat.widget.Toolbar)view.findViewById(R.id.toolbar);
        MainActivity activity = (MainActivity) getActivity();
        activity.setTitle("");
        activity.setSupportActionBar(toolbar);

        return view;
    }
}