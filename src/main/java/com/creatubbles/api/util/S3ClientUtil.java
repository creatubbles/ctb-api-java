package com.creatubbles.api.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Jevgeni on 27.10.2015.
 */
public class S3ClientUtil {
    public static final String AWS_S3_BUCKET_NAME = "ctbstaging";
    public static final String AWS_S3_BASE_URL = "http://" + AWS_S3_BUCKET_NAME + ".s3.amazonaws.com/";

    public static AmazonS3 getClient(String accessKey, String secretKey, String sessionToken) {
        return new AmazonS3Client(getCredentials(accessKey, secretKey, sessionToken));
    }

    private static AWSCredentials getCredentials(String accessKey, String secretKey, String sessionToken) {
        return new BasicSessionCredentials(accessKey, secretKey, sessionToken);
    }
}
