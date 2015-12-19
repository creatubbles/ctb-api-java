package com.creatubbles.api.response.auth;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;

@APIVersion(2)
public class OAuthAccessTokenResponse extends CreatubblesResponse{

    public String access_token;
    public Long expires_in;
    public String token_type;

}
