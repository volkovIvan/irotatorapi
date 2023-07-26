package com.ivanvolkov.imagerotatorapi.domain;

import com.ivanvolkov.imagerotatorapi.data.TaskState;
import lombok.Data;

@Data
public class TaskReadDto {

    private String id;
    private String fileName;
    private String processedFilePath;
    private TaskState state;
}
