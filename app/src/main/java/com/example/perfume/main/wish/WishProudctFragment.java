package com.example.perfume.main.wish;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.perfume.FlavorAdapter;
import com.example.perfume.ItemDecoration;
import com.example.perfume.R;
import com.example.perfume.main.GridSpacingItemDecoration;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WishProudctFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WishProudctFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    RecyclerView wish_product_grid;
    WishProductAdapter adapter;
    TextView wishcount_text;

    SwipeRefreshLayout mSwipeRefreshLayout;
    FragmentTransaction ft;

    public WishProudctFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WishProudctFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WishProudctFragment newInstance(String param1, String param2) {
        WishProudctFragment fragment = new WishProudctFragment();
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
        View view = inflater.inflate(R.layout.fragment_wish_proudct, container, false);

        wishcount_text = (TextView)view.findViewById(R.id.wishcount_text);


        ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this);
        // 새로고침
        mSwipeRefreshLayout = (SwipeRefreshLayout)view.findViewById(R.id.swipe_fresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        ft.commit();
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });

        // 예시
        ArrayList<Integer> data1 = new ArrayList<>();
        ArrayList<String> data2 = new ArrayList<>();
        ArrayList<String> data3 = new ArrayList<>();
        ArrayList<Integer> data4 = new ArrayList<>();

        // 예시로 8개 추가
        for(int i = 0; i < 8; i++)
        {
            data1.add(R.mipmap.ex_chanel_image);
            data2.add("브랜드");
            data3.add("향수 이름");
            data4.add(R.mipmap.icon_full_heart);
        }
        // 저장 목록 아이템 개수
        wishcount_text.setText(String.valueOf(data1.size() + " 개"));

        int spancount = 2;
        int spacing = 50;
        boolean includeEdge = true;

        wish_product_grid = (RecyclerView)view.findViewById(R.id.wish_perfume_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        wish_product_grid.setLayoutManager(mLayoutManager);
        wish_product_grid.addItemDecoration(new GridSpacingItemDecoration(spancount, spacing, includeEdge));
        adapter = new WishProductAdapter(view.getContext(), data1, data2, data3, data4);
        wish_product_grid.setAdapter(adapter);

        return view;
    }
}