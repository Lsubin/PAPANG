package com.papang.perfume.data;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataService {

    private String BASE_URL = "http://3.130.237.74:8888/"; // TODO REST API 퍼블릭 IP로 변경

    Retrofit retrofitClient =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .client(new OkHttpClient.Builder().build())
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

    public Retrofit getRetrofitClient(){
        return retrofitClient;
    }
}