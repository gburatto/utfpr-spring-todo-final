package com.utfpr.todo.clean.domain.vo;

import java.time.LocalDateTime;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class EndAt {
    
    private LocalDateTime value;

    public EndAt(LocalDateTime startAt, LocalDateTime value) {
        this.isInvalidEndAt(startAt, value);
        this.value = value;
    }

    private void isInvalidEndAt(LocalDateTime startAt, LocalDateTime endAt) {
        if (endAt.isBefore(startAt)) {
            throw new ValidationException("EndAt must be after StartAt");
        }
    }
    
    public LocalDateTime getValue() {
        return value;
    }

}
