package com.utfpr.todo.users;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "tbl-users")
public class UserModel {

  @Id
  @UuidGenerator
  private String id;

  private String username;
  private String name;
  private String email;
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  // @JsonIgnore
  // public String getPassword() {
  //   return password;
  // }

  // @JsonProperty
  // public void setPassword(String password) {
  //   this.password = password;
  // }

}
