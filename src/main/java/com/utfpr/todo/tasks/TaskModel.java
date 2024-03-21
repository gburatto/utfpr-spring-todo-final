package com.utfpr.todo.tasks;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity(name = "tbl-tasks")
public class TaskModel {

  @Id
  @UuidGenerator
  private String id;

  private String userId;

  @NotBlank
  @Column(nullable = false)
  private String title;

  @NotBlank
  @Column(nullable = false)
  private String description;

  @NotBlank
  @Column(nullable = false)
  private String priority;

  private boolean completed;

  private LocalDateTime startAt;

  private LocalDateTime endAt;

  @CreationTimestamp
  private LocalDateTime createdAt;

}
