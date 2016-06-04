package com.creatubbles.api.request.landingurls;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.landingurls.GetCreationLandingUrlResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;


public class GetCreationLandingUrlRequest extends CreatubblesRequest<GetCreationLandingUrlResponse> {

    public GetCreationLandingUrlRequest(String creationId, String accessToken) {
        super(EndPoints.CREATION_LANDING_URL.format(creationId), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends GetCreationLandingUrlResponse> getResponseClass() {
        return GetCreationLandingUrlResponse.class;
    }
}
