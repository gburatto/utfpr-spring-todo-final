package com.utfpr.todo.clean.application.usecase.complete_task;

import com.utfpr.todo.clean.application.gateway.TaskGateway;
import com.utfpr.todo.clean.domain.entity.Task;
import com.utfpr.todo.exceptions.NotFoundException;

public class CompleteTask {

    private final TaskGateway taskGateway;

    public CompleteTask(TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }

    public CompleteTaskOutput execute(CompleteTaskCommand command) {
        
        Task task = taskGateway.findById(command.getTaskId());

        if (task == null) {
            throw new NotFoundException("Task not found");
        }

        task.complete();

        Task updatedTask = taskGateway.save(task);

        return CompleteTaskMapper.toCreateTaskOutput(updatedTask);

    }

}
