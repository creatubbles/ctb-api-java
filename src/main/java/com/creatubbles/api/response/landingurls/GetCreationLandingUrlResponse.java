package com.creatubbles.api.response.landingurls;

import lombok.Getter;
import lombok.ToString;

import com.creatubbles.api.core.CreatubblesResponse;

@Getter
@ToString(callSuper = true)
public class GetCreationLandingUrlResponse extends CreatubblesResponse {

    private String url;

}
