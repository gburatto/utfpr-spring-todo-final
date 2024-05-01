package com.utfpr.todo.clean.application.usecase.create_user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateUserOutput {
    
    private String id;

    private String username;

    private String name;
    
    private String email;

}
