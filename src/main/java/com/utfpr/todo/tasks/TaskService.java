package com.utfpr.todo.tasks;

import org.springframework.stereotype.Service;

@Service
public class TaskService {

  private TaskRepository taskRepository;

  public TaskService(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  public TaskModel create(TaskModel task) {
    return taskRepository.save(task);
  }

}
