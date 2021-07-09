package com.papang.perfume.recommendation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.papang.perfume.AllResultProductActivity;
import com.papang.perfume.CustomGuideDialog;
import com.papang.perfume.NoResultActivity;
import com.papang.perfume.R;
import com.papang.perfume.adapter.QuestionPagerAdapter;
import com.papang.perfume.custom.NonSwipeViewPager;
import com.papang.perfume.data.DataApi;
import com.papang.perfume.data.DataService;
import com.papang.perfume.data.Perfume;
import com.papang.perfume.data.UserRecommendation;
import com.papang.perfume.recommendation.Question4;
import com.papang.perfume.recommendation.Question5;
import com.papang.perfume.recommendation.Question6;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/*
순서가 3, 4, 5, 6, 2로 수정
q_state, q_result 다 순서에 맞춰서 수정
Question3 = q_state, q_result[0], mCurrentPage = 0
Question4 = q_state, q_result[1], mCurrentPage = 1
Question5 = q_state, q_result[2], mCurrentPage = 2
Question6 = q_state, q_result[3], mCurrentPage = 3
Question2 = q_state, q_result[4], mCurrentPage = 4
 */
public class QuestionActivity extends AppCompatActivity {

    ImageButton nextQ;
    ImageButton check_Result_btn;
    ImageButton q_back_btn;
    ImageButton guide_btn;

    DataService dataService;
    DataApi dataApi;
    ArrayList<Perfume> perfumes;
    ArrayList<String> perfumeInfos;

    int mCurrentPosition;

    String email;

    public static Context context;

    QuestionPagerAdapter qAdapter;
    NonSwipeViewPager questionPager;

    // 각 페이지의 state, result를 저장하는 배열 선언 및 초기화
    public String[] q_result = new String[5];
    public Boolean[] q_state = {false, false, false, false, false};

    // 뒤로가기 키 누를 시
    // 마지막으로 뒤로가기 버튼을 눌렀던 시간 저장
    private long backKeyPressedTime = 0;
    // 첫 번째 뒤로가기 버튼을 누를때 표시
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        context = this;
        checkLogin();

        dataService = new DataService();
        dataApi =  dataService.getRetrofitClient().create(DataApi.class);

        questionPager = (NonSwipeViewPager)findViewById(R.id.question_View);
        qAdapter = new QuestionPagerAdapter(getSupportFragmentManager());
        questionPager.setAdapter(qAdapter);

        // 페이지 바뀔때 불러와지는 리스너
        questionPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            //이건 안쓸거임(스크롤 기능)
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;;
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        nextQ = (ImageButton)findViewById(R.id.nextQ);
        check_Result_btn = (ImageButton)findViewById(R.id.check_Result_btn);
        guide_btn = (ImageButton)findViewById(R.id.guide_btn);

        // 가이드 북 클릭 시
        guide_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomGuideDialog dlg = new CustomGuideDialog(context);
                dlg.ShowDialog(mCurrentPosition);

            }
        });

        // 다음 페이지로 넘어가는 Next 버튼 클릭 이벤트
        nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 넥스트 버튼이 활성화 된 상태라면
                if(nextQ.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.nextbtn_c).getConstantState())){
                    if(mCurrentPosition == 0 && !q_state[mCurrentPosition].equals(false)){
                        guide_btn.setVisibility(View.VISIBLE);
                        Question4 question4 = new Question4(q_result[0]);
                        qAdapter.addPage(question4);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 1 && !q_state[mCurrentPosition].equals(false)){
                        Question5 question5 = new Question5(q_result[1]);
                        qAdapter.addPage(question5);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 2 && !q_state[mCurrentPosition].equals(false)){
                        Question6 question6 = new Question6();
                        qAdapter.addPage(question6);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 3 && !q_state[mCurrentPosition].equals(false)){
                        Question2 question2 = new Question2();
                        nextQ.setVisibility(View.INVISIBLE);
                        check_Result_btn.setVisibility(View.VISIBLE);
                        qAdapter.addPage(question2);
                        qAdapter.notifyDataSetChanged();
                    }
                    /*
                    else if(mCurrentPosition == 4 && q_state[mCurrentPosition + 1].equals(false)){
                        Question6 question6 = new Question6();
                        nextQ.setVisibility(View.INVISIBLE);
                        qAdapter.addPage(question6);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 5 && q_state[mCurrentPosition + 1].equals(false)){
                        guide_btn.setVisibility(View.GONE);
                        nextQ.setVisibility(View.INVISIBLE);
                        check_Result_btn.setVisibility(View.VISIBLE);
                        Question7 question7 = new Question7();
                        qAdapter.addPage(question7);
                        qAdapter.notifyDataSetChanged();
                    }
                     */
                    questionPager.setCurrentItem(mCurrentPosition+1, false);
                    nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn));
                }
            }
        });

        check_Result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfumeInfos = new ArrayList<>();
                perfumeInfos.add(q_result[0]);      // 스타일
                perfumeInfos.add(q_result[1]);      // 메인향료
                perfumeInfos.add(q_result[2]);      // 추가향료1
                perfumeInfos.add(q_result[3]);      // 추가향료2
                perfumeInfos.add(q_result[4]);      // 용령

                // 선택안함 클릭했을 때 - 15!
                if(q_result[3].equals("15"))
                    getPerfumeExclude(perfumeInfos);
                else
                    getPerfume(perfumeInfos);
            }
        });

        /*
        // 이전 페이지로 넘어가는 뒤로가기 버튼 클릭 이벤트
        q_back_btn = (ImageButton)findViewById(R.id.q_back_btn);
        q_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(mCurrentPosition){
                    case 0:
                        finish();       // 0일때는 맨 처음 페이지이기 때문에 아예 Question Activity를 꺼줘야 한다.
                        break;
                    case 1:
                        questionPager.setCurrentItem(0, false);
                        nextPageOn();
                        break;
                    case 2:      // 여기서부터는 버튼들이 유기적으로 연결되어 있기 때문에 2,3,4를 동적으로 추가/삭제해야된다.
                        questionPager.setCurrentItem(1, false);
                        nextPageOn();
                        break;
                    case 3:
                        questionPager.setCurrentItem(2, false);
                        nextPageOn();
                        break;
                    case 4:
                        questionPager.setCurrentItem(3, false);
                        nextPageOn();
                        break;
                    case 5:
                        questionPager.setCurrentItem(4, false);
                        nextQ.setVisibility(View.VISIBLE);
                        check_Result_btn.setVisibility(View.INVISIBLE);
                        nextPageOn();
                        break;

                        /* 가격 체크하는 Question7은 베타에서 사용하지 않기로 결정 - 주석 처리
                    case 6:
                        questionPager.setCurrentItem(5, false);
                        nextQ.setVisibility(View.VISIBLE);
                        check_Result_btn.setVisibility(View.INVISIBLE);
                        nextPageOn();
                        break;
                }
            }
        }); */
    }

    // 넥스트 버튼을 활성화 시키기 위한 함수
    public void nextPage(int index, Boolean state, String result){
        if(state == true && mCurrentPosition == index){
            q_state[index] = state;
            q_result[index] = result;
            nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn_c));
        }
        else{
            nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn));
            q_state[index] = state;
            q_result[index] = result;
        }
        if(state == true && mCurrentPosition == 4){
            check_Result_btn.setVisibility(View.VISIBLE);
        }
        else if(state == false && mCurrentPosition == 4){
            check_Result_btn.setVisibility(View.INVISIBLE);
        }
    }

    // 넥스트 버튼을 활성화 시키기 위한 함수
    public void nextPageOn(){
        nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn_c));
    }

    public void deletePage(int index){
        qAdapter.deletePage(index);
        for(int i = index+1; i < 8; i++){
            q_state[i] = false;
            q_result[i] = "";
        }
    }

    public ArrayList<Integer> changeSize(String size){
        ArrayList<Integer> sizes = new ArrayList<>();

        switch (size) {
            case "size1":
                sizes.add(0);
                sizes.add(50);
                break;
            case "size2":
                sizes.add(0);
                sizes.add(100);
                break;
            case "size3":
                sizes.add(0);
                sizes.add(500);
                break;
        }
        return sizes;
    }

    /*
    public int changeConcentration(String concentration){
        int concentrations = 0;

        switch (concentration) {
            case "ode_c":
                concentrations = 1;
                break;
            case "ode_d":
                concentrations = 2;
                break;
            case "ode_p":
                concentrations = 3;
                break;
            case "ode_pp":
                concentrations = 4;
                break;
        }
        return concentrations;
    }
    */

    private void checkLogin(){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Info", MODE_PRIVATE);    // test 이름의 기본모드 설정, 만약 test key값이 있다면 해당 값을 불러옴.
        email = sharedPreferences.getString("Email","");
    }

    public void getPerfumeExclude(final ArrayList<String> perfumeInfos){
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes = changeSize(perfumeInfos.get(4));
        //int concentration = changeConcentration(perfumeInfos.get(0));
        dataApi.getExcludeRecommendationResult(Integer.parseInt(perfumeInfos.get(0)), Integer.parseInt(perfumeInfos.get(1)),
                Integer.parseInt(perfumeInfos.get(2)), sizes.get(0), sizes.get(1)).enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ArrayList<String> perfumes = response.body();
                if(perfumes.size() > 0) {
                    Intent result = new Intent(getApplicationContext(), AllResultProductActivity.class);
                    result.putExtra("결과", perfumes);
                    result.putExtra("정보", perfumeInfos);
                    if(!email.equals(""))
                        setUserRecommend();
                    startActivity(result);
                    finish();
                }
                else{
                    Intent result = new Intent(getApplicationContext(), NoResultActivity.class);
                    startActivity(result);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("연결", t.getMessage());
            }
        });
    }

    public void getPerfume(final ArrayList<String> perfumeInfos){
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes = changeSize(perfumeInfos.get(4));
        //int concentration = changeConcentration(perfumeInfos.get(0));

        dataApi.getRecommendationResult(Integer.parseInt(perfumeInfos.get(0)), Integer.parseInt(perfumeInfos.get(1)),
                Integer.parseInt(perfumeInfos.get(2)), Integer.parseInt(perfumeInfos.get(3)), sizes.get(0), sizes.get(1)).enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(Call<ArrayList<String>> call, Response<ArrayList<String>> response) {
                ArrayList<String> perfumes = response.body();
                if(perfumes.size() > 0) {
                    Intent result = new Intent(getApplicationContext(), AllResultProductActivity.class);
                    result.putExtra("결과", perfumes);
                    result.putExtra("정보", perfumeInfos);
                    if(!email.equals(""))
                        setUserRecommend();
                    startActivity(result);
                    finish();
                }
                else{
                    Intent result = new Intent(getApplicationContext(), NoResultActivity.class);
                    startActivity(result);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<String>> call, Throwable t) {
                Log.e("연결", t.getMessage());
            }
        });
    }

    private void setUserRecommend(){
        dataApi.getUserRecommend(email).enqueue(new Callback<UserRecommendation>() {
            @Override
            public void onResponse(Call<UserRecommendation> call, Response<UserRecommendation> response) {
                UserRecommendation ur;
                ur = response.body();
                changeRecommend(email);
            }

            @Override
            public void onFailure(Call<UserRecommendation> call, Throwable t) {
                saveRecommend();
            }
        });
    }

    public void changeRecommend(String email){
        Map<String, String> user_recommend = new HashMap<>();
        user_recommend.put("email", email);
        user_recommend.put("style", perfumeInfos.get(0));
        user_recommend.put("flavor1", perfumeInfos.get(1));
        user_recommend.put("flavor2", perfumeInfos.get(2));
        user_recommend.put("flavor3", perfumeInfos.get(3));
        user_recommend.put("size", perfumeInfos.get(4));
        dataApi.changeUserRecommend(email, user_recommend).enqueue(new Callback<UserRecommendation>() {
            @Override
            public void onResponse(Call<UserRecommendation> call, Response<UserRecommendation> response) {

            }

            @Override
            public void onFailure(Call<UserRecommendation> call, Throwable t) {

            }
        });
    }

    public void saveRecommend(){
        Map<String, String> user_recommend = new HashMap<>();
        user_recommend.put("email", email);
        user_recommend.put("style", perfumeInfos.get(0));
        user_recommend.put("flavor1", perfumeInfos.get(1));
        user_recommend.put("flavor2", perfumeInfos.get(2));
        user_recommend.put("flavor3", perfumeInfos.get(3));
        user_recommend.put("size", perfumeInfos.get(4));

        dataApi.addUserRecommend(user_recommend).enqueue(new Callback<UserRecommendation>() {
            @Override
            public void onResponse(Call<UserRecommendation> call, Response<UserRecommendation> response) {
            }

            @Override
            public void onFailure(Call<UserRecommendation> call, Throwable t) {

            }
        });
    }


    // 뒤로가기 키 2번 누를 시 -> 질문 종료
    @Override
    public void onBackPressed() {
        // 기존 뒤로가기 버튼의 기능을 막기위해 주석처리 또는 삭제
        // super.onBackPressed();

        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지났으면 Toast Show
        // 2000 milliseconds = 2 seconds
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            toast = Toast.makeText(this, "\'뒤로\' 버튼을 한번 더 누르시면 질문이 종료됩니다.", Toast.LENGTH_SHORT);
            toast.show();
            return;
        }
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간에 2초를 더해 현재시간과 비교 후
        // 마지막으로 뒤로가기 버튼을 눌렀던 시간이 2초가 지나지 않았으면 종료
        // 현재 표시된 Toast 취소
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            finish();
            toast.cancel();
        }
    }
}