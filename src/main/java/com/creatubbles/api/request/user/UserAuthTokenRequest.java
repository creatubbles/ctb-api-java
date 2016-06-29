package com.creatubbles.api.request.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.user.UserAuthTokenResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
@Deprecated
public class UserAuthTokenRequest extends CreatubblesRequest<UserAuthTokenResponse> {

    public UserAuthTokenRequest(String accessToken) {
        super(EndPoints.AUTH_TOKEN, HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserAuthTokenResponse> getResponseClass() {
        return UserAuthTokenResponse.class;
    }
}