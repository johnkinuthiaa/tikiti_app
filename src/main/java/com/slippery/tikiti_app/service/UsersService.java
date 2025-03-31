package com.slippery.tikiti_app.service;

import com.slippery.tikiti_app.dto.users.UserRequest;
import com.slippery.tikiti_app.dto.users.UserResponse;

public interface UsersService {
    UserResponse createNewUser(UserRequest user);
    UserResponse findAllUsers();
    UserResponse deleteAllUsers();
    UserResponse deleteUserById(Long userId);
    UserResponse findUserById(Long userId);
}
