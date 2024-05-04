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

        if (name == null) {
            throw new ValidationException("Name must not be null");
        }
        
        Pattern numbers = Pattern.compile("[0-9]");
        Pattern symbols = Pattern.compile("[\\/!@#$%&*()_+=|<>?{},;:\\[\\]~^§¹²³£¢¬°\"]");
        Matcher hasNumber = numbers.matcher(name);
        Matcher hadSymbol = symbols.matcher(name);

        if (hasNumber.find() || hadSymbol.find()) {
            throw new ValidationException("Name cannot contain numbers or symbols");
        }
    }
    
    public String getValue() {
        return value;
    }

}
