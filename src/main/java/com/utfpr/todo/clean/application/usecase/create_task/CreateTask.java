package com.utfpr.todo.clean.application.usecase.create_task;

import com.utfpr.todo.clean.application.gateway.TaskGateway;
import com.utfpr.todo.clean.domain.entity.Task;

// use case
public class CreateTask {

    private final TaskGateway taskGateway;

    public CreateTask(final TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }
    
    public CreateTaskOutput execute(CreateTaskCommand command) {

        Task task = CreateTaskMapper.toTask(command);

        Task createdTask = taskGateway.save(task);

        // ExternalCalendarGateway.create(task);

        // taskEmailService.send(task);

        // taskNotificationService...

        // logger events...

        // send event...

        return CreateTaskMapper.toCreateTaskOutput(createdTask);
        
    }

}
