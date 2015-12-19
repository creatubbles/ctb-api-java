package com.creatubbles.api.response.auth;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;

@APIVersion(1)
public class SignInResponse extends CreatubblesResponse {
    public String access_token;
}
