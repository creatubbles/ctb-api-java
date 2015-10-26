package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class GetCreationsRequest extends CreatubblesRequest {

    public GetCreationsRequest() {
        super(EndPoints.CREATIONS, HttpMethod.GET);
    }

    public GetCreationsRequest(String id) {
        super(String.format(EndPoints.CREATORS_CREATIONS, id), HttpMethod.GET);
    }

    public GetCreationsRequest(String id, String accessToken) {
        super(String.format(EndPoints.CREATORS_CREATIONS, id), HttpMethod.GET, accessToken);
    }

    public String getCreatorIdParam() {
        return getUrlParameters().get("creator_id");
    }

    public GetCreationsRequest setCreatorIdParam(boolean creatorId) {
        setUrlParameter("creator_id", String.valueOf(creatorId));
        return this;
    }

    public String getByCreatorIdParam() {
        return getUrlParameters().get("by_creator");
    }

    public GetCreationsRequest setByCreatorIdParam(boolean creatorId) {
        setUrlParameter("by_creator", String.valueOf(creatorId));
        return this;
    }

    public boolean getFeaturedParam() {
        return Boolean.parseBoolean(getUrlParameters().get("featured"));
    }

    public GetCreationsRequest setFeaturedParam(boolean featured) {
        setUrlParameter("featured", String.valueOf(featured));
        return this;
    }

    public boolean getRecentParam() {
        return Boolean.parseBoolean(getUrlParameters().get("recent"));
    }

    public GetCreationsRequest setRecentParam(boolean recent) {
        setUrlParameter("recent", String.valueOf(recent));
        return this;
    }

    public boolean getPopularParam() {
        return Boolean.parseBoolean(getUrlParameters().get("popular"));
    }

    public GetCreationsRequest setPopularParam(boolean popular) {
        setUrlParameter("popular", String.valueOf(popular));
        return this;
    }

    public boolean getBubbledParam() {
        return Boolean.parseBoolean(getUrlParameters().get("bubbled"));
    }

    public GetCreationsRequest setBubbledParam(boolean bubbled) {
        setUrlParameter("bubbled", String.valueOf(bubbled));
        return this;
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameters().get("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public GetCreationsRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return GetCreationsResponse.class;
    }
}
