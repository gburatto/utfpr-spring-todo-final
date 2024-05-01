package com.utfpr.todo.unit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.tasks.Priority;
import com.utfpr.todo.exceptions.ValidationException;

public class PriorityTest {
    
    @Test
    public void priority_ValidInput_ShouldCreatePriority() {

        // Arrange
        String validPriority = "high";

        // Act
        Priority priority = new Priority(validPriority);

        // Assert
        assertNotNull(priority);
        assertEquals(validPriority, priority.getValue());

    }

    @Test
    public void priority_InvalidInput_ThrowsException() {

        // Arrange
        String invalidPriority = "other";
        String message = "Priority must be low, medium or high";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Priority(invalidPriority);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void priority_NullInput_ThrowsException() {

        // Arrange
        String nullPriority = null;
        String message = "Priority must be low, medium or high";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Priority(nullPriority);
        });
        assertEquals(message, exception.getMessage());

    }

}
