package com.example.perfume;

import android.content.Context;
import android.icu.number.IntegerWidth;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.os.Debug;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rizlee.rangeseekbar.RangeSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question7 extends Fragment implements RangeSeekBar.OnRangeSeekBarRealTimeListener {

    RangeSeekBar price_seekbar;
    TextView price_min;
    TextView price_max;
    private Context context;

    View v;
    public RangeSeekBar.OnRangeSeekBarRealTimeListener mOnRangeListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    int max;
    int min;

    public Question7() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question7.
     */
    // TODO: Rename and change types and number of parameters
    public static Question7 newInstance(String param1, String param2) {
        Question7 fragment = new Question7();
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

        v = inflater.inflate(R.layout.fragment_question7, container, false);
        context = container.getContext();

        price_max = (TextView) v.findViewById(R.id.price_tag_max);
        price_min = (TextView) v.findViewById(R.id.price_tag_min);
        price_seekbar = (RangeSeekBar) v.findViewById(R.id.price_seekbar);

        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if (((QuestionActivity) QuestionActivity.context).q_state[6] != false) {
            min = Integer.parseInt(((QuestionActivity) QuestionActivity.context).q_result[6]);
            max = Integer.parseInt(((QuestionActivity) QuestionActivity.context).q_result[7]);
            price_min.setText(Integer.toString(min));
            price_max.setText(Integer.toString(max));
            price_seekbar.setLeft(min);
            price_seekbar.setRight(max);
        } else {
            min = 0;
            max = 0;
        }

        price_seekbar.setListenerRealTime(this);
        return v;
    }

    @Override
    public void onValuesChanging(float v, float v1) {
        price_min.setText(Float.toString(v));
        price_max.setText(Float.toString(v1));
        ((QuestionActivity) QuestionActivity.context).nextPage(6, true, String.valueOf(v));
        ((QuestionActivity) QuestionActivity.context).nextPage(7, true, String.valueOf(v1));
    }

    @Override
    public void onValuesChanging(int i, int i1) {
        price_min.setText(Integer.toString(i));
        price_max.setText(Integer.toString(i1));
        ((QuestionActivity) QuestionActivity.context).nextPage(6, true, String.valueOf(i));
        ((QuestionActivity) QuestionActivity.context).nextPage(7, true, String.valueOf(i1));
    }
}