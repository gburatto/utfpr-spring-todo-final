package com.utfpr.todo.tasks;

import java.time.format.DateTimeFormatter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  @Test
  public void createTask_WithDataValid_ReturnsTask() {

    TaskModel createdTask = taskRepository.save(TaskConstants.TASK);

    TaskModel foundTask = taskRepository.findById(createdTask.getId()).get();

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");

    Assertions.assertThat(createdTask).isNotNull();
    Assertions.assertThat(createdTask.getId()).isNotNull();
    Assertions.assertThat(createdTask.getTitle()).isEqualTo(foundTask.getTitle());
    Assertions.assertThat(createdTask.getDescription()).isEqualTo(foundTask.getDescription());
    Assertions.assertThat(createdTask.getPriority()).isEqualTo(foundTask.getPriority());
    Assertions.assertThat(createdTask.getStartAt().format(formatter)).isEqualTo(foundTask.getStartAt().format(formatter));
    Assertions.assertThat(createdTask.getEndAt().format(formatter)).isEqualTo(foundTask.getEndAt().format(formatter));

  }

  @Test
  public void createTask_WithInvalidData_ThrowsException() {

    TaskModel emptyTask = TaskConstants.TASK_EMPTY;

    Assertions.assertThatThrownBy(() -> taskRepository.save(emptyTask)).isInstanceOf(RuntimeException.class);

  }

}
