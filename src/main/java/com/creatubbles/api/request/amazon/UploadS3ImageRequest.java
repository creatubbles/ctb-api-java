package com.creatubbles.api.request.amazon;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.amazon.UploadS3ImageResponse;
import com.creatubbles.api.util.HttpUtil;
import com.creatubbles.api.util.HttpUtil.Response;

import java.io.IOException;

/**
 * Created by Jevgeni on 28.10.2015.
 */
@APIVersion(2)
public class UploadS3ImageRequest extends CreatubblesRequest<UploadS3ImageResponse> {

    private byte[] data;
    private String url;

    public UploadS3ImageRequest(byte[] data, String url) {
        super(null, null);
        this.data = data;
        this.url = url;
    }

    @Override
    public Class<? extends UploadS3ImageResponse> getResponseClass() {
        return UploadS3ImageResponse.class;
    }

    @Override
    public CreatubblesRequest<UploadS3ImageResponse> execute() {
        resetResponse();
        UploadS3ImageResponse response = new UploadS3ImageResponse();
        try {
            Response resp = HttpUtil.uploadObject(data, url, HttpUtil.IMAGE_JPEG_CONTENT_TYPE);
            response.success = isSuccessStatusCode(resp.code);
            response.message = resp.message;
            setResponseCache(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
