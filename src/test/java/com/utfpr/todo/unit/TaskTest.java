package com.utfpr.todo.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.domain.Task;

public class TaskTest {

    @Test
    public void createTask_ValidInput_ShouldCreateTask() {

        // Arrange
        String taskId = UUID.randomUUID().toString();
        String userId = UUID.randomUUID().toString();
        String title = "Task Title";
        String description = "Task Description";
        String priority = "high";
        boolean completed = false;
        LocalDateTime startAt = LocalDateTime.now().plusDays(1);
        LocalDateTime endAt = LocalDateTime.now().plusDays(2);

        // Act
        Task task = new Task(taskId, userId, title, description, priority, completed, startAt, endAt);
        
        // Assert
        assertNotNull(task);
        assertEquals(taskId, task.getId());
        assertEquals(userId, task.getUserId());
        assertEquals(title, task.getTitle());
        assertEquals(description, task.getDescription());
        assertEquals(priority, task.getPriority());
        assertEquals(completed, task.isCompleted());
        assertEquals(startAt, task.getStartAt());
        assertEquals(endAt, task.getEndAt());

    }

}
