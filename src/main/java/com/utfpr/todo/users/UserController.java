package com.utfpr.todo.users;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

  @Autowired
  private UserRepository userRepository;

  @PostMapping
  public ResponseEntity<?> create(@RequestBody UserModel user) {

    UserModel userModel = userRepository.findByUsername(user.getUsername());

    if (userModel != null) {
      // throw new RuntimeException("Username already exists");
      return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
          Collections.singletonMap("error", "Username already exists"));
    }

    String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

    user.setPassword(hashedPassword);

    UserModel newUser = userRepository.save(user);

    return ResponseEntity.status(HttpStatus.CREATED).body(newUser);

  }

}
