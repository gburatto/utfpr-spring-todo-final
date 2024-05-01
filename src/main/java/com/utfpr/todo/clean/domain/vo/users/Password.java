package com.utfpr.todo.clean.domain.vo.users;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Password {
    
    private String value;

    public Password(String value) {
        this.isInvalidPassword(value);
        this.value = value;
    }

    private void isInvalidPassword(String password) {
        if (password == null || password.length() < 6) {
            throw new ValidationException("Password must have at least 6 characters");
        }
    }
    
    public String getValue() {
        return value;
    }

}
