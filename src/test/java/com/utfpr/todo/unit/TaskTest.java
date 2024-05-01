package com.utfpr.todo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.entity.Task;
import com.utfpr.todo.exceptions.ValidationException;

public class TaskTest {

    @Test
    public void createTask_ValidInput_ShouldCreateTask() {

        // Arrange
        String userId = UUID.randomUUID().toString();
        String title = "Task Title";
        String description = "Task Description";
        String priority = "high";
        LocalDateTime startAt = LocalDateTime.now().plusDays(1);
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

        boolean expectedCompleted = false;

        // Act
        Task task = Task.create(userId, title, description, priority, startAt, endAt);
        
        // Assert
        assertNotNull(task);
        assertNotNull(task.getId());
        assertEquals(userId, task.getUserId());
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(priority, task.getPriority());
        assertEquals(expectedCompleted, task.isCompleted());
        assertEquals(startAt, task.getStartAt());
        assertEquals(endAt, task.getEndAt());

    }

    @Test
    public void createTask_InvalidInput_ThrowsException() {

        // Arrange
        String userId = UUID.randomUUID().toString();
        String invalidTitle = "Task";
        String invalidDescription = "Task";
        String invalidPriority = "none";
        LocalDateTime invalidStartAt = LocalDateTime.now().minusDays(1);
        LocalDateTime invalidEndAt = LocalDateTime.now().minusDays(2);

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            Task.create(userId, invalidTitle, invalidDescription,
                        invalidPriority, invalidStartAt, invalidEndAt);
        });

    }

    @Test
    public void createTask_NullInput_ThrowsException() {

        // Arrange
        String userId = null;
        String nullTitle = null;
        String nullDescription = null;
        String nullPriority = null;
        LocalDateTime nullStartAt = null;
        LocalDateTime nullEndAt = null;

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            Task.create(userId, nullTitle, nullDescription,
                        nullPriority, nullStartAt, nullEndAt);
        });

    }

}
