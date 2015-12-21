package com.creatubbles.api.request.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.PingCreationsUploadsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

/**
 * Created by Jevgeni on 22.12.2015.
 */
@APIVersion(2)
public class PingCreationsUploadsRequest extends CreatubblesRequest<PingCreationsUploadsResponse> {

    public PingCreationsUploadsRequest(int uploadId,  String accessToken) {
        super(String.format(EndPoints.PING_CREATIONS_UPLOADS, uploadId), HttpMethod.PUT, accessToken);
    }

    @Override
    public Class<? extends PingCreationsUploadsResponse> getResponseClass() {
        return PingCreationsUploadsResponse.class;
    }
}
