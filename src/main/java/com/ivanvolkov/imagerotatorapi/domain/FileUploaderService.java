package com.ivanvolkov.imagerotatorapi.domain;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

public interface FileUploaderService {

    String handleFileUpload(MultipartFile file) throws IOException;
}
