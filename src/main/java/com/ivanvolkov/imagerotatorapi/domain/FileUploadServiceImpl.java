package com.ivanvolkov.imagerotatorapi.domain;

import com.ivanvolkov.imagerotatorapi.aws.AwsS3Service;
import com.ivanvolkov.imagerotatorapi.data.Task;
import com.ivanvolkov.imagerotatorapi.data.TaskRepository;
import com.ivanvolkov.imagerotatorapi.data.TaskState;
import io.awspring.cloud.sqs.operations.SqsTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploaderService {

    @Value("${aws.task-queue-sqs}")
    private String taskQueueEndpoint;

    private final AwsS3Service awsS3Service;
    private final TaskRepository taskRepository;
    private final SqsTemplate sqsTemplate;

    @Override
    public String handleFileUpload(MultipartFile file) throws IOException {
        log.info("started uploading file");
        Task task = new Task();
        task.setState(TaskState.CREATED);
        String path = awsS3Service.upload(file);
        log.info("Uploaded file");
        task.setFileName(file.getOriginalFilename());
        task.setOriginalFilePath(path);
        task = taskRepository.save(task);
        log.info("Saved task with id {}", task.getId());
        sqsTemplate.send(taskQueueEndpoint, task.getId());
        return task.getId();
    }
}
