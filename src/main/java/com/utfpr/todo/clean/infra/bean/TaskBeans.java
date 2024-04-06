package com.utfpr.todo.clean.infra.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.utfpr.todo.clean.application.gateway.TaskGateway;
import com.utfpr.todo.clean.application.usecase.create_task.CreateTask;

@Configuration
public class TaskBeans {

    private final TaskGateway taskGateway;

    public TaskBeans(final TaskGateway taskGateway) {
        this.taskGateway = taskGateway;
    }
    
    @Bean
    public CreateTask createTask() {
        return new CreateTask(taskGateway);
    }

}
