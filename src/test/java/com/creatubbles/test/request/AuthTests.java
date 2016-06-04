package com.creatubbles.test.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.request.auth.OAuthAccessTokenRequest;

public class AuthTests {

    @Before
    public void setup() {
        CreatubblesAPI.setStagingMode(true);
    }
    
    public static String getAuthToken() {
        return getAuthToken("gartt", "password");
    }

    static String getAuthToken(String user, String pass) {
        OAuthAccessTokenRequest req = new OAuthAccessTokenRequest(TestStuff.CLIENT_ID, TestStuff.CLIENT_SECRET, user, pass);
        return req.execute().getResponse().getAccessToken();
    }

    @Test
    public void testAuth() {
        Assert.assertNotNull(getAuthToken());
    }

    @Test
    public void testInvalidAuth() {
        Assert.assertNull(getAuthToken("invalid", "invalid"));
    }
}
