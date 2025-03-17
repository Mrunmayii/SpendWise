package com.mrunmayi.expensetracker.repo;


import com.mrunmayi.expensetracker.model.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
}
