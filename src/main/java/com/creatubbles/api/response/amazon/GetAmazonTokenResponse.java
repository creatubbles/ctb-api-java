package com.creatubbles.api.response.amazon;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Credentials;

@APIVersion(1)
public class GetAmazonTokenResponse extends CreatubblesResponse {
    public String error, expires_at;
    public Credentials credentials;
}
