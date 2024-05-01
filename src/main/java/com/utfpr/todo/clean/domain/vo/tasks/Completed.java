package com.utfpr.todo.clean.domain.vo.tasks;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Completed {
    
    private boolean value;

    public Completed(boolean value) {
        this.value = value;
    }
    
    public void complete() {
        
        if (value) {
            throw new ValidationException("Task is already completed");
        }

        value = true;

    }

    public boolean getValue() {
        return value;
    }

}
