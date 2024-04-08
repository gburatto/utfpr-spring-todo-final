package com.utfpr.todo.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utfpr.todo.exceptions.ValidationException;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public UserModel findByUsername(String email) {

        UserModel userModel = userRepository.findByUsername(email);
        
        if (userModel != null) {
          throw new ValidationException("Username already exists");
        }

        return userModel;

    }

    public UserModel save(UserModel user) {

        UserModel newUser = userRepository.save(user);

        return newUser;

    }
    
}
