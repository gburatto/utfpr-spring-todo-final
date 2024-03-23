package com.utfpr.todo.tasks;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskOutputDTO {

  private String id;

  private String title;

  private String description;

  private String priority;

  private boolean completed;

  private LocalDateTime startAt;

  private LocalDateTime endAt;

}
