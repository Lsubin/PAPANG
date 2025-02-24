package com.papang.perfume.review;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RatingBar;
import android.widget.TextView;

import com.papang.perfume.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReviewRatingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReviewRatingFragment extends Fragment {

    TextView average_rating;
    RatingBar ratingBar;

    View v;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReviewRatingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReviewRatingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReviewRatingFragment newInstance(String param1, String param2) {
        ReviewRatingFragment fragment = new ReviewRatingFragment();
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
        v = inflater.inflate(R.layout.fragment_review_rating, container, false);

        average_rating = (TextView) v.findViewById(R.id.average_rating);
        ratingBar = (RatingBar) v.findViewById(R.id.average_ratingbar);

        return v;
    }
}