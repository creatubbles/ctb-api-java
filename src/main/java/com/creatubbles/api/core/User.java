package com.creatubbles.api.core;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import com.google.gson.annotations.SerializedName;

@ToString(callSuper = true)
@EqualsAndHashCode
@Getter
public class User extends CreatubblesObject {
    
    private String age;
    @SerializedName("birth_month")
    private String birthMonth;
    @SerializedName("birth_year")
    private String birthYear;
    
    private String username;
    @SerializedName("display_name")
    private String displayName;
    private String email, role;
    
    @SerializedName("country_code")
    private String countryCode;
    @SerializedName("country_name")
    private String countryName;
    
    @SerializedName("created_at")
    private String createdDate;
    @SerializedName("short_url")
    private String shortUrl;
    @SerializedName("avatar_url")
    private String avatarUrl;
    
    private String gender, interests;
}
