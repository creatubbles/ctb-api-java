package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.CreateCreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreateCreationRequest extends CreatubblesRequest<CreateCreationResponse> {

    public CreateCreationRequest(String accessToken) {
        super(EndPoints.CREATIONS.getTemplate(), HttpMethod.POST, accessToken);
    }

    @Override
    public Class<? extends CreateCreationResponse> getResponseClass() {
        return CreateCreationResponse.class;
    }
}
