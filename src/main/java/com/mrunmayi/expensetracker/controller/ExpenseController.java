package com.mrunmayi.expensetracker.controller;

import com.mrunmayi.expensetracker.dto.ExpenseDTO;
import com.mrunmayi.expensetracker.entity.Expense;
import com.mrunmayi.expensetracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<List<Expense>> getExpenses() {
        List<Expense> expenseList = expenseService.getAllExpenses();
        System.out.println("Fetched Expenses: " + expenseList); // Debugging
        return ResponseEntity.ok(expenseList);
    }

    @PostMapping
    public ResponseEntity<String> addExpense(@RequestBody ExpenseDTO expenseDTO) {
        System.out.println("Received request: " + expenseDTO);
        return ResponseEntity.ok(expenseService.addExpense(expenseDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build(); // Returns 204 No Content
    }
}
