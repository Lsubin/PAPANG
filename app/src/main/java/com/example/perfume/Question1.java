package com.example.perfume;

import android.content.Context;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question1 extends Fragment {

    public Boolean q1_state = false;       // 선택했는지 상태 값

    ImageButton ode_c;
    ImageButton ode_d;
    ImageButton ode_p;

    ImageView ode_c_text;
    ImageView ode_d_text;
    ImageView ode_p_text;

    Context context;
    View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question1() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question1.
     */
    // TODO: Rename and change types and number of parameters
    public static Question1 newInstance(String param1, String param2) {
        Question1 fragment = new Question1();
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
        v = inflater.inflate(R.layout.fragment_question1, container, false);
        // Inflate the layout for this fragment

        ode_c = (ImageButton)v.findViewById(R.id.ode_c);        // 오대 코롱
        ode_d = (ImageButton)v.findViewById(R.id.ode_d);        // 오드 뚜왈렛
        ode_p = (ImageButton)v.findViewById(R.id.ode_p);        // 오드 퍼퓸

        ode_c_text = (ImageView)v.findViewById(R.id.ode_c_text);        // 오대 코롱
        ode_d_text = (ImageView)v.findViewById(R.id.ode_d_text);        // 오드 뚜왈렛
        ode_p_text = (ImageView)v.findViewById(R.id.ode_p_text);        // 오드 퍼퓸

        ImageButton.OnClickListener btnClickListener = new ImageButton.OnClickListener(){
            @Override
            public void onClick(View v) {
                switch(v.getId()){
                    case R.id.ode_c :
                        if(q1_state == true){
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_c_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_c_text));
                            q1_state = false;
                        }
                        else{
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.circle_c_click));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            ode_c_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_c_text_c));
                            ode_d_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_d_text));
                            ode_p_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_p_text));
                            q1_state = true;
                        }
                        break;
                    case R.id.ode_d :
                        if(q1_state == true){
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_d_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_d_text));
                            q1_state = false;
                        }
                        else {
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.circle_d_click));
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            ode_c_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_c_text));
                            ode_d_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_d_text_c));
                            ode_p_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_p_text));
                            q1_state = true;
                        }
                        break;
                    case R.id.ode_p :
                        if(q1_state == true){
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            ode_p_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_p_text));
                            q1_state = false;
                        }
                        else {
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.circle_p_click));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_c_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_c_text));
                            ode_d_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_d_text));
                            ode_p_text.setImageDrawable(getResources().getDrawable(R.mipmap.ode_p_text_c));
                            q1_state = true;
                        }
                        break;
                }
            }
        };

        ode_c.setOnClickListener(btnClickListener);
        ode_d.setOnClickListener(btnClickListener);
        ode_p.setOnClickListener(btnClickListener);

        return v;
    }
}