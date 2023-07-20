package com.ivanvolkov.imagerotatorapi.azure;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface AzureBlobService {

    String upload(MultipartFile multipartFile) throws IOException;
}
