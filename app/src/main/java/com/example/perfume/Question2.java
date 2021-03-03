package com.example.perfume;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

    RangeSeekBar size_seekbar;
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

        bottle_size = (ImageView)v.findViewById(R.id.q_bottle_size);
        size_seekbar = (RangeSeekBar)v.findViewById(R.id.size_seekbar);
        size_seekbar.setOnRangeChangedListener(new OnRangeChangedListener() {
            @Override
            public void onRangeChanged(RangeSeekBar view, float leftValue, float rightValue, boolean isFromUser) {
            }

            @Override
            public void onStartTrackingTouch(RangeSeekBar view, boolean isLeft) {

            }

            @Override
            public void onStopTrackingTouch(RangeSeekBar view, boolean isLeft) {
                float progress = view.getLeftSeekBar().getProgress();
                switch(String.valueOf(progress)){
                    case "0.0":
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.bottle));
                        break;
                    case "1.0":
                        q2_state = true;
                        q2_result = "1";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size1));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case "2.0":
                        q2_state = true;
                        q2_result = "2";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size2));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case "3.0":
                        q2_state = true;
                        q2_result = "3";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size3));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case "4.0":
                        q2_state = true;
                        q2_result = "4";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size4));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case "5.0":
                        q2_state = true;
                        q2_result = "5";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size5));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                    case "6.0":
                        q2_state = true;
                        q2_result = "6";
                        bottle_size.setImageDrawable(getResources().getDrawable(R.mipmap.size5));
                        ((QuestionActivity)QuestionActivity.context).nextPage(1, q2_state, q2_result);
                        break;
                }
            }
        });
        return v;
    }
}