package com.utfpr.todo.unit.users;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.users.Name;
import com.utfpr.todo.exceptions.ValidationException;

public class NameTest {
    
    @Test
    public void name_ValidInput_ShouldCreateName() {

        // Arrange
        String validName = "Name";

        // Act
        Name name = new Name(validName);

        // Assert
        assertNotNull(name);
        assertEquals(validName, name.getValue());

    }

    @Test
    public void name_InvalidInputNumbers_ThrowsException() {

        // Arrange
        String invalidName = "Name123";
        String message = "Name cannot contain numbers or symbols";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Name(invalidName);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void name_InvalidInputSymbols_ThrowsException() {

        // Arrange
        String invalidName = "Name_";
        String message = "Name cannot contain numbers or symbols";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Name(invalidName);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void name_NullInput_ThrowsException() {

        // Arrange
        String nullName = null;
        String message = "Name must not be null";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Name(nullName);
        });
        assertEquals(message, exception.getMessage());

    }

}
