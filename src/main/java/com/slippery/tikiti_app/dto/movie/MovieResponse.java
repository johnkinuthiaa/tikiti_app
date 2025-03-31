package com.slippery.tikiti_app.dto.movie;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.tikiti_app.models.Movie;
import lombok.Data;
import java.util.List;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {
    private String message;
    private int statusCode;
    private Movie movie;
    private List<Movie> movies;
}
