package com.utfpr.todo.clean.infra.gateway;

import org.springframework.stereotype.Service;

import com.utfpr.todo.clean.application.gateway.UserGateway;
import com.utfpr.todo.clean.domain.entity.User;
import com.utfpr.todo.clean.infra.model.UserModel;
import com.utfpr.todo.clean.infra.repository.UserModelJpaRepository;

// fws & drivers
@Service
public class UserJpaRepositoryGateway implements UserGateway{

    private final UserModelJpaRepository userModelJpaRepository;

    public UserJpaRepositoryGateway(UserModelJpaRepository userModelJpaRepository) {
        this.userModelJpaRepository = userModelJpaRepository;
    }

    @Override
    public User save(User user) {
        
        UserModel userModel = UserModel.fromAggregate(user);

        UserModel createdUser = this.userModelJpaRepository.save(userModel);

        return createdUser.toAggregate();

    }

    @Override
    public User findById(String id) {
        
        return this.userModelJpaRepository.findById(id)
                .map(UserModel::toAggregate)
                .orElse(null);

    }

    @Override
    public User findByUsername(String username) {
        
        UserModel userModel = userModelJpaRepository.findByUsername(username);
        
        if (userModel != null) {
            User user = userModel.toAggregate();
            return user;
        } else {
            return null;
        }

    }
    
}
