package com.example.perfume;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question3 extends Fragment {

    public Boolean q3_state = false;       // 선택했는지 상태 값
    public String q3_result;

    ImageButton style_1_btn;
    ImageButton style_2_btn;
    ImageButton style_3_btn;
    ImageButton style_4_btn;
    ImageButton style_5_btn;
    ImageButton style_6_btn;
    ImageButton style_7_btn;
    ImageButton style_8_btn;

    Context context;
    View v;
    String style_name;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question3() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question3.
     */
    // TODO: Rename and change types and number of parameters
    public static Question3 newInstance(String param1, String param2) {
        Question3 fragment = new Question3();
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
        v = inflater.inflate(R.layout.fragment_question3, container, false);

        style_1_btn = (ImageButton)v.findViewById(R.id.style_1_btn);
        style_2_btn = (ImageButton)v.findViewById(R.id.style_2_btn);
        style_3_btn = (ImageButton)v.findViewById(R.id.style_3_btn);
        style_4_btn = (ImageButton)v.findViewById(R.id.style_4_btn);
        style_5_btn = (ImageButton)v.findViewById(R.id.style_5_btn);
        style_6_btn = (ImageButton)v.findViewById(R.id.style_6_btn);
        style_7_btn = (ImageButton)v.findViewById(R.id.style_7_btn);
        style_8_btn = (ImageButton)v.findViewById(R.id.style_8_btn);

        ImageButton.OnClickListener btnClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.style_1_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1_c));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "1";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_2_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2_c));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "2";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_3_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3_c));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "3";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_4_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4_c));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "4";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_5_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5_c));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "5";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_6_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6_c));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "6";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_7_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7_c));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                        q3_state = true;
                        q3_result = "7";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;

                    case R.id.style_8_btn :
                        style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                        style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                        style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                        style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                        style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                        style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                        style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                        style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8_c));
                        q3_state = true;
                        q3_result = "8";
                        ((QuestionActivity)QuestionActivity.context).nextPage(2, q3_state, q3_result);
                        break;
                }
            }
        };

        style_1_btn.setOnClickListener(btnClickListener);
        style_2_btn.setOnClickListener(btnClickListener);
        style_3_btn.setOnClickListener(btnClickListener);
        style_4_btn.setOnClickListener(btnClickListener);
        style_5_btn.setOnClickListener(btnClickListener);
        style_6_btn.setOnClickListener(btnClickListener);
        style_7_btn.setOnClickListener(btnClickListener);
        style_8_btn.setOnClickListener(btnClickListener);

        return v;
    }
}