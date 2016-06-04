package com.creatubbles.api.request.landingurls;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.LandingUrl;
import com.creatubbles.api.response.landingurls.GetSpecificLandingUrlResponse;
import com.creatubbles.api.util.HttpMethod;

import static com.creatubbles.api.util.EndPoints.SPECIFIC_LANDING_URL;

/**
 * Created by Jevgeni on 09.03.2016.
 */
@APIVersion(2)
public class GetSpecificLandingUrlRequest extends CreatubblesRequest<GetSpecificLandingUrlResponse> {

    public GetSpecificLandingUrlRequest(String accessToken, LandingUrl.LandingUrlType type) {
        super(SPECIFIC_LANDING_URL.format(type), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends GetSpecificLandingUrlResponse> getResponseClass() {
        return GetSpecificLandingUrlResponse.class;
    }
}
