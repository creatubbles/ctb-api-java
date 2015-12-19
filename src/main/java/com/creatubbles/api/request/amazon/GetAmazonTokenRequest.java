package com.creatubbles.api.request.amazon;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.amazon.GetAmazonTokenResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
public class GetAmazonTokenRequest extends CreatubblesRequest<GetAmazonTokenResponse> {

    public GetAmazonTokenRequest(String accessToken) {
        super(EndPoints.AWS_TOKEN, HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends GetAmazonTokenResponse> getResponseClass() {
        return GetAmazonTokenResponse.class;
    }
}
