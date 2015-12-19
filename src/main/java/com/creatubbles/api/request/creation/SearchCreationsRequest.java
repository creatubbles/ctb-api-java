package com.creatubbles.api.request.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.SearchCreationsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(1)
public class SearchCreationsRequest extends CreatubblesRequest<SearchCreationsResponse> {

    public SearchCreationsRequest(String searchParam) {
        super(EndPoints.SEARCH_CREATIONS, HttpMethod.GET);
        setUrlParameter("search", searchParam);
    }

    public String getSearchParam() {
        return getUrlParameter("search");
    }

    public SearchCreationsRequest setSearchParam(String searchParam) {
        setUrlParameter("search", searchParam);
        return this;
    }

    public Integer getPageNumber() {
        try {
            return Integer.parseInt(getUrlParameter("page"));
        } catch (NumberFormatException e) {

        }
        return null;
    }

    public SearchCreationsRequest setPageNumber(int pageNumber) {
        setUrlParameter("page", String.valueOf(pageNumber));
        return this;
    }

    @Override
    public Class<? extends SearchCreationsResponse> getResponseClass() {
        return SearchCreationsResponse.class;
    }
}
