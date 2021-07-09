package com.papang.perfume.Auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class AccessTokenResponse {

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("now")
    int now;

    @SerializedName("expired_at")
    int expiredAt;
}
