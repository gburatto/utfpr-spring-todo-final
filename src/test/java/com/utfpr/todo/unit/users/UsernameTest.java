package com.utfpr.todo.unit.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.users.Username;
import com.utfpr.todo.exceptions.ValidationException;

public class UsernameTest {
    
    @Test
    public void username_ValidInput_ShouldCreateUsername() {

        // Arrange
        String validUsername = "username";

        // Act
        Username username = new Username(validUsername);

        // Assert
        assertNotNull(username);
        assertEquals(validUsername, username.getValue());

    }

    @Test
    public void username_InvalidInput_ThrowsException() {

        // Arrange
        String invalidUsername = "user";
        String message = "Username must have at least 6 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Username(invalidUsername);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void username_NullInput_ThrowsException() {

        // Arrange
        String nullUsername = null;
        String message = "Username must have at least 6 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Username(nullUsername);
        });
        assertEquals(message, exception.getMessage());

    }

}
