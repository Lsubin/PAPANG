package com.papang.perfume.Auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AuthData {
    @SerializedName("imp_key")
    String api_key;

    @SerializedName("imp_secret")
    String api_secret;

    AuthData(String api_key, String api_secret){
        this.api_secret = api_secret;
        this.api_key = api_key;
    }
}
