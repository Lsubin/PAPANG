package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    ImageButton nextQ;
    ImageButton check_Result_btn;

    int mCurrentPosition;

    public static Context context;

    // 각 페이지의 state, result를 저장하는 배열 선언 및 초기화
    String[] q_state = new String[7];
    String[] q_result = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        context = this;

        final ViewPager questionPager = (ViewPager)findViewById(R.id.question_View);
        final QuestionPagerAdapter qAdapter = new QuestionPagerAdapter(getSupportFragmentManager());
        questionPager.setAdapter(qAdapter);

        // 좌우스크롤 막아버림
        questionPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

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

        nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 넥스트 버튼이 활성화 된 상태라면
                if(nextQ.getDrawable().getConstantState().equals(getResources().getDrawable(R.mipmap.nextbtn_c).getConstantState())){
                    if(mCurrentPosition == 2){
                        Question4 question4 = new Question4(q_result[2]);
                        qAdapter.addPage(question4);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 3){
                        Question5 question5 = new Question5(q_result[3]);
                        qAdapter.addPage(question5);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 4){
                        Question6 question6 = new Question6();
                        qAdapter.addPage(question6);
                        qAdapter.notifyDataSetChanged();
                    }
                    else if(mCurrentPosition == 5){
                        nextQ.setVisibility(View.INVISIBLE);
                        check_Result_btn.setVisibility(View.VISIBLE);
                        Question7 question7 = new Question7();
                        qAdapter.addPage(question7);
                        qAdapter.notifyDataSetChanged();
                    }
                    questionPager.setCurrentItem(mCurrentPosition+1, true);
                    nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn));
                }
            }
        });
    }

    // 넥스트 버튼을 활성화 시키기 위한 함수
    public void nextPage(int index, Boolean state, String result){
        if(state == true && mCurrentPosition == index){
            q_state[index] = state.toString();
            q_result[index] = result;
            nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn_c));
        }
        else{
            nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn));
            q_state[index] = state.toString();
            q_result[index] = result;
        }
    }
}