package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.core.CreatubblesResponse;
import com.creatubbles.api.response.creation.UploadCreationResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;

// TODO Order of operations (and how the upload actually happens) is kind of vague in the API docs - figure out OoO
public class UploadCreationRequest extends CreatubblesRequest {

    public UploadCreationRequest() {
        super(EndPoints.CREATIONS, HttpMethod.POST);
    }

    @Override
    public Class<? extends CreatubblesResponse> getResponseClass() {
        return UploadCreationResponse.class;
    }
}
