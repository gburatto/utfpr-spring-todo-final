package com.utfpr.todo.clean.application.gateway;

import com.utfpr.todo.clean.domain.entity.Task;

// interface adapter
public interface TaskGateway {
    
    Task save(Task task);

    Task findById(String id);

}
