package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.GetCreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;


public class GetCreationRequest extends CreatubblesRequest<GetCreationResponse> {

    public GetCreationRequest(String id, String accessToken) {
        super(EndPoints.CREATION.format(id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends GetCreationResponse> getResponseClass() {
        return GetCreationResponse.class;
    }
}
