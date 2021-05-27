package com.example.perfume.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.PointerIcon;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.perfume.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewSeasonFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewSeasonFragment extends Fragment {

    TextView spring_gage;
    TextView summer_gage;
    TextView fall_gage;
    TextView winter_gage;

    ProgressBar spring;
    ProgressBar summer;
    ProgressBar fall;
    ProgressBar winter;

    View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewSeasonFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewSeasonFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewSeasonFragment newInstance(String param1, String param2) {
        ReviewSeasonFragment fragment = new ReviewSeasonFragment();
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
        v = inflater.inflate(R.layout.fragment_review_season, container, false);

        spring_gage = (TextView) v.findViewById(R.id.spring_gage);
        summer_gage = (TextView) v.findViewById(R.id.summer_gage);
        fall_gage = (TextView) v.findViewById(R.id.fall_gage);
        winter_gage = (TextView) v.findViewById(R.id.winter_gage);

        spring = (ProgressBar) v.findViewById(R.id.spring_progressbar);
        summer = (ProgressBar) v.findViewById(R.id.summer_progressbar);
        fall = (ProgressBar) v.findViewById(R.id.fall_progressbar);
        winter = (ProgressBar) v.findViewById(R.id.winter_progressbar);

        return v;
    }
}