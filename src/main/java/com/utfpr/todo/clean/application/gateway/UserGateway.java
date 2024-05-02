package com.utfpr.todo.clean.application.gateway;

import com.utfpr.todo.clean.domain.entity.User;

// interface adapter
public interface UserGateway {

    User save(User user);

    User findById(String id);

    User findByUsername(String username);
   
}
