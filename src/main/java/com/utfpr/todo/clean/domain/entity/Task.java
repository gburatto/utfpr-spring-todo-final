package com.utfpr.todo.clean.domain.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import com.utfpr.todo.clean.domain.vo.tasks.Completed;
import com.utfpr.todo.clean.domain.vo.tasks.Description;
import com.utfpr.todo.clean.domain.vo.tasks.EndAt;
import com.utfpr.todo.clean.domain.vo.tasks.Priority;
import com.utfpr.todo.clean.domain.vo.tasks.StartAt;
import com.utfpr.todo.clean.domain.vo.tasks.Title;

import lombok.Getter;

// entity
@Getter
public class Task {
    
    private String id;

    private String userId;

    private Title title;

    private Description description;

    private Priority priority;

    private Completed completed;

    private StartAt startAt;

    private EndAt endAt;

    private Task(String taskId, String userId, String title,
                String description, String priority, boolean completed,
                LocalDateTime startAt, LocalDateTime endAt) {

            this.id = taskId;
            this.userId = userId;
            this.title = new Title(title);
            this.description = new Description(description);
            this.priority = new Priority(priority);
            this.completed = new Completed(completed);
            this.startAt = new StartAt(startAt);
            this.endAt = new EndAt(this.startAt.getValue(), endAt);

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



    public void complete() {
        completed.complete();
    }

    public String getTitle() {
        return title.getValue();
    }

    public String getDescription() {
        return description.getValue();
    }

    public String getPriority() {
        return priority.getValue();
    }

    public boolean isCompleted() {
        return completed.getValue();
    }

    public LocalDateTime getStartAt() {
        return startAt.getValue();
    }

    public LocalDateTime getEndAt() {
        return endAt.getValue();
    }

}
