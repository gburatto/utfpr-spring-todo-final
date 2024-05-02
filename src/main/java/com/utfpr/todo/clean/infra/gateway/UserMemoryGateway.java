package com.utfpr.todo.clean.infra.gateway;

import java.util.HashMap;
import java.util.Map;

import com.utfpr.todo.clean.application.gateway.UserGateway;
import com.utfpr.todo.clean.domain.entity.User;

// frameworks & drivers
public class UserMemoryGateway implements UserGateway {

    private Map<String, User> users = new HashMap<>();

    @Override
    public User save(User user) {
        users.put(user.getId(), user);
        return user;
    }

    @Override
    public User findById(String id) {
        return users.get(id);
    }

    @Override
    public User findByUsername(String username) {
        return users.get(username);
    }

    
}
