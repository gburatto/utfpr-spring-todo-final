package com.utfpr.todo.clean.domain.vo;

import com.utfpr.todo.exceptions.ValidationException;

public class Title {

    private String value;

    public Title(String value) {
        this.isInvalidTitle(value);
        this.value = value;
    }

    private void isInvalidTitle(String title) {
        if (title == null || title.length() < 5) {
            throw new ValidationException("Title must have at least 5 characters");
        }
    }
    
    public String getValue() {
        return value;
    }

}
