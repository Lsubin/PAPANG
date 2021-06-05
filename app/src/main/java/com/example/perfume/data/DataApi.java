package com.example.perfume.data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface DataApi {

    // 향수 전체 읽어오는 함수
    @GET("papang_perfume")
    Call<List<Perfume>> selectAll();

    // 파팡 추천 향수가져오는 함수
    @GET("perfume/recommendation/{name}")
    Call<List<Perfume>> selectName(@Path("name") String name);

    // 향수 해시태그 가져오는 함수
    @GET("flavor_hashtag/{flavor}/{flavor2}")
    Call<List<Hashtag>> getFlavorHashtag(@Path("flavor") int flavor,
                                         @Path("flavor2") int flavor2);

    // 향수 추천 결과 받아오는 함수
    @GET("perfume_recommend/{concentration}/{size1}/{size2}/{style}/{main}/{first}/{second}")
    Call<ArrayList<Perfume>> getRecommendationResult(@Path("concentration") int concentration,
                                                     @Path("size1") int size1, @Path("size2") int size2,
                                                     @Path("style") int style, @Path("main") int main,
                                                     @Path("first") int first, @Path("second") int second);

    // 최저가 url 가져오는 함수
    @GET("price_url/{perfumeID}")
    Call<Price> getPerfumeURL(@Path("perfumeID") int perfumeID);

    // 이메일로 사용자 정보 가져오는 함수
    @GET("join/{email}")
    Call<User> getUser(@Path("email") String email);

    // 아이디 찾기할때 휴대폰 번호로 찾는 함수
    @GET("join/search_email/{phone}")
    Call<User> getEmail(@Path("phone") String phone);

    // 사용자 가입하는 함수
    @POST("join/user")
    Call<User> joinUser(@Body Map<String, String> map);

    // 비밀번호 다시 세팅하는 함수
    @POST("join/reset_pw/{email}")
    Call<User> resetPW(@Path("email") String email, @Body Map<String, String> map);

    // 사용자 찜 목록 가져오는 함수
    @GET("wish/{email}")
    Call<List<Wish>> getWishList(@Path("email") String email);

    // 사용자 찜 목록에 업로드하는 함수
    @POST("wish/add")
    Call<Wish> addWishList(@Body Map<String, String> map);

    // 향수 찜 개수 가져오는 함수
    @GET("perfume_wish/{name}")
    Call<PerfumeWish> getPerfumeWish(@Path("name") String name);

    @POST("perfume_wish/add_wish/{name}")
    Call<PerfumeWish> addWishCount(@Path("name") String name, @Body Map<String, String> map);

    @POST("perfume_wish/delete_wish/{name}")
    Call<PerfumeWish> deleteWishCount(@Path("name") String name, @Body Map<String, String> map);

    // 사용자 찜 목록에서 하나 가져오는 함수
    @GET("wish/{email}/{name}")
    Call<Wish> getWisPerfume(@Path("email") String email,
                                 @Path("name") String name);

    // 사용자 찜 목록에서 삭제하는 함수
    @POST("wish/delete/{email}/{name}")
    Call<Integer> deleteWish(@Path("email") String email,
                             @Path("name") String name);
}
