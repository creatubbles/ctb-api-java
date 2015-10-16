package com.creatubbles.api.request.user;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class UserAuthTokenRequest extends CreatubblesRequest {

    public UserAuthTokenRequest(String accessToken) {
        super(EndPoints.AUTH_TOKEN, HttpMethod.GET, accessToken);
    }
}
