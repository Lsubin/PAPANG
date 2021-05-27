package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.perfume.adapter.GuidePagerAdapter;
import com.sothree.slidinguppanel.SlidingUpPanelLayout;

public class RecommendationActivity extends AppCompatActivity {
    ConstraintLayout recommendation_layout;
    ImageButton recommendation_exit;
    ImageButton recommendation_start;
    ImageButton recommendation_guide;
    ConstraintLayout guide_up_pannel;
    SlidingUpPanelLayout recommendation_slidinglayout;
    TextView guide_page_txt;
    TextView guide_page_start;

    int mCurrentPosition;       // 현재 페이지

    ImageButton guide_backpage;
    ImageButton guide_nextpage;
    ImageButton guide_exit;

    ViewPager guide_viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        recommendation_slidinglayout = (SlidingUpPanelLayout) findViewById(R.id.recommendation_slidinglayout);

        recommendation_slidinglayout.getChildAt(1).setOnClickListener(null);

        recommendation_layout = (ConstraintLayout) findViewById(R.id.recommendation_layout);
        recommendation_layout.setPadding(0, getStatusBarHeight(getApplicationContext()), 0, 0);

        guide_viewpager = (ViewPager) findViewById(R.id.guide_View);
        GuidePagerAdapter gAdapter = new GuidePagerAdapter(getApplicationContext());
        guide_viewpager.setAdapter(gAdapter);

        guide_up_pannel = (ConstraintLayout) findViewById(R.id.guide_up_pannel);

        recommendation_exit = (ImageButton) findViewById(R.id.recommendation_exit);
        recommendation_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        recommendation_start = (ImageButton) findViewById(R.id.recommendation_start);
        recommendation_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent question = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(question);
            }
        });

        recommendation_guide = (ImageButton) findViewById(R.id.recommendation_guide);
        recommendation_guide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recommendation_slidinglayout.setPanelState(SlidingUpPanelLayout.PanelState.EXPANDED);
            }
        });

        guide_exit = (ImageButton) findViewById(R.id.guide_exit);
        guide_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recommendation_slidinglayout.setPanelState(SlidingUpPanelLayout.PanelState.HIDDEN);
            }
        });

        guide_backpage = (ImageButton) findViewById(R.id.guide_backpage);        // 이전 페이지
        guide_nextpage = (ImageButton) findViewById(R.id.guide_nextpage);        // 다음 페이지
        guide_page_txt = (TextView) findViewById(R.id.guide_page_txt);           // 페이지 표시 텍스트뷰
        guide_page_start = (TextView) findViewById(R.id.guide_page_start);       // START TEXT

        guide_nextpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (guide_viewpager.getCurrentItem() == 0) {
                    guide_viewpager.setCurrentItem(1);
                    guide_backpage.setVisibility(View.VISIBLE);
                    guide_page_start.setVisibility(View.GONE);
                    guide_page_txt.setVisibility(View.VISIBLE);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 1) {
                    guide_viewpager.setCurrentItem(2);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 2) {
                    guide_viewpager.setCurrentItem(3);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 3) {
                    guide_viewpager.setCurrentItem(4);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 4) {
                    guide_viewpager.setCurrentItem(5);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 5) {
                    guide_viewpager.setCurrentItem(6);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 6) {
                    guide_viewpager.setCurrentItem(7);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                    guide_nextpage.setVisibility(View.GONE);
                }
            }
        });

        guide_backpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (guide_viewpager.getCurrentItem() == 1) {
                    guide_viewpager.setCurrentItem(0);
                    guide_backpage.setVisibility(View.GONE);
                    guide_page_start.setVisibility(View.VISIBLE);
                    guide_page_txt.setVisibility(View.GONE);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 2) {
                    guide_viewpager.setCurrentItem(1);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 3) {
                    guide_viewpager.setCurrentItem(2);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 4) {
                    guide_viewpager.setCurrentItem(3);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 5) {
                    guide_viewpager.setCurrentItem(4);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 6) {
                    guide_viewpager.setCurrentItem(5);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 6) {
                    guide_viewpager.setCurrentItem(5);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                } else if (guide_viewpager.getCurrentItem() == 7) {
                    guide_viewpager.setCurrentItem(6);
                    guide_nextpage.setVisibility(View.VISIBLE);
                    guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
                }
            }
        });

        // 페이지 바뀔 때 불러와지는 함수
        guide_viewpager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mCurrentPosition = position;    // 현재 페이지 설정
                ChangeGuidePage(mCurrentPosition);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


    }

    private void ChangeGuidePage(int mCurrentPosition) {
        if (mCurrentPosition == 0) {
            guide_backpage.setVisibility(View.GONE);
            guide_page_start.setVisibility(View.VISIBLE);
            guide_page_txt.setVisibility(View.GONE);
        } else if (mCurrentPosition == 1) {
            guide_backpage.setVisibility(View.VISIBLE);
            guide_page_start.setVisibility(View.GONE);
            guide_page_txt.setVisibility(View.VISIBLE);
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 2) {
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 3) {
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 4) {
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 5) {
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 6) {
            guide_nextpage.setVisibility(View.VISIBLE);
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        } else if (mCurrentPosition == 7) {
            guide_nextpage.setVisibility(View.GONE);
            guide_page_txt.setText(guide_viewpager.getCurrentItem() + " / 7");
        }
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = getResources().getDimensionPixelSize(resourceId);
        return result;
    }
}