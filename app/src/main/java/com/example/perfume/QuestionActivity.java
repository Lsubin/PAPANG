package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    ImageButton nextQ;
    ImageButton check_Result_btn;
    ImageButton q_back_btn;
    ImageButton guide_btn;

    int mCurrentPosition;

    public static Context context;

    // 각 페이지의 state, result를 저장하는 배열 선언 및 초기화
    public String[] q_state = new String[7];
    public String[] q_result = new String[7];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        context = this;

        final NonSwipeViewPager questionPager = (NonSwipeViewPager)findViewById(R.id.question_View);
        final QuestionPagerAdapter qAdapter = new QuestionPagerAdapter(getSupportFragmentManager());
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
                    if(mCurrentPosition == 1){
                        guide_btn.setVisibility(View.GONE);
                    }
                    if(mCurrentPosition == 2){
                        guide_btn.setVisibility(View.VISIBLE);
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
                        guide_btn.setVisibility(View.GONE);
                        nextQ.setVisibility(View.INVISIBLE);
                        check_Result_btn.setVisibility(View.VISIBLE);
                        Question7 question7 = new Question7();
                        qAdapter.addPage(question7);
                        qAdapter.notifyDataSetChanged();
                    }
                    questionPager.setCurrentItem(mCurrentPosition+1, false);
                    nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn));
                }
            }
        });

        check_Result_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), q_result[0] + " /  " +
                        q_result[1] + " / " + q_result[2] + " / " + q_result[3] + " / " +
                        q_result[4] + " / " + q_result[5] + " / " + q_result[6], 0).show();
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
                        nextPageOn();
                        break;
                    case 6:
                        questionPager.setCurrentItem(5, false);
                        nextPageOn();
                        break;
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

    // 넥스트 버튼을 활성화 시키기 위한 함수
    public void nextPageOn(){
        nextQ.setImageDrawable(getResources().getDrawable(R.mipmap.nextbtn_c));
    }
}