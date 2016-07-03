package com.creatubbles.api.response.landingurls;

import lombok.Getter;

import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.LandingUrl;

@Getter
public class GetSpecificLandingUrlResponse extends CreatubblesResponse {

    private LandingUrl url;
}
