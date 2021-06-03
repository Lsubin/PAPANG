package com.example.perfume.data;

import java.lang.reflect.Member;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class DataService {

    private String BASE_URL = "http://192.168.91.147:8888/"; // TODO REST API 퍼블릭 IP로 변경

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