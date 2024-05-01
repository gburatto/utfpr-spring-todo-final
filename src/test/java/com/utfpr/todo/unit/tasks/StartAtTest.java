package com.utfpr.todo.unit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.tasks.StartAt;
import com.utfpr.todo.exceptions.ValidationException;

public class StartAtTest {
    
    @Test
    public void startAt_ValidInput_ShouldCreateStartAt() {

        // Arrange
        LocalDateTime validStartAt = LocalDateTime.now().plusDays(2);

        // Act
        StartAt startAt = new StartAt(validStartAt);

        // Assert
        assertNotNull(startAt);
        assertEquals(validStartAt, startAt.getValue());

    }

    @Test
    public void startAt_InvalidInput_ThrowsException() {

        // Arrange
        LocalDateTime invalidStartAt = LocalDateTime.now().minusDays(1);
        String message = "StartAt must be a future date";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new StartAt(invalidStartAt);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void startAt_NullInput_ThrowsException() {

        // Arrange
        LocalDateTime nullStartAt = null;
        String message = "StartAt cannot be null and must be a future date";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new StartAt(nullStartAt);
        });
        assertEquals(message, exception.getMessage());

    }

}
