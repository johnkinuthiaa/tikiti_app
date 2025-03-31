package com.slippery.tikiti_app.service;

import com.slippery.tikiti_app.dto.venue.VenueResponse;
import com.slippery.tikiti_app.models.Venue;

public interface VenueService {
    VenueResponse createNewVenue(Venue venue);
    VenueResponse getVenueById(Long id);
    VenueResponse getVenueByName(String name);
    VenueResponse getVenueByLocation(String location);
    VenueResponse deleteVenueById(Long venueId);
    VenueResponse getAllVenues();
    VenueResponse deleteAllVenue(Long adminId);
}
