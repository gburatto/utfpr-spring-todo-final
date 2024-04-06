package com.utfpr.todo.clean.infra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskCommand;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskOutput;
import com.utfpr.todo.clean.infra.model.TaskModel;
import com.utfpr.todo.tasks.TaskInputDTO;
import com.utfpr.todo.tasks.TaskOutputDTO;
import com.utfpr.todo.tasks.TaskService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/tasks")
public class TaskController {

  @Autowired
  private TaskService taskService;

  @Autowired
  private CreateTask createTask;

  @PostMapping
  public ResponseEntity<TaskOutputDTO> create(HttpServletRequest request, @RequestBody @Valid TaskInputDTO taskInput) {

    String userId = request.getAttribute("userId").toString();

    CreateTaskCommand command = CreateTaskCommand.builder()
                .userId(userId)
                .title(taskInput.getTitle())
                .description(taskInput.getDescription())
                .priority(taskInput.getPriority())
                .startAt(taskInput.getStartAt())
                .endAt(taskInput.getEndAt()).build();

    CreateTaskOutput createdTask = createTask.execute(command);

    TaskOutputDTO taskOutput = TaskOutputDTO.builder()
                .id(createdTask.getId())
                .userId(createdTask.getUserId())
                .title(createdTask.getTitle())
                .description(createdTask.getDescription())
                .priority(createdTask.getPriority())
                .startAt(createdTask.getStartAt())
                .endAt(createdTask.getEndAt())
                .completed(createdTask.isCompleted()).build();

    return ResponseEntity.status(HttpStatus.CREATED).body(taskOutput);

  }

  @PatchMapping("/{id}/complete")
  public ResponseEntity<?> complete(@PathVariable String id) {

    TaskModel updatedTask = taskService.complete(id);

    return ResponseEntity.status(HttpStatus.OK).body(updatedTask);

  }

}
