package com.papang.perfume.Auth;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import com.papang.perfume.AuthActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AuthService {
    Context mContext;

    public AuthService(Context context){
        mContext = context;
    }
    String API_URL = "https://api.iamport.kr";

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(API_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    AuthApi authApi = retrofitClient.create(AuthApi.class);

    @JavascriptInterface
    public void getData(final String impUid){
        Log.e("impUid", impUid);
        String apiKey = "6336739829140985";
        String apiSecretKey = "ZvXrz0fdjBQ2WMUcK6EL3vk6Vzu2JeJIJGajHqx11n3XaYaV5OG9j0feAKhjY02IHc1oDyqEWuC7sM2x";

        AuthData authData = new AuthData(apiKey, apiSecretKey);
        authApi.token(authData).enqueue(new Callback<AccessToken>() {
            @Override
            public void onResponse(Call<AccessToken> call, Response<AccessToken> response) {
                String token = response.body().response.accessToken;
                authApi.certification_by_imp_uid(token, impUid).enqueue(new Callback<Certification>() {
                    @Override
                    public void onResponse(Call<Certification> call, Response<Certification> response) {
                        Log.e("phone", response.body().response.merchant_uid);
                        ((AuthActivity)AuthActivity.context).gotoJoin(response.body().response.name, response.body().response.phone);
                    }

                    @Override
                    public void onFailure(Call<Certification> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onFailure(Call<AccessToken> call, Throwable t) {

            }
        });
    }
}
