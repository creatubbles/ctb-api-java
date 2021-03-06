package com.creatubbles.api.request.landingurls;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.landingurls.GetLandingUrlsResponse;
import com.creatubbles.api.util.HttpMethod;

import static com.creatubbles.api.util.EndPoints.LANDING_URLS;

/**
 * Created by Jevgeni on 09.03.2016.
 */
public class GetLandingUrlsRequest extends CreatubblesRequest<GetLandingUrlsResponse> {

    public GetLandingUrlsRequest(String accessToken) {
        super(LANDING_URLS.getTemplate(), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends GetLandingUrlsResponse> getResponseClass() {
        return GetLandingUrlsResponse.class;
    }
}
