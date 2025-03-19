package com.mrunmayi.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record SignUpRequest (
    @NotNull(message="Customer username is required")
    @JsonProperty("username")
    String username,

    @NotNull(message = "Password should be present")
    @NotEmpty(message = "Password should be present")
    @NotBlank(message = "Password should be present")
    @Size(min = 5, max = 12)
    @JsonProperty("password")
    String password,

    @NotNull(message = "email should be present")
    @NotEmpty(message = "email should be present")
    @NotBlank(message = "email should be present")
    @JsonProperty("email")
    String email,

    @NotNull(message = "Name should be present")
    @NotEmpty(message = "Name should be present")
    @NotBlank(message = "Name should be present")
    @JsonProperty("firstName")
    String firstname,

    @JsonProperty("lastName")
    String lastname
    ){

}
