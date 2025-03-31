package com.slippery.tikiti_app.dto.users;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserRequest {

    @NotBlank(message = "Username should not be blank")
    @Min(value = 3,message = "Username should have more than 3 characters")
    @Max(value = 20,message = "Username should not have more than 20 characters")
    private String username;

    @NotBlank(message = "Email should not be blank")
    @Email
    private String email;

    @NotBlank(message = "Password should not be blank")
    @Min(value = 6,message = "passwords should have more than 6 characters")
    @Max(value = 20,message = "passwords should not have more than 20 characters")
    private String password;

    @NotBlank(message = "Role should not be blank")
    @Min(value = 3,message = "Role should have more than 3 characters")
    @Max(value = 20,message = "Role should not have more than 20 characters")
    private String role;
}
