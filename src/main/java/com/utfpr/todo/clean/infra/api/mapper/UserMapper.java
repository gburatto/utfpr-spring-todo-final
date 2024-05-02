package com.utfpr.todo.clean.infra.api.mapper;

import org.springframework.stereotype.Component;

import com.utfpr.todo.clean.infra.api.input.UserInputDTO;
import com.utfpr.todo.clean.infra.api.output.UserOutputDTO;
import com.utfpr.todo.clean.infra.model.UserModel;

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
