package com.papang.perfume.Auth;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
public class CertificationResponse {

    @SerializedName("imp_uid")
    String imp_uid;

    @SerializedName("merchant_uid")
    String merchant_uid;

    @SerializedName("pg_tid")
    String pg_tid;

    @SerializedName("pg_provider")
    String pg_provider;

    @SerializedName("name")
    String name;

    @SerializedName("phone")
    String phone;

    @SerializedName("carrier")
    String carrier;

    @SerializedName("unique_key")
    String unique_key;

    @SerializedName("unique_in_site")
    String unique_in_site;

    @SerializedName("gender")
    String gender;

    @SerializedName("birthday")
    String birthday;

    @SerializedName("foreigner")
    Boolean foreigner;
}
