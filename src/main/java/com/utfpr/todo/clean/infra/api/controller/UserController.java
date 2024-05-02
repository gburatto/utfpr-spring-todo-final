package com.utfpr.todo.clean.infra.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.utfpr.todo.clean.application.usecase.create_user.CreateUser;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUserCommand;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUserOutput;
import com.utfpr.todo.clean.infra.api.input.UserInputDTO;
import com.utfpr.todo.clean.infra.api.mapper.UserMapper;
import com.utfpr.todo.clean.infra.api.output.UserOutputDTO;

import jakarta.validation.Valid;

@RestController
@RequestMapping("")
public class UserController {

  @Autowired
  private CreateUser createUser;

  @Autowired
  private UserMapper userMapper;

  @PostMapping("/createUser")
  public ResponseEntity<UserOutputDTO> create(@RequestBody @Valid UserInputDTO userInput) {

    CreateUserCommand command = CreateUserCommand.builder()
                .username(userInput.getUsername())
                .name(userInput.getName())
                .email(userInput.getEmail())
                .password(userInput.getPassword()).build();

    CreateUserOutput createdUser = createUser.execute(command);

    UserOutputDTO userOutput = UserOutputDTO.builder()
                .id(createdUser.getId())
                .username(createdUser.getUsername())
                .name(createdUser.getName())
                .email(createdUser.getEmail()).build();

    System.out.println(createdUser);
    return ResponseEntity.status(HttpStatus.CREATED).body(userOutput);

  }

}
