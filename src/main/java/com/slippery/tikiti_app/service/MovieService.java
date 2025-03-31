package com.slippery.tikiti_app.service;
import com.slippery.tikiti_app.dto.movie.MovieResponse;

import com.slippery.tikiti_app.models.Movie;

public interface MovieService {
    MovieResponse createNewMovie(Movie movie);
    MovieResponse findMovieById(Long id);
    MovieResponse findMovieByName(String name);
}
