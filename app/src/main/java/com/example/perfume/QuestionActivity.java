package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

public class QuestionActivity extends AppCompatActivity {

    ImageButton nextQ;
    int mCurrentPosition;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        final ViewPager questionPager = (ViewPager)findViewById(R.id.question_View);
        final QuestionPagerAdapter qAdapter = new QuestionPagerAdapter(getSupportFragmentManager());
        questionPager.setAdapter(qAdapter);

        questionPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;
                Toast.makeText(getApplicationContext(), String.valueOf(mCurrentPosition), 0).show();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        /*
        nextQ = (ImageButton)findViewById(R.id.nextQ);
        nextQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch(mCurrentPosition){
                    case 0 :
                        Question1 mainFragment = (Question1) getFragmentManager().findFragmentById(R.id.);
                        mainFragment.changeFragmentTextView("호호호");
                }
            }
        });
         */
    }

    public static class FlavorAdapter {
    }
}