package com.papang.perfume.Auth;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface AuthApi {

    @POST("/users/getToken")
    Call<AccessToken> token(@Body AuthData auth);

    @GET("/certifications/{imp_uid}")
    Call<Certification> certification_by_imp_uid(@Header("Authorization") String token,
                                                 @Path("imp_uid") String imp_uid);

}
