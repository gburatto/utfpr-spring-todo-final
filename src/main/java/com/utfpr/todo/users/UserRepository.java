package com.utfpr.todo.users;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel, String> {

  UserModel findByUsername(String username);

}
