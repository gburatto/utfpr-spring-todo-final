package com.utfpr.todo.clean.infra.api.output;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserOutputDTO {
    
    private String id;
    private String username;
    private String name;
    private String email;

}
