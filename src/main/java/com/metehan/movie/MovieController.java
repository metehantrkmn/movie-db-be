package com.metehan.movie;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
        public ResponseEntity getNote(@RequestParam IdPojo pojo ){
        return  ResponseEntity.ok(
                movieService.getNote(SecurityContextHolder.getContext().getAuthentication().getName(),
                                     pojo.getMovieId())
        );
    }

    @GetMapping("/api/v1/document")
    public ResponseEntity getDocument(){
        return  ResponseEntity.ok(
                movieService.getDocument(SecurityContextHolder.getContext().getAuthentication().getName())
        );
    }

    @PostMapping("/api/v1/add-favorite")
    public ResponseEntity addFavorite(@RequestBody IdPojo pojo){
        return ResponseEntity.ok(
            movieService.addFavorite(
                    SecurityContextHolder.getContext().getAuthentication().getName(),
                    pojo.getMovieId()
            )
        );
    }

   @PostMapping("/api/v1/add-note")
    public ResponseEntity addNote(@RequestBody IdPojo pojo){
        return ResponseEntity.ok(
                movieService.addNote(
                        SecurityContextHolder.getContext().getAuthentication().getName(),
                        pojo.getMovieId(),
                        pojo.getNote()
                )
        );
   }

   @PostMapping("/api/v1/delete-favorite")
    public ResponseEntity deleteFromFavorite(@RequestBody IdPojo pojo){
        movieService.deleteFavorite(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                pojo.getMovieId()
        );
        return ResponseEntity.ok("succesfuly deleted");
   }

   @PostMapping("/api/v1/delete-note")
    public ResponseEntity deleteNote(@RequestBody IdPojo pojo){
        movieService.deleteNote(
                SecurityContextHolder.getContext().getAuthentication().getName(),
                pojo.getMovieId()
        );
        return ResponseEntity.ok("succesfully deleted the note from favorite film");
   }



}


