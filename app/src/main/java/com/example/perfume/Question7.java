package com.example.perfume;

import android.icu.number.IntegerWidth;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rizlee.rangeseekbar.RangeSeekBar;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question7#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question7 extends Fragment {

    RangeSeekBar price_seekbar;
    TextView price_min;
    TextView price_max;

    View v;
    public RangeSeekBar.OnRangeSeekBarPostListener mOnRangeListener = null;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

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

        price_max = (TextView)v.findViewById(R.id.price_tag_max);
        price_min = (TextView)v.findViewById(R.id.price_tag_min);
        price_seekbar = (RangeSeekBar)v.findViewById(R.id.price_seekbar);

        price_seekbar.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                price_min.setText(price_seekbar.getLeftText());
                price_max.setText(price_seekbar.getRightText());
                return false;
            }
        });

        /*
        price_seekbar.OnRangeSeekBarPostListener(new RangeSeekBar.OnRangeSeekBarRealTimeListener(){
            @Override
            public void onValuesChanging(float v, float v1) {
                price_min.setText(Float.toString(v));
                price_max.setText(Float.toString(v1));
            }

            @Override
            public void onValuesChanging(int i, int i1) {
                price_min.setText(i);
                price_max.setText(i1);
            }
        });
        */
        return v;
    }
}