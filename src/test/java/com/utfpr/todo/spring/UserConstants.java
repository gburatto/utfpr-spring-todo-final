package com.utfpr.todo.spring;

import java.util.Base64;
import java.util.UUID;

import com.utfpr.todo.clean.application.usecase.create_user.CreateUserCommand;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUserOutput;
import com.utfpr.todo.clean.infra.api.output.UserOutputDTO;
import com.utfpr.todo.clean.infra.model.UserModel;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserConstants {
    
    public static final String USER_ID = UUID.randomUUID().toString();

    public static final String NAME = "name";

    public static final String EMAIL = "email@gmail.com";

    public static final String USERNAME = "username";

    public static final String PASSWORD = "password";

    public static final String HASHED_PASSWORD = BCrypt.withDefaults().hashToString(12, PASSWORD.toCharArray());

    public static final String AUTH_HEADER = "Basic " + Base64.getEncoder().encodeToString((USERNAME + ":" + PASSWORD).getBytes());

    public static final UserModel USER_INPUT_DTO = UserModel.builder()
        .name(NAME)
        .email(EMAIL)
        .username(USERNAME)
        .password(PASSWORD)
        .build();

    public static final UserOutputDTO USER_OUTPUT_DTO = UserOutputDTO.builder()
        .id(USER_ID)
        .name(NAME)
        .email(EMAIL)
        .username(USERNAME)
        .build();

    public static final UserModel CREATED_USER = UserModel.builder()
        .id(USER_ID)
        .email(EMAIL)
        .name(NAME)
        .username(USERNAME)
        .password(HASHED_PASSWORD)
        .build();

    public static final CreateUserCommand CREATE_USER_COMMAND = CreateUserCommand.builder()
        .name(NAME)
        .email(EMAIL)
        .username(USERNAME)
        .password(PASSWORD)
        .build();

    public static final CreateUserOutput CREATE_USER_OUTPUT = CreateUserOutput.builder()
        .id(USER_ID)
        .name(NAME)
        .email(EMAIL)
        .username(USERNAME)
        .build();

}
