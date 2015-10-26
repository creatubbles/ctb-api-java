package com.creatubbles.api.request.user;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.user.UserTokenResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class UserTokenRequest extends CreatubblesRequest {

    public UserTokenRequest(String accessToken) {
        super(EndPoints.USER_TOKEN, HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return UserTokenResponse.class;
    }
}
