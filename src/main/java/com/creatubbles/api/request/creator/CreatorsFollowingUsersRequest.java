package com.creatubbles.api.request.creator;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creator.CreatorsFollowingUsersResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;


public class CreatorsFollowingUsersRequest extends CreatubblesRequest<CreatorsFollowingUsersResponse> {

    public CreatorsFollowingUsersRequest(String id) {
        this(id, 1);
    }

    public CreatorsFollowingUsersRequest(String id, int page) {
        super(String.format(EndPoints.CREATORS_FOLLOWING_USERS, id), HttpMethod.GET);
        setUrlParameter("page", String.valueOf(page));
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameter("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public CreatorsFollowingUsersRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends CreatorsFollowingUsersResponse> getResponseClass() {
        return CreatorsFollowingUsersResponse.class;
    }
}
