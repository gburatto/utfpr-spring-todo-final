package com.utfpr.todo.spring.tasks;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utfpr.todo.clean.application.usecase.complete_task.CompleteTask;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;
import com.utfpr.todo.clean.infra.api.controller.TaskController;
import com.utfpr.todo.clean.infra.repository.UserModelJpaRepository;
import com.utfpr.todo.spring.users.UserConstants;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserModelJpaRepository userRepository;

  @MockBean
  private CreateTask createTask;

  @MockBean
  private CompleteTask completeTask;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTask_WithDataValid_ReturnsCreated() throws JsonProcessingException, Exception {

    Mockito.when(userRepository.findByUsername(UserConstants.USERNAME)).thenReturn(UserConstants.CREATED_USER);

    Mockito.when(createTask.execute(TaskConstants.CREATE_TASK_COMMAND)).thenReturn(TaskConstants.CREATE_TASK_OUTPUT);

    mockMvc.perform(
        post("/createTask")
            .contentType("application/json")
            .header("Authorization", UserConstants.AUTH_HEADER)
            .content(objectMapper.writeValueAsString(TaskConstants.TASK)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(TaskConstants.TASK_OUTPUT_DTO)));

  }

}
