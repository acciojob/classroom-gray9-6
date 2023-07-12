package com.driver.exceptions;

public class TeacherDoesNotExists extends RuntimeException{
    public TeacherDoesNotExists(String message) {
        super(message);
    }
}
