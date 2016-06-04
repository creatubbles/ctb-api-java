package com.creatubbles.api.response.auth;

import lombok.Getter;
import lombok.ToString;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.google.gson.annotations.SerializedName;

@APIVersion(2)
@Getter
@ToString(callSuper = true)
public class OAuthAccessTokenResponse extends CreatubblesResponse {

    @SerializedName("access_token")
    private String accessToken;
    @SerializedName("expires_in")
    private Long lifetime;
    @SerializedName("token_type")
    private String type;

}
