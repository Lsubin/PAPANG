package com.example.perfume.main.my;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.Image;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.perfume.AppSatisfactionActivity;
import com.example.perfume.EditMyinfoActivity;
import com.example.perfume.ParticipatedEventActivity;
import com.example.perfume.R;
import com.example.perfume.RecommendationActivity;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.UserRecommendation;
import com.example.perfume.data.Wish;

import org.w3c.dom.Text;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyPageFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyPageFragment extends Fragment {
    DataService dataService;
    DataApi dataApi;

    ConstraintLayout touch_event_zone;
    ConstraintLayout touch_add_zone;
    ImageButton btn_findperfume;
    ImageButton btn_eidtmyinfo;
    TextView user_name_text;

    TextView result_concentration;
    TextView result_size;
    TextView result_flavors;
    TextView result_style;
    ImageView style_img;

    ImageView result_never;

    Context context;

    private String email;
    private String nickname;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyPageFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyPageFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MyPageFragment newInstance(String param1, String param2) {
        MyPageFragment fragment = new MyPageFragment();
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
        View view = inflater.inflate(R.layout.fragment_my_page, container, false);
        context = view.getContext();

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        user_name_text = (TextView) view.findViewById(R.id.user_name_text);
        touch_event_zone = (ConstraintLayout) view.findViewById(R.id.touch_event_zone);
        touch_add_zone = (ConstraintLayout) view.findViewById(R.id.touch_add_zone);
        btn_findperfume = (ImageButton) view.findViewById(R.id.btn_findperfume);
        btn_eidtmyinfo = (ImageButton) view.findViewById(R.id.btn_editmyinfo);

        result_concentration = (TextView)view.findViewById(R.id.result_concentration);
        result_size = (TextView)view.findViewById(R.id.result_size);
        result_flavors = (TextView)view.findViewById(R.id.result_flavors);
        result_style = (TextView)view.findViewById(R.id.result_style);
        style_img = (ImageView)view.findViewById(R.id.style_img);
        result_never = (ImageView)view.findViewById(R.id.result_never);

        checkLogin();

        touch_event_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), ParticipatedEventActivity.class);
                startActivity(intent);
            }
        });

        touch_add_zone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AppSatisfactionActivity.class);
                startActivity(intent);
            }
        });

        btn_findperfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), RecommendationActivity.class);
                startActivity(intent);
            }
        });

        btn_eidtmyinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), EditMyinfoActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }

    private void checkLogin(){
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        email = sharedPreferences.getString("Email","");
        nickname = sharedPreferences.getString("Nickname","");

        user_name_text.setText(nickname + "님!");

        setUserRecommend();
    }

    private void setUserRecommend(){
        dataApi.getUserRecommend(email).enqueue(new Callback<UserRecommendation>() {
            @Override
            public void onResponse(Call<UserRecommendation> call, Response<UserRecommendation> response) {
                UserRecommendation ur;
                ur = response.body();
                style_img.setImageDrawable(getResources().getDrawable(R.mipmap.style_2_img));
                result_never.setVisibility(View.INVISIBLE);
                btn_findperfume.setVisibility(View.INVISIBLE);

                result_concentration.setVisibility(View.VISIBLE);
                result_size.setVisibility(View.VISIBLE);
                result_flavors.setVisibility(View.VISIBLE);
                result_style.setVisibility(View.VISIBLE);

                changeResultText(ur);
            }

            @Override
            public void onFailure(Call<UserRecommendation> call, Throwable t) {

            }
        });
    }

    private void changeResultText(UserRecommendation ur) { //가격은 String q_7 추가
        String flavor_1 = "";
        String flavor_2 = "";
        String flavor_3 = "";

        switch (ur.getConcentration()) {
            case "ode_c":
                result_concentration.setText("오데 코롱");
                break;
            case "ode_d":
                result_concentration.setText("오드 뚜왈렛");
                break;
            case "ode_p":
                result_concentration.setText("오데 퍼퓸");
                break;
            case "ode_pp":
                result_concentration.setText("퍼퓸");
                break;
        }

        switch (ur.getSize()) {
            case "size1":
                result_size.setText("0ml ~ 15ml");
                break;
            case "size2":
                result_size.setText("15ml ~ 30ml");
                break;
            case "size3":
                result_size.setText("30ml ~ 50ml");
                break;
            case "size4":
                result_size.setText("50ml ~ 75ml");
                break;
            case "size5":
                result_size.setText("75ml ~ 100ml");
                break;
            case "size6":
                result_size.setText("100ml 이상");
                break;
        }

        switch (ur.getStyle()) {
            case 1:
                result_style.setText("포근한, 차분한, 따뜻한, 순수한");
                break;
            case 2:
                result_style.setText("발랄한, 귀여운, 사랑스러운");
                break;
            case 3:
                result_style.setText("관능적인, 화려한");
                break;
            case 4:
                result_style.setText("환상적인, 황홀한, 몽환적인, 신비로운");
                break;
            case 5:
                result_style.setText("젠틀한, 클래식한, 깊이있는");
                break;
            case 6:
                result_style.setText("세련된, 우아한, 도시적인, 모던한");
                break;
            case 7:
                result_style.setText("산뜻한, 시원한, 활기찬");
                break;
            case 8:
                result_style.setText("강렬한, 파워풀한, 존재감있는, 대담한");
                break;
        }

        switch (ur.getFlavor1()) {
            case 1:
                flavor_1 = "Aldehyde";
                break;
            case 2:
                flavor_1 = "Animalic";
                break;
            case 3:
                flavor_1 = "Aromatic";
                break;
            case 4:
                flavor_1 = "Balsam";
                break;
            case 5:
                flavor_1 = "Chypre";
                break;
            case 6:
                flavor_1 = "Citrus";
                break;
            case 7:
                flavor_1 = "Green";
                break;
            case 8:
                flavor_1 = "Floral";
                break;
            case 9:
                flavor_1 = "Fruity";
                break;
            case 10:
                flavor_1 = "Spicy";
                break;
            case 11:
                flavor_1 = "Woody";
                break;
            case 12:
                flavor_1 = "None";
                break;
        }

        switch (ur.getFlavor2()) {
            case 1:
                flavor_2 = "Aldehyde";
                break;
            case 2:
                flavor_2 = "Animalic";
                break;
            case 3:
                flavor_2 = "Aromatic";
                break;
            case 4:
                flavor_2 = "Balsam";
                break;
            case 5:
                flavor_2 = "Chypre";
                break;
            case 6:
                flavor_2 = "Citrus";
                break;
            case 7:
                flavor_2 = "Green";
                break;
            case 8:
                flavor_2 = "Floral";
                break;
            case 9:
                flavor_2 = "Fruity";
                break;
            case 10:
                flavor_2 = "Spicy";
                break;
            case 11:
                flavor_2 = "Woody";
                break;
            case 12:
                flavor_2 = "None";
                break;
        }

        switch (ur.getFlavor3()) {
            case 1:
                flavor_3 = "Aldehyde";
                break;
            case 2:
                flavor_3 = "Animalic";
                break;
            case 3:
                flavor_3 = "Aromatic";
                break;
            case 4:
                flavor_3 = "Balsam";
                break;
            case 5:
                flavor_3 = "Chypre";
                break;
            case 6:
                flavor_3 = "Citrus";
                break;
            case 7:
                flavor_3 = "Green";
                break;
            case 8:
                flavor_3 = "Floral";
                break;
            case 9:
                flavor_3 = "Fruity";
                break;
            case 10:
                flavor_3 = "Spicy";
                break;
            case 11:
                flavor_3 = "Woody";
                break;
            case 12:
                flavor_3 = "None";
                break;
        }

        result_flavors.setText(flavor_1 + ", " + flavor_2 + ", " + flavor_3);

    }
}