package com.slippery.tikiti_app.dto.venue;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.slippery.tikiti_app.models.Venue;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class VenueResponse {
    private String message;
    private int statusCode;
    private Venue venue;
    private List<Venue> venues;
}
