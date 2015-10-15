package com.creatubbles.api.request;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class UserTokenRequest extends CreatubblesRequest {

    public UserTokenRequest(String accessToken) {
        super(EndPoints.USER_TOKEN, HttpMethod.GET, accessToken);
    }
}
