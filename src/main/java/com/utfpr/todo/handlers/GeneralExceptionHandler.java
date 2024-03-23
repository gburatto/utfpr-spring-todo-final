package com.utfpr.todo.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.utfpr.todo.exceptions.NotFoundException;
import com.utfpr.todo.exceptions.ValidationException;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
    Map<String, String> errors = new HashMap<>();
    ex.getBindingResult().getAllErrors().forEach((error) -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      errors.put(fieldName, errorMessage);
    });
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(errors);
  }


  @ExceptionHandler(ValidationException.class)
  public ResponseEntity<?> handleValidationExceptions(ValidationException ex) {
    
    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
        Collections.singletonMap("error", ex.getMessage())
    );

  }

  @ExceptionHandler(NotFoundException.class)
  public ResponseEntity<?> handleNotFoundExceptions(NotFoundException ex) {
    
    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
        Collections.singletonMap("error", ex.getMessage())
    );

  }
  
}
