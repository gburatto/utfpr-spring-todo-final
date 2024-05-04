package com.utfpr.todo.clean.infra.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.utfpr.todo.clean.domain.entity.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "tbl-users")
public class UserModel {

  @Id
  @UuidGenerator
  private String id;

  @Column(nullable = false)
  private String username;

  @Column(nullable = false)
  private String name;

  @Column(nullable = false)
  private String email;

  @Column(nullable = false)
  private String password;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public User toAggregate() {
    return User.restore(
      this.id,
      this.username,
      this.name,
      this.email,
      this.password
    );
  }

  public static UserModel fromAggregate(User user) {
    return UserModel.builder()
      .id(user.getId())
      .username(user.getUsername())
      .name(user.getName())
      .email(user.getEmail())
      .password(user.getPassword())
      .build();
  }

}
