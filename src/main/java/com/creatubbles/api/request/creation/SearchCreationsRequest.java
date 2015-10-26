package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.creation.SearchCreationsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

public class SearchCreationsRequest extends CreatubblesRequest {

    public SearchCreationsRequest(String searchParam) {
        super(EndPoints.SEARCH_CREATIONS, HttpMethod.GET);
        setUrlParameter("search", searchParam);
    }

    public String getSearchParam() {
        return getUrlParameters().get("search");
    }

    public SearchCreationsRequest setSearchParam(String searchParam) {
        setUrlParameter("search", searchParam);
        return this;
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameters().get("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public SearchCreationsRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return SearchCreationsResponse.class;
    }
}
