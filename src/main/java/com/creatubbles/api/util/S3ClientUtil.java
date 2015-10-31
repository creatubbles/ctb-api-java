package com.creatubbles.api.util;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;

/**
 * Created by Jevgeni on 27.10.2015.
 */
public class S3ClientUtil {
    private static final String AWS_S3_ACCESS_KEY = "AKIAJJCG5PC6P7E7URLQ";
    private static final String AWS_S3_SECRET_KEY = "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";
    private static final AWSCredentials AWS_S3_CREDENTIALS = new BasicAWSCredentials(AWS_S3_ACCESS_KEY, AWS_S3_SECRET_KEY);
    private static final AmazonS3 s3Client = new AmazonS3Client(AWS_S3_CREDENTIALS);
    public static final String AWS_S3_BUCKET_NAME = "ctbstaging";
    public static final String AWS_S3_BASE_URL = "http://" + AWS_S3_BUCKET_NAME + ".s3.amazonaws.com/";

    public static AmazonS3 getClient() {
        return s3Client;
    }
}
