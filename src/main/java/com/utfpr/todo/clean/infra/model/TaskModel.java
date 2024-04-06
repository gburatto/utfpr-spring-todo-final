package com.utfpr.todo.clean.infra.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UuidGenerator;

import com.utfpr.todo.clean.domain.entity.Task;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
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

  @Column(nullable = false)
  private String title;

  @Column(nullable = false)
  private String description;

  @Column(nullable = false)
  private String priority;

  private boolean completed;

  private LocalDateTime startAt;

  private LocalDateTime endAt;

  @CreationTimestamp
  private LocalDateTime createdAt;

  public Task toAggregate() {
    return Task.restore(
      this.id,
      this.userId,
      this.title,
      this.description,
      this.priority,
      this.completed,
      this.startAt,
      this.endAt
    );
  }

  public static TaskModel fromAggregate(Task task) {
    return TaskModel.builder()
      .id(task.getId())
      .userId(task.getUserId())
      .title(task.getTitle())
      .description(task.getDescription())
      .priority(task.getPriority())
      .completed(task.isCompleted())
      .startAt(task.getStartAt())
      .endAt(task.getEndAt())
      .build();
  }

}
