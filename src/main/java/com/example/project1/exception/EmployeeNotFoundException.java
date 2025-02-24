package com.example.project1.exception;

public class EmployeeNotFoundException extends Exception{
    private String msg;

    public EmployeeNotFoundException(String msg) {
        super(msg);
        this.msg = msg;
    }
}
