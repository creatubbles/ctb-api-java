package com.creatubbles.api.request.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class CreationsUploadsRequest extends CreatubblesRequest<CreationsUploadsResponse> {

    public CreationsUploadsRequest(String creationId, String accessToken) {
        super(String.format(EndPoints.CREATIONS_UPLOAD, creationId), HttpMethod.POST, accessToken);
    }

    @Override
    public Class<? extends CreationsUploadsResponse> getResponseClass() {
        return CreationsUploadsResponse.class;
    }
}
