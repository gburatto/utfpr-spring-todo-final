package com.utfpr.todo.spring.tasks;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.utfpr.todo.clean.infra.api.input.TaskInputDTO;
import com.utfpr.todo.clean.infra.model.TaskModel;
import com.utfpr.todo.clean.infra.model.UserModel;
import com.utfpr.todo.spring.users.UserConstants;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TaskIT {

  @Autowired
  private TestRestTemplate restTemplate;

  @Test
  public void createTask_WithDataValid_ReturnsCreated() {

    // given / arrange

    ResponseEntity<UserModel> createUserResponse = restTemplate.postForEntity("/createUser", UserConstants.USER_INPUT_DTO, UserModel.class);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", UserConstants.AUTH_HEADER);

    HttpEntity<TaskInputDTO> request = new HttpEntity<>(TaskConstants.TASK_INPUT_DTO, headers);

    // when / act

    ResponseEntity<TaskModel> createTaskResponse = restTemplate.postForEntity("/createTask", request, TaskModel.class);
    //ResponseEntity<Object> createTaskResponse = restTemplate.postForEntity("/createTask", request, Object.class);
    //System.out.println(createTaskResponse.getBody());

    // then / assert

    Assertions.assertThat(createTaskResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    Assertions.assertThat(createTaskResponse.getBody().getId()).isNotNull();
    Assertions.assertThat(createTaskResponse.getBody().getUserId()).isEqualTo(createUserResponse.getBody().getId());
    Assertions.assertThat(createTaskResponse.getBody().getTitle()).isEqualTo(TaskConstants.TASK_CREATED.getTitle());
    Assertions.assertThat(createTaskResponse.getBody().getDescription()).isEqualTo(TaskConstants.TASK_CREATED.getDescription());
    Assertions.assertThat(createTaskResponse.getBody().getPriority()).isEqualTo(TaskConstants.TASK_CREATED.getPriority());

  }

  @Test
  public void createTask_WithInvalidData_ReturnsUnprocessableEntity() {

    // given / arrange

    restTemplate.postForEntity("/createUser", UserConstants.USER_INPUT_DTO, UserModel.class);

    HttpHeaders headers = new HttpHeaders();
    headers.set("Authorization", UserConstants.AUTH_HEADER);

    HttpEntity<TaskInputDTO> request = new HttpEntity<>(TaskConstants.TASK_INVALID_START_AT_DATE, headers);

    // when / act

    ResponseEntity<Object> createTaskResponse = restTemplate.postForEntity("/createTask", request, Object.class);

    // then / assert

    Assertions.assertThat(createTaskResponse.getStatusCode()).isEqualTo(HttpStatus.UNPROCESSABLE_ENTITY);
    
  }

}
