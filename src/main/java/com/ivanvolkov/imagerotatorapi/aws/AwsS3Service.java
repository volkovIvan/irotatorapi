package com.ivanvolkov.imagerotatorapi.aws;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AwsS3Service {

    String upload(MultipartFile multipartFile) throws IOException;
}
