package com.mrunmayi.expensetracker.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

public record ExpenseResponse(
        @JsonProperty("id")
        Long id,

        @JsonProperty("name")
        String name,

        @JsonProperty("amount")
        Double amount,

        @JsonProperty("category")
        String category,

        @JsonProperty("date")
        Date date
) {
}
