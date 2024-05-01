package com.utfpr.todo.unit.tasks;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.vo.tasks.Title;
import com.utfpr.todo.exceptions.ValidationException;

public class TitleTest {
    
    @Test
    public void title_ValidInput_ShouldCreateTitle() {

        // Arrange
        String validTitle = "Task Title";

        // Act
        Title title = new Title(validTitle);

        // Assert
        assertNotNull(title);
        assertEquals(validTitle, title.getValue());

    }

    @Test
    public void title_InvalidInput_ThrowsException() {

        // Arrange
        String invalidTitle = "Task";
        String message = "Title must have at least 5 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Title(invalidTitle);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void title_NullInput_ThrowsException() {

        // Arrange
        String nullTitle = null;
        String message = "Title must have at least 5 characters";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            new Title(nullTitle);
        });
        assertEquals(message, exception.getMessage());

    }
}
