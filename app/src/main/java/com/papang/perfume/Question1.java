package com.papang.perfume;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * A simple {@link Fragment} subclass.
 * <p>
 * Use the {@link Question1#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question1 extends Fragment {

    public Boolean q1_state = false;       // 선택했는지 상태 값
    public String q1_result;

    ImageButton ode_c;
    ImageButton ode_d;
    ImageButton ode_p;
    ImageButton ode_pp;

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

        ode_c = (ImageButton) v.findViewById(R.id.ode_c);        // 오대 코롱
        ode_d = (ImageButton) v.findViewById(R.id.ode_d);        // 오드 뚜왈렛
        ode_p = (ImageButton) v.findViewById(R.id.ode_p);        // 오드 퍼퓸
        ode_pp = (ImageButton) v.findViewById(R.id.ode_pp);      // 퍼퓸

        /*
        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if (((QuestionActivity) QuestionActivity.context).q_state[0] != false) {
            String result = ((QuestionActivity) QuestionActivity.context).q_result[0];
            switch (result) {
                case "ode_c":
                    ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.circle_c_click));
                    ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                    ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                    ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                    q1_state = true;
                    q1_result = result;
                    break;
                case "ode_d":
                    ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.circle_d_click));
                    ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                    ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                    ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                    q1_state = true;
                    q1_result = result;
                    break;
                case "ode_p":
                    ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.circle_p_click));
                    ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                    ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                    ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                    q1_state = true;
                    q1_result = result;
                    break;
                case "ode_pp":
                    ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.circle_pp_click));
                    ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                    ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                    ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                    q1_state = true;
                    q1_result = result;
                    break;
            }
        } */

        ImageButton.OnClickListener btnClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.ode_c:
                        if (q1_state == true && q1_result == "ode_c") {
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            q1_state = false;
                            q1_result = "";
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        } else if ((q1_state == false) || (q1_state == true && q1_result != "ode_c")) {
                            q1_result = "ode_c";
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.circle_c_click));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                            q1_state = true;
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        }
                        break;
                    case R.id.ode_d:
                        if (q1_state == true && q1_result == "ode_d") {
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            q1_state = false;
                            q1_result = "";
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        } else if ((q1_state == false) || (q1_state == true && q1_result != "ode_d")) {
                            q1_result = "ode_d";
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.circle_d_click));
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                            q1_state = true;
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        }
                        break;
                    case R.id.ode_p:
                        if (q1_state == true && q1_result == "ode_p") {
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            q1_state = false;
                            q1_result = "";
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        } else if ((q1_state == false) || (q1_state == true && q1_result != "ode_p")) {
                            q1_result = "ode_p";
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.circle_p_click));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                            q1_state = true;
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        }
                        break;

                    case R.id.ode_pp:
                        if (q1_state == true && q1_result == "ode_pp") {
                            ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_pp));
                            q1_state = false;
                            q1_result = "";
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        } else if ((q1_state == false) || (q1_state == true && q1_result != "ode_pp")) {
                            q1_result = "ode_pp";
                            ode_pp.setImageDrawable(getResources().getDrawable(R.mipmap.circle_pp_click));
                            ode_d.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_d));
                            ode_c.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_c));
                            ode_p.setImageDrawable(getResources().getDrawable(R.mipmap.uncircle_p));
                            q1_state = true;
                            ((QuestionActivity) QuestionActivity.context).nextPage(0, q1_state, q1_result);
                        }
                        break;
                }
            }
        };

        ode_c.setOnClickListener(btnClickListener);
        ode_d.setOnClickListener(btnClickListener);
        ode_p.setOnClickListener(btnClickListener);
        ode_pp.setOnClickListener(btnClickListener);

        return v;
    }
}