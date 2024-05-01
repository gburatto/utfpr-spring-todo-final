package com.utfpr.todo.unit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.tasks.Completed;
import com.utfpr.todo.exceptions.ValidationException;

public class CompletedTest {

    @Test
    public void completed_ValidInput_ShouldCreateCompleted() {

        // Arrange
        boolean completedValue = false;

        // Act
        Completed completed = new Completed(completedValue);

        // Assert
        assertNotNull(completed);
        assertEquals(completedValue, completed.getValue());

    }

    @Test
    public void complete_NotYetCompleted_ChangesValueToTrue() {

        // Arrange
        boolean completedValue = false;
        Completed completed = new Completed(completedValue);

        // Act
        completed.complete();

        // Assert
        assertTrue(completed.getValue());

    }

    @Test
    public void complete_AlreadyCompleted_ThrowsException() {

        // Arrange
        boolean completedValue = true;
        Completed completed = new Completed(completedValue);
        String message = "Task is already completed";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            completed.complete();
        });
        assertEquals(message, exception.getMessage());

    }

}
