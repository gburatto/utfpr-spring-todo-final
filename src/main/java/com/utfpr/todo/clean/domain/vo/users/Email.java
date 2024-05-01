package com.utfpr.todo.clean.domain.vo.users;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Email {
    
    private String value;

    public Email(String value) {
        this.isInvalidEmail(value);
        this.value = value;
    }

    private void isInvalidEmail(String email) {
        if (email == null || email.length() < 10) {
            throw new ValidationException("Email must have at least 10 characters");
        }
        if (!email.contains("@") || !email.contains(".")) {
            throw new ValidationException("Email must contain \"@\" and \".\"");
        }
    }
    
    public String getValue() {
        return value;
    }

}
