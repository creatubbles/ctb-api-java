package com.creatubbles.api.request.gallery;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.gallery.UserGalleryResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
public class UserGalleryRequest extends CreatubblesRequest<UserGalleryResponse> {

    public UserGalleryRequest(String id) {
        this(id, null);
    }

    public UserGalleryRequest(String id, String accessToken) {
        super(String.format(EndPoints.USERS_GALLERIES, id), HttpMethod.GET, accessToken);
    }

    @Override
    public Class<? extends UserGalleryResponse> getResponseClass() {
        return UserGalleryResponse.class;
    }
}
