package com.metehan.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MovieController {

    private final MovieService movieService;

    //todo
    @GetMapping("/api/v1/favorites")
    public ResponseEntity getFavorites(){
        return  ResponseEntity.ok(
                movieService.getFavorites(SecurityContextHolder.getContext().getAuthentication().getName())
        );
    }

    @GetMapping("/api/v1/note")
    public ResponseEntity getNote(){
        return  ResponseEntity.ok(
                movieService.getNote(SecurityContextHolder.getContext().getAuthentication().getName())
        );
    }

    @GetMapping("/api/v1/document")
    public ResponseEntity getDocument(){
        return  ResponseEntity.ok(
                movieService.getDocument(SecurityContextHolder.getContext().getAuthentication().getName())
        );
    }

    @PostMapping("/api/v1/add-favorite")
    public ResponseEntity addFavorite(@RequestParam Integer filmId){
        return ResponseEntity.ok(
            movieService.addFavorite(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    filmId
            )
        );
    }

}
