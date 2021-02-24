package com.example.perfume;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question5#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question5 extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String flavor = "flavor_balsam";
    Context context;
    View v;

    RecyclerView add_flavor_grid;
    FlavorAdapter flavorAdapter;
    ArrayList<Drawable> drawables;

    ImageView q5_frame4;

    public Question5() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Question5.
     */
    // TODO: Rename and change types and number of parameters
    public static Question5 newInstance(String param1, String param2) {
        Question5 fragment = new Question5();
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
        context = container.getContext();
        v = inflater.inflate(R.layout.fragment_question5, container, false);
        q5_frame4 = (ImageView)v.findViewById(R.id.q5_frame4);

        // 고른 향료에 따라 이미지 다르게 뿌려주기
        if(flavor.equals("flavor_citrus"))
            flavor_citrus(v);
        if(flavor.equals("flavor_aldehyde"))
            flavor_aldehyde(v);
        if(flavor.equals("flavor_animalic"))
            flavor_animalic(v);
        if(flavor.equals("flavor_fruity"))
            flavor_fruity(v);
        if(flavor.equals("flavor_floral"))
            flavor_floral(v);
        if(flavor.equals("flavor_aromatic"))
            flavor_aromatic(v);
        if(flavor.equals("flavor_spicy"))
            flavor_spicy(v);
        if(flavor.equals("flavor_chypre"))
            flavor_chypre(v);
        if(flavor.equals("flavor_woody"))
            flavor_woody(v);
        if(flavor.equals("flavor_green"))
            flavor_green(v);
        if(flavor.equals("flavor_balsam"))
            flavor_balsam(v);



        Log.v("향료", flavor);

        return v;
    }

    // 시트러스
    private void flavor_citrus(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_citrus);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));;
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aldehyde));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 알데하이드
    private void flavor_aldehyde(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_aldehyde);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 애니멀
    private void flavor_animalic(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_animalic);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_spicy));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 프루티
    private void flavor_fruity(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_fruity);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 플로럴
    private void flavor_floral(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_floral);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_fruity));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aldehyde));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_spicy));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_chypre));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 아로마틱
    private void flavor_aromatic(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_aromatic);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 스파이시
    private void flavor_spicy(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_spicy);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 시프레
    private void flavor_chypre(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_chypre);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_fruity));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 우디
    private void flavor_woody(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_woody);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_spicy));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 그린
    private void flavor_green(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_green);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_fruity));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_chypre));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }

    // 발삼
    private void flavor_balsam(View v) {
        q5_frame4.setImageResource(R.mipmap.question_5_text_balsam);
        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_spicy));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));

        add_flavor_grid = (RecyclerView)v.findViewById(R.id.add_flavor_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        add_flavor_grid.setLayoutManager(mLayoutManager);
        add_flavor_grid.addItemDecoration(new ItemDecoration(getActivity()));
        flavorAdapter = new FlavorAdapter(drawables);
        add_flavor_grid.setAdapter(flavorAdapter);
    }



}