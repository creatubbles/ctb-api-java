package com.creatubbles.test.request;

import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.request.creation.GetCreationsRequest;
import com.creatubbles.api.request.landingurls.GetCreationLandingUrlRequest;
import com.creatubbles.api.request.user.UserProfileRequest;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.response.landingurls.GetCreationLandingUrlResponse;
import com.creatubbles.api.response.meta.MetaPagination;

public class CreationTests {

    @Before
    public void setup() {
        CreatubblesAPI.setStagingMode(true);
    }
    
    @Test
    public void testGetCreations() {
        GetCreationsRequest req = new GetCreationsRequest(new UserProfileRequest(AuthTests.getAuthToken()).execute().getResponse().getUser().getId(), AuthTests.getAuthToken());
        List<GetCreationsResponse> resp = req.execute().getResponseList();
        Assert.assertNotNull(resp);
        for (GetCreationsResponse r : resp) {
            Assert.assertNotNull(r.getCreation());
            Creation c = r.getCreation();
            Assert.assertNotNull(c.getName());
            Assert.assertNotNull(c.getId());
            Assert.assertNotNull(c.getImage());
            Assert.assertNotNull(r.getRelationships());
            Assert.assertNotNull(r.getRelationships().getUser());
            Assert.assertNotNull(r.getRelationships().getUser().getId());
            Assert.assertNotNull(r.getRelationships().getCreators());
            Assert.assertNotNull(r.getRelationships().getCreators()[0].getId());
            
            MetaPagination meta = req.getMetadata(MetaPagination.class);
            
            Assert.assertNotNull(meta);
            Assert.assertEquals(2, meta.getObjectCount());
            Assert.assertEquals(1, meta.getPageCount());
            Assert.assertEquals(1, meta.getPage());
        }
    }

    @Test
    public void testInvalidGetCreations() {
        GetCreationsRequest req = new GetCreationsRequest("invalid", "invalid");
        Assert.assertTrue(req.execute().getResponseList().isEmpty());
    }
    
    @Test
    public void testLandingURL() {
        GetCreationsRequest req = new GetCreationsRequest(new UserProfileRequest(AuthTests.getAuthToken()).execute().getResponse().getUser().getId(), AuthTests.getAuthToken());
        List<GetCreationsResponse> resp = req.execute().getResponseList();
        GetCreationLandingUrlRequest req2 = new GetCreationLandingUrlRequest(resp.get(0).getCreation().getId(), AuthTests.getAuthToken());
        GetCreationLandingUrlResponse resp2 = req2.execute().getResponse();
        Assert.assertNotNull(resp2.getUrl());
        System.out.println(resp2.getUrl());
    }
    
    /*
    @Test
    public void testBubbling() {
        GetCreationsRequest creations = new GetCreationsRequest(new UserProfileRequest(AuthTests.getAuthToken()).execute().getResponse().getUser().getId(), AuthTests.getAuthToken());
        GetCreationsResponse creationsresp = creations.execute().getResponse();
        
        Random r = new Random();
        CreateBubbleRequest req = new CreateBubbleRequest(AuthTests.getAuthToken(), BubbleTarget.CREATION, creationsresp.creations.get(0).id, BubbleColor.values()[r.nextInt(BubbleColor.values().length)], r.nextFloat(), r.nextFloat());
        CreateBubbleResponse resp = req.execute().getResponse();
        Assert.assertTrue(resp.id >= 0);
    }
    */
}
