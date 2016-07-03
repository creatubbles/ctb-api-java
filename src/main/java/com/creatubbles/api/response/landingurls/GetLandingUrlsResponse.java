package com.creatubbles.api.response.landingurls;

import lombok.Getter;

import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.LandingUrl;
import com.creatubbles.api.response.ArrayResponse;

@Getter
@ArrayResponse
public class GetLandingUrlsResponse extends CreatubblesResponse {

    private LandingUrl url;
}