package com.example.demo.controller;

import com.example.demo.model.Task;
import com.example.demo.repository.TaskRepository;
import com.example.demo.service.TaskService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

@RestController
public class TaskController {

	@Qualifier("taskServiceImpl")
	@Autowired
	private TaskService service;

	@Autowired
	private UserService userService;

	@PostMapping("/tasks")
	public Task create(@RequestBody Task task) {
		task.setUserId(userService.getCurrentUser().getId());
		return service.createTask(task);
	}

	@GetMapping("/tasks")
	public Iterable<Task> getAllTasks() {
		return service.getAllTasks();
	}

	@GetMapping("/tasks/{id}")
	public Iterable<Task> getTasksByUserId(@PathVariable long id) {
		return service.getTasksByUserId(id);
	}

	@PutMapping("/tasks/{id}")
	public Task updateTask(@RequestBody Task task,
						   @PathVariable long id) {
		return service.updateTask(task, id);
	}

	@DeleteMapping("tasks/{id}")
	public void deleteTask(@PathVariable long id) {
		service.deleteTask(id);
	}

	@PatchMapping("/tasks/{id}")
	public void patchTask(@PathVariable long id,
						  @RequestBody Task task) {
		service.markAsDone(id, task);
	}

	@PatchMapping("/tasks/{id}:mark-as-done")
	public void patchTask(@PathVariable long id) {
		service.markAsDone(id);
	}

}
