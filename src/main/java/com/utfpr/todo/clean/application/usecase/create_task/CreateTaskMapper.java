package com.utfpr.todo.clean.application.usecase.create_task;

import com.utfpr.todo.clean.domain.entity.Task;

public class CreateTaskMapper {

    public static Task toTask(CreateTaskCommand command) {
        return Task.create(
            command.getUserId(),
            command.getTitle(),
            command.getDescription(),
            command.getPriority(),
            command.getStartAt(),
            command.getEndAt());
    }
    
    public static CreateTaskOutput toCreateTaskOutput(Task task) {
        return CreateTaskOutput.builder()
            .id(task.getId())
            .userId(task.getUserId())
            .title(task.getTitle())
            .description(task.getDescription())
            .priority(task.getPriority())
            .completed(task.isCompleted())
            .startAt(task.getStartAt())
            .endAt(task.getEndAt())
            .build();
    }
    
}
