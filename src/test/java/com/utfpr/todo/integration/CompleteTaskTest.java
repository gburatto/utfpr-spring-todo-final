package com.utfpr.todo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.application.usecase.complete_task.CompleteTask;
import com.utfpr.todo.clean.application.usecase.complete_task.CompleteTaskCommand;
import com.utfpr.todo.clean.application.usecase.complete_task.CompleteTaskOutput;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskCommand;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskOutput;
import com.utfpr.todo.clean.infra.gateway.TaskMemoryGateway;
import com.utfpr.todo.exceptions.NotFoundException;
import com.utfpr.todo.exceptions.ValidationException;

public class CompleteTaskTest {
    
    @Test
    public void completeTask_WithValidTaskId_ReturnsTaskOutput() {

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

        boolean initalCompleted = false;

        CreateTaskOutput createdTask = createTask.execute(command);

        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
        assertEquals(initalCompleted, createdTask.isCompleted());

        CompleteTask completeTask = new CompleteTask(taskGateway);

        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(createdTask.getId());

        boolean expectedCompleted = true;

        // Act
        CompleteTaskOutput output = completeTask.execute(completeTaskCommand);

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
    public void completeTask_WithInvalidTaskId_ThrowsException() {

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

        boolean initalCompleted = false;

        CreateTaskOutput createdTask = createTask.execute(command);

        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
        assertEquals(initalCompleted, createdTask.isCompleted());

        CompleteTask completeTask = new CompleteTask(taskGateway);

        String invalidId = "0";
        CompleteTaskCommand completeTaskCommandWithInvalidId = new CompleteTaskCommand(invalidId);

        String message = "Task not found";

        // Act / Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            completeTask.execute(completeTaskCommandWithInvalidId);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void completeTask_WithNullTaskId_ThrowsException() {

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

        boolean initalCompleted = false;

        CreateTaskOutput createdTask = createTask.execute(command);

        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
        assertEquals(initalCompleted, createdTask.isCompleted());

        CompleteTask completeTask = new CompleteTask(taskGateway);

        String nullId = null;
        CompleteTaskCommand completeTaskCommandWithNullId = new CompleteTaskCommand(nullId);

        String message = "Task not found";

        // Act / Assert
        NotFoundException exception = assertThrows(NotFoundException.class, () -> {
            completeTask.execute(completeTaskCommandWithNullId);
        });
        assertEquals(message, exception.getMessage());

    }

    @Test
    public void completeTask_AlreadyCompleted_ThrowsException() {

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

        boolean initalCompleted = false;

        CreateTaskOutput createdTask = createTask.execute(command);

        assertNotNull(createdTask);
        assertNotNull(createdTask.getId());
        assertEquals(initalCompleted, createdTask.isCompleted());

        CompleteTask completeTask = new CompleteTask(taskGateway);

        CompleteTaskCommand completeTaskCommand = new CompleteTaskCommand(createdTask.getId());

        boolean expectedCompleted = true;

        CompleteTaskOutput output = completeTask.execute(completeTaskCommand);
        assertNotNull(output);
        assertEquals(expectedCompleted, output.isCompleted());

        String message = "Task is already completed";

        // Act / Assert
        ValidationException exception = assertThrows(ValidationException.class, () -> {
            completeTask.execute(completeTaskCommand);
        });
        assertEquals(message, exception.getMessage());

    }

}
