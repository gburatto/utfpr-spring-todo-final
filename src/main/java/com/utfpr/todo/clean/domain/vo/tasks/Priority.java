package com.utfpr.todo.clean.domain.vo.tasks;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Priority {
    
    private String value;

    public Priority(String value) {
        this.isInvalidPriority(value);
        this.value = value;
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
    
    public String getValue() {
        return value;
    }

}
