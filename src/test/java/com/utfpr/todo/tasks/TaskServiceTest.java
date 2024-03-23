package com.utfpr.todo.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.utfpr.todo.exceptions.ValidationException;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

  @InjectMocks
  private TaskService taskService;

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private TaskMapper taskMapper;

  // operação_estado_retorno`
  @Test
  public void createTask_WithValidData_ReturnsTask() {

    Mockito.when(taskMapper.fromInput(TaskConstants.TASK_INPUT_DTO))
                .thenReturn(TaskConstants.TASK);

    Mockito.when(taskRepository.save(TaskConstants.TASK)).thenReturn(TaskConstants.TASK_CREATED);

    TaskModel createdTask = taskService.create(TaskConstants.TASK_INPUT_DTO);

    Assertions.assertThat(createdTask).isNotNull();
    Assertions.assertThat(createdTask.getId()).isNotNull();
    Assertions.assertThat(createdTask.getTitle()).isEqualTo(TaskConstants.TASK.getTitle());
    Assertions.assertThat(createdTask.getDescription()).isEqualTo(TaskConstants.TASK.getDescription());
    Assertions.assertThat(createdTask.getPriority()).isEqualTo(TaskConstants.TASK.getPriority());

  }

  @Test
  public void createTask_WithInvalidStartDate_ThrowsValidationException() {

    Assertions.assertThatThrownBy(() ->
      taskService.create(TaskConstants.TASK_INVALID_START_AT_DATE))
      .isInstanceOf(ValidationException.class)
      .hasMessage("A data de início deve ser maior que a data atual");

  }

  @Test
  public void createTask_WithInvalidEndDate_ThrowsValidationException() {

    Assertions.assertThatThrownBy(() ->
      taskService.create(TaskConstants.TASK_INVALID_END_AT_DATE))
      .isInstanceOf(ValidationException.class)
      .hasMessage("A data de término deve ser maior que a data de início");

  }

}
