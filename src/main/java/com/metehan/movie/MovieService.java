package com.metehan.movie;

import com.metehan.movie.exception.NoUserDocumentFoundForCurrentUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final UserDocumentRepository userDocumentRepository;

    public List<Integer> getFavorites(String email) {
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                );
        return document.getFavorites();
    }

    public String getNote(String email){
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                );
        return document.getNote();
    }

    public UserDocument getDocument(String email){
        return userDocumentRepository
                    .findUserDocumentByEmail(email).orElseThrow(() ->
                            new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                    );
    }

    public Object addFavorite(String name, Integer filmId) {
    }
}
