package com.example.perfume.main.my;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.perfume.R;
import com.example.perfume.main.my.MyPageFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotLoginFragment extends Fragment {

    MyPageFragment fragment;
    ImageButton google_btn, naver_btn, kakao_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotLoginFragment newInstance(String param1, String param2) {
        NotLoginFragment fragment = new NotLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_not_login, container, false);


        google_btn = (ImageButton)view.findViewById(R.id.google_btn);
        naver_btn = (ImageButton)view.findViewById(R.id.naver_btn);
        kakao_btn = (ImageButton)view.findViewById(R.id.kakao_btn);

        fragment = new MyPageFragment();
        // 임시로 로그인 화면 이동!
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_layout, fragment)
                        .commit();
            }
        });
        return view;
    }
}