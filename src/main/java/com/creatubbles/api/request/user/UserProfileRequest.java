package com.creatubbles.api.request.user;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.user.UserProfileResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class UserProfileRequest extends CreatubblesRequest<UserProfileResponse> {

    public UserProfileRequest(String accessToken) {
        super(String.format(EndPoints.USERS_PROFILE, "me"), HttpMethod.GET, accessToken);
    }

    public UserProfileRequest(String id, String accessToken) {
        super(String.format(EndPoints.USERS_PROFILE, id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserProfileResponse> getResponseClass() {
        return UserProfileResponse.class;
    }
}
