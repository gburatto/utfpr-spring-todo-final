package com.utfpr.todo.unit.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.entity.User;
import com.utfpr.todo.exceptions.ValidationException;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserTest {
    
    @Test
    public void createUser_ValidInput_ShouldCreateUser() {

        // Arrange
        String username = "username";
        String name = "Name";
        String email = "email@example.com";
        String password = "123456";

        // Act
        User user = User.create(username, name, email, password);
        
        // Assert
        assertNotNull(user);
        assertNotNull(user.getId());
        assertEquals(username, user.getUsername());
        assertEquals(name, user.getName());
        assertEquals(email, user.getEmail());
        assertTrue(BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified);
        
    }

    @Test
    public void createUser_InvalidInput_ThrowsException() {

        // Arrange
        String invalidUsername = "user";
        String invalidName = "Name123";
        String invalidEmail = "email";
        String invalidPassword = "123";

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            User.create(invalidUsername, invalidName, invalidEmail, invalidPassword);
        });

    }

    @Test
    public void createUser_NullInput_ThrowsException() {

        // Arrange
        String nullUsername = null;
        String nullName = null;
        String nullEmail = null;
        String nullPassword = null;

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            User.create(nullUsername, nullName, nullEmail, nullPassword);
        });

    }
    
}
