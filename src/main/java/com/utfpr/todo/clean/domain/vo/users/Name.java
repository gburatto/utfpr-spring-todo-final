package com.utfpr.todo.clean.domain.vo.users;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.utfpr.todo.exceptions.ValidationException;

// value object
public class Name {
    
    private String value;

    public Name(String value) {
        this.isInvalidName(value);
        this.value = value;
    }

    private void isInvalidName(String name) {

        Pattern numbers = Pattern.compile("[0-9]");
        Pattern symbols = Pattern.compile("[\\/!@#$%&*()_+=|<>?{},;:\\[\\]~^§¹²³£¢¬°\"]");
        Matcher hasNumber = numbers.matcher(name);
        Matcher hadSymbol = symbols.matcher(name);

        if (name == null || hasNumber.find() || hadSymbol.find()) {
            throw new ValidationException("Name must not be null and cannot contain numbers or symbols");
        }
    }
    
    public String getValue() {
        return value;
    }

}
