package com.ivanvolkov.imagerotatorapi.domain;

import com.ivanvolkov.imagerotatorapi.data.Task;
import com.ivanvolkov.imagerotatorapi.data.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public TaskReadDto getTaskInfo(String taskId) {
        Optional<Task> task = this.taskRepository.getTask(taskId);
        if(task.isPresent()) {
            TaskReadDto taskDto = new TaskReadDto();
            taskDto.setId(task.get().getId());
            taskDto.setFileName(task.get().getFileName());
            taskDto.setProcessedFilePath(task.get().getProcessedFilePath());
            taskDto.setState(task.get().getState());
            return taskDto;
        } else {
            return null;
        }
    }
}
