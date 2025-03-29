package com.slippery.tikiti_app.repository;

import com.slippery.tikiti_app.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<Users,Long> {
    Users findByUsername(String username);
}
