package com.creatubbles.api.request.creator;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.creator.CreatorsFollowersResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class CreatorsFollowersRequest extends CreatubblesRequest {

    public CreatorsFollowersRequest(String id) {
        this(id, 1);
    }

    public CreatorsFollowersRequest(String id, int page) {
        super(String.format(EndPoints.CREATORS_FOLLOWERS, id), HttpMethod.GET);
        setUrlParameter("page", String.valueOf(page));
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return CreatorsFollowersResponse.class;
    }
}
