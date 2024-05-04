package com.utfpr.todo.unit.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.users.Password;
import com.utfpr.todo.exceptions.ValidationException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class PasswordTest {
    
    @Test
    public void password_ValidInput_ShouldCreateHashedPassword() {

        // Arrange
        String validPassword = "123456";

        // Act
        Password password = new Password(validPassword);

        // Assert
        assertNotNull(password);
        assertTrue(validPassword != password.getValue()); // Password should be hashed, so the values must be different
        assertTrue(BCrypt.verifyer().verify(validPassword.toCharArray(), password.getValue()).verified);

    }

    @Test
    public void password_InvalidInput_ThrowsException() {

        // Arrange
        String invalidPassword = "123";
        String message = "Password must have at least 6 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Password(invalidPassword);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void password_NullInput_ThrowsException() {

        // Arrange
        String nullPassword = null;
        String message = "Password must have at least 6 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Password(nullPassword);
        });
        assertEquals(message, exception.getMessage());

    }

}
