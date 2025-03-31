package com.slippery.tikiti_app.service.impl;

import com.slippery.tikiti_app.dto.venue.VenueResponse;
import com.slippery.tikiti_app.models.Seat;
import com.slippery.tikiti_app.models.Venue;
import com.slippery.tikiti_app.repository.VenueRepository;
import com.slippery.tikiti_app.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class VenueServiceImplementation implements VenueService {
    private final VenueRepository repository;

    public VenueServiceImplementation(VenueRepository repository) {
        this.repository = repository;
    }

    @Override
    public VenueResponse createNewVenue(Venue venue) {
        VenueResponse response =new VenueResponse();
        var existingVenue =existingVenue(venue.getName());
        if(existingVenue){
            response.setMessage("venue with the name "+venue.getName()+" already exists");
            response.setStatusCode(409);
            return response;
        }
        venue.setMovies(new ArrayList<>());
        for(Seat seat:venue.getSeatsInVenue()){
            seat.setVenue(venue);
        }
        repository.save(venue);
        response.setMessage("venue with the name "+venue.getName()+" created successfully");
        response.setStatusCode(201);
        response.setVenue(venue);
        return response;
    }

    @Override
    public VenueResponse getVenueById(Long id) {
        return null;
    }

    @Override
    public VenueResponse getVenueByName(String name) {
        return null;
    }

    @Override
    public VenueResponse getVenueByLocation(String location) {
        return null;
    }

    @Override
    public VenueResponse deleteVenueById(Long venueId) {
        return null;
    }

    @Override
    public VenueResponse getAllVenues() {
        return null;
    }

    @Override
    public VenueResponse deleteAllVenue(Long adminId) {
        return null;
    }

    public boolean existingVenue(String name){
        var existingVenue =repository.findByName(name);
        return existingVenue !=null;
    }
}
