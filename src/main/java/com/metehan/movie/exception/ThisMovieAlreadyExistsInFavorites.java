package com.metehan.movie.exception;

public class ThisMovieAlreadyExistsInFavorites extends RuntimeException {
    public ThisMovieAlreadyExistsInFavorites(String message) {
        super(message);
    }

    public ThisMovieAlreadyExistsInFavorites(String message, Throwable cause) {
        super(message, cause);
    }
}
