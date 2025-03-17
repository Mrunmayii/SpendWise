package com.mrunmayi.expensetracker.service;


import com.mrunmayi.expensetracker.model.Expense;
import com.mrunmayi.expensetracker.repo.ExpenseRepo;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ExpenseService {
    private final ExpenseRepo expenseRepo;

    public ExpenseService(ExpenseRepo expenseRepo) {
        this.expenseRepo = expenseRepo;
    }

    public List<Expense> getAllExpenses() {
        return expenseRepo.findAll();
    }

    public Expense addExpense(Expense expense) {
        return expenseRepo.save(expense);
    }

    public void deleteExpense(Long id) {
        expenseRepo.deleteById(id);
    }
}
