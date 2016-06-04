package com.creatubbles.api.request.creator;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creator.GetCreatorsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

@APIVersion(2)
public class GetCreatorsRequest extends CreatubblesRequest<GetCreatorsResponse> {

    public GetCreatorsRequest(String userId, String token) {
        super(EndPoints.USERS.getTemplate(), HttpMethod.GET, token);
        setUrlParameter(Params.user_id.name(), userId);
    }

    @Override
    public Class<? extends GetCreatorsResponse> getResponseClass() {
        return GetCreatorsResponse.class;
    }

    public enum Params {
        page, per_page, user_id, scope;

        public enum Scope {
            managers, creators;
        }
    }

}
