package com.utfpr.todo.clean.infra.bean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.utfpr.todo.clean.application.gateway.UserGateway;
import com.utfpr.todo.clean.application.usecase.create_user.CreateUser;

@Configuration
public class UserBeans {
    
    private final UserGateway userGateway;

    public UserBeans(final UserGateway userGateway) {
        this.userGateway = userGateway;
    }

    @Bean
    public CreateUser createUser() {
        return new CreateUser(userGateway);
    }
    
}
