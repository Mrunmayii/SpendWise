package com.mrunmayi.expensetracker.service;


import com.mrunmayi.expensetracker.dto.ExpenseRequest;
import com.mrunmayi.expensetracker.dto.ExpenseResponse;
import com.mrunmayi.expensetracker.entity.Expense;
import com.mrunmayi.expensetracker.entity.User;
import com.mrunmayi.expensetracker.repo.ExpenseRepo;
import com.mrunmayi.expensetracker.repo.UserRepo;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
//@Transactional
@RequiredArgsConstructor
public class ExpenseService {
    private final ExpenseRepo expenseRepo;
    private final UserRepo userRepo;

    @Transactional
    public List<ExpenseResponse> getAllExpenses(UserDetails userDetails) {
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
        System.out.println("in getallexpenses service");
//        Long userid = user.getId();
//        List<Expense> expenses = expenseRepo.findExpensesBy(userid);
        List<Expense> expenses = expenseRepo.findAllByUser(user);
        return expenses.stream()
                .map(expense -> new ExpenseResponse(
                        expense.getId(),
                        expense.getName(),
                        expense.getAmount(),
                        expense.getCategory(),
                        expense.getDate()
                ))
                .collect(Collectors.toList());
    }

    public String addExpense(ExpenseRequest expenseRequest, UserDetails userDetails) {
        System.out.println("Inside service:DTO is: " + expenseRequest);
        User user = userRepo.findByUsername(userDetails.getUsername())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Expense expense = new Expense();
        expense.setName(expenseRequest.name());
        expense.setCategory(expenseRequest.category());
        expense.setAmount(expenseRequest.amount());
        expense.setDate(expenseRequest.date());
        expense.setUser(user);
        expenseRepo.save(expense);
        System.out.println("Expense saved successfully: " + expense);
        return "Added new expense";
    }

    public void deleteExpense(Long id, UserDetails userDetails) {
        Expense expense = expenseRepo.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));
        System.out.println("username is"+ userDetails.getUsername());
        if(!expense.getUser().getUsername().equals(userDetails.getUsername())) {
            throw new UsernameNotFoundException("Username not found");
        }
        expenseRepo.delete(expense);
    }
}
