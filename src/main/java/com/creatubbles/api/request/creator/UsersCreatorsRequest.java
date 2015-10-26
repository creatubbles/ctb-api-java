package com.creatubbles.api.request.creator;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creator.UsersCreatorsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class UsersCreatorsRequest extends CreatubblesRequest<UsersCreatorsResponse> {

    public UsersCreatorsRequest() {
        this("me", null);
    }

    public UsersCreatorsRequest(String id) {
        this(id, null);
    }

    public UsersCreatorsRequest(String id, int pageNumber) {
        this(id, null, pageNumber);
    }

    public UsersCreatorsRequest(String id, String accessToken) {
        super(String.format(EndPoints.USERS_CREATORS, id), HttpMethod.GET, accessToken);
    }

    public UsersCreatorsRequest(String id, String accessToken, int pageNumber) {
        super(String.format(EndPoints.USERS_CREATORS, id), HttpMethod.GET, accessToken);
        setUrlParameter("page", String.valueOf(pageNumber));
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameters().get("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public void setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
    }

    @Override
    public Class<? extends UsersCreatorsResponse> getResponseClass() {
        return UsersCreatorsResponse.class;
    }
}
