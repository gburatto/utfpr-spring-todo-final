package com.utfpr.todo.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.utfpr.todo.exceptions.ValidationException;
import com.utfpr.todo.users.UserConstants;

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

    Mockito.when(taskMapper.fromInput(TaskConstants.TASK_INPUT_DTO, UserConstants.USER_ID))
                .thenReturn(TaskConstants.TASK);

    Mockito.when(taskRepository.save(TaskConstants.TASK)).thenReturn(TaskConstants.TASK_CREATED);

    Mockito.when(taskMapper.fromModel(TaskConstants.TASK_CREATED))
                .thenReturn(TaskConstants.TASK_OUTPUT_DTO);

    TaskOutputDTO createdTask = taskService.create(TaskConstants.TASK_INPUT_DTO, UserConstants.USER_ID);

    Assertions.assertThat(createdTask).isNotNull();
    Assertions.assertThat(createdTask.getId()).isNotNull();
    Assertions.assertThat(createdTask.getTitle()).isEqualTo(TaskConstants.TASK.getTitle());
    Assertions.assertThat(createdTask.getDescription()).isEqualTo(TaskConstants.TASK.getDescription());
    Assertions.assertThat(createdTask.getPriority()).isEqualTo(TaskConstants.TASK.getPriority());

  }

  @Test
  public void createTask_WithInvalidStartDate_ThrowsValidationException() {

    Assertions.assertThatThrownBy(() ->
      taskService.create(TaskConstants.TASK_INVALID_START_AT_DATE, UserConstants.USER_ID))
      .isInstanceOf(ValidationException.class)
      .hasMessage("A data de início deve ser maior que a data atual");

  }

  @Test
  public void createTask_WithInvalidEndDate_ThrowsValidationException() {

    Assertions.assertThatThrownBy(() ->
      taskService.create(TaskConstants.TASK_INVALID_END_AT_DATE, UserConstants.USER_ID))
      .isInstanceOf(ValidationException.class)
      .hasMessage("A data de término deve ser maior que a data de início");

  }

}
