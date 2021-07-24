package com.example.pack.exception;

public class ClientIdNotFoundException extends RuntimeException {

    public ClientIdNotFoundException(String message) {
        super(message);
    }
}
