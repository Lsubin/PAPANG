package com.example.perfume;

import android.app.Dialog;
import android.content.Context;
import android.gesture.GestureUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;

public class CustomGuideDialog {

    private Context context;
    private ImageButton exit_btn;
    private ImageView guide_img;

    public CustomGuideDialog(Context context) {
        this.context = context;
    }

    public void ShowDialog(int mCurrentPosition) {

        final Dialog dlg = new Dialog(context);
        // 액티비티의 타이틀바를 숨긴다.
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 커스텀 다이얼로그의 레이아웃을 설정한다.
        dlg.setContentView(R.layout.custom_guide_dialog);
        GuidePosition(mCurrentPosition, dlg);
        // 커스텀 다이얼로그를 노출한다.
        dlg.show();

        exit_btn = (ImageButton) dlg.findViewById(R.id.exit_btn);
        exit_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dlg.dismiss();
            }
        });
    }

    private void GuidePosition(int mCurrentPosition, Dialog dlg) {
        guide_img = (ImageView) dlg.findViewById(R.id.guide_img);

        if (mCurrentPosition == 0) {
            guide_img.setImageResource(R.mipmap.guide_book_3_1);
        }
        if (mCurrentPosition == 1) {
            guide_img.setImageResource(R.mipmap.guide_book_4_1);
        }
        if (mCurrentPosition == 3) {
            guide_img.setImageResource(R.mipmap.guide_img);
        }
        if (mCurrentPosition == 4) {
            guide_img.setImageResource(R.mipmap.guide_img);
        }
        if (mCurrentPosition == 5) {
            guide_img.setImageResource(R.mipmap.guide_img);
        }
    }

}
