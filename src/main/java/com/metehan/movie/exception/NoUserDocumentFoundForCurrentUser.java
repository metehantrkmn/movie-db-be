package com.metehan.movie.exception;

public class NoUserDocumentFoundForCurrentUser extends RuntimeException {
    public NoUserDocumentFoundForCurrentUser(String message) {
        super(message);
    }

    public NoUserDocumentFoundForCurrentUser(String message, Throwable cause) {
        super(message, cause);
    }
}
