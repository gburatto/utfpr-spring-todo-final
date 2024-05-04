package com.utfpr.todo.spring.users;

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
import com.utfpr.todo.clean.application.usecase.create_user.CreateUser;
import com.utfpr.todo.clean.infra.api.controller.UserController;
import com.utfpr.todo.clean.infra.api.mapper.UserMapper;
import com.utfpr.todo.clean.infra.model.UserModel;
import com.utfpr.todo.clean.infra.repository.UserModelJpaRepository;

@WebMvcTest(UserController.class)
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private UserModelJpaRepository userRepository;

  @MockBean
  private CreateUser createUser;

  @MockBean
  private UserMapper userMapper;

  @Autowired
  private ObjectMapper objectMapper;

  @Test
  public void createTask_WithDataValid_ReturnsCreated() throws JsonProcessingException, Exception {

    Mockito.when(createUser.execute(UserConstants.CREATE_USER_COMMAND)).thenReturn(UserConstants.CREATE_USER_OUTPUT);

    Mockito.when(userRepository.save(any(UserModel.class))).thenReturn(UserConstants.CREATED_USER);

    Mockito.when(userMapper.fromModel(UserConstants.CREATED_USER)).thenReturn(UserConstants.USER_OUTPUT_DTO);

    mockMvc.perform(
        post("/createUser")
            .contentType("application/json")
            .content(objectMapper.writeValueAsString(UserConstants.USER_INPUT_DTO)))
        .andExpect(status().isCreated())
        .andExpect(content().json(objectMapper.writeValueAsString(UserConstants.USER_OUTPUT_DTO)));

  }

}
