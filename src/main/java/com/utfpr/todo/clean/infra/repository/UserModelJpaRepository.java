package com.utfpr.todo.clean.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.utfpr.todo.clean.infra.model.UserModel;

public interface UserModelJpaRepository extends JpaRepository<UserModel, String> {

  UserModel findByUsername(String username);

}
