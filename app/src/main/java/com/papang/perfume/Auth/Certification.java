package com.papang.perfume.Auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class Certification {

    @SerializedName("code")
    int code;

    @SerializedName("message")
    String message;

    @SerializedName("response")
    CertificationResponse response;
}
