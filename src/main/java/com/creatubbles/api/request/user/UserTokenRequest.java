package com.creatubbles.api.request.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.user.UserTokenResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
public class UserTokenRequest extends CreatubblesRequest<UserTokenResponse> {

    public UserTokenRequest(String accessToken) {
        super(EndPoints.USER_TOKEN, HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserTokenResponse> getResponseClass() {
        return UserTokenResponse.class;
    }
}
