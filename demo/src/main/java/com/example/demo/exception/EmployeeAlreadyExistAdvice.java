package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class EmployeeAlreadyExistAdvice {
    @ResponseBody
    @ExceptionHandler(EmployeeAlreadyExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    String EmployeeNotFoundHandler(EmployeeAlreadyExistException exception){
        return exception.getMessage();
    }

}
