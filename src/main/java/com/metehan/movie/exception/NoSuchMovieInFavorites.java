package com.metehan.movie.exception;

public class NoSuchMovieInFavorites extends RuntimeException{

    public NoSuchMovieInFavorites(String message) {
        super(message);
    }

    public NoSuchMovieInFavorites(String message, Throwable cause) {
        super(message, cause);
    }
}
