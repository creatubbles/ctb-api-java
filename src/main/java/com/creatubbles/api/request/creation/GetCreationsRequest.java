package com.creatubbles.api.request.creation;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.GetCreationsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class GetCreationsRequest extends CreatubblesRequest<GetCreationsResponse> {

    public enum Params {
        page,
        per_page,
        gallery_id,
        user_id,
        sort,
        search,

        ;
    }

    public enum Sort {
        popular,
        recent,

        ;
    }

    public GetCreationsRequest(String userId, String authToken) {
        this(userId, 1, authToken);
    }

    public GetCreationsRequest(String userId, int page, String accessToken) {
        super(EndPoints.CREATIONS.getTemplate(), HttpMethod.GET, accessToken);
        setUrlParameter(Params.user_id.name(), userId);
        setUrlParameter(Params.page.name(), String.valueOf(page));
    }

    @Override
    public Class<? extends GetCreationsResponse> getResponseClass() {
        return GetCreationsResponse.class;
    }
}
