package com.utfpr.todo.clean.domain.vo.tasks;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Description {

    private String value;

    public Description(String value) {
        this.isInvalidDescription(value);
        this.value = value;
    }

    private void isInvalidDescription(String description) {
        if (description == null || description.length() < 10) {
            throw new ValidationException("Description must have at least 10 characters");
        }
    }
    
    public String getValue() {
        return value;
    }

}
