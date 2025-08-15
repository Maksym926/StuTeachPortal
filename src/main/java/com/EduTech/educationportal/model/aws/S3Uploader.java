package com.EduTech.educationportal.model.aws;

import com.EduTech.educationportal.utils.Log;
import software.amazon.awssdk.auth.credentials.ProfileCredentialsProvider;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.File;
import java.nio.file.Paths;

public class S3Uploader {
    private final S3Client s3;
    public S3Uploader(){
        s3 = S3Client.builder()
                .region(Region.EU_NORTH_1)
                .credentialsProvider(ProfileCredentialsProvider.create())
                .build();
    }
    public void uploadAssignment(String teacherName, File file){
        String key = "assignments" + teacherName + "/" + file.getName();
        PutObjectRequest putRequest  = PutObjectRequest.builder()
                .bucket("assignments-bucket1")
                .key(key)
                .build();
        s3.putObject(putRequest, RequestBody.fromFile(Paths.get(file.getAbsolutePath())));
        Log.info("File " + file.getName() + " was uploaded to the server");
    }
}
