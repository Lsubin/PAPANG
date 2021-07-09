package com.papang.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.papang.perfume.Auth.AuthService;
import com.papang.perfume.JoinActivity;
import com.papang.perfume.R;

public class AuthActivity extends AppCompatActivity {

    public static Context context;

    WebView auth_web_view;
    WebSettings mWebSettings;
    WebChromeClient webChromeClient;
    WebViewClient webViewClient;
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        context = this;

        url = getIntent().getStringExtra("url");
        AuthService authService = new AuthService(context);

        auth_web_view = (WebView)findViewById(R.id.auth_web_view);
        auth_web_view.setWebViewClient(new WebViewClient()); // 클릭시 새창 안뜨게
        mWebSettings = auth_web_view.getSettings(); //세부 세팅 등록
        mWebSettings.setJavaScriptEnabled(true); // 웹페이지 자바스클비트 허용 여부
        mWebSettings.setSupportZoom(false); // 화면 줌 허용 여부
        mWebSettings.setLoadWithOverviewMode(true);
        mWebSettings.setDefaultTextEncodingName("UTF-8");
        mWebSettings.setBuiltInZoomControls(false); // 화면 확대 축소 허용 여부

        auth_web_view.setWebChromeClient(webChromeClient);
        auth_web_view.setWebViewClient(webViewClient);
        auth_web_view.addJavascriptInterface(authService, "Android");
        auth_web_view.loadUrl("file:///android_asset/html/certification.html");
    }

    public void gotoJoin(String name, String phone){
        Intent goto_join = new Intent(getApplicationContext(), JoinActivity.class);
        goto_join.putExtra("name", name);
        goto_join.putExtra("phone", phone);
        startActivity(goto_join);
        auth_web_view.goBack();
        finish();
    }
}