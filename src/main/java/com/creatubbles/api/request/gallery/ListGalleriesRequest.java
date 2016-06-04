package com.creatubbles.api.request.gallery;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.gallery.ListGalleriesResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class ListGalleriesRequest extends CreatubblesRequest<ListGalleriesResponse> {

    public ListGalleriesRequest(String id, boolean creation) {
        this(id, creation, null);
    }

    public ListGalleriesRequest(String id, boolean creation, String accessToken) {
        super(getEndPoint(id, creation), HttpMethod.GET, accessToken);
    }
    
    private static String getEndPoint(String id, boolean creation) {
        if (id == null) {
            return EndPoints.GALLERIES.getTemplate();
        } else if (creation) {
            return EndPoints.CREATIONS_GALLERIES.format(id);
        } else {
            return EndPoints.USER_GALLERIES.format(id);
        }
    }

    @Override
    public Class<? extends ListGalleriesResponse> getResponseClass() {
        return ListGalleriesResponse.class;
    }
}
