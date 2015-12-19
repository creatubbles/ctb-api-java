package com.creatubbles.api.request.creator;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.Creator;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creator.CreateCreatorResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
public class CreateCreatorRequest extends CreatubblesRequest<CreateCreatorResponse> {

    public CreateCreatorRequest(String accessToken, Creator creator) {
        super(String.format(EndPoints.USERS_CREATORS, "me"), HttpMethod.POST, accessToken);
        setData(CreatubblesAPI.GSON.toJson(creator));
    }

    @Override
    public Class<? extends CreateCreatorResponse> getResponseClass() {
        return CreateCreatorResponse.class;
    }
}
