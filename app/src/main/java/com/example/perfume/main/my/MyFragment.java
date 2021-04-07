package com.example.perfume.main.my;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.perfume.R;

public class MyFragment extends Fragment {

    NotLoginFragment fragment;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my, container, false);

        fragment = new NotLoginFragment();
        getFragmentManager()
                .beginTransaction()
                .replace(R.id.fragment_layout, fragment)
                .commit();

        return view;
    }
}
