package com.utfpr.todo.users;

import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    
    public UserOutputDTO toOutputDTO(UserModel user) {
        return UserOutputDTO.builder()
            .id(user.getId())
            .username(user.getUsername())
            .name(user.getName())
            .email(user.getEmail())
            .build();
    }

}
