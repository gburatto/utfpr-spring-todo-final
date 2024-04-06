package com.utfpr.todo.clean.infra.api.mapper;

import org.springframework.stereotype.Component;

import com.utfpr.todo.clean.infra.api.input.TaskInputDTO;
import com.utfpr.todo.clean.infra.api.output.TaskOutputDTO;
import com.utfpr.todo.clean.infra.model.TaskModel;

@Component
public class TaskMapper {
    
    public TaskModel fromInput(TaskInputDTO input, String userId) {

        return TaskModel.builder()
            .userId(userId)
            .title(input.getTitle())
            .description(input.getDescription())
            .priority(input.getPriority())
            .startAt(input.getStartAt())
            .endAt(input.getEndAt())
            .completed(false)
            .build();

    }

    public TaskOutputDTO fromModel(TaskModel taskModel) {

        return TaskOutputDTO.builder()
            .id(taskModel.getId())
            .userId(taskModel.getUserId())
            .title(taskModel.getTitle())
            .description(taskModel.getDescription())
            .priority(taskModel.getPriority())
            .completed(taskModel.isCompleted())
            .startAt(taskModel.getStartAt())
            .endAt(taskModel.getEndAt())
            .build();

    }

}
