package com.example.perfume.main.wish;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.perfume.ProductDetailsActivity;
import com.example.perfume.QuestionActivity;
import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
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
import static java.lang.Thread.sleep;

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

    ImageButton find_my_perfume_btn;

    private boolean itemTouch;
    ProgressBar loading_pb;
    ConstraintLayout whole_frame;
    ConstraintLayout whole_frame2;
    Thread thread;
    int i  = 0;
    Boolean isCheckedImg = false;
    Boolean isCheckedData = false;

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
        whole_frame2 = (ConstraintLayout) view.findViewById(R.id.whole_frame2);
        find_my_perfume_btn = (ImageButton) view.findViewById(R.id.find_my_perfume_btn);

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
        loading_pb = (ProgressBar)view.findViewById(R.id.loading_pb);
        whole_frame = (ConstraintLayout)view.findViewById(R.id.whole_frame);

        //로딩
        thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while(true){
                    try{
                        i++;
                        sleep(1000);
                        if(isCheckedData == true && isCheckedImg == true) { // 향수 정보랑 이미지 불러와졌으면
                            handler.sendEmptyMessage(1);
                            break;
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }); thread.start();

        if(access.equals("Login")){
            getWishList();
        }
        else{
            wishcount_text.setText("0개");
            show_noWish();
        }


        wish_product_grid.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView rv, @NonNull MotionEvent e) {
                if (MotionEvent.ACTION_UP == e.getAction() && itemTouch) {
                    View reV = rv.findChildViewUnder(e.getX(), e.getY());
                    String p_name = adapter.getName(rv.getChildAdapterPosition(reV));
                    Intent gotoDetail = new Intent(context, ProductDetailsActivity.class);
                    gotoDetail.putExtra("name", p_name);
                    startActivity(gotoDetail);
                } else if (MotionEvent.ACTION_DOWN == e.getAction()) {
                    itemTouch = true;
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

        wish_product_grid.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                itemTouch = false;
            }
        });

        return view;
    }

    private void show_noWish() {
        loading_pb.setVisibility(View.INVISIBLE);
        whole_frame2.setVisibility(View.VISIBLE);
        whole_frame.setVisibility(View.INVISIBLE);
        find_my_perfume_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });
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
                isCheckedData = true;

                // 저장 목록 아이템 개수
                wishcount_text.setText(String.valueOf(wishes.size() + " 개"));
                adapter = new WishProductAdapter(context, wishes, WishProudctFragment.this);
                wish_product_grid.setAdapter(adapter);

                // 찜 0개 이면 나만의 향수 찾기 화면 보이게
                if(wishes.size() == 0)
                {
                   show_noWish();
                }
            }

            @Override
            public void onFailure(Call<List<Wish>> call, Throwable t) {

            }
        });
    }

    private Handler handler = new Handler(){
        public void handleMessage(Message msg){
            if(msg.what == 1)
            {
                loading_pb.setVisibility(View.INVISIBLE);
                whole_frame.setVisibility(View.VISIBLE);
                whole_frame2.setVisibility(View.INVISIBLE);
            }
        }
    };

    // 이미지는 어댑터에서 불러옴, 불러오면 true
    public boolean setCheckedImg(boolean check)
    {
        return isCheckedImg = check;
    }

}