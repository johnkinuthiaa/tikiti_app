package com.slippery.tikiti_app.controller;

import com.slippery.tikiti_app.dto.users.UserRequest;
import com.slippery.tikiti_app.dto.users.UserResponse;
import com.slippery.tikiti_app.service.UsersService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
public class UserController{
    private final UsersService service;

    public UserController(UsersService service) {
        this.service = service;
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody @Valid UserRequest request){
        var createdUser =service.createNewUser(request);
        return ResponseEntity.status(HttpStatusCode.valueOf(createdUser.getStatusCode())).body(createdUser);
    }
    @GetMapping("/all")
    public ResponseEntity<UserResponse> findAllUsers(){
        var allUsers =service.findAllUsers();
        return ResponseEntity.status(HttpStatusCode.valueOf(allUsers.getStatusCode())).body(allUsers);
    }
    @DeleteMapping("/delete/all")
    public ResponseEntity<UserResponse> deleteAllUsers(){
        var deletedUsers =service.deleteAllUsers();
        return ResponseEntity.status(HttpStatusCode.valueOf(deletedUsers.getStatusCode())).body(deletedUsers);
    }

    @DeleteMapping("/{userId}/delete")
    public ResponseEntity<UserResponse> deleteUserById(@PathVariable Long userId) {
        var deletedUser =service.deleteUserById(userId);
        return ResponseEntity.status(HttpStatusCode.valueOf(deletedUser.getStatusCode())).body(deletedUser);
    }

    @GetMapping("/{userId}/get")
    public ResponseEntity<UserResponse> findUserById(@PathVariable Long userId) {
        var foundUser =service.findUserById(userId);
        return ResponseEntity.status(HttpStatusCode.valueOf(foundUser.getStatusCode())).body(foundUser);
    }
}
