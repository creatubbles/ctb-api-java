package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.LandingURLResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;


public class LandingURLRequest extends CreatubblesRequest<LandingURLResponse> {

    public LandingURLRequest(String creationId, String accessToken) {
        super(String.format(EndPoints.CREATION_LANDING_URL, creationId), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends LandingURLResponse> getResponseClass() {
        return LandingURLResponse.class;
    }
}
