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
    public TaskDto getTaskInfo(String taskId) {
        Optional<Task> task = this.taskRepository.findById(taskId);
        if(task.isPresent()) {
            TaskDto taskDto = new TaskDto();
            taskDto.setId(task.get().getId());
            taskDto.setFileName(task.get().getFileName());
            taskDto.setOriginalFilePath(task.get().getOriginalFilePath());
            taskDto.setProcessedFilePath(task.get().getProcessedFilePath());
            taskDto.setState(task.get().getState());
            return taskDto;
        } else {
            return null;
        }
    }
}
