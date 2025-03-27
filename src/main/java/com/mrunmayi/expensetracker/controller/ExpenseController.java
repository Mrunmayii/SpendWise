package com.mrunmayi.expensetracker.controller;

import com.mrunmayi.expensetracker.dto.ExpenseRequest;
import com.mrunmayi.expensetracker.dto.ExpenseResponse;
import com.mrunmayi.expensetracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/expenses")
public class ExpenseController {
    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @GetMapping("/test")
    public String testEndpoint() {
        System.out.println("✅ Test endpoint hit!");
        return "Test successful!";
    }

    @GetMapping
    public ResponseEntity<List<ExpenseResponse>> getExpenses(@AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Inside getExpenses");
        System.out.println("user details are: "+ userDetails.getUsername());
        List<ExpenseResponse> expenseList = expenseService.getAllExpenses(userDetails);
        System.out.println("Fetched Expenses: " + expenseList); // Debugging
        return ResponseEntity.ok(expenseList);
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseRequest expenseRequest, @AuthenticationPrincipal UserDetails userDetails) {
        System.out.println("Received request: " + expenseRequest);
        return ResponseEntity.ok(expenseService.addExpense(expenseRequest, userDetails));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id, @AuthenticationPrincipal UserDetails userDetails) {
        expenseService.deleteExpense(id, userDetails);
        return ResponseEntity.noContent().build();
    }
}
