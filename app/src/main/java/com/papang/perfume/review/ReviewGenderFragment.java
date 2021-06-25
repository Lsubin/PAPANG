package com.papang.perfume.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.papang.perfume.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewGenderFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewGenderFragment extends Fragment {
    TextView woman_gage;
    TextView man_gage;
    TextView none_gage;

    ProgressBar woman;
    ProgressBar man;
    ProgressBar none;

    View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewGenderFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewGenderFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewGenderFragment newInstance(String param1, String param2) {
        ReviewGenderFragment fragment = new ReviewGenderFragment();
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
        v = inflater.inflate(R.layout.fragment_review_gender, container, false);

        woman_gage = (TextView) v.findViewById(R.id.woman_gage);
        man_gage = (TextView) v.findViewById(R.id.man_gage);
        none_gage = (TextView) v.findViewById(R.id.none_gage);

        woman = (ProgressBar) v.findViewById(R.id.woman_progressbar);
        man = (ProgressBar) v.findViewById(R.id.man_progressbar);
        none = (ProgressBar) v.findViewById(R.id.none_progressbar);

        return v;
    }
}