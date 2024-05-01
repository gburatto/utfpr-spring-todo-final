package com.utfpr.todo.spring;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.utfpr.todo.users.UserConstants;
import com.utfpr.todo.users.UserController;
import com.utfpr.todo.users.UserMapper;
import com.utfpr.todo.users.UserModel;
import com.utfpr.todo.users.UserRepository;
import com.utfpr.todo.users.UserService;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserService userService;

  @MockBean
  private UserRepository userRepository;

  @MockBean
  private UserMapper userMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTask_WithDataValid_ReturnsCreated() throws JsonProcessingException, Exception {

    Mockito.when(userService.save(any(UserModel.class))).thenReturn(UserConstants.CREATED_USER);

    Mockito.when(userRepository.save(any(UserModel.class))).thenReturn(UserConstants.CREATED_USER);

    Mockito.when(userMapper.fromModel(UserConstants.CREATED_USER)).thenReturn(UserConstants.USER_OUTPUT_DTO);

    mockMvc.perform(
        post("/users")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(UserConstants.USER_INPUT_DTO)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(UserConstants.USER_OUTPUT_DTO)));

  }

}
