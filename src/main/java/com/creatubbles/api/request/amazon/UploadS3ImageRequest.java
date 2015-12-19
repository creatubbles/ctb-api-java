package com.creatubbles.api.request.amazon;

import com.creatubbles.api.APIVersion;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.amazon.UploadS3ImageResponse;
import com.creatubbles.api.util.MultiPartUtil;

import java.io.IOException;

/**
 * Created by Jevgeni on 28.10.2015.
 */
@APIVersion(2)
public class UploadS3ImageRequest extends CreatubblesRequest<UploadS3ImageResponse> {

    private byte[] data;
    private String url;
    private String fileName;

    public UploadS3ImageRequest(byte[] data, String fileName, String url) {
        super(null, null);
        this.data = data;
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public Class<? extends UploadS3ImageResponse> getResponseClass() {
        return UploadS3ImageResponse.class;
    }

    @Override
    public CreatubblesRequest<UploadS3ImageResponse> execute() {
        resetResponse();
        try {
            MultiPartUtil multiPart = new MultiPartUtil(url, "UTF-8");
            multiPart.addFilePart(fileName, data, fileName).finish();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public UploadS3ImageResponse getResponse() {
        return new UploadS3ImageResponse();
    }
}
