package com.slippery.tikiti_app.controller;
import com.slippery.tikiti_app.dto.movie.MovieResponse;
import com.slippery.tikiti_app.models.Movie;
import com.slippery.tikiti_app.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController{
    private final MovieService service;

    public MovieController(MovieService service) {
        this.service = service;
    }
    @PostMapping("/create/movie")
    public ResponseEntity<MovieResponse> createNewMovie(@RequestBody Movie movie) {
        var createdMovie =service.createNewMovie(movie);
        return ResponseEntity.ok(createdMovie);
    }
    @GetMapping("/find/{id}")
    public ResponseEntity<MovieResponse> findMovieById(@PathVariable Long id) {
        var foundMovie =service.findMovieById(id);
        return ResponseEntity.ok(foundMovie);
    }
    @GetMapping("/find/name")
    public ResponseEntity<MovieResponse> findMovieByName(@RequestParam String name) {
        var foundMovieByName =service.findMovieByName(name);
        return ResponseEntity.ok(foundMovieByName);
    }
}
