package com.utfpr.todo.clean.domain.vo.users;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Username {
    
    private String value;

    public Username(String value) {
        this.isInvalidUsername(value);
        this.value = value;
    }

    private void isInvalidUsername(String username) {
        if (username == null || username.length() < 6) {
            throw new ValidationException("Username must have at least 6 characters");
        }
    }
    
    public String getValue() {
        return value;
    }

}
