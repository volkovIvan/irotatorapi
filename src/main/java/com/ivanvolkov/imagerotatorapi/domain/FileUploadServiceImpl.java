package com.ivanvolkov.imagerotatorapi.domain;

import com.ivanvolkov.imagerotatorapi.azure.AzureBlobService;
import com.ivanvolkov.imagerotatorapi.data.Task;
import com.ivanvolkov.imagerotatorapi.data.TaskRepository;
import com.ivanvolkov.imagerotatorapi.data.TaskState;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import reactor.core.publisher.Sinks;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class FileUploadServiceImpl implements FileUploaderService {

    private final AzureBlobService azureBlobService;
    private final TaskRepository taskRepository;
    private final Sinks.Many<Message<TaskMessage>> many;

    @Override
    public String handleFileUpload(MultipartFile file) throws IOException {
        log.info("started uploading file");
        Task task = new Task();
        task.setState(TaskState.CREATED);
        String path = azureBlobService.upload(file);
        log.info("Uploaded file");
        task.setFileName(file.getOriginalFilename());
        task.setOriginalFilePath(path);
        task = taskRepository.save(task);
        log.info("Saved task with id {}", task.getId());
        TaskMessage taskMessage = new TaskMessage();
        taskMessage.setId(task.getId());
        taskMessage.setFileSize(file.getSize());
        many.emitNext(MessageBuilder.withPayload(taskMessage).build(), Sinks.EmitFailureHandler.FAIL_FAST);
        return task.getId();
    }
}
