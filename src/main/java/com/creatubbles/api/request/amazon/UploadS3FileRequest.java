package com.creatubbles.api.request.amazon;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.amazon.UploadS3FileResponse;
import com.creatubbles.api.util.HttpUtil;
import com.creatubbles.api.util.HttpUtil.Response;

import java.io.IOException;

/**
 * Created by Jevgeni on 28.10.2015.
 */
@APIVersion(2)
public class UploadS3FileRequest extends CreatubblesRequest<UploadS3FileResponse> {

    private byte[] data;
    private String url;
    private String contentType;

    public UploadS3FileRequest(byte[] data, String url, String contentType) {
        super(null, null);
        this.data = data;
        this.url = url;
        this.contentType = contentType;
    }

    @Override
    public Class<? extends UploadS3FileResponse> getResponseClass() {
        return UploadS3FileResponse.class;
    }

    @Override
    public CreatubblesRequest<UploadS3FileResponse> execute() {
        resetResponse();
        try {
            Response resp = HttpUtil.uploadObject(data, url, contentType);
            UploadS3FileResponse response = new UploadS3FileResponse(isSuccessStatusCode(resp.code));
            response.setMessage(resp.message);
            setResponseCache(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }
}
