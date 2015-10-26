package com.creatubbles.api.request.user;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class UserProfileRequest extends CreatubblesRequest<UserProfileResponse> {

    public UserProfileRequest() {
        this("me");
    }

    public UserProfileRequest(String id) {
        super(String.format(EndPoints.USERS_PROFILE, id), HttpMethod.GET);
    }

    public UserProfileRequest(String id, String accessToken) {
        super(String.format(EndPoints.USERS_PROFILE, id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserProfileResponse> getResponseClass() {
        return UserProfileResponse.class;
    }
}
