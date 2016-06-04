package com.creatubbles.test.request;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.request.creator.GetCreatorsRequest;
import com.creatubbles.api.request.user.UserProfileRequest;
import com.creatubbles.api.response.creator.GetCreatorsResponse;

public class UserTests {

    @Before
    public void setup() {
        CreatubblesAPI.setStagingMode(true);
    }
    
    @Test
    public void testProfile() {
        UserProfileRequest req = new UserProfileRequest(AuthTests.getAuthToken());
        Assert.assertNotNull(req.execute().getResponse().getUser().getId());
        Assert.assertNotNull(req.execute().getResponse().getId());
    }

    @Test
    public void testInvalidProfile() {
        UserProfileRequest req = new UserProfileRequest("invalid");
        Assert.assertNull(req.execute().getResponse().getUser());
    }

    @Test
    public void testCreators() {
        String auth = AuthTests.getAuthToken("tterrag1098@gmail.com", "creatubblespw");
        UserProfileRequest profileReq = new UserProfileRequest(auth);
        GetCreatorsRequest req = new GetCreatorsRequest(profileReq.execute().getResponse().getUser().getId(), auth);
        List<GetCreatorsResponse> resp = req.execute().getResponseList();
        Assert.assertNotNull(resp.get(0).getCreator());
        Assert.assertEquals(2, resp.size());
    }

    @Test
    public void testInvalidCreators() {
        GetCreatorsRequest req = new GetCreatorsRequest("invalid", "invalid");
        Assert.assertTrue(req.execute().getResponseList().isEmpty());
    }
}
