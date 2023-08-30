package com.ivanvolkov.imagerotatorapi.data;

import java.util.Optional;

public interface TaskRepository {

    Task save(Task task);

    Optional<Task> getTask(String id);
}
