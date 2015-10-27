package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class GetCreationsRequest extends CreatubblesRequest<GetCreationsResponse> {

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
        return getUrlParameter("creator_id");
    }

    public GetCreationsRequest setCreatorIdParam(boolean creatorId) {
        setUrlParameter("creator_id", String.valueOf(creatorId));
        return this;
    }

    public String getByCreatorIdParam() {
        return getUrlParameter("by_creator");
    }

    public GetCreationsRequest setByCreatorIdParam(boolean creatorId) {
        setUrlParameter("by_creator", String.valueOf(creatorId));
        return this;
    }

    public boolean getFeaturedParam() {
        return Boolean.parseBoolean(getUrlParameter("featured"));
    }

    public GetCreationsRequest setFeaturedParam(boolean featured) {
        setUrlParameter("featured", String.valueOf(featured));
        return this;
    }

    public boolean getRecentParam() {
        return Boolean.parseBoolean(getUrlParameter("recent"));
    }

    public GetCreationsRequest setRecentParam(boolean recent) {
        setUrlParameter("recent", String.valueOf(recent));
        return this;
    }

    public boolean getPopularParam() {
        return Boolean.parseBoolean(getUrlParameter("popular"));
    }

    public GetCreationsRequest setPopularParam(boolean popular) {
        setUrlParameter("popular", String.valueOf(popular));
        return this;
    }

    public boolean getBubbledParam() {
        return Boolean.parseBoolean(getUrlParameter("bubbled"));
    }

    public GetCreationsRequest setBubbledParam(boolean bubbled) {
        setUrlParameter("bubbled", String.valueOf(bubbled));
        return this;
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameter("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public GetCreationsRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends GetCreationsResponse> getResponseClass() {
        return GetCreationsResponse.class;
    }
}
