package com.creatubbles.api.request.creator;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreateCreatorRequest extends CreatubblesRequest {

    public CreateCreatorRequest(String accessToken, Creator creator) {
        super(String.format(EndPoints.USERS_CREATORS, "me"), HttpMethod.POST, accessToken);
        setData(CreatubblesAPI.GSON.toJson(creator));
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return CreateCreatorResponse.class;
    }
}
