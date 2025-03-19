package com.mrunmayi.expensetracker.service;


import com.mrunmayi.expensetracker.dto.ExpenseDTO;
import com.mrunmayi.expensetracker.entity.Expense;
import com.mrunmayi.expensetracker.repo.ExpenseRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
//@Transactional
public class ExpenseService {
    private final ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public String addExpense(ExpenseDTO expenseDTO) {
        System.out.println("Inside service:DTO is: " + expenseDTO); // Log before saving
        Expense expense = new Expense();
        expense.setName(expenseDTO.name());
        expense.setCategory(expenseDTO.category());
        expense.setAmount(expenseDTO.amount());
        expense.setDate(expenseDTO.date());
        expenseRepo.save(expense);
        System.out.println("Expense saved successfully: " + expense);
        return "Added new expense";
    }

    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
