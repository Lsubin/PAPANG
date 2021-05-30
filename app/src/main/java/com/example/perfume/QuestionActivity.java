package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.perfume.adapter.QuestionPagerAdapter;
import com.example.perfume.adapter.ResultProductAdpater;
import com.example.perfume.custom.NonSwipeViewPager;
import com.example.perfume.data.DataApi;
import com.example.perfume.data.DataService;
import com.example.perfume.data.Perfume;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public static Context context;

    QuestionPagerAdapter qAdapter;
    NonSwipeViewPager questionPager;

    // 각 페이지의 state, result를 저장하는 배열 선언 및 초기화
    public String[] q_result = new String[8];
    public Boolean[] q_state = {false, false, false, false, false, false, false, false};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        context = this;

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
                    if(mCurrentPosition == 1 && !q_state[mCurrentPosition + 1].equals(false)){
                        guide_btn.setVisibility(View.GONE);
                    }
                    if(mCurrentPosition == 2 && q_state[mCurrentPosition + 1].equals(false)){
                        guide_btn.setVisibility(View.VISIBLE);
                        Question4 question4 = new Question4(q_result[2]);
                        qAdapter.addPage(question4);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 3 && q_state[mCurrentPosition + 1].equals(false)){
                        Question5 question5 = new Question5(q_result[3]);
                        qAdapter.addPage(question5);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 4 && q_state[mCurrentPosition + 1].equals(false)){
                        Question6 question6 = new Question6();
                        nextQ.setVisibility(View.INVISIBLE);
                        qAdapter.addPage(question6);
                        qAdapter.notifyDataSetChanged();
                    }
                    /*
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
                perfumeInfos.add(q_result[0]);
                perfumeInfos.add(q_result[1]);
                perfumeInfos.add(q_result[2]);
                perfumeInfos.add(q_result[3]);
                perfumeInfos.add(q_result[4]);
                perfumeInfos.add(q_result[5]);

                getPerfume(perfumeInfos);
            }
        });

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
                         */
                }
            }
        });
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
        if(state == true && mCurrentPosition == 5){
            check_Result_btn.setVisibility(View.VISIBLE);
        }
        else if(state == false && mCurrentPosition == 5){
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
                sizes.add(15);
                break;
            case "size2":
                sizes.add(15);
                sizes.add(30);
                break;
            case "size3":
                sizes.add(30);
                sizes.add(50);
                break;
            case "size4":
                sizes.add(50);
                sizes.add(75);
                break;
            case "size5":
                sizes.add(75);
                sizes.add(100);
                break;
            case "size6":
                sizes.add(100);
                sizes.add(500);
                break;
        }
        return sizes;
    }

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


    public void getPerfume(final ArrayList<String> perfumeInfos){
        ArrayList<Integer> sizes = new ArrayList<>();
        sizes = changeSize(perfumeInfos.get(1));
        int concentration = changeConcentration(perfumeInfos.get(0));
        Log.e("시작" , concentration + " / " + sizes.get(0) + " / " + sizes.get(1) + " / "  + perfumeInfos.get(2) + perfumeInfos.get(3) + perfumeInfos.get(4) + perfumeInfos.get(5));

        dataApi.getRecommendationResult(concentration, sizes.get(0), sizes.get(1), Integer.parseInt(perfumeInfos.get(2))
                , Integer.parseInt(perfumeInfos.get(3)), Integer.parseInt(perfumeInfos.get(4)), Integer.parseInt(perfumeInfos.get(5))).enqueue(new Callback<ArrayList<Perfume>>() {
            @Override
            public void onResponse(Call<ArrayList<Perfume>> call, Response<ArrayList<Perfume>> response) {
                perfumes = response.body();
                if(perfumes.size() > 0) {
                    Intent result = new Intent(getApplicationContext(), AllResultProductActivity.class);
                    result.putExtra("결과", perfumes);
                    result.putExtra("정보", perfumeInfos);
                    startActivity(result);
                }
                else{
                    Intent result = new Intent(getApplicationContext(), NoResultActivity.class);
                    startActivity(result);
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Perfume>> call, Throwable t) {
                Log.e("연결", t.getMessage());
            }
        });
    }

}