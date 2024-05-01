package com.utfpr.todo.unit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.tasks.Description;
import com.utfpr.todo.exceptions.ValidationException;

public class DescriptionTest {
    @Test
    public void description_ValidInput_ShouldCreateDescription() {

        // Arrange
        String validDescription = "Task Description";

        // Act
        Description description = new Description(validDescription);

        // Assert
        assertNotNull(description);
        assertEquals(validDescription, description.getValue());

    }

    @Test
    public void description_InvalidInput_ThrowsException() {

        // Arrange
        String invalidDescription = "Task";
        String message = "Description must have at least 10 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Description(invalidDescription);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void title_NullInput_ThrowsException() {

        // Arrange
        String nullDescription = null;
        String message = "Description must have at least 10 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Description(nullDescription);
        });
        assertEquals(message, exception.getMessage());

    }
}
