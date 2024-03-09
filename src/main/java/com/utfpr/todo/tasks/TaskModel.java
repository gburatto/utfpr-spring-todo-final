package com.utfpr.todo.tasks;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
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
  @GeneratedValue(generator = "UUID")
  private UUID id;

  // private UUID userId;

  // @Column(nullable = false)
  @NotBlank
  private String title;

  // @Column(nullable = false)
  @NotBlank
  private String description;

  private boolean completed;

  // @Column(nullable = false)
  @NotBlank
  private String priority;

  // private LocalDateTime startAt;

  private LocalDateTime endAt;

  // @CreationTimestamp
  private LocalDateTime createdAt;

}
