package com.papang.perfume.recommendation;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.papang.perfume.R;

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

    int index = 0;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String result;

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

        style_1_btn = (ImageButton) v.findViewById(R.id.style_1_btn);
        style_2_btn = (ImageButton) v.findViewById(R.id.style_2_btn);
        style_3_btn = (ImageButton) v.findViewById(R.id.style_3_btn);
        style_4_btn = (ImageButton) v.findViewById(R.id.style_4_btn);
        style_5_btn = (ImageButton) v.findViewById(R.id.style_5_btn);
        style_6_btn = (ImageButton) v.findViewById(R.id.style_6_btn);
        style_7_btn = (ImageButton) v.findViewById(R.id.style_7_btn);
        style_8_btn = (ImageButton) v.findViewById(R.id.style_8_btn);

        /*
        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if (((QuestionActivity) QuestionActivity.context).q_state[2] != false) {
            result = ((QuestionActivity) QuestionActivity.context).q_result[2];
            Toast.makeText(v.getContext(), result, 0).show();
            switch (result) {
                case "1":
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1_c));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "2":
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "3":
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "4":
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "5":
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "6":
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "7":
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8));
                    q3_state = true;
                    q3_result = result;
                    break;
                case "8":
                    style_8_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_8_c));
                    style_1_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_1));
                    style_2_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_2));
                    style_3_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_3));
                    style_4_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_4));
                    style_5_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_5));
                    style_6_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_6));
                    style_7_btn.setImageDrawable(getResources().getDrawable(R.mipmap.style_7));
                    q3_state = true;
                    q3_result = result;
                    break;
            }
        } else result = "null"; */

        ImageButton.OnClickListener btnClickListener = new ImageButton.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.style_1_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2);*/
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_2_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2);*/
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_3_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2);*/
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_4_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2);*/
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_5_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2); */
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_6_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2); */
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_7_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2); */
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
                        break;

                    case R.id.style_8_btn:
                        /*
                        if (!result.equals("null"))
                            ((QuestionActivity) QuestionActivity.context).deletePage(2); */
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
                        ((QuestionActivity) QuestionActivity.context).nextPage(index, q3_state, q3_result);
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