package com.metehan.movie.exception;

import com.metehan.movie.UserDocument;
import com.metehan.movie.UserDocumentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
@RequiredArgsConstructor
public class UserDocumentExceptionHandler {

    private final UserDocumentRepository userDocumentRepository;

    //in case user authenticated somehow but have no user document in the mongo database
    //it creates a new empty one and send it as response
    @ExceptionHandler(value = {NoUserDocumentFoundForCurrentUser.class})
    public ResponseEntity handleNoDocumentFoundException(NoUserDocumentFoundForCurrentUser ex){
        UserDocument document =(UserDocument) userDocumentRepository.save(
                UserDocument.builder()
                        .email(
                                SecurityContextHolder.getContext().getAuthentication().getName()
                        ).build()
        );
        return ResponseEntity.ok(document);
    }

    @ExceptionHandler(value={ThisMovieAlreadyExistsInFavorites.class})
    public ResponseEntity handleMovieAlreadyExistsInFavorites(ThisMovieAlreadyExistsInFavorites ex){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(
                ex.getMessage()
        );
    }

    @ExceptionHandler(value={NoSuchMovieInFavorites.class})
    public ResponseEntity handleNoSuchMovieInFavorites(NoSuchMovieInFavorites ex){
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status)
                .body(ex.getMessage());
    }

}
