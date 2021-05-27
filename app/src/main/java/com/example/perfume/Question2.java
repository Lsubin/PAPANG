package com.example.perfume;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.jaygoo.widget.OnRangeChangedListener;
import com.jaygoo.widget.RangeSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question2#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question2 extends Fragment {

    View v;

    ImageButton size1;
    ImageButton size2;
    ImageButton size3;
    ImageButton size4;
    ImageButton size5;
    ImageButton size6;

    ImageView bottle_size;

    Boolean q2_state;
    String q2_result;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question2() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question2.
     */
    // TODO: Rename and change types and number of parameters
    public static Question2 newInstance(String param1, String param2) {
        Question2 fragment = new Question2();
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
        v = inflater.inflate(R.layout.fragment_question2, container, false);
        // Inflate the layout for this fragment

        bottle_size = (ImageView) v.findViewById(R.id.q_bottle_size);

        size1 = (ImageButton) v.findViewById(R.id.size_1);
        size2 = (ImageButton) v.findViewById(R.id.size_2);
        size3 = (ImageButton) v.findViewById(R.id.size_3);
        size4 = (ImageButton) v.findViewById(R.id.size_4);
        size5 = (ImageButton) v.findViewById(R.id.size_5);
        size6 = (ImageButton) v.findViewById(R.id.size_6);

        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if (((QuestionActivity) QuestionActivity.context).q_state[1] != false) {
            String result = ((QuestionActivity) QuestionActivity.context).q_result[1];
            switch (result) {
                case "size1":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size1));
                    size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
                case "size2":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size2));
                    size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
                case "size3":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size3));
                    size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
                case "size4":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size4));
                    size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
                case "size5":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size5));
                    size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
                case "size6":
                    bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size6));
                    size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                    q2_state = true;
                    q2_result = result;
                    break;
            }
            ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
        }

        Button.OnClickListener sizeClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.size_1:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size1));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        q2_state = true;
                        q2_result = "size1";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case R.id.size_2:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size2));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        q2_state = true;
                        q2_result = "size2";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case R.id.size_3:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size3));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        q2_state = true;
                        q2_result = "size3";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case R.id.size_4:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size4));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        q2_state = true;
                        q2_result = "size4";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case R.id.size_5:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size5));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        q2_state = true;
                        q2_result = "size5";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case R.id.size_6:
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size6));
                        size1.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size2.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size3.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size4.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size5.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size));
                        size6.setImageDrawable(getResources().getDrawable(R.mipmap.btn_size_click));
                        q2_state = true;
                        q2_result = "size6";
                        ((QuestionActivity) QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                }
            }
        };

        size1.setOnClickListener(sizeClickListener);
        size2.setOnClickListener(sizeClickListener);
        size3.setOnClickListener(sizeClickListener);
        size4.setOnClickListener(sizeClickListener);
        size5.setOnClickListener(sizeClickListener);
        size6.setOnClickListener(sizeClickListener);

        return v;
    }
}