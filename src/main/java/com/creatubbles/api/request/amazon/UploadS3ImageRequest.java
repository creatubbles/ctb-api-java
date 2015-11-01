package com.creatubbles.api.request.amazon;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
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
    private String accessKey;
    private String secretKey;
    private String sessionToken;

    public UploadS3ImageRequest(byte[] data, String filePath, String accessKey, String secretKey, String sessionToken) {
        super(null, null);
        this.filePath = filePath;
        this.data = data;
        this.accessKey = accessKey;
        this.secretKey = secretKey;
        this.sessionToken = sessionToken;
    }

    @Override
    public Class<? extends UploadS3ImageResponse> getResponseClass() {
        return UploadS3ImageResponse.class;
    }

    @Override
    public CreatubblesRequest<UploadS3ImageResponse> execute() {
        resetResponse();
        AmazonS3 client = S3ClientUtil.getClient(accessKey, secretKey, sessionToken);
        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(data.length);
        PutObjectRequest putObjectRequest = new PutObjectRequest(S3ClientUtil.AWS_S3_BUCKET_NAME, filePath, new ByteArrayInputStream(data), metadata)
                .withCannedAcl(CannedAccessControlList.PublicRead);
        client.putObject(putObjectRequest);
        return this;
    }

    @Override
    public UploadS3ImageResponse getResponse() {
        return new UploadS3ImageResponse();
    }
}
