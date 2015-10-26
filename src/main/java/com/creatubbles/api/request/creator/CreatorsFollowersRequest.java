package com.creatubbles.api.request.creator;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creator.CreatorsFollowersResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreatorsFollowersRequest extends CreatubblesRequest<CreatorsFollowersResponse> {

    public CreatorsFollowersRequest(String id) {
        this(id, 1);
    }

    public CreatorsFollowersRequest(String id, int page) {
        super(String.format(EndPoints.CREATORS_FOLLOWERS, id), HttpMethod.GET);
        setUrlParameter("page", String.valueOf(page));
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameters().get("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public CreatorsFollowersRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends CreatorsFollowersResponse> getResponseClass() {
        return CreatorsFollowersResponse.class;
    }
}
