package com.utfpr.todo.clean.domain;

import java.time.LocalDateTime;

import com.utfpr.todo.exceptions.ValidationException;

import lombok.Data;

@Data
public class Task {
    
    private String id;

    private String userId;

    private String title;

    private String description;

    private String priority;

    private boolean completed;

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public Task(String taskId, String userId, String title,
                String description, String priority, boolean completed,
                LocalDateTime startAt, LocalDateTime endAt) {
        
            // if (this.isInvalidTitle(title)) {
            //     throw...
            // }

            this.isInvalidTitle(title);
            this.isInvalidDescription(description);
            this.isInvalidPriority(priority);
            this.isInvalidStartAt(startAt);
            this.isInvalidEndAt(startAt, endAt);

            this.id = taskId;
            this.userId = userId;
            this.title = title;
            this.description = description;
            this.priority = priority;
            this.completed = completed;
            this.startAt = startAt;
            this.endAt = endAt;

    }

    private void isInvalidTitle(String title) {
        if (title == null || title.length() < 5) {
            throw new ValidationException("Title must have at least 5 characters");
        }
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

}
