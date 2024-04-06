package com.utfpr.todo.clean.infra.gateway;

import org.springframework.stereotype.Service;

import com.utfpr.todo.clean.application.gateway.TaskGateway;
import com.utfpr.todo.clean.domain.entity.Task;
import com.utfpr.todo.clean.infra.model.TaskModel;
import com.utfpr.todo.clean.infra.repository.TaskModelJpaRepository;

// fws & drivers
@Service
public class TaskJpaRepositoryGateway implements TaskGateway {

    private final TaskModelJpaRepository taskModelJpaRepository;

    public TaskJpaRepositoryGateway(TaskModelJpaRepository taskModelJpaRepository) {
        this.taskModelJpaRepository = taskModelJpaRepository;
    }

    @Override
    public Task save(Task task) {

        TaskModel taskModel = TaskModel.fromAggregate(task);

        TaskModel createdTask = this.taskModelJpaRepository.save(taskModel);

        return createdTask.toAggregate();

    }

    @Override
    public Task findById(String id) {

        return this.taskModelJpaRepository.findById(id)
                .map(TaskModel::toAggregate)
                .orElse(null);

    }
    
}
