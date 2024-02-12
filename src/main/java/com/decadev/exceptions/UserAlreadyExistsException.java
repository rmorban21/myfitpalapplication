package com.decadev.exceptions;

public class UserAlreadyExistsException extends Exception {
    public UserAlreadyExistsException() {
    }

    public UserAlreadyExistsException(String s) {
        super(s);
    }

    public UserAlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserAlreadyExistsException(Throwable cause) {
        super(cause);
    }

}
