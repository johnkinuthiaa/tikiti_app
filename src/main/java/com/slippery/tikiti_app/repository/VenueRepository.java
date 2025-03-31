package com.slippery.tikiti_app.repository;

import com.slippery.tikiti_app.models.Venue;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VenueRepository extends JpaRepository<Venue,Long> {
    Venue findByName(String name);
}
