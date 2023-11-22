package com.bryan.registrationlogindemo.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import lombok.Builder

@Builder
class UserDto {

    Long id
    @NotEmpty
    String firstName
    @NotEmpty
    String lastName
    @NotEmpty(message = "Email should not be empty")
    @Email
    String email
    @NotEmpty(message = "Password should not be empty")
    String password
}
