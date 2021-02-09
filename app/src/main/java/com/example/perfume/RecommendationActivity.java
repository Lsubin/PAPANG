package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class RecommendationActivity extends AppCompatActivity {
    ConstraintLayout recommendation_layout;
    ImageButton recommendation_exit;
    ImageButton recommendation_start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        Window w = getWindow();
        w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
                WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        recommendation_layout = (ConstraintLayout)findViewById(R.id.recommendation_layout);
        recommendation_layout.setPadding(0, getStatusBarHeight(getApplicationContext()), 0, 0);

        recommendation_exit = (ImageButton)findViewById(R.id.recommendation_exit);
        recommendation_exit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
        });

        recommendation_start = (ImageButton)findViewById(R.id.recommendation_start);
        recommendation_start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent question = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(question);
            }
        });
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0)
            result = getResources().getDimensionPixelSize(resourceId);
        return result;
    }
}