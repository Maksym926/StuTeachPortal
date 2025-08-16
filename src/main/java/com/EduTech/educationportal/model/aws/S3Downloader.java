package com.EduTech.educationportal.model.aws;

import com.EduTech.educationportal.utils.Log;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;

import java.nio.file.Path;

public class S3Downloader {
    private final S3Client s3;

    public S3Downloader(){
        s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
    public void downloadAssignment(String key, Path destination){
        GetObjectRequest getRequest = GetObjectRequest.builder()
                .bucket("assignments-bucket1")
                .key(key)
                .build();
        s3.getObject(getRequest, destination);
        Log.info("The file was downloaded");

    }
}
