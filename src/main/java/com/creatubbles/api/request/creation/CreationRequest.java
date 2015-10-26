package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.CreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreationRequest extends CreatubblesRequest {

    public CreationRequest() {
        this("me", null);
    }

    public CreationRequest(String accessToken) {
        this("me", accessToken);
    }

    public CreationRequest(String id, String accessToken) {
        super(String.format(EndPoints.CREATORS_CREATIONS, id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return CreationResponse.class;
    }
}
