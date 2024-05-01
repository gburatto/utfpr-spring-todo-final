package com.utfpr.todo.users;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserModel fromInput(UserInputDTO input) {

        return UserModel.builder()
            .username(input.getUsername())
            .name(input.getName())
            .email(input.getEmail())
            .password(input.getPassword())
            .build();

    }
    
    public UserOutputDTO fromModel(UserModel user) {
        return UserOutputDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

}
