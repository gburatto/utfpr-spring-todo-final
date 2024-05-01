package com.utfpr.todo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.EndAt;
import com.utfpr.todo.exceptions.ValidationException;

public class EndAtTest {
    
    @Test
    public void endAt_ValidInput_ShouldCreateEndAt() {

        // Arrange
        LocalDateTime validStartAt = LocalDateTime.now().plusDays(2);
        LocalDateTime validEndAt = LocalDateTime.now().plusDays(3);

        // Act
        EndAt endAt = new EndAt(validStartAt, validEndAt);

        // Assert
        assertNotNull(endAt);
        assertEquals(validEndAt, endAt.getValue());

    }

    @Test
    public void endAt_InvalidInput_ThrowsException() {

        // Arrange
        LocalDateTime validStartAt = LocalDateTime.now().plusDays(2);
        LocalDateTime invalidEndAt = LocalDateTime.now().plusDays(1);
        String message = "EndAt must be after StartAt";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new EndAt(validStartAt, invalidEndAt);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void endAt_NullInput_ThrowsException() {

        // Arrange
        LocalDateTime validStartAt = LocalDateTime.now().plusDays(2);
        LocalDateTime nullEndAt = null;
        String message = "EndAt cannot be null and must be after StartAt";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new EndAt(validStartAt, nullEndAt);
        });
        assertEquals(message, exception.getMessage());

    }

}
