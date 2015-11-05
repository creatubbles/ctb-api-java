package com.creatubbles.api.request.creation;

import com.creatubbles.api.CreatubblesAPI;
import com.creatubbles.api.core.Creation;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.UpdateCreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;
import com.creatubbles.api.util.S3ClientUtil;
import com.google.gson.JsonObject;

/**
 * Created by Jevgeni on 28.10.2015.
 */
public class UpdateCreationRequest extends CreatubblesRequest<UpdateCreationResponse> {

    public UpdateCreationRequest(String accessToken, Creation creation) {
        super(String.format(EndPoints.CREATIONS_BY_ID, creation.id), HttpMethod.PUT, accessToken);
        JsonObject jsonObject = new JsonObject();
        if (creation.url != null && !creation.url.startsWith("http")) {
            creation.url = S3ClientUtil.AWS_S3_BASE_URL + creation.url;
        }
        jsonObject.add("creation", CreatubblesAPI.GSON.toJsonTree(creation));
        setData(jsonObject.toString());
    }

    @Override
    public Class<? extends UpdateCreationResponse> getResponseClass() {
        return UpdateCreationResponse.class;
    }
}
