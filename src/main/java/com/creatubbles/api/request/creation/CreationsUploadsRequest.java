package com.creatubbles.api.request.creation;

import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.creation.CreationsUploadsResponse;
import com.creatubbles.api.util.EndPoints;
import com.creatubbles.api.util.HttpMethod;
import com.creatubbles.api.util.HttpUtil;

public class CreationsUploadsRequest extends CreatubblesRequest<CreationsUploadsResponse> {

    private String extension;

    public CreationsUploadsRequest(String creationId, String extension, String accessToken) {
        super(EndPoints.CREATIONS_UPLOADS.format(creationId), HttpMethod.POST, accessToken);
        this.extension = extension;
        setUrlParameter("extension", extension);
    }

    @Override
    public Class<? extends CreationsUploadsResponse> getResponseClass() {
        return CreationsUploadsResponse.class;
    }

    @Override
    public CreatubblesRequest<CreationsUploadsResponse> execute() {
        if (extension == null || !HttpUtil.allowedFileTypes.contains(extension)) {
            resetResponse();
            CreationsUploadsResponse response = new CreationsUploadsResponse();
            response.setMessage(String.format("Invalid file with type %s", extension));
            setResponseCache(response);
            return this;
        }
        return super.execute();
    }
}
