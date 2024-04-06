package com.utfpr.todo.clean.application.usecase.complete_task;

import com.utfpr.todo.clean.domain.entity.Task;

public class CompleteTaskMapper {

    public static CompleteTaskOutput toCreateTaskOutput(Task task) {
        
        return CompleteTaskOutput.builder()
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
