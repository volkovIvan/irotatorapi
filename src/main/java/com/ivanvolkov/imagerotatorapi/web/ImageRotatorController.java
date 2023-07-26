package com.ivanvolkov.imagerotatorapi.web;

import com.ivanvolkov.imagerotatorapi.domain.FileUploaderService;
import com.ivanvolkov.imagerotatorapi.domain.TaskReadDto;
import com.ivanvolkov.imagerotatorapi.domain.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/rotate")
public class ImageRotatorController {

    private final TaskService taskService;
    private final FileUploaderService fileUploaderService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<String> handleFileUpload(@RequestBody MultipartFile file) throws IOException {
        String taskId = this.fileUploaderService.handleFileUpload(file);
        return ResponseEntity.ok(taskId);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<TaskReadDto> getTaskInfo(@RequestParam("taskid") String taskId) {
        TaskReadDto taskDto = this.taskService.getTaskInfo(taskId);
        return taskDto == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(taskDto);
    }

}
