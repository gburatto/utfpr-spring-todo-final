package com.utfpr.todo.clean.application.usecase.create_user;

import com.utfpr.todo.clean.domain.entity.User;

public class CreateUserMapper {
    
    public static User toUser(CreateUserCommand command) {
        return User.create(
            command.getUsername(),
            command.getName(),
            command.getEmail(),
            command.getPassword());
    }
    
    public static CreateUserOutput toCreateUserOutput(User user) {
        
        return CreateUserOutput.builder()
            .id(user.getId())
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

}
