package com.example.perfume.kakao;

import android.app.Application;

import com.example.perfume.R;
import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {
    private static GlobalApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;

        // Kakao Sdk 초기화
        KakaoSdk.init(this, this.getResources().getString(R.string.kakao_app_key));
    }
}
