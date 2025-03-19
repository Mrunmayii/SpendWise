package com.mrunmayi.expensetracker.repo;


import com.mrunmayi.expensetracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
}
