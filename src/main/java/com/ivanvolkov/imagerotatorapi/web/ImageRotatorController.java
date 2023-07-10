package com.ivanvolkov.imagerotatorapi.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
public class ImageRotatorController {

    @PostMapping(path="rotate")
    public ResponseEntity handleFileUpload(@RequestParam("file") MultipartFile file) {

    }
}
