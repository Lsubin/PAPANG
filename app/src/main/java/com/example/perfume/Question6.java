package com.example.perfume;

import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Question6#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Question6 extends Fragment {

    View v;

    // Flavor_click_drawables
    ArrayList<Drawable> drawables_click;

    RecyclerView flavor2_grid;
    FlavorAdapter flavorAdapter;
    ArrayList<Drawable> drawables;
    ArrayList<Integer> drawables_Num;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public Question6() {
        // Required empty public constructor
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
        // click 이미지들 추가
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_aldehyde_click));    //알데하이드 1번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_animalic_click));    //애니멀릭 2번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_aromatic_click));    //아로마틱 3번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_balsam_click));      //발삼 4번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_chypre_click));      //시프레 5번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_citrus_click));      //시트러스 6번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_green_click));       //그린 7번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_floral_click));      //플로럴 8번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_fruity_click));      //프루티 9번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_spicy_click));       //스파이시 10번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_woody_click));       //우디 11번
        drawables_click.add(getResources().getDrawable(R.mipmap.flavor_nope_click));       //없음 12번

        init(v);

        return v;
    }

    private void init(View v) {

        drawables = new ArrayList<>();
        drawables.add(getResources().getDrawable(R.mipmap.flavor_green));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_citrus));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_fruity));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_floral));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aldehyde));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_woody));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_animalic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_balsam));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_aromatic));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_spicy));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_chypre));
        drawables.add(getResources().getDrawable(R.mipmap.flavor_nope));

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


        flavor2_grid = (RecyclerView)v.findViewById(R.id.flavor2_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(v.getContext(), 3);
        flavorAdapter = new FlavorAdapter(drawables, drawables_Num);
        flavor2_grid.setAdapter(flavorAdapter);
    }

}