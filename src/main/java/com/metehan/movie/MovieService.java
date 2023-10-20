package com.metehan.movie;

import com.metehan.movie.exception.NoSuchMovieInFavorites;
import com.metehan.movie.exception.NoUserDocumentFoundForCurrentUser;
import com.metehan.movie.exception.ThisMovieAlreadyExistsInFavorites;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final UserDocumentRepository userDocumentRepository;

    public List<Movie> getFavorites(String email) {
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                );
        return document.getFavorites();
    }

    public String getNote(String email, Integer movieId){
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                );

        Movie movie = document.getFavorites().stream()
                .filter(m -> m.getMovieId() == movieId)
                .findFirst()
                .orElseThrow(() -> new NoSuchMovieInFavorites("no movie find with such movieId in favorites"));

        return movie.getNote();
    }

    public UserDocument getDocument(String email){
        return userDocumentRepository
                    .findUserDocumentByEmail(email).orElseThrow(() ->
                            new NoUserDocumentFoundForCurrentUser("Couldnt find and document for the current user")
                    );
    }

    public List<Movie> addFavorite(String email, Integer filmId) {
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Coulnd'n find any document for the current user")
                );

        //check if favorites list null
        //other way it throw exception out of your controll
        System.out.println("add favorite debug 1");
        List<Movie> favorites = document.getFavorites();
        if(favorites == null)
            favorites = new ArrayList<>();


        //checks if filmId already exists in list.
        for(Movie m: favorites){
            if(m.getMovieId() == filmId)
                throw new ThisMovieAlreadyExistsInFavorites("this movie already added to favorites");
        }

        //creates new Movie object and add it to the favorites list
        favorites.add(
                Movie.builder()
                        .movieId(filmId)
                        .note(null)
                        .build()
        );

        //if there was no element in the favorites list at first then the favorites list object is not the one that document have(it is null in document)
        //so the changes on the favorites list do not reflect to document objects favorite list
        //you have to set it in order to truely save to the database
        document.setFavorites(favorites);

        //saves the updates to database
        userDocumentRepository.save(document);

        return favorites;
    }

    //you can use this method also for the update note requests
    //it does the same think what the update method will do
    public UserDocument addNote(String email, Integer movieId, String note){
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Coulnd'n find any document for the current user")
                );

        Movie movie = document.getFavorites().stream()
                .filter((m) -> m.getMovieId() == movieId)
                .findFirst()
                .orElseThrow(() -> new NoSuchMovieInFavorites("no such movie in favorites"));

        movie.setNote(note);
        userDocumentRepository.save(document);

        return document;
    }

    public UserDocument deleteNote(String email, Integer movieId){
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Coulnd'n find any document for the current user")
                );

        Movie movie = document.getFavorites().stream()
                .filter((m) -> m.getMovieId() == movieId)
                .findFirst()
                .orElseThrow(() -> new NoSuchMovieInFavorites("no such movie in favorites"));

        movie.setNote(null);
        userDocumentRepository.save(document);

        return document;
    }

    public UserDocument deleteFavorite(String email, Integer movieId){
        UserDocument document = userDocumentRepository
                .findUserDocumentByEmail(email).orElseThrow(() ->
                        new NoUserDocumentFoundForCurrentUser("Coulnd'n find any document for the current user")
                );

        document.getFavorites().removeIf(m -> m.getMovieId() == movieId);
        userDocumentRepository.save(document);
        return document;
    }



}
