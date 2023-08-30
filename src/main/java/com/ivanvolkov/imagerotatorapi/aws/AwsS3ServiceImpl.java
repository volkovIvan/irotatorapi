package com.ivanvolkov.imagerotatorapi.aws;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AwsS3ServiceImpl implements AwsS3Service {

    @Value("${aws.bucket-name}")
    private String bucketName;

    private final S3Client amazonS3Client;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        String fileName = multipartFile.getOriginalFilename();
        PutObjectRequest requestPut = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(fileName)
                .build();
        try(InputStream stream = multipartFile.getInputStream()) {
            RequestBody requestBody = RequestBody.fromInputStream(stream, multipartFile.getSize());
            amazonS3Client.putObject(requestPut,requestBody);
        }
        GetUrlRequest requestGet = GetUrlRequest.builder().bucket(bucketName ).key(fileName).build();
        return amazonS3Client.utilities().getUrl(requestGet).toExternalForm();
    }
}
