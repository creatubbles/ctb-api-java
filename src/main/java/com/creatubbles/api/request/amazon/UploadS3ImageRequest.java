package com.creatubbles.api.request.amazon;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.creatubbles.api.core.CreatubblesRequest;
import com.creatubbles.api.response.amazon.UploadS3ImageResponse;
import com.creatubbles.api.util.S3ClientUtil;

import java.io.ByteArrayInputStream;

/**
 * Created by Jevgeni on 28.10.2015.
 */
public class UploadS3ImageRequest extends CreatubblesRequest<UploadS3ImageResponse> {

    private String filePath;
    private byte[] data;

    public UploadS3ImageRequest(byte[] data, String filePath) {
        super(null, null);
        this.filePath = filePath;
        this.data = data;
    }

    @Override
    public Class<? extends UploadS3ImageResponse> getResponseClass() {
        return UploadS3ImageResponse.class;
    }

    @Override
    public CreatubblesRequest<UploadS3ImageResponse> execute() {
        resetResponse();
        AmazonS3 client = S3ClientUtil.getClient();
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(data.length);
        PutObjectRequest putObjectRequest = new PutObjectRequest(S3ClientUtil.AWS_S3_BUCKET_NAME, filePath, new ByteArrayInputStream(data), metadata);
        client.putObject(putObjectRequest);
        return this;
    }

    @Override
    public UploadS3ImageResponse getResponse() {
        return new UploadS3ImageResponse();
    }
}
