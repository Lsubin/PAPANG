package com.example.perfume.main.wish;

import android.content.Context;
import android.content.SharedPreferences;
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

import com.example.perfume.R;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.Wish;
import com.example.perfume.main.GridSpacingItemDecoration;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

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

    Context context;

    DataService dataService;
    DataApi dataApi;
    List<Wish> wishes;

    String email;
    String access;

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
        context = view.getContext();

        checkLogin();

        wishcount_text = (TextView) view.findViewById(R.id.wishcount_text);

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);
        wishes = new ArrayList<>();

        int spancount = 2;
        int spacing = 50;
        boolean includeEdge = true;

        wish_product_grid = (RecyclerView) view.findViewById(R.id.wish_perfume_grid);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(view.getContext(), 2);
        wish_product_grid.setLayoutManager(mLayoutManager);
        wish_product_grid.addItemDecoration(new GridSpacingItemDecoration(spancount, spacing, includeEdge));

        ft = getFragmentManager().beginTransaction();
        ft.detach(this).attach(this);
        // 새로고침
        mSwipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_fresh);
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


        if(access.equals("Login")){
            getWishList();
        }
        else{
            wishcount_text.setText("0개");
        }

        return view;
    }

    private void checkLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        access = sharedPreferences.getString("Access","");
        email = sharedPreferences.getString("Email","");
    }

    public void getWishList(){
        dataApi.getWishList(email).enqueue(new Callback<List<Wish>>() {
            @Override
            public void onResponse(Call<List<Wish>> call, Response<List<Wish>> response) {
                wishes = response.body();

                // 저장 목록 아이템 개수
                wishcount_text.setText(String.valueOf(wishes.size() + " 개"));
                adapter = new WishProductAdapter(context, wishes);
                wish_product_grid.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {

            }
        });
    }
}