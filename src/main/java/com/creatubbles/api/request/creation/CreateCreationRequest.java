package com.creatubbles.api.request.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class CreateCreationRequest extends CreatubblesRequest<CreateCreationResponse> {


    public CreateCreationRequest(String accessToken) {
        super(EndPoints.CREATIONS, HttpMethod.POST, accessToken);
    }

    @Override
    public Class<? extends CreateCreationResponse> getResponseClass() {
        return CreateCreationResponse.class;
    }
}
