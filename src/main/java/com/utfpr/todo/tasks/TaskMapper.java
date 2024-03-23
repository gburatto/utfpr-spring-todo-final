package com.utfpr.todo.tasks;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    
    public TaskModel fromInput(TaskInputDTO input) {

        return TaskModel.builder()
            .userId(input.getUserId())
            .title(input.getTitle())
            .description(input.getDescription())
            .priority(input.getPriority())
            .startAt(input.getStartAt())
            .endAt(input.getEndAt())
            .completed(false)
            .build();

    }

}