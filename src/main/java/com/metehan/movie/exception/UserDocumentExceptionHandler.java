package com.metehan.movie.exception;

import com.metehan.movie.UserDocument;
import com.metehan.movie.UserDocumentRepository;
import lombok.RequiredArgsConstructor;
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
                        )
        );
        return ResponseEntity.ok(document);
    }

}
