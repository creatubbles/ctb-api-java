package com.creatubbles.api.response.amazon;

import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.core.Creditional;

public class GetAmazonTokenResponse extends CreatubblesResponse {
    public String error, expires_at;
    public Creditional creditionals;
}
