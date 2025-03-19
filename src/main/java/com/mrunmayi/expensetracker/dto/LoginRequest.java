package com.mrunmayi.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;

public record LoginRequest (

        @NotNull(message="Customer username is required")
        @JsonProperty("username")
        String username,

        @NotNull(message = "Password should be present")
        @NotEmpty(message = "Password should be present")
        @NotBlank(message = "Password should be present")
        @Size(min = 5, max = 12)
        @JsonProperty("password")
        String password
){

}
