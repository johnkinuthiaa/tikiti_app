package com.slippery.tikiti_app.service.impl;

import com.slippery.tikiti_app.dto.venue.VenueResponse;
import com.slippery.tikiti_app.models.Seat;
import com.slippery.tikiti_app.models.Venue;
import com.slippery.tikiti_app.repository.VenueRepository;
import com.slippery.tikiti_app.service.VenueService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class VenueServiceImplementation implements VenueService {
    private final VenueRepository repository;

    public VenueServiceImplementation(VenueRepository repository) {
        this.repository = repository;
    }

    @Override
    public VenueResponse createNewVenue(Venue venue) {
        VenueResponse response =new VenueResponse();
        var existingVenue =existingVenue(venue.getName().strip());
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
        VenueResponse response =new VenueResponse();
        Optional<Venue> existingVenue =repository.findById(id);
        if(existingVenue.isEmpty()){
            response.setMessage("No venue with id "+id+" was found");
            response.setStatusCode(204);
            return response;
        }
        response.setVenue(existingVenue.get());
        response.setStatusCode(200);
        response.setMessage("Venue with id "+id+" was found");
        return response;
    }

    @Override
    public VenueResponse getVenueByName(String name) {
        VenueResponse response =new VenueResponse();
        var existingVenueByName =repository.findAll().stream()
                .filter(venue->venue.getName().toLowerCase().contains(name.toLowerCase()))
                .toList();
        if(existingVenueByName.isEmpty()){
            response.setMessage("No venue with the name "+name+" was found");
            response.setStatusCode(204);
            return response;
        }
        response.setMessage("Venue with the name "+name);
        response.setStatusCode(200);
        response.setVenues(existingVenueByName);

        return response;
    }

    @Override
    public VenueResponse getVenueByLocation(String location) {
        VenueResponse response =new VenueResponse();
        var existingLocation =repository.findAll().stream()
                .filter(venue->venue.getLocation().toLowerCase().contains(location.toLowerCase()))
                .toList();
        if(existingLocation.isEmpty()){
            response.setStatusCode(204);
            response.setMessage("No theatre with in "+location+" was found");
            return response;
        }
        response.setVenues(existingLocation);
        response.setStatusCode(200);
        response.setMessage("Theatres in "+location);

        return response;
    }

    @Override
    public VenueResponse deleteVenueById(Long venueId) {
        VenueResponse response =new VenueResponse();
        var existingVenue =getVenueById(venueId);
        if(existingVenue.getStatusCode() !=200){
            return existingVenue;
        }
        repository.deleteById(existingVenue.getVenue().getId());
        response.setMessage("Venue with id "+venueId+" was deleted!");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public VenueResponse getAllVenues() {
        VenueResponse response =new VenueResponse();
        var allVenues =repository.findAll();
        if(allVenues.isEmpty()){
            response.setMessage("The venue list is empty");
            response.setStatusCode(204);
            return response;
        }
        response.setVenues(allVenues);
        response.setMessage("All venues in database");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public VenueResponse deleteAllVenue(Long adminId) {
        VenueResponse response =new VenueResponse();
        var allVenues =getAllVenues();
        if(allVenues.getStatusCode() !=200){
            return allVenues;
        }
        repository.deleteAll(allVenues.getVenues());
        response.setMessage(allVenues.getMessage()+" deleted successfully");
        response.setStatusCode(204);
        return response;
    }

    public boolean existingVenue(String name){
        var existingVenue =repository.findByName(name);
        return existingVenue !=null;
    }
}
