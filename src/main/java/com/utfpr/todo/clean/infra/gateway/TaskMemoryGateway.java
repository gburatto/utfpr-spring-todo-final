package com.utfpr.todo.clean.infra.gateway;

import java.util.HashMap;
import java.util.Map;

import com.utfpr.todo.clean.application.gateway.TaskGateway;
import com.utfpr.todo.clean.domain.entity.Task;

// frameworks & drivers
public class TaskMemoryGateway implements TaskGateway {

    private Map<String, Task> tasks = new HashMap<>();

    @Override
    public Task save(Task task) {
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Task findById(String id) {
        return tasks.get(id);
    }
    
}
