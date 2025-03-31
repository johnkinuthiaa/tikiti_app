package com.slippery.tikiti_app.controller;

import com.slippery.tikiti_app.dto.venue.VenueResponse;
import com.slippery.tikiti_app.models.Venue;
import com.slippery.tikiti_app.service.VenueService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping("/find/id")
    public ResponseEntity<VenueResponse> getVenueById(@RequestParam Long id) {
        var existingVenue =service.getVenueById(id);
        return ResponseEntity.ok(existingVenue);
    }
    @GetMapping("/find/name")
    public ResponseEntity<VenueResponse> getVenueByName(@RequestParam String name) {
        var venue =service.getVenueByName(name);
        return ResponseEntity.ok(venue);
    }
    @GetMapping("/find/location")
    public ResponseEntity<VenueResponse> getVenueByLocation(@RequestParam String location) {
        var venue =service.getVenueByLocation(location);
        return  ResponseEntity.ok(venue);
    }

    @DeleteMapping("/{venueId}/delete")
    public ResponseEntity<VenueResponse> deleteVenueById(@PathVariable Long venueId) {
        var deleteVenue =service.deleteVenueById(venueId);
        return ResponseEntity.ok(deleteVenue);
    }

    @GetMapping("/get/all")
    public ResponseEntity<VenueResponse> getAllVenues() {
        var allVenues =service.getAllVenues();
        return ResponseEntity.ok(allVenues);
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<VenueResponse> deleteAllVenue(@RequestParam Long adminId) {
        var message =service.deleteAllVenue(adminId);

        return ResponseEntity.ok(message);
    }
}
