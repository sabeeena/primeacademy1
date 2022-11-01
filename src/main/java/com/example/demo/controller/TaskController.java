package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

	@Autowired
	private TaskRepository taskRepository;
	@PostMapping("/tasks")
	public Task create(@RequestBody Task task) {
		return taskRepository.save(task);
	}

	@PatchMapping("/tasks/{id}")
	public void patchMethod(@PathVariable Long id, @RequestBody Task task){
		if (task.isDone())
			taskRepository.markAsDone(id);
	}
	@PatchMapping("/tasks/{id}:mark-as-done")
	public void patchMethod(@PathVariable Long id){
		taskRepository.markAsDone(id);
	}

}
