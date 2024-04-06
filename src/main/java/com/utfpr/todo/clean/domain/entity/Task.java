package com.utfpr.todo.clean.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.utfpr.todo.clean.domain.vo.Title;
import com.utfpr.todo.exceptions.ValidationException;

import lombok.Data;

// entity
@Data
public class Task {
    
    private String id;

    private String userId;

    private Title title;

    private String description;

    private String priority;

    private boolean completed;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    private Task(String taskId, String userId, String title,
                String description, String priority, boolean completed,
                LocalDateTime startAt, LocalDateTime endAt) {
                    
            this.isInvalidDescription(description);
            this.isInvalidPriority(priority);
            this.isInvalidStartAt(startAt);
            this.isInvalidEndAt(startAt, endAt);

            this.id = taskId;
            this.userId = userId;
            this.title = new Title(title);
            this.description = description;
            this.priority = priority;
            this.completed = completed;
            this.startAt = startAt;
            this.endAt = endAt;

    }

    public static Task create(String userId, String title,
                              String description, String priority,
                              LocalDateTime startAt, LocalDateTime endAt) {

        String taskId = UUID.randomUUID().toString();

        boolean completed = false;

        return new Task(taskId, userId, title, description, priority, completed, startAt, endAt);

    }

    public static Task restore(String taskId, String userId, String title,
                                String description, String priority, boolean completed,
                                LocalDateTime startAt, LocalDateTime endAt) {

        return new Task(taskId, userId, title, description, priority, completed, startAt, endAt);
        
    }

    private void isInvalidDescription(String description) {
        if (description == null || description.length() < 10) {
            throw new ValidationException("Description must have at least 10 characters");
        }
    }

    private void isInvalidPriority(String priority) {
        if ((priority == null) ||
            ((!priority.equals("low")) &&
             (!priority.equals("medium")) &&
             (!priority.equals("high"))
            )
        ) {
            throw new ValidationException("Priority must be low, medium or high");
        }
    }

    private void isInvalidStartAt(LocalDateTime startAt) {

        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(startAt)) {
            throw new ValidationException("StartAt must be a future date");
        }
    
    }

    private void isInvalidEndAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (endAt.isBefore(startAt)) {
            throw new ValidationException("EndAt must be after StartAt");
        }
    }

    public String getTitle() {
        return title.getValue();
    }

}
