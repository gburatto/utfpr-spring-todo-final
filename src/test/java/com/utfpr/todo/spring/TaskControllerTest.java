package com.utfpr.todo.spring;

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
import com.utfpr.todo.clean.infra.api.controller.TaskController;
import com.utfpr.todo.users.UserConstants;
import com.utfpr.todo.users.UserRepository;

@WebMvcTest(TaskController.class)
public class TaskControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserRepository userRepository;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTask_WithDataValid_ReturnsCreated() throws JsonProcessingException, Exception {

    Mockito.when(userRepository.findByUsername(UserConstants.USERNAME)).thenReturn(UserConstants.USER);

    mockMvc.perform(
        post("/tasks")
            .contentType("application/json")
            .header("Authorization", UserConstants.AUTH_HEADER)
            .content(objectMapper.writeValueAsString(TaskConstants.TASK)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(TaskConstants.TASK_OUTPUT_DTO)));

  }

}
