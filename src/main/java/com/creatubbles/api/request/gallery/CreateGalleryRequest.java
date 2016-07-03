package com.creatubbles.api.request.gallery;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.Gallery;
import com.creatubbles.api.response.gallery.CreateGalleryResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreateGalleryRequest extends CreatubblesRequest<CreateGalleryResponse> {

    public CreateGalleryRequest(Gallery gallery, String accessToken) {
        super(EndPoints.GALLERIES.getTemplate(), HttpMethod.POST, accessToken);
        setData(CreatubblesAPI.GSON.toJson(gallery));
    }

    @Override
    public Class<? extends CreateGalleryResponse> getResponseClass() {
        return CreateGalleryResponse.class;
    }
}
