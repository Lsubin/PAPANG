package com.example.perfume;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.example.perfume.adapter.FlavorAdapter;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question6 extends Fragment {

    View v;

    RecyclerView flavor2_grid;
    FlavorAdapter flavorAdapter;

    ArrayList<Integer> drawables_Num;

    public Boolean q6_state = false;
    public String q6_result;
    public Integer q6_position;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    int result;

    public Question6() {
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question6.
     */
    // TODO: Rename and change types and number of parameters
    public static Question6 newInstance(String param1, String param2) {
        Question6 fragment = new Question6();
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
        v = inflater.inflate(R.layout.fragment_question6, container, false);


        /*
        // BackBTN을 이용해 뒤로 갔다 온 경우에 원래 값을 설정해야한다.
        if (((QuestionActivity) QuestionActivity.context).q_state[5] != false) {
            result = Integer.parseInt(((QuestionActivity) QuestionActivity.context).q_result[5]);
            q6_result = String.valueOf(result);
            q6_state = true;
        } else
            result = 0;  */

        init(v);

        return v;
    }

    private void init(View v) {
        drawables_Num = new ArrayList<>();

        drawables_Num.add(7);
        drawables_Num.add(6);
        drawables_Num.add(9);
        drawables_Num.add(8);
        drawables_Num.add(1);
        drawables_Num.add(11);
        drawables_Num.add(2);
        drawables_Num.add(4);
        drawables_Num.add(3);
        drawables_Num.add(10);
        drawables_Num.add(5);
        drawables_Num.add(12);
        drawables_Num.add(13);
        drawables_Num.add(14);
        drawables_Num.add(15);


        flavor2_grid = (RecyclerView) v.findViewById(R.id.flavor2_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        flavor2_grid.setLayoutManager(mLayoutManager);
        flavor2_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(v.getContext(), drawables_Num);
        flavor2_grid.setAdapter(flavorAdapter);

        if (result != 0) {
            int num = drawables_Num.indexOf(result);
            flavorAdapter.setDrawable(num);
            q6_state = true;
            q6_result = String.valueOf(result);
        }

        flavor2_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                // RecyclerView로 전달된 TouchEvent를 받는다.
                if (e.getAction() == MotionEvent.ACTION_DOWN) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    int position = rv.getChildAdapterPosition(reV);
                    if (position >= 0) {
                        String num = String.valueOf(flavorAdapter.getNum(position));
                        if (q6_state == false) {
                            flavorAdapter.setDrawable(position);
                            q6_result = num;
                            q6_position = position;
                            q6_state = true;
                        } else if (q6_state == true && (!num.equals(q6_result))) {
                            if (result != 0)
                                ((QuestionActivity) QuestionActivity.context).deletePage(5);
                            flavorAdapter.setBackDrawable(q6_position);
                            flavorAdapter.setDrawable(position);
                            q6_result = num;
                            q6_position = position;
                        }
                        ((QuestionActivity) QuestionActivity.context).nextPage(5, q6_state, q6_result);
                    }
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
            }
        });

        flavorAdapter.notifyDataSetChanged();
    }

}