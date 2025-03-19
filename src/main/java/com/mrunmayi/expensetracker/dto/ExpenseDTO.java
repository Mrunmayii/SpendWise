package com.mrunmayi.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

public record ExpenseDTO (


        @NotNull(message="Expense name is required")
        @NotEmpty(message = "Name is required")
        @JsonProperty("name")
        String name,

        @NotNull(message="Amount is required")
        @NotEmpty(message = "Amount is required")
        @JsonProperty("amount")
        Double amount,

        @JsonProperty("category")
        String category,

        @JsonProperty("date")
        Date date
    ){

    }
