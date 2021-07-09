package com.papang.perfume.custom;

import android.app.Activity;
import android.content.Intent;
import android.text.TextUtils;
import android.webkit.JavascriptInterface;

public class SmsAuthFactory extends Activity {

    private String TAG = "SmsAuthFactory";
    private Activity activity = this;

    @JavascriptInterface
    public void resultAuth(String message, String impKey){
        Intent intent = new Intent();

        if(message == "success" && !TextUtils.isEmpty(impKey)) {
            intent.putExtra("result","success");
            intent.putExtra("imp_key", impKey);
            activity.setResult(RESULT_OK,intent);
            activity.finish();
        }
        else{
            intent.putExtra("result","failure");
            activity.setResult(RESULT_OK,intent);
            activity.finish();
        }
    }
}
