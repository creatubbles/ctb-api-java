package com.creatubbles.api.core;

import lombok.ToString;
import lombok.Value;

import com.google.gson.annotations.SerializedName;

@Value
@ToString(callSuper = true)
public class User extends CreatubblesObject {
    
    String age;
    @SerializedName("birth_month")
    String birthMonth;
    @SerializedName("birth_year")
    String birthYear;
    
    String username;
    @SerializedName("display_name")
    String displayName;
    String email, role;
    
    @SerializedName("country_code")
    String countryCode;
    @SerializedName("country_name")
    String countryName;
    
    @SerializedName("created_at")
    String createdDate;
    @SerializedName("short_url")
    String shortUrl;
    @SerializedName("avatar_url")
    String avatarUrl;
    
    String gender, interests;
}
