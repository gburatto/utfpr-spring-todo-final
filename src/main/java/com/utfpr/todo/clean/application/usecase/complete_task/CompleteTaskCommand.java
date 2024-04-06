package com.utfpr.todo.clean.application.usecase.complete_task;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompleteTaskCommand {
    
    private String taskId;
    
}
