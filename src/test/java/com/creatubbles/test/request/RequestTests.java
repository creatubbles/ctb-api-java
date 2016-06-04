package com.creatubbles.test.request;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.request.user.UserProfileRequest;

public class RequestTests {

    @Before
    public void setup() {
        CreatubblesAPI.setStagingMode(true);
    }
    
    @Test
    public void testRequestOperations() {
        UserProfileRequest req = new UserProfileRequest(AuthTests.getAuthToken());
        Assert.assertNull(req.getResponse());
        Assert.assertNull(req.getRawResponse());
        Assert.assertFalse(req.isDone());
        req.execute();
        Assert.assertNotNull(req.getResponse());
        Assert.assertNotNull(req.getRawResponse());
        Assert.assertTrue(req.isDone());
    }
}
