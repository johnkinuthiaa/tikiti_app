package com.slippery.tikiti_app.service.impl;

import com.slippery.tikiti_app.dto.movie.MovieResponse;
import com.slippery.tikiti_app.models.Movie;
import com.slippery.tikiti_app.repository.MovieRepository;
import com.slippery.tikiti_app.service.MovieService;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImplementation implements MovieService {
    private final MovieRepository repository;

    public MovieServiceImplementation(MovieRepository repository) {
        this.repository = repository;
    }

    @Override
    public MovieResponse createNewMovie(Movie movie) {
        MovieResponse response =new MovieResponse();
        var existingMovieByName =findMovieByName(movie.getMovieTitle());
        if(existingMovieByName.getStatusCode() !=204){
            response.setMessage("The Movie "+movie.getMovieTitle() +" Was already exists in the database");
            response.setStatusCode(409);
            return response;
        }
        repository.save(movie);
        response.setMessage("New Movie "+movie.getMovieTitle() +" created successfully");
        response.setStatusCode(201);
        response.setMovie(movie);
        return response;
    }

    @Override
    public MovieResponse findMovieById(Long id) {
        return null;
    }

    @Override
    public MovieResponse findMovieByName(String name) {
        return null;
    }
}
