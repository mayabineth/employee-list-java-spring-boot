package com.example.demo.exception;

public class EmployeeAlreadyExistException extends RuntimeException {
    public EmployeeAlreadyExistException(String id){
        super("employee "+id+" already exists");

    }
}
