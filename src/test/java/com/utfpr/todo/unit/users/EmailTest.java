package com.utfpr.todo.unit.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.users.Email;
import com.utfpr.todo.exceptions.ValidationException;

public class EmailTest {
    
    @Test
    public void email_ValidInput_ShouldCreateEmail() {

        // Arrange
        String validEmail = "email@example.com";

        // Act
        Email email = new Email(validEmail);

        // Assert
        assertNotNull(email);
        assertEquals(validEmail, email.getValue());

    }

    @Test
    public void email_InvalidInputTooShort_ThrowsException() {

        // Arrange
        String invalidEmail = "a@b.c";
        String message = "Email must have at least 10 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Email(invalidEmail);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void email_InvalidInputNoAt_ThrowsException() {

        // Arrange
        String invalidEmail = "example.com";
        String message = "Email must contain '@' and '.'";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Email(invalidEmail);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void email_InvalidInputNoDot_ThrowsException() {

        // Arrange
        String invalidEmail = "email@example";
        String message = "Email must contain '@' and '.'";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Email(invalidEmail);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void email_NullInput_ThrowsException() {

        // Arrange
        String nullEmail = null;
        String message = "Email must have at least 10 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Email(nullEmail);
        });
        assertEquals(message, exception.getMessage());

    }

}


