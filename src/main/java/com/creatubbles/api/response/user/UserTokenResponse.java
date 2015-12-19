package com.creatubbles.api.response.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;

@APIVersion(1)
public class UserTokenResponse extends CreatubblesResponse {
    public String user_token;
}
