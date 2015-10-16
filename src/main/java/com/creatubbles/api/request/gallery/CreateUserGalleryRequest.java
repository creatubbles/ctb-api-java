package com.creatubbles.api.request.gallery;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

// TODO Proper (de)serialization of the Gallery object to support optional params
public class CreateUserGalleryRequest extends CreatubblesRequest {

    public CreateUserGalleryRequest(Gallery gallery, String accessToken) {
        super(String.format(EndPoints.USERS_GALLERIES, "me"), HttpMethod.POST, accessToken);
        setData(CreatubblesAPI.GSON.toJson(gallery));
    }
}
