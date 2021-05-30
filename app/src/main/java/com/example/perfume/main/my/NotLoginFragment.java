package com.example.perfume.main.my;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.perfume.JoinActivity;
import com.example.perfume.R;
import com.example.perfume.main.my.MyPageFragment;
import com.kakao.sdk.auth.model.OAuthToken;
import com.kakao.sdk.user.UserApiClient;
import com.kakao.sdk.user.model.User;

import kotlin.Unit;
import kotlin.jvm.functions.Function2;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NotLoginFragment extends Fragment {
    View view;

    MyPageFragment fragment;
    ImageButton goto_join_btn, goto_login_btn, kakao_btn;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NotLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NotLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NotLoginFragment newInstance(String param1, String param2) {
        NotLoginFragment fragment = new NotLoginFragment();
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
        view = inflater.inflate(R.layout.fragment_not_login, container, false);

        goto_join_btn = (ImageButton) view.findViewById(R.id.goto_join_btn);
        goto_login_btn = (ImageButton) view.findViewById(R.id.goto_login_btn);

        fragment = new MyPageFragment();

        // 회원가입 화면으로 이동
        goto_join_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), JoinActivity.class);
                startActivity(intent);
            }
        });

        /*
        kakao_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserApiClient.getInstance().loginWithKakaoTalk(view.getContext(), new Function2<OAuthToken, Throwable, Unit>() {
                    @Override
                    public Unit invoke(OAuthToken oAuthToken, Throwable throwable) {
                        if(throwable != null)
                            Log.e("로그인 실패", throwable.getMessage());
                        else
                            Log.d("로그인", "성공");

                        UserApiClient.getInstance().me(new Function2<User, Throwable, Unit>() {
                            @Override
                            public Unit invoke(User user, Throwable throwable) {
                                if(throwable != throwable)
                                    Log.e("사용자 정보 요청 실패", throwable.getMessage());
                                else
                                    Log.i("사용자 정보 요청 성공", user.toString());
                                return null;
                            }
                        });
                        return null;
                    }
                });

            }
        });

        // 임시로 로그인 화면 이동!
        google_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.fragment_layout, fragment)
                        .commit();
            }
        });
         */
        return view;
    }
}