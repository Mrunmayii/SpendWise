package com.mrunmayi.expensetracker.repo;


import com.mrunmayi.expensetracker.entity.Expense;
import com.mrunmayi.expensetracker.entity.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepo extends JpaRepository<Expense, Long> {
    @EntityGraph(attributePaths = {"user"})
    List<Expense> findAllByUser(User user);
}
