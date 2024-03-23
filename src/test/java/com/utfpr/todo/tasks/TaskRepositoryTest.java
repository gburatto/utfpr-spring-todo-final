package com.utfpr.todo.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TaskRepositoryTest {

  @Autowired
  private TaskRepository taskRepository;

  //@Autowired
  //private TestEntityManager testEntityManager;

  @Test
  public void createTask_WithDataValid_ReturnsTask() {

    TaskModel createdTask = taskRepository.save(TaskConstants.TASK);

    System.out.println("createdTask");
    System.out.println(createdTask);

    //TaskModel foundTask = testEntityManager.find(TaskModel.class, createdTask.getId());

    TaskModel foundTask = taskRepository.findById(createdTask.getId()).get();

    System.out.println("foundTask");
    System.out.println(foundTask);

    System.out.println(createdTask.getStartAt().toLocalDate());
    System.out.println(foundTask.getStartAt().toLocalDate());

    //Assertions.assertThat(createdTask).isEqualTo(foundTask);
    Assertions.assertThat(createdTask).isNotNull();
    Assertions.assertThat(createdTask.getId()).isNotNull();
    Assertions.assertThat(createdTask.getTitle()).isEqualTo(foundTask.getTitle());
    Assertions.assertThat(createdTask.getDescription()).isEqualTo(foundTask.getDescription());
    Assertions.assertThat(createdTask.getPriority()).isEqualTo(foundTask.getPriority());
    Assertions.assertThat(createdTask.getStartAt().toLocalDate()).isEqualTo(foundTask.getStartAt().toLocalDate());
    Assertions.assertThat(createdTask.getEndAt().toLocalDate()).isEqualTo(foundTask.getEndAt().toLocalDate());

  }

  @Test
  public void createTask_WithInvalidData_ThrowsException() {

    TaskModel emptyTask = TaskConstants.TASK_EMPTY;

    Assertions.assertThatThrownBy(() -> taskRepository.save(emptyTask)).isInstanceOf(RuntimeException.class);

  }

}
