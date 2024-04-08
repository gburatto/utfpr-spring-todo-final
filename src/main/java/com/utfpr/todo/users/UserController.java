package com.utfpr.todo.users;

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
  private UserService userService;

  @Autowired
  private UserMapper userMapper;

  @PostMapping
  public ResponseEntity<UserOutputDTO> create(@RequestBody UserModel user) {

    userService.findByUsername(user.getUsername());

    String hashedPassword = BCrypt.withDefaults().hashToString(12, user.getPassword().toCharArray());

    user.setPassword(hashedPassword);

    UserModel newUser = userService.save(user);

    UserOutputDTO output = userMapper.toOutputDTO(newUser);

    return ResponseEntity.status(HttpStatus.CREATED).body(output);

  }

}
