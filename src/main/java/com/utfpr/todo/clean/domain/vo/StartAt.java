package com.utfpr.todo.clean.domain.vo;

import java.time.LocalDateTime;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class StartAt {
    
    private LocalDateTime value;

    public StartAt(LocalDateTime value) {
        this.isInvalidStartAt(value);
        this.value = value;
    }

    private void isInvalidStartAt(LocalDateTime startAt) {

        LocalDateTime currentDate = LocalDateTime.now();
        if (currentDate.isAfter(startAt)) {
            throw new ValidationException("StartAt must be a future date");
        }
    
    }
    
    public LocalDateTime getValue() {
        return value;
    }

}
