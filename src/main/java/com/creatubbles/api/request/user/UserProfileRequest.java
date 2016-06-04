package com.creatubbles.api.request.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class UserProfileRequest extends CreatubblesRequest<UserProfileResponse> {

    public UserProfileRequest(String accessToken) {
        this("me", accessToken);
    }

    public UserProfileRequest(String id, String accessToken) {
        super(EndPoints.USERS_PROFILE.format(id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserProfileResponse> getResponseClass() {
        return UserProfileResponse.class;
    }
}
