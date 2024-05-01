package com.utfpr.todo.clean.application.usecase.create_user;

import com.utfpr.todo.clean.application.gateway.UserGateway;
import com.utfpr.todo.clean.domain.entity.User;

// use case
public class CreateUser {
    
    private final UserGateway userGateway;

    public CreateUser(final UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    public CreateUserOutput execute(CreateUserCommand command) {

        User user = CreateUserMapper.toUser(command);

        User createdUser = userGateway.save(user);

        return CreateUserMapper.toCreateUserOutput(createdUser);

    }

}
