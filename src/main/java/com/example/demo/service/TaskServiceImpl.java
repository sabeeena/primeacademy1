package com.example.demo.service;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Autowired
    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Override
    public Iterable<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @Override
    public Iterable<Task> getTasksByUserId(long id) {
        List<Task> tasks = new ArrayList<Task>();
        Iterable<Task> allTasks = taskRepository.findAll();
        for (Task task : allTasks) {
            if (task.getId() == id)
                tasks.add(taskRepository.findById(id).orElse(null));
        }
        return tasks;
    }

    @Override
    public Task updateTask(Task task, long id) {
        task.setId(id);
        return taskRepository.save(task);
    }

    @Override
    public void deleteTask(long id) {
        taskRepository.deleteById(id);
    }

    @Override
    public void markAsDone(long id, Task task) {
        if (task.isDone()) {
            taskRepository.markAsDone(id);
        }
    }

    @Override
    public void markAsDone(long id) {
        taskRepository.markAsDone(id);
    }
}
