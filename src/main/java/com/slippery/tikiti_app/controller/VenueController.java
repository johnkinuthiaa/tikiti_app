package com.slippery.tikiti_app.controller;

import com.slippery.tikiti_app.dto.venue.VenueResponse;
import com.slippery.tikiti_app.models.Venue;
import com.slippery.tikiti_app.service.VenueService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/venue")
public class VenueController{
    private final VenueService service;

    public VenueController(VenueService service) {
        this.service = service;
    }
    @PostMapping("/create")
    public ResponseEntity<VenueResponse> createNewVenue(@RequestBody Venue venue) {
        var createdVenue =service.createNewVenue(venue);
        return ResponseEntity.status(HttpStatusCode.valueOf(createdVenue.getStatusCode())).body(createdVenue);
    }

    public VenueResponse getVenueById(Long id) {
        return null;
    }

    public VenueResponse getVenueByName(String name) {
        return null;
    }

    public VenueResponse getVenueByLocation(String location) {
        return null;
    }

    public VenueResponse deleteVenueById(Long venueId) {
        return null;
    }

    public VenueResponse getAllVenues() {
        return null;
    }

    public VenueResponse deleteAllVenue(Long adminId) {
        return null;
    }
}
