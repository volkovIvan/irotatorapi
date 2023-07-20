package com.ivanvolkov.imagerotatorapi.azure;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class AzureBlobServiceImpl implements AzureBlobService {

    private final BlobContainerClient blobContainerClient;

    @Override
    public String upload(MultipartFile multipartFile) throws IOException {
        BlobClient blob = blobContainerClient.getBlobClient(multipartFile.getOriginalFilename());
        try(InputStream stream = multipartFile.getInputStream()) {
            blob.upload(stream, multipartFile.getSize(), true);
        }
        return blob.getBlobUrl();
    }
}
