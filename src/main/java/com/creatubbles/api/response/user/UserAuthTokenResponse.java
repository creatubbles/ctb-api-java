package com.creatubbles.api.response.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;

@APIVersion(1)
public class UserAuthTokenResponse extends CreatubblesResponse {
    public String auth_token;
}
