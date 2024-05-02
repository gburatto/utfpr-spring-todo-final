package com.utfpr.todo.clean.domain.entity;

import java.util.UUID;

import com.utfpr.todo.clean.domain.vo.users.Email;
import com.utfpr.todo.clean.domain.vo.users.Name;
import com.utfpr.todo.clean.domain.vo.users.Password;
import com.utfpr.todo.clean.domain.vo.users.Username;

import at.favre.lib.crypto.bcrypt.BCrypt;
import lombok.Getter;

// entity
@Getter
public class User {
    
  private String id;
  private Username username;
  private Name name;
  private Email email;
  private Password password;

  private User(String id, String username, String name, String email, String password) {

    this.id = id;
    this.username = new Username(username);
    this.name = new Name(name);
    this.email = new Email(email);
    this.password = new Password(password);

  }

  public static User create(String username, String name, String email, String password) {

    String id = UUID.randomUUID().toString();
        
    return new User(id, username, name, email, password);
        
  }

  public static User restore(String id, String username, String name, String email, String password) {

    return new User(id, username, name, email, password);

  }

  public String getUsername() {
    return username.getValue();
  }

  public String getName() {
    return name.getValue();
  }

  public String getEmail() {
    return email.getValue();
  }

  public String getPassword() {
    return password.getValue();
  }

}
