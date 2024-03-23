package com.utfpr.todo.tasks;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.utfpr.todo.exceptions.ValidationException;

@Service
public class TaskService {

  private TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskModel create(TaskModel task) {

    LocalDateTime currentDate = LocalDateTime.now();

    if (currentDate.isAfter(task.getStartAt())) {
      throw new ValidationException("A data de início deve ser maior que a data atual");
    }

    if (task.getEndAt().isBefore(task.getStartAt())) {
      throw new ValidationException("A data de término deve ser maior que a data de início");
    }

    return taskRepository.save(task);

  }

  public TaskModel complete(String id) {

    TaskModel task = taskRepository.findById(id).orElseThrow(() -> new RuntimeException("Task not found"));

    task.setCompleted(true);

    return taskRepository.save(task);

  }

}
