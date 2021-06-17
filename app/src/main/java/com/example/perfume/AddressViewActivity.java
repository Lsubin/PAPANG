package com.example.perfume;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class AddressViewActivity extends AppCompatActivity {

    private WebView addressView;

    class MyJavaScriptInterface
    {
        @JavascriptInterface
        @SuppressWarnings("unused")
        public void processDATA(String data) {
            Bundle extra = new Bundle();
            Intent intent = new Intent();
            extra.putString("data", data);
            intent.putExtras(extra);
            setResult(RESULT_OK, intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_view);

        addressView = (WebView) findViewById(R.id.addressView);
        addressView.getSettings().setJavaScriptEnabled(true);
        addressView.addJavascriptInterface(new MyJavaScriptInterface(), "Android");

        addressView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                addressView.loadUrl("javascript:sample2_execDaumPostcode();");
            }
        });

        addressView.loadUrl("http://3.130.237.74:/daum.html");
    }
}