package com.slippery.tikiti_app.service.impl;

import com.slippery.tikiti_app.dto.users.UserDto;
import com.slippery.tikiti_app.dto.users.UserRequest;
import com.slippery.tikiti_app.dto.users.UserResponse;
import com.slippery.tikiti_app.models.Users;
import com.slippery.tikiti_app.repository.UserRepository;
import com.slippery.tikiti_app.service.UsersService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImplementation implements UsersService {
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder =new BCryptPasswordEncoder(12);
    private final AuthenticationManager authenticationManager;

    public UserServiceImplementation(UserRepository repository, AuthenticationManager authenticationManager) {
        this.repository = repository;
        this.authenticationManager = authenticationManager;
    }

    @Override
    public UserResponse createNewUser(UserRequest user) {
        UserResponse response =new UserResponse();
        UserDto userDto =new UserDto();
        Users newUser =new Users();

//        check if user already exists
        var existingUserByEmail =userExistsByEmail(user.getEmail());
        if(existingUserByEmail){
            response.setMessage("User with the email "+user.getEmail()+" already exists...Please log in " +
                    "to your account or try a different email");
            response.setStatusCode(409);
            return response;
        }
        var existingUserByUsername =userExistsByUsername(user.getUsername());
        if(existingUserByUsername){
            response.setMessage("User with the username "+user.getUsername()+" already exists...Please log in " +
                    "to your account or try a different unique username");
            response.setStatusCode(409);
            return response;
        }

//        creating the new user
        newUser.setEmail(user.getEmail());
        newUser.setRole(user.getRole());
        newUser.setUsername(user.getUsername());
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        repository.save(newUser);

//        setting the userDto...should user an object mapper though
        userDto.setEmail(newUser.getEmail());
        userDto.setId(newUser.getId());
        userDto.setRole(newUser.getRole());
        userDto.setUsername(newUser.getUsername());
        userDto.setCreatedOn(newUser.getCreatedOn());

//      response
        response.setMessage("New user created successfully");
        response.setStatusCode(201);
        response.setUser(userDto);
        return response;
    }

    @Override
    public UserResponse findAllUsers() {
        UserResponse response =new UserResponse();
        UserDto userDto =new UserDto();
        var allUsers =repository.findAll();

        if(allUsers.isEmpty()){
            response.setMessage("No user present in the database");
            response.setStatusCode(404);
            return response;
        }

        List<UserDto> userDtoList =new ArrayList<>();

        for(Users user :allUsers){
            userDto.setUsername(user.getUsername());
            userDto.setEmail(user.getEmail());
            userDto.setRole(user.getRole());
            userDto.setId(user.getId());
            userDto.setCreatedOn(user.getCreatedOn());
            userDtoList.add(userDto);
        }
        response.setUsers(userDtoList);
        response.setMessage("All users in the system");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserResponse deleteAllUsers() {
        UserResponse response =new UserResponse();
        var allUsers =repository.findAll();
        if(allUsers.isEmpty()){
            response.setStatusCode(203);
            response.setMessage("No users found in the database");
            return response;
        }
        repository.deleteAll();
        response.setMessage("All users in the database deleted");
        response.setStatusCode(200);
        return response;
    }

    @Override
    public UserResponse deleteUserById(Long userId) {
        UserResponse response =new UserResponse();
        var user =findUserById(userId);
        if(user.getStatusCode() !=200){
            return user;
        }
        repository.deleteById(user.getUser().getId());
        response.setMessage("User with id "+userId+" deleted successfully");
        response.setStatusCode(203);
        return response;
    }

    @Override
    public UserResponse findUserById(Long userId) {
        UserResponse response =new UserResponse();
        UserDto userDto =new UserDto();
        var user =repository.findById(userId);
        if(user.isEmpty()){
            response.setMessage("User with id "+userId+" was not found");
            response.setStatusCode(404);
            return response;
        }
//        creating the dto
        userDto.setUsername(user.get().getUsername());
        userDto.setEmail(user.get().getEmail());
        userDto.setId(user.get().getId());
        userDto.setRole(user.get().getRole());
        userDto.setCreatedOn(user.get().getCreatedOn());

        response.setUser(userDto);
        response.setMessage("User with id "+user.get().getId());
        response.setStatusCode(200);
        return response;
    }
//    checks for existingUser by Email and username

    public Boolean userExistsByEmail(String email){
        Users existingUser =repository.findByEmail(email);

        return existingUser != null;
    }
    public Boolean userExistsByUsername(String username){
        Users existingUser =repository.findByUsername(username);
        return existingUser != null;
    }
}
