package com.driver.exceptions;

public class StudentDoesNotExists extends RuntimeException{
    public StudentDoesNotExists(String message) {
        super(message);
    }
}
