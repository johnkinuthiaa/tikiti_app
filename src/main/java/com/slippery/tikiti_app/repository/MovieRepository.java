package com.slippery.tikiti_app.repository;

import com.slippery.tikiti_app.models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie,Long> {
}
