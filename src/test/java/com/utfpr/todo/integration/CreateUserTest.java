package com.utfpr.todo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.application.usecase.create_user.CreateUser;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUserCommand;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUserOutput;
import com.utfpr.todo.clean.infra.gateway.UserMemoryGateway;
import com.utfpr.todo.exceptions.ValidationException;

public class CreateUserTest {
    
    @Test
    public void createUser_WithDataValid_ReturnsUserOutput() {

        // Arrange
        UserMemoryGateway userGateway = new UserMemoryGateway();

        CreateUser createUser = new CreateUser(userGateway);

        CreateUserCommand command = CreateUserCommand.builder()
            .username("username")
            .name("Name")
            .email("email@example.com")
            .password("123456")
            .build();

        // Act
        CreateUserOutput output = createUser.execute(command);

        // Assert
        assertNotNull(output);
        assertNotNull(output.getId());
        assertEquals(command.getUsername(), output.getUsername());
        assertEquals(command.getName(), output.getName());
        assertEquals(command.getEmail(), output.getEmail());

    }

    @Test
    public void createUser_WithDataInvalid_ThrowsException() {

        // Arrange
        UserMemoryGateway userGateway = new UserMemoryGateway();

        CreateUser createUser = new CreateUser(userGateway);

        CreateUserCommand invalidCommand = CreateUserCommand.builder()
            .username("user")
            .name("Name123")
            .email("email")
            .password("123")
            .build();

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            createUser.execute(invalidCommand);
        });

    }

    @Test
    public void createUser_WithDataNull_ThrowsException() {

        // Arrange
        UserMemoryGateway userGateway = new UserMemoryGateway();

        CreateUser createUser = new CreateUser(userGateway);

        CreateUserCommand nullCommand = CreateUserCommand.builder().build();

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            createUser.execute(nullCommand);
        });

    }

}
