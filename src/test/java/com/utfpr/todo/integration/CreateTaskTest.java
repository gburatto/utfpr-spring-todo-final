package com.utfpr.todo.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDateTime;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskCommand;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTaskOutput;
import com.utfpr.todo.clean.infra.gateway.TaskMemoryGateway;

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

}
