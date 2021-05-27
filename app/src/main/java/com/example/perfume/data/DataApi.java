package com.example.perfume.data;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataApi {

    @GET("papang_perfume")
    Call<List<Perfume>> selectAll();

    @GET("perfume/recommendation/{name}")
    Call<List<Perfume>> selectName(@Path("name") String name);

    @GET("flavor_hashtag/{flavor}/{flavor2}")
    Call<List<Hashtag>> getFlavorHashtag(@Path("flavor") int flavor,
                                         @Path("flavor2") int flavor2);

    @GET("perfume_recommend//{concentration}/{size1}/{size2}/{style}/{main}/{first}/{second}")
    Call<List<Perfume>> getRecommendationResult(@Path("concentration") int concentration,
                                                @Path("size1") int size1, @Path("size2") int size2,
                                                @Path("style") int style, @Path("main") int main,
                                                @Path("first") int first, @Path("second") int second);
}
