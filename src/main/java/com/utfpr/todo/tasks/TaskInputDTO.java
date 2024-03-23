package com.utfpr.todo.tasks;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TaskInputDTO {
  
  @NotBlank
  private String title;

  @NotBlank
  private String description;

  @NotBlank
  private String priority;

  private LocalDateTime startAt;

  private LocalDateTime endAt;

}
