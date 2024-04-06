package com.utfpr.todo.clean.infra.repository;

import org.springframework.data.repository.CrudRepository;

import com.utfpr.todo.clean.infra.model.TaskModel;

public interface TaskModelJpaRepository extends CrudRepository<TaskModel, String> {
        
}
