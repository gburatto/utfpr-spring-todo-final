package com.utfpr.todo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskCommand;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskOutput;
import com.utfpr.todo.clean.infra.gateway.TaskMemoryGateway;
import com.utfpr.todo.exceptions.ValidationException;

public class CreateTaskTest {
    
    @Test
    public void createTask_WithDataValid_ReturnsTaskOutput() {

        // Arrange
        TaskMemoryGateway taskGateway = new TaskMemoryGateway();

        CreateTask createTask = new CreateTask(taskGateway);

        CreateTaskCommand command = CreateTaskCommand.builder()
            .userId(UUID.randomUUID().toString())
            .title("Task Title")
            .description("Task Description")
            .priority("low")
            .startAt(LocalDateTime.now().plusDays(1))
            .endAt(LocalDateTime.now().plusDays(2))
            .build();

        boolean expectedCompleted = false;

        // Act
        CreateTaskOutput output = createTask.execute(command);

        // Assert
        assertNotNull(output);
        assertNotNull(output.getId());
        assertEquals(command.getTitle(), output.getTitle());
        assertEquals(command.getDescription(), output.getDescription());
        assertEquals(command.getPriority(), output.getPriority());
        assertEquals(command.getStartAt(), output.getStartAt());
        assertEquals(command.getEndAt(), output.getEndAt());
        assertEquals(expectedCompleted, output.isCompleted());

    }

    @Test
    public void createTask_WithDataInvalid_ThrowsException() {

        // Arrange
        TaskMemoryGateway taskGateway = new TaskMemoryGateway();

        CreateTask createTask = new CreateTask(taskGateway);

        CreateTaskCommand invalidCommand = CreateTaskCommand.builder()
            .userId(UUID.randomUUID().toString())
            .title("Task") // Invalid Title
            .description("Task") // Invalid Description
            .priority("other") // Invalid Priority
            .startAt(LocalDateTime.now().minusDays(1)) // Invalid StartAt
            .endAt(LocalDateTime.now().minusDays(2)) // Invalid EndAt
            .build();

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            createTask.execute(invalidCommand);
        });

    }

    @Test
    public void createTask_WithDataNull_ThrowsException() {

        // Arrange
        TaskMemoryGateway taskGateway = new TaskMemoryGateway();

        CreateTask createTask = new CreateTask(taskGateway);

        CreateTaskCommand nullCommand = CreateTaskCommand.builder().build();

        // Act / Assert
        assertThrows(ValidationException.class, () -> {
            createTask.execute(nullCommand);
        });

    }

}
