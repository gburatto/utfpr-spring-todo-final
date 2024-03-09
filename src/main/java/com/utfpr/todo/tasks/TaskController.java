package com.utfpr.todo.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody TaskModel task) {

    TaskModel createdTask = taskService.create(task);

    return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);

  }

}
