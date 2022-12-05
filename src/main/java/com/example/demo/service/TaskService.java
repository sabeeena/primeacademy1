package com.example.demo.service;

import com.example.demo.model.Task;
import org.springframework.stereotype.Service;

@Service
public interface TaskService {

    Task createTask(Task task);

    Iterable<Task> getAllTasks();

    Iterable<Task> getTasksByUserId(long id);

    Task updateTask(Task task, long id);

    void deleteTask(long id);

    void markAsDone(long id, Task task);

    void markAsDone(long id);

}
